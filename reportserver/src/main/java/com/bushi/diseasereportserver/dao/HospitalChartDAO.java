package com.bushi.diseasereportserver.dao;

import com.bushi.diseasereportserver.pojo.Chart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalChartDAO extends JpaRepository<Chart, Integer> {
    List<Chart> findAllBySuperlevel(int superlevel);
}
