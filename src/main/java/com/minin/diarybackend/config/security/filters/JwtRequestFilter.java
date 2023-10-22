package com.minin.diarybackend.config.security.filters;

import com.minin.diarybackend.config.security.custom.CustomPrincipal;
import com.minin.diarybackend.services.credentials.ICredentialsService;
import com.minin.diarybackend.utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;
    private final ICredentialsService credentialsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            jwt = authHeader.substring(7);

            try {
                username = jwtTokenUtils.getUsername(jwt);
            } catch (ExpiredJwtException e) {
                log.debug("expired_token");
            } catch (SignatureException e) {
                log.debug("wrong_signature");
            }

        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UUID identityId = credentialsService.findByUsername(username)
                    .getIdentity()
                    .getId();

            CustomPrincipal token = new CustomPrincipal(
                    username,
                    null,
                    jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
                    identityId
            );

            SecurityContextHolder.getContext().setAuthentication(token);

        }

        filterChain.doFilter(request, response);

    }

}
