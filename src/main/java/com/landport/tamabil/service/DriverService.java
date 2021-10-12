package com.landport.tamabil.service;

import com.landport.tamabil.model.Driver;
import com.landport.tamabil.model.Pilot;
import com.landport.tamabil.repository.DriverRepository;
import com.landport.tamabil.repository.PilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class DriverService extends AbstractService<Driver, Long> {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    EntityManager entityManager;

    @Override
    protected JpaRepository<Driver, Long> getRepository() {
        return driverRepository;
    }

}
