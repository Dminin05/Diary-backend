package com.minin.uploadservice.services;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.minin.dtos.messages.MessageFiles;
import com.minin.uploadservice.properties.S3Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadService implements IUploadService {

    private final S3Properties s3Properties;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void uploadFiles(UUID messageId, List<MultipartFile> files) {

        List<String> filesUrl = new ArrayList<>();
        AmazonS3 amazonS3 = createConnectionToObjectStorage();

        try(ExecutorService executorService = Executors.newFixedThreadPool(files.size())) {

            List<Future<String>> futures = new ArrayList<>();

            for (MultipartFile multipartFile : files) {

                Future<String> futureUrl = executorService.submit(() -> {

                    String fileName = generateUniqueName();
                    ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentLength(multipartFile.getBytes().length);

                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(multipartFile.getBytes());
                    amazonS3.putObject(s3Properties.getName(), fileName, byteArrayInputStream, metadata);

                    return amazonS3.getUrl(s3Properties.getName(), fileName).toExternalForm();
                });

                futures.add(futureUrl);
            }

            for (Future<String> futureUrl : futures) {
                try  {
                    String url = futureUrl.get();
                    filesUrl.add(url);
                } catch (InterruptedException | ExecutionException e) {
                    log.error("one_of_the_thread_has_an_exception", e.getMessage());
                    throw new AmazonS3Exception(e.getMessage());
                }
            }

        } catch (AmazonS3Exception e) {
            log.error("error_uploading_files", e.getMessage());
            throw new AmazonS3Exception(e.getMessage());
        }

        MessageFiles messageFiles = new MessageFiles(messageId, filesUrl);
        kafkaTemplate.send("upload-files", messageFiles);
    }

    private AmazonS3 createConnectionToObjectStorage() {

        final AmazonS3 amazonS3;

        try {
            amazonS3 = AmazonS3ClientBuilder.standard()
                    .withEndpointConfiguration(
                            new com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration(
                                    "https://storage.yandexcloud.net",
                                    "ru-central1"
                            )
                    )
                    .withCredentials(
                            new AWSStaticCredentialsProvider(
                                    new BasicAWSCredentials(s3Properties.getAccessKeyId(), s3Properties.getSecretAccessKey())
                            )
                    )
                    .build();
        } catch (SdkClientException e) {
            log.error("error_creating_client_for_object_storage", e.getMessage());
            throw new SdkClientException(e.getMessage());
        }

        return amazonS3;
    }

    private String generateUniqueName() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
