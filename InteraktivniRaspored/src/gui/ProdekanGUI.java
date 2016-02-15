package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataBase.DBExecuteNastavnik;
import dataBase.DBExecuteSala;
import dataBase.DBExecuteUsmjerenje;
import dataBase.DBExecuteZgrada;
import modeli.Nastavnik_;
import modeli.Sala_;
import modeli.Usmjerenje_;
import modeli.Zgrada_;
import tables.Nastavnik;
import tables.Sala;
import tables.Usmjerenje;
import tables.Zgrada;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.table.DefaultTableModel;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 776, 346);
		contentPane.add(tabbedPane);
		
		/**
		 * Nastavnici
		 */
		guiNastavnici(tabbedPane);		
		
		/**
		 * Zgrade
		 */
		guiZgrade(tabbedPane);
		
		/**
		 * Sale
		 */
		guiSale(tabbedPane);
		
		/**
		 * Usmjerenja
		 */		
		guiUsmjerenja(tabbedPane);
		
		/**
		 * Predmeti
		 */
		JPanel panelPredmeti = new JPanel();
		tabbedPane.addTab("Predmeti", null, panelPredmeti, null);
		
		/**
		 * Semestar
		 */
		JPanel panelSemestar = new JPanel();
		tabbedPane.addTab("Semestar", null, panelSemestar, null);
		
		/**
		 * Grupe
		 */
		JPanel panelGrupe = new JPanel();
		tabbedPane.addTab("Grupe", null, panelGrupe, null);
		
		/**
		 * Raspored
		 */
		JPanel panelRaspored = new JPanel();
		tabbedPane.addTab("Raspored", null, panelRaspored, null);
	}

	private void guiUsmjerenja(JTabbedPane tabbedPane) throws SQLException {
		JPanel panelUsmjerenja = new JPanel();
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 12, 430, 289);
		panelUsmjerenja.add(scrollPane);
		
		table_5 = new JTable();
		scrollPane.setViewportView(table_5);
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

	private void guiSale(JTabbedPane tabbedPane) throws SQLException {
		JPanel panelSale = new JPanel();
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
		
		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(12, 240, 200, 24);
		panelSale.add(comboBox_2);
		
		//popuniti combobox:
		//combobox se puni redom.prvi element u bazi je prvi element u combo boxu. vazno za kasnije, gdje zelimo povezati salu sa zgradom
		Zgrada.zgradaLista.clear();
		DBExecuteZgrada.getZgrade();
		ArrayList<Zgrada_> zgrade = new ArrayList<Zgrada_>();
		zgrade = Zgrada.zgradaLista;
		for (int i = 0; i < zgrade.size(); i++) {
			Zgrada_ zgrada = new Zgrada_();	
			zgrada = zgrade.get(i);
			comboBox_2.addItem(zgrada.getNazZgrada());
		}
		
		
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(235, 12, 524, 289);
		panelSale.add(scrollPane);
		
		table_4 = new JTable();
		scrollPane.setViewportView(table_4);
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

	private void guiZgrade(JTabbedPane tabbedPane) throws SQLException {
		JPanel panelZgrade = new JPanel();
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
		btnPotvrdiUnos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				Zgrada_ zgrada = new Zgrada_();
				
				zgrada.setNazZgrada(txtNazivzgrade.getText());
				zgrada.setKratZgrada(txtKratzgrada.getText());
				
				try {
					DBExecuteZgrada.insertZgrada(zgrada);
					popuniTabeluZgradama();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnPotvrdiUnos_1.setBounds(12, 155, 150, 25);
		panelZgrade.add(btnPotvrdiUnos_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 12, 430, 289);
		panelZgrade.add(scrollPane);
		
		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
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

	private void guiNastavnici(JTabbedPane tabbedPane) throws SQLException {
		JPanel panelNastavnici = new JPanel();
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
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 271, 143, 30);
		panelNastavnici.add(comboBox);
		
		comboBox.addItem("Prodekan");
		comboBox.addItem("Profesor");
		comboBox.addItem("Asistent");
		
		JButton btnPotvrdiUnos = new JButton("Potvrdi unos");
		btnPotvrdiUnos.addActionListener(new ActionListener() {
			/**
			 * Kada se desi unos, treba da upise u bazu podataka odgovarajuceg nastavnika.
			 */
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

		});
		btnPotvrdiUnos.setBounds(167, 274, 150, 25);
		panelNastavnici.add(btnPotvrdiUnos);
		
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
	 * @dino 
	 * poziva getNastavnici cime se puni globalni vektor nastavnika, iz tog vektora popunjavamo tabelu
	 */
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
	
	private void resetTable(DefaultTableModel model) {
		
		model.setRowCount(0);
		clearListe();

	}

	private void clearListe() {
		Nastavnik.nastavnikLista.clear();
		Zgrada.zgradaLista.clear();
		Sala.salaLista.clear();
		Usmjerenje.usmjerenjeLista.clear();
	}
}