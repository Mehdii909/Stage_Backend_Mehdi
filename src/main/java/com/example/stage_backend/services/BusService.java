package com.example.stage_backend.services;

import com.example.stage_backend.dao.BusRepository;
import com.example.stage_backend.entities.Bus;
import com.example.stage_backend.interfaces.IBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService implements IBus {

    private final BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public List<Bus> getAll() {
        return busRepository.findAll();
    }

    @Override
    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }

    @Override
    public void saveBus(Bus bus) {
        busRepository.save(bus);
    }

    @Override
    public void updateBus(Long id, Bus bus) {
        Bus existingBus = busRepository.findById(id).orElse(null);

        if (existingBus != null) {
            existingBus.setImmatriculation(bus.getImmatriculation());
            existingBus.setMarqueModele(bus.getMarqueModele());
            existingBus.setNombrePlaces(bus.getNombrePlaces());
            existingBus.setEtat(bus.getEtat());
            existingBus.setAgence(bus.getAgence());

            busRepository.save(existingBus);
        } else {
            throw new IllegalArgumentException("Bus with ID " + id + " does not exist.");
        }
    }

    @Override
    public void archiverBus(Long busId) {
        Bus bus = busRepository.findById(busId).orElse(null);

        if (bus != null) {
            bus.setEtat("archiver");
            busRepository.save(bus);
        } else {
            throw new IllegalArgumentException("Bus with ID " + busId + " does not exist.");
        }
    }

    @Override
    public void activerBus(Long busId) {
        Bus bus = busRepository.findById(busId).orElse(null);

        if (bus != null) {
            bus.setEtat("activer");
            busRepository.save(bus);
        } else {
            throw new IllegalArgumentException("Bus with ID " + busId + " does not exist.");
        }
    }

    @Override
    public void deleteBus(Long id) {
        Bus bus = busRepository.findById(id).orElse(null);

        if (bus != null) {
            busRepository.delete(bus);
        } else {
            throw new IllegalArgumentException("Bus with ID " + id + " does not exist.");
        }
    }

    @Override
    public List<Bus> getAllBusEtatActiver() {
        return busRepository.findByEtat("activer", Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Bus> getAllBusEtatArchiver() {
        return busRepository.findByEtat("archiver", Sort.by(Sort.Direction.ASC, "id"));
    }
}
