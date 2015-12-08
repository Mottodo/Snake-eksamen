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

public class Game extends JPanel {

	private Main client;
	private JSONObject CurrentGame = null;
	private JTextField textField;
	private JLabel lblNewLabel;
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
		
		lblNewLabel = new JLabel("");
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
		
		
		try {
			
			JLabel lblNewLabel_5;
			
			lblNewLabel_5 = new JLabel(CurrentGame.getString("Player1") + ": Score " + CurrentGame.getInt("Player1Score"));
			
			lblNewLabel_5.setFont(new Font("Consolas", Font.PLAIN, 10));
			lblNewLabel_5.setBounds(287, 11, 153, 14);
			add(lblNewLabel_5);
			
			
			if (CurrentGame.has("Player2")) {
			
				JLabel lblNewLabel_4 = new JLabel(CurrentGame.getString("Player2") + ": Score " + CurrentGame.getInt("Player2Score"));
				lblNewLabel_4.setFont(new Font("Consolas", Font.PLAIN, 11));
				lblNewLabel_4.setBounds(287, 38, 153, 14);
				add(lblNewLabel_4);
			
			}
			
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		JLabel lblTest;
		try {
			lblTest = new JLabel(CurrentGame.getString( "GameName"));
			lblTest.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
				}
			});
			lblTest.setBounds(68, 11, 74, 14);
			add(lblTest);
			
			String ButtonText = "Leave Game";
			
			
			
			if (this.client.getCurrentUser().equals(this.CurrentGame.getString("Player1"))){
				
				
				ButtonText = "Delete Game";
				
			}
			
			
			JLabel lblNewLabel_3 = new JLabel(ButtonText);
			lblNewLabel_3.addMouseListener(new MouseAdapter() {
				@Override
				
				public void mouseClicked(MouseEvent e) {
					Game This = ( Game) (e.getComponent().getParent());
					
					JSONObject DeleteGame = new JSONObject();
					
					
					try {

						String Method = "LeaveGame";
						
						if (This.client.getCurrentUser().equals(This.CurrentGame.getString("Player1"))){
							
							
							Method = "DeleteGame";
						}

						
						DeleteGame.put("GameName", This.CurrentGame.get("GameName"));
						DeleteGame.put("Username", This.client.getCurrentUser());
						DeleteGame.put("Method", Method);
						 
						JSONObject result = This.client.request(DeleteGame);
						
						
						if (result != null && result.has("Result")) {
							
							if (result.getBoolean("Result")) {
							
								This.client.changePage(new GamePage(This.client));
							}
							
						}
					
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 9));
			lblNewLabel_3.setBounds(367, 211, 56, 14);
			add(lblNewLabel_3);
			
			textField = new JTextField();
			textField.setBounds(68, 62, 230, 20);
			add(textField);
			textField.setColumns(10);
			
			JButton btnNewButton = new JButton("Send styringshandlinger");
			btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					Game This = (Game) (e.getComponent().getParent());
					
					JSONObject UserInput = new JSONObject();
					
					try {
						
						UserInput.put("Username", This.client.getCurrentUser());
						UserInput.put("GameName", This.CurrentGame.getString("GameName"));
						UserInput.put("Method", "UserInput");
						UserInput.put("UserInput", This.textField.getText());
						
						JSONObject result = This.client.request(UserInput);
						
						if (result != null && result.has("Result")) {
							
							int State = result.getInt("Result");
							
							if (State != -1) {
								int Player = 1;
								
								if (This.CurrentGame.getString("Player2").equals(This.client.getCurrentUser())) {
									
									Player = 2;
								}
								
								String Text;
								
								if (State == Player) {
									
									Text = "You are the winner";
								}
								else {
									
									Text = "You Lost :(";
								}
								
								if (State == 0) {
									
									Text = "<html>No one has died yet,<br>  so the game continues</html>";
								}
								
								if (State == -2) {
									
									Text = "Waiting for the other player";
								}
								
								This.lblNewLabel.setText(Text);
							}
						}
						
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnNewButton.setBounds(68, 93, 230, 23);
			add(btnNewButton);
			
			
			
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}
}
