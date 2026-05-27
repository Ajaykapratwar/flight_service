package com.rbu.service;

import java.util.List;

import com.rbu.entity.Flight;

public interface FlightService {
	Flight save(Flight flight);

    Flight findByCode(int code);

    List<Flight> list();

    boolean delete(int code);

    List<Flight> findByCarrier(String carrier);

    List<Flight> findByRoute(String source, String destination);

    List<Flight> findByPriceRange(double priceMin, double priceMax);
}
