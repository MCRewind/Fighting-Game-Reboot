import java.awt.Toolkit;

import javax.swing.JFrame;

public class FightingMain extends JFrame {

	public static void main(String[] args) {
		new FightingMain();
	}
	
	public FightingMain() {
		add(new Game());
		setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}
