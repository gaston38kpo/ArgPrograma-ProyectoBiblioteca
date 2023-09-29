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

    @GetMapping("/find/{id}")
    public ResponseEntity<LibroDto> findLibro(@RequestParam String title) {
        LibroDto libroDto = libroService.findLibro(title);
        return new ResponseEntity<>(libroDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateLibro(@RequestBody Long id, @RequestBody LibroDto libroDto) {
        LibroDto libro = libroService.findLibro(id);

        if (libro == null) {
            String mensaje = "Libro con ID " + id + " no encontrado";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        libro.setTitle(libroDto.getTitle());
        libro.setGenre(libroDto.getGenre());
        libro.setAutores(libroDto.getAutores());
        libro.setPublishDate(libroDto.getPublishDate());

        libroService.saveLibro(libro);
        String mensaje = "Libro '" + libro.getTitle() + "' fue actualizado correctamente";

        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLibro(@RequestBody Long id) {
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
