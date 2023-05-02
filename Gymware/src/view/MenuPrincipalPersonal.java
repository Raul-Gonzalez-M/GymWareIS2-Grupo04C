package view;
import javax.swing.*;

import controller.Controller;
import controller.GymController;
import model.Actividad;
import model.Cliente;
import model.Material;
import model.Personal;
import model.Usuario;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MenuPrincipalPersonal extends JPanel {

	private Controller controller;
	private JTabbedPane tabbedPane;
	private Usuario usuario;
	private JSpinner numMaterial;

	public MenuPrincipalPersonal(Controller controller, Usuario usuarioActual) {
	    this.controller = controller;
	    this.usuario = usuarioActual;
	    try {
			initComponents();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() throws SQLException {
	    tabbedPane = new JTabbedPane();
	  
	    tabbedPane.addTab("Usuarios", createUserPanel());
	    //tabbedPane.addTab("Actividades", createActivityPanel());
	    tabbedPane.addTab("Materiales", createMaterialesPanel());
	    

	    setLayout(new BorderLayout());
	    add(tabbedPane, BorderLayout.CENTER);

	    setSize(1200, 800);
	}
	
	private JPanel createMaterialesPanel() throws SQLException {
		JPanel materialesPanel = new JPanel(new BorderLayout());


		JTable materialesTable = new JTable();
		NonEditableTableModel model = new NonEditableTableModel(new Object[] {"Material", "Precio", "Unidades", "Actividad asociada"}, 0);
	    for (Material material : controller.getMaterialesDisponibles()) {
	        model.addRow(new Object[]{
	                material.getNombre(),
	                material.getPrecio(),
	                material.getCantidad_disponible(),
	                material.getActividad_asociada()
	        });
	    }
	    
	    materialesTable.setModel(model);
	    
	    numMaterial = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
	    numMaterial.setValue(1);

	    JButton updateButton = new JButton("Reponer");
	    updateButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	int selectedRow = materialesTable.getSelectedRow();
	            if (selectedRow == -1) {
	                JOptionPane.showMessageDialog(null, "Por favor, selecciona un material");
	                return;
	            } else {
		            int cantidad = (int)numMaterial.getValue();
		            model.setValueAt((double)model.getValueAt(selectedRow, 2) + cantidad, selectedRow, 2);
		            model.fireTableDataChanged();
		            controller.updateMaterial(cantidad, (String)model.getValueAt(selectedRow, 0));
	            }
	            
	            
	        }
	    });
	    
	    JButton eliminarButton = new JButton("Eliminar");
	    eliminarButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	int selectedRow = materialesTable.getSelectedRow();
	            if (selectedRow == -1) {
	                JOptionPane.showMessageDialog(null, "Por favor, selecciona un material");
	                return;
	            } else {
		            int cantidad = (int)numMaterial.getValue();
		            if(cantidad <= (double)model.getValueAt(selectedRow, 2)) {
		            	cantidad *= -1;
			            model.setValueAt((double)model.getValueAt(selectedRow, 2) + cantidad, selectedRow, 2);
			            model.fireTableDataChanged();
			            controller.updateMaterial(cantidad, (String)model.getValueAt(selectedRow, 0));
		            } else {
		            	JOptionPane.showMessageDialog(Utils.getWindow(MenuPrincipalPersonal.this), "Has seleccionado más material del disponible...");
		            }
	            }
	            
	            
	        }
	    });
	    
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    buttonPanel.add(updateButton);
	    buttonPanel.add(eliminarButton);
	    buttonPanel.add(numMaterial);
	    materialesPanel.add(new JScrollPane(materialesTable), BorderLayout.CENTER);
	    materialesPanel.add(buttonPanel, BorderLayout.SOUTH);

	    return materialesPanel;
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
