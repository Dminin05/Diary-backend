package com.minin.diarybackend.config.security.custom;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

@Getter
public class CustomPrincipal extends UsernamePasswordAuthenticationToken {

    private final UUID identityId;

    public CustomPrincipal(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, UUID identityId) {
        super(principal, credentials, authorities);
        this.identityId = identityId;
    }

}
