package com.EjBackend.backend.dto;


public class MotoResponseDTO {

    private Long id;
    private String marca;
    private String modelo;
    private String color;
    private Double precio;
    private Long cilindraje;
    private String potencia;
    private String velocidad;
    private String aceleracion;
    private String consumo;
    private String combustible;

    public MotoResponseDTO() {
    }

    public MotoResponseDTO(Long id, String marca, String modelo, String color, Double precio,
                           Long cilindraje, String potencia, String velocidad,
                           String aceleracion, String consumo, String combustible) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

