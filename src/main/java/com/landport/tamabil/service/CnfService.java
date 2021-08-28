package com.landport.tamabil.service;

import com.landport.tamabil.model.CnfAgent;
import com.landport.tamabil.model.Importer;
import com.landport.tamabil.repository.CnfRepository;
import com.landport.tamabil.repository.ImporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CnfService extends AbstractService<CnfAgent, Long> {

    @Autowired
    private CnfRepository cnfRepository;

    @Override
    protected JpaRepository<CnfAgent, Long> getRepository() {
        return cnfRepository;
    }

}
