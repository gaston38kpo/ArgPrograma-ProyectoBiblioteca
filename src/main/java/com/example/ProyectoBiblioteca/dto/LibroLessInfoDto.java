package com.example.ProyectoBiblioteca.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibroLessInfoDto {

    private String title;
    private Date publishDate;
}
