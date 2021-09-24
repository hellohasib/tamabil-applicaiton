package com.landport.tamabil.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.landport.tamabil.model.Weight;
import com.landport.tamabil.repository.WeightRepository;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
public class WeightService extends AbstractService<Weight, Long> {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Autowired
    private WeightRepository weightRepository;
    @Autowired
    EntityManager entityManager;

    @Override
    protected JpaRepository<Weight, Long> getRepository() {
        return weightRepository;
    }

    public List<Weight> getAll() {
        return weightRepository.findAll();
    }

    public List<Weight> getSpecificList(String fromdate, String todate) throws ParseException {
        final String sqlQuery = "SELECT * FROM weight_tbl where created_at between" +"'"+fromdate+"'"+" and "+"'"+todate+"'";
        Query query = entityManager.createNativeQuery(sqlQuery,Weight.class);
        List<Weight> list = (List<Weight>) query.getResultList();
        return list;
    }


}
