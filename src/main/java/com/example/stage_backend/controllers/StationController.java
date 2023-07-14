package com.example.stage_backend.controllers;

import com.example.stage_backend.entities.Station;
import com.example.stage_backend.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/stations", produces = "application/json")
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public ResponseEntity<List<Station>> getAllStations() {
        List<Station> stations = stationService.getAll();
        return ResponseEntity.ok(stations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable("id") Long id) {
        Optional<Station> station = stationService.getStationById(id);
        return station.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Station> saveStation(@RequestBody Station station) {
        stationService.saveStation(station);
        return new ResponseEntity<>(station, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Station> updateStation(@PathVariable("id") Long id, @RequestBody Station station) {
        stationService.updateStation(id, station);
        return new ResponseEntity<>(station, HttpStatus.OK);
    }

    @PutMapping("/{id}/archiver")
    public void archiverStation(@PathVariable("id") Long id) {
        stationService.archiverStation(id);
    }

    @PutMapping("/{id}/activer")
    public void activerStation(@PathVariable("id") Long id) {
        stationService.activerStation(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStation(@PathVariable("id") Long id) {
        stationService.deleteStation(id);
    }
}
