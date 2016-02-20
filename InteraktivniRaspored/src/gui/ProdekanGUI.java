package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ProdekanGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void startProdekanGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdekanGUI window = new ProdekanGUI();
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
	public ProdekanGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 725, 397);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUnos = new JLabel("Unos:");
		lblUnos.setBounds(12, 38, 70, 15);
		frame.getContentPane().add(lblUnos);
		
		
		/**
		 * Ako se pritisne dugme Nastavnici, pokrene guiNastavnici.startGuiNastavnici
		 */
		JButton btnUnosNastavnika = new JButton("Nastavnici");
		btnUnosNastavnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NastavnikGUI.startNastavnikGUI();
				TabelaNastavnikGUI.startTabelaNastavnikGUI();
				}
		});
		btnUnosNastavnika.setBounds(12, 65, 200, 25);
		frame.getContentPane().add(btnUnosNastavnika);
		
		
		/**
		 * Ako se pritisne dugme 'Zgrade i sale', pokrene guiZgradeSale.startGuiZgradeSale
		 */
		JButton btnUnosZgradeSale = new JButton("Zgrade i sale");
		btnUnosZgradeSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ZgradaSalaGUI.startZgradaSalaGUI();
			}
		});
		btnUnosZgradeSale.setBounds(12, 102, 200, 25);
		frame.getContentPane().add(btnUnosZgradeSale);
		
		/**
		 * Ako se pritisne dugme 'Usmjerenja', pokrene	UsmjerenjeGUI.startUsmjerenjaGUI();
		 */		
		JButton btnUnosUsmjerenja = new JButton("Usmjerenja");
		btnUnosUsmjerenja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsmjerenjeGUI.startUsmjerenjaGUI();
			}
		});
		btnUnosUsmjerenja.setBounds(12, 139, 200, 25);
		frame.getContentPane().add(btnUnosUsmjerenja);
		
		
		/**
		 * Ako se pritisne dugme 'Grupe i studeti', pokrene	GrupaStudentGUI.startGrupaStudentGUI();
		 */	
		JButton btnUnosGrupeStudenti = new JButton("Grupe i studenti");
		btnUnosGrupeStudenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VezaGrupaStudentGUI.startGrupaStudentGUI();
			}
		});
		btnUnosGrupeStudenti.setBounds(12, 176, 200, 25);
		frame.getContentPane().add(btnUnosGrupeStudenti);
		
		
		/**
		 * Ako se pritisne dugme 'Semestri', pokrene SemestarGUI.startSemestarGUI();
		 */	
		JButton btnUnosSemestri = new JButton("Semestri");
		btnUnosSemestri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SemestarGUI.startSemestarGUI();
			}
		});
		btnUnosSemestri.setBounds(12, 213, 200, 25);
		frame.getContentPane().add(btnUnosSemestri);
		
		
		/**
		 * Ako se pritisne dugme 'Predmeti', pokrene PredmetGUI.startPredmetGUI();
		 */	
		JButton btnUnosPredmeti = new JButton("Predmeti");
		btnUnosPredmeti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PredmetGUI.startPredmetGUI();
			}
		});
		btnUnosPredmeti.setBounds(12, 250, 200, 25);
		frame.getContentPane().add(btnUnosPredmeti);
		
		
		
		JLabel lblPovezivanje = new JLabel("Povezivanje:");
		lblPovezivanje.setBounds(264, 38, 100, 15);
		frame.getContentPane().add(lblPovezivanje);
		
		/**
		 * Ako se pritisne dugme 'Nastavnik - Predmet', pokrene VezaNastavnikPredmetGUI.startVezaNastPred();
		 */	
		JButton btnNastavnikPredmet = new JButton("Nastavnik - Predmet");
		btnNastavnikPredmet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VezaNastavnikPredmetGUI.startVezaNastPred();
			}
		});
		btnNastavnikPredmet.setBounds(264, 65, 200, 25);
		frame.getContentPane().add(btnNastavnikPredmet);
		
		/**
		 * Ako se pritisne dugme 'Grupa - Predmet', pokrene VezaNastavnikPredmetGUI.startVezaNastPred();
		 */	
		JButton btnGrupaPredmet = new JButton("Nastavnik - Grupa");
		btnGrupaPredmet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VezaNastavnikGrupaGUI.startNastavnikGrupaGUI();
			}
		});
		btnGrupaPredmet.setBounds(264, 102, 200, 25);
		frame.getContentPane().add(btnGrupaPredmet);
		
		
		
		
	}
}
