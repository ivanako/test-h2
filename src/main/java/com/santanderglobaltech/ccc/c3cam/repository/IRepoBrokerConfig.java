package com.santanderglobaltech.ccc.c3cam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santanderglobaltech.ccc.c3cam.model.BrokerConfig;

@Repository
public interface IRepoBrokerConfig extends JpaRepository<BrokerConfig, Integer> {

}
