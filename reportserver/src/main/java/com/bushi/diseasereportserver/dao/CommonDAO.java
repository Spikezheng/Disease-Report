package com.bushi.diseasereportserver.dao;
import com.bushi.diseasereportserver.pojo.Common;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonDAO extends JpaRepository<Common,Integer>{
    Common findByNameAndPassword(String name, String password);
}
