package com.landport.tamabil.service;

import com.landport.tamabil.model.Vehicle;
import com.landport.tamabil.model.Weight;
import com.landport.tamabil.repository.VehicleRepository;
import com.landport.tamabil.repository.WeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class WeightService extends AbstractService<Weight, Long> {

    @Autowired
    private WeightRepository weightRepository;

    @Override
    protected JpaRepository<Weight, Long> getRepository() {
        return weightRepository;
    }

}
