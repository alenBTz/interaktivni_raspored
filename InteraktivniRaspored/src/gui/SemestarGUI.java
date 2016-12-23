package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataBase.DBExecuteSemestar;
import modeli.Semestar_;
import tables.Semestar;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SemestarGUI {

	private JFrame frameSemestar;
	private JTextField txtSemestar;

	int active1 = 0;
	int active2 = 0;
	
	/**
	 * Launch the application.
	 */
	public static void startSemestarGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SemestarGUI window = new SemestarGUI();
					window.frameSemestar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SemestarGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameSemestar = new JFrame();
		frameSemestar.setBounds(100, 100, 237, 142);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameSemestar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameSemestar.getContentPane().setLayout(null);
		
		JButton btnPregledSemestara = new JButton("Pregled semestara");
		btnPregledSemestara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * pozivamo prikaz tabele semestra.
				 */
				/**
				 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom provjere nastavnika otvori, i ako unesemo semestar, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active1 == 1)
					TabelaSemestarGUI.frameTabelaSemestar.dispose();
				TabelaSemestarGUI.startTabelaSemestarGUI();
				active2 = 1;
			
			}
		});
		btnPregledSemestara.setBounds(30, 12, 170, 25);
		frameSemestar.getContentPane().add(btnPregledSemestara);
		
		JLabel lblSemestar = new JLabel("Semestar:");
		lblSemestar.setBounds(12, 49, 73, 15);
		frameSemestar.getContentPane().add(lblSemestar);
		
		txtSemestar = new JTextField();
		txtSemestar.setText("");
		txtSemestar.setBounds(103, 47, 114, 19);
		frameSemestar.getContentPane().add(txtSemestar);
		txtSemestar.setColumns(10);
		
		JButton btnPotvrdiUnosSemestar = new JButton("Potvrdi unos");
		btnPotvrdiUnosSemestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//izmjene uveo Alen 
				if(vecPostojiSemstar())
				{
					System.out.println("Vec postoji kreiran semestar, izaberite drugi redni broj ili izbirsite postojeci!");
				}
				else
				{
					//kraj izmjena
					/**
					 * Treba unjeti novoi semestar.
					 */
					/**
					 * Ako vec postoji lista semestara procitana iz BP, brisemo je, kako se ne bi postojali dupli podaci
					 */
					Semestar.semestarLista.clear();

					/**
					 * Kreiramo varijablu 'semestar', u koju cemo upisati podatke koje je unio korisnik
					 */
					Semestar_ semestar= new Semestar_();
					/*
					 * Unosimo podatke u tu varijablu
					 */
					
					semestar.setNazSemestar(txtSemestar.getText());
					
					/*
					 * Konektujemo se na BP da unesemo semestar
					 */
					try {
						/*
						 * upisujemo semestar u BP
						 */
						DBExecuteSemestar.insertSemestar(semestar);
					
						/*
						 * osvjezavamo tabelu jer smo unjeli novi semestar
						 */
						/**
						 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
						 * prilikom provjere nastavnika otvori, i ako unesemo semestar, stara tabela da se zatvori a nova otvori
						 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
						 */
						if(active2 == 1)
							TabelaSemestarGUI.frameTabelaSemestar.dispose();
						TabelaSemestarGUI.startTabelaSemestarGUI();
						active1 = 1;

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			private boolean vecPostojiSemstar() {
				ArrayList<Semestar_> semestri = Semestar.semestarLista;

				for (int i = 0; i < semestri.size(); i++) 
				{
					Semestar_ semestarPom = new Semestar_();
					semestarPom = semestri.get(i);
					
					if(semestarPom.getNazSemestar().equals(txtSemestar.getText()))
					{
						return true;
					}
				}
				return false;
			}
		});
		btnPotvrdiUnosSemestar.setBounds(30, 76, 170, 25);
		frameSemestar.getContentPane().add(btnPotvrdiUnosSemestar);
	}
}
