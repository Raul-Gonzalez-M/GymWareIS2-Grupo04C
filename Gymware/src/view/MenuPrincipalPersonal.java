package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import model.Cliente;
import model.Material;
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
	    tabbedPane.addTab("Materiales", createMaterialesPanel());
	    tabbedPane.addTab("Actividades", createActivityPanel());

	    setLayout(new BorderLayout());
	    add(tabbedPane, BorderLayout.CENTER);

	    setSize(1200, 800);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private JPanel createMaterialesPanel() throws SQLException {
		JPanel materialesPanel = new JPanel(new BorderLayout());


		JTable materialesTable = new JTable();
		EditableTableModel model = new EditableTableModel(new Object[] {"Material", "Precio", "Unidades", "Actividad asociada"}, 0);

	    for (Material material : controller.getMaterialesDisponibles()) {
	        model.addRow(new Object[]{
	                material.getNombre(),
	                material.getPrecio(),
	                material.getCantidad_disponible(),
	                material.getActividad_asociada()
	        });
	    }
	    
		Object[] emptyRow = new Object[] {"", "", "", ""};
		model.addRow(emptyRow);
	    materialesTable.setModel(model);
	    numMaterial = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
	    numMaterial.setValue(1);

	    JButton reponerButton = new JButton("Reponer");
	    reponerButton.addActionListener(new ActionListener() {
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
	    
	    JButton eliminarButton = new JButton("Eliminar cantidad");
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
	    
	    JButton agregarButton = new JButton("Agregar Producto");
	    agregarButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	            int lastRow = materialesTable.getRowCount() - 1;

	            String nombre = (String) model.getValueAt(lastRow, 0);
	            double precio = Double.parseDouble(model.getValueAt(lastRow, 1).toString());
	            double unidades = Double.parseDouble(model.getValueAt(lastRow, 2).toString());
	            String act_asociada = (String) model.getValueAt(lastRow, 3);

	            model.addRow(new Object[] {null, null, null, null}); 
	            model.setValueAt(nombre, lastRow, 0); 
	            model.setValueAt(precio, lastRow, 1);
	            model.setValueAt(unidades, lastRow, 2);
	            model.setValueAt(act_asociada, lastRow, 3);
	            model.fireTableDataChanged();

	            controller.agregarMaterial(nombre,precio,(int) unidades, act_asociada);
	            JOptionPane.showMessageDialog(null, "Material agregado correctamente");
	        }
	    });
	    
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    buttonPanel.add(agregarButton);
	    buttonPanel.add(eliminarButton);
	    buttonPanel.add(reponerButton);
	    buttonPanel.add(numMaterial);
	    materialesPanel.add(new JScrollPane(materialesTable), BorderLayout.CENTER);
	    materialesPanel.add(buttonPanel, BorderLayout.SOUTH);

	    return materialesPanel;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	        String viejoDNI = null;
	        Object newValue = model.getValueAt(row, column);
	        boolean nuevo = false, ok = false;
	        Cliente cliente = controller.getListaClientes().get(row);
	        switch (column) {
		        case 0:
		            viejoDNI = cliente.getDNI();
		            if (!controller.existeDni((String) newValue) && !viejoDNI.equals((String) newValue)) {
		                cliente.setDNI((String) newValue);
		                nuevo = true;
		            }
		            else
			        	JOptionPane.showMessageDialog(userPanel, "Valor incorrecto de DNI, el DNI ya existe o es el mismo que tenia");
		            break;
		        case 1:
		            if (!newValue.equals(cliente.getNombre())) {
		                cliente.setNombre((String) newValue);
		                ok = true;
		            }
		            break;
		        case 2:
		            if (!newValue.equals(cliente.getContrasena())) {
		                cliente.setContrasena((String) newValue);
		                ok = true;
		            }
		            break;
		        case 3:
		            if (!newValue.equals(cliente.getSaldo())) {
		                cliente.setSaldo(Double.parseDouble((String) newValue));
		                ok = true;
		            }
		            break;
		        default:
		            break;
	        }

	        if(nuevo) {
	        	controller.darBajaUsusario(viejoDNI);
	        	controller.borrarUsuarioTodasActividades(viejoDNI);
	        	controller.registrarUsuario(cliente.getDNI(),cliente.getNombre(), cliente.getContrasena(),cliente.getFechaAlta(), cliente.getSaldo());
	        	JOptionPane.showMessageDialog(userPanel, "DNI Cambiado correctamente, se han borrado todas las actividades asociadas");
	        	nuevo = false;
	        }
	        else if (ok) {
		        controller.actualizarCliente(cliente);
	        	JOptionPane.showMessageDialog(userPanel, "Los cambios se han guardado correctamente.");
	        	ok = false;
	        }
	    });

	    return userPanel;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public JPanel createActivityPanel() {
		return null;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private class NonEditableTableModel extends DefaultTableModel {
	    public NonEditableTableModel(Object[] columnNames, int rowCount) {
	        super(columnNames, rowCount);
	    }

	    @Override
	    public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	}
	
	private class EditableTableModel extends NonEditableTableModel {
	    public EditableTableModel(Object[] columnNames, int rowCount) {
	        super(columnNames, rowCount);
	    }

	    @Override
	    public boolean isCellEditable(int row, int column) {
	        if (row == getRowCount() - 1) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	}

}
