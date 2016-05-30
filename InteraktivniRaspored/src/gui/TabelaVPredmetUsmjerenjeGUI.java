package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dataBase.DBExecuteIzborni;
import dataBase.DBExecutePredmet;
import dataBase.DBExecutePredmetUsmjerenjeIzborni;
import dataBase.DBExecuteUsmjerenje;
import modeli.Izborni_;
import modeli.PredmetUsmjerenjeIzborni_;
import modeli.Predmet_;
import modeli.Usmjerenje_;
import pomocneF.IzbrisiRed;
import pomocneF.PomocneF;
import tables.Izborni;
import tables.Predmet;
import tables.PredmetUsmjerenjeIzborni;
import tables.Usmjerenje;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabelaVPredmetUsmjerenjeGUI {

	public static JFrame frameTabelaPredmUsmj;
	private JTable tablePredmUsmj;
	private DefaultTableModel modelVezaPredUsmjerenja;
	/**
	 * Launch the application.
	 */
	public static void startTabelaVPredmUsmj() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaVPredmetUsmjerenjeGUI window = new TabelaVPredmetUsmjerenjeGUI();
					window.frameTabelaPredmUsmj.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabelaVPredmetUsmjerenjeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//komentar
		
		frameTabelaPredmUsmj = new JFrame();
		frameTabelaPredmUsmj.setBounds(100, 100, 670, 499);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaPredmUsmj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaPredmUsmj.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 642, 411);
		frameTabelaPredmUsmj.getContentPane().add(scrollPane);
		
		tablePredmUsmj = new JTable();
		scrollPane.setViewportView(tablePredmUsmj);
		tablePredmUsmj.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sifra","Usmjerenje", "Predmet"
			}
		));
		tablePredmUsmj.getColumnModel().getColumn(1).setPreferredWidth(255);
		tablePredmUsmj.getColumnModel().getColumn(2).setPreferredWidth(274);
		
		try {
			popuniTabeluUsmjerPredmetima();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		JButton btnIzbriiOdabraniRed = new JButton("Izbriši odabrani red");
		btnIzbriiOdabraniRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int red = tablePredmUsmj.getSelectedRow();

				if(red != -1)
				{
					try {
						String imePredmeta = tablePredmUsmj.getValueAt(red, 2).toString();
						//Object id = tablePredmUsmj.getValueAt(red, 0);
						PredmetUsmjerenjeIzborni_ pui = new PredmetUsmjerenjeIzborni_();
						pui.setSifIzborni(2);
						pui.setSifPredmet(DBExecutePredmet.getPredmetByName(imePredmeta).getSifPredmet());
						pui.setSifUsmjerenje(14);
						DBExecutePredmetUsmjerenjeIzborni.updatePredmetUsmjerenje(pui);
						//IzbrisiRed.izbrisiRed(id,"sifPredmUsmjIzborni","predmetusmjerenjeizborni");
						
						modelVezaPredUsmjerenja.removeRow(red);
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
		btnIzbriiOdabraniRed.setBounds(12, 435, 200, 25);
		frameTabelaPredmUsmj.getContentPane().add(btnIzbriiOdabraniRed);
		
		
	}
	
	private void popuniTabeluUsmjerPredmetima() throws SQLException{
		/**
		 * U tabeli prikazujemo samo obavezne predmete po smjerovima. Svi predmeti su postavljeni kao izborni za svaki smijer, i BP je vec popunjena 
		 * tim n-torkama. U tabelu samo izdvajamo n-torke gdje je predmet obavezam za odgovarajuci smijer
		 */
		
		
		modelVezaPredUsmjerenja = (DefaultTableModel) tablePredmUsmj.getModel();
		
		/**
		 * Pozovemo ovaj metod kako ne bi bilo u tabeli ispis duplih elemenata. Resetuje tabelu, tj isprazni joj sadrzaj
		 */
		PomocneF.resetTable(modelVezaPredUsmjerenja);
		
		/**
		 * Povezivanje sa bazama podataka
		 */
		DBExecutePredmetUsmjerenjeIzborni.getPredmetUsmjerenja();
		DBExecuteIzborni.getIzborni();
		DBExecutePredmet.getPredmeti();
		DBExecuteUsmjerenje.getUsmjerenja();
		
		/**
		 * Kreiramo vektore koje cemo popuniti n-torkama iz svih navedenih BP
		 */
		ArrayList<PredmetUsmjerenjeIzborni_> puiX = new ArrayList<PredmetUsmjerenjeIzborni_>();
		ArrayList<Izborni_> izborniX = new ArrayList<Izborni_>();
		ArrayList<Predmet_> predmeti = new ArrayList<Predmet_>();
		ArrayList<Usmjerenje_> usmjerenja = new ArrayList<Usmjerenje_>();
		
		/**
		 * Popunjavamo te vektore n-torkama
		 */
		puiX = PredmetUsmjerenjeIzborni.predmetUsmjerenjeIzborniLista;
		izborniX = Izborni.izborniLista;
		predmeti = Predmet.predmetLista;
		usmjerenja = Usmjerenje.usmjerenjeLista;

		/**
		 * prolazimo kroz sve elemente vektora puiX (tj sve n-torke iz baze PredmetUsmjerenjeIzborni su u tom vektoru)
		 */
		for (int i = 0; i < puiX.size(); i++) {
			
			/**
			 * kreiramo varijable u koje cemo snimati po jednu dohvacenu n-torku iz vektora
			 */
			PredmetUsmjerenjeIzborni_ pui = new PredmetUsmjerenjeIzborni_();
			Izborni_ izborni = new Izborni_();
			Predmet_ predmet = new Predmet_();
			Usmjerenje_ usmjerenje = new Usmjerenje_();
			
			/**
			 * Uzimamo vrijednost n-torke iz tabele PredmetUsmjerenjeIzborni i snimamo u 'pui'
			 */
			pui = puiX.get(i);
			String sifPredmUsmjIzborni = String.valueOf(pui.getSifPredmUsmjIzborni());

			/**
			 * u pui imamo polja sifPredmet, sifUsmjerenje, sifIzborni.
			 * Na osnovu tih polja mozemo dohvatiti odgovarajuce n-torke iz preostale 
			 * tri baze: Usmjerenje, Predmet i Izborni 
			 */
			
			for (int j = 0; j < izborniX.size(); j++) {
				/**
				 * kreiramo nastavnikPom, ppomocnu varijablu.
				 */
				Izborni_ izborniPom = new Izborni_();
				/**
				 * u izborniPom snimamo trenutno dohvacenu n-torku
				 */
				izborniPom = izborniX.get(j);
				/**
				 * Ako je ta n-torka jednaka onoj koja je bila u tabeli puiX, onda je zapamti i iskoci iz petlje, ako nije
				 * nastavi se vrtiti kroz petlju.
				 * provjeravamo na osnovu polja sifNastavnik
				 */
				if(izborniPom.getSifIzborni() == pui.getSifIzborni()){
					izborni = izborniPom;
					break;
				}
			}
			/**
			 * postupak analogan gornjem.
			 */
			for (int j = 0; j < usmjerenja.size(); j++) {
				Usmjerenje_ usmjerenjePom = new Usmjerenje_();
				usmjerenjePom = usmjerenja.get(j);
				if(usmjerenjePom.getSifUsmjerenje() == pui.getSifUsmjerenje()){
					usmjerenje = usmjerenjePom;
					break;
				}
			}
			/**
			 * postupak analogan gornjem.
			 */
			for (int j = 0; j < predmeti.size(); j++) {
				Predmet_ predmetPom = new Predmet_();
				predmetPom = predmeti.get(j);
				if(predmetPom.getSifPredmet() == pui.getSifPredmet()){
					predmet = predmetPom;
					break;
				}
			}
			
			/**
			 * Uzimamo samo obavezne predmete, tj da je poje pui.getSifIzborni = 1 tj da je predmet OBAVEZAN
			 */
			if (pui.getSifIzborni() == 1) {
				/**
				 * Kako smo sad pokupili predmet, usmjerenje, i izborni, mozemo ispisati u tabelu
				 * U tabeli necemo ispisavit polja "izborni", jer tabela sadrzi samo predmete koji su obavezni na odredjenom smjeru.
				 * Vec su ranije u bazu unjeti samo obavezni predmeti za odgovarajuci smijer.
				 */
				modelVezaPredUsmjerenja.addRow(new Object[]{
						sifPredmUsmjIzborni,usmjerenje.getNazUsmjerenje(), predmet.getNazPredmet()
				});
				
			}
			

		}
	}
}
