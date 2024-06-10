package com.natacion.sparta.repositorio;

import com.natacion.sparta.modelo.Clases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaseRepositorio extends JpaRepository<Clases, Long> {
    @Query("SELECT c FROM Clases c JOIN FETCH c.diasClase WHERE c.idClase = :id")
    Optional<Clases> findByIdWithDiasClase(@Param("id") Long id);

    Optional<Clases> findByNombre(String nombre);

}

