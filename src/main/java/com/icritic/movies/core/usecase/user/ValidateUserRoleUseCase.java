package com.icritic.movies.core.usecase.user;

import com.icritic.movies.core.model.enums.Role;
import com.icritic.movies.exception.ForbiddenAccessException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Component
@NoArgsConstructor
public class ValidateUserRoleUseCase {

    public void execute(List<Role> allowedRoles, String currentRole) {
        if(!nonNull(currentRole)) {
            throw new ForbiddenAccessException();
        }

        if(Role.valueOf(currentRole) == Role.ADMIN) {
            return;
        }

        if(!allowedRoles.contains(Role.valueOf(currentRole))) {
            throw new ForbiddenAccessException();
        }
    }
}
