package com.icritic.movies.core.model.enums;

import com.icritic.movies.exception.ResourceViolationException;

public enum Role {
    DEFAULT,
    MODERATOR,
    ADMIN;

    public static Role parseRole(String role) {
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (Exception e) {
            throw new ResourceViolationException("Invalid role");
        }
    }
}
