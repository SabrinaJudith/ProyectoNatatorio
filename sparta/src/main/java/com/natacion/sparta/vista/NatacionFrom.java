package com.natacion.sparta.vista;

import com.natacion.sparta.modelo.Clases;
import com.natacion.sparta.modelo.DiaClase;
import com.natacion.sparta.modelo.Profesores;
import com.natacion.sparta.modelo.Socios;
import com.natacion.sparta.servicio.NatacionServicio;
import com.natacion.sparta.vista.tabla.TableGradientCell;
import com.toedter.calendar.JCalendar;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class NatacionFrom extends JFrame {
    private NatacionServicio natacionServicio;
    private JPanel ventanaPrincipal;
    private JTabbedPane tPanelesMenu;
    private JPanel PSocios;
    private JLabel jLtituloSocios;
    private JTextField tFbuscar;
    private JTextField tFNombreSocio;
    private JTextField tFApellidoSocio;
    private JTextField tFDniSocio;
    private JTextField tFTeleSocio;
    private JTextField tFTeleEmerSocio;
    private JTextField tFAlergiasSocio;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JButton modificarButton;
    private JTable TablaSocios;
    private JButton sociosButton;
    private JButton profesoresButton;
    private JButton clasesButton;
    private JButton facturacionButton;
    private JButton cerrarSesiónButton;
    private JPanel PanelMenu;
    private JPanel PProfesores;
    private JComboBox jCBclasesAsociar1;
    private JLabel CantidadSocios;
    private JButton limpiarButton;
    private JButton buscarButton;
    private JTextField tFNombreProfe;
    private JTextField tFApellidoProfe;
    private JTextField tFTelefonoProfe;
    private JTextField tFDireccionProfe;
    private JButton limpiarButtonProfe;
    private JButton eliminarButtonProfe;
    private JButton modificarButtonProfe;
    private JButton agregarButtonProfe;
    private JButton buscarButtonProfe;
    private JTextField tFbuscarProfe;
    private JTable TablaProfe;
    private JLabel Cantidadprofe;
    private JPanel PClases;
    private JTextField tFNombreClase;
    private JTextField tFDiasClase;
    private JTextField tFHorariosInicioClase;
    private JPanel jPCalendario;
    private JButton agregarHorarioButton;
    private JButton jBAgregarClase;
    private JTable TablaClase;
    private JLabel CantidadClase;
    private JTextField tFHorariosFinClase;
    private JButton jBModificarClase;
    private JButton jBEliminarClase;
    private JCheckBox lunesCheckBox;
    private JCheckBox martesCheckBox;
    private JCheckBox miércolesCheckBox;
    private JCheckBox juevesCheckBox;
    private JCheckBox viernesCheckBox;
    private JCheckBox sábadosCheckBox;
    private JComboBox cBHorarioInicio;
    private JComboBox cBHorarioFin;
    private JButton asociarAClaseButton;
    private JComboBox jCBclasesAsociar;
    private JButton asociarAClaseButton1;
    private JPanel contentPane;
    private JDesktopPane jDesktopPane;


    @Autowired
    public NatacionFrom(NatacionServicio natacionServicio){
        this.natacionServicio = natacionServicio;
        iniciarForma();
        cargarClasesEnComboBox();
        List<Clases> clasesFiltrados = null;
//SOCIOS
        sociosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tPanelesMenu.setSelectedComponent(PSocios);

            }
        });
        // Agregar ListSelectionListener a la tabla de socios
        TablaSocios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Obtener la fila seleccionada
                    int selectedRow = TablaSocios.getSelectedRow();
                    if (selectedRow != -1) { // Si hay una fila seleccionada

                        // Obtener los datos del socio seleccionado
                        Long idSocio = (Long) TablaSocios.getValueAt(selectedRow, 0);
                        String nombre = (String) TablaSocios.getValueAt(selectedRow, 1);
                        String apellido = (String) TablaSocios.getValueAt(selectedRow, 2);
                        String dni = (String) TablaSocios.getValueAt(selectedRow, 3);
                        String telefono = (String) TablaSocios.getValueAt(selectedRow, 4);
                        String telefonoEmergencia = (String) TablaSocios.getValueAt(selectedRow, 5);
                        String alergias = (String) TablaSocios.getValueAt(selectedRow, 6);

                        // Mostrar los datos en los campos correspondientes
                        tFNombreSocio.setText(nombre);
                        tFApellidoSocio.setText(apellido);
                        tFDniSocio.setText(dni);
                        tFTeleSocio.setText(telefono);
                        tFTeleEmerSocio.setText(telefonoEmergencia);
                        tFAlergiasSocio.setText(alergias);

                        // Y así sucesivamente con los demás campos
                    }
                }
            }
        });
        // ActionListener para el botón "Modificar"
        List<Socios> sociosFiltrados = null;
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el ID del socio seleccionado en la tabla
                int selectedRow = TablaSocios.getSelectedRow();
                if (selectedRow != -1) {
                    Long idSocio = (Long) TablaSocios.getValueAt(selectedRow, 0);

                    // Obtener los nuevos valores de los campos de texto
                    String nuevoNombre = tFNombreSocio.getText();
                    String nuevoApellido = tFApellidoSocio.getText();
                    String nuevoDni = tFDniSocio.getText();
                    String nuevoTelefono = tFTeleSocio.getText();
                    String nuevoTelefonoEmergencia = tFTeleEmerSocio.getText();
                    String nuevasAlergias = tFAlergiasSocio.getText();

                    // Crear un objeto Socios con los nuevos valores
                    Socios nuevoSocio = new Socios();
                    nuevoSocio.setIdSocio(idSocio);
                    nuevoSocio.setNombre(nuevoNombre);
                    nuevoSocio.setApellido(nuevoApellido);
                    nuevoSocio.setDni(nuevoDni);
                    nuevoSocio.setTelefono(nuevoTelefono);
                    nuevoSocio.setTelefonoEmergencia(nuevoTelefonoEmergencia);
                    nuevoSocio.setAlergias(nuevasAlergias);

                    // Llamar al método modificarSocio del servicio para actualizar el socio
                    Socios socioModificado = natacionServicio.modificarSocio(idSocio, nuevoSocio);
                    if (socioModificado != null) {
                        // Actualizar la tabla de socios
                        mostrarTablaSocios(sociosFiltrados);
                        // Limpiar los campos de texto después de la modificación
                        limpiarCamposTexto();
                        // Mostrar un mensaje de éxito
                        JOptionPane.showMessageDialog(NatacionFrom.this, "Socio modificado exitosamente.");
                    } else {
                        // Mostrar un mensaje de error si no se puede modificar el socio
                        JOptionPane.showMessageDialog(NatacionFrom.this, "No se pudo modificar el socio.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Mostrar un mensaje de error si no se ha seleccionado ningún socio
                    JOptionPane.showMessageDialog(NatacionFrom.this, "Por favor, seleccione un socio de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // ActionListener para el botón "Limpiar"
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar los campos de texto
                limpiarCamposTexto();
                // Y así sucesivamente con los demás campos
            }
        });
        // ActionListener para el botón "Agregar"
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto para el nuevo socio
                String nombre = tFNombreSocio.getText();
                String apellido = tFApellidoSocio.getText();
                String dni = tFDniSocio.getText();
                String telefono = tFTeleSocio.getText();
                String telefonoEmergencia = tFTeleEmerSocio.getText();
                String alergias = tFAlergiasSocio.getText();

                // Crear un nuevo objeto Socios con los datos ingresados
                Socios nuevoSocio = new Socios();
                nuevoSocio.setNombre(nombre);
                nuevoSocio.setApellido(apellido);
                nuevoSocio.setDni(dni);
                nuevoSocio.setTelefono(telefono);
                nuevoSocio.setTelefonoEmergencia(telefonoEmergencia);
                nuevoSocio.setAlergias(alergias);

                // Llamar al método agregarSocio del servicio para agregar el nuevo socio
                Socios socioAgregado = natacionServicio.guardarSocio(nuevoSocio);
                if (socioAgregado != null) {
                    // Actualizar la tabla de socios
                    mostrarTablaSocios(sociosFiltrados);
                    // Limpiar los campos de texto después de agregar el socio
                    limpiarCamposTexto();
                    // Mostrar un mensaje de éxito
                    JOptionPane.showMessageDialog(NatacionFrom.this, "Socio agregado exitosamente.");
                } else {
                    // Mostrar un mensaje de error si no se puede agregar el socio
                    JOptionPane.showMessageDialog(NatacionFrom.this, "No se pudo agregar el socio.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // ActionListener para el botón "Eliminar"
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si hay un socio seleccionado en la tabla
                int selectedRow = TablaSocios.getSelectedRow();
                if (selectedRow != -1) { // Si hay un socio seleccionado
                    // Obtener los datos del socio seleccionado
                    Long idSocio = (Long) TablaSocios.getValueAt(selectedRow, 0);
                    String nombreSocio = (String) TablaSocios.getValueAt(selectedRow, 1);

                    // Mostrar un cuadro de diálogo de confirmación
                    int confirmacion = JOptionPane.showConfirmDialog(NatacionFrom.this,
                            "¿Está seguro de que desea eliminar al socio '" + nombreSocio + "'?",
                            "Confirmar Eliminación",
                            JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) { // Si el usuario confirma la eliminación
                        // Llamar al método eliminarSocio del servicio para eliminar el socio
                        boolean socioEliminado = natacionServicio.eliminarSocio(idSocio);
                        if (socioEliminado) {
                            // Actualizar la tabla de socios
                            mostrarTablaSocios(sociosFiltrados);
                            // Mostrar un mensaje de éxito
                            JOptionPane.showMessageDialog(NatacionFrom.this, "Socio '" + nombreSocio + "' eliminado exitosamente.");
                        } else {
                            // Mostrar un mensaje de error si no se puede eliminar el socio
                            JOptionPane.showMessageDialog(NatacionFrom.this, "No se pudo eliminar el socio.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    // Mostrar un mensaje de error si no se ha seleccionado ningún socio
                    JOptionPane.showMessageDialog(NatacionFrom.this, "Por favor, seleccione un socio de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // En el ActionListener para el botón de búsqueda
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el término de búsqueda ingresado por el usuario
                String terminoBusqueda = tFbuscar.getText();
                // Filtrar los socios según el término de búsqueda
                Socios filtro = new Socios();
                filtro.setDni(terminoBusqueda); // Buscar por DNI
                List<Socios> sociosFiltrados = natacionServicio.buscarSocios(filtro);
                // Mostrar los socios filtrados en la tabla
                mostrarTablaSocios(sociosFiltrados);

                // Seleccionar automáticamente la fila del socio buscado si se encuentra
                if (!sociosFiltrados.isEmpty()) {
                    // Obtener el ID del socio buscado
                    Long idSocioBuscado = sociosFiltrados.get(0).getIdSocio(); // Suponiendo que solo tomas el primer socio de la lista
                    // Buscar el índice de la fila correspondiente al socio buscado
                    for (int i = 0; i < TablaSocios.getRowCount(); i++) {
                        Long idSocioTabla = (Long) TablaSocios.getValueAt(i, 0);
                        if (idSocioTabla.equals(idSocioBuscado)) {
                            // Seleccionar la fila correspondiente al socio buscado
                            TablaSocios.setRowSelectionInterval(i, i);
                            // Hacer scroll para asegurarse de que la fila seleccionada sea visible
                            TablaSocios.scrollRectToVisible(TablaSocios.getCellRect(i, 0, true));
                            break;
                        }
                    }
                }
            }
        });
        //JTable.setDefaultRenderer(Object.class, new TableGradientCell());

        asociarAClaseButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = TablaSocios.getSelectedRow();
                if (selectedRow != -1) { // Si hay una fila seleccionada
                    Long idSocio = (Long) TablaSocios.getValueAt(selectedRow, 0);
                    String nombreSocio = (String) TablaSocios.getValueAt(selectedRow, 1);
                    String claseSeleccionada = (String) jCBclasesAsociar1.getSelectedItem();

                    // Obtener el ID de la clase seleccionada
                    Long idClase = natacionServicio.obtenerIdClasePorNombre(claseSeleccionada);

                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea asociar al socio " + nombreSocio + " a la clase " + claseSeleccionada + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.YES_OPTION) {
                        // Asociar el socio a la clase
                        try {
                            natacionServicio.asociarSocioAClase(idSocio, idClase);
                            // Actualizar la tabla de socios
                            mostrarTablaSocios(natacionServicio.listarSocios());
                        } catch (RuntimeException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un socio de la tabla.");
                }
            }
        });




        mostrarTablaSocios(sociosFiltrados);
//PROFESORES
        profesoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tPanelesMenu.setSelectedComponent(PProfesores);

            }
        });
        TablaProfe.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Obtener la fila seleccionada
                    int selectedRow = TablaProfe.getSelectedRow();
                    if (selectedRow != -1) { // Si hay una fila seleccionada
                        // Obtener los datos del socio seleccionado
                        Long idProfesor = (Long) TablaProfe.getValueAt(selectedRow, 0);
                        String nombre = (String) TablaProfe.getValueAt(selectedRow, 1);
                        String apellido = (String) TablaProfe.getValueAt(selectedRow, 2);
                        String telefono = (String) TablaProfe.getValueAt(selectedRow, 3);
                        String Direccion = (String) TablaProfe.getValueAt(selectedRow, 4);
                        // Mostrar los datos en los campos correspondientes
                        tFNombreProfe.setText(nombre);
                        tFApellidoProfe.setText(apellido);
                        tFTelefonoProfe.setText(telefono);
                        tFDireccionProfe.setText(Direccion);

                        // Y así sucesivamente con los demás campos
                    }
                }
            }
        });
        List<Profesores> ProfeFiltrados = null;
        modificarButtonProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el ID del socio seleccionado en la tabla
                int selectedRow = TablaProfe.getSelectedRow();
                if (selectedRow != -1) {
                    Long idProfe = (Long) TablaProfe.getValueAt(selectedRow, 0);
                    // Obtener los nuevos valores de los campos de texto
                    String nuevoNombre = tFNombreProfe.getText();
                    String nuevoApellido = tFApellidoProfe.getText();
                    String nuevoTelefono = tFTelefonoProfe.getText();
                    String nuevoDireccion = tFDireccionProfe.getText();
                    // Crear un objeto Socios con los nuevos valores
                    Profesores nuevoProfe = new Profesores();
                    nuevoProfe.setIdProfesor(idProfe);
                    nuevoProfe.setNombre(nuevoNombre);
                    nuevoProfe.setApellido(nuevoApellido);
                    nuevoProfe.setTelefono(nuevoTelefono);
                    nuevoProfe.setDireccion(nuevoDireccion);


                    // Llamar al método modificarSocio del servicio para actualizar el socio
                    Profesores profeModificado = natacionServicio.modificarProfe(idProfe, nuevoProfe);
                    if (profeModificado != null) {
                        // Actualizar la tabla de socios
                        mostrarTablaProfe(ProfeFiltrados);
                        // Limpiar los campos de texto después de la modificación
                        limpiarCamposTextoProfe();
                        // Mostrar un mensaje de éxito
                        JOptionPane.showMessageDialog(NatacionFrom.this, "Socio modificado exitosamente.");
                    } else {
                        // Mostrar un mensaje de error si no se puede modificar el socio
                        JOptionPane.showMessageDialog(NatacionFrom.this, "No se pudo modificar el socio.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Mostrar un mensaje de error si no se ha seleccionado ningún socio
                    JOptionPane.showMessageDialog(NatacionFrom.this, "Por favor, seleccione un socio de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        limpiarButtonProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar los campos de texto
                limpiarCamposTextoProfe();
                // Y así sucesivamente con los demás campos
            }
        });
        agregarButtonProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto para el nuevo profe

                String nombre = tFNombreProfe.getText();
                String apellido = tFApellidoProfe.getText();
                String telefono = tFTelefonoProfe.getText();
                String direccion = tFDireccionProfe.getText();

                Profesores nuevoProfe = new Profesores();
                nuevoProfe.setNombre(nombre);
                nuevoProfe.setApellido(apellido);
                nuevoProfe.setTelefono(telefono);
                nuevoProfe.setDireccion(direccion);

                // Llamar al método agregarSocio del servicio para agregar el nuevo socio
                Profesores profeAgregado = natacionServicio.guardarProfesor(nuevoProfe);
                if (profeAgregado != null) {
                    // Actualizar la tabla de socios
                    mostrarTablaProfe(ProfeFiltrados);
                    // Limpiar los campos de texto después de agregar el socio
                    limpiarCamposTextoProfe();
                    // Mostrar un mensaje de éxito
                    JOptionPane.showMessageDialog(NatacionFrom.this, "Profesor agregado exitosamente.");
                } else {
                    // Mostrar un mensaje de error si no se puede agregar el socio
                    JOptionPane.showMessageDialog(NatacionFrom.this, "No se pudo agregar el Profesor.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        eliminarButtonProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si hay un profesor seleccionado en la tabla
                int selectedRow = TablaProfe.getSelectedRow();
                if (selectedRow != -1) { // Si hay un profesor seleccionado
                    // Obtener los datos del profesor seleccionado
                    Long idProfe = (Long) TablaProfe.getValueAt(selectedRow, 0);
                    String nombreProfe = (String) TablaProfe.getValueAt(selectedRow, 1);

                    // Mostrar un cuadro de diálogo de confirmación
                    int confirmacion = JOptionPane.showConfirmDialog(NatacionFrom.this,
                            "¿Está seguro de que desea eliminar al profesor '" + nombreProfe + "'?",
                            "Confirmar Eliminación",
                            JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) { // Si el usuario confirma la eliminación
                        // Llamar al método eliminarSocio del servicio para eliminar el profesor
                        boolean profeEliminado = natacionServicio.eliminarProfesor(idProfe);
                        if (profeEliminado) {
                            // Actualizar la tabla de profesor
                            mostrarTablaProfe(ProfeFiltrados);
                            // Mostrar un mensaje de éxito
                            JOptionPane.showMessageDialog(NatacionFrom.this, "Profesor '" + nombreProfe + "' eliminado exitosamente.");
                        } else {
                            // Mostrar un mensaje de error si no se puede eliminar el profesor
                            JOptionPane.showMessageDialog(NatacionFrom.this, "No se pudo eliminar el Profesor.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    // Mostrar un mensaje de error si no se ha seleccionado ningún profesor
                    JOptionPane.showMessageDialog(NatacionFrom.this, "Por favor, seleccione un profesor de la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buscarButtonProfe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el término de búsqueda ingresado por el usuario
                String terminoBusqueda = tFbuscarProfe.getText();
                // Filtrar los socios según el término de búsqueda
                Profesores filtro = new Profesores();
                filtro.setNombre(terminoBusqueda); // Buscar por DNI
                List<Profesores> profeFiltrados = natacionServicio.buscarProfe(filtro);
                // Mostrar los socios filtrados en la tabla
                mostrarTablaProfe(ProfeFiltrados);

                // Seleccionar automáticamente la fila del socio buscado si se encuentra
                if (!ProfeFiltrados.isEmpty()) {
                    // Obtener el ID del socio buscado
                    Long idProfeBuscado = ProfeFiltrados.get(0).getIdProfesor(); // Suponiendo que solo tomas el primer socio de la lista
                    // Buscar el índice de la fila correspondiente al socio buscado
                    for (int i = 0; i < TablaProfe.getRowCount(); i++) {
                        Long idProfeTabla = (Long) TablaProfe.getValueAt(i, 0);
                        if (idProfeTabla.equals(idProfeBuscado)) {
                            // Seleccionar la fila correspondiente al socio buscado
                            TablaProfe.setRowSelectionInterval(i, i);
                            // Hacer scroll para asegurarse de que la fila seleccionada sea visible
                            TablaProfe.scrollRectToVisible(TablaProfe.getCellRect(i, 0, true));
                            break;
                        }
                    }
                }
            }
        });

        asociarAClaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = TablaProfe.getSelectedRow();
                if (selectedRow != -1) {
                    Long idProfesor = (Long) TablaProfe.getValueAt(selectedRow, 0);
                    String nombreProfesor = (String) TablaProfe.getValueAt(selectedRow, 1);
                    String nombreClaseSeleccionada = (String) jCBclasesAsociar.getSelectedItem();

                    // Mostrar un cuadro de diálogo de confirmación
                    int opcion = JOptionPane.showConfirmDialog(
                            NatacionFrom.this,
                            "¿Estás seguro de que deseas asociar al profesor " + nombreProfesor + " a la clase " + nombreClaseSeleccionada + "?",
                            "Confirmar Asociación",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (opcion == JOptionPane.YES_OPTION) {
                        try {
                            natacionServicio.asociarClaseConProfesor(nombreClaseSeleccionada, idProfesor);
                            JOptionPane.showMessageDialog(NatacionFrom.this, "Clase asociada con éxito.");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(NatacionFrom.this, "Error al asociar la clase: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(NatacionFrom.this, "Por favor, selecciona un profesor.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        mostrarTablaProfe(ProfeFiltrados);

//CLASES
        clasesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tPanelesMenu.setSelectedComponent(PClases);

                // Agregar ActionListener a cada JCheckBox para manejar los cambios en tFDiasClase
                lunesCheckBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actualizarDiasClasecb();
                        actualizarHorarioInicio();
                        actualizarHorarioFin();
                    }
                });

                martesCheckBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actualizarDiasClasecb();
                        actualizarHorarioInicio();
                        actualizarHorarioFin();
                    }
                });

                miércolesCheckBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actualizarDiasClasecb();
                        actualizarHorarioInicio();
                        actualizarHorarioFin();
                    }
                });

                juevesCheckBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actualizarDiasClasecb();
                        actualizarHorarioInicio();
                        actualizarHorarioFin();
                    }
                });

                viernesCheckBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actualizarDiasClasecb();
                        actualizarHorarioInicio();
                        actualizarHorarioFin();
                    }
                });

                sábadosCheckBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actualizarDiasClasecb();
                        actualizarHorarioInicio();
                        actualizarHorarioFin();
                    }
                });

                // Llamar al método para actualizar tFDiasClase
                actualizarDiasClasecb();

                // Define los horarios preestablecidos en un array
                String[] horarios = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"};


                // Agregar los horarios al JComboBox
                for (String horario : horarios) {
                    cBHorarioInicio.addItem(horario);
                    cBHorarioFin.addItem(horario);
                }
                // Agregar ActionListener al JComboBox para manejar los cambios en el horario
                cBHorarioInicio.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtiene el horario seleccionado del JComboBox
                        actualizarHorarioInicio();
                    }
                });
                cBHorarioFin.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actualizarHorarioFin();
                    }
                });
            }
        });

        TablaClase.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Obtener la fila seleccionada
                    int selectedRow = TablaClase.getSelectedRow();
                    if (selectedRow != -1) { // Si hay una fila seleccionada
                        // Obtener los datos de la clase seleccionada
                        Long idClase = (Long) TablaClase.getValueAt(selectedRow, 0);
                        String nombre = (String) TablaClase.getValueAt(selectedRow, 1);
                        String dia = (String) TablaClase.getValueAt(selectedRow, 2);
                        String horarioInicio = (String) TablaClase.getValueAt(selectedRow, 3);
                        String horarioFin = (String) TablaClase.getValueAt(selectedRow, 4);

                        // Mostrar los datos en los campos correspondientes
                        tFNombreClase.setText(nombre);
                        tFDiasClase.setText(dia);
                        tFHorariosInicioClase.setText(horarioInicio);
                        tFHorariosFinClase.setText(horarioFin);
                        lunesCheckBox.setSelected(false);
                        martesCheckBox.setSelected(false);
                        miércolesCheckBox.setSelected(false);
                        juevesCheckBox.setSelected(false);
                        viernesCheckBox.setSelected(false);
                        sábadosCheckBox.setSelected(false);
                        // Seleccionar automáticamente el JCheckBox correspondiente al día de la semana
                        switch (dia.toLowerCase()) {
                            case "lunes":
                                lunesCheckBox.setSelected(true);
                                break;
                            case "martes":
                                martesCheckBox.setSelected(true);
                                break;
                            case "miércoles":
                                miércolesCheckBox.setSelected(true);
                                break;
                            case "jueves":
                                juevesCheckBox.setSelected(true);
                                break;
                            case "viernes":
                                viernesCheckBox.setSelected(true);
                                break;
                            case "sábado":
                                sábadosCheckBox.setSelected(true);
                                break;
                            default:
                                // Si el día no coincide con ninguno de los días conocidos, no seleccionar ningún JCheckBox
                                lunesCheckBox.setSelected(false);
                                martesCheckBox.setSelected(false);
                                miércolesCheckBox.setSelected(false);
                                juevesCheckBox.setSelected(false);
                                viernesCheckBox.setSelected(false);
                                sábadosCheckBox.setSelected(false);
                                break;
                        }


                    }
                }
            }

        });

        agregarHorarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = TablaClase.getSelectedRow();
                if (selectedRow != -1) {
                    Long idClase = (Long) TablaClase.getValueAt(selectedRow, 0);
                    String nombre = tFNombreClase.getText();
                    String dia = tFDiasClase.getText();
                    String horarioInicio = tFHorariosInicioClase.getText();
                    String horarioFin = tFHorariosFinClase.getText();

                    int opcion = JOptionPane.showConfirmDialog(NatacionFrom.this,
                            "¿Estás seguro de que deseas modificar esta clase?",
                            "Confirmar Modificación", JOptionPane.YES_NO_OPTION);

                    if (opcion == JOptionPane.YES_OPTION) {
                        Clases claseActualizada = new Clases();
                        claseActualizada.setIdClase(idClase);
                        claseActualizada.setNombre(nombre);

                        String[] diasArray = dia.split(",");
                        String[] horariosInicioArray = horarioInicio.split(",");
                        String[] horariosFinArray = horarioFin.split(",");

                        List<DiaClase> diasClaseList = new ArrayList<>();
                        for (int i = 0; i < diasArray.length; i++) {
                            DiaClase diaClase = new DiaClase();
                            diaClase.setDia(diasArray[i].trim());
                            diaClase.setHorarioInicio(horariosInicioArray[i].trim());
                            diaClase.setHorarioFin(horariosFinArray[i].trim());
                            diaClase.setClase(claseActualizada);
                            diasClaseList.add(diaClase);
                        }
                        claseActualizada.setDiasClase(diasClaseList);

                        try {
                            Clases claseModificada = natacionServicio.actualizarClase(claseActualizada);

                            StringBuilder mensaje = new StringBuilder("Clase modificada con éxito:\n");
                            mensaje.append("Nombre: ").append(claseModificada.getNombre()).append("\n");
                            for (DiaClase dc : claseModificada.getDiasClase()) {
                                mensaje.append("Día: ").append(dc.getDia()).append(", Horario de Inicio: ")
                                        .append(dc.getHorarioInicio()).append(", Horario de Fin: ").append(dc.getHorarioFin()).append("\n");
                            }
                            JOptionPane.showMessageDialog(NatacionFrom.this, mensaje.toString(), "Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(NatacionFrom.this, "Error al modificar la clase: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(NatacionFrom.this, "Por favor, selecciona una clase para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jBModificarClase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = TablaClase.getSelectedRow();
                if (selectedRow != -1) {
                    Long idClase = (Long) TablaClase.getValueAt(selectedRow, 0);
                    Long idDiaClase = (Long) TablaClase.getValueAt(selectedRow, 1); // Asegúrate de tener el ID del día de clase
                    String nombre = tFNombreClase.getText();
                    String dia = tFDiasClase.getText();
                    String horarioInicio = tFHorariosInicioClase.getText();
                    String horarioFin = tFHorariosFinClase.getText();

                    int opcion = JOptionPane.showConfirmDialog(NatacionFrom.this,
                            "¿Estás seguro de que deseas modificar este horario?",
                            "Confirmar Modificación", JOptionPane.YES_NO_OPTION);

                    if (opcion == JOptionPane.YES_OPTION) {
                        Clases claseActualizada = new Clases();
                        claseActualizada.setIdClase(idClase);
                        claseActualizada.setNombre(nombre);

                        DiaClase diaClaseActualizado = new DiaClase();
                        diaClaseActualizado.setId(idDiaClase); // Establece el ID del día de clase
                        diaClaseActualizado.setDia(dia);
                        diaClaseActualizado.setHorarioInicio(horarioInicio);
                        diaClaseActualizado.setHorarioFin(horarioFin);
                        diaClaseActualizado.setClase(claseActualizada);

                        List<DiaClase> diasClaseList = new ArrayList<>();
                        diasClaseList.add(diaClaseActualizado);
                        claseActualizada.setDiasClase(diasClaseList);

                        try {
                            Clases claseModificada = natacionServicio.actualizarHorarioDiaClase(claseActualizada);

                            StringBuilder mensaje = new StringBuilder("Horario modificado con éxito:\n");
                            mensaje.append("Nombre: ").append(claseModificada.getNombre()).append("\n");
                            for (DiaClase dc : claseModificada.getDiasClase()) {
                                mensaje.append("Día: ").append(dc.getDia()).append(", Horario de Inicio: ")
                                        .append(dc.getHorarioInicio()).append(", Horario de Fin: ").append(dc.getHorarioFin()).append("\n");
                            }
                            JOptionPane.showMessageDialog(NatacionFrom.this, mensaje.toString(), "Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(NatacionFrom.this, "Error al modificar el horario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(NatacionFrom.this, "Por favor, selecciona un horario para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jBEliminarClase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = TablaClase.getSelectedRow();
                if (selectedRow != -1) {
                    Long idDiaClase = (Long) TablaClase.getValueAt(selectedRow, 5); // Asegúrate de tener el ID del día de clase

                    int opcion = JOptionPane.showConfirmDialog(NatacionFrom.this,
                            "¿Estás seguro de que deseas eliminar este día de clase?",
                            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

                    if (opcion == JOptionPane.YES_OPTION) {
                        try {
                            // Llamar al método en el servicio para eliminar el día de clase
                            natacionServicio.eliminarDiaClase(idDiaClase);

                            // Mostrar mensaje de éxito
                            JOptionPane.showMessageDialog(NatacionFrom.this, "Día de clase eliminado exitosamente.");

                            // Actualizar la tabla para reflejar los cambios
                            // Esto dependerá de cómo implementes la actualización de la tabla en tu aplicación
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(NatacionFrom.this, "Error al eliminar el día de clase: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(NatacionFrom.this, "Por favor, selecciona un día de clase para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jBAgregarClase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores ingresados en los campos de texto
                String nombreClase = tFNombreClase.getText();
                String diasClase = tFDiasClase.getText();
                String horariosInicio = tFHorariosInicioClase.getText();
                String horariosFin = tFHorariosFinClase.getText();

                // Dividir los días, horarios de inicio y fin ingresados en listas separadas
                String[] diasArray = diasClase.split(",");
                String[] horariosInicioArray = horariosInicio.split(",");
                String[] horariosFinArray = horariosFin.split(",");

                // Verificar que la cantidad de días, horarios de inicio y fin coincidan
                if (diasArray.length != horariosInicioArray.length || diasArray.length != horariosFinArray.length) {
                    JOptionPane.showMessageDialog(NatacionFrom.this, "La cantidad de días, horarios de inicio y fin no coincide.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método
                }

                // Crear una lista de DiaClase para almacenar los días y horarios
                List<DiaClase> diasClaseList = new ArrayList<>();
                for (int i = 0; i < diasArray.length; i++) {
                    // Crear el objeto DiaClase con el día y los horarios de inicio y fin
                    DiaClase diaClase = new DiaClase();
                    diaClase.setDia(diasArray[i]);
                    diaClase.setHorarioInicio(horariosInicioArray[i].trim());
                    diaClase.setHorarioFin(horariosFinArray[i].trim());
                    diasClaseList.add(diaClase);
                }

                // Crear un nuevo objeto Clases con los valores ingresados
                Clases nuevaClase = new Clases();
                nuevaClase.setNombre(nombreClase);
                nuevaClase.setDiasClase(diasClaseList);

                try {
                    // Guardar la nueva clase en la base de datos usando el servicio
                    natacionServicio.guardarClaseConDias(nuevaClase);
                    // Mostrar un mensaje de éxito
                    JOptionPane.showMessageDialog(NatacionFrom.this, "Clase agregada exitosamente.");
                    // Limpiar los campos de texto después de agregar la clase
                    limpiarCamposTextoClase();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(NatacionFrom.this, "Error al agregar la clase.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Crear y agregar el componente JCalendar a jPCalendario
        JCalendar calendar = new JCalendar();
        calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Obtener la fecha seleccionada
                Date fechaSeleccionada = calendar.getDate();
                // Obtener el día de la semana en formato de texto
                String diaDeLaSemana = obtenerDiaDeLaSemana(fechaSeleccionada);
                System.out.println("Día de la semana obtenido: " + diaDeLaSemana); // Imprimir el día obtenido

                // Obtener todas las clases
                List<Clases> todasLasClases = natacionServicio.obtenerClasesConDiasClase();

                // Filtrar las clases por el día de la semana
                List<Clases> clasesFiltradas = filtrarClasesPorDia(todasLasClases, diaDeLaSemana);

                // Mostrar las clases filtradas en la tabla
                mostrarTablaClase(clasesFiltradas, diaDeLaSemana);
            }
        });
        jPCalendario.setLayout(new BorderLayout());
        jPCalendario.add(calendar, BorderLayout.CENTER); // Agregar el componente JCalendar al panel en el centro

    }

    private void limpiarCamposTexto() {
        tFNombreSocio.setText("");
        tFApellidoSocio.setText("");
        tFDniSocio.setText("");
        tFTeleSocio.setText("");
        tFTeleEmerSocio.setText("");
        tFAlergiasSocio.setText("");
    }

    private void limpiarCamposTextoClase() {
        tFNombreClase.setText("");
        tFDiasClase.setText("");
        tFHorariosInicioClase.setText("");
        tFHorariosFinClase.setText("");
    }
    private void limpiarCamposTextoProfe() {
        tFNombreProfe.setText("");
        tFApellidoProfe.setText("");
        tFTelefonoProfe.setText("");
        tFDireccionProfe.setText("");

    }
    private void iniciarForma() {
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Inicializar los componentes del JFrame
        // Configurar el contenido del JFrame
        setContentPane(ventanaPrincipal);
        setSize(1500, 900);
        setTitle("Sparta Club de Natación");
        setLocationRelativeTo(null);
        // Hacer visible el JFrame
        setVisible(true);
    }

    // Método para mostrar los socios en una tabla
    private void mostrarTablaSocios(List<Socios> sociosFiltrados) {
        List<Socios> socios = natacionServicio.listarSocios();

        // Obtener la cantidad de socios
        int cantidadSocios = socios.size();

        // Establecer la cantidad de socios en el JLabel
        CantidadSocios.setText("Cantidad de Socios: " + cantidadSocios);

        // Crear el modelo de tabla y agregar datos
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Dni");
        model.addColumn("Teléfono");
        model.addColumn("Teléfono Emergencia");
        model.addColumn("Alergias");
        model.addColumn("Clases"); // Nueva columna para mostrar las clases

        for (Socios socio : socios) {
            // Obtener los nombres de las clases asociadas al socio
            List<Clases> clases = socio.getClases();
            String nombresClases = clases.stream()
                    .map(Clases::getNombre)
                    .collect(Collectors.joining(", "));

            Object[] fila = new Object[]{
                    socio.getIdSocio(),
                    socio.getNombre(),
                    socio.getApellido(),
                    socio.getDni(),
                    socio.getTelefono(),
                    socio.getTelefonoEmergencia(),
                    socio.getAlergias(),
                    nombresClases // Agregar los nombres de las clases en la nueva columna
            };
            model.addRow(fila);
        }

        TablaSocios.setRowSelectionAllowed(true);
        TablaSocios.setModel(model);
        TablaSocios.setDefaultRenderer(Object.class, new TableGradientCell());
    }



    // Método para mostrar los socios en una tabla
    private void mostrarTablaProfe(List<Profesores> ProfeFiltrados) {
        List<Profesores> Profe = natacionServicio.listarProfesores();

        // Obtener la cantidad de profesores
        int cantidadProfes = Profe.size();

        // Establecer la cantidad de profesores en el JLabel
        Cantidadprofe.setText("Cantidad de Profesores: " + cantidadProfes);

        // Crear el modelo de tabla y agregar datos
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Teléfono");
        model.addColumn("Dirección");
        model.addColumn("Clases"); // Nueva columna para mostrar las clases

        for (Profesores profesor : Profe) {
            // Obtener los nombres de las clases asociadas al profesor
            List<Clases> clases = profesor.getClases();
            String nombresClases = clases.stream()
                    .map(Clases::getNombre)
                    .collect(Collectors.joining(", "));

            Object[] fila = new Object[]{
                    profesor.getIdProfesor(),
                    profesor.getNombre(),
                    profesor.getApellido(),
                    profesor.getTelefono(),
                    profesor.getDireccion(),
                    nombresClases // Agregar los nombres de las clases en la nueva columna
            };
            model.addRow(fila);
        }

        TablaProfe.setRowSelectionAllowed(true);
        TablaProfe.setModel(model);
        TablaProfe.setDefaultRenderer(Object.class, new TableGradientCell());
    }



    // Método para mostrar los socios en una tabla
    private void mostrarTablaClase(List<Clases> clases, String diaFiltrado) {
        if (clases == null) {
            clases = new ArrayList<>(); // Crear una lista vacía si es null
        }
        // Crear el modelo de tabla y agregar datos
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("IdClase");
        model.addColumn("Nombre");
        model.addColumn("Día");
        model.addColumn("Horario Inicio");
        model.addColumn("Horario Fin");
        model.addColumn("IdDia");
        for (Clases clase : clases) {
            // Obtener los días y horarios de la clase actual
            List<DiaClase> diasClase = clase.getDiasClase();
            for (DiaClase diaClase : diasClase) {
                if (diaClase.getDia().equalsIgnoreCase(diaFiltrado)) {
                    // Agregar una fila con los datos de la clase actual y el día filtrado
                    Object[] fila = new Object[]{
                            clase.getIdClase(),
                            clase.getNombre(),
                            diaClase.getDia(),
                            diaClase.getHorarioInicio(),
                            diaClase.getHorarioFin(),
                            diaClase.getId(),
                    };
                    model.addRow(fila);
                }
            }
        }

        TablaClase.setRowSelectionAllowed(true);
        TablaClase.setModel(model);
        TablaClase.setDefaultRenderer(Object.class, new TableGradientCell());
    }


    public String obtenerDiaDeLaSemana(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        String diaSemana = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("es", "ES"));
        System.out.println("Día de la semana obtenido: " + diaSemana); // Imprimir el día obtenido
        return diaSemana;
    }

    public List<Clases> filtrarClasesPorDia(List<Clases> clases, String dia) {
        return clases.stream()
                .filter(clase -> clase.getDiasClase().stream()
                        .anyMatch(diaClase -> diaClase.getDia().equalsIgnoreCase(dia)))
                .collect(Collectors.toList());
    }
    private void actualizarDiasClasecb() {
        StringBuilder diasSeleccionados = new StringBuilder();
        if (lunesCheckBox.isSelected()) {
            diasSeleccionados.append("lunes, ");
        }
        if (martesCheckBox.isSelected()) {
            diasSeleccionados.append("martes, ");
        }
        if (miércolesCheckBox.isSelected()) {
            diasSeleccionados.append("miércoles, ");
        }
        if (juevesCheckBox.isSelected()) {
            diasSeleccionados.append("jueves, ");
        }
        if (viernesCheckBox.isSelected()) {
            diasSeleccionados.append("viernes, ");
        }
        if (sábadosCheckBox.isSelected()) {
            diasSeleccionados.append("sábado, ");
        }

        // Eliminar la última coma y espacio si hay algún día seleccionado
        int length = diasSeleccionados.length();
        if (length > 0) {
            // Eliminar tanto la coma como el espacio después de la última coma
            diasSeleccionados.delete(length - 2, length);
        }

        // Establecer el texto en el JTextField tFDiasClase
        tFDiasClase.setText(diasSeleccionados.toString());
    }


    // Método para actualizar el horario de inicio en el JTextField tFHorarioInicioClase
    private void actualizarHorarioInicio() {
        // Lista para almacenar los horarios seleccionados
        List<String> horariosSeleccionados = new ArrayList<>();
        String horarioSeleccionado = (String) cBHorarioInicio.getSelectedItem();
        // Verificar cada JCheckBox para determinar los días seleccionados y agregar los horarios correspondientes
        if (lunesCheckBox.isSelected()) {
            horariosSeleccionados.add(horarioSeleccionado);
        }
        if (martesCheckBox.isSelected()) {
            horariosSeleccionados.add(horarioSeleccionado);
        }
        if (miércolesCheckBox.isSelected()) {
            horariosSeleccionados.add(horarioSeleccionado);
        }
        if (juevesCheckBox.isSelected()) {
            horariosSeleccionados.add(horarioSeleccionado);
        }
        if (viernesCheckBox.isSelected()) {
            horariosSeleccionados.add(horarioSeleccionado);
        }
        if (sábadosCheckBox.isSelected()) {
            horariosSeleccionados.add(horarioSeleccionado);
        }

        // Verificar si hay más de un día seleccionado
        if (horariosSeleccionados.size() > 1) {
            // Construir una cadena con los horarios separados por comas
            StringBuilder horariosConcatenados = new StringBuilder();
            for (String horario : horariosSeleccionados) {
                horariosConcatenados.append(horario).append(", ");
            }
            // Eliminar la última coma y espacio si hay más de un horario seleccionado
            horariosConcatenados.delete(horariosConcatenados.length() - 2, horariosConcatenados.length());

            // Establecer la cadena de horarios en el JTextField tFHorarioInicioClase
            tFHorariosInicioClase.setText(horariosConcatenados.toString());
        } else if (horariosSeleccionados.size() == 1) {
            // Si solo hay un horario seleccionado, establecerlo directamente en el JTextField
            tFHorariosInicioClase.setText(horariosSeleccionados.get(0));
        } else {
            // Si no hay días seleccionados, establecer un texto vacío en el JTextField
            tFHorariosInicioClase.setText("");
        }
    }
    private void actualizarHorarioFin() {
        // Lista para almacenar los horarios seleccionados
        List<String> horariosSeleccionados = new ArrayList<>();
        String horarioSeleccionado = (String) cBHorarioFin.getSelectedItem();
        // Verificar cada JCheckBox para determinar los días seleccionados y agregar los horarios correspondientes
        if (lunesCheckBox.isSelected()) {
            horariosSeleccionados.add(horarioSeleccionado);
        }
        if (martesCheckBox.isSelected()) {
            horariosSeleccionados.add(horarioSeleccionado);
        }
        if (miércolesCheckBox.isSelected()) {
            horariosSeleccionados.add(horarioSeleccionado);
        }
        if (juevesCheckBox.isSelected()) {
            horariosSeleccionados.add(horarioSeleccionado);
        }
        if (viernesCheckBox.isSelected()) {
            horariosSeleccionados.add(horarioSeleccionado);
        }
        if (sábadosCheckBox.isSelected()) {
            horariosSeleccionados.add(horarioSeleccionado);
        }

        // Verificar si hay más de un día seleccionado
        if (horariosSeleccionados.size() > 1) {
            // Construir una cadena con los horarios separados por comas
            StringBuilder horariosConcatenados = new StringBuilder();
            for (String horario : horariosSeleccionados) {
                horariosConcatenados.append(horario).append(", ");
            }
            // Eliminar la última coma y espacio si hay más de un horario seleccionado
            horariosConcatenados.delete(horariosConcatenados.length() - 2, horariosConcatenados.length());

            // Establecer la cadena de horarios en el JTextField tFHorarioInicioClase
            tFHorariosFinClase.setText(horariosConcatenados.toString());
        } else if (horariosSeleccionados.size() == 1) {
            // Si solo hay un horario seleccionado, establecerlo directamente en el JTextField
            tFHorariosFinClase.setText(horariosSeleccionados.get(0));
        } else {
            // Si no hay días seleccionados, establecer un texto vacío en el JTextField
            tFHorariosFinClase.setText("");
        }
    }

    private void cargarClasesEnComboBox() {
        try {
            List<Clases> clases = natacionServicio.listarClases();
            for (Clases clase : clases) {
                jCBclasesAsociar.addItem(clase.getNombre());
                jCBclasesAsociar1.addItem(clase.getNombre());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(NatacionFrom.this, "Error al cargar las clases: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




}
