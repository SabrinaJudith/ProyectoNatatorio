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
public class Clases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClase;

    private String nombre;

    // Relación uno a muchos con ClaseDia
    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
    private List<DiaClase> diasClase = new ArrayList<>();

    // Relación muchos a muchos con Socios
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "socio_clase",
            joinColumns = @JoinColumn(name = "idClase"),
            inverseJoinColumns = @JoinColumn(name = "idSocio"))
    private List<Socios> socios = new ArrayList<>();

    // Relación uno a muchos con Profesores
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesores profesor;

    public void addSocio(Socios socio) {
        this.socios.add(socio);
        socio.getClases().add(this);
    }

    public void removeSocio(Socios socio) {
        this.socios.remove(socio);
        socio.getClases().remove(this);
    }
}

