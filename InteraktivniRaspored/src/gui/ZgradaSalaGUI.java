package gui;

import java.awt.EventQueue;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dataBase.DBExecuteSala;
import dataBase.DBExecuteZgrada;
import modeli.Sala_;
import modeli.Zgrada_;
import pomocneF.PomocneF;
import tables.Zgrada;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class ZgradaSalaGUI {

	private JFrame frameZgradaSala;
	private JTextField txtNazivzgrade;
	private JTextField txtKratzgrade;
	private JTextField txtNazivsale;
	private JTextField txtKratsale;
	private JTextField txtKapacitetsale;
	JComboBox<String> comboBoxPripZgradi = new JComboBox<String>();

	int active1 = 0;
	int active2 = 0;
	int active3 = 0;
	int active4 = 0;
	
	/**
	 * Launch the application.
	 */
	public static void startZgradaSalaGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZgradaSalaGUI window = new ZgradaSalaGUI();
					window.frameZgradaSala.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ZgradaSalaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameZgradaSala = new JFrame();
		frameZgradaSala.setBounds(100, 100, 310, 370);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameZgradaSala.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameZgradaSala.getContentPane().setLayout(null);
		
		
		JButton btnPregledZgrada = new JButton("Pregled zgrada");
		btnPregledZgrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Ako vec postoji lista zgrada procitana iz BP, brisemo je, kako se ne bi postojali dupli podaci
				 */
				Zgrada.zgradaLista.clear();
				/**
				 * Prikazujemo tabelu sa zgradama
				 */
				/**
				 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active1 == 1)
					TabelaZgradaGUI.frameTabelaZgrade.dispose();
				TabelaZgradaGUI.startTabelaZgradaGUI();
				active2 = 1;
				
			}
		});
		btnPregledZgrada.setBounds(139, 12, 150, 25);
		frameZgradaSala.getContentPane().add(btnPregledZgrada);
		
		JLabel lblNazivZgrade = new JLabel("Naziv zgrade:");
		lblNazivZgrade.setBounds(24, 49, 97, 15);
		frameZgradaSala.getContentPane().add(lblNazivZgrade);
		
		txtNazivzgrade = new JTextField();
		txtNazivzgrade.setText("");
		txtNazivzgrade.setBounds(139, 47, 150, 19);
		frameZgradaSala.getContentPane().add(txtNazivzgrade);
		txtNazivzgrade.setColumns(10);
		
		JLabel lblSkraenica = new JLabel("Skraćenica:");
		lblSkraenica.setBounds(39, 76, 82, 15);
		frameZgradaSala.getContentPane().add(lblSkraenica);
		
		txtKratzgrade = new JTextField();
		txtKratzgrade.setText("");
		txtKratzgrade.setBounds(139, 74, 150, 19);
		frameZgradaSala.getContentPane().add(txtKratzgrade);
		txtKratzgrade.setColumns(10);
		
		JButton btnPotvrdiUnosZgrada = new JButton("Potvrdi unos");
		btnPotvrdiUnosZgrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Treba unjeti novu zgradu.
				 */
				/**
				 * Ako vec postoji lista zgrada procitana iz BP, brisemo je, kako se ne bi postojali dupli podaci
				 */
				Zgrada.zgradaLista.clear();
				
				/**
				 * Kreiramo varijablu zgrada, u koju cemo upisati podatke koje je unio korisnik
				 */
				Zgrada_ zgrada = new Zgrada_();

				/**
				 * Unosimo u nastavnika podatke koje je unio korisnik
				 */
				zgrada.setNazZgrada(txtNazivzgrade.getText());
				zgrada.setKratZgrada(txtKratzgrade.getText());

				/**
				 * Upis 'korisnik' u BP
				 */
				try {
					/**
					 * Upisujemo 'zgrada' u BP pozivanjem metoda:
					 */
					DBExecuteZgrada.insertZgrada(zgrada);
					
					/**
					 * Posto smo unjeli novu zgradu, zelimo da osvjezimo tabelu.
					 */
					/**
					 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
					 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
					 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
					 */
					if(active2 == 1)
						TabelaZgradaGUI.frameTabelaZgrade.dispose();
					TabelaZgradaGUI.startTabelaZgradaGUI();
					active1 = 1;
					TabelaZgradaGUI.startTabelaZgradaGUI();					
					
					/**
					 * Kako smo unjeli novu zgradu, potrebno je i osvjeziti ComboBox koji sadrzi zgrade
					 *  u dijelu gdje se sale unose
					 */

					comboBoxPripZgradi.setBounds(137, 267, 150, 24);
					frameZgradaSala.getContentPane().add(comboBoxPripZgradi);
					/**
					 * Popuni comboBox pozivoj metoda:
					 */
					fillComboBoxSala();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnPotvrdiUnosZgrada.setBounds(139, 103, 150, 25);
		frameZgradaSala.getContentPane().add(btnPotvrdiUnosZgrada);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 140, 277, 2);
		frameZgradaSala.getContentPane().add(separator);
		
		JButton btnPregledSala = new JButton("Pregled sala");
		btnPregledSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active3 == 1){
					TabelaSalaGUI.frameTabelaSala.dispose();	
				}
				TabelaSalaGUI.startTabelaSalaGUI();
				active4 = 1;
			}
		});
		btnPregledSala.setBounds(139, 154, 150, 25);
		frameZgradaSala.getContentPane().add(btnPregledSala);
		
		JLabel lblNazivSale = new JLabel("Naziv sale:");
		lblNazivSale.setBounds(44, 191, 77, 15);
		frameZgradaSala.getContentPane().add(lblNazivSale);
		
		txtNazivsale = new JTextField();
		txtNazivsale.setText("");
		txtNazivsale.setBounds(139, 189, 150, 19);
		frameZgradaSala.getContentPane().add(txtNazivsale);
		txtNazivsale.setColumns(10);
		
		JLabel lblSkraenica_1 = new JLabel("Skraćenica:");
		lblSkraenica_1.setBounds(39, 218, 82, 15);
		frameZgradaSala.getContentPane().add(lblSkraenica_1);
		
		txtKratsale = new JTextField();
		txtKratsale.setText("");
		txtKratsale.setBounds(139, 216, 150, 19);
		frameZgradaSala.getContentPane().add(txtKratsale);
		txtKratsale.setColumns(10);
		
		JLabel lblKapacitet = new JLabel("Kapacitet:");
		lblKapacitet.setBounds(48, 245, 73, 15);
		frameZgradaSala.getContentPane().add(lblKapacitet);
		
		txtKapacitetsale = new JTextField();
		txtKapacitetsale.setText("");
		txtKapacitetsale.setBounds(139, 243, 150, 19);
		frameZgradaSala.getContentPane().add(txtKapacitetsale);
		txtKapacitetsale.setColumns(10);
		
		JLabel lblPripadaZgradi = new JLabel("Pripada zgradi:");
		lblPripadaZgradi.setBounds(12, 272, 109, 15);
		frameZgradaSala.getContentPane().add(lblPripadaZgradi);
		
		comboBoxPripZgradi.setBounds(137, 267, 150, 24);
		frameZgradaSala.getContentPane().add(comboBoxPripZgradi);
		/**
		 * Popuni comboBox pozivoj metoda:
		 */
		try {
			fillComboBoxSala();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JButton btnPotvrdiUnosSala = new JButton("Potvrdi unos");
		btnPotvrdiUnosSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/**
				 * Brisemo sve vektore koji su dohvaceni iz BP da ne bi bilo duplih podataka u njima
				 */
				PomocneF.clearListe();
			
				/**
				 * Pošto se sala veze za zgrade, moramo dohvatiti sve zgrade iz BP
				 */
				try {
					DBExecuteZgrada.getZgrade();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				/**
				 * kreiramo varijablu tipa Sala_ u koju unosimo podatke koje je upisao korisnik
				 */
				Sala_ sala = new Sala_();

				/**
				 * unosimo podatke u "sala".
				 */
				sala.setNazivSala(txtNazivsale.getText());
				sala.setKratSala(txtKratsale.getText());
				sala.setBrMjesta(Integer.parseInt(txtKapacitetsale.getText())); //uneseni string moramo u int pretvoriti pa upisati

				/**
				 * Provjeravamo koja opcija je unjeta u ComboBox
				 */
				if (comboBoxPripZgradi.getSelectedIndex() == -1) {
					System.err.println("Pogresno unesen ili nije unesen tip nastavnika");
				} else {
					/**
					 * Povezujemo sada salu sa zgradom.
					 */
					/**
					 * uzimamo indeks odabrane zgrade iz Combo Boxa, i upisujemo u varijablu pom. 
					 * Prva stavka ima index 0, druga 1, itd.
					 * Posto se ovaj ComboBox puni iz vektora koji dobijemo iz Baze podataka, a i indeksiranje vektora 
					 * ide od 0, to znaci da prvi element u combo boxu odgovara prvom elementu u vektoru napunjenom iz BP.
					 */
					int pom = comboBoxPripZgradi.getSelectedIndex(); 
					
					/**
					 * Trebamo dohvatiti sve zgrade iz BP.
					 */
					Zgrada_ zgrada = new Zgrada_();	
					ArrayList<Zgrada_> zgrade = new ArrayList<Zgrada_>();
					zgrade = Zgrada.zgradaLista;
					/**
					 * u vektoru 'zgrade', na pozicij 'pom' se nalazi n torka koja nas zanima, pa je dohvatimo i snimimo u 'zgrada'
					 */
					zgrada = zgrade.get(pom); 
					/**
					 * dohvatimo sada sifru zgrade, i upisemo u 'pom2'
					 */
					int pom2 = zgrada.getSifZgrada();
					
					/**
					 * Sada sifru zgrade upisujemo u varijablu 'sala'
					 */
					sala.setSifZgrada(zgrada.getSifZgrada()); //uzmi sifru zgrade, i upisi u tabelu sala
				}
				
				/**
				 * Upis 'sala' u BP. Popunili smo svako polje varijable 'sala' pa je mozemo upisati 
				 */
				try {
					DBExecuteSala.insertSala(sala);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/**
				 * Kada smo unjeli salu u DB, trebamo osvjeziti tabelu
				 */
				/**
				 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active4 == 1){
					TabelaSalaGUI.frameTabelaSala.dispose();
				}
				TabelaSalaGUI.startTabelaSalaGUI();
				active3 = 1;


			
			}
		});
		btnPotvrdiUnosSala.setBounds(139, 303, 150, 25);
		frameZgradaSala.getContentPane().add(btnPotvrdiUnosSala);
		
	}

	
	
	private void fillComboBoxSala() throws SQLException {
		/**
		 * Da ne bude duplikata, brisemo sve liste.
		 */
		Zgrada.zgradaLista.clear();
		/**
		 * Praznimo i sve elemente iz comboboxa, kako bi ga napunili svjezijim ntorkama iz BP
		 */
		comboBoxPripZgradi.removeAllItems();

		DBExecuteZgrada.getZgrade();
		ArrayList<Zgrada_> zgrade = new ArrayList<Zgrada_>();
		zgrade = Zgrada.zgradaLista;
		for (int i = 0; i < zgrade.size(); i++) {
			Zgrada_ zgradaPom = new Zgrada_();	
			zgradaPom = zgrade.get(i);
			comboBoxPripZgradi.addItem(zgradaPom.getNazZgrada());
		}
	}
		
	

}
