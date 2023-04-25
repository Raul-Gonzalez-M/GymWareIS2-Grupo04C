package model;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.GymController;

public class MaterialDialog extends JDialog {
    
    private GymController gymController;
    
    private JTextField nombreField;
    private JTextField precioField;
    private JTextField unidadesField;
    
    public MaterialDialog(GymController gymController) {
        this.gymController = gymController;
        
        setTitle("Agregar Material");
        setModal(true);
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
    }
    
    private void initComponents() {
        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel precioLabel = new JLabel("Precio:");
        JLabel unidadesLabel = new JLabel("Unidades:");
        
        nombreField = new JTextField(20);
        precioField = new JTextField(20);
        unidadesField = new JTextField(20);
        
        JPanel panelCampos = new JPanel(new BorderLayout());
        panelCampos.add(nombreLabel, BorderLayout.WEST);
        panelCampos.add(nombreField, BorderLayout.CENTER);
        panelCampos.add(precioLabel, BorderLayout.WEST);
        panelCampos.add(precioField, BorderLayout.CENTER);
        panelCampos.add(unidadesLabel, BorderLayout.WEST);
        panelCampos.add(unidadesField, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton aceptarButton = new JButton("Aceptar");
        JButton cancelarButton = new JButton("Cancelar");
        panelBotones.add(aceptarButton);
        panelBotones.add(cancelarButton);
        
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String precioText = precioField.getText();
                String unidadesText = unidadesField.getText();
                
                if (nombre.isEmpty() || precioText.isEmpty() || unidadesText.isEmpty()) {
                    JOptionPane.showMessageDialog(MaterialDialog.this, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    double precio = 0;
                    int unidades = 0;
                    try {
                        precio = Double.parseDouble(precioText);
                        unidades = Integer.parseInt(unidadesText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MaterialDialog.this, "Ingrese un valor numérico válido en Precio y Unidades.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    Material material = new Material(nombre, precio, 1);
                    gymController.agregarMaterial(material);
                    
                    JOptionPane.showMessageDialog(MaterialDialog.this, "Material agregado exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            }
        });
        
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelCampos, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
    }
    
}
