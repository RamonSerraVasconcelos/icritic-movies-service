package com.icritic.movies.entrypoint.resource;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.core.model.enums.Role;
import com.icritic.movies.core.usecase.category.CreateCategoryUseCase;
import com.icritic.movies.core.usecase.category.FindAllCategoriesUseCase;
import com.icritic.movies.core.usecase.category.FindCategoryByIdUseCase;
import com.icritic.movies.core.usecase.category.UpdateCategoryUseCase;
import com.icritic.movies.core.usecase.user.ValidateUserRoleUseCase;
import com.icritic.movies.entrypoint.dto.category.CategoryRequestDto;
import com.icritic.movies.entrypoint.dto.category.CategoryResponseDto;
import com.icritic.movies.entrypoint.dto.category.PageableCategoryResponse;
import com.icritic.movies.entrypoint.mapper.CategoryDtoMapper;
import com.icritic.movies.exception.ResourceViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping(path = "/movies/categories")
@RequiredArgsConstructor
public class CategoryResource {

    private final Validator validator;

    private final ValidateUserRoleUseCase validateUserRoleUseCase;

    private final CreateCategoryUseCase createCategoryUseCase;

    private final UpdateCategoryUseCase updateCategoryUseCase;

    private final FindAllCategoriesUseCase findAllCategoriesUseCase;

    private final FindCategoryByIdUseCase findCategoryByIdUseCase;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(HttpServletRequest request, @RequestBody CategoryRequestDto categoryRequestDto) {
        validateUserRole(request, List.of(Role.MODERATOR));

        Set<ConstraintViolation<CategoryRequestDto>> violations = validator.validate(categoryRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        Category savedCategory = createCategoryUseCase.execute(categoryRequestDto.getName(), categoryRequestDto.getDescription());
        CategoryResponseDto response = CategoryDtoMapper.INSTANCE.categoryToCategoryResponsetDto(savedCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(HttpServletRequest request, @PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto) {
        validateUserRole(request, List.of(Role.MODERATOR));

        Set<ConstraintViolation<CategoryRequestDto>> violations = validator.validate(categoryRequestDto);
        if (!violations.isEmpty()) {
            throw new ResourceViolationException(violations);
        }

        Category savedCategory = updateCategoryUseCase.execute(id, categoryRequestDto.getName(), categoryRequestDto.getDescription());
        CategoryResponseDto response = CategoryDtoMapper.INSTANCE.categoryToCategoryResponsetDto(savedCategory);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<PageableCategoryResponse> findAllCategories(Pageable pageable) {
        Page<Category> categories = findAllCategoriesUseCase.execute(pageable);

        List<CategoryResponseDto> categoriesResponseDto = categories.stream().map(CategoryDtoMapper.INSTANCE::categoryToCategoryResponsetDto).collect(Collectors.toList());

        PageableCategoryResponse response = PageableCategoryResponse.builder()
                .data(categoriesResponseDto)
                .page(pageable.getPageNumber())
                .nextPage(pageable.getPageNumber() + 1)
                .size(pageable.getPageSize())
                .total(categories.getTotalElements())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> findCategoryById(@PathVariable Long id) {
        Category category = findCategoryByIdUseCase.execute(id);

        CategoryResponseDto response = CategoryDtoMapper.INSTANCE.categoryToCategoryResponsetDto(category);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private void validateUserRole(HttpServletRequest request, List<Role> requiredRoles) {
        String role = request.getAttribute("role").toString();
        validateUserRoleUseCase.execute(requiredRoles, role);
    }
}
