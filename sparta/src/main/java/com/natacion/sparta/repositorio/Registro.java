package com.natacion.sparta.repositorio;

import com.natacion.sparta.modelo.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface Registro extends JpaRepository<CustomUser, UUID>{

    Optional<CustomUser> findByNombre(String nombreUsuario);
}
