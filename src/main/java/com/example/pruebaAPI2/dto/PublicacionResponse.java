package com.example.pruebaAPI2.dto;

import java.util.List;

public class PublicacionResponse {

    private List<PublicacionDTO> contenido;
    private int numeroDePagina;
    private int medidaDePagina;
    private long totalElementos;
    private int totalPagina;
    private boolean ultima;

    public List<PublicacionDTO> getContenido() {
        return contenido;
    }

    public void setContenido(List<PublicacionDTO> contenido) {
        this.contenido = contenido;
    }

    public int getNumeroDePagina() {
        return numeroDePagina;
    }

    public void setNumeroDePagina(int numeroDePagina) {
        this.numeroDePagina = numeroDePagina;
    }

    public int getMedidaDePagina() {
        return medidaDePagina;
    }

    public void setMedidaDePagina(int medidaDePagina) {
        this.medidaDePagina = medidaDePagina;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTotalPagina() {
        return totalPagina;
    }

    public void setTotalPagina(int totalPagina) {
        this.totalPagina = totalPagina;
    }

    public boolean isUltima() {
        return ultima;
    }

    public void setUltima(boolean ultima) {
        this.ultima = ultima;
    }

    public PublicacionResponse() {
        super();
    }
}
