package com.example.ProyectoBiblioteca.controller;

import com.example.ProyectoBiblioteca.dto.LibroDto;
import com.example.ProyectoBiblioteca.mapper.MapperLibro;
import com.example.ProyectoBiblioteca.model.Libro;
import com.example.ProyectoBiblioteca.service.LibroI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/libros")
public class LibroController {

    private final LibroI libroService;

    @GetMapping("/findAll")
    public ResponseEntity<List<LibroDto>> findAllLibros() {
        List<LibroDto> lista = libroService.findAllLibros();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveLibro(@RequestBody LibroDto libroDto) {
        String mensaje = libroService.saveLibro(libroDto);
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @PostMapping("/findByTitle")
    public ResponseEntity<LibroDto> findLibrosByTitle(@RequestBody LibroDto libroDto) {
        if (libroDto == null || libroDto.getTitle() == null || libroDto.getTitle().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        LibroDto libroDtoFound = libroService.findLibro(libroDto.getTitle());
        if (libroDtoFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(libroDtoFound, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateLibro(@RequestParam Long id, @RequestBody LibroDto libroDto) {
        LibroDto libro = libroService.findLibro(id);

        if (libro == null) {
            String mensaje = "Libro con ID " + id + " no encontrado";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        libro.setTitle(libroDto.getTitle());
        libro.setGenre(libroDto.getGenre());
        libro.setAutores(libroDto.getAutores());
        libro.setPublishDate(libroDto.getPublishDate());

        libroService.updateLibro(id, libro);
        String mensaje = "Libro '" + libro.getTitle() + "' fue actualizado correctamente";

        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLibro(@RequestParam Long id) {
        LibroDto libro = libroService.findLibro(id);

        if (libro == null) {
            String mensaje = "Libro con ID " + id + " no encontrado";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        libroService.deleteLibro(id);
        String mensaje = "Libro '" + libro.getTitle() + "' fue eliminado correctamente";

        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

}
