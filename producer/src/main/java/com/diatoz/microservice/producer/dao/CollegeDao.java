package com.diatoz.microservice.producer.dao;

import com.diatoz.microservice.producer.entity.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeDao extends JpaRepository<CollegeEntity, Integer> {
}
