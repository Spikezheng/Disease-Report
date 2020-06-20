package com.bushi.diseasereportserver.dao;

import com.bushi.diseasereportserver.pojo.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalDAO extends JpaRepository<Hospital, Integer> {
    Hospital findByNameAndPassword(String name, String password);
}
