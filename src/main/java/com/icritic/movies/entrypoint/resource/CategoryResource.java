package com.icritic.movies.entrypoint.resource;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.usecase.category.CreateCategoryUseCase;
import com.icritic.movies.entrypoint.dto.category.CategoryRequestDto;
import com.icritic.movies.entrypoint.dto.category.CategoryResponseDto;
import com.icritic.movies.entrypoint.mapper.CategoryDtoMapper;
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
@RequestMapping(path = "/movies/categories")
@RequiredArgsConstructor
public class CategoryResource {

    private final Validator validator;

    private final CreateCategoryUseCase createCategoryUseCase;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(HttpServletRequest request, @RequestBody CategoryRequestDto categoryRequestDto) {
        Set<ConstraintViolation<CategoryRequestDto>> violations = validator.validate(categoryRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        String role = request.getAttribute("role").toString();

        Category savedCategory = createCategoryUseCase.execute(categoryRequestDto.getName(), categoryRequestDto.getDescription(), role);
        CategoryResponseDto response = CategoryDtoMapper.INSTANCE.categoryToCategoryResponsetDto(savedCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
