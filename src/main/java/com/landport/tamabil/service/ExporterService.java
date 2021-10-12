package com.landport.tamabil.service;

import com.landport.tamabil.model.Exporter;
import com.landport.tamabil.model.Goods;
import com.landport.tamabil.repository.ExporterRepository;
import com.landport.tamabil.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class ExporterService extends AbstractService<Exporter, Long> {

    @Autowired
    private ExporterRepository exporterRepository;
    @Autowired
    EntityManager entityManager;

    @Override
    protected JpaRepository<Exporter, Long> getRepository() {
        return exporterRepository;
    }

}
