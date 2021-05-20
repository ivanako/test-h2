package com.santanderglobaltech.ccc.c3cam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santanderglobaltech.ccc.c3cam.model.SMSRequest;

public interface IRepoSMSRequest extends JpaRepository<SMSRequest, Integer> {

}
