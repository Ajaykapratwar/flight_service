package com.rbu.test;

import static org.mockito.BDDMockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbu.controller.FlightRestController;
import com.rbu.entity.Flight;
import com.rbu.service.FlightService;

@WebMvcTest(FlightRestController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestFlightRestController {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FlightService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Flight flight, flight2;

    @BeforeEach
    public void setUp() {
        flight = new Flight(101, "Indigo", "Nagpur", "Mumbai", 3500);
        flight2 = new Flight(102, "AirIndia", "Pune", "Indore", 4200);
    }

    @Test
    @Order(1)
    public void testSaveFlight() throws Exception {
        given(service.save(any(Flight.class))).willReturn(flight);

        ResultActions response = mockMvc.perform(post("/api/v1/flights/add").contentType(MediaType.APPLICATION_JSON)
        		.content(objectMapper.writeValueAsString(flight)));

        response.andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void testFindByCode() throws Exception {
        given(service.findByCode(flight.getCode())).willReturn(flight);

        ResultActions response = mockMvc.perform(
                get("/api/v1/flights/{code}", flight.getCode())
        );

        response.andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void testList() throws Exception {
        List<Flight> flights = Arrays.asList(flight, flight2);

        given(service.list()).willReturn(flights);

        ResultActions response = mockMvc.perform(get("/api/v1/flights/list"));

        response.andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void testFindByCarrier() throws Exception {
        List<Flight> flights = Arrays.asList(flight, flight2);

        given(service.findByCarrier("Indigo")).willReturn(flights);

        ResultActions response = mockMvc.perform(get("/api/v1/flights/carrier/{carrier}", "Indigo"));

        response.andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void testFindByRoute() throws Exception {
        List<Flight> flights = Arrays.asList(flight, flight2);

        given(service.findByRoute("Nagpur", "Mumbai")).willReturn(flights);

        ResultActions response = mockMvc.perform(get("/api/v1/flights/route").param("source", "Nagpur").param("destination", "Mumbai"));

        response.andExpect(status().isOk());
    }

    @Test
    @Order(6)
    public void testFindByPriceRange() throws Exception {
        List<Flight> flights = Arrays.asList(flight, flight2);

        given(service.findByPriceRange(3000, 5000)).willReturn(flights);

        ResultActions response = mockMvc.perform(get("/api/v1/flights/price").param("min", "3000").param("max", "5000"));

        response.andExpect(status().isOk());
    }

    @Test
    @Order(7)
    public void testDelete() throws Exception {
        given(service.delete(flight.getCode())).willReturn(true);

        ResultActions response = mockMvc.perform(delete("/api/v1/flights/{code}", flight.getCode()));

        response.andExpect(status().isOk());
    }
}