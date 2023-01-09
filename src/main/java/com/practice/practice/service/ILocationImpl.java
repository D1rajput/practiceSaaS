package com.practice.practice.service;

import com.practice.practice.model.Location;
import com.practice.practice.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ILocationImpl implements ILocationService {

    @Autowired
    LocationRepo locationRepo;

    @Override
    public Boolean saveLocation(Location location) throws Exception {
        if (location != null) {
            Location data = new Location();
            if (location.getId() != null) {
                data.setId(location.getId());
            }
            data.setName(location.getName());
            Location result = locationRepo.save(data);
            if (result != null) {
                return true;
            }
        }
        throw new Exception("Data can not be empty");
    }

    @Override
    public List<Location> getLocation() throws Exception {
        return locationRepo.findAll();
    }
}
