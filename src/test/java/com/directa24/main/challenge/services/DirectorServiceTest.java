package com.directa24.main.challenge.services;

import com.directa24.main.challenge.clients.Directa24Client;
import com.directa24.main.challenge.dtos.MoviesDto;
import com.directa24.main.challenge.exceptions.ResourceNotFoundException;
import com.directa24.main.challenge.mock.MockData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DirectorServiceTest {

    @Mock private Directa24Client directa24Client;
    @InjectMocks private DirectorService directorService;

    @Test
    void givenDirectorService_whenCallExternalService_thenReturnlistOfDirectors() {
        final int threshold = 2;
        final int pageNumber = 2;
        final List<String> expectedResult = List.of("MartinScorsese", "WoodyAllen");
        Optional<MoviesDto> mockMoviesDto = MockData.getMockMoviesDto();
         Mockito.when(directa24Client.getMovies(pageNumber)).thenReturn(
                mockMoviesDto);
        List<String> actualResult = directorService.getDirectorsByThreshold(threshold);
        assertArrayEquals(actualResult.toArray(), expectedResult.toArray());
    }

    @Test
    void givenDirectorService_whenCallExternalService_thenReturnEmpty() {
        final int threshold = 2;
        final int pageNumber = 2;
        final String expectedResult = "There is no data from directa24 client service";
        Optional<MoviesDto> mockMoviesDto = Optional.empty();
        Mockito.when(directa24Client.getMovies(pageNumber)).thenReturn(
                mockMoviesDto);
        ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class,
                () -> directorService.getDirectorsByThreshold(threshold));
        assertEquals(resourceNotFoundException.getMessage(), expectedResult);
    }
}