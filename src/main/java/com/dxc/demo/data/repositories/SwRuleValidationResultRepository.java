package com.dxc.demo.data.repositories;

import com.dxc.demo.data.domain.SwRuleValidationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SwRuleValidationResultRepository extends JpaRepository<SwRuleValidationResult, String> {
    @Modifying
    @Transactional
    @Query("delete from SwRuleValidationResult t where t.resName=?1")
    void deleteByResName(String resName);
    List<SwRuleValidationResult> findByResName(String resName);
}
