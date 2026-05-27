package com.rbu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rbu.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findByCarrier(String carrier);

    @Query("FROM Flight WHERE source = :source AND destination = :destination")
    List<Flight> findByRoute(@Param("source") String source, @Param("destination") String destination);

    @Query("FROM Flight WHERE cost BETWEEN :min AND :max")
    List<Flight> findByPriceRange(@Param("min") double min, @Param("max") double max);
}
