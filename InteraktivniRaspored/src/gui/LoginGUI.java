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
import modeli.Korisnik_;
import javax.swing.JPasswordField;



public class LoginGUI {
	public static int sessionSifNastavnik ;
	private JFrame frameLogin;
	private JTextField txtKorisnickoIme;
	private JButton btnPrijaviSe;
	private JPasswordField txtSifra;

	
	public static void startLoginGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.frameLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginGUI() {
		initialize();
	}
	
	
	private void initialize() {
		frameLogin = new JFrame();
		frameLogin.setBounds(100, 100, 430, 250);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameLogin.getContentPane().setLayout(null);
		
		JButton btnPregledNastavnika = new JButton("Pregled nastavnika");
		btnPregledNastavnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		btnPregledNastavnika.setBounds(141, 10, 175, 25);
		JLabel lblIme = new JLabel("Korisnicko Ime:");
		lblIme.setBounds(77, 49, 100, 15);
		frameLogin.getContentPane().add(lblIme);
		
		txtKorisnickoIme = new JTextField();
		txtKorisnickoIme.setText("");
		txtKorisnickoIme.setBounds(177, 47, 166, 19);
		frameLogin.getContentPane().add(txtKorisnickoIme);
		txtKorisnickoIme.setColumns(10);
		
		JLabel lblSifra = new JLabel("Lozinka:");
		lblSifra.setBounds(77, 74, 62, 15);
		frameLogin.getContentPane().add(lblSifra);
		
		btnPrijaviSe = new JButton("Prijavi se");
		btnPrijaviSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Korisnik_ session = DBExecuteKorisnik.getKorisnik(txtKorisnickoIme.getText(), txtSifra.getText());
					if(session.getUloga().equals("Prodekan"))
					{
						frameLogin.dispose();
						ProdekanGUI.startProdekanGUI();
						sessionSifNastavnik = -1;

					}
					else if(session.getUloga().equals("Profesor"))
					{
						String sessionUserName = session.getIme();
						String sessionUserSurname = session.getPrezime();
						sessionSifNastavnik = DBExecuteNastavnik.getSifNastavnikByNameAndSurname(sessionUserName, sessionUserSurname);
						frameLogin.dispose();
						ProfesorInterfaceGUI.startProfesorInterfaceGUI();
					}
					else 
					{
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.err.println("Pogresno uneseni podaci ,probajte ponovo!");
					e1.printStackTrace();
				}
			}
		});
		btnPrijaviSe.setBounds(177, 102, 89, 23);
		frameLogin.getContentPane().add(btnPrijaviSe);
		
		JButton btnNeregistrovaniKorisnik = new JButton("Neregistrovani korisnik");
		btnNeregistrovaniKorisnik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameLogin.dispose();
				PrikazRasporedFilteriGUI.startPrikazRasporedFilteriGUI();
			}
		});
		btnNeregistrovaniKorisnik.setBounds(238, 178, 166, 23);
		frameLogin.getContentPane().add(btnNeregistrovaniKorisnik);
		
		txtSifra = new JPasswordField();
		txtSifra.setBounds(177, 75, 166, 20);
		frameLogin.getContentPane().add(txtSifra);
	
		/*final JComboBox<String> comboBoxUlogaKorisnika = new JComboBox<String>();
		comboBoxUlogaKorisnika.setBounds(177, 150, 166, 24);
		frameLogin.getContentPane().add(comboBoxUlogaKorisnika);
		comboBoxUlogaKorisnika.addItem("Prodekan");
		comboBoxUlogaKorisnika.addItem("Profesor");
		comboBoxUlogaKorisnika.addItem("Student");	*/
	}
}