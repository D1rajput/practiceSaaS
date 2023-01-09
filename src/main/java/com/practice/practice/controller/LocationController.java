package com.practice.practice.controller;

import com.practice.practice.model.Location;
import com.practice.practice.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/location")
public class LocationController {

    @Autowired
    ILocationService locationService;

    @GetMapping("/all")
    public ResponseEntity<List<Location>> getLocation() throws Exception {
        try {
            List<Location> response = locationService.getLocation();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/save")
    public ResponseEntity<Boolean> saveLocation(@RequestBody Location location) throws Exception {
        try {
            Boolean res = locationService.saveLocation(location);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
