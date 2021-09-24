package com.landport.tamabil.repository;

import com.landport.tamabil.model.Vehicle;
import com.landport.tamabil.model.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {
}
