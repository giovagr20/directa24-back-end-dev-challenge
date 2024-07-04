package com.directa24.main.challenge.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heartbeat")
@Tag(name = "Hearbeat")
public class HeartbeatController {
    @Operation(summary = "Gets the current status of application")
    @GetMapping
    public ResponseEntity<String> heartbeat() {
        return new ResponseEntity<>("Service is working", HttpStatus.OK);
    }
}
