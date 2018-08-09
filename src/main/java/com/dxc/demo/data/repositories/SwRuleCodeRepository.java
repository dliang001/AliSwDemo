package com.dxc.demo.data.repositories;

import com.dxc.demo.data.domain.SwRuleCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwRuleCodeRepository extends JpaRepository<SwRuleCode, String> {
}
