package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modeli.Nastavnik_;
import tables.Nastavnik;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginScreen {

	private JFrame frame;
	private JTextField txtUsername;
	
	public ArrayList<Nastavnik_> listaNastavnika1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void startLoginScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setBounds(200, 170, 100, 15);
		frame.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(200, 190, 100, 19);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setBounds(200, 220, 100, 15);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 240, 100, 19);
		frame.getContentPane().add(passwordField);
		
		
		JButton btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String username = txtUsername.getText();
				char[] pom = passwordField.getPassword();
				String password = new String(pom);
				
				System.out.println(username);
				System.out.println(password);
				listaNastavnika1 = Nastavnik.nastavnikLista;
				
				for (int i = 0; i < listaNastavnika1.size(); i++) {
					Nastavnik_ nastavnik = new Nastavnik_();
					nastavnik = listaNastavnika1.get(i);
					if(username.equals(nastavnik.getUsername()) && password.equals(nastavnik.getPassword())){
						
						/**
						 * Ovdje umjesto ovog outputa treba da ide kod za ulazak na stranicu, u zavisnosti da li je korisnik 
						 * prodekan, profesor ili asistent.
						 */
						if (nastavnik.getVrstaNastavnik() == 0) {
							/**
							 * Za prodekana
							 */
							System.out.println("Prodekan: Ispravan username i password za prodekana");
						}
						else if (nastavnik.getVrstaNastavnik() == 1) {
							/**
							 * Za profesora 
							 */
							System.out.println("Profesor:Ispravan username i password za prodekana");
						}
						else if (nastavnik.getVrstaNastavnik() == 2) {
							/**
							 * Za asistenta
							 */
							System.out.println("Asistent: Ispravan username i password za prodekana");
						}
						else {			
							/**
							 * Za studenta
							 */
							System.out.println("Student: Ispravan username i password.");
						}
						
						break;
					}
					
				}				
			}
		});
		btnLogin.setBounds(200, 270, 100, 25);
		frame.getContentPane().add(btnLogin);
		
		/**
		 * @dino
		 * za ucitavanje loga od UNTZ
		 */
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/untz-logo.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(175, 12, 150, 141);
		frame.getContentPane().add(label);
		
		
	}
}
