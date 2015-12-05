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
import javax.swing.SwingConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JSlider;

public class GamePage extends JPanel {
	
	private JTextField textField;
	private Main client;
	/**
	 * Create the panel.
	 */
	public GamePage(Main client) {
		
		this.client = client; 
		
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
		btnJoinGame.setFont(new Font("Consolas", Font.BOLD, 10));
		btnJoinGame.setBounds(240, 157, 99, 23);
		this.add(btnJoinGame);
		
		JLabel lblNewLabel_1 = new JLabel("<html> Game name</html>");
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 11));
		lblNewLabel_1.setBounds(79, 34, 96, 35);
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Games");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 11));
		lblNewLabel_2.setBounds(240, 46, 99, 14);
		this.add(lblNewLabel_2);

		DefaultListModel test = new DefaultListModel();
		test.addElement("Jane Doe");
		test.addElement("Jane Doe");
		
		JList list = new JList();		
		list.setModel(test);
		
		JScrollPane scrollPane_1 = new JScrollPane(list);
		scrollPane_1.setBounds(240, 71, 96, 73);
		this.add(scrollPane_1);

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
		lblBack.setBounds(20, 236, 46, 14);
		add(lblBack);

	}
}
