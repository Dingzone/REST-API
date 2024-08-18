package com.example.REST_API.controller;

import com.example.REST_API.model.Lokasi;
import com.example.REST_API.repository.LokasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lokasi")
public class LokasiController {

    @Autowired
    private LokasiRepository lokasiRepository;

    @PostMapping
    public Lokasi addLokasi(@RequestBody Lokasi lokasi) {
        return lokasiRepository.save(lokasi);
    }

    @GetMapping
    public List<Lokasi> getAllLokasi() {
        return lokasiRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lokasi> updateLokasi(@PathVariable Long id, @RequestBody Lokasi lokasiDetails) {
        Optional<Lokasi> optionalLokasi = lokasiRepository.findById(id);
        if (optionalLokasi.isPresent()) {
            Lokasi lokasi = optionalLokasi.get();
            lokasi.setNamaLokasi(lokasiDetails.getNamaLokasi());
            lokasi.setNegara(lokasiDetails.getNegara());
            lokasi.setProvinsi(lokasiDetails.getProvinsi());
            lokasi.setKota(lokasiDetails.getKota());
            return ResponseEntity.ok(lokasiRepository.save(lokasi));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLokasi(@PathVariable Long id) {
        if (lokasiRepository.existsById(id)) {
            lokasiRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
