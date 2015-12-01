import javax.swing.JPanel;
import java.awt.Color;
import java.awt.List;
import java.awt.Canvas;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Game extends JPanel {

	private Main Client;
	
	/**
	 * Create the panel.
	 */
	public Game(Main Client) {
		this.Client = Client;
		
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

	}
}
