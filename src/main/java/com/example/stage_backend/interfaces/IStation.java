package com.example.stage_backend.interfaces;

import com.example.stage_backend.entities.Eleve;
import com.example.stage_backend.entities.Station;

import java.util.List;
import java.util.Optional;

public interface IStation {
    List<Station> getAll();
    Optional<Station> getStationById(Long id);
    List<Station> getAllStationEtatActiver();
    List<Station> getAllStationEtatArchiver();
    void saveStation(Station station);
    void updateStation(Long id, Station station);
    void archiverStation(Long stationId);
    void activerStation(Long stationId);
    void deleteStation(Long stationId);
}

