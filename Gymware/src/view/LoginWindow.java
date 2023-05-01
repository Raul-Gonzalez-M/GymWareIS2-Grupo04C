package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JTextField;

import controller.GymController;
import controller.Controller;
import model.Cliente;
import model.Personal;
import model.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginWindow extends JPanel {

	private JTextField textField;
	private JPasswordField passwordField;
	private JPanel parentPanel;
	private CardLayout cardLayout;
	private Controller controller;
	
	public LoginWindow(Controller controller, JPanel pp) {
		this.controller = controller;
		this.parentPanel = pp;
        this.cardLayout = (CardLayout) parentPanel.getLayout();
		setLayout(null);
		inicializar();
	}
	
	private void inicializar() {
		
		JLabel lblUsername = new JLabel("DNI");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(482, 310, 78, 17);
		add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(482, 337, 219, 26);
		add(textField);
		textField.setColumns(10);
		setSize(1200, 800);
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(482, 374, 78, 17);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(482, 401, 219, 26);
		add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String DNI = textField.getText();
		        String password = new String(passwordField.getPassword());
				Usuario usuarioActual = null;
				if(controller.verificarCredenciales(DNI, password)){
					usuarioActual = controller.obtenerUsuarioPorDNI(DNI);
				}
		        if(usuarioActual != null) {
		        	controller.setUsuario(usuarioActual);
		        	usuarioActual.setActividades(controller.obtenerActividadPorDNI(usuarioActual.getDNI()));
		            JOptionPane.showMessageDialog(LoginWindow.this, "¡Bienvenido " + usuarioActual.getNombre() + "!", "Inicio de sesión exitoso", JOptionPane.INFORMATION_MESSAGE);
		            if (usuarioActual instanceof Cliente) {
		            	MenuPrincipalCliente menuCliente;
						try {
							menuCliente = new MenuPrincipalCliente(controller, usuarioActual,parentPanel);
						} catch (SQLException e1) {
							menuCliente = null;
							e1.printStackTrace();
						}
		            	parentPanel.add(menuCliente, "menuCliente");
		                cardLayout.show(parentPanel, "menuCliente");
		            } else if (usuarioActual instanceof Personal) {
		            	 MenuPrincipalPersonal menuPersonal = new MenuPrincipalPersonal(controller, usuarioActual);
		            	 parentPanel.add(menuPersonal, "menuPersonal");
		            	 cardLayout.show(parentPanel, "menuPersonal");
		            } else {
		                JOptionPane.showMessageDialog(LoginWindow.this, "Error al obtener el tipo de usuario", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(LoginWindow.this, "Usuario o contraseña incorrectos", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		btnLogin.setBounds(542, 453, 85, 21);
		add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(547, 209, 91, 45);
		add(lblNewLabel);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(parentPanel, "home");
			}
		});
		btnVolver.setBounds(542, 641, 85, 21);
		add(btnVolver);

	}
}
