package com.icritic.movies.entrypoint.resource;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.model.enums.Role;
import com.icritic.movies.core.usecase.director.CreateDirectorUseCase;
import com.icritic.movies.core.usecase.director.UpdateDirectorUseCase;
import com.icritic.movies.core.usecase.user.ValidateUserRoleUseCase;
import com.icritic.movies.entrypoint.dto.director.DirectorRequestDto;
import com.icritic.movies.entrypoint.dto.director.DirectorResponseDto;
import com.icritic.movies.entrypoint.mapper.DirectorDtoMapper;
import com.icritic.movies.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/movies/directors")
@RequiredArgsConstructor
public class DirectorResource {

    private final Validator validator;

    private final ValidateUserRoleUseCase validateUserRoleUseCase;

    private final CreateDirectorUseCase createDirectorUseCase;

    private final UpdateDirectorUseCase updateDirectorUseCase;

    @PostMapping
    public ResponseEntity<DirectorResponseDto> createDirector(HttpServletRequest request, @RequestBody DirectorRequestDto directorRequestDto) {
        validateUserRole(request, List.of(Role.MODERATOR));

        Set<ConstraintViolation<DirectorRequestDto>> violations = validator.validate(directorRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        Director createdDirector = createDirectorUseCase.execute(directorRequestDto.getName(), directorRequestDto.getDescription(), directorRequestDto.getCountryId());
        DirectorResponseDto response = DirectorDtoMapper.INSTANCE.directorToDirectorResponseDto(createdDirector);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorResponseDto> updateDirector(HttpServletRequest request, @PathVariable Long id, @RequestBody DirectorRequestDto directorRequestDto) {
        validateUserRole(request, List.of(Role.MODERATOR));

        Set<ConstraintViolation<DirectorRequestDto>> violations = validator.validate(directorRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        Director updatedDirector = updateDirectorUseCase.execute(id, directorRequestDto.getName(), directorRequestDto.getDescription(), directorRequestDto.getCountryId());

        DirectorResponseDto response = DirectorDtoMapper.INSTANCE.directorToDirectorResponseDto(updatedDirector);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private void validateUserRole(HttpServletRequest request, List<Role> requiredRoles) {
        String role = request.getAttribute("role").toString();
        validateUserRoleUseCase.execute(requiredRoles, role);
    }
}
