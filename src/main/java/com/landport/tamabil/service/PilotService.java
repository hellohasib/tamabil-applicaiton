package com.landport.tamabil.service;

import com.landport.tamabil.model.Pilot;
import com.landport.tamabil.repository.PilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PilotService extends AbstractService<Pilot, Long> {

    @Autowired
    private PilotRepository pilotRepository;

    @Override
    protected JpaRepository<Pilot, Long> getRepository() {
        return pilotRepository;
    }

}
