package com.directa24.main.challenge.clients;

import com.directa24.main.challenge.dtos.MoviesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "directa24Client", value = "directa24Client", url = "${client.directa24.url}")
public interface Directa24Client {
    @GetMapping
    Optional<MoviesDto> getMovies(@RequestParam("page") int pageNumber);
}
