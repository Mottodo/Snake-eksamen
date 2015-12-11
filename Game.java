import javax.swing.JPanel;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.Color;
import java.awt.List;
import java.awt.Canvas;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Game extends JPanel { // nedarver funktioner fra JPanel
	// initialisering af variabler
	private Main client;
	private JSONObject CurrentGame = null;
	private JTextField textField;
	private JLabel lblNewLabel;
	/**
	 * Create the panel.
	 */
	public Game(Main client, String GameName) {//kontrukt�r der siger at Game skal modtage og indholde en Main klasse der hedder client
		this.client = client;// s�tter client ind i this.client s� man er i stand til at tr�kke p� Main klassen
	
		JSONObject GameRequest = new JSONObject(); // opretter et JSONObject der hedder GameRequest og l�gger et nyt JSONObject i den
		
		
		try {
			GameRequest.put("Username", this.client.getCurrentUser()); // put inds�tter en n�gle (Username), med v�rdien fra this.client.CurrentUser, dvs. den nuv�rende bruger
			GameRequest.put("GameName", GameName);// put inds�tter en n�gle (GameName) med GameName som v�rdi ind i GameRequest
			GameRequest.put("Method", "GameInfo");//Put s�tter metoden "Method" med v�rdien "GameInfo" ind i GameRequest
			 
			JSONObject result = this.client.request(GameRequest); // opretter et JSONObject result og l�gger GameRequest ind i den 
			
			
			
			if (result != null && result.has("Result")) {// hvis result ikke er null og result har et Result s� skal koden forts�tte
				if (result.getBoolean("Result")) { // hvis Result er true s� forts�tter koden
					this.CurrentGame = result.getJSONObject("GameInfo"); //S�tter GameInfo ind i CurrentGame
				}
				
			}
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (this.CurrentGame == null) { // hvis CurrenGame er det samme som nul k�re if'en
			
			this.client.changePage(new GamePage(this.client));// skifter side til GamePage
			
		}
		
		
		setBackground(new Color(153, 255, 102)); // s�tter bagrunds farven
		setLayout(null);// s�tter layout til null
		
		lblNewLabel = new JLabel(""); // laver en ny JLabel der indholder en tom string
		lblNewLabel.setBounds(68, 122, 230, 127);// s�tter gr�nserne 
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.black));// s�tter border til sort
		add(lblNewLabel);// tilf�jer den nye JLabel
		
		
	
		
		JLabel lblNewLabel_1 = new JLabel("Logout"); //laver en ny JLabel hvor der st�r Logout i den
		lblNewLabel_1.addMouseListener(new MouseAdapter() {// tilf�jer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) {// laver en mouseCliked funktion e
				Game This = (Game) (e.getComponent().getParent()); // Opretter en variabel af typen Game og s�tter Game's v�rdier ind i This for at f� adgang til de forskellige elementer p� siden
				
				This.client.changePage(new Gui(This.client)); // skifter side til Gui siden (Login siden)
			}
		});
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 11)); //s�tter skriftstilen og st�rrelsen
		lblNewLabel_1.setBounds(378, 237, 46, 14);// s�tter gr�nserne
		add(lblNewLabel_1);// tilf�jer den nye JLabel
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Back"); // opretter et ny JLabel der st�r back i
		lblNewLabel_2.addMouseListener(new MouseAdapter() {// tilf�jer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) {// laver en mouseCliked funktion e
				Game This = (Game) (e.getComponent().getParent());// Opretter en variabel af typen Game og s�tter Game's v�rdier ind i This for at f� adgang til de forskellige elementer p� siden
				
				This.client.changePage(new SnakeMenu(This.client)); // skifter side til SnakeMenu
			}
		});
		lblNewLabel_2.setFont(new Font("Consolas", Font.PLAIN, 11)); // s�tter skriftstilen og st�relsen 
		lblNewLabel_2.setBounds(20, 237, 46, 14);// s�tter gr�nserne
		add(lblNewLabel_2);// tilf�jer den nye JLabel
		
		
		JLabel lblSnake = new JLabel("Snake"); //opretter en ny JLabel hvor der st�r Snake i
		lblSnake.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));// s�tter skriftstilen og st�rrelsen
		lblSnake.setBounds(189, 11, 46, 14);//s�tter gr�nserne
		add(lblSnake);// tilf�jer det nye JLabel
		
		
		try {
			
			JLabel lblNewLabel_5; // Opretter et ny JLabel
			
			lblNewLabel_5 = new JLabel(CurrentGame.getString("Player1") + ": Score " + CurrentGame.getInt("Player1Score"));// viser Player1's navn og hans tilh�rende score
			
			lblNewLabel_5.setFont(new Font("Consolas", Font.PLAIN, 10));// s�tter skriftsstilen og st�rrelsen
			lblNewLabel_5.setBounds(287, 11, 153, 14);// s�tter gr�nserne
			add(lblNewLabel_5);// tilf�jer det nye JLabel
			
			
			if (CurrentGame.has("Player2")) { // hvis det nuv�rende spil har en Player2 skal if'en k�re
			
				JLabel lblNewLabel_4 = new JLabel(CurrentGame.getString("Player2") + ": Score " + CurrentGame.getInt("Player2Score")); // opretter et nyt JLabel og l�gger Player2 og hans score ind i JLablet'et og viser v�rdierne
				lblNewLabel_4.setFont(new Font("Consolas", Font.PLAIN, 11)); // s�tter skriftstilen og st�rrelsen
				lblNewLabel_4.setBounds(287, 38, 153, 14);// s�tter gr�nserne
				add(lblNewLabel_4);// tilf�jer det nye JLabel
			
			}
			
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		JLabel lblTest; // opretter et JLabel
		try {
			lblTest = new JLabel(CurrentGame.getString( "GameName")); // l�gger GameName ind i JLabel og viser spillet's navn
			
			lblTest.setBounds(68, 11, 74, 14); // s�tter gr�nserne
			add(lblTest);// tilf�jer den nye JLabel
			
			String ButtonText = "Leave Game"; // opretter ButtonText der indeholder en stringen Leave Game
			
			
			
			if (this.client.getCurrentUser().equals(this.CurrentGame.getString("Player1"))){ // hvis den nuv�rende bruger er lig med Player1 s� k�re if'en
				
				
				ButtonText = "Delete Game"; // buttonText s�ttes til Delete Game
				
			}
			
			
			JLabel lblNewLabel_3 = new JLabel(ButtonText); //Opretter en ny JLabel som indeholder ButtonText
			lblNewLabel_3.addMouseListener(new MouseAdapter() {// tilf�jer mouse event handler
				@Override
				
				public void mouseClicked(MouseEvent e) {// laver en mouseCliked funktion e
					Game This = ( Game) (e.getComponent().getParent());// Opretter en variabel af typen Game og s�tter Game's v�rdier ind i This for at f� adgang til de forskellige elementer p� siden
					
					JSONObject DeleteGame = new JSONObject(); // opretter et nyt JSONObject DeleteGame som indeholde et nyt JSONObject
					
					
					try {

						String Method = "LeaveGame";// oprtter en string der hedder Method som indeholder "LeaveGame"
						
						if (This.client.getCurrentUser().equals(This.CurrentGame.getString("Player1"))){ // Hvis den nuv�rendebruger er lig med Player1 k�re if'en
							
							
							Method = "DeleteGame"; // Method s�ttes til DeleteGame
						}

						
						DeleteGame.put("GameName", This.CurrentGame.get("GameName"));// put inds�tter en n�gle (GameName), med v�rdien fra this.CurrentGame.get("GameName), dvs. det nuv�rende game navn ind i DeleteGame
						DeleteGame.put("Username", This.client.getCurrentUser());// put inds�tter en n�gle (Username), med v�rdien fra this.client.CurrentUser, dvs. den nuv�rende bruger ind i DeleteGame
						DeleteGame.put("Method", Method);//Put s�tter metoden "Method" med v�rdien "Mehod" ind i DeleteGame
						 
						JSONObject result = This.client.request(DeleteGame); // opretter et JSONObject kaldet result og l�gger DeleteGame ind i den
						
						
						if (result != null && result.has("Result")) {// hvis result ikke er null og result har et Result s� skal koden forts�tte
							
							if (result.getBoolean("Result")) { // hvis Result er true s� k�re if'en
							
								This.client.changePage(new GamePage(This.client)); // skifter siden til GamePage
							}
							
						}
					
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 9)); // s�tter skrftstilen og st�rrelsen
			lblNewLabel_3.setBounds(367, 211, 56, 14); // s�tter gr�nserne
			add(lblNewLabel_3);// opretter det nye JLabel
			
			textField = new JTextField(); // opretter et ny JTextField
			textField.setBounds(68, 62, 230, 20); // s�tter gr�nserne
			add(textField);// tilf�jer det nye JText Field
			textField.setColumns(10);
			
			JButton btnNewButton = new JButton("Send styringshandlinger"); // opretter en ny JButton hvor der st�r Send styringshandlinger
			btnNewButton.addMouseListener(new MouseAdapter() {// tilf�jer mouse event handler
				@Override
				public void mouseClicked(MouseEvent e) {// laver en mouseCliked funktion e
					
					Game This = (Game) (e.getComponent().getParent()); // Opretter en variabel af typen Game og s�tter Game's v�rdier ind i This for at f� adgang til de forskellige elementer p� siden
					
					JSONObject UserInput = new JSONObject(); // opretter et nyt JSONObject kaldet UserInput og l�gger et ny JSON Object i den
					
					try {
						
						UserInput.put("Username", This.client.getCurrentUser());// put inds�tter en n�gle (UserName), med v�rdien fra this.client.getCurrentUser, dvs. det nuv�rende bruger l�gges ind i UserInput
						UserInput.put("GameName", This.CurrentGame.getString("GameName"));// put inds�tter en n�gle (GameName), med v�rdien fra this.CurrentGame.get("GameName), dvs. det nuv�rende game navn ind i UserInput
						UserInput.put("Method", "UserInput");//Put s�tter metoden "Method" med v�rdien "UserInput" ind i UserInput
						UserInput.put("UserInput", This.textField.getText()); // put inds�tter en n�gle UserInput med v�rdien fra This.textField.getText, dvs. texten fra textFielden puttes ind i UserInput
						
						JSONObject result = This.client.request(UserInput); // opretter et JSONObject kaldet result og l�gger UserInput ind i den
						
						if (result != null && result.has("Result")) { // hvis result ikke er null og result har et Result s� skal koden forts�tte
							
							int State = result.getInt("Result"); // opretter en int der kaldes state som Result l�gges i 
							
							if (State != -1) { // hvis State er alt andet end -1 k�re koden 
								int Player = 1; // opretter int kaldet player som s�ttes til 1
								
								if (This.CurrentGame.getString("Player2").equals(This.client.getCurrentUser())) { // hvis Player2 er det samme som den nuv�rende bruger k�re if'en
									
									Player = 2; // player s�ttes til 2
								}
								
								String Text; // opretter String der hedder Text
								
								if (State == Player) { // hvis State er det samme som Player k�re if'en
									
									Text = "You are the winner"; // text s�ttes til "You are the winner"
								}
								else { // ellers k�re else'en
									
									Text = "You Lost :("; // Text s�ttes til You Lost
								}
								
								if (State == 0) { // hvis State er det samme som 0 k�re if'en
									
									Text = "<html>No one has died yet,<br>  so the game continues</html>";// Text s�ttes til <html>No one has died yet,<br>  so the game continues</html> 
								}
								
								if (State == -2) { // hvis State er det samme som -2 k�re if'en
									
									Text = "Waiting for the other player"; // Text s�ttes til Waiting for the other player
								}
								
								This.lblNewLabel.setText(Text); // lblNewLabel s�ttes til at indeholde Text
							}
						}
						
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnNewButton.setBounds(68, 93, 230, 23); // s�tter gr�nserne
			add(btnNewButton); // tilf�jer den nye Button
			
			
			
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}
}
