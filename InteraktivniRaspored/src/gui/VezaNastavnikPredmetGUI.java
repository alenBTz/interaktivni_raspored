package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import dataBase.DBExecuteNastavnik;
import dataBase.DBExecutePredmet;
import dataBase.DBExecutePredmetNastavnikTipNastave;
import dataBase.DBExecuteTipNastave;
import modeli.Nastavnik_;
import modeli.PredmetNastavnikTipNastave_;
import modeli.Predmet_;
import modeli.TipNastave_;
import pomocneF.PomocneF;
import tables.Nastavnik;
import tables.Predmet;
import tables.PredmetNastavnikTipNastave;
import tables.TipNastave;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VezaNastavnikPredmetGUI {

	private JFrame frameVezaNastPred;

	JComboBox<String> comboBoxVezaPredmet = new JComboBox<String>();
	JComboBox<String> comboBoxVezaTipNastave = new JComboBox<String>();
	JComboBox<String> comboBoxVezaNastavnik = new JComboBox<String>();
	
	int active1 = 0;
	int active2 = 0;
	
	/**
	 * Launch the application.
	 */
	public static void startVezaNastPred() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VezaNastavnikPredmetGUI window = new VezaNastavnikPredmetGUI();
					window.frameVezaNastPred.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VezaNastavnikPredmetGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameVezaNastPred = new JFrame();
		frameVezaNastPred.setBounds(100, 100, 405, 220);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameVezaNastPred.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameVezaNastPred.getContentPane().setLayout(null);
		
		JButton btnPregledPredmetaI = new JButton("Pregled dodjeljenih nastavnika za predmete");
		btnPregledPredmetaI.addActionListener(new ActionListener() {
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
					TabelaVezaNastPred.frameTabelaVezaNastPred.dispose();
				TabelaVezaNastPred.startTabelaVezaNastPred();
				active2 = 1;
			
			}
		});
		btnPregledPredmetaI.setBounds(12, 12, 370, 25);
		frameVezaNastPred.getContentPane().add(btnPregledPredmetaI);
		
		JLabel lblPredmet = new JLabel("Predmet:");
		lblPredmet.setBounds(34, 49, 65, 15);
		frameVezaNastPred.getContentPane().add(lblPredmet);
		
		
		comboBoxVezaPredmet.setBounds(117, 49, 250, 24);
		frameVezaNastPred.getContentPane().add(comboBoxVezaPredmet);
		/**
		 * Pozivamo metod koji puni ovaj comboBox
		 */
		try {
			fillComboBoxPredPredmeti();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		JLabel lblTipNastave = new JLabel("Tip nastave:");
		lblTipNastave.setBounds(12, 87, 87, 15);
		frameVezaNastPred.getContentPane().add(lblTipNastave);
		
		comboBoxVezaTipNastave.setBounds(117, 82, 250, 25);
		frameVezaNastPred.getContentPane().add(comboBoxVezaTipNastave);
		/**
		 * Pozivamo metod koji puni ovaj comboBox
		 */
		try {
			fillComboBoxPredTipNastave();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblNastavnik = new JLabel("Nastavnik:");
		lblNastavnik.setBounds(24, 124, 75, 15);
		frameVezaNastPred.getContentPane().add(lblNastavnik);
		
		comboBoxVezaNastavnik.setBounds(117, 119, 250, 24);
		frameVezaNastPred.getContentPane().add(comboBoxVezaNastavnik);
		/**
		 * Pozivamo metod koji puni ovaj comboBox
		 */
		try {
			fillComboBoxPredNastavnik();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JButton btnPotvrdiUnosVezaPNTN = new JButton("Potvrdi unos");
		btnPotvrdiUnosVezaPNTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				PomocneF.clearListe();
				
				/**
				 * kada pritisnemo na dugme, treba da povezemo nastavnika sa predmetom koji predaje i koji je tip nastave.
				 * trebamo se konektovati na bazu nastavnika, tipa nastave, predmeta i predmetNasstavnikTipNastave
				 */
				try {
					DBExecutePredmet.getPredmeti();
					DBExecuteTipNastave.getTipNastave();
					DBExecuteNastavnik.getNastavnici();
					DBExecutePredmetNastavnikTipNastave.getPredmetNastavnikTipNastave();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				/**
				 * Predmet: treba pokupimo vrijednosti odabrane iz comboboxa za Predmet i snimiti 'predmet'
				 */
				Predmet_ predmet = new Predmet_();

				if (comboBoxVezaPredmet.getSelectedIndex() == -1) {
					System.err.println("Nije odabrana nijedna stavkka");

				} else {
					/**
					 * dohvatimo index odabrane stavke, i taj index odgovara rednom broju u vektoru kojeg pokupimo iz BP
					 */
					int pom = comboBoxVezaPredmet.getSelectedIndex();
					
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
				TipNastave_ tipNastave = new TipNastave_();

				if (comboBoxVezaTipNastave.getSelectedIndex() == -1) {
					System.err.println("Nije odabrana nijedna stavkka");

				} else {
					int pom = comboBoxVezaTipNastave.getSelectedIndex();

					ArrayList<TipNastave_> tipoviNastave = new ArrayList<TipNastave_>();
					tipoviNastave = TipNastave.tipNastaveLista;
					tipNastave = tipoviNastave.get(pom);;
				}

				/**
				 * Analogno gore iznad
				 */
				Nastavnik_ nastavnik = new Nastavnik_();

				if (comboBoxVezaNastavnik.getSelectedIndex() == -1) {
					System.err.println("Nije odabrana nijedna stavkka");

				} else {
					int pom = comboBoxVezaNastavnik.getSelectedIndex();

					ArrayList<Nastavnik_> nastavnici = new ArrayList<Nastavnik_>();
					nastavnici = Nastavnik.nastavnikLista;
					nastavnik = nastavnici.get(pom);;
				}

				/**
				 * Dohvacene n-torke ubacujemo u varijablu pntn, koju cemo upisati u  BP
				 */

				PredmetNastavnikTipNastave_ pntn = new PredmetNastavnikTipNastave_();

				pntn.setSifNastavnik(nastavnik.getSifNastavnik());
				pntn.setSifPredmet(predmet.getSifPredmet());
				pntn.setSifTipNastave(tipNastave.getSifTipNastave());

				/*
				 * treba obratiti paznju na veze: 
				 * jedan nastavnik iz jednog predmeta moze drzati vise tipova nastave.
				 * jedan nastavnik, jedan tip nastave drzi na vise predmeta
				 * iz jednog predmeta,jednog tipa, samojedan nastavnik drzi nastavu.
				 * 
				 * to znaci da 
				 * 1. ako je vec unesen nastavnik za jedan tip predavanja za jedan predmet, 
				 * ne moze biti unesen neki drugi. tj provjeravamo da li vec postoji unos za jedan predmet i odgovarajuci tip nastave
				 * ako postoji, znaci da za konkretno taj tip nastave na tom predmetu vise ne mozemo unositi nikog
				 * 
				 * Treba provjeriti da se ovo zadovolji prije nego se ubaci u bazu podataka.
				 */
				
				/**
				 * dohvatimo sve n-torke iz baze PredmetNastavnikTipNastave
				 */
				ArrayList<PredmetNastavnikTipNastave_> pntnX = new ArrayList<PredmetNastavnikTipNastave_>();
				pntnX = PredmetNastavnikTipNastave.predmetNastavnikTipNastaveLista;
				
				/**
				 * postavimo kljuc, koji oznacava da li cemo upisivati ili necemo
				 */
				int lock = 0;
				
				for (int i = 0; i < pntnX.size(); i++) {
					PredmetNastavnikTipNastave_ pntnPom2 = new PredmetNastavnikTipNastave_();
					pntnPom2 = pntnX.get(i);
					
					/**
					 * provjera da li je unesen isti taj nastavnik za isti tip predavanja za isti predmet. tj da ne bude duplikat
					 */
					if(pntnPom2.getSifNastavnik() == nastavnik.getSifNastavnik() &&  pntnPom2.getSifTipNastave() == tipNastave.getSifTipNastave() 
							&& pntnPom2.getSifPredmet() == predmet.getSifPredmet())	{
						/**
						 * if je zadovoljen, znaci da vec postoji zapis, ne ubacuj opet
						 */
						lock = 1;
						System.out.println("Pokusaj unosa vec postojece n-torke");					
						break;
					}
					/**
					 * provjera stavke 1 iz gornjeg komentara
					 */
					if(pntnPom2.getSifTipNastave() == tipNastave.getSifTipNastave() 
							&& pntnPom2.getSifPredmet() == predmet.getSifPredmet()){
						lock = 1;
						System.out.println("Postoji vec profesor koji drzi " + tipNastave.getNazTipNastave() 
						+ " iz " + predmet.getNazPredmet());	
						break;
					}
					
				}

				/**
				 * ubacujemo tu stavku u bazu podataka:
				 */
				if (lock == 0) {
					try {
						DBExecutePredmetNastavnikTipNastave.insertPredmetNastavnikTipNastave(pntn);
						
						/**
						 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
						 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
						 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
						 */
						if(active2 == 1)
							TabelaVezaNastPred.frameTabelaVezaNastPred.dispose();
						TabelaVezaNastPred.startTabelaVezaNastPred();
						active1 = 1;
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
				else{
					System.out.println("Greska priliko uunosa");					
				}

			}
		});
		btnPotvrdiUnosVezaPNTN.setBounds(127, 155, 150, 25);
		frameVezaNastPred.getContentPane().add(btnPotvrdiUnosVezaPNTN);
	}
	
	
	private void fillComboBoxPredPredmeti() throws SQLException{
		PomocneF.clearListe();

		comboBoxVezaPredmet.removeAllItems();

		DBExecutePredmet.getPredmeti();
		ArrayList<Predmet_> predmeti = new ArrayList<Predmet_>();
		predmeti = Predmet.predmetLista;
		for (int i = 0; i < predmeti.size(); i++) {
			Predmet_ predmet = new Predmet_();	
			predmet = predmeti.get(i);
			comboBoxVezaPredmet.addItem(predmet.getNazPredmet());
		}		

	}
	
	private void fillComboBoxPredTipNastave() throws SQLException{
		PomocneF.clearListe();
		
		comboBoxVezaTipNastave.removeAllItems();

		DBExecuteTipNastave.getTipNastave();
		ArrayList<TipNastave_> tipoviNastave = new ArrayList<TipNastave_>();
		tipoviNastave = TipNastave.tipNastaveLista;
		for (int i = 0; i < tipoviNastave.size(); i++) {
			TipNastave_ tipNastave = new TipNastave_();	
			tipNastave = tipoviNastave.get(i);
			comboBoxVezaTipNastave.addItem(tipNastave.getNazTipNastave());
		}
	}
	
	private void fillComboBoxPredNastavnik() throws SQLException{
		PomocneF.clearListe();
		
		comboBoxVezaNastavnik.removeAllItems();

		DBExecuteNastavnik.getNastavnici();
		ArrayList<Nastavnik_> nastavnici = new ArrayList<Nastavnik_>();
		nastavnici = Nastavnik.nastavnikLista;
		for (int i = 0; i < nastavnici.size(); i++) {
			Nastavnik_ nastavnik = new Nastavnik_();	
			nastavnik = nastavnici.get(i);
			comboBoxVezaNastavnik.addItem(nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik());
		}
	}
}
