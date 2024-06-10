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
public class Socios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSocio;

    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String telefonoEmergencia;
    private String alergias;

    // Relación muchos a muchos con Clases
    @ManyToMany(mappedBy = "socios", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Clases> clases = new ArrayList<>();

    public void addClase(Clases clase) {
        this.clases.add(clase);
        clase.getSocios().add(this);
    }

    public void removeClase(Clases clase) {
        this.clases.remove(clase);
        clase.getSocios().remove(this);
    }
}
