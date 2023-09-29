package com.example.ProyectoBiblioteca.service;

import com.example.ProyectoBiblioteca.dto.AutorDto;
import com.example.ProyectoBiblioteca.dto.LibroDto;

import java.util.List;

public interface AutorI {

    List<AutorDto> findAllAutores();

    String saveAutor(AutorDto autorDto);

    AutorDto findAutor(String surname);

    AutorDto findAutor(Long id);

    String updateAutor(Long id, AutorDto autorDto);

    String deleteAutor(Long id);
}

