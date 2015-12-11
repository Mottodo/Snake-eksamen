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
	public GamePage(Main client) { //kontruktør der siger at GamePage skal modtage og indholde en Main klasse der hedder client
		
		this.client = client; // sætter client ind i this.client så man er i stand til at trække på Main klassen
		
		class JListItem{ // opretter en midlertidig klasse JListItem
			
			public int Highscore; // opretter offentlig variabel int Highscore
			public String GameName; // opretter offentlig variabel String Gamename
			
			public JListItem(int Highscore, String GameName) {  // opretter en kontruktør der modtager Highscore og GameName når JListItem oprettes
		
				this.Highscore = Highscore; // sætter Highscore ind i variablen this.Highscore
				this.GameName = GameName; // sætter GameName ind i variablen this.GameName
				
			}
			public String toString() { // funktion der returnere GameName, GameScore og Highscore som strings
				
				return this.GameName + ":" + "     GameScore " + this.Highscore;
				
		}
		
		
		}
		
		this.setBackground(new Color(153, 255, 102)); // sætter baggrundsfarve
		
		
		this.setLayout(null); // sætter layout til null
		
		JLabel lblNewLabel = new JLabel("Snake"); //opretter ny JLabel hvor der står "Snake" i
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14)); // sætter skriftstil og størrelse
		lblNewLabel.setBounds(189, 11, 46, 14); // sætter grænserne
		this.add(lblNewLabel);// tilføjer det nye JLabel
		
		textField = new JTextField(); // opretter et nyt JTextField
		textField.setBounds(79, 126, 96, 20); // sætter grænserne
		this.add(textField); // tilføjer det nye JTextField
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Create game");// opretter en ny Jbutton hvor der står "Create Game " i
		btnNewButton.addMouseListener(new MouseAdapter() {// tilføjer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) { // laver en mouseCliked funktion e
				GamePage This = (GamePage) (e.getComponent().getParent());  // Opretter en variabel af typen GamePage og putter GamePage værdier ind i This
				
				
				JSONObject CreateGame = new JSONObject(); // opretter et ny JSONObject CreateGame og lægger et nyt JSONObject i
				
				try {
					CreateGame.put("GameName", This.textField.getText());// put indsætter en nøgle (GameName) med this.text.Field.getText som værdi (det spil navn brugeren har indtastet) 
					CreateGame.put("Username", This.client.getCurrentUser());// put indsætter en nøgle (Username) med this.client.getCurrentUser som værdi (den nuværende bruger) 
					CreateGame.put("Method", "CreateGame"); //Put sætter metoden "Method" med værdien "CreateGame" ind i CreateGame
					 
					JSONObject result = This.client.request(CreateGame); // opretter et JSONObject med navnet result og lægger CreateGame derind
					
					
					
					if (result != null && result.has("Result")) {  // hvis result ikke er null og result har et Result så skal koden fortsætter
						
						if (result.getBoolean("Result")) { // hvis Result er true skal koden køre
							This.client.changePage(new Game(This.client, This.textField.getText())); // skifter til Game siden hvis det lykkes at oprette det nye spil
						}
						
					}
				
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton.setFont(new Font("Consolas", Font.BOLD, 9)); // sætter skrifstilen og størrelsen 
		btnNewButton.setBounds(79, 157, 99, 23); // sætter grænserne
		this.add(btnNewButton); // tilføjer den nye Jbutton
		
		
		JButton btnJoinGame = new JButton("Join Game"); // opretter den nye Jbutton hvor der står "Join Game" i den
		btnJoinGame.addMouseListener(new MouseAdapter() { // tilføjer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) { // laver en mouseCliked funktion e
				GamePage This = (GamePage) (e.getComponent().getParent()); // Opretter en variabel af typen GamePage og GamePage's værdier ind i This for at få adgang til de forskellige elementer på siden
				
				JSONObject JoinGame = new JSONObject(); // opretter et JsonObjet variabel kaldet JoinGame og lægger et nyt JSONObject ind i den
				JListItem Select = (JListItem) This.list.getSelectedValue(); // opretter en JListItem variabel Select og lægger den valgte værdi fra JListItem ind i den
				if (Select != null){
					
					
					try {
						
					
						
						JoinGame.put("GameName", Select.GameName); // put indsætter en nøgle (GameName) med den valgte værdi fra JListItem som værdi ind i JoinGame
						JoinGame.put("Username", This.client.getCurrentUser());// // put indsætter en nøgle (GameName) med this.client.getCurrentUser som værdi altså den nuværende bruger ind i JoinGame
						JoinGame.put("Method", "JoinGame");//Put sætter metoden "Method" med værdien "JoinGame" ind i JoinGame
						 
						JSONObject result = This.client.request(JoinGame); // opretter et JSONObject result og lægger JoinGame ind i den
						
						
						
						if (result != null && result.has("Result")) { // hvis result ikke er null og result har et Result så skal koden fortsætte
							
							boolean ThisResult = result.getBoolean("Result");// opretter en boolean ThisResult og lægger "Result" ind i den
							if (ThisResult){ // hvis ThisResult er true fortsætter koden
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
		btnJoinGame.setFont(new Font("Consolas", Font.BOLD, 10));// sætter skriftstilen og størrelsen
		btnJoinGame.setBounds(199, 157, 140, 23); // sætter grænserne
		this.add(btnJoinGame); // tilføjer den nye JButton
		
		JLabel lblNewLabel_1 = new JLabel("<html> Game name</html>"); // opretter den nye Jlabel hvor der står Game Name i den
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 11)); // sætter skriftstilen og størrelsen
		lblNewLabel_1.setBounds(79, 34, 96, 35); // sætter grænserne 
		this.add(lblNewLabel_1);// tilføjer den nye JLabel
		
		JLabel lblNewLabel_2 = new JLabel("Games"); // opretter den nye JLabel hvor der står "Games" i den
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);// center aligner teksten 
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 11));// sætter skriftstilen og størrelsen
		lblNewLabel_2.setBounds(219, 46, 99, 14); // sætter grænserne
		this.add(lblNewLabel_2); // tilføjer den nye JLabel

		
		
		JLabel lblLogout = new JLabel("Logout"); // opretter en ny JLabel hvor der står "Logout" i den
		lblLogout.addMouseListener(new MouseAdapter() {// tilføjer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) {// laver en mouseCliked funktion e
				GamePage This = (GamePage) (e.getComponent().getParent()); // Opretter en variabel af typen GamePage og sætter GamePage's værdier ind i This for at få adgang til de forskellige elementer på siden
				
				This.client.changePage(new Gui(This.client)); // skifter side til Gui siden(login siden)
				
			}
		});
		lblLogout.setFont(new Font("Consolas", Font.BOLD, 11)); // skriftstilen og størrelsen 
		lblLogout.setBounds(378, 237, 46, 14); // sætter grænserne
		this.add(lblLogout); // tiløjer den nye JLabel
		
		JLabel lblBack = new JLabel("Back"); // opretter den nye JLabel som der står "Back" i
		lblBack.addMouseListener(new MouseAdapter() { // tilføjer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) { // laver en mouseCliked funktion e
				GamePage This = (GamePage) (e.getComponent().getParent()); // Opretter en variabel af typen GamePage og sætter GamePage's værdier ind i This for at få adgang til de forskellige elementer på siden
				
				This.client.changePage(new SnakeMenu(This.client)); // skifter side til SnakeMenu siden
			}
		});
		lblBack.setFont(new Font("Consolas", Font.BOLD, 11)); //sætter skriftstilen og størrelsen
		lblBack.setBounds(20, 237, 46, 14);// sætter grænserne
		add(lblBack);// tilføjer det nye JLabel
		
		JSONObject AllGames = new JSONObject();// opretter en ny JSONObject variabel som hedder AllGames og lægger eet ny JSONObject ind i den
		JSONObject result;// opretter en JSONObject variabel der hedeer result
		JSONArray GameList = null; // opretter et JSONArray der hedder GameList og sætter den til null
		try {
			AllGames.put("Method", "ShowGames");// putter en key Method med værdien ShowGames ind i AllGames
			 
			result = this.client.request(AllGames);// putter AllGames ind i result
			
			
			
			if (result != null && result.has("Result")) {// hvis result ikke er null og result har et Result så skal koden fortsætte
				
				GameList = result.getJSONArray("Result");// putter Result ind i JSONArray'et Gamelist
				
			}
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		list = new JList(); // opretter en ny Jlist
		list.setBounds(199, 64, 157, 82);// sætter grænserne
		add(list);// tilføjer den nye JList
		
		
		if (GameList != null) {// hvis GameList ikke er lig null så fortsætter koden
			DefaultListModel listModel = new DefaultListModel();// opretter en DefaultListModel som hedder listModel og putter en ny DefaultListModel ind i den
			
			JSONObject CurrentGame; // opretter et JSONObject CurrentGame
			
			for (int i = 0; i< GameList.length(); i++) { // Laver et for loop og opretter int i som er sat til 0 og siger koden skal køre så længe GameList's længde er større end i's, for hvert loop stiger i med 1.
		
				
				try {
					CurrentGame = GameList.getJSONObject(i); // lægger JSONObjeectet ved i over i CurrentGame
					
					
					
					listModel.addElement(new JListItem(CurrentGame.getInt("Highscore"), CurrentGame.getString("Name"))); // Tilføjer det nuværendespils highscore og det nuværende spil navn.
				
					
					
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			list.setModel(listModel);// sætter listModel på list
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // gør at man kun kan vælge en værdi af gangen
			
			
			JScrollPane scrollPane_1 = new JScrollPane(list); // opretter en ny JScrollPane og lægger en ny JScrollPane i den der tilføre list
			scrollPane_1.setBounds(199, 64, 157, 82); // sætter grænserne
			this.add(scrollPane_1); // tilføjer den nye JScollPane
		}
		
		
		
		JButton btnNewButton_1 = new JButton("Refresh"); // opretter en ny Jbutton hvor der står Refresh 
		btnNewButton_1.addMouseListener(new MouseAdapter() {// tilføjer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) { // laver en mouseCliked funktion e
				GamePage This = (GamePage) (e.getComponent().getParent()); // Opretter en variabel af typen GamePage og sætter GamePage's værdier ind i This for at få adgang til de forskellige elementer på siden
				
				This.client.changePage(new GamePage(This.client)); // åbner GamePage igen hvilket opdatere siden
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Consolas", Font.PLAIN, 8)); // sætter skriftstilen og størrelsen
		btnNewButton_1.setBounds(359, 64, 67, 23); // sætter grænserne
		add(btnNewButton_1); // tilføjer den nye JButton

	}
}
