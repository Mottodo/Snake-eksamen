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

public class HighscorePage extends JPanel {

	private Main client;
	/**
	 * Create the panel.
	 */
	public HighscorePage(Main client) {
		
		this.client = client;
		
		this.setBackground(new Color(153, 255, 102));
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Logout");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HighscorePage This = (HighscorePage) (e.getComponent().getParent());
				
				This.client.changePage(new Gui(This.client));
			}
		});
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 11));
		lblNewLabel.setBounds(378, 237, 46, 14);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Snake");
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(189, 11, 46, 14);
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("My Highscore");
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel_2.setBounds(25, 70, 100, 14);
		this.add(lblNewLabel_2);
		
		DefaultListModel test = new DefaultListModel();
		test.addElement("Jane Doe");
		test.addElement("Jane Doe");
		
		JList list = new JList();		
		list.setModel(test);
		
		JScrollPane scrollPane_1 = new JScrollPane(list);
		scrollPane_1.setBounds(135, 104, 151, 147);
		this.add(scrollPane_1);
		
		
		JLabel lblNewLabel_3 = new JLabel("Global Highscore");
		lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel_3.setBounds(296, 70, 375, 14);
		this.add(lblNewLabel_3);
		
		JLabel lblAllHighscores = new JLabel("All Highscores");
		lblAllHighscores.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllHighscores.setFont(new Font("Consolas", Font.BOLD, 14));
		lblAllHighscores.setBounds(135, 70, 151, 14);
		this.add(lblAllHighscores);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HighscorePage This = (HighscorePage) (e.getComponent().getParent());
				
				This.client.changePage(new SnakeMenu(This.client));
			}
		});
		lblBack.setFont(new Font("Consolas", Font.BOLD, 11));
		lblBack.setBounds(20, 236, 46, 14);
		add(lblBack);
		
		JSONObject UserHighscore = new JSONObject();
		int Highscore = -1;
		
		try {
			UserHighscore.put("Username", this.client.getCurrentUser());
			UserHighscore.put("Method", "UserHighscore");
			 
			JSONObject result = this.client.request(UserHighscore);
			
			
			
			if (result != null && result.has("Result")) {
				
				Highscore = result.getInt("Result");
				
			}
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel label = new JLabel(Integer.toString(Highscore));
		label.setFont(new Font("Consolas", Font.BOLD, 14));
		label.setBounds(50, 120, 46, 14);
		add(label);
		
		JSONObject GlobalHighscore = new JSONObject();
		Highscore = -1;
		
		try {
			GlobalHighscore.put("Method", "GlobalHighscore");
			 
			JSONObject result = this.client.request(GlobalHighscore);
			
			
			
			if (result != null && result.has("Result")) {
				
				Highscore = result.getInt("Result");
				
			}
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		JLabel label_1 = new JLabel(Integer.toString(Highscore));
		label_1.setFont(new Font("Consolas", Font.BOLD, 14));
		label_1.setBounds(336, 120, 46, 14);
		add(label_1);


	}

}
