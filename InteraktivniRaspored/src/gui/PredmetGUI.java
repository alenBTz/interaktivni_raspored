package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataBase.DBExecutePredmet;
import dataBase.DBExecuteSemestar;
import modeli.Predmet_;
import modeli.Semestar_;
import pomocneF.PomocneF;
import tables.Predmet;
import tables.Semestar;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PredmetGUI {

	private JFrame framePredmet;
	private JTextField txtPredmet;
	private JTextField txtSkracenicapredmet;
	
	JComboBox<String> comboBoxPredSemestar = new JComboBox<String>();
	
	
	int active1 = 0;
	int active2 = 0;
	
	/**
	 * Launch the application.
	 */
	public static void startPredmetGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PredmetGUI window = new PredmetGUI();
					window.framePredmet.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PredmetGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		framePredmet = new JFrame();
		framePredmet.setBounds(100, 100, 425, 204);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		framePredmet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		framePredmet.getContentPane().setLayout(null);
		
		JButton btnPregledPredmeta = new JButton("Pregled Predmeta");
		btnPregledPredmeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				/**
				 * pozivamo prikaz tabele predmeta.
				 */
				/**
				 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active1 == 1)
					TabelaPredmetGUI.frameTabelaPredmet.dispose();
				TabelaPredmetGUI.startTabelaPredmetGUI();
				active2 = 1;
			
			}
		});
		btnPregledPredmeta.setBounds(122, 10, 200, 25);
		framePredmet.getContentPane().add(btnPregledPredmeta);
		
		JLabel lblPredmet = new JLabel("Predmet:");
		lblPredmet.setBounds(29, 49, 65, 15);
		framePredmet.getContentPane().add(lblPredmet);
		
		txtPredmet = new JTextField();
		txtPredmet.setText("");
		txtPredmet.setBounds(103, 47, 300, 19);
		framePredmet.getContentPane().add(txtPredmet);
		txtPredmet.setColumns(10);
		
		JLabel lblSkraenica = new JLabel("Skraćenica:");
		lblSkraenica.setBounds(12, 76, 82, 15);
		framePredmet.getContentPane().add(lblSkraenica);
		
		txtSkracenicapredmet = new JTextField();
		txtSkracenicapredmet.setText("");
		txtSkracenicapredmet.setBounds(103, 74, 300, 19);
		framePredmet.getContentPane().add(txtSkracenicapredmet);
		txtSkracenicapredmet.setColumns(10);
		
		JLabel lblSemestar = new JLabel("Semestar:");
		lblSemestar.setBounds(21, 103, 73, 15);
		framePredmet.getContentPane().add(lblSemestar);
		
		
		comboBoxPredSemestar.setBounds(103, 98, 300, 25);
		framePredmet.getContentPane().add(comboBoxPredSemestar);
		try {
			fillComboBoxUSemestru();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		JButton btnPotvrdiUnosPredmet = new JButton("Potvrdi unos");
		btnPotvrdiUnosPredmet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Treba unjeti novi predmet.
				 */
				/**
				 * Ako vec postoji lista predmeta i semestara, procitana iz BP, brisemo je, kako se ne bi postojali dupli podaci
				 */
				PomocneF.clearListe();		
				
				/**
				 * kreiramo varijablu 'predmet' u koju cemo smjestati unesene vrijednosti iz GUI-a
				 */
				Predmet_ predmet = new Predmet_();
				
				/*
				 * Upisujemo unesene vrijednosti u predmet.
				 */
				predmet.setNazPredmet(txtPredmet.getText());
				predmet.setKratPredmet(txtSkracenicapredmet.getText());

				/**
				 * Pošto trebamo upisati i semestar na kojem se predmet predaje, konektujemo se na BP i tabelu semestar
				 */
				//konektujemo se na baze semestar
				try {
					DBExecuteSemestar.getSemestri();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/**
				 * Iz comboBoxa dohvatamo index odabranog semestra.
				 */
				if (comboBoxPredSemestar.getSelectedIndex() == -1) {
					System.err.println("Pogresno unesen/odabran ili nije unesen/odabran semestar");
				} else {
					/**
					 * dohvatimo index odabranog elementa i upisujemo u pom
					 */
					int pom = comboBoxPredSemestar.getSelectedIndex();
					
					/**
					 * kreiramo varijablu 'semestar' koja ce nam sluziti da u nju iz BP upisemo trazeni semestar
					 */
					Semestar_ semestar = new Semestar_();
					
					/**
					 * u vektor semestri pokupimo sve n-torke iz tabele ssemestar
					 */
					ArrayList<Semestar_> semestri = new ArrayList<Semestar_>();
					semestri = Semestar.semestarLista;
					
					/**
					 * Na osnovu odabranog indexa iz ComboBoxa, trazimo odgovarajucu n-torku iz vektora i upisujemo u semestar.
					 * indeksi odgovarajucih elemenata su isti, jer se ComboBox upravo punio preko ovog vektora
					 */
					semestar = semestri.get(pom);

					/**
					 * Vrijednost sifre dohvacenog semestra upisujemo u polje sifSemestar od varijable 'predmet'
					 */
					predmet.setSifSemestar(semestar.getSifSemestar());

				}
				
				/**
				 * Varijabla 'predmet' je spremna da se upise u BP
				 */
				try {
					/**
					 * Upis u BP
					 */
					DBExecutePredmet.insertPredmet(predmet);
					
					/**
					 * Posto smo unjeli novi predmet, treba tabelu osvjeziti
					 */
					/**
					 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
					 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
					 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
					 */
					if(active2 == 1)
						TabelaPredmetGUI.frameTabelaPredmet.dispose();
					TabelaPredmetGUI.startTabelaPredmetGUI();
					active1 = 1;			

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				//osvjezavamo combobox jer smo unjeli novi predmet
				/*
				comboBoxPredPredmet.setBounds(400, 63, 271, 24);
				panelPredmeti.add(comboBoxPredPredmet);
				try {
					fillComboBoxPredPredmeti();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
		});
		btnPotvrdiUnosPredmet.setBounds(122, 135, 200, 25);
		framePredmet.getContentPane().add(btnPotvrdiUnosPredmet);
	}
	
	/**
	 * Metod koji puni ComboBox semestra
	 * @throws SQLException
	 */
	private void fillComboBoxUSemestru() throws SQLException {
		PomocneF.clearListe();
		comboBoxPredSemestar.removeAllItems();

		DBExecuteSemestar.getSemestri();
		ArrayList<Semestar_> semestri = new ArrayList<Semestar_>();
		semestri = Semestar.semestarLista;
		for (int i = 0; i < semestri.size(); i++) {
			Semestar_ semestarPom = new Semestar_();
			semestarPom = semestri.get(i);
			comboBoxPredSemestar.addItem(semestarPom.getNazSemestar());
		}
	}

}
