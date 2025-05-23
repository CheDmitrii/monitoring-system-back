package ru.system.monitoring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.system.library.dto.common.SensorDTO;
import ru.system.monitoring.service.SensorService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/sensor")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;

    @PostMapping("/create")
    public Mono<ResponseEntity<Map<String, UUID>>> createSensor(@RequestBody @Valid SensorDTO sensor) {
        return Mono.just(ResponseEntity.ok(Map.of("id", sensorService.createSensor(sensor))));
    }
}
