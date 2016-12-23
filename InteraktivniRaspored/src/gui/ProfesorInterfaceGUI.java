package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class ProfesorInterfaceGUI {
	JFrame frameProfesorGUI;
	
	int active1 = 0;
	int active2 = 0;
	
	public static void startProfesorInterfaceGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfesorInterfaceGUI window = new ProfesorInterfaceGUI();
					window.frameProfesorGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ProfesorInterfaceGUI() {
		initialize();
	}
	
	private void initialize() {
		frameProfesorGUI = new JFrame();
		frameProfesorGUI.setBounds(100, 100, 430, 300);
	
		frameProfesorGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameProfesorGUI.getContentPane().setLayout(null);
		
		JButton btnPrikaziRezervacije = new JButton("Rezervacije");
		btnPrikaziRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				TabelaRezervacijaGUI.startTabelaRezervacijaGUI();
			}
		});
		
		btnPrikaziRezervacije.setBounds(141, 197, 175, 25);
		frameProfesorGUI.getContentPane().add(btnPrikaziRezervacije);
		
		JButton btnGenerisanjeIzvjestaja = new JButton("Isprintaj Izvjestaj");
		btnGenerisanjeIzvjestaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				IzvjestajGUI.startIzvjestajGUI();
			}
		});
		
		btnGenerisanjeIzvjestaja.setBounds(141, 97, 175, 25);
		frameProfesorGUI.getContentPane().add(btnGenerisanjeIzvjestaja);
		
		JButton btnRezervacija = new JButton("Rezervacija");
		btnRezervacija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				RasporedGUI.startRasporedGUI();
			}
		});
		btnRezervacija.setBounds(141, 11, 175, 23);
		frameProfesorGUI.getContentPane().add(btnRezervacija);
	}
}


