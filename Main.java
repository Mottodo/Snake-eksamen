import java.awt.Container;
import java.awt.EventQueue;
import java.io.BufferedReader;
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

	private String adress = "localhost";
	private int portNumber = 10800;
	private Socket socket;
	private String currentUser;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel JPanel = new Gui(this);
		frame.getContentPane().add(JPanel);
	}

	public void changePage(JPanel newJPanel) {
		Container window = frame.getContentPane();
		window.removeAll();
		window.add(newJPanel);
		window.repaint();
		window.revalidate();
	}
	
	public JSONObject request(JSONObject data) {
		
		System.out.print(data);
		
		JSONObject answer = null;  // serveren modtager kun JSONObecter, ellers returnere den null
		
		try {
			
			this.socket = new Socket(this.adress, this.portNumber);
			PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
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

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
}
