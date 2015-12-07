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

public class GamePage extends JPanel {
	
	private JTextField textField;
	private Main client;
	private JList list;
	/**
	 * Create the panel.
	 */
	public GamePage(Main client) {
		
		this.client = client; 
		
		class JListItem{
			
			public int Highscore;
			public String GameName;
			
			public JListItem(int Highscore, String GameName) { 
		
				this.Highscore = Highscore;
				this.GameName = GameName;
				
			}
			public String toString() {
				
				return this.GameName + "             " + this.Highscore;
				
		}
		
		
		}
		
		this.setBackground(new Color(153, 255, 102));
		
		
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Snake");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(189, 11, 46, 14);
		this.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(79, 126, 96, 20);
		this.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Create game");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GamePage This = (GamePage) (e.getComponent().getParent());
				
				
				JSONObject CreateGame = new JSONObject();
				
				try {
					CreateGame.put("GameName", This.textField.getText());
					CreateGame.put("Method", "CreateGame");
					 
					JSONObject result = This.client.request(CreateGame);
					
					
					
					if (result != null && result.has("Result")) {
						
						if (result.getBoolean("Result")) {
							This.client.changePage(new Game(This.client));
						}
						
					}
				
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton.setFont(new Font("Consolas", Font.BOLD, 9));
		btnNewButton.setBounds(79, 157, 99, 23);
		this.add(btnNewButton);
		
		JButton btnJoinGame = new JButton("Join Game");
		btnJoinGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GamePage This = (GamePage) (e.getComponent().getParent());
				
				JSONObject JoinGame = new JSONObject();
				
				
				try {
					
					JListItem Select = (JListItem) This.list.getSelectedValue();
					
					JoinGame.put("GameName", Select.GameName);
					JoinGame.put("Username", This.client.getCurrentUser());
					JoinGame.put("Method", "JoinGame");
					 
					JSONObject result = This.client.request(JoinGame);
					
					
					
					if (result != null && result.has("Result")) {
						
						boolean ThisResult = result.getBoolean("Result");
						if (ThisResult){
							This.client.changePage(new Game(This.client));
								
						}
					}
				
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnJoinGame.setFont(new Font("Consolas", Font.BOLD, 10));
		btnJoinGame.setBounds(199, 157, 140, 23);
		this.add(btnJoinGame);
		
		JLabel lblNewLabel_1 = new JLabel("<html> Game name</html>");
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 11));
		lblNewLabel_1.setBounds(79, 34, 96, 35);
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Games");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 11));
		lblNewLabel_2.setBounds(219, 46, 99, 14);
		this.add(lblNewLabel_2);

		DefaultListModel test = new DefaultListModel();
		test.addElement("Jane Doe");

		JLabel lblLogout = new JLabel("Logout");
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GamePage This = (GamePage) (e.getComponent().getParent());
				
				This.client.changePage(new Gui(This.client));
				
			}
		});
		lblLogout.setFont(new Font("Consolas", Font.BOLD, 11));
		lblLogout.setBounds(378, 237, 46, 14);
		this.add(lblLogout);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GamePage This = (GamePage) (e.getComponent().getParent());
				
				This.client.changePage(new SnakeMenu(This.client));
			}
		});
		lblBack.setFont(new Font("Consolas", Font.BOLD, 11));
		lblBack.setBounds(20, 237, 46, 14);
		add(lblBack);
		
		JSONObject AllGames = new JSONObject();
		JSONObject result;
		JSONArray GameList = null;
		try {
			AllGames.put("Method", "ShowGames");
			 
			result = this.client.request(AllGames);
			
			
			
			if (result != null && result.has("Result")) {
				
				GameList = result.getJSONArray("Result");
				
			}
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		list = new JList();
		list.setBounds(199, 64, 157, 82);
		add(list);
		
		
		if (GameList != null) {
			DefaultListModel listModel = new DefaultListModel();
			
			JSONObject CurrentGame;
			
			for (int i = 0; i< GameList.length(); i++) {
		
				
				try {
					CurrentGame = GameList.getJSONObject(i);
					
					
					
					listModel.addElement(new JListItem(CurrentGame.getInt("Highscore"), CurrentGame.getString("Name")));
				
					
					
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			list.setModel(listModel);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			
			JScrollPane scrollPane_1 = new JScrollPane(list);
			scrollPane_1.setBounds(199, 64, 157, 82);
			this.add(scrollPane_1);
		}
		
		
		
		JButton btnNewButton_1 = new JButton("Refresh");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GamePage This = (GamePage) (e.getComponent().getParent());
				
				This.client.changePage(new GamePage(This.client));
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Consolas", Font.PLAIN, 9));
		btnNewButton_1.setBounds(359, 64, 75, 23);
		add(btnNewButton_1);

	}
}
