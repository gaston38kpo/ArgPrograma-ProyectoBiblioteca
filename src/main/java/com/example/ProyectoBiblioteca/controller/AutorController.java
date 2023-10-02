package com.example.ProyectoBiblioteca.controller;

import com.example.ProyectoBiblioteca.dto.AutorDto;
import com.example.ProyectoBiblioteca.dto.LibroDto;
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

    @PostMapping("/findBySurname")
    public ResponseEntity<AutorDto> findAutorBySurname(@RequestBody AutorDto autorDto) {
        if (autorDto == null || autorDto.getSurname() == null || autorDto.getSurname().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AutorDto autorDtoFound = autorService.findAutor(autorDto.getSurname());
        if (autorDtoFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(autorDtoFound, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<AutorDto> findAutorById(@RequestParam Long id) {
        AutorDto autorDto = autorService.findAutor(id);
        return new ResponseEntity<>(autorDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAutor(@RequestParam Long id, @RequestBody AutorDto autorDto) {
        AutorDto autor = autorService.findAutor(id);

        if (autor == null) {
            String mensaje = "Autor con ID " + id + " no encontrado";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        autor.setName(autorDto.getName());
        autor.setSurname(autorDto.getSurname());
        autor.setLibros(autorDto.getLibros()); // Asumiendo que los libros se actualizan de esta manera

        autorService.updateAutor(id, autor);
        String mensaje = "Autor '" + autor.getName() + " " + autor.getSurname() + "' fue actualizado correctamente";

        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAutor(@RequestParam Long id) {
        String mensaje = autorService.deleteAutor(id);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}

