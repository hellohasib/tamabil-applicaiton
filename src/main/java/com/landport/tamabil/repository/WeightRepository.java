package com.landport.tamabil.repository;

import com.landport.tamabil.model.Vehicle;
import com.landport.tamabil.model.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {

}
