package com.landport.tamabil.service;

import com.landport.tamabil.model.Importer;
import com.landport.tamabil.model.Vehicle;
import com.landport.tamabil.repository.ImporterRepository;
import com.landport.tamabil.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImporterService extends AbstractService<Importer, Long> {

    @Autowired
    private ImporterRepository importerRepository;

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
}
