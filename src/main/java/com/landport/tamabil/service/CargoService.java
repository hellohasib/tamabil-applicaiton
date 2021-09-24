package com.landport.tamabil.service;

import com.landport.tamabil.model.Cargo;
import com.landport.tamabil.model.CnfAgent;
import com.landport.tamabil.model.Weight;
import com.landport.tamabil.repository.CargoRepository;
import com.landport.tamabil.repository.CnfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class CargoService extends AbstractService<Cargo, Long> {

    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    protected JpaRepository<Cargo, Long> getRepository() {
        return cargoRepository;
    }

    public List<Cargo> getSpecificList(String fromdate, String todate) {
        final String sqlQuery = "SELECT * FROM cargo_tbl where created_at between" +"'"+fromdate+"'"+" and "+"'"+todate+"'";
        Query query = entityManager.createNativeQuery(sqlQuery, Cargo.class);
        List<Cargo> list = (List<Cargo>) query.getResultList();
        return list;
    }
}
