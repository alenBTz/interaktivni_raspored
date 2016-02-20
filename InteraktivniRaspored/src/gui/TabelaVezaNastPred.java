package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

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

import javax.swing.JScrollPane;

public class TabelaVezaNastPred {

	public static JFrame frameTabelaVezaNastPred;
	private JTable tableVezaNastPred;

	/**
	 * Launch the application.
	 */
	public static void startTabelaVezaNastPred() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaVezaNastPred window = new TabelaVezaNastPred();
					window.frameTabelaVezaNastPred.setVisible(true);
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
	public TabelaVezaNastPred() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frameTabelaVezaNastPred = new JFrame();
		frameTabelaVezaNastPred.setBounds(100, 100, 750, 500);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaVezaNastPred.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaVezaNastPred.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 724, 412);
		frameTabelaVezaNastPred.getContentPane().add(scrollPane);
		
		tableVezaNastPred = new JTable();
		scrollPane.setViewportView(tableVezaNastPred);
		tableVezaNastPred.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nastavnik", "Predmet", "Tip nastave"
			}
		));
		tableVezaNastPred.getColumnModel().getColumn(0).setPreferredWidth(273);
		tableVezaNastPred.getColumnModel().getColumn(1).setPreferredWidth(298);
		tableVezaNastPred.getColumnModel().getColumn(2).setPreferredWidth(224);
		
		popuniTabeluNastavnicimaPredmetima();
		
		
		JButton btnIzbriiVezu = new JButton("Izbriši vezu");
		btnIzbriiVezu.setBounds(12, 436, 117, 25);
		frameTabelaVezaNastPred.getContentPane().add(btnIzbriiVezu);
	}
	
	private void popuniTabeluNastavnicimaPredmetima() throws SQLException{

		DefaultTableModel model = (DefaultTableModel) tableVezaNastPred.getModel();
		
		/**
		 * Pozovemo ovaj metod kako ne bi bilo u tabeli ispis duplih elemenata. Resetuje tabelu, tj isprazni joj sadrzaj
		 */
		PomocneF.resetTable(model);
		
		/**
		 * Povezivanje sa bazama podataka
		 */
		DBExecutePredmetNastavnikTipNastave.getPredmetNastavnikTipNastave();
		DBExecuteNastavnik.getNastavnici();
		DBExecutePredmet.getPredmeti();
		DBExecuteTipNastave.getTipNastave();
		
		/**
		 * Kreiramo vektore koje cemo popuniti n-torkama iz svih navedenih BP
		 */
		ArrayList<PredmetNastavnikTipNastave_> pntnX = new ArrayList<PredmetNastavnikTipNastave_>();
		ArrayList<Nastavnik_> nastavnici = new ArrayList<Nastavnik_>();
		ArrayList<Predmet_> predmeti = new ArrayList<Predmet_>();
		ArrayList<TipNastave_> tipoviNastave = new ArrayList<TipNastave_>();
		
		/**
		 * Popunjavamo te vektore n-torkama
		 */
		pntnX = PredmetNastavnikTipNastave.predmetNastavnikTipNastaveLista;
		nastavnici = Nastavnik.nastavnikLista;
		predmeti = Predmet.predmetLista;
		tipoviNastave = TipNastave.tipNastaveLista;

		/**
		 * prolazimo kroz sve elemente vektora pntnX (tj sve n-torke iz baze PredmetNastavnikTipNastave su u tom vektoru)
		 */
		for (int i = 0; i < pntnX.size(); i++) {
			/**
			 * kreiramo varijable u koje cemo snimati po jednu dohvacenu n-torku iz vektora
			 */
			PredmetNastavnikTipNastave_ pntn = new PredmetNastavnikTipNastave_();
			Nastavnik_ nastavnik = new Nastavnik_();
			Predmet_ predmet = new Predmet_();
			TipNastave_ tipNastave = new TipNastave_();
			
			/**
			 * Uzimamo vrijednost n-torke iz tabele PredmetNastavnikTipNastave i snimamo u 'pntn'
			 */
			pntn = pntnX.get(i);
			
			/**
			 * u pntn imamo polja sifNastavnik, sifTipNastave, sifPredmet.
			 * Na osnovu tih polja mozemo dohvatiti odgovarajuce n-torke iz preostale 
			 * tri baze: Nastavnik, Predmet i tip nastave 
			 */
			
			for (int j = 0; j < nastavnici.size(); j++) {
				/**
				 * kreiramo nastavnikPom, ppomocnu varijablu.
				 */
				Nastavnik_ nastavnikPom = new Nastavnik_();
				/**
				 * u nastavnikPom snimamo trenutno dohvacenu n-torku
				 */
				nastavnikPom = nastavnici.get(j);
				/**
				 * Ako je ta n-torka jednaka onoj koja je bila u tabeli pntn, onda je zapamti i iskoci iz petlje, ako nije
				 * nastavi se vrtiti kroz petlju.
				 * provjeravamo na osnovu polja sifNastavnik
				 */
				if(nastavnikPom.getSifNastavnik() == pntn.getSifNastavnik()){
					nastavnik = nastavnikPom;
					break;
				}
			}
			/**
			 * postupak analogan gornjem.
			 */
			for (int j = 0; j < predmeti.size(); j++) {
				Predmet_ predmetPom = new Predmet_();
				predmetPom = predmeti.get(j);
				if(predmetPom.getSifPredmet() == pntn.getSifPredmet()){
					predmet = predmetPom;
					break;
				}
			}
			/**
			 * postupak analogan gornjem.
			 */
			for (int j = 0; j < tipoviNastave.size(); j++) {
				TipNastave_ tipNastavePom = new TipNastave_();
				tipNastavePom = tipoviNastave.get(j);
				if(tipNastavePom.getSifTipNastave() == pntn.getSifTipNastave()){
					tipNastave = tipNastavePom;
					break;
				}
			}
			
			/**
			 * Kako smo sad pokupili predmet, nastavnik, i tip nastave, mozemo ispisati u tabelu
			 */
			model.addRow(new Object[]{
					predmet.getNazPredmet(), nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik(), tipNastave.getNazTipNastave()
			});

		}
	}
}
