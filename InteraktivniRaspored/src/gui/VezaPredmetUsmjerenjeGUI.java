package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import dataBase.DBExecuteIzborni;
import dataBase.DBExecutePredmet;
import dataBase.DBExecutePredmetUsmjerenjeIzborni;
import dataBase.DBExecuteUsmjerenje;
import modeli.Izborni_;
import modeli.PredmetUsmjerenjeIzborni_;
import modeli.Predmet_;
import modeli.Usmjerenje_;
import pomocneF.PomocneF;
import tables.Izborni;
import tables.Predmet;
import tables.PredmetUsmjerenjeIzborni;
import tables.Usmjerenje;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class VezaPredmetUsmjerenjeGUI {

	public static JFrame frameVezaPredmUsmj;
	JComboBox<String> comboBoxUsmjerenje = new JComboBox<String>();

	int active1 = 0;
	int active2 = 0;

	/**
	 * Launch the application.
	 */
	public static void startVezaPredmUsmjGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VezaPredmetUsmjerenjeGUI window = new VezaPredmetUsmjerenjeGUI();
					window.frameVezaPredmUsmj.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VezaPredmetUsmjerenjeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//komentar
		
		frameVezaPredmUsmj = new JFrame();
		/**
		 * Frame cemo postaviti na poziciju da se nasloni na glavni prozor odma sa desne strane.
		 */
		frameVezaPredmUsmj.setBounds(ProdekanGUI.frame.getX() + ProdekanGUI.frame.getWidth(), ProdekanGUI.frame.getY(), 454, 290);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameVezaPredmUsmj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameVezaPredmUsmj.getContentPane().setLayout(null);

		JButton btnPregledObaveznihPredmeta = new JButton("Pregled obaveznih predmeta po usmjerenjima");
		btnPregledObaveznihPredmeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * pozivamo prikaz tabele.
				 */
				/**
				 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active1 == 1)
					TabelaVPredmetUsmjerenjeGUI.frameTabelaPredmUsmj.dispose();
				TabelaVPredmetUsmjerenjeGUI.startTabelaVPredmUsmj();
				active2 = 1;

			}
		});

		btnPregledObaveznihPredmeta.setBounds(12, 12, 428, 25);
		frameVezaPredmUsmj.getContentPane().add(btnPregledObaveznihPredmeta);
		

		JLabel lblUsmjerenje = new JLabel("Usmjerenje:");
		lblUsmjerenje.setBounds(36, 90, 85, 15);
		frameVezaPredmUsmj.getContentPane().add(lblUsmjerenje);

		comboBoxUsmjerenje.setBounds(139, 85, 250, 24);
		frameVezaPredmUsmj.getContentPane().add(comboBoxUsmjerenje);
		try {
			fillComboBoxUsmjerenja();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnRedovni = new JButton("Redovni");
		btnRedovni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DBExecutePredmet.getPredmeti();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ArrayList<Predmet_> predmeti = new ArrayList<Predmet_>();
				predmeti = Predmet.predmetLista;
				if (comboBoxUsmjerenje.getSelectedIndex() == -1) {
					System.err.println("Nije odabrana nijedna stavkka");

				} else {
					String imeUsmjerenja = comboBoxUsmjerenje.getSelectedItem().toString();
					
					try {
						int sifraUsmjerenja = DBExecuteUsmjerenje.getSifUsmjerenja(imeUsmjerenja);
						System.out.println(sifraUsmjerenja+"///////");
						TabelaPredmetGUI.startTabelaPredmetGUI(2,sifraUsmjerenja);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				

			}
		});
		btnRedovni.setBounds(139, 179, 89, 23);
		frameVezaPredmUsmj.getContentPane().add(btnRedovni);
	}

	

	private void fillComboBoxUsmjerenja() throws SQLException{
		PomocneF.clearListe();

		comboBoxUsmjerenje.removeAllItems();

		DBExecuteUsmjerenje.getUsmjerenja();
		ArrayList<Usmjerenje_> usmjerenja = new ArrayList<Usmjerenje_>();
		usmjerenja = Usmjerenje.usmjerenjeLista;
		for (int i = 0; i < usmjerenja.size(); i++) {
			Usmjerenje_ usmjerenje = new Usmjerenje_();	
			usmjerenje = usmjerenja.get(i);
			comboBoxUsmjerenje.addItem(usmjerenje.getNazUsmjerenje());
		}		

	}
}
