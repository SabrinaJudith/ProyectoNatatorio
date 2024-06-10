package com.natacion.sparta.servicio;

import com.natacion.sparta.modelo.DiaClase;
import com.natacion.sparta.modelo.Socios;
import com.natacion.sparta.modelo.Clases;
import com.natacion.sparta.modelo.Profesores;
import com.natacion.sparta.repositorio.DiaClaseRepositorio;
import com.natacion.sparta.repositorio.SocioRepositorio;
import com.natacion.sparta.repositorio.ClaseRepositorio;
import com.natacion.sparta.repositorio.ProfesorRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NatacionServicio {

    @Autowired
    private SocioRepositorio socioRepositorio;

    @Autowired
    private ClaseRepositorio claseRepositorio;

    @Autowired
    private ProfesorRepositorio profesorRepositorio;
    @Autowired
    private DiaClaseRepositorio diaClaseRepositorio;
    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;
    // Resto del código del servicio...

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    // Operaciones CRUD para Socio
    public List<Socios> listarSocios() {
        return socioRepositorio.findAll();
    }
    public Clases obtenerClasePorNombre(String nombre) {
        Optional<Clases> claseOptional = claseRepositorio.findByNombre(nombre);
        return claseOptional.orElse(null);
    }


    public List<Socios> buscarSocios(Socios filtro) {
        // Verificar qué propiedades del objeto filtro están completadas y construir la consulta dinámica
        if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
            return socioRepositorio.findByNombreContaining(filtro.getNombre());
        } else if (filtro.getApellido() != null && !filtro.getApellido().isEmpty()) {
            return socioRepositorio.findByApellidoContaining(filtro.getApellido());
        } else if (filtro.getDni() != null && !filtro.getDni().isEmpty()) {
            return socioRepositorio.findByDniContaining(filtro.getDni());
        } else {
            // Si ninguna propiedad de filtro está completada, retornar todos los socios
            return socioRepositorio.findAll();
        }
    }
    @Transactional
    public Socios obtenerSocioPorId(Long idSocio) {
        Optional<Socios> optionalSocio = socioRepositorio.findById(idSocio);
        if (optionalSocio.isPresent()) {
            Socios socio = optionalSocio.get();
            Hibernate.initialize(socio.getClases()); // Cargar la colección clases antes de cerrar la sesión
            return socio;
        } else {
            throw new IllegalArgumentException("No se encontró el socio con el ID proporcionado.");
        }
    }





    public Socios guardarSocio(Socios socio) {
        return socioRepositorio.save(socio);
    }

    public boolean eliminarSocio(Long id) {
        try {
            socioRepositorio.deleteById(id);
            return true; // Indica que se ha eliminado correctamente
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores (puedes personalizarlo según tu lógica)
            return false; // Indica que ha ocurrido un error al eliminar
        }
    }

    public Socios modificarSocio(Long id, Socios nuevoSocio) {
        // Verificar si el socio con el ID proporcionado existe en la base de datos
        Optional<Socios> socioExistenteOptional = socioRepositorio.findById(id);
        if (socioExistenteOptional.isPresent()) {
            Socios socioExistente = socioExistenteOptional.get();
            // Actualizar los datos del socio existente con los datos del nuevo socio
            socioExistente.setNombre(nuevoSocio.getNombre());
            socioExistente.setApellido(nuevoSocio.getApellido());
            socioExistente.setDni(nuevoSocio.getDni());
            socioExistente.setTelefono(nuevoSocio.getTelefono());
            socioExistente.setTelefonoEmergencia(nuevoSocio.getTelefonoEmergencia());
            socioExistente.setAlergias(nuevoSocio.getAlergias());
            // Puedes actualizar otros campos según sea necesario

            // Guardar el socio actualizado en el repositorio y devolverlo
            return socioRepositorio.save(socioExistente);
        } else {
            // Si no se encuentra el socio con el ID proporcionado, devolver null o lanzar una excepción según tu lógica de negocio
            return null;
        }
    }

    @Transactional
    public void asociarSocioAClase(Long idSocio, Long idClase) {
        Socios socio = socioRepositorio.findById(idSocio)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));
        Clases clase = claseRepositorio.findById(idClase)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        socio.addClase(clase); // Asegúrate de que este método agregue correctamente la clase al socio
        socioRepositorio.save(socio); // Guarda el socio con la clase asociada
    }

    // Operaciones CRUD para Clase

    // Operaciones CRUD para Clase

    @Transactional(readOnly = true)
    public List<Clases> listarClases() {
        List<Clases> clases = claseRepositorio.findAll();
        for (Clases clase : clases) {
            Hibernate.initialize(clase.getDiasClase()); // Inicializar la colección
        }
        return clases;
    }

    public Long obtenerIdClasePorNombre(String nombreClase) {
        Optional<Clases> optionalClase = claseRepositorio.findByNombre(nombreClase);
        if (optionalClase.isPresent()) {
            return optionalClase.get().getIdClase();
        } else {
            throw new RuntimeException("No se encontró ninguna clase con el nombre proporcionado: " + nombreClase);
        }
    }


    @Transactional(readOnly = true)
    public List<Clases> obtenerClasesConDiasClase() {
        List<Clases> clases = claseRepositorio.findAll();
        for (Clases clase : clases) {
            Hibernate.initialize(clase.getDiasClase());
            System.out.println(clase.getNombre() + " tiene " + clase.getDiasClase().size() + " días.");
        }
        return clases;
    }

    @Transactional
    public Clases obtenerClasePorId(Long id) {
        Optional<Clases> claseOptional = claseRepositorio.findByIdWithDiasClase(id);
        if (claseOptional.isPresent()) {
            return claseOptional.get();
        }
        return null;
    }



    public Clases guardarClaseConDias(Clases clase) {
        // Guardar la clase en la base de datos
        Clases claseGuardada = claseRepositorio.save(clase);

        // Guardar los días de clase por separado
        List<DiaClase> diasClase = clase.getDiasClase();
        for (DiaClase diaClase : diasClase) {
            diaClase.setClase(claseGuardada); // Establecer la referencia a la clase guardada
            diaClaseRepositorio.save(diaClase); // Guardar el día de clase
        }

        return claseGuardada;
    }


    @Transactional
    public Clases actualizarClase(Clases clase) {
        Optional<Clases> claseExistenteOptional = claseRepositorio.findById(clase.getIdClase());
        if (claseExistenteOptional.isPresent()) {
            Clases claseExistente = claseExistenteOptional.get();

            // Actualizar los atributos de la clase existente
            claseExistente.setNombre(clase.getNombre());

            // Actualizar los días de clase
            List<DiaClase> diasClaseExistentes = claseExistente.getDiasClase();
            diasClaseExistentes.clear(); // Limpiar los días existentes
            for (DiaClase diaClase : clase.getDiasClase()) {
                diaClase.setClase(claseExistente); // Establecer la referencia a la clase existente
                diasClaseExistentes.add(diaClaseRepositorio.save(diaClase)); // Guardar o actualizar el día de clase
            }

            // Guardar los cambios en la base de datos
            return claseRepositorio.save(claseExistente);
        } else {
            throw new IllegalArgumentException("La clase especificada no existe en la base de datos.");
        }
    }

    @Transactional
    public Clases actualizarHorarioDiaClase(Clases clase) {
        Optional<Clases> claseExistenteOptional = claseRepositorio.findByIdWithDiasClase(clase.getIdClase());
        if (claseExistenteOptional.isPresent()) {
            Clases claseExistente = claseExistenteOptional.get();

            // Actualizar los atributos de la clase existente
            claseExistente.setNombre(clase.getNombre());

            // Obtener los días de clase existentes
            List<DiaClase> diasClaseExistentes = claseExistente.getDiasClase();

            // Actualizar solo el día de clase especificado
            for (DiaClase diaClaseActualizado : clase.getDiasClase()) {
                for (DiaClase diaClaseExistente : diasClaseExistentes) {
                    if (diaClaseExistente.getId().equals(diaClaseActualizado.getId())) {
                        diaClaseExistente.setDia(diaClaseActualizado.getDia());
                        diaClaseExistente.setHorarioInicio(diaClaseActualizado.getHorarioInicio());
                        diaClaseExistente.setHorarioFin(diaClaseActualizado.getHorarioFin());
                    }
                }
            }

            // Guardar los cambios en la base de datos
            return claseRepositorio.save(claseExistente);
        } else {
            throw new IllegalArgumentException("La clase especificada no existe en la base de datos.");
        }
    }
    // En tu servicio (NatacionServicio)

    @Transactional
    public void eliminarDiaClase(Long idDiaClase) {
        // Buscar el día de clase por su ID
        Optional<DiaClase> diaClaseOptional = diaClaseRepositorio.findById(idDiaClase);
        if (diaClaseOptional.isPresent()) {
            DiaClase diaClase = diaClaseOptional.get();

            // Eliminar el día de clase
            diaClaseRepositorio.delete(diaClase);
        } else {
            throw new IllegalArgumentException("El día de clase especificado no existe en la base de datos.");
        }
    }





    // Operaciones CRUD para Profesor
    public List<Profesores> listarProfesores() {
        List<Profesores> profesores = profesorRepositorio.findAll();
        for (Profesores profesor : profesores) {
            Hibernate.initialize(profesor.getClases()); // Inicializar la colección de clases
        }
        return profesores;
    }


    public Profesores obtenerProfesorPorId(Long id) {
        Optional<Profesores> profesorOptional = profesorRepositorio.findById(id);
        return profesorOptional.orElse(null);
    }

    public List<Profesores> buscarProfe(Profesores filtro) {
        // Verificar qué propiedades del objeto filtro están completadas y construir la consulta dinámica
        if (filtro.getNombre() != null && !filtro.getNombre().isEmpty()) {
            return profesorRepositorio.findByNombreContaining(filtro.getNombre());
        } else if (filtro.getApellido() != null && !filtro.getApellido().isEmpty()) {
            return profesorRepositorio.findByApellidoContaining(filtro.getApellido());
        } else if (filtro.getDireccion() != null && !filtro.getDireccion().isEmpty()) {
            return profesorRepositorio.findByDireccionContaining(filtro.getDireccion());
        } else {
            // Si ninguna propiedad de filtro está completada, retornar todos los socios
            return profesorRepositorio.findAll();
        }
    }
    public Profesores guardarProfesor(Profesores profesor) {
        return profesorRepositorio.save(profesor);
    }

    public boolean eliminarProfesor(Long id) {
        try {
            profesorRepositorio.deleteById(id);
            return true; // Indica que se ha eliminado correctamente
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores (puedes personalizarlo según tu lógica)
            return false; // Indica que ha ocurrido un error al eliminar
        }
    }

    // Método modificarSocio en tu servicio

    public Profesores modificarProfe(Long id, Profesores nuevoProfe) {
        // Verificar si el socio con el ID proporcionado existe en la base de datos
        Optional<Profesores> profeExistenteOptional = profesorRepositorio.findById(id);
        if (profeExistenteOptional.isPresent()) {
            Profesores profeExistente = profeExistenteOptional.get();
            // Actualizar los datos del socio existente con los datos del nuevo socio
            profeExistente.setNombre(nuevoProfe.getNombre());
            profeExistente.setApellido(nuevoProfe.getApellido());
            profeExistente.setTelefono(nuevoProfe.getTelefono());
            profeExistente.setDireccion(nuevoProfe.getDireccion());
            // Puedes actualizar otros campos según sea necesario

            // Guardar el socio actualizado en el repositorio y devolverlo
            return profesorRepositorio.save(profeExistente);
        } else {
            // Si no se encuentra el socio con el ID proporcionado, devolver null o lanzar una excepción según tu lógica de negocio
            return null;
        }
    }

    @Transactional
    public Clases asociarClaseConProfesor(String nombreClase, Long idProfesor) {
        Optional<Clases> claseOptional = claseRepositorio.findByNombre(nombreClase);
        Optional<Profesores> profesorOptional = profesorRepositorio.findById(idProfesor);

        if (claseOptional.isPresent() && profesorOptional.isPresent()) {
            Clases clase = claseOptional.get();
            Profesores profesor = profesorOptional.get();
            clase.setProfesor(profesor);
            return claseRepositorio.save(clase);
        } else {
            throw new IllegalArgumentException("Clase o profesor no encontrados");
        }
    }

}
