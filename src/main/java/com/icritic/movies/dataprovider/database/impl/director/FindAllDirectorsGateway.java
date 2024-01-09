package com.icritic.movies.dataprovider.database.impl.director;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.director.FindAllDirectorsBoundary;
import com.icritic.movies.dataprovider.database.entity.DirectorEntity;
import com.icritic.movies.dataprovider.database.mapper.DirectorEntityMapper;
import com.icritic.movies.dataprovider.database.repository.DirectorEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllDirectorsGateway implements FindAllDirectorsBoundary {

    private final DirectorEntityRepository directorEntityRepository;

    public Page<Director> execute(Pageable pageable) {
        DirectorEntityMapper mapper = DirectorEntityMapper.INSTANCE;

        Page<DirectorEntity> pageableDirectors = directorEntityRepository.findAllByOrderByIdAsc(pageable);

        List<Director> directors = pageableDirectors.getContent()
                .stream()
                .map(mapper::directorEntityToDirector)
                .collect(Collectors.toList());

        return new PageImpl<>(directors, pageable, pageableDirectors.getTotalElements());
    }
}
