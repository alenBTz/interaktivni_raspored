package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataBase.DBExecuteKorisnik;
import dataBase.DBExecuteNastavnik;
import modeli.Nastavnik_;
import tables.Nastavnik;

public class NastavnikGUI {

	private JFrame frameNastavnik;
	private JTextField txtImenastavnik;
	private JTextField txtPreznastavnik;
	
	int active1 = 0;
	int active2 = 0;
	
	/**
	 * Launch the application.
	 */
	public static void startNastavnikGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NastavnikGUI window = new NastavnikGUI();
					window.frameNastavnik.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NastavnikGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameNastavnik = new JFrame();
		frameNastavnik.setBounds(100, 100, 330, 250);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameNastavnik.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameNastavnik.getContentPane().setLayout(null);
		
		/**
		 * Kada se pritisne dugme Pregled nastavnika, treba samo da izbaci tabelu sa nastavnicima.
		 */
		JButton btnPregledNastavnika = new JButton("Pregled nastavnika");
		btnPregledNastavnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * pozivamo prikaz tabele nastavnika.
				 */
				/**
				 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active1 == 1)
					TabelaNastavnikGUI.frameTabelaNastavnik.dispose();
				TabelaNastavnikGUI.startTabelaNastavnikGUI();
				active2 = 1;
				
				
			}
		});
		btnPregledNastavnika.setBounds(141, 10, 175, 25);
		frameNastavnik.getContentPane().add(btnPregledNastavnika);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(109, 49, 30, 15);
		frameNastavnik.getContentPane().add(lblIme);
		
		txtImenastavnik = new JTextField();
		txtImenastavnik.setText("");
		txtImenastavnik.setBounds(150, 47, 166, 19);
		frameNastavnik.getContentPane().add(txtImenastavnik);
		txtImenastavnik.setColumns(10);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(77, 74, 62, 15);
		frameNastavnik.getContentPane().add(lblPrezime);
		
		txtPreznastavnik = new JTextField();
		txtPreznastavnik.setText("");
		txtPreznastavnik.setBounds(150, 72, 166, 19);
		frameNastavnik.getContentPane().add(txtPreznastavnik);
		txtPreznastavnik.setColumns(10);
		
		JLabel lblVrstaNastavnika = new JLabel("Vrsta nastavnika:");
		lblVrstaNastavnika.setBounds(14, 155, 125, 15);
		frameNastavnik.getContentPane().add(lblVrstaNastavnika);
		
		final JComboBox<String> comboBoxVrstaNast = new JComboBox<String>();
		comboBoxVrstaNast.setBounds(150, 150, 166, 24);
		frameNastavnik.getContentPane().add(comboBoxVrstaNast);
		
		/**
		 * Unosimo u ComboBox izbore. Prvi uneseni je sa indexom 0, drugi sa 1, itd
		 */
		comboBoxVrstaNast.addItem("Prodekan");
		comboBoxVrstaNast.addItem("Profesor");
		comboBoxVrstaNast.addItem("Asistent");		
		
		
		JButton btnPotvrdiUnos = new JButton("Potvrdi unos");
		btnPotvrdiUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean postojiProdekan = false;

				/**
				 * Treba unjeti novog nastavnika.
				 */
				/**
				 * Ako vec postoji lista nastavnika procitana iz BP, brisemo je, kako se ne bi postojali dupli podaci
				 */
				Nastavnik.nastavnikLista.clear();
				
				/**
				 * Kreiramo varijablu nastavnik, u koju cemo upisati podatke koje je unio korisnik
				 */
				Nastavnik_ nastavnik = new Nastavnik_();
				
				/**
				 * Unosimo u nastavnika podatke koje je unio korisnik
				 */
				nastavnik.setImeNastavnik(txtImenastavnik.getText());
				nastavnik.setPrezNastavnik(txtPreznastavnik.getText());
				
				/**
				 * Provjeravamo sta je korisnik odabrao iz ComboBoxa, i odgovarajuci odabir unosimo u nastavnika
				 */
				if (comboBoxVrstaNast.getSelectedIndex() == -1) {
					System.err.println("Pogresno unesen ili nije unesen tip nastavnika");
				} else {
					nastavnik.setVrstaNastavnik(comboBoxVrstaNast.getSelectedIndex());
				}
				System.out.println("comboBoxVrstaNast.getSelectedItem(): " + comboBoxVrstaNast.getSelectedItem());
				System.out.println(comboBoxVrstaNast.getSelectedItem().equals("Prodekan"));
				if (comboBoxVrstaNast.getSelectedItem().equals("Prodekan"))
				{
					try
					{
						if (DBExecuteNastavnik.countProdekana() != 0)
						{
							postojiProdekan = true;
						}
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
					
					if(postojiProdekan)
					{
						System.err.println("U bazi moze postojati samo jedan prodekan,za unos novog izbrisite trenutnog!");
					}
					else
					{
						try 
						{
							DBExecuteNastavnik.insertNastavnik(nastavnik);
						}
						catch (SQLException e1)
						{
							e1.printStackTrace();
						}
					}
				}
				else{
					//Ne unosimo prodekana te nema potrebe za provjeru da li vec postoji prodekan
					/**
					 * Kako imamo sve podatke koje su unesene, mozemo varijablu 'korisnik' upisati u BP
					 */
					
					try {
						DBExecuteNastavnik.insertNastavnik(nastavnik);
						
						/**
						 * Posto smo unjeli novog nastavnika, zelimo da osvjezimo tabelu.
						 */
						if(active2 == 1)
							TabelaNastavnikGUI.frameTabelaNastavnik.dispose();
						TabelaNastavnikGUI.startTabelaNastavnikGUI();
						active1 = 1;

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnPotvrdiUnos.setBounds(141, 186, 175, 25);
		frameNastavnik.getContentPane().add(btnPotvrdiUnos);
	}

}
