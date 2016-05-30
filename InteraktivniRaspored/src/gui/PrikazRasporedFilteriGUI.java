package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;

import dataBase.DBExecutePredavanjeUsmjerenje;
import dataBase.DBExecuteSemestar;
import dataBase.DBExecuteUsmjerenje;
import modeli.PredavanjeUsmjerenje_;
import modeli.Predmet_;
import modeli.Semestar_;
import modeli.Usmjerenje_;
import pomocneF.PomocneF;
import tables.Semestar;
import tables.Usmjerenje;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PrikazRasporedFilteriGUI {

	private JFrame framePrikazRasporedFilteri;
	
	JComboBox<String> comboBoxSemestar = new JComboBox<String>();
	JComboBox<String> comboBoxUsmjerenje = new JComboBox<String>();


	/**
	 * Launch the application.
	 */
	public static void startPrikazRasporedFilteriGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazRasporedFilteriGUI window = new PrikazRasporedFilteriGUI();
					window.framePrikazRasporedFilteri.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazRasporedFilteriGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		framePrikazRasporedFilteri = new JFrame();
		framePrikazRasporedFilteri.setBounds(100, 100, 386, 430);
		//framePrikazRasporedFilteri.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrikazRasporedFilteri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		framePrikazRasporedFilteri.getContentPane().setLayout(null);
		
		JLabel lblUsmjerenje = new JLabel("Usmjerenje:");
		lblUsmjerenje.setBounds(12, 39, 85, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblUsmjerenje);
		
		comboBoxUsmjerenje.setBounds(115, 34, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxUsmjerenje);
		try {
			fillcomboBoxUsmjerenje();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JLabel lblSemestar = new JLabel("Semestar:");
		lblSemestar.setBounds(24, 75, 73, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblSemestar);
		
		comboBoxSemestar.setBounds(115, 70, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxSemestar);
		try {
			fillcomboBoxSemestar();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblPrikaiRasporedZa = new JLabel("Prikaži raspored za:");
		lblPrikaiRasporedZa.setBounds(12, 12, 143, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblPrikaiRasporedZa);
		
		JButton btnPrikaziRaspored = new JButton("Prikaži raspored");
		btnPrikaziRaspored.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<Integer> lista = DBExecutePredavanjeUsmjerenje.getPredmetByUsmjerenje(14);
					System.out.println("broj vracenih predmeta je :"+ lista.get(1));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PrikazRasporedGUI.startPrikazRasporedGUI();
			}
		});
		btnPrikaziRaspored.setBounds(115, 106, 250, 25);
		framePrikazRasporedFilteri.getContentPane().add(btnPrikaziRaspored);
		
		JLabel lblProfesor = new JLabel("Profesor:");
		lblProfesor.setBounds(31, 186, 66, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblProfesor);
		
		JComboBox comboBoxProfesor = new JComboBox();
		comboBoxProfesor.setBounds(115, 181, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxProfesor);
		
		JLabel lblPredmet = new JLabel("Predmet:");
		lblPredmet.setBounds(32, 222, 65, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblPredmet);
		
		JComboBox comboBoxPredmet = new JComboBox();
		comboBoxPredmet.setBounds(115, 217, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxPredmet);
		
		JLabel lblZgrada = new JLabel("Zgrada:");
		lblZgrada.setBounds(40, 258, 57, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblZgrada);
		
		JComboBox comboBoxZgrada = new JComboBox();
		comboBoxZgrada.setBounds(115, 253, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxZgrada);
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setBounds(61, 294, 36, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblSala);
		
		JComboBox comboBoxSala = new JComboBox();
		comboBoxSala.setBounds(115, 289, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxSala);
		
		JLabel lblGrupa = new JLabel("Grupa:");
		lblGrupa.setBounds(49, 334, 48, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblGrupa);
		
		JComboBox comboBoxGrupa = new JComboBox();
		comboBoxGrupa.setBounds(115, 329, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxGrupa);
		
		JButton btnPrikaziRaspored2 = new JButton("Prikaži raspored");
		btnPrikaziRaspored2.setBounds(115, 365, 250, 25);
		framePrikazRasporedFilteri.getContentPane().add(btnPrikaziRaspored2);
		
		//komentar
	}
	private void fillcomboBoxSemestar() throws SQLException {
		PomocneF.clearListe();
		comboBoxSemestar.removeAllItems();

		DBExecuteSemestar.getSemestri();
		ArrayList<Semestar_> semestri = new ArrayList<Semestar_>();
		semestri = Semestar.semestarLista;
		System.out.println("Broj semestara" + semestri.size() );
		for (int i = 0; i < semestri.size(); i++) {
			System.out.println("usao sam");
			Semestar_ semestarPom = new Semestar_();
			semestarPom = semestri.get(i);
			System.out.println(semestarPom.getNazSemestar());
			comboBoxSemestar.addItem(semestarPom.getNazSemestar());
		}
	}
	private void fillcomboBoxUsmjerenje() throws SQLException {
		PomocneF.clearListe();
		comboBoxUsmjerenje.removeAllItems();

		DBExecuteUsmjerenje.getUsmjerenja();
		ArrayList<Usmjerenje_> usmjerenja = new ArrayList<Usmjerenje_>();
		usmjerenja = Usmjerenje.usmjerenjeLista;
		System.out.println("Broj Usmjerenja" + usmjerenja.size() );
		for (int i = 0; i < usmjerenja.size(); i++) {
			System.out.println("usao sam");
			Usmjerenje_ usmjerenjePom = new Usmjerenje_();
			usmjerenjePom = usmjerenja.get(i);
			System.out.println(usmjerenjePom.getNazUsmjerenje());
			comboBoxUsmjerenje.addItem(usmjerenjePom.getNazUsmjerenje());
		}
	}
}
