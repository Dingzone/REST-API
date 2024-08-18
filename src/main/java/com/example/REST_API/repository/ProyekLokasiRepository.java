package com.example.REST_API.repository;

import com.example.REST_API.model.ProyekLokasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyekLokasiRepository extends JpaRepository<ProyekLokasi, Long> {
}
