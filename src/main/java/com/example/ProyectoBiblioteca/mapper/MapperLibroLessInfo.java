package com.example.ProyectoBiblioteca.mapper;

import com.example.ProyectoBiblioteca.dto.LibroDto;
import com.example.ProyectoBiblioteca.dto.LibroLessInfoDto;
import com.example.ProyectoBiblioteca.model.Libro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class MapperLibroLessInfo {

    public static LibroLessInfoDto toDto(Libro libro) {
        LibroLessInfoDto libroLessInfoDto = new LibroLessInfoDto();

        libroLessInfoDto.setTitle(libro.getTitle());
        libroLessInfoDto.setPublishDate(libro.getPublishDate());

        return libroLessInfoDto;
    }

    public static Libro toEntity(LibroLessInfoDto libroLessInfoDto) {
        Libro libro = new Libro();

        libro.setTitle(libroLessInfoDto.getTitle());
        libro.setPublishDate(libroLessInfoDto.getPublishDate());

        return libro;
    }
}
