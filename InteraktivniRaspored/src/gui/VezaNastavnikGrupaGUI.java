package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;

import dataBase.DBExecuteGrupa;
import dataBase.DBExecuteNastavnik;
import dataBase.DBExecuteNastavnikGrupa;
import modeli.Grupa_;
import modeli.NastavnikGrupa_;
import modeli.Nastavnik_;
import pomocneF.PomocneF;
import tables.Grupa;
import tables.Nastavnik;
import tables.NastavnikGrupa;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VezaNastavnikGrupaGUI {

	private JFrame frameNastavnikGrupa;
	JComboBox<String> comboBoxGrupa = new JComboBox<String>();
	JList<String> listNastavnika = new JList<String>();
	
	int active1 = 0;
	int active2 = 0;
	
	/**
	 * Launch the application.
	 */
	public static void startNastavnikGrupaGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VezaNastavnikGrupaGUI window = new VezaNastavnikGrupaGUI();
					window.frameNastavnikGrupa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VezaNastavnikGrupaGUI() {
		try {
			initialize();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frameNastavnikGrupa = new JFrame();
		frameNastavnikGrupa.setBounds(100, 100, 450, 387);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameNastavnikGrupa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameNastavnikGrupa.getContentPane().setLayout(null);
		
		JButton btnPregledNastGrupa = new JButton("Pregled nastavnika i grupa");
		btnPregledNastGrupa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				/**
				 * pozivamo prikaz tabele grupa.
				 */
				/**
				 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active1 == 1)
					TabelaNastavnikGrupaGUI.frameTabelaNastGrupa.dispose();
				TabelaNastavnikGrupaGUI.startTabelaNastGrupa();
				active2 = 1;
				
			}
		});
		btnPregledNastGrupa.setBounds(98, 12, 250, 25);
		frameNastavnikGrupa.getContentPane().add(btnPregledNastGrupa);
		
		JLabel lblGrupa = new JLabel("Grupa:");
		lblGrupa.setBounds(39, 49, 48, 15);
		frameNastavnikGrupa.getContentPane().add(lblGrupa);
		
		
		comboBoxGrupa.setBounds(98, 44, 250, 24);
		frameNastavnikGrupa.getContentPane().add(comboBoxGrupa);
		fillComboBoxNastGrupe();	

		
		
		JLabel lblNastavnik = new JLabel("Nastavnik:");
		lblNastavnik.setBounds(12, 76, 75, 15);
		frameNastavnikGrupa.getContentPane().add(lblNastavnik);
		
		createGrupeListProf();
		
		JButton btnPotvrdiUnos = new JButton("Potvrdi unos");
		btnPotvrdiUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Izbrisemo vektor nastavnika ako vec postoji, da ne bude duplih prikaza i sl
				 */
				PomocneF.clearListe();

				/**
				 * konektujemo se i dohvatamo iz baze podataka nastavnike, grupe, i grupeNastavnike
				 */
				try {
					DBExecuteNastavnik.getNastavnici();
					DBExecuteGrupa.getGrupe();
					DBExecuteNastavnikGrupa.getNastavnikGrupa();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/**
				 * moramo dohvatiti odabranu grupu i svakog odabranog nastavnika. Onda za svakog nastavnika 
				 * moramo po jednom unjeti grupu, jer je n na n veza u bp
				 * 
				 */
								
				/**
				 * Provjeravamo koja je grupa odabrana u comboBoxu
				 */
				Grupa_ grupa = new Grupa_();
				if (comboBoxGrupa.getSelectedIndex() == -1) {
					System.err.println("Pogresno unesen ili nije unesen tip nastavnika");
				} else {
					/**
					 * dohvati index odabrane stavke iz comboboxa. 0 za prvu, 1 za drugu stavku itd
					 */
					int pom = comboBoxGrupa.getSelectedIndex();
					/**
					 * dohvati iz BP grupe i sve n-torke snimimo u vektor 'grupe'
					 */
					ArrayList<Grupa_> grupe = new ArrayList<Grupa_>();
					grupe = Grupa.grupaLista;
					/**
					 * u grupa upisi vrijednost dohvacene stavke iz ComboBoxa (i u arraylisti indeksiranje ide od 0, pa moze ovo)
					 */
					grupa = grupe.get(pom);
				}

				/**
				 * sad kako imamo grupu na koju se odnosi odabir, treba da pokupimo sve odabrane nastavnike:
				 */
				/***
				 * dohvatimo sve nastavnike iz BP i sve te n-torke smjestimo u vektor 'nastavnici'
				 */
				ArrayList<Nastavnik_> nastavnici = new ArrayList<>();
				nastavnici = Nastavnik.nastavnikLista; 
				
				/**
				 * Kreiramo listu nastavnika, i u nju smjestamo sve vrijednosti koje su odabrane iz JList 'listNastavnika'
				 */
				List<String> odabrNast = listNastavnika.getSelectedValuesList();
				
				//Prolazimo kroz sve elemente liste, tj za svakog odabranog profesora
				for (int i = 0; i < odabrNast.size(); i++) {
					/**
					 * treba od svakog odabranog clana iz liste da uzmemo podatke. Namjestili smo da u listu pored imena, ubacuje i sifProfesora, pa cemo po tome 
					 * traziti, jer sifProfesora jedinstveno odredjuje profesora.
					 */
					
					/**
					 * Kreiramo String pomNastavnik, i u njega upisujemo prvi odabrani element iz liste 'listNastavnik', npr '1. Emir Meskovic'
					 */
					String pomNastavnik = odabrNast.get(i);
					
					/**
					 * Iz stringa 'pomNastavik' trebamo izdvojiti sifru. Sifra zavrsava tackom, pa cemo to iskorisiti.
					 * Prvo cemo da provjerimo na kojoj poziciji se nalazi tacka
					 */
					int tacka = pomNastavnik.indexOf('.');
					/**
					 * kako imamo poziciju tacke, mozemo iz stringa 'pomNastavnik' izvuci substring do prve tacke, sto predstavlja sifNastavnik,
					 * koja ce i dalje biti tipa String
					 */
					String sifNastString = pomNastavnik.substring(0, tacka);
					/**
					 * pretvaramo string sifNastString u int
					 */
					int sifNastavnik = Integer.parseInt(sifNastString);
					
					/**
					 * sada na osnovu sifNastavnik treba da dohvatimo odgovarajuceg nastavnika iz BP
					 */
					/**
					 * u for petlji trazimo odgovarajuceg nastavika, i kada ga nadjemo, upisujemo ga u varijablu 'nastavnik' i iskacemo iz petlje
					 */
					Nastavnik_ nastavnik = new Nastavnik_();
					for (int j = 0; j < nastavnici.size(); j++) {
						Nastavnik_ nastavnik1 = new Nastavnik_();
						nastavnik1 = nastavnici.get(j);
						if(nastavnik1.getSifNastavnik() == sifNastavnik){
							nastavnik = nastavnik1;
							break;
						}
					}
					/**
					 * posto imamo sad i odgovarajuceg nastavnika, pocinjemo upisivati u tabelu NastavnikGrupa podatke
					 * tj povezujemo nastavnika sa grupom.
					 */
					NastavnikGrupa_ nastavnikGrupa = new NastavnikGrupa_();
					/**
					 * unosimo sifGrupe, iz varijable grupa koju smo ranije dohvatili
					 */
					nastavnikGrupa.setSifGrupa(grupa.getSifGrupa());
					nastavnikGrupa.setSifNastavnik(nastavnik.getSifNastavnik());

					/**
					 * provjeravamo da li smo vec unjeli jednom tog nastavnika sa tom grupom
					 * prolazimo kroz cijelu tabelu, i trazimo da li zapis vec postoji, ako ne postoji, unosimo, 
					 * ako postoji, preskacemo taj unos.
					 */
					ArrayList<NastavnikGrupa_> nastGrupe = new ArrayList<>();
					nastGrupe = NastavnikGrupa.nastavnikGrupaLista;
					int kontr = 0; //kontrolna varijabla
					for (int k = 0; k < nastGrupe.size(); k++) {
						NastavnikGrupa_ nastGrupa = new NastavnikGrupa_();
						nastGrupa = nastGrupe.get(k);
						if (nastGrupa.getSifNastavnik() == nastavnikGrupa.getSifNastavnik() 
								&& nastGrupa.getSifGrupa() == nastavnikGrupa.getSifGrupa()) {
							kontr = 1;
							break;
						}
					}


					/**
					 * upis u bazu podataka, i upisujemo ako je kontr = 0, tj ako nije pronadjen nijedan vec uneseni
					 */
					if (kontr == 0) {
						try {
							DBExecuteNastavnikGrupa.insertNastavnikGrupa(nastavnikGrupa);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else {
						System.out.println("Odgovarajuca grupa je vec dodijeljena tom nastavniku");
					}
				}

				/**
				 * osvjezavamo tabelu
				 */
				if(active2 == 1)
					TabelaNastavnikGrupaGUI.frameTabelaNastGrupa.dispose();
				TabelaNastavnikGrupaGUI.startTabelaNastGrupa();
				active1 = 1;
				
			}
		});
		btnPotvrdiUnos.setBounds(98, 323, 250, 25);
		frameNastavnikGrupa.getContentPane().add(btnPotvrdiUnos);
		
		
	}
	
	private void fillComboBoxNastGrupe() throws SQLException{
		PomocneF.clearListe();

		comboBoxGrupa.removeAllItems();

		DBExecuteGrupa.getGrupe();
		ArrayList<Grupa_> grupe= new ArrayList<Grupa_>();
		grupe = Grupa.grupaLista;
		for (int i = 0; i < grupe.size(); i++) {
			Grupa_ grupaPom = new Grupa_();	
			grupaPom = grupe.get(i);
			comboBoxGrupa.addItem(grupaPom.getNazivGrupa());
		}
	}
	
	private void createGrupeListProf() throws SQLException {
		PomocneF.clearListe();

		/**
		 * Pokupiti nastavnike iz baze podataka i upisati u vektor nastavnici
		 */
		DBExecuteNastavnik.getNastavnici();
		ArrayList<Nastavnik_> nastavnici = new ArrayList<>();
		nastavnici = Nastavnik.nastavnikLista;
		
		/**
		 * Trebamo napraviti niz stringova, koji sadrzi sve stringove koji se ispisuju u listi.
		 */
		final String[] valuesNastavnici = new String[nastavnici.size()];//kreiramo niz stringova sa nastavici.size polja
		/**
		 * upisujemo u taj niz nastavnike iz tabele
		 */
		for (int i = 0; i < nastavnici.size(); i++) {
			Nastavnik_ nastavnik = new Nastavnik_();
			nastavnik = nastavnici.get(i);
			valuesNastavnici[i] = nastavnik.getSifNastavnik() + ". " + nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 75, 250, 236);
		frameNastavnikGrupa.getContentPane().add(scrollPane);
		scrollPane.setViewportView(listNastavnika);
		

		listNastavnika.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listNastavnika.setBackground(Color.LIGHT_GRAY);
		listNastavnika.setModel(new AbstractListModel() {
			public int getSize() {
				return valuesNastavnici.length;
			}
			public Object getElementAt(int index) {
				return valuesNastavnici[index];
			}
		});

	}

	
	
}
