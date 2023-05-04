package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.util.Date;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import controller.Controller;
import model.Cliente;
import model.Personal;
import model.Usuario;

public class RegisterWindow extends JPanel {

	private JTextField dniTextField;
	private JTextField nombreTextField;
	private JTextField apellidosTextField;
	private JPasswordField passwordField;
	private JPanel parentPanel;
	private CardLayout cardLayout;
	private Controller controller;
	
	public RegisterWindow(Controller controller, JPanel pp) {
		this.controller = controller;
		this.parentPanel = pp;
		this.cardLayout = (CardLayout) parentPanel.getLayout();
		setLayout(null);
		inicializar();
	}
	
	public void inicializar() {
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblRegistro.setBounds(535, 209, 150, 45);
		add(lblRegistro);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(482, 310, 78, 17);
		add(lblDni);
		
		dniTextField = new JTextField();
		dniTextField.setColumns(10);
		dniTextField.setBounds(482, 337, 219, 26);
		add(dniTextField);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(482, 374, 78, 17);
		add(lblNombre);
		
		nombreTextField = new JTextField();
		nombreTextField.setColumns(10);
		nombreTextField.setBounds(482, 401, 219, 26);
		add(nombreTextField);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(482, 450, 78, 17);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(482, 480, 219, 26);
		add(passwordField);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = dniTextField.getText();
				String nombre = nombreTextField.getText();
				String password = new String(passwordField.getPassword());
				
				if (dni.length() != 9) {
					JOptionPane.showMessageDialog(null, "Introduce un DNI válido");
					return;
				}
				
				if (controller.existeDni(dni)) {
					JOptionPane.showMessageDialog(null, "Este DNI ya esta en uso");
					return;
				}
				else {

					if (nombre.isEmpty() ||  password.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Rellena todos los campos.");
						return;
					}
	
					LocalDate fechaActual = LocalDate.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String fecha = fechaActual.format(formatter);
	
					boolean registrado = controller.registrarUsuario(dni,nombre,password, fecha, 0);
					
					System.out.println(registrado?"si":"no");
					
					if (registrado) {
						JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
						cardLayout.show(parentPanel, "loginWindow");
					} else {
						JOptionPane.showMessageDialog(null, "No se ha podido registrar al usuario.");
					}
				}
			}
		});
		btnRegistrarse.setBounds(482, 550, 219, 29);
		add(btnRegistrarse);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(parentPanel, "home");
			}
		});
		btnVolver.setBounds(12, 13, 97, 25);
		add(btnVolver);

	}
}
