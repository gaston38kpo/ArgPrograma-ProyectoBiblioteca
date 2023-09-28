package com.example.ProyectoBiblioteca.dto;

import com.example.ProyectoBiblioteca.model.Libro;
import lombok.*;

import java.util.Set;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutorDto {

    private String name;
    private String surname;
    private Set<Libro> libros;
}