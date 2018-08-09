package com.dxc.demo.data.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.demo.data.domain.Metadata;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Repository
public interface MetadataRepository extends JpaRepository<Metadata, String> {

    Page<Metadata> findByNameIgnoreCaseContainingOrCodeIgnoreCaseContainingOrAttributesIgnoreCaseContaining(String name, String code,
            String attributes, Pageable pageable);

    @Query("select m from Metadata m where m.attributes = :attributes order by m.name, m.code")
    List<Metadata> findByAttributesQuery(@Param("attributes") String attributes);
}
