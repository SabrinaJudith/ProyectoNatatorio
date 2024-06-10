package com.natacion.sparta.repositorio;

import com.natacion.sparta.modelo.DiaClase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaClaseRepositorio extends JpaRepository<DiaClase, Long> {
    // Puedes agregar métodos personalizados si necesitas consultas específicas
    void deleteById(Long id);
}

