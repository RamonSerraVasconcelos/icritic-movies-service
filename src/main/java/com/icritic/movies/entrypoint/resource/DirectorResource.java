package com.icritic.movies.entrypoint.resource;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.director.CreateDirectorUseCase;
import com.icritic.movies.entrypoint.dto.director.DirectorRequestDto;
import com.icritic.movies.entrypoint.dto.director.DirectorResponseDto;
import com.icritic.movies.entrypoint.mapper.DirectorDtoMapper;
import com.icritic.movies.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RestController
@RequestMapping(path = "/movies/directors")
@RequiredArgsConstructor
public class DirectorResource {

    private final Validator validator;

    private final CreateDirectorUseCase createDirectorUseCase;

    @PostMapping
    public ResponseEntity<DirectorResponseDto> createDirector(HttpServletRequest request, @RequestBody DirectorRequestDto directorRequestDto) {
        Set<ConstraintViolation<DirectorRequestDto>> violations = validator.validate(directorRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        Director createdDirector = createDirectorUseCase.execute(directorRequestDto.getName(), directorRequestDto.getDescription(), directorRequestDto.getCountryId());
        DirectorResponseDto response = DirectorDtoMapper.INSTANCE.directorToDirectorResponseDto(createdDirector);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
