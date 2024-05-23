package com.icritic.movies.entrypoint.resource;

import com.icritic.movies.core.model.Movie;
import com.icritic.movies.core.model.MovieFilter;
import com.icritic.movies.core.model.MovieRequestParams;
import com.icritic.movies.core.model.Review;
import com.icritic.movies.core.model.enums.ReviewLikeAction;
import com.icritic.movies.core.model.enums.Role;
import com.icritic.movies.core.usecase.movie.CreateMovieUseCase;
import com.icritic.movies.core.usecase.movie.DeleteMovieUseCase;
import com.icritic.movies.core.usecase.movie.DeleteReviewUseCase;
import com.icritic.movies.core.usecase.movie.FindAllMoviesUseCase;
import com.icritic.movies.core.usecase.movie.FindMovieByIdUseCase;
import com.icritic.movies.core.usecase.movie.FindReviewsUseCase;
import com.icritic.movies.core.usecase.movie.RateMovieUseCase;
import com.icritic.movies.core.usecase.movie.ReviewMovieUseCase;
import com.icritic.movies.core.usecase.movie.UpdateMovieUseCase;
import com.icritic.movies.core.usecase.movie.UpvoteReviewUseCase;
import com.icritic.movies.core.usecase.user.ValidateUserRoleUseCase;
import com.icritic.movies.entrypoint.dto.Metadata;
import com.icritic.movies.entrypoint.dto.movie.MovieRequestDto;
import com.icritic.movies.entrypoint.dto.movie.MovieResponseDto;
import com.icritic.movies.entrypoint.dto.movie.PageableMovieResponse;
import com.icritic.movies.entrypoint.dto.movie.PageableReviewResponse;
import com.icritic.movies.entrypoint.dto.movie.RateMovieRequestDto;
import com.icritic.movies.entrypoint.dto.movie.ReviewCreateResponse;
import com.icritic.movies.entrypoint.dto.movie.ReviewRequestDto;
import com.icritic.movies.entrypoint.dto.movie.ReviewResponseDto;
import com.icritic.movies.entrypoint.dto.movie.ReviewUpvoteRequestDto;
import com.icritic.movies.entrypoint.mapper.MovieDtoMapper;
import com.icritic.movies.entrypoint.mapper.ReviewDtoMapper;
import com.icritic.movies.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    private final RateMovieUseCase rateMovieUseCase;

    private final ReviewMovieUseCase reviewMovieUseCase;

    private final FindReviewsUseCase findReviewsUseCase;

    private final UpvoteReviewUseCase upvoteReviewUseCase;

    private final DeleteReviewUseCase deleteReviewUseCase;

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
    public ResponseEntity<PageableMovieResponse> findAllMovies(Pageable pageable,
                                                               @RequestParam(name = "name", required = false) String name,
                                                               @RequestParam(name = "categories", required = false) List<Integer> categories,
                                                               @RequestParam(name ="directors", required = false) List<Integer> directors,
                                                               @RequestParam(name ="actors", required = false) List<Integer> actors) {
        MovieFilter movieFilter = MovieFilter.builder()
                .name(name)
                .categories(categories)
                .directors(directors)
                .actors(actors)
                .pageable(pageable)
                .build();

        Page<Movie> moviesPageables = findAllMoviesUseCase.execute(movieFilter);

        List<MovieResponseDto> moviesResponseDto = moviesPageables.getContent()
                .stream()
                .map(MovieDtoMapper.INSTANCE::movieToMovieResponseDto)
                .collect(Collectors.toList());

        PageableMovieResponse response = PageableMovieResponse.builder()
                .data(moviesResponseDto)
                .metadata(Metadata.builder()
                        .page(pageable.getPageNumber())
                        .nextPage(pageable.getPageNumber() + 1)
                        .size(pageable.getPageSize())
                        .total(moviesPageables.getTotalElements())
                        .build())
                .build();

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

    @PatchMapping("/{id}/rate")
    public ResponseEntity<Void> rateMovie(HttpServletRequest request, @PathVariable Long id, @RequestBody RateMovieRequestDto rateMovieRequestDto) {
        Set<ConstraintViolation<RateMovieRequestDto>> violations = validator.validate(rateMovieRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        String userId = request.getAttribute("userId").toString();

        rateMovieUseCase.execute(id, Long.parseLong(userId), rateMovieRequestDto.getRate());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewCreateResponse> reviewMovie(HttpServletRequest request, @RequestBody ReviewRequestDto reviewRequestDto) {
        Set<ConstraintViolation<ReviewRequestDto>> violations = validator.validate(reviewRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        String userId = request.getAttribute("userId").toString();

        Review review = reviewMovieUseCase.execute(reviewRequestDto.getMovieId(), Long.parseLong(userId), reviewRequestDto.getReview());

        ReviewCreateResponse reviewResponseDto = ReviewDtoMapper.INSTANCE.reviewToReviewCreateResponse(review);

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponseDto);
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(HttpServletRequest request, @PathVariable Long reviewId) {
        Long userId = Long.parseLong(request.getAttribute("userId").toString());
        Role userRole = Role.parseRole(request.getAttribute("role").toString());

        deleteReviewUseCase.execute(reviewId, userId, userRole);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<PageableReviewResponse> findReviewsByMovieId(HttpServletRequest request, @PathVariable Long movieId, Pageable pageable) {
        Page<Review> reviews = findReviewsUseCase.execute(pageable, movieId, request.getHeader("Authorization"));

        List<ReviewResponseDto> reviewResponseDtos = reviews.getContent()
                .stream()
                .map(ReviewDtoMapper.INSTANCE::reviewToReviewResponseDto)
                .collect(Collectors.toList());

        PageableReviewResponse response = PageableReviewResponse.builder()
                .data(reviewResponseDtos)
                .metadata(Metadata.builder()
                        .page(pageable.getPageNumber())
                        .nextPage(pageable.getPageNumber() + 1)
                        .size(pageable.getPageSize())
                        .total(reviews.getTotalElements())
                        .build())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/review/{reviewId}/upvote")
    public ResponseEntity<Void> likeReview(HttpServletRequest request, @PathVariable Long reviewId, @RequestBody ReviewUpvoteRequestDto reviewUpvoteRequestDto) {
        Set<ConstraintViolation<ReviewUpvoteRequestDto>> violations = validator.validate(reviewUpvoteRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        Long userId = Long.parseLong(request.getAttribute("userId").toString());
        ReviewLikeAction action = ReviewLikeAction.parseAction(reviewUpvoteRequestDto.getAction());

        upvoteReviewUseCase.execute(reviewId, userId, action);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private void validateUserRole(HttpServletRequest request, List<Role> requiredRoles) {
        String role = request.getAttribute("role").toString();
        validateUserRoleUseCase.execute(requiredRoles, role);
    }
}
