package com.dxc.demo.data.repositories;

import com.dxc.demo.data.domain.SwRule;
import com.dxc.demo.data.domain.SwRuleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SwRuleDetailRepository extends JpaRepository<SwRuleDetail, String> {
    @Modifying
    @Transactional
    @Query("delete from SwRuleDetail t where t.swRule=?1")
    void deleteByRuleId(SwRule swRule);
}
