package com.landport.tamabil.repository;

import com.landport.tamabil.model.Cargo;
import com.landport.tamabil.model.CnfAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
