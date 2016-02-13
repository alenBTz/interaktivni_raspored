package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataBase.DBExecuteNastavnik;
import modeli.Nastavnik_;
import tables.Nastavnik;

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
		JPanel panelNastavnici = new JPanel();
		tabbedPane.addTab("Nastavnici", null, panelNastavnici, null);
		panelNastavnici.setLayout(null);
		
		JLabel lblUnosNovogNastavnika = new JLabel("Unos novog nastavnika:");
		lblUnosNovogNastavnika.setBounds(12, 12, 200, 15);
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
		
		comboBox.addItem("Asistent");
		comboBox.addItem("Profesor");
		comboBox.addItem("Prodekan");
		
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
		
		/**
		 * Zgrade
		 */
		JPanel panelZgrade = new JPanel();
		tabbedPane.addTab("Zgrade", null, panelZgrade, null);
		
		/**
		 * Sale
		 */
		JPanel panelSale = new JPanel();
		tabbedPane.addTab("Sale", null, panelSale, null);
		
		/**
		 * Usmjerenja
		 */
		JPanel panelUsmjerenja = new JPanel();
		tabbedPane.addTab("Usmjerenja", null, panelUsmjerenja, null);
		
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

	private void resetTable(DefaultTableModel model) {
		
		model.setRowCount(0);
		Nastavnik.nastavnikLista.clear();

	}
	
}
