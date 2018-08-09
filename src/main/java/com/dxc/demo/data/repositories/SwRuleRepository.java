package com.dxc.demo.data.repositories;

import com.dxc.demo.data.domain.SwRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwRuleRepository extends JpaRepository<SwRule, String> {
}
