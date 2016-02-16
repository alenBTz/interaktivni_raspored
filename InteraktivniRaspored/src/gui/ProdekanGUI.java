package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataBase.DBExecuteNastavnik;
import dataBase.DBExecutePredmet;
import dataBase.DBExecuteSala;
import dataBase.DBExecuteSemestar;
import dataBase.DBExecuteUsmjerenje;
import dataBase.DBExecuteZgrada;
import modeli.Nastavnik_;
import modeli.Predmet_;
import modeli.Sala_;
import modeli.Semestar_;
import modeli.Usmjerenje_;
import modeli.Zgrada_;
import tables.Nastavnik;
import tables.Predmet;
import tables.Sala;
import tables.Semestar;
import tables.Usmjerenje;
import tables.Zgrada;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;

public class ProdekanGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtIme;
	private JTextField txtPrezime;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTable table;
	private JTextField txtNazivzgrade;
	private JTextField txtKratzgrada;
	private JTable table_2;
	private JTextField txtNazsala;
	private JTextField txtKratsala;
	private JTextField txtBrmjesta;
	private JTable table_4;
	private JTable table_5;
	private JTextField txtNazivUsmjerenja;
	private JTextField txtKratUsmjerenja;
	private JTable tableSemestar;

	private JTextField txtNazivPredmeta;
	private JTextField txtKratpredmet;
	private JTextField txtNazsemestar;
	private JTable tablePredmet;
	
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	JPanel panelNastavnici = new JPanel();
	JPanel panelZgrade = new JPanel();
	JPanel panelSale = new JPanel();
	JPanel panelUsmjerenja = new JPanel();
	JPanel panelPredmeti = new JPanel();
	JPanel panelSemestar = new JPanel();
	JPanel panelGrupe = new JPanel();
	JPanel panelRaspored = new JPanel();
	
    JComboBox<String> comboBox_2 = new JComboBox<String>();
	JComboBox<String> comboBoxPredajeNast = new JComboBox<String>();
	JComboBox<String> comboBoxTipNastavnika = new JComboBox<String>();
	
	ActionListener buttonListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			
			clearListe();
			
			Zgrada_ zgrada = new Zgrada_();
			
			zgrada.setNazZgrada(txtNazivzgrade.getText());
			zgrada.setKratZgrada(txtKratzgrada.getText());
			
			try {
				DBExecuteZgrada.insertZgrada(zgrada);
				popuniTabeluZgradama();
				
				comboBox_2.setBounds(12, 240, 200, 24);
				panelSale.add(comboBox_2);
				
				fillComboBoxSala();
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		
	};
	
	
	ActionListener buttonListenerNastavnik = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			
			clearListe();
			
			Nastavnik_ nastavnik = new Nastavnik_();
			
			nastavnik.setImeNastavnik(txtIme.getText());
			nastavnik.setPrezNastavnik(txtPrezime.getText());
			nastavnik.setUsername(txtUsername.getText());
			nastavnik.setPassword(txtPassword.getText());
			if (comboBoxTipNastavnika.getSelectedIndex() == -1) {
				System.err.println("Pogresno unesen ili nije unesen tip nastavnika");
			} else {
				nastavnik.setVrstaNastavnik(comboBoxTipNastavnika.getSelectedIndex());
			}
			
			try {
				DBExecuteNastavnik.insertNastavnik(nastavnik);
				popuniTabeluNastavnicima();

				comboBoxPredajeNast.setBounds(113, 245, 206, 24);
				panelPredmeti.add(comboBoxPredajeNast);
				
				fillComboBoxPredmet();
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	};
	private JTable table_3;
	

	/**
	 * Launch the application.
	 */
	public static void startProdekanGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdekanGUI frame = new ProdekanGUI();
					frame.setVisible(true);
					
					DBExecuteNastavnik.getNastavnici();

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ProdekanGUI() throws SQLException {

		initJTPane();
		
		initPanelNastavnici();		
		
		initPanelZgrade();		
		
		initPanelSale();		
			
		initPanelUsmjerenja();		

		initPanelSemestar();
	
		initPanelPredmeti();
		
		
		/**
		 * Grupe
		 */
		tabbedPane.addTab("Grupe", null, panelGrupe, null);
		panelGrupe.setLayout(null);
		
		/**
		 * Raspored
		 */
		tabbedPane.addTab("Raspored", null, panelRaspored, null);
		
		
		

	}

	private void initPanelPredmeti() throws SQLException {
		tabbedPane.addTab("Predmeti", null, panelPredmeti, null);
		panelPredmeti.setLayout(null);
		
		JLabel lblUnosPredmeta = new JLabel("Unos podataka o predmetima:");
		lblUnosPredmeta.setBounds(12, 12, 250, 15);
		panelPredmeti.add(lblUnosPredmeta);
		
		JLabel lblNazivPredmeta = new JLabel("Naziv predmeta:");
		lblNazivPredmeta.setBounds(12, 39, 150, 15);
		panelPredmeti.add(lblNazivPredmeta);
		
		txtNazivPredmeta = new JTextField();
		txtNazivPredmeta.setText("");
		txtNazivPredmeta.setBounds(12, 66, 305, 19);
		panelPredmeti.add(txtNazivPredmeta);
		txtNazivPredmeta.setColumns(10);
		
		JLabel lblSkraenicaNazivaPredmeta = new JLabel("Skraćenica naziva predmeta:");
		lblSkraenicaNazivaPredmeta.setBounds(340, 39, 250, 15);
		panelPredmeti.add(lblSkraenicaNazivaPredmeta);
		
		txtKratpredmet = new JTextField();
		txtKratpredmet.setText("");
		txtKratpredmet.setBounds(340, 66, 250, 19);
		panelPredmeti.add(txtKratpredmet);
		txtKratpredmet.setColumns(10);
		
		JLabel lblKojemSemestruPripada = new JLabel("Predaje se u:");
		lblKojemSemestruPripada.setBounds(609, 39, 150, 15);
		panelPredmeti.add(lblKojemSemestruPripada);
		
		final JComboBox comboBoxUSemestru = new JComboBox();
		comboBoxUSemestru.setBounds(609, 63, 150, 24);
		panelPredmeti.add(comboBoxUSemestru);
		
		clearListe();
		comboBoxUSemestru.removeAllItems();
		DBExecuteSemestar.getSemestri();
		ArrayList<Semestar_> semestri = new ArrayList<Semestar_>();
		semestri = Semestar.semestarLista;
		for (int i = 0; i < semestri.size(); i++) {
			Semestar_ semestarPom = new Semestar_();
			semestarPom = semestri.get(i);
			comboBoxUSemestru.addItem(semestarPom.getNazSemestar());
		}
		

		
		
		JLabel lblIzborni = new JLabel("Izborni:");
		lblIzborni.setBounds(208, 97, 70, 15);
		panelPredmeti.add(lblIzborni);
		
		final JRadioButton rdbtnDa = new JRadioButton("Da");
		rdbtnDa.setBounds(208, 125, 45, 23);
		panelPredmeti.add(rdbtnDa);
		
		final JRadioButton rdbtnNe = new JRadioButton("Ne");
		rdbtnNe.setBounds(272, 125, 45, 23);
		panelPredmeti.add(rdbtnNe);
		
		ButtonGroup groupIzborni = new ButtonGroup();
		groupIzborni.add(rdbtnDa);
		groupIzborni.add(rdbtnNe);

		
		
		JLabel lblTipNastave = new JLabel("Tip nastave:");
		lblTipNastave.setBounds(12, 97, 150, 15);
		panelPredmeti.add(lblTipNastave);
		
		final JComboBox<String> comboBoxTipNastave = new JComboBox<String>();
		comboBoxTipNastave.setBounds(12, 124, 178, 24);
		panelPredmeti.add(comboBoxTipNastave);
		
		comboBoxTipNastave.addItem("Predavanja");
		comboBoxTipNastave.addItem("Auditorne vježbe");
		comboBoxTipNastave.addItem("Laboratorijske vježbe");


		
		
		JLabel lblPredaje = new JLabel("Predaje:");
		lblPredaje.setBounds(340, 97, 70, 15);
		panelPredmeti.add(lblPredaje);
		
		comboBoxPredajeNast.setBounds(340, 124, 250, 24);
		panelPredmeti.add(comboBoxPredajeNast);
		
		fillComboBoxPredmet();
		

		
		
		JButton btnPotvrdiUnos_4 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DBExecuteNastavnik.getNastavnici();
					DBExecuteSemestar.getSemestri();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //trebaju nam svi nastavnici
				
				Predmet_ predmet = new Predmet_();
				
				predmet.setNazPredmet(txtNazivPredmeta.getText());
				predmet.setKratPredmet(txtKratpredmet.getText());
				
				
				if (comboBoxTipNastave.getSelectedIndex() == -1) {
					System.err.println("Pogresno unesen ili nije unesen tip nastavnika");
				} else {
					if(comboBoxTipNastave.getSelectedIndex() == 0)
						predmet.setTipNastave("Predavanja");
					else if (comboBoxTipNastave.getSelectedIndex() == 1) 
						predmet.setTipNastave("Auditorne vježbe");
					else if (comboBoxTipNastave.getSelectedIndex() == 2) 
						predmet.setTipNastave("Laboratorijske vježbe");
					
				}
								
				if (rdbtnDa.isSelected()) 
					predmet.setIzborni(1);
				else if(rdbtnNe.isSelected())
					predmet.setIzborni(0);

				
				/**
				 * Za Semestar 
				 */
				if (comboBoxUSemestru.getSelectedIndex() == -1) {
					System.err.println("Pogresno unesen ili nije unesen semestar");
				} else {
					int pom = comboBoxUSemestru.getSelectedIndex();
					
					Semestar_ semestar = new Semestar_();
					ArrayList<Semestar_> semestri = new ArrayList<Semestar_>();
					semestri = Semestar.semestarLista;
					semestar = semestri.get(pom);
					
					int pom2 = semestar.getSifSemestar();
					predmet.setSifSemestar(pom2);
					System.out.println("---------SifSemestar");
					System.out.println(pom2);
					System.out.println("---------");
				}		
				
				/*
				 * Za Nastavnika 
				 */
				if (comboBoxPredajeNast.getSelectedIndex() == -1) {
					System.err.println("Pogresno unesen ili nije unesen nastavnik");
				} else {
					int pom3 = comboBoxPredajeNast.getSelectedIndex();
					
					Nastavnik_ nastavnik = new Nastavnik_();
					ArrayList<Nastavnik_> nastavnici = new ArrayList<Nastavnik_>();
					nastavnici = Nastavnik.nastavnikLista;
					nastavnik = nastavnici.get(pom3);
					
					int pom4 = nastavnik.getSifNastavnik();
					predmet.setSifNastavnik(pom4);
					System.out.println("---------SifNastavnik");
					System.out.println(pom4);
					System.out.println("---------");
				}
				
				/**
				 * Unos u bazu podataka u tabelu predmet
				 */
				try {
					DBExecutePredmet.insertPredmet(predmet);
					popuniTabeluPredmetima();				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});
		btnPotvrdiUnos_4.setBounds(609, 124, 150, 25);
		panelPredmeti.add(btnPotvrdiUnos_4);
		
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 165, 747, 142);
		panelPredmeti.add(scrollPane);
		
		tablePredmet = new JTable();
		scrollPane.setViewportView(tablePredmet);
		tablePredmet.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sifra", "Predmet", "Skracenica", "Vrsta Nastave", "Predaje", "Izborni", "Semestar"
			}
		));
		
		popuniTabeluPredmetima();				

		
		tablePredmet.getColumnModel().getColumn(0).setPreferredWidth(35);
		tablePredmet.getColumnModel().getColumn(1).setPreferredWidth(270);
		tablePredmet.getColumnModel().getColumn(2).setPreferredWidth(70);
		tablePredmet.getColumnModel().getColumn(3).setPreferredWidth(160);
		tablePredmet.getColumnModel().getColumn(4).setPreferredWidth(200);
		tablePredmet.getColumnModel().getColumn(5).setPreferredWidth(50);
		tablePredmet.getColumnModel().getColumn(6).setPreferredWidth(70);
		
		
		tablePredmet.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
			}
		});
		
		
	}

	

	private void initPanelSemestar() throws SQLException {
		tabbedPane.addTab("Semestar", null, panelSemestar, null);
		panelSemestar.setLayout(null);
		
		JLabel lblPodaciOSemestrima = new JLabel("Unos podataka o semestrima:");
		lblPodaciOSemestrima.setBounds(12, 12, 230, 15);
		panelSemestar.add(lblPodaciOSemestrima);
		
		JLabel lblNazivSemestra = new JLabel("Naziv semestra:");
		lblNazivSemestra.setBounds(12, 39, 150, 15);
		panelSemestar.add(lblNazivSemestra);
		
		txtNazsemestar = new JTextField();
		txtNazsemestar.setText("nazSemestar");
		txtNazsemestar.setBounds(12, 66, 305, 19);
		panelSemestar.add(txtNazsemestar);
		txtNazsemestar.setColumns(10);
		
		JButton btnPotvrdiUnos_3 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Semestar_ semestar= new Semestar_();
				semestar.setNazSemestar(txtNazsemestar.getText());
				
				try {
					DBExecuteSemestar.insertSemestar(semestar);
					popuniTabeluSemestrima();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnPotvrdiUnos_3.setBounds(12, 97, 150, 25);
		panelSemestar.add(btnPotvrdiUnos_3);
		
		JScrollPane scrollPaneSemestar = new JScrollPane();
		scrollPaneSemestar.setBounds(329, 12, 430, 289);
		panelSemestar.add(scrollPaneSemestar);
		
		tableSemestar = new JTable();
		scrollPaneSemestar.setViewportView(tableSemestar);
		tableSemestar.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sifra", "Naziv semestra"
			}
		));
		
		popuniTabeluSemestrima();
		
		tableSemestar.getColumnModel().getColumn(0).setPreferredWidth(61);
		tableSemestar.getColumnModel().getColumn(1).setPreferredWidth(356);
		
		table_2.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
			}
		});
	}

	private void initPanelUsmjerenja() throws SQLException {
		tabbedPane.addTab("Usmjerenja", null, panelUsmjerenja, null);
		panelUsmjerenja.setLayout(null);
		
		JLabel lblUnosNovogUsmjerenja = new JLabel("Unos podataka o usmjerenjima:");
		lblUnosNovogUsmjerenja.setBounds(12, 12, 305, 15);
		panelUsmjerenja.add(lblUnosNovogUsmjerenja);
		
		JLabel lblNazUsmjerenja = new JLabel("Naziv usmjerenja:");
		lblNazUsmjerenja.setBounds(12, 39, 150, 15);
		panelUsmjerenja.add(lblNazUsmjerenja);
		
		txtNazivUsmjerenja = new JTextField();
		txtNazivUsmjerenja.setText("");
		txtNazivUsmjerenja.setBounds(12, 66, 305, 19);
		panelUsmjerenja.add(txtNazivUsmjerenja);
		txtNazivUsmjerenja.setColumns(10);
		
		JLabel lblKratUsmjerenja = new JLabel("Skraćenica za usmjerenje:");
		lblKratUsmjerenja.setBounds(12, 97, 250, 15);
		panelUsmjerenja.add(lblKratUsmjerenja);
		
		txtKratUsmjerenja = new JTextField();
		txtKratUsmjerenja.setText("");
		txtKratUsmjerenja.setBounds(12, 124, 305, 19);
		panelUsmjerenja.add(txtKratUsmjerenja);
		txtKratUsmjerenja.setColumns(10);
		
		JButton btnPotvrdiUnosUsmjerenja = new JButton("Potvrdi unos");
		btnPotvrdiUnosUsmjerenja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				Usmjerenje_ usmjerenje = new Usmjerenje_();
				
				usmjerenje.setNazUsmjerenje(txtNazivUsmjerenja.getText());
				usmjerenje.setKratUsmjerenje(txtKratUsmjerenja.getText());
				
				try {
					DBExecuteUsmjerenje.insertUsmjerenje(usmjerenje);
					popuniTabeluUsmjerenjima();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnPotvrdiUnosUsmjerenja.setBounds(12, 155, 150, 25);
		panelUsmjerenja.add(btnPotvrdiUnosUsmjerenja);
		
		JScrollPane scrollPaneUsmjerenja = new JScrollPane();
		scrollPaneUsmjerenja.setBounds(329, 12, 430, 289);
		panelUsmjerenja.add(scrollPaneUsmjerenja);
		
		table_5 = new JTable();
		scrollPaneUsmjerenja.setViewportView(table_5);
		table_5.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sif", "Naziv usmjerenja", "Skracenica za naziv"
			}
		));
		
		popuniTabeluUsmjerenjima();
		
		table_5.getColumnModel().getColumn(0).setPreferredWidth(61);
		table_5.getColumnModel().getColumn(1).setPreferredWidth(356);
		table_5.getColumnModel().getColumn(2).setPreferredWidth(204);
		
		table_5.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
			}
		});
	}

	private void initPanelSale() throws SQLException {
		tabbedPane.addTab("Sale", null, panelSale, null);
		panelSale.setLayout(null);
				
		JLabel lblUnosPodatakaO = new JLabel("Unos podataka o salama:");
		lblUnosPodatakaO.setBounds(12, 12, 200, 15);
		panelSale.add(lblUnosPodatakaO);
		
		JLabel lblNazivSale = new JLabel("Naziv sale:");
		lblNazivSale.setBounds(12, 39, 150, 15);
		panelSale.add(lblNazivSale);
		
		txtNazsala = new JTextField();
		txtNazsala.setText("nazSala");
		txtNazsala.setBounds(12, 66, 200, 19);
		panelSale.add(txtNazsala);
		txtNazsala.setColumns(10);
		
		JLabel lblSkraenicaNazivaSale = new JLabel("Skraćenica naziva sale:");
		lblSkraenicaNazivaSale.setBounds(12, 97, 200, 15);
		panelSale.add(lblSkraenicaNazivaSale);
		
		txtKratsala = new JTextField();
		txtKratsala.setText("kratSala");
		txtKratsala.setBounds(12, 124, 200, 19);
		panelSale.add(txtKratsala);
		txtKratsala.setColumns(10);
		
		JLabel lblKapacitetSale = new JLabel("Kapacitet sale:");
		lblKapacitetSale.setBounds(12, 155, 200, 15);
		panelSale.add(lblKapacitetSale);
		
		txtBrmjesta = new JTextField();
		txtBrmjesta.setText("brMjesta");
		txtBrmjesta.setBounds(12, 182, 200, 19);
		panelSale.add(txtBrmjesta);
		txtBrmjesta.setColumns(10);
		
		JLabel lblZgradiKojojPripada = new JLabel("Zgradi kojoj pripada sala:");
		lblZgradiKojojPripada.setBounds(12, 213, 200, 15);
		panelSale.add(lblZgradiKojojPripada);
		
		comboBox_2.setBounds(12, 240, 200, 24);
		panelSale.add(comboBox_2);
		
		fillComboBoxSala();	
		
		JButton btnPotvrdiUnos_2 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DBExecuteZgrada.getZgrade();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				Sala_ sala = new Sala_();
				
				sala.setNazivSala(txtNazsala.getText());
				sala.setKratSala(txtKratsala.getText());
				sala.setBrMjesta(Integer.parseInt(txtBrmjesta.getText()));
				
				
				if (comboBox_2.getSelectedIndex() == -1) {
					System.err.println("Pogresno unesen ili nije unesen tip nastavnika");
				} else {
					//povezivanje sale sa zgradom
					int pom = comboBox_2.getSelectedIndex(); //uzmi indeks na kojem se nalazi polje u combo box-u, i zapamti (prvi je na 0, drugi na 1).
										
					Zgrada_ zgrada = new Zgrada_();	
					ArrayList<Zgrada_> zgrade = new ArrayList<Zgrada_>();
					zgrade = Zgrada.zgradaLista;
					zgrada = zgrade.get(pom); //uzmi unos iz vektora sa tom pozicijom (jer prvi clan iz vektora se unosi na prvo mjesto u combo boxu, itd, zato ovo moze
					int pom2 = zgrada.getSifZgrada();
					sala.setSifZgrada(zgrada.getSifZgrada()); //uzmi sifru zgrade, i upisi u tabelu sala
					System.out.println("---------");
					System.out.println(pom2);
					System.out.println("---------");
				}
				
				try {
					DBExecuteSala.insertSala(sala);
					popuniTabeluSalama();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnPotvrdiUnos_2.setBounds(12, 276, 150, 25);
		panelSale.add(btnPotvrdiUnos_2);
		
		JScrollPane scrollPaneSale = new JScrollPane();
		scrollPaneSale.setBounds(235, 12, 524, 289);
		panelSale.add(scrollPaneSale);
		
		table_4 = new JTable();
		scrollPaneSale.setViewportView(table_4);
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Zgrada", "Sifra sale", "Naziv sale", "Skracenica", "Kapacitet"
			}
		));
		popuniTabeluSalama();
		
		table_4.getColumnModel().getColumn(0).setPreferredWidth(170);
		table_4.getColumnModel().getColumn(2).setPreferredWidth(174);
		table_4.getColumnModel().getColumn(3).setPreferredWidth(124);
		
		table_4.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
			}
		});
	}

	private void initPanelZgrade() throws SQLException {
		tabbedPane.addTab("Zgrade", null, panelZgrade, null);
		panelZgrade.setLayout(null);
		
		JLabel lblUnosNoveZgrade = new JLabel("Unos podataka o zgradama:");
		lblUnosNoveZgrade.setBounds(12, 12, 305, 15);
		panelZgrade.add(lblUnosNoveZgrade);
		
		JLabel lblNazZgrade = new JLabel("Naziv zgrade:");
		lblNazZgrade.setBounds(12, 39, 150, 15);
		panelZgrade.add(lblNazZgrade);
		
		txtNazivzgrade = new JTextField();
		txtNazivzgrade.setText("");
		txtNazivzgrade.setBounds(12, 66, 305, 19);
		panelZgrade.add(txtNazivzgrade);
		txtNazivzgrade.setColumns(10);
		
		JLabel lblSkraenicaZaNaziv = new JLabel("Skraćenica za naziv zgrade:");
		lblSkraenicaZaNaziv.setBounds(12, 97, 250, 15);
		panelZgrade.add(lblSkraenicaZaNaziv);
		
		txtKratzgrada = new JTextField();
		txtKratzgrada.setText("");
		txtKratzgrada.setBounds(12, 124, 305, 19);
		panelZgrade.add(txtKratzgrada);
		txtKratzgrada.setColumns(10);
		
		JButton btnPotvrdiUnos_1 = new JButton("Potvrdi unos");
		btnPotvrdiUnos_1.setBounds(12, 155, 150, 25);
		btnPotvrdiUnos_1.addActionListener(buttonListener);
		panelZgrade.add(btnPotvrdiUnos_1);
		
		JScrollPane scrollPaneZgrade = new JScrollPane();
		scrollPaneZgrade.setBounds(329, 12, 430, 289);
		panelZgrade.add(scrollPaneZgrade);
		
		table_2 = new JTable();
		scrollPaneZgrade.setViewportView(table_2);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sif", "Naziv zgrade", "Skracenica za naziv"
			}
		));
		
		popuniTabeluZgradama();
		
		table_2.getColumnModel().getColumn(0).setPreferredWidth(61);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(356);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(204);
		
		table_2.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
			}
		});
	}

	private void initPanelNastavnici() throws SQLException {
		tabbedPane.addTab("Nastavnici", null, panelNastavnici, null);
		panelNastavnici.setLayout(null);
		
		JLabel lblUnosNovogNastavnika = new JLabel("Unos podataka o nastavnicima:");
		lblUnosNovogNastavnika.setBounds(12, 12, 250, 15);
		panelNastavnici.add(lblUnosNovogNastavnika);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(12, 39, 70, 15);
		panelNastavnici.add(lblIme);
		
		txtIme = new JTextField();
		txtIme.setText("");
		txtIme.setBounds(12, 66, 305, 19);
		panelNastavnici.add(txtIme);
		txtIme.setColumns(10);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(12, 97, 70, 15);
		panelNastavnici.add(lblPrezime);
		
		txtPrezime = new JTextField();
		txtPrezime.setText(" ");
		txtPrezime.setBounds(12, 124, 305, 19);
		panelNastavnici.add(txtPrezime);
		txtPrezime.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(12, 155, 100, 15);
		panelNastavnici.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setText(" ");
		txtUsername.setBounds(12, 182, 305, 19);
		panelNastavnici.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(12, 213, 100, 15);
		panelNastavnici.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setText(" ");
		txtPassword.setBounds(12, 240, 305, 19);
		panelNastavnici.add(txtPassword);
		txtPassword.setColumns(10);
		
		
		comboBoxTipNastavnika.setBounds(12, 271, 143, 30);
		panelNastavnici.add(comboBoxTipNastavnika);
		
		comboBoxTipNastavnika.addItem("Prodekan");
		comboBoxTipNastavnika.addItem("Profesor");
		comboBoxTipNastavnika.addItem("Asistent");
		
		JButton btnPotvrdiUnosNastavnik = new JButton("Potvrdi unos");
		/*btnPotvrdiUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Nastavnik_ nastavnik = new Nastavnik_();
				
				nastavnik.setImeNastavnik(txtIme.getText());
				nastavnik.setPrezNastavnik(txtPrezime.getText());
				nastavnik.setUsername(txtUsername.getText());
				nastavnik.setPassword(txtPassword.getText());
				if (comboBox.getSelectedIndex() == -1) {
					System.err.println("Pogresno unesen ili nije unesen tip nastavnika");
				} else {
					nastavnik.setVrstaNastavnik(comboBox.getSelectedIndex());
				}
				
				try {
					DBExecuteNastavnik.insertNastavnik(nastavnik);
					popuniTabeluNastavnicima();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});*/
		btnPotvrdiUnosNastavnik.setBounds(167, 274, 150, 25);
		btnPotvrdiUnosNastavnik.addActionListener(buttonListenerNastavnik);
		panelNastavnici.add(btnPotvrdiUnosNastavnik);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 12, 430, 289);
		panelNastavnici.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sif", "Ime", "Prezime", "Vrsta", "Username", "Password"
			}
		));
	
		
		popuniTabeluNastavnicima();
	
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(65);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
			}
		});
	}

	
	/**
	 * @author dino
	 * Inizijalizacija JTabbetPane-a
	 */
	private void initJTPane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane.setBounds(12, 12, 776, 346);
		contentPane.add(tabbedPane);
		
		
		tabbedPane.add(panelNastavnici);
		tabbedPane.add(panelZgrade);
		tabbedPane.add(panelSale);
		tabbedPane.add(panelUsmjerenja);
		
		getContentPane().add(tabbedPane);
		
	}


	/**
	 * @throws SQLException 
	 * @dino 
	 * poziva getNastavnici cime se puni globalni vektor nastavnika, iz tog vektora popunjavamo tabelu
	 */
	private void popuniTabeluSemestrima() throws SQLException{
		DefaultTableModel model = (DefaultTableModel) tableSemestar.getModel();
		
		resetTable(model);
		
		DBExecuteSemestar.getSemestri();
		ArrayList<Semestar_> semestri = new ArrayList<Semestar_>();
		semestri = Semestar.semestarLista;
		for (int i = 0; i < semestri.size(); i++) {
			Semestar_ semestar = new Semestar_();	
			semestar = semestri.get(i);
			String sifSemtString = String.valueOf(semestar.getSifSemestar());
			
			model.addRow(new Object[]{sifSemtString, semestar.getNazSemestar()});
		}
	}
	
	private void popuniTabeluNastavnicima() throws SQLException {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		resetTable(model);
		
		DBExecuteNastavnik.getNastavnici();
		ArrayList<Nastavnik_> nastavnici = new ArrayList<Nastavnik_>();
		nastavnici = Nastavnik.nastavnikLista;
		for (int i = 0; i < nastavnici.size(); i++) {
			Nastavnik_ nastavnik = new Nastavnik_();	
			nastavnik = nastavnici.get(i);
			String sifNastString = String.valueOf(nastavnik.getSifNastavnik());
			String vrstaNastavnikString = String.valueOf(nastavnik.getVrstaNastavnik());
			
			model.addRow(new Object[]{sifNastString, nastavnik.getImeNastavnik(), nastavnik.getPrezNastavnik(), vrstaNastavnikString, nastavnik.getUsername(), nastavnik.getPassword()});
		}
	}
	
	private void popuniTabeluZgradama() throws SQLException{
		DefaultTableModel model = (DefaultTableModel) table_2.getModel();
		
		resetTable(model);
		
		DBExecuteZgrada.getZgrade();

		ArrayList<Zgrada_> zgrade = new ArrayList<Zgrada_>();
		zgrade = Zgrada.zgradaLista;
		for (int i = 0; i < zgrade.size(); i++) {
			Zgrada_ zgrada = new Zgrada_();	
			zgrada = zgrade.get(i);
			String sifZgradaString = String.valueOf(zgrada.getSifZgrada());
			
			model.addRow(new Object[]{sifZgradaString, zgrada.getNazZgrada(), zgrada.getKratZgrada()});
		}
	}
	
	private void popuniTabeluSalama() throws SQLException{
		DefaultTableModel model = (DefaultTableModel) table_4.getModel();
		
		resetTable(model);
		
		DBExecuteSala.getSala();
		DBExecuteZgrada.getZgrade();
				
		ArrayList<Sala_> sale = new ArrayList<Sala_>();
		ArrayList<Zgrada_> zgrade = new ArrayList<Zgrada_>();
		
		sale = Sala.salaLista;
		zgrade = Zgrada.zgradaLista;
		
		
		for (int i = 0; i < sale.size(); i++) {
			Sala_ sala= new Sala_();	
			Zgrada_ zgrada = new Zgrada_();

			sala = sale.get(i);
			String sifSalaString = String.valueOf(sala.getSifSala());
			
			/**
			 * pretrazujemo vektor zgrada da nadjemo sa odgovarajucim indeksom. Kada ga pronadjemo, tu zgradu snimimo u varijablu zgrada i izadjemo.
			 */
			for (int j = 0; j < zgrade.size(); j++) {
				Zgrada_ zgrada1 = new Zgrada_();
				zgrada1 = zgrade.get(j);
				if(zgrada1.getSifZgrada() == sala.getSifZgrada()){
					zgrada = zgrada1;
					break;
				}
			}
			
			
			model.addRow(new Object[]{zgrada.getNazZgrada(), sifSalaString, sala.getNazivSala(), sala.getKratSala(), sala.getBrMjesta()});
		}
	}

	private void fillComboBoxSala() throws SQLException {
		Zgrada.zgradaLista.clear();
		comboBox_2.removeAllItems();
		
		DBExecuteZgrada.getZgrade();
		ArrayList<Zgrada_> zgrade = new ArrayList<Zgrada_>();
		zgrade = Zgrada.zgradaLista;
		for (int i = 0; i < zgrade.size(); i++) {
			Zgrada_ zgradaPom = new Zgrada_();	
			zgradaPom = zgrade.get(i);
			comboBox_2.addItem(zgradaPom.getNazZgrada());
		}
	}
	
	private void popuniTabeluUsmjerenjima() throws SQLException {
		
		DefaultTableModel model = (DefaultTableModel) table_5.getModel();
		
		resetTable(model);
		
		DBExecuteUsmjerenje.getUsmjerenja();

		ArrayList<Usmjerenje_> usmjerenja = new ArrayList<Usmjerenje_>();
		usmjerenja = Usmjerenje.usmjerenjeLista;
		for (int i = 0; i < usmjerenja.size(); i++) {
			Usmjerenje_ usm = new Usmjerenje_();	
			usm = usmjerenja.get(i);
			String sifUsmjerenjeString = String.valueOf(usm.getSifUsmjerenje());
			
			model.addRow(new Object[]{sifUsmjerenjeString, usm.getNazUsmjerenje(), usm.getKratUsmjerenje()});
		}
		
	}
	
	private void popuniTabeluPredmetima() throws SQLException{
		
		
		DefaultTableModel model = (DefaultTableModel) tablePredmet.getModel();
		
		resetTable(model);
		
		DBExecutePredmet.getPredmeti();
		DBExecuteSemestar.getSemestri();
		DBExecuteNastavnik.getNastavnici();
				
		ArrayList<Predmet_> predmeti = new ArrayList<Predmet_>();
		ArrayList<Semestar_> semestri = new ArrayList<Semestar_>();
		ArrayList<Nastavnik_> nastavnici = new ArrayList<Nastavnik_>();

		predmeti = Predmet.predmetLista;
		semestri = Semestar.semestarLista;
		nastavnici = Nastavnik.nastavnikLista;		
		
		for (int i = 0; i < predmeti.size(); i++) {
			Predmet_ predmet = new Predmet_();	
			Semestar_ semestar = new Semestar_();
			Nastavnik_ nastavnik = new Nastavnik_();

			predmet = predmeti.get(i);
			String sifPredString = String.valueOf(predmet.getSifPredmet()); //treba za ispis u tabelu da ga u string pretvorimo, zato ovaj korak
			
			String izborniString = "";
			if(predmet.getIzborni() == 0)
				izborniString = "Ne";
			else if(predmet.getIzborni() == 1)
				izborniString = "Da";

			/**
			 * pretrazujemo vektor semestara da nadjemo sa odgovarajucim indeksom. Kada ga pronadjemo, taj semestar snimimo u varijablu semestar i izadjemo.
			 */
			for (int j = 0; j < semestri.size(); j++) {
				Semestar_ semestar1 = new Semestar_();
				semestar1 = semestri.get(j);
				
				if(semestar1.getSifSemestar() == predmet.getSifSemestar()){
					semestar = semestar1;
					break;
				}
			}
			/**
			 * pretrazujemo vektor profesora da nadjemo sa odgovarajucim indeksom. Kada ga pronadjemo, tog nastavnika snimimo u varijablu nastavnika i izadjemo.
			 */
			for (int k = 0; k < nastavnici.size(); k++) {
				Nastavnik_ nastavnik1 = new Nastavnik_();
				nastavnik1 = nastavnici.get(k);
				
				if(nastavnik1.getSifNastavnik() == predmet.getSifNastavnik()){
					nastavnik = nastavnik1;
					break;
				}
			}
			// sifra naziv skracenica
			//"Sifra", "Predmet", "Skracenica", "Vrsta Nastave", "Predaje", "Izborni", "Semestar"
			model.addRow(new Object[]{
					sifPredString, predmet.getNazPredmet(), predmet.getKratPredmet(), predmet.getTipNastave(), nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik()
					,izborniString, semestar.getNazSemestar()
					});
		}			
			
	}
		
		
		
	
	
	private void fillComboBoxPredmet() throws SQLException {
		clearListe();
		comboBoxPredajeNast.removeAllItems();
		DBExecuteNastavnik.getNastavnici();
		ArrayList<Nastavnik_> nastavnici = new ArrayList<Nastavnik_>();
		nastavnici = Nastavnik.nastavnikLista;
		for (int i = 0; i < nastavnici.size(); i++) {
			Nastavnik_ nastavnikPom = new Nastavnik_();
			nastavnikPom = nastavnici.get(i);
			comboBoxPredajeNast.addItem(nastavnikPom.getImeNastavnik() + " " + nastavnikPom.getPrezNastavnik());
		}
	}
	
	
	
	private void resetTable(DefaultTableModel model) {
		
		model.setRowCount(0);
		clearListe();

	}

	private void clearListe() {
		Nastavnik.nastavnikLista.clear();
		Zgrada.zgradaLista.clear();
		Sala.salaLista.clear();
		Usmjerenje.usmjerenjeLista.clear();
		Semestar.semestarLista.clear();
		Predmet.predmetLista.clear();
	}
}