package com.example.demo.repository;

import com.example.demo.model.SubtaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtaskRepository extends JpaRepository<SubtaskEntity, Long> {
}
