package com.icritic.movies.dataprovider.database.impl.director;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.boundary.director.SaveDirectorBoundary;
import com.icritic.movies.dataprovider.database.entity.DirectorEntity;
import com.icritic.movies.dataprovider.database.mapper.DirectorEntityMapper;
import com.icritic.movies.dataprovider.database.repository.DirectorEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveDirectorGateway implements SaveDirectorBoundary {

    private final DirectorEntityRepository directorEntityRepository;

    public Director execute(Director director) {
        DirectorEntityMapper mapper = DirectorEntityMapper.INSTANCE;

        DirectorEntity savedDirector = directorEntityRepository.save(mapper.directorToDirectorEntity(director));

        return mapper.directorEntityToDirector(savedDirector);
    }
}
