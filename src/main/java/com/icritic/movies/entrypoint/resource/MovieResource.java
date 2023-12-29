package com.icritic.movies.entrypoint.resource;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.MovieRequestParams;
import com.icritic.movies.core.model.enums.Role;
import com.icritic.movies.core.usecase.movie.CreateMovieUseCase;
import com.icritic.movies.core.usecase.movie.DeleteMovieUseCase;
import com.icritic.movies.core.usecase.movie.FindAllMoviesUseCase;
import com.icritic.movies.core.usecase.movie.FindMovieByIdUseCase;
import com.icritic.movies.core.usecase.movie.UpdateMovieUseCase;
import com.icritic.movies.core.usecase.user.ValidateUserRoleUseCase;
import com.icritic.movies.entrypoint.dto.movie.MovieRequestDto;
import com.icritic.movies.entrypoint.dto.movie.MovieResponseDto;
import com.icritic.movies.entrypoint.mapper.MovieDtoMapper;
import com.icritic.movies.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import java.util.stream.Collectors;

@SuppressWarnings("DuplicatedCode")
@RestController
@RequestMapping(path = "/movies")
@RequiredArgsConstructor
public class MovieResource {

    private final Validator validator;

    private final ValidateUserRoleUseCase validateUserRoleUseCase;

    private final CreateMovieUseCase createMovieUseCase;

    private final UpdateMovieUseCase updateMovieUseCase;

    private final FindAllMoviesUseCase findAllMoviesUseCase;

    private final FindMovieByIdUseCase findMovieByIdUseCase;

    private final DeleteMovieUseCase deleteMovieUseCase;

    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(HttpServletRequest request, @RequestBody MovieRequestDto movieRequestDto) {
        validateUserRole(request, List.of(Role.MODERATOR));

        Set<ConstraintViolation<MovieRequestDto>> violations = validator.validate(movieRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        MovieDtoMapper mapper = MovieDtoMapper.INSTANCE;

        MovieRequestParams movieRequestParams = mapper.movieRequestDtoToMovieRequestParams(movieRequestDto);

        Movie createdMovie = createMovieUseCase.execute(movieRequestParams);

        MovieResponseDto response = mapper.movieToMovieResponseDto(createdMovie);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(HttpServletRequest request, @PathVariable Long id, @RequestBody MovieRequestDto movieRequestDto) {
        validateUserRole(request, List.of(Role.MODERATOR));

        Set<ConstraintViolation<MovieRequestDto>> violations = validator.validate(movieRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        MovieDtoMapper mapper = MovieDtoMapper.INSTANCE;

        MovieRequestParams movieRequestParams = mapper.movieRequestDtoToMovieRequestParams(movieRequestDto);

        Movie updatedMovie = updateMovieUseCase.execute(id, movieRequestParams);

        MovieResponseDto response = mapper.movieToMovieResponseDto(updatedMovie);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> findAllMovies() {
        List<Movie> movies = findAllMoviesUseCase.execute();

        List<MovieResponseDto> response = movies.stream().map(MovieDtoMapper.INSTANCE::movieToMovieResponseDto).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> findMovieById(@PathVariable Long id) {
        Movie movie = findMovieByIdUseCase.execute(id);

        MovieResponseDto response = MovieDtoMapper.INSTANCE.movieToMovieResponseDto(movie);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(HttpServletRequest request, @PathVariable Long id) {
        validateUserRole(request, List.of(Role.MODERATOR));

        deleteMovieUseCase.execute(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private void validateUserRole(HttpServletRequest request, List<Role> requiredRoles) {
        String role = request.getAttribute("role").toString();
        validateUserRoleUseCase.execute(requiredRoles, role);
    }
}
