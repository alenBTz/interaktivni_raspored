package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dataBase.DBExecuteZgrada;
import modeli.Zgrada_;
import pomocneF.IzbrisiRed;
import pomocneF.PomocneF;
import tables.Zgrada;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabelaZgradaGUI {

	public static JFrame frameTabelaZgrade;
	private JTable tableZgrade;
	private JButton btnIzbrisiZgrade;
	private JScrollPane scrollPane;
	private DefaultTableModel modelZgrade;
	/**
	 * Launch the application.
	 */
	public static void startTabelaZgradaGUI(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaZgradaGUI window = new TabelaZgradaGUI();
					window.frameTabelaZgrade.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabelaZgradaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTabelaZgrade = new JFrame();
		frameTabelaZgrade.setBounds(100, 100, 450, 350);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaZgrade.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaZgrade.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 424, 262);
		frameTabelaZgrade.getContentPane().add(scrollPane);
		
		tableZgrade = new JTable();
		scrollPane.setViewportView(tableZgrade);
		tableZgrade.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Naziv zgrade", "Skra\u0107enica"
			}
		));
		tableZgrade.getColumnModel().getColumn(1).setPreferredWidth(274);
		tableZgrade.getColumnModel().getColumn(2).setPreferredWidth(123);
		
		/**
		 * Funkcija implementira popunjavanje date tabele zgradama
		 */
		try {
			popuniTabeluZgradama();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnIzbrisiZgrade = new JButton("Izbriši zgrade");
		btnIzbrisiZgrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int red = tableZgrade.getSelectedRow();
				if(red != -1)
				{
					try {
						Object id = tableZgrade.getValueAt(red, 0);
						IzbrisiRed.izbrisiRed(id,"sifZgrada","zgrada");
						modelZgrade.removeRow(red);
					} catch (SQLException e) {
						System.out.println("Operacija brisanja nije uspjela ");
						e.printStackTrace();
					}
				}
				else{
					System.out.println("Niti jedan red nije selektovan");
				}
			}
		});
		btnIzbrisiZgrade.setBounds(12, 286, 150, 25);
		frameTabelaZgrade.getContentPane().add(btnIzbrisiZgrade);
	}
	
	private void popuniTabeluZgradama() throws SQLException{
		modelZgrade = (DefaultTableModel) tableZgrade.getModel();

		PomocneF.resetTable(modelZgrade);

		DBExecuteZgrada.getZgrade();

		ArrayList<Zgrada_> zgrade = new ArrayList<Zgrada_>();
		zgrade = Zgrada.zgradaLista;
		for (int i = 0; i < zgrade.size(); i++) {
			Zgrada_ zgrada = new Zgrada_();	
			zgrada = zgrade.get(i);
			String sifZgradaString = String.valueOf(zgrada.getSifZgrada());

			modelZgrade.addRow(new Object[]{sifZgradaString, zgrada.getNazZgrada(), zgrada.getKratZgrada()});
		}
	}
		
}


