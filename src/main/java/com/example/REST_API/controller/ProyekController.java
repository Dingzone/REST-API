package com.example.REST_API.controller;

import com.example.REST_API.model.Proyek;
import com.example.REST_API.model.ProyekLokasi;
import com.example.REST_API.model.Lokasi;
import com.example.REST_API.repository.ProyekRepository;
import com.example.REST_API.repository.ProyekLokasiRepository;
import com.example.REST_API.repository.LokasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proyek")
public class ProyekController {

    @Autowired
    private ProyekRepository proyekRepository;

    @Autowired
    private LokasiRepository lokasiRepository;

    @Autowired
    private ProyekLokasiRepository proyekLokasiRepository;

    @PostMapping
    public Proyek addProyek(@RequestBody Proyek proyek) {
        return proyekRepository.save(proyek);
    }

    @GetMapping
    public List<Proyek> getAllProyek() {
        return proyekRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyek> updateProyek(@PathVariable Long id, @RequestBody Proyek proyekDetails) {
        Optional<Proyek> optionalProyek = proyekRepository.findById(id);
        if (optionalProyek.isPresent()) {
            Proyek proyek = optionalProyek.get();
            proyek.setNamaProyek(proyekDetails.getNamaProyek());
            proyek.setDeskripsi(proyekDetails.getDeskripsi());
            return ResponseEntity.ok(proyekRepository.save(proyek));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyek(@PathVariable Long id) {
        if (proyekRepository.existsById(id)) {
            proyekRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{proyekId}/lokasi/{lokasiId}")
    public ResponseEntity<ProyekLokasi> addLokasiToProyek(@PathVariable Long proyekId, @PathVariable Long lokasiId) {
        Optional<Proyek> optionalProyek = proyekRepository.findById(proyekId);
        Optional<Lokasi> optionalLokasi = lokasiRepository.findById(lokasiId);

        if (optionalProyek.isPresent() && optionalLokasi.isPresent()) {
            ProyekLokasi proyekLokasi = new ProyekLokasi();
            proyekLokasi.setProyek(optionalProyek.get());
            proyekLokasi.setLokasi(optionalLokasi.get());
            return ResponseEntity.ok(proyekLokasiRepository.save(proyekLokasi));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{proyekId}/lokasi")
    public ResponseEntity<List<ProyekLokasi>> getAllLokasiByProyek(@PathVariable Long proyekId) {
        Optional<Proyek> optionalProyek = proyekRepository.findById(proyekId);
        if (optionalProyek.isPresent()) {
            List<ProyekLokasi> proyekLokasiList = proyekLokasiRepository.findAllByProyek(optionalProyek.get());
            return ResponseEntity.ok(proyekLokasiList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
