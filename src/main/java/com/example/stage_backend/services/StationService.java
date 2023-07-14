package com.example.stage_backend.services;

import com.example.stage_backend.dao.StationRepository;
import com.example.stage_backend.entities.Station;
import com.example.stage_backend.interfaces.IStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService implements IStation {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public List<Station> getAll() {
        return stationRepository.findAll();
    }

    @Override
    public Optional<Station> getStationById(Long id) {
        return stationRepository.findById(id);
    }

    @Override
    public List<Station> getAllStationEtatActiver() {
        return stationRepository.findByEtat("activer");
    }

    @Override
    public List<Station> getAllStationEtatArchiver() {
        return stationRepository.findByEtat("archiver");
    }

    @Override
    public void saveStation(Station station) {
        stationRepository.save(station);
    }

    @Override
    public void updateStation(Long id, Station station) {
        Station existingStation = stationRepository.findById(id).orElse(null);

        if (existingStation != null) {
            existingStation.setNom(station.getNom());
            existingStation.setRegion(station.getRegion());
            existingStation.setCoordonneesGpsLatitude(station.getCoordonneesGpsLatitude());
            existingStation.setCoordonneesGpsLongitude(station.getCoordonneesGpsLongitude());
            stationRepository.save(existingStation);
        } else {
            throw new IllegalArgumentException("La station avec l'ID " + id + " n'existe pas.");
        }
    }

    @Override
    public void archiverStation(Long stationId) {
        Station station = stationRepository.findById(stationId).orElse(null);

        if (station != null) {
            station.setEtat("archiver");
            stationRepository.save(station);
        } else {
            throw new IllegalArgumentException("La station avec l'ID " + stationId + " n'existe pas.");
        }
    }

    @Override
    public void activerStation(Long stationId) {
        Station station = stationRepository.findById(stationId).orElse(null);

        if (station != null) {
            station.setEtat("activer");
            stationRepository.save(station);
        } else {
            throw new IllegalArgumentException("La station avec l'ID " + stationId + " n'existe pas.");
        }
    }

    @Override
    public void deleteStation(Long stationId) {
        Station station = stationRepository.findById(stationId).orElse(null);

        if (station != null) {
            stationRepository.delete(station);
        } else {
            throw new IllegalArgumentException("La station avec l'ID " + stationId + " n'existe pas.");
        }
    }
}
