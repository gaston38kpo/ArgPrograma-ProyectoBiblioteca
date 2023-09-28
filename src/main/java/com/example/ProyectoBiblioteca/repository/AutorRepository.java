package com.example.ProyectoBiblioteca.repository;

import com.example.ProyectoBiblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository  extends JpaRepository<Libro, Long> {
}