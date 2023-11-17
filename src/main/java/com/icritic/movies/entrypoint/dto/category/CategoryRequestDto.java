package com.icritic.movies.entrypoint.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryRequestDto {

    @NotBlank(message = "Category name is required")
    @Length(min = 2, message = "Category length must be at least 2 characters long")
    private String name;

    @NotBlank(message = "Category description is required")
    @Length(min = 4, message = "Category length must be at least 4 characters long")
    private String description;
}
