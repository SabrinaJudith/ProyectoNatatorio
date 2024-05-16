package com.natacion.sparta.servicio;

import com.natacion.sparta.modelo.CustomUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRegistroServicio {
    List<CustomUser> listarUsuarios();
    Optional<CustomUser> buscarUsuarioPorId(UUID id);

    CustomUser guardarUsuario(CustomUser usuario);

    void eliminarUsuario(UUID id);

}
