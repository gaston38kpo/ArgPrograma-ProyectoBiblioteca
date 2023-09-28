package com.example.ProyectoBiblioteca.dto;

import com.example.ProyectoBiblioteca.model.Autor;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibroDto {

    private String title;
    private Date publishDate;
    private String genre;
    private Set<Autor> autores;
}
