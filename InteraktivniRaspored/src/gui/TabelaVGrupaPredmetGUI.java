package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

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

import javax.swing.JScrollPane;

public class TabelaVGrupaPredmetGUI {

	public static JFrame frameTabelaGrupaPredmet;
	private JTable tableGrupaPredmetTipN;

	/**
	 * Launch the application.
	 */
	public static void startTabelaGrupaPredmet() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaVGrupaPredmetGUI window = new TabelaVGrupaPredmetGUI();
					window.frameTabelaGrupaPredmet.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabelaVGrupaPredmetGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//komentar
		
		frameTabelaGrupaPredmet = new JFrame();
		frameTabelaGrupaPredmet.setBounds(100, 100, 600, 500);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaGrupaPredmet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaGrupaPredmet.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 574, 412);
		frameTabelaGrupaPredmet.getContentPane().add(scrollPane);
		
		tableGrupaPredmetTipN = new JTable();
		scrollPane.setViewportView(tableGrupaPredmetTipN);
		tableGrupaPredmetTipN.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Grupa", "Predmet", "Tip nastave"
			}
		));
		tableGrupaPredmetTipN.getColumnModel().getColumn(0).setPreferredWidth(106);
		tableGrupaPredmetTipN.getColumnModel().getColumn(1).setPreferredWidth(249);
		tableGrupaPredmetTipN.getColumnModel().getColumn(2).setPreferredWidth(199);
		
		try {
			popuniTabeluGrupaPredmetima();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton btnIzbriiStavku = new JButton("Izbriši stavku");
		btnIzbriiStavku.setBounds(12, 436, 150, 25);
		frameTabelaGrupaPredmet.getContentPane().add(btnIzbriiStavku);
	}
	
	private void popuniTabeluGrupaPredmetima() throws SQLException{

		DefaultTableModel model = (DefaultTableModel) tableGrupaPredmetTipN.getModel();
		
		/**
		 * Pozovemo ovaj metod kako ne bi bilo u tabeli ispis duplih elemenata. Resetuje tabelu, tj isprazni joj sadrzaj
		 */
		PomocneF.resetTable(model);
		
		/**
		 * Povezivanje sa bazama podataka
		 */
		DBExecutePredmet.getPredmeti();
		DBExecuteGrupa.getGrupe();
		DBExecuteTipNastave.getTipNastave();
		DBExecutePredmetGrupaTipNastave.getPredmetGrupaTipNastave();
		/**
		 * Kreiramo vektore koje cemo popuniti n-torkama iz svih navedenih BP
		 */
		ArrayList<PredmetGrupaTipNastave_> pgtnX = new ArrayList<PredmetGrupaTipNastave_>();
		ArrayList<Grupa_> grupe= new ArrayList<Grupa_>();
		ArrayList<Predmet_> predmeti = new ArrayList<Predmet_>();
		ArrayList<TipNastave_> tipoviNastave = new ArrayList<TipNastave_>();
		
		/**
		 * Popunjavamo te vektore n-torkama
		 */
		pgtnX = PredmetGrupaTipNastave.predmetGrupaTipNastaveLista;
		grupe = Grupa.grupaLista;
		predmeti = Predmet.predmetLista;
		tipoviNastave = TipNastave.tipNastaveLista;

		/**
		 * prolazimo kroz sve elemente vektora pgtnX (tj sve n-torke iz baze PredmetGrupaTipNastave su u tom vektoru)
		 */
		for (int i = 0; i < pgtnX.size(); i++) {
			/**
			 * kreiramo varijable u koje cemo snimati po jednu dohvacenu n-torku iz vektora
			 */
			PredmetGrupaTipNastave_ pgtn = new PredmetGrupaTipNastave_();
			Grupa_ grupa = new Grupa_();
			Predmet_ predmet = new Predmet_();
			TipNastave_ tipNastave = new TipNastave_();
			
			/**
			 * Uzimamo vrijednost n-torke iz tabele PredmetGrupaTipNastave i snimamo u 'pgtn'
			 */
			pgtn = pgtnX.get(i);
			
			/**
			 * u pgtn imamo polja sifGrupa, sifTipNastave, sifPredmet.
			 * Na osnovu tih polja mozemo dohvatiti odgovarajuce n-torke iz preostale 
			 * tri baze: Grupa, Predmet i TipNastave
			 */
			
			for (int j = 0; j < grupe.size(); j++) {
				/**
				 * kreiramo grupaPom, ppomocnu varijablu.
				 */
				Grupa_ grupaPom = new Grupa_();
				/**
				 * u nastavnikPom snimamo trenutno dohvacenu n-torku
				 */
				grupaPom = grupe.get(j);
				/**
				 * Ako je ta n-torka jednaka onoj koja je bila u tabeli pgtn, onda je zapamti i iskoci iz petlje, ako nije
				 * nastavi se vrtiti kroz petlju.
				 * provjeravamo na osnovu polja sifNastavnik
				 */
				if(grupaPom.getSifGrupa() == pgtn.getSifGrupa()){
					grupa = grupaPom;
					break;
				}
			}
			/**
			 * postupak analogan gornjem.
			 */
			for (int j = 0; j < predmeti.size(); j++) {
				Predmet_ predmetPom = new Predmet_();
				predmetPom = predmeti.get(j);
				if(predmetPom.getSifPredmet() == pgtn.getSifPredmet()){
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
				if(tipNastavePom.getSifTipNastave() == pgtn.getSifTipNastave()){
					tipNastave = tipNastavePom;
					break;
				}
			}
			
			/**
			 * Kako smo sad pokupili predmet, grupu, i tip nastave, mozemo ispisati u tabelu
			 */
			model.addRow(new Object[]{
					grupa.getNazivGrupa(), predmet.getNazPredmet(), tipNastave.getNazTipNastave()
			});

		}
	}
}
