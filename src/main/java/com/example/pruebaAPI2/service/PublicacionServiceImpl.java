package com.example.pruebaAPI2.service;

import com.example.pruebaAPI2.dto.PublicacionDTO;
import com.example.pruebaAPI2.dto.PublicacionResponse;
import com.example.pruebaAPI2.entities.Publicacion;
import com.example.pruebaAPI2.exceptions.ResourceNotFoundException;
import com.example.pruebaAPI2.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {

        Publicacion publicacion = mapearEntity(publicacionDTO);

        Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);

        PublicacionDTO publicacionResponse = mapearDTO(nuevaPublicacion);
        return publicacionResponse;
    }

    @Override
    public PublicacionResponse obtenerTodasLasPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor,String sortDir){

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();

        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
        Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);

        List<Publicacion> listaDePublicaciones = publicaciones.getContent();
        List<PublicacionDTO> contenido = listaDePublicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());

        PublicacionResponse publicacionResponse = new PublicacionResponse();
        publicacionResponse.setContenido(contenido);
        publicacionResponse.setMedidaDePagina(publicaciones.getNumber());
        publicacionResponse.setMedidaDePagina(publicaciones.getSize());
        publicacionResponse.setTotalElementos(publicaciones.getTotalElements());
        publicacionResponse.setTotalPagina(publicaciones.getTotalPages());
        publicacionResponse.setUltima(publicaciones.isLast());

        return publicacionResponse;

    }

    @Override
    public PublicacionDTO obtenerPublicacionPorId(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        return mapearDTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());

        Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
        return mapearDTO(publicacionActualizada);
    }

    @Override
    public void eliminarPublicacion(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

        publicacionRepository.delete(publicacion);
    }

    //Convierte entidad a DTO
    private PublicacionDTO mapearDTO(Publicacion publicacion) {
        PublicacionDTO publicacionDTO = new PublicacionDTO();

        publicacionDTO.setId(publicacion.getId());
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setDescripcion(publicacion.getDescripcion());
        publicacionDTO.setContenido(publicacion.getContenido());

        return publicacionDTO;
    }

    //Convierte de DTO a entidad
    private Publicacion mapearEntity(PublicacionDTO publicacionDTO) {
        Publicacion publicacion = new Publicacion();

        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());

        return publicacion;
    }
}