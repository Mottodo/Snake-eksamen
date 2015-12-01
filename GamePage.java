import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GamePage extends JPanel {
	
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public GamePage() {
		
		
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
		btnNewButton.setFont(new Font("Consolas", Font.BOLD, 10));
		btnNewButton.setBounds(79, 157, 99, 23);
		this.add(btnNewButton);
		
		JButton btnJoinGame = new JButton("Join Game");
		btnJoinGame.setFont(new Font("Consolas", Font.BOLD, 10));
		btnJoinGame.setBounds(240, 157, 99, 23);
		this.add(btnJoinGame);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Insert new<br> game name</html>");
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 10));
		lblNewLabel_1.setBounds(79, 46, 96, 35);
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Games");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 11));
		lblNewLabel_2.setBounds(240, 46, 99, 14);
		this.add(lblNewLabel_2);
		
		JList list = new JList();
		list.setBounds(240, 71, 96, 73);
		this.add(list);
		
		JLabel lblLogout = new JLabel("Logout");
		lblLogout.setFont(new Font("Consolas", Font.BOLD, 11));
		lblLogout.setBounds(378, 237, 46, 14);
		this.add(lblLogout);


	}

}
