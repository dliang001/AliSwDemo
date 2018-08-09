package com.dxc.demo.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.demo.data.domain.InformationResource;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Repository
public interface InformationResourceRepository extends JpaRepository<InformationResource, String> {

    Page<InformationResource> findByNameIgnoreCaseContainingOrSystemIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(
            String name, 
            String system,
            String description,
            Pageable pageable);

}
