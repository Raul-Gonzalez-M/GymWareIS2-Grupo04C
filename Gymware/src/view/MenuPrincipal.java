package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.GymController;
import model.Actividad;
import model.Cliente;
import model.Encuesta;
import model.Gimnasio;
import model.Material;
import model.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ModuleLayer.Controller;

public class MenuPrincipal extends JPanel {

    private GymController gymcontroller;
    private JTabbedPane tabbedPane;
    private Gimnasio gimnasio;
    private Usuario usuario;
    
    public MenuPrincipal(GymController controller) {
        this.gymcontroller = controller;
        this.gimnasio = controller.getGimnasio();
        this.usuario = gymcontroller.getUsuario();
        initComponents();     
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Actividades", createActivitiesPanel());
        tabbedPane.addTab("Encuestas", createSurveyPanel());
        tabbedPane.addTab("Materiales", createMaterialsPanel());
        tabbedPane.addTab("Mi Perfil", createProfilePanel());
        setSize(1200, 800);
        getRootPane().add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createActivitiesPanel() {
            JPanel activitiesPanel = new JPanel(new BorderLayout());
        
            // Crear tabla con las actividades disponibles
            JTable activitiesTable = new JTable();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Actividad");
            model.addColumn("Inicio");
            model.addColumn("Fin");
            model.addColumn("Instructor");
            model.addColumn("Plazas Disponibles");
            for (Actividad actividad : gimnasio.getActividades()) {
                model.addRow(new Object[] {
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
                        JOptionPane.showMessageDialog(null, "Selecciona una actividad para inscribirte");
                        return;
                    }
                    // Obtener la actividad y el usuario actual
                    Actividad actividad = gimnasio.getActividades().get(selectedRow);
                    // Comprobar si hay plazas disponibles
                    if (actividad.getPlazasDisponibles() == 0) {
                        JOptionPane.showMessageDialog(null, "Lo siento, ya no quedan plazas disponibles en esta actividad");
                        return;
                    }
                    // Comprobar si el usuario ya está inscrito
                    if (usuario.getActividades().contains(actividad)) {
                        JOptionPane.showMessageDialog(null, "Ya estás inscrito en esta actividad");
                        return;
                    }
                    // Inscribir al usuario en la actividad
                    gimnasio.inscribirEnActividad(usuario, actividad);
                    JOptionPane.showMessageDialog(null, "Te has inscrito en la actividad " + actividad.getNombre());
                }
            });
        
            // Añadir tabla y botón al panel
            activitiesPanel.add(new JScrollPane(activitiesTable), BorderLayout.CENTER);
            activitiesPanel.add(inscribirseButton, BorderLayout.SOUTH);
        
            return activitiesPanel;
        }
        
    }

    private JPanel createSurveyPanel() {

        JPanel surveyPanel = new JPanel(new BorderLayout());

        DefaultListModel<Encuesta> surveyListModel = new DefaultListModel<>();
        for (Encuesta survey : gymcontroller.getAvailableSurveys()) {
            surveyListModel.addElement(survey);
        }


        JList<Encuesta> surveyList = new JList<>(surveyListModel);
        surveyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        surveyList.setCellRenderer(new SurveyListRenderer());
        JScrollPane surveyScrollPane = new JScrollPane(surveyList);


        JButton startSurveyButton = new JButton("Realizar Encuesta");
        startSurveyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Encuesta selectedSurvey = surveyList.getSelectedValue();
                if (selectedSurvey == null) {
                    JOptionPane.showMessageDialog(
                            MainWindow.this,
                            "Por favor seleccione una encuesta para realizar.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // show the survey form for the selected survey
                    showSurveyForm(selectedSurvey);
                }
            }
        });


        surveyPanel.add(surveyScrollPane, BorderLayout.CENTER);
        surveyPanel.add(startSurveyButton, BorderLayout.SOUTH);

        return surveyPanel;
    }

    }

    private JPanel createMaterialsPanel() {
    	
            JPanel materialsPanel = new JPanel(new BorderLayout());
            materialsPanel.setBorder(BorderFactory.createTitledBorder("Venta de materiales"));
        
            // Lista de materiales
            JPanel materialsListPanel = new JPanel(new GridLayout(0, 1, 0, 10));
            for (Material material : gymcontroller.getMateriales()) {
                JLabel label = new JLabel(material.getNombre() + " - " + material.getPrecio() + "€");
                JButton button = new JButton("Comprar");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Mostrar mensaje de confirmación de compra
                        int result = JOptionPane.showConfirmDialog(null,
                                "¿Estás seguro de que deseas comprar " + material.getNombre() + "?\nPrecio: " + material.getPrecio() + "€",
                                "Confirmar compra", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            // Registrar la compra en la base de datos
                        	gymcontroller.registrarCompra(material);
                            JOptionPane.showMessageDialog(null, "Compra realizada con éxito", "Compra realizada", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });
                JPanel materialPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                materialPanel.add(label);
                materialPanel.add(button);
                materialsListPanel.add(materialPanel);
            }
        
            JScrollPane scrollPane = new JScrollPane(materialsListPanel);
            materialsPanel.add(scrollPane, BorderLayout.CENTER);
        
            return materialsPanel;
        }        
    }

    private JPanel createProfilePanel() {
    	
            JPanel profilePanel = new JPanel(new BorderLayout());
            JLabel titleLabel = new JLabel("Mi perfil", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            profilePanel.add(titleLabel, BorderLayout.NORTH);
        
            JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
            JLabel nameLabel = new JLabel("Nombre:");
            JTextField nameField = new JTextField();
            nameField.setEditable(false);
        
            JLabel emailLabel = new JLabel("Email:");
            JTextField emailField = new JTextField();
            emailField.setEditable(false);
        
            JLabel phoneLabel = new JLabel("Teléfono:");
            JTextField phoneField = new JTextField();
            phoneField.setEditable(false);
        
            JLabel planLabel = new JLabel("Plan contratado:");
            JTextField planField = new JTextField();
            planField.setEditable(false);
        
            formPanel.add(nameLabel);
            formPanel.add(nameField);
            formPanel.add(emailLabel);
            formPanel.add(emailField);
            formPanel.add(phoneLabel);
            formPanel.add(phoneField);
            formPanel.add(planLabel);
            formPanel.add(planField);
        
            JButton editButton = new JButton("Editar perfil");
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nameField.setEditable(true);
                    emailField.setEditable(true);
                    phoneField.setEditable(true);
                    editButton.setText("Guardar cambios");
                    editButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String newName = nameField.getText();
                            String newEmail = emailField.getText();
                            String newPhone = phoneField.getText();
        
                            // Actualizamos los datos del cliente
                            boolean result = gymcontroller.actualizar;
                            if (result) {
                                JOptionPane.showMessageDialog(null, "Perfil actualizado con éxito");
                                nameField.setEditable(false);
                                emailField.setEditable(false);
                                phoneField.setEditable(false);
                                editButton.setText("Editar perfil");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al actualizar el perfil");
                            }
                        }
                    });
                }
            });
        
            // Mostramos los datos del cliente en los campos correspondientes

            nameField.setText(usuario.getNombre());
            emailField.setText(usuario.getEmail());
            phoneField.setText(usuario.getPhone());
            planField.setText(usuario.getPlan().getName());
        
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(editButton);
        
            profilePanel.add(formPanel, BorderLayout.CENTER);
            profilePanel.add(buttonPanel, BorderLayout.SOUTH);
        
            return profilePanel;
    } 
}
