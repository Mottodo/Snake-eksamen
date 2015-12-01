import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HighscorePage extends JPanel {

	private Main Client;
	/**
	 * Create the panel.
	 */
	public HighscorePage(Main Client) {
		
		this.Client = Client;
		
		this.setBackground(new Color(153, 255, 102));
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Logout");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HighscorePage This = (HighscorePage) (e.getComponent().getParent());
				
				This.Client.changePage(new Gui(This.Client));
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
				
				This.Client.changePage(new SnakeMenu(This.Client));
			}
		});
		lblBack.setFont(new Font("Consolas", Font.BOLD, 11));
		lblBack.setBounds(20, 236, 46, 14);
		add(lblBack);
		
		JLabel label = new JLabel("1231");
		label.setFont(new Font("Consolas", Font.BOLD, 14));
		label.setBounds(50, 120, 46, 14);
		add(label);
		
		JLabel label_1 = new JLabel("1231");
		label_1.setFont(new Font("Consolas", Font.BOLD, 14));
		label_1.setBounds(336, 120, 46, 14);
		add(label_1);


	}

}
