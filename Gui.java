import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;

public class Gui {

	private JFrame frame;
	private JTextField textField;
	private JLabel lblSnake;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 255, 255));
		frame.getContentPane().setBackground(new Color(153, 255, 102));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Consolas", Font.BOLD, 14));
		lblUsername.setBounds(172, 62, 114, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel.setBounds(172, 140, 114, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(new Color(0, 255, 255));
		textField.setBackground(new Color(0, 0, 0));
		textField.setBounds(114, 89, 184, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblSnake = new JLabel("Snake");
		lblSnake.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
		lblSnake.setBounds(177, 27, 46, 14);
		frame.getContentPane().add(lblSnake);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(0, 255, 255));
		passwordField.setBackground(new Color(0, 0, 0));
		passwordField.setBounds(114, 163, 184, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Consolas", Font.BOLD, 14));
		btnLogin.setBounds(162, 207, 89, 23);
		frame.getContentPane().add(btnLogin);
	}
}
