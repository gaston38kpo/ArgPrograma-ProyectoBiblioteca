package com.example.ProyectoBiblioteca.service;

import com.example.ProyectoBiblioteca.dto.LibroDto;
import com.example.ProyectoBiblioteca.dto.LibroLessInfoDto;
import com.example.ProyectoBiblioteca.model.Libro;

import java.util.List;

public interface LibroI {

    List<LibroDto> findAllLibros();

    List<LibroLessInfoDto> findAllLibrosLessInfo();

    String saveLibro(LibroDto libroDto);

    LibroDto findLibro(String title);

    LibroDto findLibro(Long id);

    String updateLibro(Long id, LibroDto libroDto);

    String deleteLibro(Long id);
}
