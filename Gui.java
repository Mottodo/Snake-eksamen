import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class Gui extends JPanel {

	public JTextField textField;
	private JLabel lblSnake;
	private JPasswordField passwordField;
	private Main client;
	
	/**
	 * Create the panel.
	 */
	public Gui(Main client) {
		
		this.client = client;
		this.client.setCurrentUser(null);
		
		
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
				
				
				JSONObject login = new JSONObject();
				
				try {
					login.put("Username", This.textField.getText());
					login.put("Password", new String(This.passwordField.getPassword()));
					login.put("Method", "Login");
					 
					JSONObject result = This.client.request(login);
					
					
					
					if (result != null && result.has("Result")) {
						
						if (result.getBoolean("Result")) {
							This.client.setCurrentUser(This.textField.getText());
							This.client.changePage(new SnakeMenu(This.client));
						}
						
					}
				
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
				
			}
		});
		btnLogin.setFont(new Font("Consolas", Font.BOLD, 14));
		btnLogin.setBounds(162, 207, 89, 23);
		this.add(btnLogin);
	

	}
	
}
