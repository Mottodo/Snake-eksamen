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

public class Gui extends JPanel { // nedarver funktioner fra JPanel

	public JTextField textField; // initialisering af variabler
	private JLabel lblSnake;
	private JPasswordField passwordField;
	private Main client;
	
	/**
	 * Create the panel.
	 */
	public Gui(Main client) {//kontrukt�r der siger at Game skal modtage og indholde en Main klasse der hedder client
		
		this.client = client;// s�tter client ind i this.client s� man er i stand til at tr�kke p� Main klassen
		this.client.setCurrentUser(null);
		
		// s�tter farve og layout
		this.setForeground(new Color(0, 255, 255));
		this.setBackground(new Color(153, 255, 102));
		this.setLayout(null);
		
		//tilf�jer de forskellige Gui elementer og deres indhold stil og st�rrelse
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
		btnLogin.addMouseListener(new MouseAdapter() { // tilf�jer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) { // laver en mouseCliked funktion e
				Gui This = (Gui) (e.getComponent().getParent()); // Opretter en variabel af typen Gui og putter Gui siden ind i This
				
				
				JSONObject login = new JSONObject(); // opretter et nyt JSONObject variabel login, som indeholder et nyt JSONObject
				
				try {
					login.put("Username", This.textField.getText()); // put inds�tter en n�gle (Username) med v�rdien fra This.textField.getText() 
					login.put("Password", new String(This.passwordField.getPassword())); // put inds�tter en n�gle (Username) med v�rdien fra This.passwordField.getPassword
					login.put("Method", "Login"); // Put s�tter medtoden Method med v�rdien "Login" ind i login
					 
					JSONObject result = This.client.request(login); // opretter et nyt JSONObject result og l�gger login derind
					
					
					
					if (result != null && result.has("Result")) { // hvis result ikke er null og result har et Result s� skal koden forts�tter
						
						if (result.getBoolean("Result")) { // hvis Result er true s� fors�t
							
							
							This.client.setCurrentUser(result.getString("Username")); // s�tter "Username" til at v�re CurrentUser via setCurentUser funktionen
							This.client.changePage(new SnakeMenu(This.client)); // skifter side til SnakeMenu
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
