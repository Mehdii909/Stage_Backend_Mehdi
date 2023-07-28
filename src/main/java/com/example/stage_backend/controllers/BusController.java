package com.example.stage_backend.controllers;

import com.example.stage_backend.entities.Bus;
import com.example.stage_backend.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/buses", produces = "application/json")
public class BusController {

    private final BusService busService;

    @Autowired
    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busService.getAll();
        return ResponseEntity.ok(buses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable("id") Long id) {
        Optional<Bus> bus = busService.getBusById(id);
        return bus.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activer/{agenceId}")
    public ResponseEntity<List<Bus>> getAllBusEtatActiverByAgenceId(@PathVariable("agenceId") Long agenceId) {
        List<Bus> buses = busService.getAllBusEtatActiverByAgenceId(agenceId);
        return ResponseEntity.ok(buses);
    }

    @GetMapping("/activer")
    public List<Bus> getAllBusesEtatActif() {
        return busService.getAllBusEtatActiver();
    }

    @GetMapping("/archiver")
    public List<Bus> getAllBusesEtatArchiver() {
        return busService.getAllBusEtatArchiver();
    }

    @PostMapping
    public ResponseEntity<Bus> saveBus(@RequestBody Bus bus) {
        busService.saveBus(bus);
        return new ResponseEntity<>(bus, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bus> updateBus(@PathVariable("id") Long id, @RequestBody Bus bus) {
        busService.updateBus(id, bus);
        return new ResponseEntity<>(bus, HttpStatus.OK);
    }

    @PutMapping("/{id}/archiver")
    public void archiverBus(@PathVariable("id") Long id) {
        busService.archiverBus(id);
    }

    @PutMapping("/{id}/activer")
    public void activerBus(@PathVariable("id") Long id) {
        busService.activerBus(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBus(@PathVariable("id") Long id) {
        busService.deleteBus(id);
    }
}
