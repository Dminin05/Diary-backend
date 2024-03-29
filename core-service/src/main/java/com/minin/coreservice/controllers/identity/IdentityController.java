package com.minin.coreservice.controllers.identity;

import com.minin.coreservice.models.Identity;
import com.minin.coreservice.services.identity.IIdentityService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Hidden
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
