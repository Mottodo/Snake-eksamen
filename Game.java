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

public class Game extends JPanel {

	private Main client;
	private JSONObject CurrentGame = null;
	
	/**
	 * Create the panel.
	 */
	public Game(Main client, String GameName) {
		this.client = client;
	
		JSONObject GameRequest = new JSONObject();
		
		
		try {
			GameRequest.put("Username", this.client.getCurrentUser());
			GameRequest.put("GameName", GameName);
			GameRequest.put("Method", "GameInfo");
			 
			JSONObject result = this.client.request(GameRequest);
			
			
			
			if (result != null && result.has("Result")) {
				if (result.getBoolean("Result")) {
					this.CurrentGame = result.getJSONObject("GameInfo");
				}
				
			}
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (this.CurrentGame == null) {
			
			this.client.changePage(new GamePage(this.client));
			
		}
		
		
		setBackground(new Color(153, 255, 102));
		setLayout(null);
		
		List list = new List();
		list.setBounds(68, 36, 110, 60);
		list.add("test");
		add(list);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(68, 122, 230, 127);
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Logout");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Game This = (Game) (e.getComponent().getParent());
				
				This.client.changePage(new Gui(This.client));
			}
		});
		lblNewLabel_1.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(378, 237, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Back");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Game This = (Game) (e.getComponent().getParent());
				
				This.client.changePage(new SnakeMenu(This.client));
			}
		});
		lblNewLabel_2.setFont(new Font("Consolas", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(20, 237, 46, 14);
		add(lblNewLabel_2);
		
		JLabel lblSnake = new JLabel("Snake");
		lblSnake.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
		lblSnake.setBounds(189, 11, 46, 14);
		add(lblSnake);
		
		JLabel lblNewLabel_3 = new JLabel("Delete Game");
		lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 9));
		lblNewLabel_3.setBounds(10, 11, 56, 14);
		add(lblNewLabel_3);
		
		JLabel lblTest;
		try {
			lblTest = new JLabel(CurrentGame.getString("GameName"));
			lblTest.setBounds(305, 63, 46, 14);
			add(lblTest);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}
}
