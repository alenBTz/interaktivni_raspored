package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrikazRasporedFilteriGUI {

	private JFrame framePrikazRasporedFilteri;

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
		framePrikazRasporedFilteri.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrikazRasporedFilteri.getContentPane().setLayout(null);
		
		JLabel lblUsmjerenje = new JLabel("Usmjerenje:");
		lblUsmjerenje.setBounds(12, 39, 85, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblUsmjerenje);
		
		JComboBox comboBoxUsmjerenje = new JComboBox();
		comboBoxUsmjerenje.setBounds(115, 34, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxUsmjerenje);
		
		JLabel lblSemestar = new JLabel("Semestar:");
		lblSemestar.setBounds(24, 75, 73, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblSemestar);
		
		JComboBox comboBoxSemestar = new JComboBox();
		comboBoxSemestar.setBounds(115, 70, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxSemestar);
		
		JLabel lblPrikaiRasporedZa = new JLabel("Prikaži raspored za:");
		lblPrikaiRasporedZa.setBounds(12, 12, 143, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblPrikaiRasporedZa);
		
		JButton btnPrikaziRaspored = new JButton("Prikaži raspored");
		btnPrikaziRaspored.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
}
