import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class Highscorepage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Highscorepage window = new Highscorepage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Highscorepage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 255, 102));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Logout");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 11));
		lblNewLabel.setBounds(378, 237, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Snake");
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(189, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("My Highscore");
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel_2.setBounds(25, 70, 100, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JList list = new JList();
		list.setBounds(135, 104, 151, 147);
		frame.getContentPane().add(list);
		
		JLabel lblNewLabel_3 = new JLabel("Global Highscore");
		lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel_3.setBounds(296, 70, 375, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblAllHighscores = new JLabel("All Highscores");
		lblAllHighscores.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllHighscores.setFont(new Font("Consolas", Font.BOLD, 14));
		lblAllHighscores.setBounds(135, 70, 151, 14);
		frame.getContentPane().add(lblAllHighscores);
	}
}
