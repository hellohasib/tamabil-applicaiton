package com.landport.tamabil.service;

import com.landport.tamabil.model.Importer;
import com.landport.tamabil.model.Vehicle;
import com.landport.tamabil.model.Weight;
import com.landport.tamabil.repository.ImporterRepository;
import com.landport.tamabil.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class ImporterService extends AbstractService<Importer, Long> {

    @Autowired
    private ImporterRepository importerRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    protected JpaRepository<Importer, Long> getRepository() {
        return importerRepository;
    }

    public List<Importer> getAllList(){
        return importerRepository.findAll();
    }

    public Optional<Importer> findById(long id) {
        return importerRepository.findById(id);
    }

    public List<Importer> getSpecificList(String fromdate, String todate) {
        final String sqlQuery = "SELECT * FROM importer_tbl where created_at between" +"'"+fromdate+"'"+" and "+"'"+todate+"'";
        Query query = entityManager.createNativeQuery(sqlQuery, Importer.class);
        List<Importer> list = (List<Importer>) query.getResultList();
        return list;
    }
}
