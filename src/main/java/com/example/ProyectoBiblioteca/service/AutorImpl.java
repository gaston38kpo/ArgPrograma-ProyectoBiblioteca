package com.example.ProyectoBiblioteca.service;

import com.example.ProyectoBiblioteca.dto.AutorDto;
import com.example.ProyectoBiblioteca.mapper.MapperAutor;
import com.example.ProyectoBiblioteca.model.Autor;
import com.example.ProyectoBiblioteca.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutorImpl implements AutorI {

    private final AutorRepository autorRepository;

    @Override
    public List<AutorDto> findAllAutores() {
        return autorRepository.findAll().stream()
                .map(MapperAutor::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public String saveAutor(AutorDto autorDto) {
        Autor autorEntity = MapperAutor.toEntity(autorDto);
        autorRepository.save(autorEntity);
        return "Autor guardado exitosamente";
    }

    @Override
    public AutorDto findAutor(String surname) {
        Autor autorEntity = autorRepository.findBySurname(surname);
        AutorDto autorDto = MapperAutor.toDto(autorEntity);
        return autorDto;
    }

    @Override
    public AutorDto findAutor(Long id) {
        Autor autorEntity = autorRepository.findById(id).orElseThrow();
        AutorDto autorDto = MapperAutor.toDto(autorEntity);
        return autorDto;
    }

    @Override
    public String updateAutor(Long id, AutorDto autorDto) {
        Autor autor = autorRepository.findById(id).orElseThrow();

        autor.setName(autorDto.getName());
        autor.setSurname(autorDto.getSurname());
        autor.setLibros(autorDto.getLibros());

        autorRepository.save(autor);

        return "Autor " + autor.getName() + " fue actualizado correctamente";
    }

    @Override
    public String deleteAutor(Long id) {
        autorRepository.deleteById(id);

        return "El autor de ID: " + id + " fue eliminado correctamente";
    }
}

