package com.natacion.sparta.repositorio;

import com.natacion.sparta.modelo.Profesores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepositorio extends JpaRepository<Profesores, Long> {
    // Puedes agregar métodos personalizados si necesitas consultas específicas
    List<Profesores> findByNombreContaining(String nombre);
    List<Profesores> findByApellidoContaining(String apellido);
    List<Profesores> findByDireccionContaining(String Direccion);
}