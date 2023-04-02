package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setSize(1200, 800);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1186, 763);
		contentPane.add(layeredPane);
		
		JButton btnSalir_1 = new JButton("Salir");
		btnSalir_1.setBounds(497, 517, 234, 42);
		layeredPane.add(btnSalir_1);
		
		JButton btnFaq_1 = new JButton("FAQ");
		btnFaq_1.setBounds(497, 421, 234, 42);
		layeredPane.add(btnFaq_1);
		
		JLabel background_1 = new JLabel("Fondo");
		background_1.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Fondo.jpg")));
		background_1.setBounds(0, 0, 1200, 800);
		layeredPane.add(background_1);
		
		JButton btnNewButton_1 = new JButton("Iniciar Sesion");
		layeredPane.setLayer(btnNewButton_1, 1);
		btnNewButton_1.setBounds(497, 331, 234, 42);
		layeredPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Logo");
		lblNewLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Logo.png")));
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setBounds(319, 38, 588, 207);
		layeredPane.add(lblNewLabel);

	}
}
