package com.landport.tamabil.service;

import com.landport.tamabil.model.Cargo;
import com.landport.tamabil.model.CnfAgent;
import com.landport.tamabil.repository.CargoRepository;
import com.landport.tamabil.repository.CnfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CargoService extends AbstractService<Cargo, Long> {

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    protected JpaRepository<Cargo, Long> getRepository() {
        return cargoRepository;
    }

}
