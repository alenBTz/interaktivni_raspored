package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import dataBase.DBExecuteGrupa;
import dataBase.DBExecutePredmet;
import dataBase.DBExecutePredmetGrupaTipNastave;
import dataBase.DBExecuteTipNastave;
import modeli.Grupa_;
import modeli.PredmetGrupaTipNastave_;
import modeli.Predmet_;
import modeli.TipNastave_;
import pomocneF.PomocneF;
import tables.Grupa;
import tables.Predmet;
import tables.PredmetGrupaTipNastave;
import tables.TipNastave;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VezaGrupaPredmetGUI {

	public static JFrame frameVezaGrupaPredmet;
	JComboBox<String> comboBoxVGrupe = new JComboBox<String>();
	JComboBox<String> comboBoxVPred = new JComboBox<String>();
	JComboBox<String> comboBoxVTipNastave = new JComboBox<String>();

	int active1 = 0;
	int active2 = 0;
	
	/**
	 * Launch the application.
	 */
	public static void startGrupaPredmetGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VezaGrupaPredmetGUI window = new VezaGrupaPredmetGUI();
					window.frameVezaGrupaPredmet.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VezaGrupaPredmetGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//komentar
		
		frameVezaGrupaPredmet = new JFrame();
		frameVezaGrupaPredmet.setBounds(100, 100, 362, 222);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		
		frameVezaGrupaPredmet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameVezaGrupaPredmet.getContentPane().setLayout(null);
		
		JButton btnPregledGrupaI = new JButton("Pregled Grupa i predmeta");
		btnPregledGrupaI.addActionListener(new ActionListener() {
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
					TabelaVGrupaPredmetGUI.frameTabelaGrupaPredmet.dispose();
				TabelaVGrupaPredmetGUI.startTabelaGrupaPredmet();
				active2 = 1;
			
			}
		});
		btnPregledGrupaI.setBounds(51, 12, 250, 25);
		frameVezaGrupaPredmet.getContentPane().add(btnPregledGrupaI);
		
		JLabel lblGrupa = new JLabel("Grupa:");
		lblGrupa.setBounds(51, 58, 48, 15);
		frameVezaGrupaPredmet.getContentPane().add(lblGrupa);
		
		comboBoxVGrupe.setBounds(117, 49, 220, 24);
		frameVezaGrupaPredmet.getContentPane().add(comboBoxVGrupe);
		try {
			fillComboBoxGrupe();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JLabel lblPredmet = new JLabel("Predmet:");
		lblPredmet.setBounds(34, 90, 65, 15);
		frameVezaGrupaPredmet.getContentPane().add(lblPredmet);
		
		comboBoxVPred.setBounds(117, 85, 220, 24);
		frameVezaGrupaPredmet.getContentPane().add(comboBoxVPred);
		try {
			fillComboBoxPredPredmeti();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JLabel lblTipNastave = new JLabel("Tip nastave:");
		lblTipNastave.setBounds(12, 126, 87, 15);
		frameVezaGrupaPredmet.getContentPane().add(lblTipNastave);
		
		comboBoxVTipNastave.setBounds(117, 121, 220, 24);
		frameVezaGrupaPredmet.getContentPane().add(comboBoxVTipNastave);
		try {
			fillComboBoxPredTipNastave();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JButton btnPotvrdiUnos = new JButton("Potvrdi unos");
		btnPotvrdiUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PomocneF.clearListe();
				
				/**
				 * kada pritisnemo na dugme, treba da povezemo grupu sa predmetom koju slusa i koji je tip nastave.
				 * trebamo se konektovati na bazu grupa, tipa nastave, predmeta i predmetGrupaTipNastave
				 */
				try {
					DBExecutePredmet.getPredmeti();
					DBExecuteTipNastave.getTipNastave();
					DBExecuteGrupa.getGrupe();
					DBExecutePredmetGrupaTipNastave.getPredmetGrupaTipNastave();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				/**
				 * Predmet: treba pokupimo vrijednosti odabrane iz comboboxa za Predmet i snimiti 'predmet'
				 */
				Predmet_ predmet = new Predmet_();

				if (comboBoxVPred.getSelectedIndex() == -1) {
					System.err.println("Nije odabrana nijedna stavkka");

				} else {
					/**
					 * dohvatimo index odabrane stavke, i taj index odgovara rednom broju u vektoru kojeg pokupimo iz BP
					 */
					int pom = comboBoxVPred.getSelectedIndex();
					
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

				if (comboBoxVTipNastave.getSelectedIndex() == -1) {
					System.err.println("Nije odabrana nijedna stavkka");

				} else {
					int pom = comboBoxVTipNastave.getSelectedIndex();

					ArrayList<TipNastave_> tipoviNastave = new ArrayList<TipNastave_>();
					tipoviNastave = TipNastave.tipNastaveLista;
					tipNastave = tipoviNastave.get(pom);;
				}

				/**
				 * Analogno gore iznad
				 */
				Grupa_ grupa = new Grupa_();

				if (comboBoxVGrupe.getSelectedIndex() == -1) {
					System.err.println("Nije odabrana nijedna stavkka");

				} else {
					int pom = comboBoxVGrupe.getSelectedIndex();

					ArrayList<Grupa_> grupe = new ArrayList<Grupa_>();
					grupe = Grupa.grupaLista;
					grupa = grupe.get(pom);;
				}

				/**
				 * Dohvacene n-torke ubacujemo u varijablu pgtn, koju cemo upisati u  BP
				 */

				PredmetGrupaTipNastave_ pgtn = new PredmetGrupaTipNastave_();

				pgtn.setSifGrupa(grupa.getSifGrupa());
				pgtn.setSifPredmet(predmet.getSifPredmet());
				pgtn.setSifTipNastave(tipNastave.getSifTipNastave());

				/*
				 * treba obratiti paznju na veze: 
				 * jedna grupa, iz jednog predmeta moze biti na vise tipova nastave (predavanja, lab, audit)
				 * jedna grupa, jedan tip nastave slusa iz vise predmeta
				 * jedan tip nastave, iz jednog predmeta, slusa vise grupa.
				 * to znaci da 
				 */
				
				/**
				 * dohvatimo sve n-torke iz baze PredmetGrupaTipNastave i upisemo u vektor pgtnX
				 */
				ArrayList<PredmetGrupaTipNastave_> pgtnX = new ArrayList<PredmetGrupaTipNastave_>();
				pgtnX = PredmetGrupaTipNastave.predmetGrupaTipNastaveLista;
				
				/**
				 * postavimo kljuc, koji oznacava da li cemo upisivati ili necemo
				 */
				int lock = 0;
				
				/**
				 * u for petlji provjeravamo da li u bazi vec postoji zapis koji zelimo unjeti.
				 */
				for (int i = 0; i < pgtnX.size(); i++) {
					/**
					 * kreiramo pomocnu varijablu, u koju upisujemo dohvacenu n-torku
					 */
					PredmetGrupaTipNastave_ pgtnPom2 = new PredmetGrupaTipNastave_();
					pgtnPom2 = pgtnX.get(i);
					
					/**
					 * provjera da li postoji n-torka u bazi. tj da ne bude duplikat
					 */
					if(pgtnPom2.getSifGrupa() == grupa.getSifGrupa() &&  pgtnPom2.getSifTipNastave() == tipNastave.getSifTipNastave() 
							&& pgtnPom2.getSifPredmet() == predmet.getSifPredmet())	{
						/**
						 * if uslov je zadovoljen, znaci da vec postoji zapis, ne ubacuj opet, iskoci iz petlje
						 */
						lock = 1;
						System.out.println("Pokusaj unosa vec postojece n-torke");					
						break;
					}
					
				}

				/**
				 * ubacujemo sadrzaj koji smo snimili u pgtn u bazu podataka:
				 */
				if (lock == 0) {
					try {
						DBExecutePredmetGrupaTipNastave.insertPredmetGrupaTipNastave(pgtn);
						
						/**
						 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
						 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
						 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
						 */
						if(active2 == 1)
							TabelaVGrupaPredmetGUI.frameTabelaGrupaPredmet.dispose();
						TabelaVGrupaPredmetGUI.startTabelaGrupaPredmet();
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
		btnPotvrdiUnos.setBounds(51, 159, 250, 25);
		frameVezaGrupaPredmet.getContentPane().add(btnPotvrdiUnos);
	}
	
	private void fillComboBoxPredPredmeti() throws SQLException{
		PomocneF.clearListe();

		comboBoxVPred.removeAllItems();

		DBExecutePredmet.getPredmeti();
		ArrayList<Predmet_> predmeti = new ArrayList<Predmet_>();
		predmeti = Predmet.predmetLista;
		for (int i = 0; i < predmeti.size(); i++) {
			Predmet_ predmet = new Predmet_();	
			predmet = predmeti.get(i);
			comboBoxVPred.addItem(predmet.getNazPredmet());
		}		

	}
	
	private void fillComboBoxPredTipNastave() throws SQLException{
		PomocneF.clearListe();
		
		comboBoxVTipNastave.removeAllItems();

		DBExecuteTipNastave.getTipNastave();
		ArrayList<TipNastave_> tipoviNastave = new ArrayList<TipNastave_>();
		tipoviNastave = TipNastave.tipNastaveLista;
		for (int i = 0; i < tipoviNastave.size(); i++) {
			TipNastave_ tipNastave = new TipNastave_();	
			tipNastave = tipoviNastave.get(i);
			comboBoxVTipNastave.addItem(tipNastave.getNazTipNastave());
		}
	}
	
	private void fillComboBoxGrupe() throws SQLException{
		PomocneF.clearListe();
		
		comboBoxVGrupe.removeAllItems();

		DBExecuteGrupa.getGrupe();
		ArrayList<Grupa_> grupe = new ArrayList<Grupa_>();
		grupe = Grupa.grupaLista;
		for (int i = 0; i < grupe.size(); i++) {
			Grupa_ grupa = new Grupa_();	
			grupa = grupe.get(i);
			comboBoxVGrupe.addItem(grupa.getNazivGrupa());
		}
	}
	
}
