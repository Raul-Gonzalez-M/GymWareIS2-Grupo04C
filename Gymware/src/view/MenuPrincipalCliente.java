package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Actividad;
import model.Cliente;
import model.Encuesta;
import model.Material;
import model.Usuario;
import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MenuPrincipalCliente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JTabbedPane tabbedPane;
	private Usuario usuario;
	private JPanel parentPanel;
	private CardLayout cardLayout;
	JLabel saldoField;
	JSpinner part;
	
	public MenuPrincipalCliente(Controller controller, Usuario usuarioActual, JPanel parentPanel) throws SQLException {
	    this.controller = controller;
	    this.usuario = usuarioActual;
	    this.parentPanel = parentPanel;
        this.cardLayout = (CardLayout) parentPanel.getLayout();
	    initComponents();
	}
	
	private void initComponents() throws SQLException {
	    tabbedPane = new JTabbedPane();

	    tabbedPane.addTab("Actividades Disponibles", createActividadesDisponibles());
	    tabbedPane.addTab("Actividades Inscritas", createActividadesInscritas());
	    tabbedPane.addTab("Compra Materiales", createMaterialesPanel());
	    //tabbedPane.addTab("Mis Materiales", createMisMaterialesPanel());
	    tabbedPane.addTab("Encuestas", createEncuestaPanel());
	    tabbedPane.addTab("Mi Perfil", createProfilePanel());
	    
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
	                controller.obtenerUsuarioPorDNI(actividad.getDNIProfesor()).getNombre(),
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
		                cliente.getActividades().add(actividad);
		                updateActividadesDisponibles(activitiesTable);
	                }
	                
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
	                controller.obtenerUsuarioPorDNI(actividad.getDNIProfesor()).getNombre(),
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
	                	cliente.getActividades().remove(actividad);
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

	private JPanel createMaterialesPanel() throws SQLException {
		JPanel materialesPanel = new JPanel(new BorderLayout());


		JTable materialesTable = new JTable();
	    NonEditableTableModel model = new NonEditableTableModel(new Object[]{"Material", "Precio", "Unidades", "Actividad asociada"}, 0);
	    for (Material material : controller.getMaterialesDisponibles()) {
	        model.addRow(new Object[]{
	                material.getNombre(),
	                material.getPrecio(),
	                material.getCantidad_disponible(),
	                material.getActividad_asociada()
	        });
	    }
	    
	    materialesTable.setModel(model);

	    JButton comprarButton = new JButton("Comprar");
	    comprarButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	int selectedRow = materialesTable.getSelectedRow();
	            if (selectedRow == -1) {
	                JOptionPane.showMessageDialog(null, "Por favor, selecciona un material");
	                return;
	            }
	            double materialSeleccionado = (double) model.getValueAt(selectedRow, 1);
	            Cliente cliente = (Cliente) usuario;
	            
	            if(cliente.getSaldo() > materialSeleccionado) {
	            	cliente.setSaldo(cliente.getSaldo() - materialSeleccionado);
	            	saldoField.setText(""+cliente.getSaldo());
	            	controller.setSaldo(cliente);
	            	model.setValueAt((double)model.getValueAt(selectedRow, 2) - 1, selectedRow, 2);
	            	JOptionPane.showMessageDialog(null, "Compra realizada!");
	            	controller.updateMaterial((String) model.getValueAt(selectedRow, 0));
	            	model.fireTableDataChanged();
	            } else {
	            	Utils.showErrorMsg("No tienes suficiente dinero en tu cuenta");
	            }
	        }
	    });
	    
	    JButton actualizarButton = new JButton("Actualizar");
	    actualizarButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	updateMaterialesDisponibles(materialesTable);
	        }
	    });
	    
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    buttonPanel.add(comprarButton);
	    buttonPanel.add(actualizarButton);
	    materialesPanel.add(new JScrollPane(materialesTable), BorderLayout.CENTER);
	    materialesPanel.add(buttonPanel, BorderLayout.SOUTH);

	    return materialesPanel;
	}
	
	private JPanel createEncuestaPanel() {
	    JPanel surveyPanel = new JPanel();
	    surveyPanel.setLayout(new BoxLayout(surveyPanel, BoxLayout.PAGE_AXIS));
	    
	    JPanel titlePanel = new JPanel();
	    JLabel title = new JLabel("Rellene los siguientes campos conforme a su opinión:");
	    title.setFont(new Font("Arial", Font.BOLD, 40));
	    titlePanel.add(title);
	    
	    JPanel ratePanel = new JPanel();
	    JLabel rateLabel = new JLabel("Cual es tu nivel de satisfacción con nuestro gimnasio:   ");
	    DefaultComboBoxModel<Integer> model  = new DefaultComboBoxModel<>();
	    model.setSelectedItem(0);
	    for(int i = 0; i <= 10; i++) {
	    	model.addElement(i);
	    }
	    JComboBox<Integer> rate = new JComboBox<>(model);
	    ratePanel.add(rateLabel);
	    ratePanel.add(rate);
	    
	    JPanel partPanel = new JPanel();
	    JLabel partLabel = new JLabel("¿Participas en alguna de nuestras actividades?   ");
	    
	    String[] values = {"SI", "NO"};
	    
	    part = new JSpinner(new SpinnerListModel(values));
	    part.setMaximumSize(new Dimension(40, 20));
	    part.setMinimumSize(new Dimension(40, 20));
	    part.setPreferredSize(new Dimension(40, 20));
	    
	    partPanel.add(partLabel);
	    partPanel.add(part);
	    
	    JPanel cambiosPanel = new JPanel();
	    cambiosPanel.setLayout(new BoxLayout(cambiosPanel, BoxLayout.PAGE_AXIS));
	    JPanel textPanel = new JPanel();
	    textPanel.setPreferredSize(new Dimension(600, 30));
	    textPanel.setMinimumSize(new Dimension(600, 30));
	    textPanel.setMaximumSize(new Dimension(600, 30));
	    JLabel text1 = new JLabel("Escribe qué cambios harías y cómo podemos mejorar: ");
	    textPanel.add(text1);
	    cambiosPanel.add(textPanel);
	    
	    JTextField text = new JTextField();
	    text.setEditable(true);
	    text.setMaximumSize(new Dimension(700, 50));
	    text.setMinimumSize(new Dimension(700, 50));
	    text.setPreferredSize(new Dimension(700, 50));
	    
	    cambiosPanel.add(text);
	    
	    JPanel buttonPanel = new JPanel();
	    JButton enviarEncuesta = new JButton("ENVIAR");
	    enviarEncuesta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rated = rate.getSelectedIndex();
				if(rated == -1) {
					JOptionPane.showMessageDialog(Utils.getWindow(MenuPrincipalCliente.this), "Escoja una valoración");
					return;
				}
				
				String participa = (String)part.getValue();
				
				if(!participa.equals("SI") && !participa.equals("NO")) {
					JOptionPane.showMessageDialog(Utils.getWindow(MenuPrincipalCliente.this), "Indica si realizas alguna actividad");
					return;
				}
				
				String texted = text.getText();
				
				Cliente cliente = (Cliente) usuario;
				
				Encuesta survey = new Encuesta(cliente.getDNI(), null, rated, texted, participa);
				controller.addEncuesta(survey);
				
				JOptionPane.showMessageDialog(Utils.getWindow(MenuPrincipalCliente.this), "¡Encuesta enviada correctamente!");
			}
	    	
	    });
	    buttonPanel.add(enviarEncuesta);
	    

	    surveyPanel.add(titlePanel);
	    surveyPanel.add(ratePanel);
	    surveyPanel.add(partPanel);
	    surveyPanel.add(cambiosPanel);
	    surveyPanel.add(buttonPanel);
	    return surveyPanel;
	}
	
	private JPanel createProfilePanel() {
	    Cliente cliente = (Cliente) usuario;
	    JPanel userPanel = new JPanel(new BorderLayout());
	    JPanel formPanel = new JPanel();
	    formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	    JLabel dniLabel = new JLabel("DNI:");
	    dniLabel.setBounds(425, 152, 54, 33);
	    JLabel dniField = new JLabel(cliente.getDNI());
	    dniField.setBounds(569, 152, 179, 33);
	    dniLabel.setFont(new Font("Arial", Font.BOLD, 12));
	    dniField.setFont(new Font("Arial", Font.PLAIN, 12));
	    dniField.setFocusable(false);

	    JLabel nameLabel = new JLabel("Nombre:");
	    nameLabel.setBounds(425, 287, 54, 36);
	    JLabel nameField = new JLabel(cliente.getNombre());
	    nameField.setBounds(569, 289, 173, 33);
	    nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
	    nameField.setFont(new Font("Arial", Font.PLAIN, 12));
	    nameLabel.setFocusable(false);

	    JLabel passwordLabel = new JLabel("Nueva Contraseña:");
	    passwordLabel.setBounds(425, 439, 122, 33);
	    JPasswordField passwordField = new JPasswordField();
	    passwordField.setBounds(569, 439, 173, 33);
	    passwordLabel.setFont(new Font("Arial", Font.BOLD, 12));
	    passwordField.setFont(new Font("Arial", Font.PLAIN, 12));

	    JLabel fechaAltaLabel = new JLabel("Fecha de alta:");
	    fechaAltaLabel.setBounds(425, 217, 122, 33);
	    JLabel fechaAltaField = new JLabel(cliente.getFechaAlta());
	    fechaAltaField.setBounds(569, 217, 173, 33);
	    fechaAltaLabel.setFont(new Font("Arial", Font.BOLD, 12));
	    fechaAltaField.setFont(new Font("Arial", Font.PLAIN, 12));
	    fechaAltaField.setFocusable(false);

	    JLabel saldoLabel = new JLabel("Saldo:");
	    saldoLabel.setBounds(425, 365, 54, 33);
	    saldoField = new JLabel(Double.toString(cliente.getSaldo()));
	    saldoField.setBounds(569, 365, 173, 33);
	    saldoLabel.setFont(new Font("Arial", Font.BOLD, 12));
	    saldoField.setFont(new Font("Arial", Font.PLAIN, 12));
	    formPanel.setLayout(null);
	    saldoField.setFocusable(false);

	    formPanel.add(dniLabel);
	    formPanel.add(dniField);
	    formPanel.add(nameLabel);
	    formPanel.add(nameField);
	    formPanel.add(passwordLabel);
	    formPanel.add(passwordField);
	    formPanel.add(fechaAltaLabel);
	    formPanel.add(fechaAltaField);
	    formPanel.add(saldoLabel);
	    formPanel.add(saldoField);

	    JButton saveButton = new JButton("Guardar cambios");
	    saveButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	char[] passwordChars = passwordField.getPassword();
	        	String nuevaContra = new String(passwordChars);
	        	controller.cambiarContrasenya(cliente,nuevaContra);
	    	    cliente.setContrasena(nuevaContra);
	    	    JOptionPane.showMessageDialog(null, "Datos actualizados");
	        }
	    });
	    
	    JButton bajaButton = new JButton("Darse de baja");
	    bajaButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	if(JOptionPane.showConfirmDialog(formPanel, "¿Está seguro de que desea darse de baja?", "DARSE DE BAJA", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
	        		controller.darBajaUsusario(cliente.getDNI());
	        		cardLayout.show(parentPanel, "home");
	        	}
	        }
	    });
	    
	    JButton saldoButton = new JButton("Recargar saldo");
	    saldoButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            cliente.setSaldo(cliente.getSaldo() + 100);
	            controller.setSaldo(cliente);
	            saldoField.setText(Double.toString(cliente.getSaldo()));
	            formPanel.repaint();
	        }
	    });
	    
	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    buttonPanel.add(saveButton);
	    buttonPanel.add(bajaButton);
	    buttonPanel.add(saldoButton);
	    userPanel.add(buttonPanel, BorderLayout.SOUTH);
	    userPanel.add(formPanel, BorderLayout.CENTER);

	    return userPanel;
	}


	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void updateActividadesInscritas(JTable activitiesTable) {

	    NonEditableTableModel model = (NonEditableTableModel) activitiesTable.getModel();
	    model.setRowCount(0); // Borrar filas anteriores
	    for (Actividad actividad : controller.obtenerActividadPorDNI(usuario.getDNI())) {
	        model.addRow(new Object[]{
	                actividad.getNombre(),
	                actividad.getHorario(),
	                controller.obtenerUsuarioPorDNI(actividad.getDNIProfesor()).getNombre(),
	                actividad.getPlazasDisponibles()
	        });
	    }
	    activitiesTable.setModel(model);
	}
	
	private void updateActividadesDisponibles(JTable activitiesTable) {

	    NonEditableTableModel model = (NonEditableTableModel) activitiesTable.getModel();
	    model.setRowCount(0); 
	    for (Actividad actividad : controller.getActNoInscrito(usuario.getDNI())) {
	        		model.addRow(new Object[]{
	                actividad.getNombre(),
	                actividad.getHorario(),
	                controller.obtenerUsuarioPorDNI(actividad.getDNIProfesor()).getNombre(),
	                actividad.getPlazasDisponibles()
	        });
	    }
	    activitiesTable.setModel(model); 
	}
	
	private void updateMaterialesDisponibles(JTable materialesTable) {
		NonEditableTableModel model = new NonEditableTableModel(new Object[]{"Material", "Precio", "Unidades", "Actividad asociada"}, 0);
	    for (Material material : controller.getMaterialesDisponibles()) {
	        model.addRow(new Object[]{
	                material.getNombre(),
	                material.getPrecio(),
	                material.getCantidad_disponible(),
	                material.getActividad_asociada()
	        });
	    }
	    materialesTable.setModel(model);
	}
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
