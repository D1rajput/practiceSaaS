package com.practice.practice.service;

import com.practice.practice.model.Location;
import java.util.List;

public interface ILocationService {
    Boolean saveLocation(Location location) throws Exception;
    List<Location> getLocation() throws Exception;
}
