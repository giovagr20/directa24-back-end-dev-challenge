package com.directa24.main.challenge.configurations.utils;

import com.directa24.main.challenge.dtos.MoviesDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.DecodeException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class MoviesDtoDecoder implements Decoder {

    private final ObjectMapper objectMapper;

    @Autowired
    public MoviesDtoDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Optional<MoviesDto> decode(Response response, java.lang.reflect.Type type) throws IOException, DecodeException {
        if (response.status() == 200) {
            InputStream inputStream = response.body().asInputStream();
            String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            MoviesDto moviesDto = objectMapper.readValue(json, MoviesDto.class);
            return Optional.of(moviesDto);
        }
        throw new DecodeException(response.status(), "Cannot decode response", response.request());
    }
}