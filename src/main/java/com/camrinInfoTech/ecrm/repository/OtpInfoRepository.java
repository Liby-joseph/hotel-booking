package com.camrinInfoTech.ecrm.repository;

import com.camrinInfoTech.ecrm.entity.OTPInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpInfoRepository extends JpaRepository<OTPInfo, Long> {

OTPInfo findByTypeAndUserId(String type, Long id);
}
