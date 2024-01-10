package com.minin.diarybackend.config.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minin.diarybackend.exceptions.AppError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.io.PrintWriter;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {

        AppError appError = new AppError(403, "Access denied");
        PrintWriter out = response.getWriter();

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        out.print(mapper.writeValueAsString(appError));
        out.flush();
    }

}
