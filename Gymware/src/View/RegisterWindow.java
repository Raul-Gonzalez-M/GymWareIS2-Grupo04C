import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindow extends JFrame {
    private JLabel nameLabel, lastNameLabel, emailLabel, passwordLabel, repeatPasswordLabel;
    private JTextField nameTextField, lastNameTextField, emailTextField;
    private JPasswordField passwordField, repeatPasswordField;
    private JButton registerButton;
    private JPanel mainPanel, formPanel, buttonPanel;
    private Controller controller;

    public RegisterWindow(Controller controller) {
        this.controller = controller;
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Labels
        nameLabel = new JLabel("Nombre: ");
        lastNameLabel = new JLabel("Apellido: ");
        emailLabel = new JLabel("Correo electrónico: ");
        passwordLabel = new JLabel("Contraseña: ");
        repeatPasswordLabel = new JLabel("Repetir contraseña: ");

        // Text fields
        nameTextField = new JTextField();
        lastNameTextField = new JTextField();
        emailTextField = new JTextField();
        passwordField = new JPasswordField();
        repeatPasswordField = new JPasswordField();

        // Button
        registerButton = new JButton("Registrarse");

        // Panels
        formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel = new JPanel(new BorderLayout());

        // Form panel
        formPanel.add(nameLabel);
        formPanel.add(nameTextField);
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameTextField);
        formPanel.add(emailLabel);
        formPanel.add(emailTextField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(repeatPasswordLabel);
        formPanel.add(repeatPasswordField);

        // Button panel
        buttonPanel.add(registerButton);

        // Main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Registro de usuario");
        this.setContentPane(mainPanel);
        this.pack();

        // Register button listener
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String name = nameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String repeatPassword = String.valueOf(repeatPasswordField.getPassword());

        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!password.equals(repeatPassword)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean success = controller.registerUser(name, lastName, email, password);

            if (success) {
                JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error al registrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
