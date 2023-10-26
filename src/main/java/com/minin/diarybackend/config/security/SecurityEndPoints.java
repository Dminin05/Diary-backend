package com.minin.diarybackend.config.security;

public class SecurityEndPoints {

    // permit_all
    static final String[] AUTH_WHITELIST_AUTH = {
            "api/v1/auth/authenticate",
            "api/v1/auth/refresh",
            "api/v1/auth/password-recovery",
            "api/v1/mailing",
            "api/v1/verification/verify-recovering"
    };

    // has_role("ADMIN")
    static final String[] AUTH_REQUIRE_ADMIN = {
            "api/v1/auth/register",
            "api/v1/groups/new",
            "api/v1/subjects/new"
    };

    // has_role("METHODIST")
    static final String[] AUTH_REQUIRE_METHODIST = {

    };

    // has_role("TEACHER")
    static final String[] AUTH_REQUIRE_TEACHER = {

    };

    // has_role("STUDENT")
    static final String[] AUTH_REQUIRE_STUDENT = {

    };

}
