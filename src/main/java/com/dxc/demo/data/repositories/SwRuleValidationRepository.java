package com.dxc.demo.data.repositories;

import com.dxc.demo.data.domain.SwRuleValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwRuleValidationRepository extends JpaRepository<SwRuleValidation, String> {
}
