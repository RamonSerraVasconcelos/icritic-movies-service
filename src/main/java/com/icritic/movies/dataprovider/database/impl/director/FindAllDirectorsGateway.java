package com.icritic.movies.dataprovider.database.impl.director;

import com.icritic.movies.core.model.Director;
import com.icritic.movies.core.usecase.boundary.director.FindAllDirectorsBoundary;
import com.icritic.movies.dataprovider.database.mapper.DirectorEntityMapper;
import com.icritic.movies.dataprovider.database.repository.DirectorEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllDirectorsGateway implements FindAllDirectorsBoundary {

    private final DirectorEntityRepository directorEntityRepository;

    public List<Director> execute() {
        DirectorEntityMapper mapper = DirectorEntityMapper.INSTANCE;

        return directorEntityRepository.findAllByOrderByIdAsc().stream().map(mapper::directorEntityToDirector).collect(Collectors.toList());
    }
}
