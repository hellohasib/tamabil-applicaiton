package com.landport.tamabil.repository;

import com.landport.tamabil.model.CnfAgent;
import com.landport.tamabil.model.Importer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnfRepository extends JpaRepository<CnfAgent, Long> {

}
