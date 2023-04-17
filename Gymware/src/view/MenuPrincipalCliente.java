package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.GymController;
import model.Actividad;
import model.Cliente;
import model.Encuesta;
import model.EncuestaDialog;
import model.Gimnasio;
import model.Material;
import model.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ModuleLayer.Controller;

public class MenuPrincipalCliente extends JPanel {

	private GymController gymController;
	private JTabbedPane tabbedPane;
	private Gimnasio gimnasio;
	private Usuario usuario;

	public MenuPrincipalCliente(GymController controller, Usuario usuarioActual) {
	    this.gymController = controller;
	    this.gimnasio = gymController.getGimnasio();
	    this.usuario = usuarioActual;
	    
	    initComponents();
	}
	
	private void initComponents() {
	    tabbedPane = new JTabbedPane();

	    tabbedPane.addTab("Actividades", createActivitiesPanel());
	    tabbedPane.addTab("Encuestas", createSurveyPanel());
	    //tabbedPane.addTab("Materiales", createMaterialsPanel());
	    //tabbedPane.addTab("Mi Perfil", createProfilePanel());

	    setLayout(new BorderLayout());
	    add(tabbedPane, BorderLayout.CENTER);

	    setSize(1200, 800);
	}


	private JPanel createActivitiesPanel() {
	    JPanel activitiesPanel = new JPanel(new BorderLayout());

	    // Crear tabla con las actividades disponibles
	    JTable activitiesTable = new JTable();
	    DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Actividad");
	    model.addColumn("Horario");
	    model.addColumn("Instructor");
	    model.addColumn("Plazas Disponibles");
	    for (Actividad actividad : gimnasio.getActividades()) {
	        model.addRow(new Object[]{
	                actividad.getNombre(),
	                actividad.getHorario(),
	                actividad.getDNIProfesor(),
	                actividad.getPlazasDisponibles()
	        });
	    }
	    activitiesTable.setModel(model);

	    // Crear botón para inscribirse
	    JButton inscribirseButton = new JButton("Inscribirse");
	    inscribirseButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Obtener la fila seleccionada
	            int selectedRow = activitiesTable.getSelectedRow();
	            if (selectedRow == -1) {
	                JOptionPane.showMessageDialog(null, "Por favor, selecciona una actividad");
	                return;
	            }
	            // Obtener la actividad seleccionada
	            Actividad actividad = gimnasio.getActividades().get(selectedRow);
	            // Comprobar si el usuario ya está inscrito en la actividad
	            if (usuario instanceof Cliente) {
	                Cliente cliente = (Cliente) usuario;
	                /*if (cliente.getActividades().contains(actividad)) {
	                    JOptionPane.showMessageDialog(null, "Ya estás inscrito en esta actividad");
	                    return;
	                }*/
	                // Comprobar si hay plazas disponibles
	                if (actividad.getPlazasDisponibles() == 0) {
	                    JOptionPane.showMessageDialog(null, "Lo siento, no quedan plazas disponibles para esta actividad");
	                    return;
	                }
	                // Inscribir al usuario en la actividad
	                gimnasio.inscribirActividad(cliente, actividad);
	                // Actualizar la tabla de actividades
	                int rowIndex = activitiesTable.convertRowIndexToView(selectedRow);
	                model.setValueAt(actividad.getPlazasDisponibles(), rowIndex, 4);
	                JOptionPane.showMessageDialog(null, "Te has inscrito en la actividad " + actividad.getNombre());
	            } else {
	                JOptionPane.showMessageDialog(null, "Solo los clientes pueden inscribirse en actividades");
	            }
	        }
	    });

	    // Crear panel de botones
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    buttonPanel.add(inscribirseButton);

	    // Agregar la tabla y el panel de botones al panel principal
	    activitiesPanel.add(new JScrollPane(activitiesTable), BorderLayout.CENTER);
	    activitiesPanel.add(buttonPanel, BorderLayout.SOUTH);

	    return activitiesPanel;
	}

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
	                gimnasio.addEncuesta(encuesta);
	                Object[] row = {encuesta.getFecha(), encuesta.getPregunta(), encuesta.getRespuesta()};
	                tableModel.addRow(row);
	            }
	        }
	    });
	    buttonPanel.add(addButton);

	    surveyPanel.add(scrollPane, BorderLayout.CENTER);
	    surveyPanel.add(buttonPanel, BorderLayout.SOUTH);

	    for (Encuesta encuesta : gimnasio.getEncuestas()) {
	        Object[] row = {encuesta.getFecha(), encuesta.getPregunta(), encuesta.getRespuesta()};
	        tableModel.addRow(row);
	    }

	    return surveyPanel;
	}

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
	                gymController.agregarMaterial(dialog.getMaterial());
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
	                gymController.eliminarMaterial(id);
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
	    for (Material material : gymController.obtenerMateriales()) {
	        model.addRow(new Object[]{material.getId(), material.getNombre(), material.getDescripcion(),
	                                  material.getCantidad(), material.getPrecio()});
	    }

	    return panel;
	}

	private JPanel createProfilePanel() {
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
}