package com.smartapi.repository;

import com.smartapi.entity.ApiTestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiTestCaseRepository
        extends JpaRepository<ApiTestCase, Long> {
    /*  
        save()
        findAll()
        findById()
        deleteById()
        count()
        these functions are created automatically by Spring Data JPA
    */
}