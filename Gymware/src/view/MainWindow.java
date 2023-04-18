package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.Controller;
import java.awt.CardLayout;


public class MainWindow extends JFrame {

	private JPanel contentPane;
	private CardLayout cardLayout;
	private JPanel homePanel;
	private JPanel loginPanel;
	private JPanel registerPanel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow(Controller vc) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(1200, 800);
		setContentPane(contentPane);
		cardLayout = new CardLayout(0, 0);
		contentPane.setLayout(cardLayout);
		
		homePanel = new HomePanel(contentPane);
		contentPane.add(homePanel, "home");
		
		loginPanel = new LoginWindow(vc, contentPane);
		contentPane.add(loginPanel, "login");
		
		registerPanel = new RegisterWindow(vc, contentPane);
		contentPane.add(registerPanel, "register");
	}


}
