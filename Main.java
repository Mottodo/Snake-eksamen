import java.awt.Container;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.JSONException;
import org.json.JSONObject;


public class Main {

	private String address = "localhost"; // instantiering af de forskellige variabler
	private int portNumber = 10800;
	private Socket socket;
	private String currentUser = null;
	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {  // oprettelse af main funktion, som indeholder et string array.
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				try {
					Main window = new Main(); // opretter et Main okject der indeholder en ny MAin
					window.frame.setVisible(true); // sætter window' s frame til at være synligt
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() { // opretter konstruktør 
		
		try {
			BufferedReader Reader = new BufferedReader(new FileReader("ClientConfig.txt"));
			
			try {
				JSONObject Setup = new JSONObject(Reader.readLine());
				
				this.address = Setup.getString("Address");
				this.portNumber = Setup.getInt("Port");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		initialize(); // hvergang der oprettes en ny klasse skal klassen indhold initialiseres
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { //intialisere indholdet nedfor
		frame = new JFrame(); // opretter et ny JFrame og putter det ind i frame
		frame.setBounds(100, 100, 450, 300); // sætter frames bounds hvilket er aplikations vinduets størrelse
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // sørger for at JFramet lukker når x klikkes
		
		JPanel JPanel = new Gui(this); // Opretter en ny JPanel variabel, Dernæst puttes en ny Gui klasse, som ejes af Main, puttes ind i JPanel
		frame.getContentPane().add(JPanel); // JPanel puttes nu ind i frame
	}

	public void changePage(JPanel newJPanel) { // funktion til at skifte side, so modtager et JPanel med navnet newJPanel
		Container window = frame.getContentPane(); // putter frames indhold ind i window af typen Container, som står for at oprette et miljø der er ansvarlig for at tilføje de tekniske elementer til komponenterne
		window.removeAll(); // sletter alt i window
		window.add(newJPanel); // tilføje det nye JPanel
		window.repaint(); // tegner newJPanel
		window.revalidate(); // bekræfter og fornyer window
	}
	
	public JSONObject request(JSONObject data) { // ny funktion af typen JSONObject med navnet request, som modtager et JSONObject med navnet data
		
		System.out.print(data); // udskriver den JSONOBjectet data
		
		JSONObject answer = null;  // serveren modtager kun JSONObjecter, ellers returnere den null
		
		try {
			
			this.socket = new Socket(this.address, this.portNumber); // opretter en ny Socket med adress og portNumber og putter den ind i this.socket
			PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true); //  
			BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			
			
			out.println(data.toString());
			try {
				answer = new JSONObject(in.readLine());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.socket.close();			
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.print(answer);
		return answer;
		
		
		
	}

	public String getCurrentUser() { // funktion til at få fat på CurrentUser
		return currentUser;
	}

	public void setCurrentUser(String currentUser) { // funktion der sætter currentUser
		this.currentUser = currentUser;
	}
}
