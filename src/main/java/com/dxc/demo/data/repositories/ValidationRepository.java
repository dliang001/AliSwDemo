package com.dxc.demo.data.repositories;

import com.dxc.demo.data.domain.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, String> {
}
