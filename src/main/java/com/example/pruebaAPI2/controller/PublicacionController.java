package com.example.pruebaAPI2.controller;

import com.example.pruebaAPI2.dto.PublicacionDTO;
import com.example.pruebaAPI2.dto.PublicacionResponse;
import com.example.pruebaAPI2.service.PublicacionService;
import com.example.pruebaAPI2.utilities.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public PublicacionResponse listarPublicaciones(@RequestParam(value = "pageNo",defaultValue = AppConstants.NUMERO_DE_PAGINA_POR_DEFECTO,required = false) int numeroDePagina, @RequestParam(value = "pageSize",defaultValue = AppConstants.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false) int medidaDePagina, @RequestParam(value = "sortBy",defaultValue = AppConstants.ORDENAR_POR_DEFECTO,required = false) String ordenarPor, @RequestParam(value = "sortDir",defaultValue = AppConstants.ORDENAR_DIRECCION_POR_DEFECTO,required = false) String sortDir){
        return publicacionService.obtenerTodasLasPublicaciones(numeroDePagina, medidaDePagina, ordenarPor, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(publicacionService.obtenerPublicacionPorId(id));
    }

    @PostMapping
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@RequestBody PublicacionDTO publicacionDTO,@PathVariable(name = "id") Long id){

        PublicacionDTO publicacionResponse = publicacionService.actualizarPublicacion(publicacionDTO, id);
        return new ResponseEntity<>(publicacionResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") Long id) {
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<>("Publicacion eliminada con exito", HttpStatus.OK);
    }
}