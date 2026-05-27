package com.rbu.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.rbu.entity.Flight;
import com.rbu.repo.FlightRepository;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestFlightRepository {

    @Autowired
    private FlightRepository repo;

    @Test
    @DisplayName("Test 1: Save Flight Test")
    @Order(1)
    public void testSave() {

        Flight flight = new Flight(101, "Indigo", "Nagpur", "Mumbai", 3500);

        Flight savedEntity = repo.save(flight);

        assertNotNull(savedEntity);
    }

    @Test
    @Order(2)
    public void testFindById() {

        Flight flight = new Flight(102, "AirIndia", "Pune", "Delhi", 4500);

        repo.save(flight);

        Flight found = repo.findById(102).get();

        assertNotNull(found);
    }

    @Test
    @Order(3)
    public void testFindAll() {

        Flight flight = new Flight(103, "SpiceJet", "Nashik", "Mumbai", 2800);

        repo.save(flight);

        List<Flight> flights = repo.findAll();

        assertTrue(flights.size() > 0);
    }

    @Test
    @Order(4)
    public void testFindByCarrier() {

        Flight flight = new Flight(104, "Vistara", "Thane", "Indore", 5200);

        repo.save(flight);

        List<Flight> flights = repo.findByCarrier("Vistara");

        assertTrue(flights.size() > 0);
    }

    @Test
    @Order(5)
    public void testFindByRoute() {

        Flight flight = new Flight(105, "Indigo", "Nagpur", "Pune", 3200);

        repo.save(flight);

        List<Flight> flights = repo.findByRoute("Nagpur", "Pune");

        assertTrue(flights.size() > 0);
    }

    @Test
    @Order(6)
    public void testFindByPriceRange() {

        Flight flight = new Flight(106, "AirAsia", "Mumbai", "Nashik", 4000);

        repo.save(flight);

        List<Flight> flights = repo.findByPriceRange(3000, 5000);

        assertTrue(flights.size() > 0);
    }

    @Test
    @Order(7)
    public void testDelete() {

        Flight flight = new Flight(107, "GoFirst", "Indore", "Nagpur", 3800);

        repo.save(flight);

        repo.deleteById(107);

        Optional<Flight> found = repo.findById(107);

        assertTrue(found.isEmpty());
    }
}