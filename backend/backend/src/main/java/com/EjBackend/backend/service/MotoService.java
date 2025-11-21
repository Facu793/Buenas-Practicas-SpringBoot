package com.EjBackend.backend.service;

import com.EjBackend.backend.dto.MotoRequestDTO;
import com.EjBackend.backend.dto.MotoResponseDTO;
import com.EjBackend.backend.model.Moto;
import com.EjBackend.backend.repository.MotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotoService {

    private final MotoRepository motoRepository;

    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    @Transactional(readOnly = true)
    public List<MotoResponseDTO> obtenerTodas() {
        return motoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MotoResponseDTO obtenerPorId(Long id) {
        Moto moto = findByIdOrThrow(id);
        return toResponseDTO(moto);
    }

    @Transactional
    public MotoResponseDTO crear(MotoRequestDTO requestDTO) {
        validarReglasDeNegocio(requestDTO);
        Moto moto = new Moto();
        updateEntityFromRequest(moto, requestDTO);
        Moto guardada = motoRepository.save(moto);
        return toResponseDTO(guardada);
    }

    @Transactional
    public MotoResponseDTO actualizar(Long id, MotoRequestDTO requestDTO) {
        validarReglasDeNegocio(requestDTO);
        Moto moto = findByIdOrThrow(id);
        updateEntityFromRequest(moto, requestDTO);
        Moto actualizada = motoRepository.save(moto);
        return toResponseDTO(actualizada);
    }

    @Transactional
    public void eliminar(Long id) {
        Moto moto = findByIdOrThrow(id);
        motoRepository.delete(moto);
    }

    private Moto findByIdOrThrow(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Moto con id " + id + " no encontrada"));
    }

    private void validarReglasDeNegocio(MotoRequestDTO requestDTO) {
        if (requestDTO.getPrecio() != null && requestDTO.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (requestDTO.getCilindraje() != null && requestDTO.getCilindraje() < 50) {
            throw new IllegalArgumentException("El cilindraje debe ser igual o mayor a 50cc");
        }
    }

    private void updateEntityFromRequest(Moto moto, MotoRequestDTO requestDTO) {
        moto.setMarca(requestDTO.getMarca());
        moto.setModelo(requestDTO.getModelo());
        moto.setColor(requestDTO.getColor());
        moto.setPrecio(requestDTO.getPrecio());
        moto.setCilindraje(requestDTO.getCilindraje());
        moto.setPotencia(requestDTO.getPotencia());
        moto.setVelocidad(requestDTO.getVelocidad());
        moto.setAceleracion(requestDTO.getAceleracion());
        moto.setConsumo(requestDTO.getConsumo());
        moto.setCombustible(requestDTO.getCombustible());
    }

    private MotoResponseDTO toResponseDTO(Moto moto) {
        return new MotoResponseDTO(
                moto.getId(),
                moto.getMarca(),
                moto.getModelo(),
                moto.getColor(),
                moto.getPrecio(),
                moto.getCilindraje(),
                moto.getPotencia(),
                moto.getVelocidad(),
                moto.getAceleracion(),
                moto.getConsumo(),
                moto.getCombustible()
        );
    }
}

