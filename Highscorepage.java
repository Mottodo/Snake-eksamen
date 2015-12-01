import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HighscorePage extends JPanel {

	/**
	 * Create the panel.
	 */
	public HighscorePage() {
		
		this.setBackground(new Color(153, 255, 102));
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Logout");
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
		
		JList list = new JList();
		list.setBounds(135, 104, 151, 147);
		this.add(list);
		
		JLabel lblNewLabel_3 = new JLabel("Global Highscore");
		lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel_3.setBounds(296, 70, 375, 14);
		this.add(lblNewLabel_3);
		
		JLabel lblAllHighscores = new JLabel("All Highscores");
		lblAllHighscores.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllHighscores.setFont(new Font("Consolas", Font.BOLD, 14));
		lblAllHighscores.setBounds(135, 70, 151, 14);
		this.add(lblAllHighscores);


	}

}
