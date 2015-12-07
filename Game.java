import javax.swing.JPanel;
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
	
	/**
	 * Create the panel.
	 */
	public Game(Main client) {
		this.client = client;
		
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

	}
}
