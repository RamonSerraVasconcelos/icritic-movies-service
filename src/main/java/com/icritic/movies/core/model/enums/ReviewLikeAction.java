package com.icritic.movies.core.model.enums;

import com.icritic.movies.exception.ResourceViolationException;

public enum ReviewLikeAction {

    LIKE,
    UNLIKE;

    public static ReviewLikeAction parseAction(String action) {
        try {
            return ReviewLikeAction.valueOf(action.toUpperCase());
        } catch (Exception e) {
            throw new ResourceViolationException("Invalid action");
        }
    }
}
