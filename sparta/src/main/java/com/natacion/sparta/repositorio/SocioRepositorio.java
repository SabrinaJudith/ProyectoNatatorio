package com.natacion.sparta.repositorio;

import com.natacion.sparta.modelo.Socios;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocioRepositorio extends JpaRepository<Socios, Long> {
    // Método para buscar socios por nombre
    List<Socios> findByNombreContaining(String nombre);
    List<Socios> findByApellidoContaining(String apellido);
    List<Socios> findByDniContaining(String dni);
    @EntityGraph(attributePaths = "clases")
    List<Socios> findAll();
}