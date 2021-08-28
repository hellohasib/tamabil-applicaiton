package com.landport.tamabil.service;

import com.landport.tamabil.model.Pilot;
import com.landport.tamabil.model.Vehicle;
import com.landport.tamabil.repository.PilotRepository;
import com.landport.tamabil.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService extends AbstractService<Vehicle, Long> {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    protected JpaRepository<Vehicle, Long> getRepository() {
        return vehicleRepository;
    }

}
