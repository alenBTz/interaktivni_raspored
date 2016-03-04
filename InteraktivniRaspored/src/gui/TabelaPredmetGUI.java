package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dataBase.DBExecutePredmet;
import dataBase.DBExecuteSemestar;
import modeli.Predmet_;
import modeli.Semestar_;
import pomocneF.PomocneF;
import tables.Predmet;
import tables.Semestar;

import javax.swing.JButton;
import javax.swing.JScrollPane;

public class TabelaPredmetGUI {

	public static JFrame frameTabelaPredmet;
	private JTable tablePredmet;
	public static DefaultTableModel modelPredmet;
	
	/**
	 * Launch the application.
	 */
	public static void startTabelaPredmetGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaPredmetGUI window = new TabelaPredmetGUI();
					window.frameTabelaPredmet.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabelaPredmetGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTabelaPredmet = new JFrame();
		frameTabelaPredmet.setBounds(100, 100, 600, 420);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaPredmet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaPredmet.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 574, 332);
		frameTabelaPredmet.getContentPane().add(scrollPane);
		
		tablePredmet = new JTable();
		scrollPane.setViewportView(tablePredmet);
		tablePredmet.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Predmet", "Skra\u0107enica", "Semestar"
			}
		));
		tablePredmet.getColumnModel().getColumn(1).setPreferredWidth(280);
		tablePredmet.getColumnModel().getColumn(2).setPreferredWidth(136);
		
		try {
			popuniTabeluPredmetima();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		JButton btnIzbriiRed = new JButton("Izbriši red");
		btnIzbriiRed.setBounds(12, 356, 117, 25);
		frameTabelaPredmet.getContentPane().add(btnIzbriiRed);
		
	}
	
	/**
	 * metod koji vrsi popunjavanje tabele
	 */
	private void popuniTabeluPredmetima() throws SQLException {
		modelPredmet = (DefaultTableModel) tablePredmet.getModel();

		PomocneF.resetTable(modelPredmet);

		DBExecutePredmet.getPredmeti();
		ArrayList<Predmet_> predmeti = new ArrayList<Predmet_>();
		predmeti = Predmet.predmetLista;
		for (int i = 0; i < predmeti.size(); i++) {
			Predmet_ predmet = new Predmet_();	
			predmet = predmeti.get(i);
			String sifPredString = String.valueOf(predmet.getSifPredmet());
			/**
			 * Trebamo se povezati sa tabelom 'semestri' da pokupimo string semestra na osnovu sifre.
			 */
			DBExecuteSemestar.getSemestri();
			ArrayList<Semestar_> semestri = new ArrayList<Semestar_>();
			semestri = Semestar.semestarLista;
			Semestar_ semestar = new Semestar_();
			for (int j = 0; j < semestri.size(); j++) {
				Semestar_ semestarPom = new Semestar_();
				semestarPom = semestri.get(j);
				if(semestarPom.getSifSemestar() == predmet.getSifSemestar()){
					semestar = semestarPom;
					break;
				}
			}
			
			modelPredmet.addRow(new Object[]{sifPredString, predmet.getNazPredmet(), predmet.getKratPredmet(), semestar.getNazSemestar()});
		}
	}
}
