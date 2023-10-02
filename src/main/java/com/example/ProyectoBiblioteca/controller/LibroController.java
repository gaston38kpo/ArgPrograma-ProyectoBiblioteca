package com.example.ProyectoBiblioteca.controller;

import com.example.ProyectoBiblioteca.dto.LibroDto;
import com.example.ProyectoBiblioteca.dto.LibroLessInfoDto;
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
    /* GET http://localhost:8080/libros/findAll */

    @GetMapping("/findAllLessInfo")
    public ResponseEntity<List<LibroLessInfoDto>> findAllLibrosLessInfo() {
        List<LibroLessInfoDto> lista = libroService.findAllLibrosLessInfo();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    /* GET http://localhost:8080/libros/findAllLessInfo */

    @PostMapping("/save")
    public ResponseEntity<String> saveLibro(@RequestBody LibroDto libroDto) {
        String mensaje = libroService.saveLibro(libroDto);
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }
    /* POST http://localhost:8080/libros/save
    BODY:
{
    "title": "Harry Potter y la piedra filosofal",
    "publishDate": "2001-11-29",
    "genre": "Ficción",
    "autores": [
        {
            "name": "Joanne Kathleen",
            "surname": "Rowling "
        }
    ]
}
    */

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
    /* POST http://localhost:8080/libros/findByTitle
{
    "title": "El Hobbit"
}
    */

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
    /* PUT http://localhost:8080/libros/update?id=3
{
    "title": "Dune",
    "publishDate": "2023-09-29T03:00:00.000+00:00",
    "genre": "Ciencia Ficción",
    "autores": [
        {
            "id": 3,
            "name": "Michael",
            "surname": "Johnson"
        }
    ]
}
    */

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
    /* DELETE http://localhost:8080/libros/delete?id=1 */

}
