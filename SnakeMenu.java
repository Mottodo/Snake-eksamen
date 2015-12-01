import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SnakeMenu extends JPanel {

	private Main Client;
	/**
	 * Create the panel.
	 */
	public SnakeMenu(Main Client) {
		
		this.Client = Client;
		
		this.setBackground(new Color(153, 255, 102));
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Snake\r\n");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(140, 11, 134, 14);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("2 Player");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SnakeMenu This = (SnakeMenu) (e.getComponent().getParent());
				
				This.Client.changePage(new GamePage(This.Client));
			}
		});
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel_1.setBounds(140, 76, 134, 29);
		this.add(lblNewLabel_1);
		
		JLabel lblHighscore = new JLabel("Highscore");
		lblHighscore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SnakeMenu This = (SnakeMenu) (e.getComponent().getParent());
				
				This.Client.changePage(new HighscorePage(This.Client));
			}
		});
		lblHighscore.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighscore.setFont(new Font("Consolas", Font.BOLD, 14));
		lblHighscore.setBounds(140, 127, 134, 29);
		this.add(lblHighscore);
		
		JLabel lblNewLabel_2 = new JLabel("Logout");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SnakeMenu This = (SnakeMenu) (e.getComponent().getParent());
				
				This.Client.changePage(new Gui(This.Client));
			}
		});
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 11));
		lblNewLabel_2.setBounds(378, 237, 46, 14);
		this.add(lblNewLabel_2);


	}

}
