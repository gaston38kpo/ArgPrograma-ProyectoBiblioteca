package com.example.ProyectoBiblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "autor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autor_id")
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @ManyToMany(mappedBy = "autores")
    private Set<Libro> libros = new HashSet<>();
}
