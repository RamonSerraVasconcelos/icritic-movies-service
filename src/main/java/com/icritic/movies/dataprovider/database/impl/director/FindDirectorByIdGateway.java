package com.icritic.movies.dataprovider.database.impl.director;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.boundary.director.FindDirectorByIdBoundary;
import com.icritic.movies.dataprovider.database.mapper.DirectorEntityMapper;
import com.icritic.movies.dataprovider.database.repository.DirectorEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindDirectorByIdGateway implements FindDirectorByIdBoundary {

    private final DirectorEntityRepository repository;

    public Optional<Director> execute(Long id) {
        DirectorEntityMapper mapper = DirectorEntityMapper.INSTANCE;

        return repository.findById(id).map(mapper::directorEntityToDirector);
    }
}
