package com.EjBackend.backend.controller;

import com.EjBackend.backend.dto.MotoRequestDTO;
import com.EjBackend.backend.dto.MotoResponseDTO;
import com.EjBackend.backend.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motos")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping
    public ResponseEntity<List<MotoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(motoService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(motoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<MotoResponseDTO> crear(@Valid @RequestBody MotoRequestDTO requestDTO) {
        MotoResponseDTO creada = motoService.crear(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoResponseDTO> actualizar(@PathVariable Long id,
                                                      @Valid @RequestBody MotoRequestDTO requestDTO) {
        return ResponseEntity.ok(motoService.actualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        motoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

