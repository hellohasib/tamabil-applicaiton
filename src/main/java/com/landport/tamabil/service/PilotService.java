package com.landport.tamabil.service;

import com.landport.tamabil.model.Pilot;
import com.landport.tamabil.model.Weight;
import com.landport.tamabil.repository.PilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class PilotService extends AbstractService<Pilot, Long> {

    @Autowired
    private PilotRepository pilotRepository;
    @Autowired
    EntityManager entityManager;

    @Override
    protected JpaRepository<Pilot, Long> getRepository() {
        return pilotRepository;
    }

    public List<Pilot> getSpecificList(String fromdate, String todate) {
        final String sqlQuery = "SELECT * FROM pilot_tbl where created_at between" +"'"+fromdate+"'"+" and "+"'"+todate+"'";
        Query query = entityManager.createNativeQuery(sqlQuery, Pilot.class);
        List<Pilot> list = (List<Pilot>) query.getResultList();
        return list;
    }
}
