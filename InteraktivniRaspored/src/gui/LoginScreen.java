package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modeli.Nastavnik_;
import tables.Nastavnik;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LoginScreen {

	private JFrame frame;
	private JTextField txtUsername;
	private JTextField txtPassword;
	
	public ArrayList<Nastavnik_> listaNastavnika1;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setBounds(186, 73, 114, 15);
		frame.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(186, 100, 114, 19);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setBounds(186, 131, 114, 15);
		frame.getContentPane().add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(186, 158, 114, 19);
		frame.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				String username = txtUsername.getText();
				String password = txtPassword.getText();

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
							System.out.println("Student: Ispravan username i password");
						}
						
						break;
					}
					
				}				
			}
		});
		btnLogin.setBounds(183, 189, 117, 25);
		frame.getContentPane().add(btnLogin);
	}
}
