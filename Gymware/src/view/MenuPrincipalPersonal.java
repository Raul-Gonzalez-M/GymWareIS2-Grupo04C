package view;
import javax.swing.*;

import controller.Controller;
import controller.GymController;
import model.Usuario;
import java.awt.*;

public class MenuPrincipalPersonal extends JPanel {

	private Controller controller;
	private JTabbedPane tabbedPane;
	private Usuario usuario;

	public MenuPrincipalPersonal(Controller controller, Usuario usuarioActual) {
	    this.controller = controller;
	    this.usuario = usuarioActual;
	    //initComponents();
	}

	private void initComponents() {
	    tabbedPane = new JTabbedPane();
	  
	    //tabbedPane.addTab("Usuarios", createUserPanel());
	    //tabbedPane.addTab("Actividades", createActivityPanel());
	    //tabbedPane.addTab("Materiales", createMaterialPanel());
	    

	    setSize(1200, 800);
	    getRootPane().add(tabbedPane, BorderLayout.CENTER);
	}
	
	
	/*public JPanel createUserPanel() {
	    JPanel panel = new JPanel(new BorderLayout());

	    // Tabla de usuarios
	    JTable table = new JTable();
	    DefaultTableModel model = new DefaultTableModel(new Object[]{"DNI", "Nombre", "Apellido", "Email", "Rol"}, 0);
	    for (Usuario usuario : gymController.obtenerUsuarios()) {
	        model.addRow(new Object[]{usuario.getDNI(), usuario.getNombre(), usuario.getApellido(),
	                                  usuario.getEmail(), usuario.getRol()});
	    }
	    table.setModel(model);

	    // Botones para modificar y eliminar usuarios
	    JPanel buttonsPanel = new JPanel(new FlowLayout());
	    JButton modifyButton = new JButton("Modificar Usuario");
	    modifyButton.addActionListener(e -> {
	        int row = table.getSelectedRow();
	        if (row != -1) {
	            String dni = (String) model.getValueAt(row, 0);
	            Usuario usuario = gymController.buscarUsuario(dni);
	            if (usuario != null) {
	                ModifyUserDialog modifyUserDialog = new ModifyUserDialog(this, "Modificar Usuario", true, usuario);
	                modifyUserDialog.setVisible(true);
	                if (modifyUserDialog.isModified()) {
	                    model.setValueAt(()usuario.getNombre(), row, 1);
	                    //model.setValueAt(usuario.getApellido(), row, 2);
	                    //model.setValueAt(usuario.getEmail(), row, 3);
	                    //model.setValueAt(usuario.getRol(), row, 4);
	                }
	            }
	        }
	    });

	    JButton deleteButton = new JButton("Eliminar Usuario");
	    deleteButton.addActionListener(e -> {
	        int row = table.getSelectedRow();
	        if (row != -1) {
	            String dni = (String) model.getValueAt(row, 0);
	            gymController.eliminarUsuario(dni);
	            model.removeRow(row);
	        }
	    });

	    buttonsPanel.add(modifyButton);
	    buttonsPanel.add(deleteButton);

	    panel.add(new JScrollPane(table), BorderLayout.CENTER);
	    panel.add(buttonsPanel, BorderLayout.SOUTH);

	    return panel;
	}*/
}
