package com.landport.tamabil.service;

import com.landport.tamabil.model.CnfAgent;
import com.landport.tamabil.model.Importer;
import com.landport.tamabil.model.Weight;
import com.landport.tamabil.repository.CnfRepository;
import com.landport.tamabil.repository.ImporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class CnfService extends AbstractService<CnfAgent, Long> {

    @Autowired
    private CnfRepository cnfRepository;
    @Autowired
    EntityManager entityManager;

    @Override
    protected JpaRepository<CnfAgent, Long> getRepository() {
        return cnfRepository;
    }

    public List<CnfAgent> getSpecificList(String fromdate, String todate) {
        final String sqlQuery = "SELECT * FROM cnf_agent_tbl where created_at between" +"'"+fromdate+"'"+" and "+"'"+todate+"'";
        Query query = entityManager.createNativeQuery(sqlQuery, CnfAgent.class);
        List<CnfAgent> list = (List<CnfAgent>) query.getResultList();
        return list;
    }
}
