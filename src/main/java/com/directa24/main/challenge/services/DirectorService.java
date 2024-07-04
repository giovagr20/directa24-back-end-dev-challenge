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

//    private int getRandomPage() {
//        Random random = new Random();
//        return random.nextInt(3) + 1;
//    }

    private List<String> getDirectorsGivenThreshold(MoviesDto moviesDtos,
                                                    int threshold) {
        //First it is neccesary count movies per director
        Map<String, Long> directorMovieCount = moviesDtos.getData()
                .stream()
                .collect(Collectors.groupingBy(
                        DataDto::getDirector,
                        Collectors.counting()
                ));

        if (directorMovieCount.isEmpty()) {
            throw new ResourceNotFoundException("There are no directors to count");
        }
        //Then filter all directors with movies greater tha threshold
        return directorMovieCount.entrySet().stream()
                .filter(entry -> entry.getValue() >= threshold)
                .map(Map.Entry::getKey)
                .toList();
    }
}
