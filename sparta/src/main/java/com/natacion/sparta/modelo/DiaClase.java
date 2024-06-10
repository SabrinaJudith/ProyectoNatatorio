package com.natacion.sparta.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaClase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dia;
    private String horarioInicio;
    private String horarioFin;

    // Relación muchos a uno con Clases
    @ManyToOne
    @JoinColumn(name = "clase_id")
    private Clases clase;

    public <T> DiaClase(String s, List<T> asList) {
    }
}
