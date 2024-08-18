package com.example.REST_API.repository;

import com.example.REST_API.model.Lokasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LokasiRepository extends JpaRepository<Lokasi, Long> {
}
