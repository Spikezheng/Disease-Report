package com.bushi.diseasereportserver.dao;

import com.bushi.diseasereportserver.pojo.CDC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CDCDAO extends JpaRepository<CDC, Integer> {
    CDC findByNameAndPassword(String name, String password);
}
