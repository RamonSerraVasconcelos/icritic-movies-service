package com.icritic.movies.entrypoint.resource;

import com.icritic.movies.core.model.Actor;
import com.icritic.movies.core.model.enums.Role;
import com.icritic.movies.core.usecase.actor.CreateActorUseCase;
import com.icritic.movies.core.usecase.actor.UpdateActorUseCase;
import com.icritic.movies.core.usecase.user.ValidateUserRoleUseCase;
import com.icritic.movies.entrypoint.dto.actor.ActorRequestDto;
import com.icritic.movies.entrypoint.dto.actor.ActorResponseDto;
import com.icritic.movies.entrypoint.mapper.ActorDtoMapper;
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
@RequestMapping(path = "/movies/actors")
@RequiredArgsConstructor
public class ActorResource {

    private final Validator validator;

    private final ValidateUserRoleUseCase validateUserRoleUseCase;

    private final CreateActorUseCase createActorUseCase;

    private final UpdateActorUseCase updateDirectorUseCase;

    @PostMapping
    public ResponseEntity<ActorResponseDto> createActor(HttpServletRequest request, @RequestBody ActorRequestDto actorRequestDto) {
        validateUserRole(request, List.of(Role.MODERATOR));

        Set<ConstraintViolation<ActorRequestDto>> violations = validator.validate(actorRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        Actor actor = createActorUseCase.execute(actorRequestDto.getName(), actorRequestDto.getDescription(), actorRequestDto.getCountryId());

        ActorResponseDto response = ActorDtoMapper.INSTANCE.actorToActorResponseDto(actor);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorResponseDto> updateActor(HttpServletRequest request, @PathVariable Long id, @RequestBody ActorRequestDto actorRequestDto) {
        validateUserRole(request, List.of(Role.MODERATOR));

        Set<ConstraintViolation<ActorRequestDto>> violations = validator.validate(actorRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        Actor actor = updateDirectorUseCase.execute(id, actorRequestDto.getName(), actorRequestDto.getDescription(), actorRequestDto.getCountryId());

        ActorResponseDto response = ActorDtoMapper.INSTANCE.actorToActorResponseDto(actor);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private void validateUserRole(HttpServletRequest request, List<Role> requiredRoles) {
        String role = request.getAttribute("role").toString();
        validateUserRoleUseCase.execute(requiredRoles, role);
    }
}
