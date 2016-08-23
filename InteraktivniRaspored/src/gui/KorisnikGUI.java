package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataBase.DBExecuteKorisnik;
import modeli.Korisnik_;
import tables.Korisnik;

public class KorisnikGUI {
	JFrame frameKorisnik;
	private JTextField txtIme;
	private JTextField txtPrezime;
	private JTextField txtKorisnickoIme;
	private JTextField txtSifra;
	
	int active1 = 0;
	int active2 = 0;
	
	public static void startKorisnikGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KorisnikGUI window = new KorisnikGUI();
					window.frameKorisnik.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public KorisnikGUI() {
		initialize();
	}
	
	private void initialize() {
		frameKorisnik = new JFrame();
		frameKorisnik.setBounds(100, 100, 430, 300);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameKorisnik.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameKorisnik.getContentPane().setLayout(null);
		
		
		
		JLabel lblKorisnickoIme = new JLabel("Korisnicko Ime:");
		lblKorisnickoIme.setBounds(77, 47, 100, 15);
		frameKorisnik.getContentPane().add(lblKorisnickoIme);
		
		txtKorisnickoIme = new JTextField();
		txtKorisnickoIme.setText("");
		txtKorisnickoIme.setBounds(177, 47, 166, 19);
		frameKorisnik.getContentPane().add(txtKorisnickoIme);
		txtKorisnickoIme.setColumns(10);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(77, 77, 100, 15);
		frameKorisnik.getContentPane().add(lblIme);
		
		txtIme = new JTextField();
		txtIme.setText("");
		txtIme.setBounds(177, 77, 166, 19);
		frameKorisnik.getContentPane().add(txtIme);
		txtIme.setColumns(10);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(77, 107, 100, 15);
		frameKorisnik.getContentPane().add(lblPrezime);
		
		txtPrezime = new JTextField();
		txtPrezime.setText("");
		txtPrezime.setBounds(177, 107, 166, 19);
		frameKorisnik.getContentPane().add(txtPrezime);
		txtPrezime.setColumns(10);
		
		JLabel lblSifra = new JLabel("Lozinka:");
		lblSifra.setBounds(77, 137, 62, 15);
		frameKorisnik.getContentPane().add(lblSifra);
		
		txtSifra = new JTextField();
		txtSifra.setText("");
		txtSifra.setBounds(177, 137, 166, 19);
		frameKorisnik.getContentPane().add(txtSifra);
		txtSifra.setColumns(10);
	
		final JComboBox<String> comboBoxUlogaKorisnika = new JComboBox<String>();
		comboBoxUlogaKorisnika.setBounds(177, 167, 166, 24);
		frameKorisnik.getContentPane().add(comboBoxUlogaKorisnika);
		comboBoxUlogaKorisnika.addItem("  --  ");
		comboBoxUlogaKorisnika.addItem("Prodekan");
		comboBoxUlogaKorisnika.addItem("Profesor");
		comboBoxUlogaKorisnika.addItem("Student");	
		
		JButton btnDodajKorisnika = new JButton("Dodaj korisnika");
		btnDodajKorisnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean postojiProdekan = false;
				Korisnik.korisnikLista.clear();
				Korisnik_ korisnik = new Korisnik_();
				korisnik.setKorisnickoIme(txtKorisnickoIme.getText());
				korisnik.setSifra(txtSifra.getText());
				korisnik.setIme(txtIme.getText());
				korisnik.setPrezime(txtPrezime.getText());
				try {
					//brojProdekana = DBExecuteKorisnik.countProdekana();
					if (DBExecuteKorisnik.countProdekana() != 0){
						postojiProdekan = true;
						System.err.println("U bazi moze postojati samo jedan prodekan,za unos novog izbrisite trenutnog!");
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if (comboBoxUlogaKorisnika.getSelectedIndex() == -1 || String.valueOf(comboBoxUlogaKorisnika.getSelectedItem()) == "  --  " || postojiProdekan) {
					System.err.println("Greska pri unosu,provjerite da li je unesen tip korisnika");
				} else {
					korisnik.setUloga(String.valueOf(comboBoxUlogaKorisnika.getSelectedItem()));
					
					try {
						/**
						 * Upisujemo 'korisnik' u BP pozivanjem metoda:
						 */
						DBExecuteKorisnik.insertKorisnik(korisnik);
						
						/**
						 * Posto smo unjeli novog nastavnika, zelimo da osvjezimo tabelu.
						 */
						if(active2 == 1)
							TabelaKorisnikGUI.frameTabelaKorisnik.dispose();
						TabelaKorisnikGUI.startTabelaKorisnikGUI();
						active1 = 1;
						txtSifra.setText("");
						txtPrezime.setText("");
						txtIme.setText("");
						txtKorisnickoIme.setText("");
						comboBoxUlogaKorisnika.setSelectedItem("  --  ");
						/*
						createGrupeListProf();

						//osvjezi u panelu Predmet combobox nastavnika
						comboBoxPredNastavnik.setBounds(400, 124, 271, 24);
						panelPredmeti.add(comboBoxPredNastavnik);
						fillComboBoxPredNastavnik();
						*/
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		
		btnDodajKorisnika.setBounds(141, 197, 175, 25);
		frameKorisnik.getContentPane().add(btnDodajKorisnika);
		
		JButton btnPregledKorisnika = new JButton("Pregled korisnika");
		btnPregledKorisnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * pozivamo prikaz tabele nastavnika.
				 */
				/**
				 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom provjere korisnika otvori, i ako unesemo korisnika, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active1 == 1)
					TabelaKorisnikGUI.frameTabelaKorisnik.dispose();
				TabelaKorisnikGUI.startTabelaKorisnikGUI();
				active2 = 1;
			}
		});
		btnPregledKorisnika.setBounds(141, 11, 175, 23);
		frameKorisnik.getContentPane().add(btnPregledKorisnika);
	}
}


