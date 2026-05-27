package com.rbu.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;

import com.rbu.entity.Flight;
import com.rbu.repo.FlightRepository;
import com.rbu.service.FlightServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestFlightService {

    @Mock
    private FlightRepository repo;

    @InjectMocks
    private FlightServiceImpl service;

    private Flight flight;
    private Flight flight2;

    @BeforeEach
    public void setUp() {
        flight = new Flight(101, "Indigo", "Nagpur", "Mumbai", 3500);
        flight2 = new Flight(102, "AirIndia", "Pune", "Indore", 4200);
    }

    @Test
    @DisplayName("Test 1 : Save Flight Service")
    @Order(1)
    public void testSaveFlight() {
        given(repo.save(flight)).willReturn(flight);
        Flight savedFlight = service.save(flight);
        assertNotNull(savedFlight);
    }

    @Test
    @DisplayName("Test 2 : Find Flight By Code Service")
    @Order(2)
    public void testFindByCode() {
        given(repo.findById(101)).willReturn(Optional.of(flight));
        Flight result = service.findByCode(101);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Test 3 : List All Flights Service")
    @Order(3)
    public void testList() {
        given(repo.findAll()).willReturn(List.of(flight, flight2));

        List<Flight> flights = service.list();

        assertEquals(2, flights.size());
    }

    @Test
    @DisplayName("Test 4 : Find Flight By Carrier Service")
    @Order(4)
    public void testFindByCarrier() {
        given(repo.findByCarrier("Indigo")).willReturn(List.of(flight));

        List<Flight> flights = service.findByCarrier("Indigo");

        assertEquals(1, flights.size());
    }

    @Test
    @DisplayName("Test 5 : Find Flight By Route Service")
    @Order(5)
    public void testFindByRoute() {
        given(repo.findByRoute("Nagpur", "Mumbai")).willReturn(List.of(flight));

        List<Flight> flights = service.findByRoute("Nagpur", "Mumbai");

        assertEquals(1, flights.size());
    }

    @Test
    @DisplayName("Test 6 : Find Flight By Price Range Service")
    @Order(6)
    public void testFindByPriceRange() {
        given(repo.findByPriceRange(3000, 5000)).willReturn(List.of(flight, flight2));

        List<Flight> flights = service.findByPriceRange(3000, 5000);

        assertEquals(2, flights.size());
    }

    @Test
    @DisplayName("Test 7 : Delete Flight Service")
    @Order(7)
    public void testDelete() {
        willDoNothing().given(repo).deleteById(flight.getCode());

        service.delete(flight.getCode());

        verify(repo, times(1)).deleteById(flight.getCode());
    }
}