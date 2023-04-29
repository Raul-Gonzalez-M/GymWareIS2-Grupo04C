package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Actividad;
import model.Cliente;
import model.Encuesta;
import model.EncuestaDialog;
import model.Material;
import model.Usuario;
import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalCliente extends JPanel {

	private Controller controller;
	private JTabbedPane tabbedPane;
	private Usuario usuario;

	public MenuPrincipalCliente(Controller controller, Usuario usuarioActual) {
	    this.controller = controller;
	    this.usuario = usuarioActual;
	    
	    initComponents();
	}
	
	private void initComponents() {
	    tabbedPane = new JTabbedPane();

	    tabbedPane.addTab("Actividades Disponibles", createActividadesDisponibles());
	    tabbedPane.addTab("Actividades Inscritas", createActividadesInscritas());
	    //tabbedPane.addTab("Encuestas", createSurveyPanel());
	    //tabbedPane.addTab("Materiales", createMaterialsPanel());
	    //tabbedPane.addTab("Mi Perfil", createProfilePanel());
	    
	    setLayout(new BorderLayout());
	    add(tabbedPane, BorderLayout.CENTER);

	    setSize(1200, 800);
	}


	private JPanel createActividadesDisponibles() {
	    JPanel activitiesPanel = new JPanel(new BorderLayout());

	    // Crear tabla con las actividades disponibles
	    JTable activitiesTable = new JTable();
	    NonEditableTableModel model = new NonEditableTableModel(new Object[]{"Actividad", "Horario", "Instructor", "Plazas Disponibles"}, 0);
	    for (Actividad actividad : controller.getActNoInscrito(usuario.getDNI())) {
	        model.addRow(new Object[]{
	                actividad.getNombre(),
	                actividad.getHorario(),
	                actividad.getDNIProfesor(),
	                actividad.getPlazasDisponibles()
	        });
	    }
	    activitiesTable.setModel(model);

	    JButton inscribirseButton = new JButton("Inscribirse");
	    inscribirseButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            int selectedRow = activitiesTable.getSelectedRow();
	            if (selectedRow == -1) {
	                JOptionPane.showMessageDialog(null, "Por favor, selecciona una actividad");
	                return;
	            }
	            String actividadSeleccionada = (String) model.getValueAt(selectedRow, 0);
	            Actividad actividad = null;
	            for (Actividad a : controller.getListaActividades()) {
	                if (a.getNombre().equals(actividadSeleccionada)) {
	                    actividad = a;
	                    break;
	                }
	            }
	            if (usuario instanceof Cliente) {
	                Cliente cliente = (Cliente) usuario;
	                
	                if(controller.inscribirActividad(cliente, actividad)) {
		                JOptionPane.showMessageDialog(null, "Te has inscrito en la actividad: " + actividad.getNombre());
		                updateActividadesDisponibles(activitiesTable);
	                }
	                
	                int rowIndex = activitiesTable.convertRowIndexToView(selectedRow);
	                model.setValueAt(actividad.getPlazasDisponibles(), rowIndex, 3);
	            } else {
	                JOptionPane.showMessageDialog(null, "Solo los clientes pueden inscribirse en actividades");
	            }
	        }
	    });
	    
	    JButton actualizarButton = new JButton("Actualizar");
	    actualizarButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	updateActividadesDisponibles(activitiesTable);
	        }
	    });
	    
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    buttonPanel.add(inscribirseButton);
	    buttonPanel.add(actualizarButton);
	    activitiesPanel.add(new JScrollPane(activitiesTable), BorderLayout.CENTER);
	    activitiesPanel.add(buttonPanel, BorderLayout.SOUTH);

	    return activitiesPanel;
	}
	
	private void updateActividadesDisponibles(JTable activitiesTable) {

	    NonEditableTableModel model = (NonEditableTableModel) activitiesTable.getModel();
	    model.setRowCount(0); // Borrar filas anteriores
	    for (Actividad actividad : controller.getActNoInscrito(usuario.getDNI())) {
	        model.addRow(new Object[]{
	                actividad.getNombre(),
	                actividad.getHorario(),
	                actividad.getDNIProfesor(),
	                actividad.getPlazasDisponibles()
	        });
	    }
	    activitiesTable.setModel(model); // Actualizar vista de la tabla
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private JPanel createActividadesInscritas() {
	    JPanel activitiesPanel = new JPanel(new BorderLayout());

	    // Crear tabla con las actividades inscritas
	    JTable activitiesTable = new JTable();
	    NonEditableTableModel model = new NonEditableTableModel(new Object[]{"Actividad", "Horario", "Instructor", "Plazas Disponibles"}, 0);
	    for (Actividad actividad : controller.obtenerActividadPorDNI(usuario.getDNI())) {
	        model.addRow(new Object[]{
	                actividad.getNombre(),
	                actividad.getHorario(),
	                actividad.getDNIProfesor(),
	                actividad.getPlazasDisponibles()
	        });
	    }
	    activitiesTable.setModel(model);
	    
	    JButton inscribirseButton = new JButton("Desinscribirse");
	    inscribirseButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            int selectedRow = activitiesTable.getSelectedRow();
	            if (selectedRow == -1) {
	                JOptionPane.showMessageDialog(null, "Por favor, selecciona una actividad");
	                return;
	            }
	            String actividadSeleccionada = (String) model.getValueAt(selectedRow, 0);
	            Actividad actividad = null;
	            for (Actividad a : controller.getListaActividades()) {
	                if (a.getNombre().equals(actividadSeleccionada)) {
	                    actividad = a;
	                    break;
	                }
	            }
	            if (usuario instanceof Cliente) {
	                Cliente cliente = (Cliente) usuario;
	                if (controller.borrarUsuarioActividad(cliente, actividad)) {
	                    JOptionPane.showMessageDialog(null, "Te has desinscrito de la actividad: " + actividad.getNombre());
	                    updateActividadesInscritas(activitiesTable); // Actualizar tabla después de borrar al usuario
	                }
	                else {
	                    JOptionPane.showMessageDialog(null, "Ha habido un problema al desinscibirse en la actividad: " + actividad.getNombre());
	                }
	            }
	        }
	    });
	    JButton actualizarButton = new JButton("Actualizar");
	    actualizarButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	updateActividadesInscritas(activitiesTable);
	        }
	    });
	    
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    buttonPanel.add(inscribirseButton);
	    buttonPanel.add(actualizarButton);
	    activitiesPanel.add(new JScrollPane(activitiesTable), BorderLayout.CENTER);
	    activitiesPanel.add(buttonPanel, BorderLayout.SOUTH);
	    
	    return activitiesPanel;
	}

	private void updateActividadesInscritas(JTable activitiesTable) {

	    NonEditableTableModel model = (NonEditableTableModel) activitiesTable.getModel();
	    model.setRowCount(0); // Borrar filas anteriores
	    for (Actividad actividad : controller.obtenerActividadPorDNI(usuario.getDNI())) {
	        model.addRow(new Object[]{
	                actividad.getNombre(),
	                actividad.getHorario(),
	                actividad.getDNIProfesor(),
	                actividad.getPlazasDisponibles()
	        });
	    }
	    activitiesTable.setModel(model); // Actualizar vista de la tabla
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private JPanel createSurveyPanel() {
	    JPanel surveyPanel = new JPanel();
	    surveyPanel.setLayout(new BorderLayout());

	    DefaultTableModel tableModel = new DefaultTableModel();
	    tableModel.addColumn("Fecha");
	    tableModel.addColumn("Pregunta");
	    tableModel.addColumn("Respuesta");

	    JTable surveyTable = new JTable(tableModel);
	    JScrollPane scrollPane = new JScrollPane(surveyTable);

	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    JButton addButton = new JButton("Agregar Encuesta");
	    addButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            EncuestaDialog dialog = new EncuestaDialog();
	            dialog.setVisible(true);
	            if (dialog.isOk()) {
	                Encuesta encuesta = dialog.getEncuesta();
	                controller.addEncuesta(encuesta);
	                Object[] row = {encuesta.getFecha(), encuesta.getPregunta(), encuesta.getRespuesta()};
	                tableModel.addRow(row);
	            }
	        }
	    });
	    buttonPanel.add(addButton);

	    surveyPanel.add(scrollPane, BorderLayout.CENTER);
	    surveyPanel.add(buttonPanel, BorderLayout.SOUTH);

	    for (Encuesta encuesta : controller.getEncuestas()) {
	        Object[] row = {encuesta.getFecha(), encuesta.getPregunta(), encuesta.getRespuesta()};
	        tableModel.addRow(row);
	    }

	    return surveyPanel;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*private JPanel createMaterialsPanel() {
	    JPanel panel = new JPanel();
	    panel.setLayout(new BorderLayout());

	    // tabla de materiales
	    DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nombre", "Descripción", "Cantidad", "Precio"}, 0);
	    JTable table = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
	    panel.add(scrollPane, BorderLayout.CENTER);

	    // botón para agregar un nuevo material
	    JButton addButton = new JButton("Agregar");
	    addButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            MaterialDialog dialog = new MaterialDialog();
	            dialog.setVisible(true);
	            if (dialog.getMaterial() != null) {
	            	controller.agregarMaterial(dialog.getMaterial());
	                model.addRow(new Object[]{dialog.getMaterial().getId(), dialog.getMaterial().getNombre(),
	                                          dialog.getMaterial().getDescripcion(), dialog.getMaterial().getCantidad(),
	                                          dialog.getMaterial().getPrecio()});
	            }
	        }
	    });

	    // botón para eliminar un material seleccionado de la tabla
	    JButton removeButton = new JButton("Eliminar");
	    removeButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            int row = table.getSelectedRow();
	            if (row != -1) {
	                String id = model.getValueAt(row, 0).toString();
	                controller.eliminarMaterial(id);
	                model.removeRow(row);
	            }
	        }
	    });

	    // panel de botones de acción
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.add(addButton);
	    buttonPanel.add(removeButton);
	    panel.add(buttonPanel, BorderLayout.SOUTH);

	    // carga inicial de los materiales en la tabla
	    for (Material material : controller.obtenerMateriales()) {
	        model.addRow(new Object[]{material.getId(), material.getNombre(), material.getDescripcion(),
	                                  material.getCantidad(), material.getPrecio()});
	    }

	    return panel;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*private JPanel createProfilePanel() {
	    JPanel profilePanel = new JPanel(new BorderLayout());
	    JPanel formPanel = new JPanel(new GridLayout(3, 2));

	    JLabel nameLabel = new JLabel("Nombre:");
	    JTextField nameField = new JTextField(usuario.getNombre());
	    JLabel dniLabel = new JLabel("DNI:");
	    JTextField dniField = new JTextField(usuario.getDNI());

	    formPanel.add(nameLabel);
	    formPanel.add(nameField);
	    formPanel.add(dniLabel);
	    formPanel.add(dniField);

	    JButton saveButton = new JButton("Guardar cambios");
	    saveButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // actualiza los datos del usuario con los valores del formulario
	            usuario.setNombre(nameField.getText());
	            // guarda los cambios en la base de datos
	            gymController.actualizarCliente((Cliente)usuario);
	            // muestra un mensaje de confirmación
	            JOptionPane.showMessageDialog(profilePanel, "Los cambios se han guardado correctamente.");
	        }
	    });

	    profilePanel.add(formPanel, BorderLayout.CENTER);
	    profilePanel.add(saveButton, BorderLayout.SOUTH);

	    return profilePanel;
	}
	

	*/
	
	private class NonEditableTableModel extends DefaultTableModel {
	    public NonEditableTableModel(Object[] columnNames, int rowCount) {
	        super(columnNames, rowCount);
	    }

	    @Override
	    public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	}
}
