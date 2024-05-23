package com.icritic.movies.dataprovider.database.impl.movie;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.movie.FindMovieDirectorsBoundary;
import com.icritic.movies.dataprovider.database.entity.DirectorEntity;
import com.icritic.movies.dataprovider.database.mapper.DirectorEntityMapper;
import com.icritic.movies.dataprovider.database.repository.DirectorEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindMovieDirectorsGateway implements FindMovieDirectorsBoundary {

    private final DirectorEntityRepository directorEntityRepository;

    @Override
    public List<Director> execute(Long movieId) {
        DirectorEntityMapper mapper = DirectorEntityMapper.INSTANCE;

        List<DirectorEntity> directorEntities = directorEntityRepository.findMovieCategories(movieId);
        return directorEntities
                .stream()
                .map(mapper::directorEntityToDirector)
                .collect(Collectors.toList());
    }
}
