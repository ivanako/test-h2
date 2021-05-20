package com.santanderglobaltech.ccc.c3cam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.santanderglobaltech.ccc.c3cam.model.Broker;

@Repository
public interface IRepoBroker extends JpaRepository<Broker, String> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE BROKERS SET BRK_ACTIVE = FALSE WHERE UPPER(BRK_CODE) = UPPER(:code)", nativeQuery = true)
	public int deactivateBroker(@Param("code") String brkCode);
}
