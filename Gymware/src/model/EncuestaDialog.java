package model;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import model.Encuesta;

public class EncuestaDialog extends JDialog {

    private Encuesta encuesta;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtPeso;
    private JTextField txtAltura;
    private JTextField txtObjetivo;
    private JButton btnGuardar;

    public EncuestaDialog() {
        setTitle("Encuesta");
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridy++;
        panel.add(new JLabel("Edad:"), gbc);
        gbc.gridy++;
        panel.add(new JLabel("Peso (kg):"), gbc);
        gbc.gridy++;
        panel.add(new JLabel("Altura (cm):"), gbc);
        gbc.gridy++;
        panel.add(new JLabel("Objetivo:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        txtNombre = new JTextField(20);
        panel.add(txtNombre, gbc);
        gbc.gridy++;
        txtEdad = new JTextField(3);
        panel.add(txtEdad, gbc);
        gbc.gridy++;
        txtPeso = new JTextField(3);
        panel.add(txtPeso, gbc);
        gbc.gridy++;
        txtAltura = new JTextField(3);
        panel.add(txtAltura, gbc);
        gbc.gridy++;
        txtObjetivo = new JTextField(20);
        panel.add(txtObjetivo, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (validarCampos()) {
//                    encuesta = new Encuesta(txtNombre.getText(), Integer.parseInt(txtEdad.getText()),
//                            Double.parseDouble(txtPeso.getText()), Double.parseDouble(txtAltura.getText()),
//                            txtObjetivo.getText());
//                    dispose();
//                } else {
//                    JOptionPane.showMessageDialog(EncuestaDialog.this, "Por favor ingrese todos los campos.",
//                            "Error", JOptionPane.ERROR_MESSAGE);
//                }
            }
        });
        panel.add(btnGuardar, gbc);
        add(panel);
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    private boolean validarCampos() {
        return !txtNombre.getText().isEmpty() && !txtEdad.getText().isEmpty() && !txtPeso.getText().isEmpty()
                && !txtAltura.getText().isEmpty() && !txtObjetivo.getText().isEmpty();
    }

	public boolean isOk() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(() -> new EncuestaDialog());
	}

}
