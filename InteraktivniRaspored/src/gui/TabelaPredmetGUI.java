package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TabelaPredmetGUI {

	public static JFrame frameTabelaPredmet;

	/**
	 * Launch the application.
	 */
	public static void startTabelaPredmetGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaPredmetGUI window = new TabelaPredmetGUI();
					window.frameTabelaPredmet.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabelaPredmetGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTabelaPredmet = new JFrame();
		frameTabelaPredmet.setBounds(100, 100, 450, 300);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaPredmet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

}
