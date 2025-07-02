package com.example.server.repository;

import com.example.server.entity.Spec;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpecRepository extends JpaRepository<Spec, UUID> {
}
