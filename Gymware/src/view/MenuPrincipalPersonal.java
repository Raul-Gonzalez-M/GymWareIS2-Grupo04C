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
	    
	    model.addTableModelListener(e -> {
	        int row = e.getFirstRow();
	        int column = e.getColumn();
	        Object newValue = model.getValueAt(row, column);
	        boolean nuevo = false;
	        Cliente cliente = controller.getListaClientes().get(row);
	        switch (column) {
	            case 0:
		        	if(controller.existeDni(cliente.getDNI())) {
		                cliente.setDNI((String) newValue);
		                nuevo = true;
		        	}
	                break;
	            case 1:
	                cliente.setNombre((String) newValue);
	                break;
	            case 2:
	                cliente.setContrasena((String) newValue);
	                break;
	            case 3:
	            	cliente.setSaldo(Double.parseDouble((String) newValue));
	                break;
	            default:
	                break;
	                
	        }
	        if(nuevo) {
	        	controller.darBajaUsusario(cliente.getDNI());
	        	controller.registrarUsuario(cliente.getDNI(),cliente.getNombre(), cliente.getContrasena(),cliente.getFechaAlta(), cliente.getSaldo());
	        }
	        else {
		        controller.actualizarCliente(cliente);
		        JOptionPane.showMessageDialog(userPanel, "Los cambios se han guardado correctamente.");
	        }
	    });

	    
	    return userPanel;
	}

}
