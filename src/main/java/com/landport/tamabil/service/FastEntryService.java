package com.landport.tamabil.service;

import com.landport.tamabil.model.FastEntry;
import com.landport.tamabil.repository.CargoRepository;
import com.landport.tamabil.repository.FastEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class FastEntryService extends AbstractService<FastEntry, Long> {

    @Autowired
    private FastEntryRepository fastEntryRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    protected JpaRepository<FastEntry, Long> getRepository() {
        return fastEntryRepository;
    }

    public List<FastEntry> getSpecificList(String fromdate, String todate) {
        final String sqlQuery = "SELECT * FROM fast_entry_tbl where created_at between" +"'"+fromdate+"'"+" and "+"'"+todate+"'";
        Query query = entityManager.createNativeQuery(sqlQuery, FastEntry.class);
        List<FastEntry> list = (List<FastEntry>) query.getResultList();
        return list;
    }

    public List<FastEntry> getSpecificLcList(String fromdate, String todate, String lcno) {
        final String sqlQuery = "SELECT * FROM fast_entry_tbl where created_at between" +"'"+fromdate+"'"+" and "+"'"+todate+"'"+"AND lc_no="+lcno;
        Query query = entityManager.createNativeQuery(sqlQuery, FastEntry.class);
        List<FastEntry> list = (List<FastEntry>) query.getResultList();
        return list;
    }
}
