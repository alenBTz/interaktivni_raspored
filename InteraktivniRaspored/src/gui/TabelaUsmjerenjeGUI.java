package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import dataBase.DBExecuteUsmjerenje;
import modeli.Usmjerenje_;
import pomocneF.PomocneF;
import tables.Usmjerenje;

import javax.swing.JScrollPane;

public class TabelaUsmjerenjeGUI {

	public static JFrame frameTabelaUsmjerenja;
	private JTable tableUsmjerenja;
	private DefaultTableModel modelUsmjerenja;
	
	/**
	 * Launch the application.
	 */
	public static void startTabelaUsmjerenja() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaUsmjerenjeGUI window = new TabelaUsmjerenjeGUI();
					window.frameTabelaUsmjerenja.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public TabelaUsmjerenjeGUI() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frameTabelaUsmjerenja = new JFrame();
		frameTabelaUsmjerenja.setBounds(100, 100, 450, 300);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaUsmjerenja.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaUsmjerenja.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 424, 207);
		frameTabelaUsmjerenja.getContentPane().add(scrollPane);
		
		tableUsmjerenja = new JTable();
		scrollPane.setViewportView(tableUsmjerenja);
		tableUsmjerenja.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Usmjerenje", "Skra\u0107enica"
			}
		));
		tableUsmjerenja.getColumnModel().getColumn(1).setPreferredWidth(204);
		tableUsmjerenja.getColumnModel().getColumn(2).setPreferredWidth(82);
		
		popuniTabeluUsmjerenjima();

		JButton btnIzbriiUsmjerenja = new JButton("Izbriši usmjerenja");
		btnIzbriiUsmjerenja.setBounds(12, 231, 180, 25);
		frameTabelaUsmjerenja.getContentPane().add(btnIzbriiUsmjerenja);
	}
	
	private void popuniTabeluUsmjerenjima() throws SQLException {

		modelUsmjerenja = (DefaultTableModel) tableUsmjerenja.getModel();

		PomocneF.resetTable(modelUsmjerenja);

		DBExecuteUsmjerenje.getUsmjerenja();

		ArrayList<Usmjerenje_> usmjerenja = new ArrayList<Usmjerenje_>();
		usmjerenja = Usmjerenje.usmjerenjeLista;
		for (int i = 0; i < usmjerenja.size(); i++) {
			Usmjerenje_ usm = new Usmjerenje_();	
			usm = usmjerenja.get(i);
			String sifUsmjerenjeString = String.valueOf(usm.getSifUsmjerenje());

			modelUsmjerenja.addRow(new Object[]{sifUsmjerenjeString, usm.getNazUsmjerenje(), usm.getKratUsmjerenje()});
		}

	}

}
