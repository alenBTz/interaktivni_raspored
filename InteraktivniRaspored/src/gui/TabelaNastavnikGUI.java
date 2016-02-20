package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dataBase.DBExecuteNastavnik;
import modeli.Nastavnik_;
import pomocneF.PomocneF;
import tables.Nastavnik;

public class TabelaNastavnikGUI {

	public static JFrame frameTabelaNastavnik;
	private JTable tableNastavnici;
	public static DefaultTableModel modelNastavnik;
	
	/**
	 * Launch the application.
	 */
	public static void startTabelaNastavnikGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaNastavnikGUI window = new TabelaNastavnikGUI();
					window.frameTabelaNastavnik.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public TabelaNastavnikGUI() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frameTabelaNastavnik = new JFrame();
		frameTabelaNastavnik.setBounds(100, 100, 650, 500);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTabelaNastavnik.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 624, 412);
		frameTabelaNastavnik.getContentPane().add(scrollPane);
		
		tableNastavnici = new JTable();
		scrollPane.setViewportView(tableNastavnici);
		tableNastavnici.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Ime", "Prezime", "Tip", "Username", "Password"
			}
		));
		tableNastavnici.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableNastavnici.getColumnModel().getColumn(1).setPreferredWidth(151);
		tableNastavnici.getColumnModel().getColumn(2).setPreferredWidth(191);
		tableNastavnici.getColumnModel().getColumn(3).setPreferredWidth(89);
		tableNastavnici.getColumnModel().getColumn(4).setPreferredWidth(138);
		tableNastavnici.getColumnModel().getColumn(5).setPreferredWidth(117);
		
		/**
		 * Funkcija implementira popunjavanje date tabele nastavnicima
		 */
		popuniTabeluNastavnicima();

		
		JButton btnIzbriiNastavnike = new JButton("Izbriši nastavnike");
		btnIzbriiNastavnike.setBounds(12, 436, 200, 25);
		frameTabelaNastavnik.getContentPane().add(btnIzbriiNastavnike);
	}
	
	/**
	 * funkcija koja vrsi popunjavanje tabele
	 */
	private void popuniTabeluNastavnicima() throws SQLException {
		modelNastavnik = (DefaultTableModel) tableNastavnici.getModel();

		PomocneF.resetTable(modelNastavnik);

		DBExecuteNastavnik.getNastavnici();
		ArrayList<Nastavnik_> nastavnici = new ArrayList<Nastavnik_>();
		nastavnici = Nastavnik.nastavnikLista;
		for (int i = 0; i < nastavnici.size(); i++) {
			Nastavnik_ nastavnik = new Nastavnik_();	
			nastavnik = nastavnici.get(i);
			String sifNastString = String.valueOf(nastavnik.getSifNastavnik());
			String vrstaNastavnikString = String.valueOf(nastavnik.getVrstaNastavnik());

			modelNastavnik.addRow(new Object[]{sifNastString, nastavnik.getImeNastavnik(), nastavnik.getPrezNastavnik(), vrstaNastavnikString, nastavnik.getUsername(), nastavnik.getPassword()});
		}
	}

}
