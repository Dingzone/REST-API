package com.example.REST_API.repository;

import com.example.REST_API.model.Proyek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyekRepository extends JpaRepository<Proyek, Long> {
}
