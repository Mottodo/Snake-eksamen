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
	public HighscorePage(Main client) {//kontruktør der siger at HighscorePage skal modtage og indholde en Main klasse der hedder client
		
		this.client = client;// sætter client ind i this.client så man er i stand til at trække på Main klassen
		
		this.setBackground(new Color(153, 255, 102)); // sætter baggrunds farven
		this.setLayout(null); // sætter layouttet
		
		JLabel lblNewLabel = new JLabel("Logout"); // opretter en JLabel hvor der står "Logout" i
		lblNewLabel.addMouseListener(new MouseAdapter() {// tilføjer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) {  // laver en mouseCliked funktion e
				HighscorePage This = (HighscorePage) (e.getComponent().getParent());  // Opretter en variabel af typen HighscorePage og putter HighscorePage's værdier ind i This
				
				This.client.changePage(new Gui(This.client));// skifter side til Gui siden (login siden) hvis man har trykket på denne JLabel
			}
		});
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 11)); // sætter skriftstil og størrelse
		lblNewLabel.setBounds(378, 237, 46, 14); // sætter grænserne
		this.add(lblNewLabel); // tilføjer elementet
		
		JLabel lblNewLabel_1 = new JLabel("Snake"); // opretter ny JLabel hvor der står "Snake" i
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14)); // sætter skriftstil og størrelse
		lblNewLabel_1.setBounds(189, 11, 46, 14); // sætter grænserne
		this.add(lblNewLabel_1); // tilføjer det nye JLabel
		
		JLabel lblNewLabel_2 = new JLabel("My Highscore"); // opretter nyt JLabel hvor der står "My Highscore"
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 14)); // sætter skrifstil og størrelse
		lblNewLabel_2.setBounds(63, 70, 100, 14); // sætter grænserne
		this.add(lblNewLabel_2); // tilføjer det nye JLabel
		
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Global Highscore"); // opretter nyt JLabel hvor der står " Global Highscore"
		lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 14)); // sætter skriftstil og størrelse
		lblNewLabel_3.setBounds(260, 70, 375, 14); // sætter grænserne
		this.add(lblNewLabel_3); // tilføjer det nye JLabel
		
		
		JLabel lblBack = new JLabel("Back"); // opretter ny JLabel hvor der står "Back"
		lblBack.addMouseListener(new MouseAdapter() { // tilføjer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) {  // laver en mouseCliked funktion e
				HighscorePage This = (HighscorePage) (e.getComponent().getParent()); // Opretter en variabel af typen HighscorePage og putter HighscorePage's værdier ind i This
				
				This.client.changePage(new SnakeMenu(This.client)); // skifter side til SnakeMenu siden hvis man har trykket på denne JLabel
			}
		
		});
		lblBack.setFont(new Font("Consolas", Font.BOLD, 11)); // sætter skriftstil og størrelse
		lblBack.setBounds(20, 236, 46, 14); // sætter grænserne
		add(lblBack); // tilføjer det nye JLabel
		
		JSONObject UserHighscore = new JSONObject();// opretter JSONObjectet UserHighscore og putter et ny JSONObject ind i den 
		int Highscore = -1; // opretter en variabel int Highscore og sætter den til minus 1, som en midlertidig værdi
		
		try {
			UserHighscore.put("Username", this.client.getCurrentUser()); // put indsætter en nøgle (Username) med this.client.getCurrentUser som værdi (den nuværende bruger) 
			UserHighscore.put("Method", "UserHighscore"); //Put sætter metoden "Method" med værdien "UserHighscore" ind i UserHighscore
			 
			JSONObject result = this.client.request(UserHighscore); // opretter et JSONObject med navnet result og lægger UserHighscore derind
			
			
			
			if (result != null && result.has("Result")) { // hvis result ikke er null og result har et Result så skal koden fortsætter
				
				Highscore = result.getInt("Result"); // lægger "Result" i variablen Highscore 
				
			}
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel label = new JLabel(Integer.toString(Highscore)); // opretter et nyt JLabel som viser Highscore som en string
		label.setFont(new Font("Consolas", Font.BOLD, 14)); // sætter skriftstil og størelse
		label.setBounds(89, 120, 46, 14); // sætter grænserne
		add(label); // tilføjer det nye JLabel
		
		JSONObject GlobalHighscore = new JSONObject(); // opretter JSONObjectet GlobalHighscore og putter et ny JSONObject ind i den 
		Highscore = -1; // sætter variablen Highscore og sætter den til minus 1, som en midlertidig værdi
		
		try {
			GlobalHighscore.put("Method", "GlobalHighscore");  //Put sætter metoden "Method" med værdien "GlobalHighscore" ind i GlobalHighscore
			 
			JSONObject result = this.client.request(GlobalHighscore); // opretter et JSONObject med navnet result og lægger GlobalHighscore derind
			
			
			
			if (result != null && result.has("Result")) { // hvis result ikke er null og result har et Result så skal koden fortsætter
				
				Highscore = result.getInt("Result"); // lægger "Result" i variablen Highscore 
				
			}
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		JLabel label_1 = new JLabel(Integer.toString(Highscore)); // opretter et ny JLabel og viser "Highscore" i den som en string
		label_1.setFont(new Font("Consolas", Font.BOLD, 14)); // sætter skriftstil og størrelse
		label_1.setBounds(300, 120, 46, 14); // sætter grænserne
		add(label_1); // tilføjer det nye JLabel


	}

}
