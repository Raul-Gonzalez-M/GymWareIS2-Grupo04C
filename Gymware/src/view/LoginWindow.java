package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;

import controller.Controller;
import controller.VistaController;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JPanel {

	private JTextField textField;
	private JPasswordField passwordField;
	private VistaController vistacontroller;

	public LoginWindow(VistaController vc) {
		
		this.vistacontroller = vc;
		
		setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(261, 230, 78, 17);
		add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(261, 257, 219, 26);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(261, 294, 78, 17);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(261, 321, 219, 26);
		add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = new String(passwordField.getPassword());
				
				if (vistacontroller.verificarCredenciales(username, password)) {
					// Si el inicio de sesión es exitoso, navegar a la siguiente vista
					// ...
				} else {
					JOptionPane.showMessageDialog(LoginWindow.this, "Usuario o contraseña incorrectos", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(261, 373, 85, 21);
		add(btnLogin);

	}
}
