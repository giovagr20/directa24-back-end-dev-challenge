package com.directa24.main.challenge.services;

import com.directa24.main.challenge.clients.Directa24Client;
import com.directa24.main.challenge.dtos.DataDto;
import com.directa24.main.challenge.dtos.MoviesDto;
import com.directa24.main.challenge.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DirectorService {
    private final Directa24Client directa24Client;

    @Autowired
    public DirectorService(Directa24Client directa24Client) {
        this.directa24Client = directa24Client;
    }

    public List<String> getDirectorsByThreshold(int threshold) {
        MoviesDto moviesDtos = getDirectors(2);
        return getDirectorsGivenThreshold(moviesDtos, threshold);
    }

    private MoviesDto getDirectors(int pageNumber) {
        return directa24Client.getMovies(pageNumber)
                .orElseThrow(() -> new ResourceNotFoundException("There is no data from directa24 client service"));
    }

    private List<String> getDirectorsGivenThreshold(MoviesDto moviesDtos,
                                                    int threshold) {

        Map<String, Long> directorMovieCount = moviesDtos.getData()
                .stream()
                .collect(Collectors.groupingBy(
                        DataDto::getDirector,
                        Collectors.counting()
                ));

        if (directorMovieCount.isEmpty()) {
            throw new ResourceNotFoundException("There are no directors to count");
        }

        return directorMovieCount.entrySet().stream()
                .filter(entry -> entry.getValue() >= threshold)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }
}
