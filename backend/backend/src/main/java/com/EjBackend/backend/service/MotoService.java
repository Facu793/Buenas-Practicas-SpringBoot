package com.EjBackend.backend.service;

import com.EjBackend.backend.dto.MotoRequestDTO;
import com.EjBackend.backend.dto.MotoResponseDTO;
import com.EjBackend.backend.exception.ResourceNotFoundException;
import com.EjBackend.backend.exception.ValidationException;
import com.EjBackend.backend.model.EstadoMoto;
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
        validarStock(requestDTO.getStock());
        Moto moto = new Moto();
        updateEntityFromRequest(moto, requestDTO);
        moto.setEstado(EstadoMoto.DISPONIBLE); // Estado inicial por defecto
        moto.setPrecio(calcularPrecioTotal(requestDTO.getPrecioBase()));
        Moto guardada = motoRepository.save(moto);
        return toResponseDTO(guardada);
    }

    @Transactional
    public MotoResponseDTO actualizar(Long id, MotoRequestDTO requestDTO) {
        validarReglasDeNegocio(requestDTO);
        validarStock(requestDTO.getStock());
        Moto moto = findByIdOrThrow(id);
        validarEstadoParaOperacion(moto, "actualizar");
        updateEntityFromRequest(moto, requestDTO);
        moto.setPrecio(calcularPrecioTotal(requestDTO.getPrecioBase()));
        Moto actualizada = motoRepository.save(moto);
        return toResponseDTO(actualizada);
    }

    @Transactional
    public void eliminar(Long id) {
        Moto moto = findByIdOrThrow(id);
        if (moto.getCliente() != null) {
            throw new ValidationException("No se puede eliminar una moto que tiene un cliente asociado");
        }
        motoRepository.delete(moto);
    }

    private Moto findByIdOrThrow(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto", id));
    }

    private void validarReglasDeNegocio(MotoRequestDTO requestDTO) {
        if (requestDTO.getPrecioBase() != null && requestDTO.getPrecioBase() <= 0) {
            throw new ValidationException("El precio base debe ser mayor a cero");
        }
        if (requestDTO.getCilindraje() != null && requestDTO.getCilindraje() < 50) {
            throw new ValidationException("El cilindraje debe ser igual o mayor a 50cc");
        }
    }

    private void validarStock(Integer stock) {
        if (stock == null || stock < 0) {
            throw new ValidationException("El stock debe ser mayor o igual a cero");
        }
        if (stock == 0) {
            throw new ValidationException("No se puede crear/actualizar una moto sin stock disponible");
        }
    }

    private void validarEstadoParaOperacion(Moto moto, String operacion) {
        if (moto.getEstado() == EstadoMoto.MANTENIMIENTO) {
            throw new ValidationException("No se puede " + operacion + " una moto que está en mantenimiento");
        }
        if (moto.getEstado() == EstadoMoto.VENDIDA && "actualizar".equals(operacion)) {
            throw new ValidationException("No se puede actualizar una moto que ya fue vendida");
        }
    }

    private Double calcularPrecioTotal(Double precioBase) {
        if (precioBase == null || precioBase <= 0) {
            return precioBase;
        }
        // Impuesto del 21% (IVA)
        double impuesto = precioBase * 0.21;
        // Comisión del 5%
        double comision = precioBase * 0.05;
        return precioBase + impuesto + comision;
    }

    private void updateEntityFromRequest(Moto moto, MotoRequestDTO requestDTO) {
        moto.setMarca(requestDTO.getMarca());
        moto.setModelo(requestDTO.getModelo());
        moto.setColor(requestDTO.getColor());
        moto.setPrecioBase(requestDTO.getPrecioBase());
        moto.setCilindraje(requestDTO.getCilindraje());
        moto.setPotencia(requestDTO.getPotencia());
        moto.setVelocidad(requestDTO.getVelocidad());
        moto.setAceleracion(requestDTO.getAceleracion());
        moto.setConsumo(requestDTO.getConsumo());
        moto.setCombustible(requestDTO.getCombustible());
        moto.setStock(requestDTO.getStock());
    }

    private MotoResponseDTO toResponseDTO(Moto moto) {
        Double precioTotal = calcularPrecioTotal(moto.getPrecioBase());
        return new MotoResponseDTO(
                moto.getId(),
                moto.getMarca(),
                moto.getModelo(),
                moto.getColor(),
                moto.getPrecio(),
                moto.getPrecioBase(),
                precioTotal,
                moto.getCilindraje(),
                moto.getPotencia(),
                moto.getVelocidad(),
                moto.getAceleracion(),
                moto.getConsumo(),
                moto.getCombustible(),
                moto.getStock(),
                moto.getEstado()
        );
    }
}

