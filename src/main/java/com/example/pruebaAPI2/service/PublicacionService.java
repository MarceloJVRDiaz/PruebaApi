package com.example.pruebaAPI2.service;

import com.example.pruebaAPI2.dto.PublicacionDTO;
import com.example.pruebaAPI2.dto.PublicacionResponse;

public interface PublicacionService {

    public PublicacionDTO crearPublicacion (PublicacionDTO publicacionDTO);
    public PublicacionResponse obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor,String sortDir);

    public PublicacionDTO obtenerPublicacionPorId(Long id);

    public PublicacionDTO actualizarPublicacion (PublicacionDTO publicacionDTO,Long id);

    public void eliminarPublicacion(Long id);
}
