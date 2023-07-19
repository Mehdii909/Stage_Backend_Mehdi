package com.example.stage_backend.interfaces;

import com.example.stage_backend.entities.Bus;

import java.util.List;

public interface IBus {

    List<Bus> getAll();
    Object getBusById(Long id);
    List<Bus> getAllBusEtatActiver();
    List<Bus> getAllBusEtatArchiver();
    void saveBus(Bus bus);
    void updateBus(Long id, Bus bus);
    void archiverBus(Long busId);
    void activerBus(Long busId);
    void deleteBus(Long busId);
}
