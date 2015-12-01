import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui extends JPanel {

	private JTextField textField;
	private JLabel lblSnake;
	private JPasswordField passwordField;
	private Main Client;
	
	/**
	 * Create the panel.
	 */
	public Gui(Main Client) {
		this.Client = Client;
		
		this.setForeground(new Color(0, 255, 255));
		this.setBackground(new Color(153, 255, 102));
		this.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Consolas", Font.BOLD, 14));
		lblUsername.setBounds(172, 62, 114, 14);
		this.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel.setBounds(172, 140, 114, 14);
		this.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(new Color(0, 255, 255));
		textField.setBackground(new Color(0, 0, 0));
		textField.setBounds(114, 89, 184, 20);
		this.add(textField);
		textField.setColumns(10);
		
		lblSnake = new JLabel("Snake");
		lblSnake.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
		lblSnake.setBounds(189, 11, 46, 14);
		this.add(lblSnake);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(0, 255, 255));
		passwordField.setBackground(new Color(0, 0, 0));
		passwordField.setBounds(114, 163, 184, 20);
		this.add(passwordField);
				
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Gui This = (Gui) (e.getComponent().getParent());
				
				This.Client.changePage(new SnakeMenu(This.Client));
			}
		});
		btnLogin.setFont(new Font("Consolas", Font.BOLD, 14));
		btnLogin.setBounds(162, 207, 89, 23);
		this.add(btnLogin);
	

	}
	
}
