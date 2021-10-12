package com.landport.tamabil.repository;

import com.landport.tamabil.model.Cargo;
import com.landport.tamabil.model.FastEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FastEntryRepository extends JpaRepository<FastEntry, Long> {

}
