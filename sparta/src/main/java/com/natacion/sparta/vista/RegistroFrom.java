package com.natacion.sparta.vista;

import com.natacion.sparta.modelo.CustomUser;
import com.natacion.sparta.servicio.RegistroServicio;
import com.natacion.sparta.servicio.NatacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

@Component
public class RegistroFrom extends JFrame {

    private RegistroServicio registroServicio;
    private NatacionServicio natacionServicio;

    @Autowired
    public RegistroFrom(RegistroServicio registroServicio, NatacionServicio natacionServicio) {
        this.registroServicio = registroServicio;
        this.natacionServicio = natacionServicio;
        iniciarForma();
    }

    private void iniciarForma() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);
        setSize(700, 500);
        panelIsquierdaRegistro.setVisible(false);

        setTitle("Sparta Club de Natación");
        setLocationRelativeTo(null);
        setVisible(true);

        // Listener para el botón "Registrarte"
        registrarteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelIzquierda.setVisible(false);
                panelIsquierdaRegistro.setVisible(true);
            }
        });

        // Listener para el botón "Confirmar"
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = tFNombre.getText();
                String apellido = tFApellido.getText();
                String password = new String(password2.getPassword());

                CustomUser nuevoUsuario = new CustomUser();
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setApellido(apellido);
                nuevoUsuario.setPassword(password);

                CustomUser usuarioGuardado = registroServicio.guardarUsuario(nuevoUsuario);

                if (usuarioGuardado != null) {
                    JOptionPane.showMessageDialog(panelPrincipal, "Usuario registrado con éxito");
                    tFNombre.setText("");
                    tFApellido.setText("");
                    password2.setText("");
                    panelIzquierda.setVisible(true);
                    panelIsquierdaRegistro.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(panelPrincipal, "No se pudo registrar el usuario. El usuario ya existe.");
                }
            }
        });
        // Agregar listener de ratón al panel principal
        panelPrincipal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelPrincipal.setBackground(new Color(132, 0, 193, 25)); // Cambia el color al entrar con una opacidad del 100%
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelPrincipal.setBackground(new Color(43, 45, 48, 255)); // Cambia el color al salir sin opacidad (totalmente opaco)
            }
        });

        // Agregar listener de ratón al panel izquierdo
        panelIzquierda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelIzquierda.setBackground(new Color(132, 0, 193, 25)); // Cambia el color al entrar con una opacidad del 100%
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelIzquierda.setBackground(new Color(43, 45, 48, 255)); // Cambia el color al salir sin opacidad (totalmente opaco)
            }
        });

        // Agregar listener de ratón al panel derecho
        panelDerecha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelDerecha.setBackground(new Color(132, 0, 193, 25)); // Cambia el color al entrar con una opacidad del 100%
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelDerecha.setBackground(new Color(43, 45, 48, 255)); // Cambia el color al salir sin opacidad (totalmente opaco)
            }
        });

        // Agregar listener de ratón al panel de registro izquierdo
        panelIsquierdaRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelIsquierdaRegistro.setBackground(new Color(132, 0, 193, 25)); // Cambia el color al entrar con una opacidad del 100%
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panelIsquierdaRegistro.setBackground(new Color(43, 45, 48, 255)); // Cambia el color al salir sin opacidad (totalmente opaco)
            }
        });

        // Listener para el botón "Iniciar Sesión"
        iniciarSesíonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = tFNombre2.getText();
                String contraseña = new String(password1.getPassword());

                boolean inicioSesionExitoso = registroServicio.verificarCredenciales(nombreUsuario, contraseña);

                if (inicioSesionExitoso) {
                    NatacionFrom menu = new NatacionFrom(natacionServicio); // Pasar la instancia de NatacionServicio
                    menu.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(panelPrincipal, "Inicio de sesión fallido. Usuario o contraseña incorrectos.");
                }
            }
        });

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth()) / 2;
        int y = (tamanioPantalla.height - getHeight()) / 2;
        setLocation(x, y);


    }

    private void cargarImagen(JLabel label, String nombreImagen) {
        URL url = getClass().getClassLoader().getResource("img/" + nombreImagen);
        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            label.setIcon(icono);
        } else {
            System.err.println("No se pudo cargar la imagen: " + nombreImagen);
        }
    }

    private JPanel panelPrincipal;
    private JPanel panelIzquierda;
    private JPanel panelDerecha;
    private JTextField tFNombre2;
    private JPasswordField password1;
    private JButton iniciarSesíonButton;
    private JLabel labelImagen1;
    private JLabel labelImagen2;
    private JLabel labelTitulo;
    private JLabel labelImagen3;
    private JLabel labelImagen4;
    private JButton registrarteButton;
    private JPanel panelIsquierdaRegistro;
    private JTextField tFNombre;
    private JTextField tFApellido;
    private JPasswordField password2;
    private JLabel tituloRegistro;
    private JLabel tituloUsuario;
    private JButton confirmarButton;
}
