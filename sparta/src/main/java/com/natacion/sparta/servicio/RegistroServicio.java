package com.natacion.sparta.servicio;
import com.natacion.sparta.modelo.CustomUser;
import com.natacion.sparta.repositorio.Registro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistroServicio implements IRegistroServicio {

    private final Registro registro;

    @Autowired
    public RegistroServicio(Registro registro) {
        this.registro = registro;
    }

    @Override
    public List<CustomUser> listarUsuarios() {
        return registro.findAll();
    }

    @Override
    public Optional<CustomUser> buscarUsuarioPorId(UUID id) {
        return registro.findById(id);
    }

    @Override
    public CustomUser guardarUsuario(CustomUser usuario) {
        return registro.save(usuario);
    }

    @Override
    public void eliminarUsuario(UUID id) {
        registro.deleteById(id);
    }


    public boolean verificarCredenciales(String nombreUsuario, String contraseña) {
        // Buscar el usuario por su nombre en la base de datos
        Optional<CustomUser> usuario = registro.findByNombre(nombreUsuario);

        // Verificar si se encontró un usuario y si la contraseña coincide
        return usuario.isPresent() && usuario.get().getPassword().equals(contraseña);
    }
}


