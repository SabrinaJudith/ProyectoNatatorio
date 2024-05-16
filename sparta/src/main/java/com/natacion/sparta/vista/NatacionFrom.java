package com.natacion.sparta.vista;

import com.natacion.sparta.servicio.NatacionServicio;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NatacionFrom extends JFrame {
    private NatacionServicio natacionServicio;
    private JPanel ventanaPrincipal;
    private JTabbedPane tPSocios;
    private JPanel PSocios;
    private JLabel jLtituloSocios;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTable table1;
    private JButton sociosButton;
    private JButton profesoresButton;
    private JButton clasesButton;
    private JButton facturacionButton;
    private JButton cerrarSesiónButton;
    private JPanel PanelMenu;
    private JPanel PProfesores;
    private JPanel contentPane;
    private JDesktopPane jDesktopPane;

    @Autowired
    public NatacionFrom(NatacionServicio natacionServicio){
        this.natacionServicio = natacionServicio;
        iniciarForma();
        sociosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tPSocios.setSelectedComponent(PSocios);
            }
        });
    }

    private void iniciarForma() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar los componentes del JFrame


        // Configurar el contenido del JFrame
        setContentPane(ventanaPrincipal);

        setSize(1200, 700);
        setTitle("Sparta Club de Natación");
        setLocationRelativeTo(null);



        // Hacer visible el JFrame
        setVisible(true);
    }
}
