package com.natacion.sparta.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profesores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfesor;

    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;

    // Relación uno a muchos con Clases
    @OneToMany(mappedBy = "profesor", fetch = FetchType.EAGER)
    private List<Clases> clases;

    // Otros campos y métodos si es necesario
}
