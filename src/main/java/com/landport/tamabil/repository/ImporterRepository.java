package com.landport.tamabil.repository;

import com.landport.tamabil.model.Importer;
import com.landport.tamabil.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImporterRepository extends JpaRepository<Importer, Long> {

}
