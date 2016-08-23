package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dataBase.DBExecuteKorisnik;
import dataBase.DBExecuteNastavnik;
import modeli.Korisnik_;
import modeli.Nastavnik_;
import pomocneF.IzbrisiRed;
import pomocneF.PomocneF;
import tables.Korisnik;
import tables.Nastavnik;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabelaKorisnikGUI {

	public static JFrame frameTabelaKorisnik;
	private JTable tableKorisnici;
	public static DefaultTableModel modelKorisnik;
	
	/**
	 * Launch the application.
	 */
	public static void startTabelaKorisnikGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaKorisnikGUI window = new TabelaKorisnikGUI();
					window.frameTabelaKorisnik.setVisible(true);
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
	public TabelaKorisnikGUI() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frameTabelaKorisnik = new JFrame();
		frameTabelaKorisnik.setBounds(100, 100, 650, 500);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaKorisnik.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 624, 412);
		frameTabelaKorisnik.getContentPane().add(scrollPane);
		
		tableKorisnici = new JTable();
		scrollPane.setViewportView(tableKorisnici);
		tableKorisnici.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Ime", "Prezime", "Tip", "Username", "Password"
			}
		));
		tableKorisnici.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableKorisnici.getColumnModel().getColumn(1).setPreferredWidth(151);
		tableKorisnici.getColumnModel().getColumn(2).setPreferredWidth(191);
		tableKorisnici.getColumnModel().getColumn(3).setPreferredWidth(89);
		tableKorisnici.getColumnModel().getColumn(4).setPreferredWidth(138);
		tableKorisnici.getColumnModel().getColumn(5).setPreferredWidth(117);
		
		/**
		 * Funkcija implementira popunjavanje date tabele nastavnicima
		 */
		popuniTabeluKorisnicima();

		
		JButton btnIzbriiKorisnike = new JButton("Izbriši korisnike");
		btnIzbriiKorisnike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int red = tableKorisnici.getSelectedRow();
			if(red != -1)
			{
				try {
					Object id = tableKorisnici.getValueAt(red, 0);
					IzbrisiRed.izbrisiRed(id,"sifKorisnik","korisnik");
					modelKorisnik.removeRow(red);
				} catch (SQLException e) {
					System.out.println("Operacija brisanja nije uspjela ");
					e.printStackTrace();
				}
			}
			else{
				System.out.println("Niti jedan red nije selektovan");
			}
			}
		});
		btnIzbriiKorisnike.setBounds(12, 436, 200, 25);
		frameTabelaKorisnik.getContentPane().add(btnIzbriiKorisnike);
	}
	
	/**
	 * funkcija koja vrsi popunjavanje tabele
	 */
	private void popuniTabeluKorisnicima() throws SQLException {
		modelKorisnik = (DefaultTableModel) tableKorisnici.getModel();

		PomocneF.resetTable(modelKorisnik);

		DBExecuteKorisnik.getKorisnici();
		ArrayList<Korisnik_> korisnici = new ArrayList<Korisnik_>();
		korisnici = Korisnik.korisnikLista;
		for (int i = 0; i < korisnici.size(); i++) {
			Korisnik_ korisnik = new Korisnik_();	
			korisnik = korisnici.get(i);
			String sifKorString = String.valueOf(korisnik.getSifKorisnik());
			String vrstaKorisnikString = String.valueOf(korisnik.getUloga());
			System.out.println(korisnik.getSifra());
			modelKorisnik.addRow(new Object[]{sifKorString, korisnik.getIme(), korisnik.getPrezime(), vrstaKorisnikString, korisnik.getKorisnickoIme(), korisnik.getSifra()});
		}
	}

}
