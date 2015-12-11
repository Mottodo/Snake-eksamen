import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import java.awt.List;

public class GamePage extends JPanel { // nedarver funktioner for JPanel
	
	// initialisering af variabler
	private JTextField textField;
	private Main client;
	private JList list;
	/**
	 * Create the panel.
	 */
	public GamePage(Main client) { //kontrukt�r der siger at GamePage skal modtage og indholde en Main klasse der hedder client
		
		this.client = client; // s�tter client ind i this.client s� man er i stand til at tr�kke p� Main klassen
		
		class JListItem{ // opretter en midlertidig klasse JListItem
			
			public int Highscore; // opretter offentlig variabel int Highscore
			public String GameName; // opretter offentlig variabel String Gamename
			
			public JListItem(int Highscore, String GameName) {  // opretter en kontrukt�r der modtager Highscore og GameName n�r JListItem oprettes
		
				this.Highscore = Highscore; // s�tter Highscore ind i variablen this.Highscore
				this.GameName = GameName; // s�tter GameName ind i variablen this.GameName
				
			}
			public String toString() { // funktion der returnere GameName, GameScore og Highscore som strings
				
				return this.GameName + ":" + "     GameScore " + this.Highscore;
				
		}
		
		
		}
		
		this.setBackground(new Color(153, 255, 102)); // s�tter baggrundsfarve
		
		
		this.setLayout(null); // s�tter layout til null
		
		JLabel lblNewLabel = new JLabel("Snake"); //opretter ny JLabel hvor der st�r "Snake" i
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14)); // s�tter skriftstil og st�rrelse
		lblNewLabel.setBounds(189, 11, 46, 14); // s�tter gr�nserne
		this.add(lblNewLabel);// tilf�jer det nye JLabel
		
		textField = new JTextField(); // opretter et nyt JTextField
		textField.setBounds(79, 126, 96, 20); // s�tter gr�nserne
		this.add(textField); // tilf�jer det nye JTextField
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Create game");// opretter en ny Jbutton hvor der st�r "Create Game " i
		btnNewButton.addMouseListener(new MouseAdapter() {// tilf�jer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) { // laver en mouseCliked funktion e
				GamePage This = (GamePage) (e.getComponent().getParent());  // Opretter en variabel af typen GamePage og putter GamePage v�rdier ind i This
				
				
				JSONObject CreateGame = new JSONObject(); // opretter et ny JSONObject CreateGame og l�gger et nyt JSONObject i
				
				try {
					CreateGame.put("GameName", This.textField.getText());// put inds�tter en n�gle (GameName) med this.text.Field.getText som v�rdi (det spil navn brugeren har indtastet) 
					CreateGame.put("Username", This.client.getCurrentUser());// put inds�tter en n�gle (Username) med this.client.getCurrentUser som v�rdi (den nuv�rende bruger) 
					CreateGame.put("Method", "CreateGame"); //Put s�tter metoden "Method" med v�rdien "CreateGame" ind i CreateGame
					 
					JSONObject result = This.client.request(CreateGame); // opretter et JSONObject med navnet result og l�gger CreateGame derind
					
					
					
					if (result != null && result.has("Result")) {  // hvis result ikke er null og result har et Result s� skal koden forts�tter
						
						if (result.getBoolean("Result")) { // hvis Result er true skal koden k�re
							This.client.changePage(new Game(This.client, This.textField.getText())); // skifter til Game siden hvis det lykkes at oprette det nye spil
						}
						
					}
				
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton.setFont(new Font("Consolas", Font.BOLD, 9)); // s�tter skrifstilen og st�rrelsen 
		btnNewButton.setBounds(79, 157, 99, 23); // s�tter gr�nserne
		this.add(btnNewButton); // tilf�jer den nye Jbutton
		
		
		JButton btnJoinGame = new JButton("Join Game"); // opretter den nye Jbutton hvor der st�r "Join Game" i den
		btnJoinGame.addMouseListener(new MouseAdapter() { // tilf�jer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) { // laver en mouseCliked funktion e
				GamePage This = (GamePage) (e.getComponent().getParent()); // Opretter en variabel af typen GamePage og GamePage's v�rdier ind i This for at f� adgang til de forskellige elementer p� siden
				
				JSONObject JoinGame = new JSONObject(); // opretter et JsonObjet variabel kaldet JoinGame og l�gger et nyt JSONObject ind i den
				JListItem Select = (JListItem) This.list.getSelectedValue(); // opretter en JListItem variabel Select og l�gger den valgte v�rdi fra JListItem ind i den
				if (Select != null){
					
					
					try {
						
					
						
						JoinGame.put("GameName", Select.GameName); // put inds�tter en n�gle (GameName) med den valgte v�rdi fra JListItem som v�rdi ind i JoinGame
						JoinGame.put("Username", This.client.getCurrentUser());// // put inds�tter en n�gle (GameName) med this.client.getCurrentUser som v�rdi alts� den nuv�rende bruger ind i JoinGame
						JoinGame.put("Method", "JoinGame");//Put s�tter metoden "Method" med v�rdien "JoinGame" ind i JoinGame
						 
						JSONObject result = This.client.request(JoinGame); // opretter et JSONObject result og l�gger JoinGame ind i den
						
						
						
						if (result != null && result.has("Result")) { // hvis result ikke er null og result har et Result s� skal koden forts�tte
							
							boolean ThisResult = result.getBoolean("Result");// opretter en boolean ThisResult og l�gger "Result" ind i den
							if (ThisResult){ // hvis ThisResult er true forts�tter koden
								This.client.changePage(new Game(This.client, Select.GameName));// hvis det lykkes at tilslutte sig et spil skifter siden til Game siden
									
							}
						}
					
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnJoinGame.setFont(new Font("Consolas", Font.BOLD, 10));// s�tter skriftstilen og st�rrelsen
		btnJoinGame.setBounds(199, 157, 140, 23); // s�tter gr�nserne
		this.add(btnJoinGame); // tilf�jer den nye JButton
		
		JLabel lblNewLabel_1 = new JLabel("<html> Game name</html>"); // opretter den nye Jlabel hvor der st�r Game Name i den
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 11)); // s�tter skriftstilen og st�rrelsen
		lblNewLabel_1.setBounds(79, 34, 96, 35); // s�tter gr�nserne 
		this.add(lblNewLabel_1);// tilf�jer den nye JLabel
		
		JLabel lblNewLabel_2 = new JLabel("Games"); // opretter den nye JLabel hvor der st�r "Games" i den
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);// center aligner teksten 
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 11));// s�tter skriftstilen og st�rrelsen
		lblNewLabel_2.setBounds(219, 46, 99, 14); // s�tter gr�nserne
		this.add(lblNewLabel_2); // tilf�jer den nye JLabel

		
		
		JLabel lblLogout = new JLabel("Logout"); // opretter en ny JLabel hvor der st�r "Logout" i den
		lblLogout.addMouseListener(new MouseAdapter() {// tilf�jer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) {// laver en mouseCliked funktion e
				GamePage This = (GamePage) (e.getComponent().getParent()); // Opretter en variabel af typen GamePage og s�tter GamePage's v�rdier ind i This for at f� adgang til de forskellige elementer p� siden
				
				This.client.changePage(new Gui(This.client)); // skifter side til Gui siden(login siden)
				
			}
		});
		lblLogout.setFont(new Font("Consolas", Font.BOLD, 11)); // skriftstilen og st�rrelsen 
		lblLogout.setBounds(378, 237, 46, 14); // s�tter gr�nserne
		this.add(lblLogout); // til�jer den nye JLabel
		
		JLabel lblBack = new JLabel("Back"); // opretter den nye JLabel som der st�r "Back" i
		lblBack.addMouseListener(new MouseAdapter() { // tilf�jer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) { // laver en mouseCliked funktion e
				GamePage This = (GamePage) (e.getComponent().getParent()); // Opretter en variabel af typen GamePage og s�tter GamePage's v�rdier ind i This for at f� adgang til de forskellige elementer p� siden
				
				This.client.changePage(new SnakeMenu(This.client)); // skifter side til SnakeMenu siden
			}
		});
		lblBack.setFont(new Font("Consolas", Font.BOLD, 11)); //s�tter skriftstilen og st�rrelsen
		lblBack.setBounds(20, 237, 46, 14);// s�tter gr�nserne
		add(lblBack);// tilf�jer det nye JLabel
		
		JSONObject AllGames = new JSONObject();// opretter en ny JSONObject variabel som hedder AllGames og l�gger eet ny JSONObject ind i den
		JSONObject result;// opretter en JSONObject variabel der hedeer result
		JSONArray GameList = null; // opretter et JSONArray der hedder GameList og s�tter den til null
		try {
			AllGames.put("Method", "ShowGames");// putter en key Method med v�rdien ShowGames ind i AllGames
			 
			result = this.client.request(AllGames);// putter AllGames ind i result
			
			
			
			if (result != null && result.has("Result")) {// hvis result ikke er null og result har et Result s� skal koden forts�tte
				
				GameList = result.getJSONArray("Result");// putter Result ind i JSONArray'et Gamelist
				
			}
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		list = new JList(); // opretter en ny Jlist
		list.setBounds(199, 64, 157, 82);// s�tter gr�nserne
		add(list);// tilf�jer den nye JList
		
		
		if (GameList != null) {// hvis GameList ikke er lig null s� forts�tter koden
			DefaultListModel listModel = new DefaultListModel();// opretter en DefaultListModel som hedder listModel og putter en ny DefaultListModel ind i den
			
			JSONObject CurrentGame; // opretter et JSONObject CurrentGame
			
			for (int i = 0; i< GameList.length(); i++) { // Laver et for loop og opretter int i som er sat til 0 og siger koden skal k�re s� l�nge GameList's l�ngde er st�rre end i's, for hvert loop stiger i med 1.
		
				
				try {
					CurrentGame = GameList.getJSONObject(i); // l�gger JSONObjeectet ved i over i CurrentGame
					
					
					
					listModel.addElement(new JListItem(CurrentGame.getInt("Highscore"), CurrentGame.getString("Name"))); // Tilf�jer det nuv�rendespils highscore og det nuv�rende spil navn.
				
					
					
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			list.setModel(listModel);// s�tter listModel p� list
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // g�r at man kun kan v�lge en v�rdi af gangen
			
			
			JScrollPane scrollPane_1 = new JScrollPane(list); // opretter en ny JScrollPane og l�gger en ny JScrollPane i den der tilf�re list
			scrollPane_1.setBounds(199, 64, 157, 82); // s�tter gr�nserne
			this.add(scrollPane_1); // tilf�jer den nye JScollPane
		}
		
		
		
		JButton btnNewButton_1 = new JButton("Refresh"); // opretter en ny Jbutton hvor der st�r Refresh 
		btnNewButton_1.addMouseListener(new MouseAdapter() {// tilf�jer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) { // laver en mouseCliked funktion e
				GamePage This = (GamePage) (e.getComponent().getParent()); // Opretter en variabel af typen GamePage og s�tter GamePage's v�rdier ind i This for at f� adgang til de forskellige elementer p� siden
				
				This.client.changePage(new GamePage(This.client)); // �bner GamePage igen hvilket opdatere siden
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Consolas", Font.PLAIN, 8)); // s�tter skriftstilen og st�rrelsen
		btnNewButton_1.setBounds(359, 64, 67, 23); // s�tter gr�nserne
		add(btnNewButton_1); // tilf�jer den nye JButton

	}
}
