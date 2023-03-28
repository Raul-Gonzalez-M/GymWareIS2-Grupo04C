package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ModuleLayer.Controller;

public class LoginWindow extends JFrame {
    private Controller controller;

    private JPanel loginPanel;
    private JLabel userLabel;
    private JTextField userTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginWindow(Controller controller) {
        this.controller = controller;

        loginPanel = new JPanel(new GridLayout(3, 2));

        userLabel = new JLabel("Usuario:");
        userTextField = new JTextField();
        passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField();

        loginPanel.add(userLabel);
        loginPanel.add(userTextField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        loginButton = new JButton("Iniciar sesión");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userTextField.getText();
                String password = new String(passwordField.getPassword());

                if (controller.login(user, password)) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Usuario o contraseña incorrectos", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(loginPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
