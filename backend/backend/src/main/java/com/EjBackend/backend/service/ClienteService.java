package com.EjBackend.backend.service;

import com.EjBackend.backend.dto.ClienteRequestDTO;
import com.EjBackend.backend.dto.ClienteResponseDTO;
import com.EjBackend.backend.dto.MotoResponseDTO;
import com.EjBackend.backend.exception.ResourceNotFoundException;
import com.EjBackend.backend.exception.ValidationException;
import com.EjBackend.backend.model.Cliente;
import com.EjBackend.backend.model.Moto;
import com.EjBackend.backend.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> obtenerTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO obtenerPorId(Long id) {
        Cliente cliente = findByIdOrThrow(id);
        return toResponseDTO(cliente);
    }

    @Transactional
    public ClienteResponseDTO crear(ClienteRequestDTO requestDTO) {
        validarReglasDeNegocio(requestDTO);
        Cliente cliente = new Cliente();
        updateEntityFromRequest(cliente, requestDTO);
        Cliente guardado = clienteRepository.save(cliente);
        return toResponseDTO(guardado);
    }

    @Transactional
    public ClienteResponseDTO actualizar(Long id, ClienteRequestDTO requestDTO) {
        validarReglasDeNegocio(requestDTO);
        Cliente cliente = findByIdOrThrow(id);
        updateEntityFromRequest(cliente, requestDTO);
        Cliente actualizado = clienteRepository.save(cliente);
        return toResponseDTO(actualizado);
    }

    @Transactional
    public void eliminar(Long id) {
        Cliente cliente = findByIdOrThrow(id);
        if (!cliente.getMotos().isEmpty()) {
            throw new ValidationException("No se puede eliminar un cliente que tiene motos asociadas");
        }
        clienteRepository.delete(cliente);
    }

    @Transactional(readOnly = true)
    public Double calcularTotalInversion(Long clienteId) {
        Cliente cliente = findByIdOrThrow(clienteId);
        return cliente.getMotos().stream()
                .filter(m -> m.getPrecio() != null)
                .mapToDouble(Moto::getPrecio)
                .sum();
    }

    private Cliente findByIdOrThrow(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
    }

    private void validarReglasDeNegocio(ClienteRequestDTO requestDTO) {
        // Validar que el email no esté duplicado (opcional, si querés agregarlo)
        if (requestDTO.getEmail() != null && !requestDTO.getEmail().isEmpty()) {
            clienteRepository.findAll().stream()
                    .filter(c -> c.getEmail().equalsIgnoreCase(requestDTO.getEmail()))
                    .findFirst()
                    .ifPresent(c -> {
                        throw new ValidationException("El email ya está registrado");
                    });
        }
    }

    public void validarLimiteMotos(Cliente cliente) {
        final int LIMITE_MOTOS = 3;
        if (cliente.getMotos().size() >= LIMITE_MOTOS) {
            throw new ValidationException("Un cliente no puede tener más de " + LIMITE_MOTOS + " motos");
        }
    }

    private void updateEntityFromRequest(Cliente cliente, ClienteRequestDTO requestDTO) {
        cliente.setNombre(requestDTO.getNombre());
        cliente.setApellido(requestDTO.getApellido());
        cliente.setEmail(requestDTO.getEmail());
        cliente.setTelefono(requestDTO.getTelefono());
        cliente.setDireccion(requestDTO.getDireccion());
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente) {
        List<MotoResponseDTO> motosDTO = cliente.getMotos().stream()
                .map(this::motoToResponseDTO)
                .collect(Collectors.toList());

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                motosDTO
        );
    }

    private MotoResponseDTO motoToResponseDTO(Moto moto) {
        // Calcular precio total (precio base + impuestos + comisión)
        Double precioTotal = null;
        if (moto.getPrecioBase() != null && moto.getPrecioBase() > 0) {
            double impuesto = moto.getPrecioBase() * 0.21;
            double comision = moto.getPrecioBase() * 0.05;
            precioTotal = moto.getPrecioBase() + impuesto + comision;
        }
        
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

