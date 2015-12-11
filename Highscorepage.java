import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HighscorePage extends JPanel { // nedarver funktioner fra JPanel

	private Main client;
	/**
	 * Create the panel.
	 */
	public HighscorePage(Main client) {//kontrukt�r der siger at HighscorePage skal modtage og indholde en Main klasse der hedder client
		
		this.client = client;// s�tter client ind i this.client s� man er i stand til at tr�kke p� Main klassen
		
		this.setBackground(new Color(153, 255, 102)); // s�tter baggrunds farven
		this.setLayout(null); // s�tter layouttet
		
		JLabel lblNewLabel = new JLabel("Logout"); // opretter en JLabel hvor der st�r "Logout" i
		lblNewLabel.addMouseListener(new MouseAdapter() {// tilf�jer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) {  // laver en mouseCliked funktion e
				HighscorePage This = (HighscorePage) (e.getComponent().getParent());  // Opretter en variabel af typen HighscorePage og putter HighscorePage's v�rdier ind i This
				
				This.client.changePage(new Gui(This.client));// skifter side til Gui siden (login siden) hvis man har trykket p� denne JLabel
			}
		});
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 11)); // s�tter skriftstil og st�rrelse
		lblNewLabel.setBounds(378, 237, 46, 14); // s�tter gr�nserne
		this.add(lblNewLabel); // tilf�jer elementet
		
		JLabel lblNewLabel_1 = new JLabel("Snake"); // opretter ny JLabel hvor der st�r "Snake" i
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14)); // s�tter skriftstil og st�rrelse
		lblNewLabel_1.setBounds(189, 11, 46, 14); // s�tter gr�nserne
		this.add(lblNewLabel_1); // tilf�jer det nye JLabel
		
		JLabel lblNewLabel_2 = new JLabel("My Highscore"); // opretter nyt JLabel hvor der st�r "My Highscore"
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 14)); // s�tter skrifstil og st�rrelse
		lblNewLabel_2.setBounds(63, 70, 100, 14); // s�tter gr�nserne
		this.add(lblNewLabel_2); // tilf�jer det nye JLabel
		
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Global Highscore"); // opretter nyt JLabel hvor der st�r " Global Highscore"
		lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 14)); // s�tter skriftstil og st�rrelse
		lblNewLabel_3.setBounds(260, 70, 375, 14); // s�tter gr�nserne
		this.add(lblNewLabel_3); // tilf�jer det nye JLabel
		
		
		JLabel lblBack = new JLabel("Back"); // opretter ny JLabel hvor der st�r "Back"
		lblBack.addMouseListener(new MouseAdapter() { // tilf�jer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) {  // laver en mouseCliked funktion e
				HighscorePage This = (HighscorePage) (e.getComponent().getParent()); // Opretter en variabel af typen HighscorePage og putter HighscorePage's v�rdier ind i This
				
				This.client.changePage(new SnakeMenu(This.client)); // skifter side til SnakeMenu siden hvis man har trykket p� denne JLabel
			}
		
		});
		lblBack.setFont(new Font("Consolas", Font.BOLD, 11)); // s�tter skriftstil og st�rrelse
		lblBack.setBounds(20, 236, 46, 14); // s�tter gr�nserne
		add(lblBack); // tilf�jer det nye JLabel
		
		JSONObject UserHighscore = new JSONObject();// opretter JSONObjectet UserHighscore og putter et ny JSONObject ind i den 
		int Highscore = -1; // opretter en variabel int Highscore og s�tter den til minus 1, som en midlertidig v�rdi
		
		try {
			UserHighscore.put("Username", this.client.getCurrentUser()); // put inds�tter en n�gle (Username) med this.client.getCurrentUser som v�rdi (den nuv�rende bruger) 
			UserHighscore.put("Method", "UserHighscore"); //Put s�tter metoden "Method" med v�rdien "UserHighscore" ind i UserHighscore
			 
			JSONObject result = this.client.request(UserHighscore); // opretter et JSONObject med navnet result og l�gger UserHighscore derind
			
			
			
			if (result != null && result.has("Result")) { // hvis result ikke er null og result har et Result s� skal koden forts�tter
				
				Highscore = result.getInt("Result"); // l�gger "Result" i variablen Highscore 
				
			}
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel label = new JLabel(Integer.toString(Highscore)); // opretter et nyt JLabel som viser Highscore som en string
		label.setFont(new Font("Consolas", Font.BOLD, 14)); // s�tter skriftstil og st�relse
		label.setBounds(89, 120, 46, 14); // s�tter gr�nserne
		add(label); // tilf�jer det nye JLabel
		
		JSONObject GlobalHighscore = new JSONObject(); // opretter JSONObjectet GlobalHighscore og putter et ny JSONObject ind i den 
		Highscore = -1; // s�tter variablen Highscore og s�tter den til minus 1, som en midlertidig v�rdi
		
		try {
			GlobalHighscore.put("Method", "GlobalHighscore");  //Put s�tter metoden "Method" med v�rdien "GlobalHighscore" ind i GlobalHighscore
			 
			JSONObject result = this.client.request(GlobalHighscore); // opretter et JSONObject med navnet result og l�gger GlobalHighscore derind
			
			
			
			if (result != null && result.has("Result")) { // hvis result ikke er null og result har et Result s� skal koden forts�tter
				
				Highscore = result.getInt("Result"); // l�gger "Result" i variablen Highscore 
				
			}
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		JLabel label_1 = new JLabel(Integer.toString(Highscore)); // opretter et ny JLabel og viser "Highscore" i den som en string
		label_1.setFont(new Font("Consolas", Font.BOLD, 14)); // s�tter skriftstil og st�rrelse
		label_1.setBounds(300, 120, 46, 14); // s�tter gr�nserne
		add(label_1); // tilf�jer det nye JLabel


	}

}
