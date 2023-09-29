package com.example.ProyectoBiblioteca.controller;

import com.example.ProyectoBiblioteca.dto.AutorDto;
import com.example.ProyectoBiblioteca.service.AutorI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/autores")
public class AutorController {

    private final AutorI autorService;

    @GetMapping("/findAll")
    public ResponseEntity<List<AutorDto>> findAllAutores() {
        List<AutorDto> lista = autorService.findAllAutores();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveAutor(@RequestBody AutorDto autorDto) {
        String mensaje = autorService.saveAutor(autorDto);
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<AutorDto> findAutor(@RequestBody String surname) {
        AutorDto autorDto = autorService.findAutor(surname);
        return new ResponseEntity<>(autorDto, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AutorDto> findAutorById(@RequestParam Long id) {
        AutorDto autorDto = autorService.findAutor(id);
        return new ResponseEntity<>(autorDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAutor(@RequestBody Long id, @RequestBody AutorDto autorDto) {
        String mensaje = autorService.updateAutor(id, autorDto);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAutor(@RequestBody Long id) {
        String mensaje = autorService.deleteAutor(id);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}

