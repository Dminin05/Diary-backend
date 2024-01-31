package com.minin.coreservice.config.security.filters;

import com.minin.coreservice.services.auth.dtos.ClaimsForToken;
import com.minin.coreservice.utils.JwtTokenUtils;
import com.minin.custom.CustomPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {

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

            UUID identityId = jwtTokenUtils.getIdentityIdFromAccessToken(authHeader.substring(7));
            String email = jwtTokenUtils.getEmailFromAccessToken(authHeader.substring(7));
            boolean isVerified = jwtTokenUtils.getIsVerifiedFromAccessToken(authHeader.substring(7));

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    new CustomPrincipal(
                            username,
                            identityId,
                            email,
                            isVerified
                    ),
                    null,
                    jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );

            SecurityContextHolder.getContext().setAuthentication(token);

        }

        filterChain.doFilter(request, response);

    }

}
