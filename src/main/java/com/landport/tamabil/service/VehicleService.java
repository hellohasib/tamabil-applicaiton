package com.landport.tamabil.service;

import com.landport.tamabil.model.Pilot;
import com.landport.tamabil.model.Vehicle;
import com.landport.tamabil.model.Weight;
import com.landport.tamabil.repository.PilotRepository;
import com.landport.tamabil.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class VehicleService extends AbstractService<Vehicle, Long> {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    protected JpaRepository<Vehicle, Long> getRepository() {
        return vehicleRepository;
    }

    public List<Vehicle> getSpecificList(String fromdate, String todate) {
        final String sqlQuery = "SELECT * FROM vehicle_tbl where created_at between" +"'"+fromdate+"'"+" and "+"'"+todate+"'";
        Query query = entityManager.createNativeQuery(sqlQuery, Vehicle.class);
        List<Vehicle> list = (List<Vehicle>) query.getResultList();
        return list;
    }
}
