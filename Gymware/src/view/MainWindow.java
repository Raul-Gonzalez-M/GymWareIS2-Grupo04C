package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private PersonalController personalController;
    private ClienteController clienteController;
    private JTabbedPane tabbedPane;

    public MainWindow(PersonalController personalController, ClienteController clienteController) {
        super("Gestión del Gimnasio");

        this.personalController = personalController;
        this.clienteController = clienteController;

        // Crear los componentes de la interfaz de usuario
        JPanel personalPanel = personalController.getPanel();
        JPanel clientePanel = clienteController.getPanel();

        // Crear el panel con las pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Personal", personalPanel);
        tabbedPane.addTab("Clientes", clientePanel);

        // Agregar el panel con las pestañas al JFrame
        this.add(tabbedPane);

        // Configurar el JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
