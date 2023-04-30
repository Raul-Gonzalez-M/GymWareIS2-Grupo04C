package view;
import javax.swing.*;

import controller.Controller;
import controller.GymController;
import model.Actividad;
import model.Cliente;
import model.Usuario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class MenuPrincipalPersonal extends JPanel {

	private Controller controller;
	private JTabbedPane tabbedPane;
	private Usuario usuario;

	public MenuPrincipalPersonal(Controller controller, Usuario usuarioActual) {
	    this.controller = controller;
	    this.usuario = usuarioActual;
	    initComponents();
	}

	private void initComponents() {
	    tabbedPane = new JTabbedPane();
	  
	    tabbedPane.addTab("Usuarios", createUserPanel());
	    //tabbedPane.addTab("Actividades", createActivityPanel());
	    //tabbedPane.addTab("Materiales", createMaterialPanel());
	    

	    setLayout(new BorderLayout());
	    add(tabbedPane, BorderLayout.CENTER);

	    setSize(1200, 800);
	}
	
	
	public JPanel createUserPanel() {
	    JPanel userPanel = new JPanel(new BorderLayout());

	    String[] columnNames = {"DNI", "Nombre", "Contraseña", "Saldo"};
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	    for (Cliente cliente : controller.getListaClientes()) {
	        model.addRow(new Object[]{
	        		cliente.getDNI(),
	        		cliente.getNombre(),
	        		cliente.getContrasena(),
	        		cliente.getSaldo(),
	        });
	    }
	    JTable userTable = new JTable(model);

	    userTable.setEnabled(true);
	    userTable.setFillsViewportHeight(true);
	    JScrollPane scrollPane = new JScrollPane(userTable);
	    userPanel.add(scrollPane, BorderLayout.CENTER);

	    JButton saveButton = new JButton("Guardar cambios");
	    saveButton.addActionListener(e -> {
	        for (int row = 0; row < model.getRowCount(); row++) {
	            int DNI = (int) model.getValueAt(row, 0);
	            String nombre = (String) model.getValueAt(row, 1);
	            String contrasenya = (String) model.getValueAt(row, 2);
	            double saldo = (int) model.getValueAt(row, 3);

	            //controller.actualizarCliente(id, nombre, apellido, edad, correoElectronico);
	        }
	        JOptionPane.showMessageDialog(userPanel, "Los cambios se han guardado correctamente.");
	    });
	    userPanel.add(saveButton, BorderLayout.SOUTH);

	    return userPanel;
	}

}
