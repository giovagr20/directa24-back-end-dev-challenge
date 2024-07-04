package com.directa24.main.challenge.controllers;

import com.directa24.main.challenge.exceptions.BadRequestException;
import com.directa24.main.challenge.services.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Tag(name = "Director Controller")
public class DirectorController {

    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @Operation(summary = "Gets the directors based on threshold")
    @GetMapping("/directors")
    public ResponseEntity<List<String>> getDirectorsByThreshold(@RequestParam("threshold") int threshold) {
        if (threshold <= NumberUtils.INTEGER_ZERO) {
            throw new BadRequestException("Threshold should be greater than 0");
        }
        return new ResponseEntity<>(directorService.getDirectorsByThreshold(threshold),
                HttpStatus.OK);
    }
}
