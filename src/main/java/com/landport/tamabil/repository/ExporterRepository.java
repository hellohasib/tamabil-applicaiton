package com.landport.tamabil.repository;

import com.landport.tamabil.model.Driver;
import com.landport.tamabil.model.Exporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExporterRepository extends JpaRepository<Exporter, Long> {

}
