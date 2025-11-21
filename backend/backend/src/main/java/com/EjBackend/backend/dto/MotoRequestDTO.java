package com.EjBackend.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MotoRequestDTO {

    @NotBlank(message = "La marca es obligatoria")
    @Size(min = 2, max = 50, message = "La marca debe tener entre 2 y 50 caracteres")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(min = 1, max = 50, message = "El modelo debe tener entre 1 y 50 caracteres")
    private String modelo;

    @NotBlank(message = "El color es obligatorio")
    @Size(max = 30, message = "El color no puede exceder 30 caracteres")
    private String color;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener máximo 10 dígitos enteros y 2 decimales")
    private Double precio;

    @NotNull(message = "El cilindraje es obligatorio")
    @Min(value = 50, message = "El cilindraje mínimo es 50cc")
    @Max(value = 2000, message = "El cilindraje máximo es 2000cc")
    private Long cilindraje;

    @NotBlank(message = "La potencia es obligatoria")
    @Size(max = 20, message = "La potencia no puede exceder 20 caracteres")
    private String potencia;

    @NotBlank(message = "La velocidad es obligatoria")
    @Size(max = 20, message = "La velocidad no puede exceder 20 caracteres")
    private String velocidad;

    @NotBlank(message = "La aceleración es obligatoria")
    @Size(max = 20, message = "La aceleración no puede exceder 20 caracteres")
    private String aceleracion;

    @NotBlank(message = "El consumo es obligatorio")
    @Size(max = 20, message = "El consumo no puede exceder 20 caracteres")
    private String consumo;

    @NotBlank(message = "El combustible es obligatorio")
    @Size(max = 20, message = "El combustible no puede exceder 20 caracteres")
    private String combustible;

    public MotoRequestDTO() {
    }

    public MotoRequestDTO(String marca, String modelo, String color, Double precio, Long cilindraje,
                          String potencia, String velocidad, String aceleracion, String consumo,
                          String combustible) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
        this.cilindraje = cilindraje;
        this.potencia = potencia;
        this.velocidad = velocidad;
        this.aceleracion = aceleracion;
        this.consumo = consumo;
        this.combustible = combustible;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(Long cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(String aceleracion) {
        this.aceleracion = aceleracion;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }
}

