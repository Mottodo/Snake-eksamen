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
	public Game(Main client, String GameName) {//kontruktør der siger at Game skal modtage og indholde en Main klasse der hedder client
		this.client = client;// sætter client ind i this.client så man er i stand til at trække på Main klassen
	
		JSONObject GameRequest = new JSONObject(); // opretter et JSONObject der hedder GameRequest og lægger et nyt JSONObject i den
		
		
		try {
			GameRequest.put("Username", this.client.getCurrentUser()); // put indsætter en nøgle (Username), med værdien fra this.client.CurrentUser, dvs. den nuværende bruger
			GameRequest.put("GameName", GameName);// put indsætter en nøgle (GameName) med GameName som værdi ind i GameRequest
			GameRequest.put("Method", "GameInfo");//Put sætter metoden "Method" med værdien "GameInfo" ind i GameRequest
			 
			JSONObject result = this.client.request(GameRequest); // opretter et JSONObject result og lægger GameRequest ind i den 
			
			
			
			if (result != null && result.has("Result")) {// hvis result ikke er null og result har et Result så skal koden fortsætte
				if (result.getBoolean("Result")) { // hvis Result er true så fortsætter koden
					this.CurrentGame = result.getJSONObject("GameInfo"); //Sætter GameInfo ind i CurrentGame
				}
				
			}
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (this.CurrentGame == null) { // hvis CurrenGame er det samme som nul køre if'en
			
			this.client.changePage(new GamePage(this.client));// skifter side til GamePage
			
		}
		
		
		setBackground(new Color(153, 255, 102)); // sætter bagrunds farven
		setLayout(null);// sætter layout til null
		
		lblNewLabel = new JLabel(""); // laver en ny JLabel der indholder en tom string
		lblNewLabel.setBounds(68, 122, 230, 127);// sætter grænserne 
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.black));// sætter border til sort
		add(lblNewLabel);// tilføjer den nye JLabel
		
		
	
		
		JLabel lblNewLabel_1 = new JLabel("Logout"); //laver en ny JLabel hvor der står Logout i den
		lblNewLabel_1.addMouseListener(new MouseAdapter() {// tilføjer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) {// laver en mouseCliked funktion e
				Game This = (Game) (e.getComponent().getParent()); // Opretter en variabel af typen Game og sætter Game's værdier ind i This for at få adgang til de forskellige elementer på siden
				
				This.client.changePage(new Gui(This.client)); // skifter side til Gui siden (Login siden)
			}
		});
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 11)); //sætter skriftstilen og størrelsen
		lblNewLabel_1.setBounds(378, 237, 46, 14);// sætter grænserne
		add(lblNewLabel_1);// tilføjer den nye JLabel
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Back"); // opretter et ny JLabel der står back i
		lblNewLabel_2.addMouseListener(new MouseAdapter() {// tilføjer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) {// laver en mouseCliked funktion e
				Game This = (Game) (e.getComponent().getParent());// Opretter en variabel af typen Game og sætter Game's værdier ind i This for at få adgang til de forskellige elementer på siden
				
				This.client.changePage(new SnakeMenu(This.client)); // skifter side til SnakeMenu
			}
		});
		lblNewLabel_2.setFont(new Font("Consolas", Font.PLAIN, 11)); // sætter skriftstilen og størelsen 
		lblNewLabel_2.setBounds(20, 237, 46, 14);// sætter grænserne
		add(lblNewLabel_2);// tilføjer den nye JLabel
		
		
		JLabel lblSnake = new JLabel("Snake"); //opretter en ny JLabel hvor der står Snake i
		lblSnake.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));// sætter skriftstilen og størrelsen
		lblSnake.setBounds(189, 11, 46, 14);//sætter grænserne
		add(lblSnake);// tilføjer det nye JLabel
		
		
		try {
			
			JLabel lblNewLabel_5; // Opretter et ny JLabel
			
			lblNewLabel_5 = new JLabel(CurrentGame.getString("Player1") + ": Score " + CurrentGame.getInt("Player1Score"));// viser Player1's navn og hans tilhørende score
			
			lblNewLabel_5.setFont(new Font("Consolas", Font.PLAIN, 10));// sætter skriftsstilen og størrelsen
			lblNewLabel_5.setBounds(287, 11, 153, 14);// sætter grænserne
			add(lblNewLabel_5);// tilføjer det nye JLabel
			
			
			if (CurrentGame.has("Player2")) { // hvis det nuværende spil har en Player2 skal if'en køre
			
				JLabel lblNewLabel_4 = new JLabel(CurrentGame.getString("Player2") + ": Score " + CurrentGame.getInt("Player2Score")); // opretter et nyt JLabel og lægger Player2 og hans score ind i JLablet'et og viser værdierne
				lblNewLabel_4.setFont(new Font("Consolas", Font.PLAIN, 11)); // sætter skriftstilen og størrelsen
				lblNewLabel_4.setBounds(287, 38, 153, 14);// sætter grænserne
				add(lblNewLabel_4);// tilføjer det nye JLabel
			
			}
			
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		JLabel lblTest; // opretter et JLabel
		try {
			lblTest = new JLabel(CurrentGame.getString( "GameName")); // lægger GameName ind i JLabel og viser spillet's navn
			
			lblTest.setBounds(68, 11, 74, 14); // sætter grænserne
			add(lblTest);// tilføjer den nye JLabel
			
			String ButtonText = "Leave Game"; // opretter ButtonText der indeholder en stringen Leave Game
			
			
			
			if (this.client.getCurrentUser().equals(this.CurrentGame.getString("Player1"))){ // hvis den nuværende bruger er lig med Player1 så køre if'en
				
				
				ButtonText = "Delete Game"; // buttonText sættes til Delete Game
				
			}
			
			
			JLabel lblNewLabel_3 = new JLabel(ButtonText); //Opretter en ny JLabel som indeholder ButtonText
			lblNewLabel_3.addMouseListener(new MouseAdapter() {// tilføjer mouse event handler
				@Override
				
				public void mouseClicked(MouseEvent e) {// laver en mouseCliked funktion e
					Game This = ( Game) (e.getComponent().getParent());// Opretter en variabel af typen Game og sætter Game's værdier ind i This for at få adgang til de forskellige elementer på siden
					
					JSONObject DeleteGame = new JSONObject(); // opretter et nyt JSONObject DeleteGame som indeholde et nyt JSONObject
					
					
					try {

						String Method = "LeaveGame";// oprtter en string der hedder Method som indeholder "LeaveGame"
						
						if (This.client.getCurrentUser().equals(This.CurrentGame.getString("Player1"))){ // Hvis den nuværendebruger er lig med Player1 køre if'en
							
							
							Method = "DeleteGame"; // Method sættes til DeleteGame
						}

						
						DeleteGame.put("GameName", This.CurrentGame.get("GameName"));// put indsætter en nøgle (GameName), med værdien fra this.CurrentGame.get("GameName), dvs. det nuværende game navn ind i DeleteGame
						DeleteGame.put("Username", This.client.getCurrentUser());// put indsætter en nøgle (Username), med værdien fra this.client.CurrentUser, dvs. den nuværende bruger ind i DeleteGame
						DeleteGame.put("Method", Method);//Put sætter metoden "Method" med værdien "Mehod" ind i DeleteGame
						 
						JSONObject result = This.client.request(DeleteGame); // opretter et JSONObject kaldet result og lægger DeleteGame ind i den
						
						
						if (result != null && result.has("Result")) {// hvis result ikke er null og result har et Result så skal koden fortsætte
							
							if (result.getBoolean("Result")) { // hvis Result er true så køre if'en
							
								This.client.changePage(new GamePage(This.client)); // skifter siden til GamePage
							}
							
						}
					
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 9)); // sætter skrftstilen og størrelsen
			lblNewLabel_3.setBounds(367, 211, 56, 14); // sætter grænserne
			add(lblNewLabel_3);// opretter det nye JLabel
			
			textField = new JTextField(); // opretter et ny JTextField
			textField.setBounds(68, 62, 230, 20); // sætter grænserne
			add(textField);// tilføjer det nye JText Field
			textField.setColumns(10);
			
			JButton btnNewButton = new JButton("Send styringshandlinger"); // opretter en ny JButton hvor der står Send styringshandlinger
			btnNewButton.addMouseListener(new MouseAdapter() {// tilføjer mouse event handler
				@Override
				public void mouseClicked(MouseEvent e) {// laver en mouseCliked funktion e
					
					Game This = (Game) (e.getComponent().getParent()); // Opretter en variabel af typen Game og sætter Game's værdier ind i This for at få adgang til de forskellige elementer på siden
					
					JSONObject UserInput = new JSONObject(); // opretter et nyt JSONObject kaldet UserInput og lægger et ny JSON Object i den
					
					try {
						
						UserInput.put("Username", This.client.getCurrentUser());// put indsætter en nøgle (UserName), med værdien fra this.client.getCurrentUser, dvs. det nuværende bruger lægges ind i UserInput
						UserInput.put("GameName", This.CurrentGame.getString("GameName"));// put indsætter en nøgle (GameName), med værdien fra this.CurrentGame.get("GameName), dvs. det nuværende game navn ind i UserInput
						UserInput.put("Method", "UserInput");//Put sætter metoden "Method" med værdien "UserInput" ind i UserInput
						UserInput.put("UserInput", This.textField.getText()); // put indsætter en nøgle UserInput med værdien fra This.textField.getText, dvs. texten fra textFielden puttes ind i UserInput
						
						JSONObject result = This.client.request(UserInput); // opretter et JSONObject kaldet result og lægger UserInput ind i den
						
						if (result != null && result.has("Result")) { // hvis result ikke er null og result har et Result så skal koden fortsætte
							
							int State = result.getInt("Result"); // opretter en int der kaldes state som Result lægges i 
							
							if (State != -1) { // hvis State er alt andet end -1 køre koden 
								int Player = 1; // opretter int kaldet player som sættes til 1
								
								if (This.CurrentGame.getString("Player2").equals(This.client.getCurrentUser())) { // hvis Player2 er det samme som den nuværende bruger køre if'en
									
									Player = 2; // player sættes til 2
								}
								
								String Text; // opretter String der hedder Text
								
								if (State == Player) { // hvis State er det samme som Player køre if'en
									
									Text = "You are the winner"; // text sættes til "You are the winner"
								}
								else { // ellers køre else'en
									
									Text = "You Lost :("; // Text sættes til You Lost
								}
								
								if (State == 0) { // hvis State er det samme som 0 køre if'en
									
									Text = "<html>No one has died yet,<br>  so the game continues</html>";// Text sættes til <html>No one has died yet,<br>  so the game continues</html> 
								}
								
								if (State == -2) { // hvis State er det samme som -2 køre if'en
									
									Text = "Waiting for the other player"; // Text sættes til Waiting for the other player
								}
								
								This.lblNewLabel.setText(Text); // lblNewLabel sættes til at indeholde Text
							}
						}
						
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnNewButton.setBounds(68, 93, 230, 23); // sætter grænserne
			add(btnNewButton); // tilføjer den nye Button
			
			
			
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}
}
