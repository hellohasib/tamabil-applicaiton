package com.landport.tamabil.repository;

import com.landport.tamabil.model.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotRepository extends JpaRepository<Pilot, Long> {

}
