package com.example.diarybackend.controllers.identity;

import com.example.diarybackend.models.Identity;
import com.example.diarybackend.services.identity.IIdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/identity")
public class IdentityController {

    private final IIdentityService identityService;

    @GetMapping
    public List<Identity> findAll() {
        return identityService.findAll();
    }

    @GetMapping("{id}")
    public Identity findById(@PathVariable UUID id) {
        return identityService.findById(id).get();
    }

//    @PostMapping
//    public ResponseEntity<Identity> create(@RequestBody IdentityCreateRequest identity) {
//        return ResponseEntity.ok(identityService.create(identity));
//    }

}
