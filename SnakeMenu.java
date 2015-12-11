import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SnakeMenu extends JPanel { // nedarver funktioner fra JPanel

	private Main client;
	/**
	 * Create the panel.
	 */
	public SnakeMenu(Main client) {//kontruktør der siger at SnakeMenu skal modtage og indholde en Main klasse der hedder client
		
		this.client = client;// sætter client ind i this.client så man er i stand til at trække på Main klassen
		
		//sætter farve og layout
		this.setBackground(new Color(153, 255, 102));
		this.setLayout(null);
		
		//opretter de forskellige Gui elementer
		JLabel lblNewLabel = new JLabel("Welcome to Snake\r\n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(140, 43, 134, 14);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("2 Player");
		lblNewLabel_1.addMouseListener(new MouseAdapter() { // tilføjer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) { // laver en mouseCliked funktion e
				SnakeMenu This = (SnakeMenu) (e.getComponent().getParent()); // Opretter en variabel af typen SnakeMenu og lægger SnakeMenu's værdier ind i This
				
				This.client.changePage(new GamePage(This.client)); // skfiter side til GamePage hvis man har trykket på denne JLabel
			}
		});
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel_1.setBounds(140, 108, 134, 29);
		this.add(lblNewLabel_1);
		
		// opretter Highscore JLabel 
		JLabel lblHighscore = new JLabel("Highscore");
		lblHighscore.addMouseListener(new MouseAdapter() { // tilføjer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) { // laver en mouseCliked funktion e
				SnakeMenu This = (SnakeMenu) (e.getComponent().getParent()); // Opretter en variabel af typen SnakeMenu og putter SnakeMenu siden ind i This
				
				This.client.changePage(new HighscorePage(This.client)); // skifter side til HighscorePage hvis man har trykket på denne JLabel
			}
		});
		// siger hvilken stil, størrelse og hvordan teksten skal skrives
		lblHighscore.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighscore.setFont(new Font("Consolas", Font.BOLD, 14));
		lblHighscore.setBounds(140, 159, 134, 29);
		this.add(lblHighscore); // opretter lblHighscore
		
		JLabel lblNewLabel_2 = new JLabel("Logout"); // opretter en  JLabel hvor der står "Logout" inden i 
		lblNewLabel_2.addMouseListener(new MouseAdapter() { // tilføjer mouse event handler
			@Override
			public void mouseClicked(MouseEvent e) { // laver en mouseCliked funktion e
				SnakeMenu This = (SnakeMenu) (e.getComponent().getParent()); // Opretter en variabel af typen SnakeMenu og putter SnakeMenu siden ind i This
				
				This.client.changePage(new Gui(This.client)); // skifter side til Gui siden (login siden) hvis man har trykket på denne JLabel
			}
		});
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 11));
		lblNewLabel_2.setBounds(378, 237, 46, 14);
		this.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Welcome   " + this.client.getCurrentUser()); // opretter et JLabel hvor der står "welcome" og navnet på den nuværende user
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 11));
		lblNewLabel_3.setBounds(140, 83, 134, 14);
		add(lblNewLabel_3);


	}

}
