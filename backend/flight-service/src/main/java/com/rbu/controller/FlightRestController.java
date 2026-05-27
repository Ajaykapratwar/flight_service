package com.rbu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rbu.entity.Flight;
import com.rbu.service.FlightService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/flights")
public class FlightRestController {

    @Autowired
    private FlightService service;

    @PostMapping(value = "/add")
    public ResponseEntity<Flight> save(@RequestBody Flight flight) {
        Flight savedFlight = service.save(flight);
        return new ResponseEntity<>(savedFlight, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{code}")
    public ResponseEntity<Flight> findByCode(@PathVariable int code) {
        Flight flight = service.findByCode(code);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @GetMapping(value = "/carrier/{carrier}")
    public ResponseEntity<List<Flight>> findByCarrier(@PathVariable String carrier) {
        List<Flight> flights = service.findByCarrier(carrier);
        if(flights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(flights, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/route")
    public ResponseEntity<List<Flight>> findByRoute(@RequestParam String source, @RequestParam String destination) {
        List<Flight> flights = service.findByRoute(source, destination);
        if(flights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(flights, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Flight>> list() {
        List<Flight> flights = service.list();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping(value = "/price")
    public ResponseEntity<List<Flight>> findByPriceRange(@RequestParam double min, @RequestParam double max) {
        List<Flight> flights = service.findByPriceRange(min, max);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> delete(@PathVariable int code) {
        if(service.delete(code))
            return new ResponseEntity<>("Flight deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Flight not found",  HttpStatus.NOT_FOUND);
    }
}
