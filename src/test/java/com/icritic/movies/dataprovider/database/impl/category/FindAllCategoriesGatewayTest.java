package com.icritic.movies.dataprovider.database.impl.category;

import com.icritic.movies.core.model.Category;
import com.icritic.movies.dataprovider.database.entity.CategoryEntity;
import com.icritic.movies.dataprovider.database.repository.CategoryEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllCategoriesGatewayTest {

    @InjectMocks
    private FindAllCategoriesGateway findAllCategoriesGateway;

    @Mock
    private CategoryEntityRepository categoryEntityRepository;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<CategoryEntity> pageableCategory;

    @Test
    void givenExecution_thenFind_andReturnAllCategories() {
        when(categoryEntityRepository.findAllByOrderByIdAsc(pageable)).thenReturn(pageableCategory);

        Page<Category> categories = findAllCategoriesGateway.execute(pageable);

        assertThat(categories).isNotNull();
    }
}