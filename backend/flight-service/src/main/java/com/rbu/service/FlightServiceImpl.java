package com.rbu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbu.entity.Flight;
import com.rbu.repo.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository repo;

    @Override
    public Flight save(Flight flight) {
        return repo.save(flight);
    }

    @Override
    public Flight findByCode(int code) {
        return repo.findById(code).orElseThrow(
                () -> new InvalidFlightException("Flight with code " + code + " not found"));
    }

    @Override
    public List<Flight> list() {
        return repo.findAll();
    }

    @Override
    public boolean delete(int code) {
        repo.deleteById(code);
        return true;
    }

    @Override
    public List<Flight> findByCarrier(String carrier) {
        return repo.findByCarrier(carrier);
    }

    @Override
    public List<Flight> findByRoute(String source, String destination) {
        return repo.findByRoute(source, destination);
    }

    @Override
    public List<Flight> findByPriceRange(double priceMin, double priceMax) {
        return repo.findByPriceRange(priceMin, priceMax);
    }
}
