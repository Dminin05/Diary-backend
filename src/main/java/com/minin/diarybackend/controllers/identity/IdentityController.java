package com.minin.diarybackend.controllers.identity;

import com.minin.diarybackend.models.Identity;
import com.minin.diarybackend.services.identity.IIdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/identities")
public class IdentityController {

    private final IIdentityService identityService;

    @GetMapping
    public List<Identity> findAll() {
        return identityService.findAll();
    }

    @GetMapping("{id}")
    public Identity findById(@PathVariable UUID id) {
        return identityService.findById(id);
    }

}
