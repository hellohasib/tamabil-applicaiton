package com.landport.tamabil.service;

import com.landport.tamabil.model.Driver;
import com.landport.tamabil.model.Goods;
import com.landport.tamabil.repository.DriverRepository;
import com.landport.tamabil.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class GoodsService extends AbstractService<Goods, Long> {

    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    EntityManager entityManager;

    @Override
    protected JpaRepository<Goods, Long> getRepository() {
        return goodsRepository;
    }

}
