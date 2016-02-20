package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataBase.DBExecuteUsmjerenje;
import modeli.Usmjerenje_;
import tables.Usmjerenje;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UsmjerenjeGUI {

	private JFrame frameUsmjerenje;
	private JTextField txtNazUsmjerenja;
	private JTextField txtKratusmjerenja;
	
	int active1 = 0;
	int active2 = 0;
	
	
	/**
	 * Launch the application.
	 */
	public static void startUsmjerenjaGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsmjerenjeGUI window = new UsmjerenjeGUI();
					window.frameUsmjerenje.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UsmjerenjeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameUsmjerenje = new JFrame();
		frameUsmjerenje.setBounds(100, 100, 390, 170);
		
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameUsmjerenje.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		frameUsmjerenje.getContentPane().setLayout(null);
		
		JButton btnPregledUsmjerenja = new JButton("Pregled usmjerenja");
		btnPregledUsmjerenja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active1 == 1)
					TabelaUsmjerenjeGUI.frameTabelaUsmjerenja.dispose();
				TabelaUsmjerenjeGUI.startTabelaUsmjerenja();
				active2 = 1;
			}
		});
		btnPregledUsmjerenja.setBounds(115, 12, 250, 25);
		frameUsmjerenje.getContentPane().add(btnPregledUsmjerenja);
		
		JLabel lblUsmjerenje = new JLabel("Usmjerenje:");
		lblUsmjerenje.setBounds(12, 51, 85, 15);
		frameUsmjerenje.getContentPane().add(lblUsmjerenje);
		
		txtNazUsmjerenja = new JTextField();
		txtNazUsmjerenja.setBounds(115, 49, 250, 19);
		frameUsmjerenje.getContentPane().add(txtNazUsmjerenja);
		txtNazUsmjerenja.setColumns(10);
		
		JLabel lblSkraenica = new JLabel("Skraćenica:");
		lblSkraenica.setBounds(15, 78, 82, 15);
		frameUsmjerenje.getContentPane().add(lblSkraenica);
		
		txtKratusmjerenja = new JTextField();
		txtKratusmjerenja.setText("");
		txtKratusmjerenja.setBounds(115, 76, 250, 19);
		frameUsmjerenje.getContentPane().add(txtKratusmjerenja);
		txtKratusmjerenja.setColumns(10);
		
		JButton btnPotvrdiUnos = new JButton("Potvrdi unos");
		btnPotvrdiUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Treba unjeti novo usmjerenje.
				 */
				/**
				 * Ako vec postoji lista usmjerenja procitana iz BP, brisemo je, kako se ne bi postojali dupli podaci
				 */
				Usmjerenje.usmjerenjeLista.clear();
				
				/**
				 * Kreiramo varijablu usmjerenje, u koju cemo upisati podatke koje je unio korisnik
				 */
				Usmjerenje_ usmjerenje = new Usmjerenje_();
				
				/**
				 * Unosimo u 'usmjerenje' podatke koje je unio korisnik
				 */
				usmjerenje.setNazUsmjerenje(txtNazUsmjerenja.getText());
				usmjerenje.setKratUsmjerenje(txtKratusmjerenja.getText());
				
				/**
				 * Kako imamo sve podatke koje su unesene, mozemo varijablu 'usmjerenje' upisati u BP
				 */
				try {
					/**
					 * Upisujemo 'nastavnik' u BP pozivanjem metoda:
					 */
					DBExecuteUsmjerenje.insertUsmjerenje(usmjerenje);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			
				/**
				 * Posto smo unjeli novog nastavnika, zelimo da osvjezimo tabelu.
				 */
				/**
				 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom unosa nastavnika otvori, i ako hocemo opet da provjerimo nastavnika, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active2 == 1)
					TabelaUsmjerenjeGUI.frameTabelaUsmjerenja.dispose();

				TabelaUsmjerenjeGUI.startTabelaUsmjerenja();
				active1 = 1;
				
			}
		});
		btnPotvrdiUnos.setBounds(115, 107, 250, 25);
		frameUsmjerenje.getContentPane().add(btnPotvrdiUnos);
		
	}

}
