package view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GymController;

import javax.swing.ImageIcon;

public class HomePanel extends JPanel {

    private CardLayout cardLayout;

    
    public HomePanel(JPanel parentPanel) {
        this.cardLayout = (CardLayout) parentPanel.getLayout();
        setLayout(null);
		setSize(1200, 800);
        
        JButton btn_registrarse = new JButton("Registrarse");
        btn_registrarse.setBounds(489, 373, 256, 34);
        add(btn_registrarse);
        
        btn_registrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(parentPanel, "register");
            }
        });
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(489, 531, 256, 34);
        add(btnSalir);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(HomePanel.class.getResource("/img/Logo.png")));
        lblLogo.setBounds(323, 10, 591, 191);
        add(lblLogo);
        
        JButton btn_faq = new JButton("FAQ");
        btn_faq.setBounds(489, 450, 256, 34);
        add(btn_faq);
        
        JButton btn_Ini_Sesion = new JButton("Iniciar Sesion");
        btn_Ini_Sesion.setBounds(489, 286, 256, 34);
        add(btn_Ini_Sesion);
        
        btn_Ini_Sesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(parentPanel, "login");
            }
        });

        JLabel Fondo = new JLabel("");
        Fondo.setIcon(new ImageIcon(HomePanel.class.getResource("/img/Fondo.jpg")));
        Fondo.setBounds(0, 0, 1180, 781);
        add(Fondo);
    }
}
