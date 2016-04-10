package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import dataBase.DBExecuteIzborni;
import dataBase.DBExecutePredmet;
import dataBase.DBExecutePredmetUsmjerenjeIzborni;
import dataBase.DBExecuteUsmjerenje;
import modeli.Izborni_;
import modeli.PredmetUsmjerenjeIzborni_;
import modeli.Predmet_;
import modeli.Usmjerenje_;
import pomocneF.PomocneF;
import tables.Izborni;
import tables.Predmet;
import tables.PredmetUsmjerenjeIzborni;
import tables.Usmjerenje;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VezaPredmetUsmjerenjeGUI {

	public static JFrame frameVezaPredmUsmj;

	JComboBox<String> comboBoxPredmet = new JComboBox<String>();
	JComboBox<String> comboBoxUsmjerenje = new JComboBox<String>();

	int active1 = 0;
	int active2 = 0;

	/**
	 * Launch the application.
	 */
	public static void startVezaPredmUsmjGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VezaPredmetUsmjerenjeGUI window = new VezaPredmetUsmjerenjeGUI();
					window.frameVezaPredmUsmj.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VezaPredmetUsmjerenjeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//komentar
		
		frameVezaPredmUsmj = new JFrame();
		/**
		 * Frame cemo postaviti na poziciju da se nasloni na glavni prozor odma sa desne strane.
		 */
		frameVezaPredmUsmj.setBounds(ProdekanGUI.frame.getX() + ProdekanGUI.frame.getWidth(), ProdekanGUI.frame.getY(), 454, 190);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameVezaPredmUsmj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameVezaPredmUsmj.getContentPane().setLayout(null);

		JButton btnPregledObaveznihPredmeta = new JButton("Pregled obaveznih predmeta po usmjerenjima");
		btnPregledObaveznihPredmeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * pozivamo prikaz tabele.
				 */
				/**
				 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active1 == 1)
					TabelaVPredmetUsmjerenjeGUI.frameTabelaPredmUsmj.dispose();
				TabelaVPredmetUsmjerenjeGUI.startTabelaVPredmUsmj();
				active2 = 1;

			}
		});

		btnPregledObaveznihPredmeta.setBounds(12, 12, 428, 25);
		frameVezaPredmUsmj.getContentPane().add(btnPregledObaveznihPredmeta);

		JLabel lblPredmet = new JLabel("Predmet:");
		lblPredmet.setBounds(56, 54, 65, 15);
		frameVezaPredmUsmj.getContentPane().add(lblPredmet);

		comboBoxPredmet.setBounds(139, 49, 250, 24);
		frameVezaPredmUsmj.getContentPane().add(comboBoxPredmet);
		try {
			fillComboBoxPredmeti();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		JLabel lblUsmjerenje = new JLabel("Usmjerenje:");
		lblUsmjerenje.setBounds(36, 90, 85, 15);
		frameVezaPredmUsmj.getContentPane().add(lblUsmjerenje);

		comboBoxUsmjerenje.setBounds(139, 85, 250, 24);
		frameVezaPredmUsmj.getContentPane().add(comboBoxUsmjerenje);
		try {
			fillComboBoxUsmjerenja();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		JButton btnPotvrdiUnos = new JButton("Potvrdi unos");
		btnPotvrdiUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Po defaultu je postavljeno da nijedan predmet nije obavezan niti za jedan smjer. I kada su se unosili predmeti, svaki predmet je postavljen za svaki
				 * smijer kao izborni predmet, i popunili smo automatski BP PredmetUsmjerenjaIzborni
				 * Sada je potrebno odabrane predmete za odabrane smijerove update-ovati, tj postaviti da su obavezni.
				 * U BP Izborni je vec definisano da sifIzborni = 1 predstavlja OBAVEZAN predmet, a sifIzborni = 2 predstavlja IZBORNI predmet
				 * Treba pokupiti odabrane stavke, pronaci u BP PredmetUsmjerenjaIzborni ogovarajuci predmet za odgovarajuci smijer, i promjeniti polje "sifIzborni"
				 */


				PomocneF.clearListe();

				/**
				 * kada pritisnemo na dugme, treba da povezemo nastavnika sa predmetom koji predaje i koji je tip nastave.
				 * trebamo se konektovati na bazu nastavnika, tipa nastave, predmeta i predmetNasstavnikTipNastave
				 */
				try {
					DBExecutePredmet.getPredmeti();
					DBExecuteUsmjerenje.getUsmjerenja();
					DBExecuteIzborni.getIzborni();
					DBExecutePredmetUsmjerenjeIzborni.getPredmetUsmjerenja();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				/**
				 * Predmet: treba pokupimo vrijednosti odabrane iz comboboxa za Predmet i snimiti u 'predmet'
				 */
				Predmet_ predmet = new Predmet_();

				if (comboBoxPredmet.getSelectedIndex() == -1) {
					System.err.println("Nije odabrana nijedna stavkka");

				} else {
					/**
					 * dohvatimo index odabrane stavke, i taj index odgovara rednom broju u vektoru kojeg pokupimo iz BP
					 */
					int pom = comboBoxPredmet.getSelectedIndex();

					/**
					 * u vektor predmeti cemo upisati sve n-torke iz bp, tj tabele predmet
					 */
					ArrayList<Predmet_> predmeti = new ArrayList<Predmet_>();
					predmeti = Predmet.predmetLista;
					/**
					 * na osnovu indexa pom odabrane stavke iz comboBoxa, dohvatimo iz vektora n-torku odgovarajucu
					 */
					predmet = predmeti.get(pom);;
				}

				/**
				 * Analogno gore iznad
				 */
				Usmjerenje_ usmjerenje = new Usmjerenje_();

				if (comboBoxUsmjerenje.getSelectedIndex() == -1) {
					System.err.println("Nije odabrana nijedna stavkka");

				} else {
					int pom = comboBoxUsmjerenje.getSelectedIndex();

					ArrayList<Usmjerenje_> usmjerenja = new ArrayList<Usmjerenje_>();
					usmjerenja = Usmjerenje.usmjerenjeLista;
					usmjerenje = usmjerenja.get(pom);;
				}

				/**
				 * Vrsimo modifikaciju predmeta umjesto da je izborni, da je obavezan
				 */
				/*
				 * U BP je vrijednost polja sifIzborni = 2 (izboni).
				 */
				Izborni_ izborni = new Izborni_();
				izborni.setSifIzborni(2);
				izborni.setIzborni("Obavezan");

				/**
				 * Dohvacene n-torke ubacujemo u varijablu pntn, koju cemo upisati u  BP
				 */

				PredmetUsmjerenjeIzborni_ pui = new PredmetUsmjerenjeIzborni_();

				pui.setSifIzborni(izborni.getSifIzborni());
				pui.setSifPredmet(predmet.getSifPredmet());
				pui.setSifUsmjerenje(usmjerenje.getSifUsmjerenje());
				System.out.println(pui.getSifIzborni());
				System.out.println(pui.getSifPredmet());
				System.out.println(pui.getSifUsmjerenje());

				/**
				 * Sada radimo update tabele, tj mijenjamo predmete za odgovarajuce smijerove da su obavezni,i ispisujemo samo obavezne predmete po smijeru
				 */
				try {
					/**
					 * Update BP
					 */
					DBExecutePredmetUsmjerenjeIzborni.updatePredmetUsmjerenjeObavezan(pui);

					/**
					 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
					 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
					 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
					 */
					if(active2 == 1)
						TabelaVPredmetUsmjerenjeGUI.frameTabelaPredmUsmj.dispose();
					TabelaVPredmetUsmjerenjeGUI.startTabelaVPredmUsmj();
					active1 = 1;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}


		});
		btnPotvrdiUnos.setBounds(139, 121, 150, 25);
		frameVezaPredmUsmj.getContentPane().add(btnPotvrdiUnos);
	}

	private void fillComboBoxPredmeti() throws SQLException{
		PomocneF.clearListe();

		comboBoxPredmet.removeAllItems();

		DBExecutePredmet.getPredmeti();
		ArrayList<Predmet_> predmeti = new ArrayList<Predmet_>();
		predmeti = Predmet.predmetLista;
		for (int i = 0; i < predmeti.size(); i++) {
			Predmet_ predmet = new Predmet_();	
			predmet = predmeti.get(i);
			comboBoxPredmet.addItem(predmet.getNazPredmet());
		}		

	}

	private void fillComboBoxUsmjerenja() throws SQLException{
		PomocneF.clearListe();

		comboBoxUsmjerenje.removeAllItems();

		DBExecuteUsmjerenje.getUsmjerenja();
		ArrayList<Usmjerenje_> usmjerenja = new ArrayList<Usmjerenje_>();
		usmjerenja = Usmjerenje.usmjerenjeLista;
		for (int i = 0; i < usmjerenja.size(); i++) {
			Usmjerenje_ usmjerenje = new Usmjerenje_();	
			usmjerenje = usmjerenja.get(i);
			comboBoxUsmjerenje.addItem(usmjerenje.getNazUsmjerenje());
		}		

	}
}
