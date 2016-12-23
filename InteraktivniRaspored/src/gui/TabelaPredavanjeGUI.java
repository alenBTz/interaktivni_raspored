package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dataBase.DBExecutePredavanje;
import dataBase.DBExecutePredmet;
import dataBase.DBExecutePredmetUsmjerenjeIzborni;
import dataBase.DBExecuteSala;
import dataBase.DBExecuteSemestar;
import modeli.CustomDefaultTableModel;
import modeli.Predavanje_;
import modeli.PredmetUsmjerenjeIzborni_;
import modeli.Predmet_;
import modeli.Sala_;
import modeli.Semestar_;
import pomocneF.IzbrisiRed;
import pomocneF.PomocneF;
import tables.Predavanje;
import tables.Predmet;
import tables.Sala;
import tables.Semestar;

public class TabelaPredavanjeGUI {
	public static JFrame frameTabelaPredavanje;
	private JTable tablePredavanje;
	public static CustomDefaultTableModel modelPredavanje;
	private JButton btnIzaberi;
	
	/**
	 * Launch the application.
	 */
	public static void startTabelaPredavanjeGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaPredavanjeGUI window = new TabelaPredavanjeGUI();
					window.frameTabelaPredavanje.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabelaPredavanjeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTabelaPredavanje = new JFrame();
		frameTabelaPredavanje.setBounds(100, 100, 600, 420);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaPredavanje.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaPredavanje.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 574, 332);
		frameTabelaPredavanje.getContentPane().add(scrollPane);
		
		tablePredavanje = new JTable();
		scrollPane.setViewportView(tablePredavanje);
		tablePredavanje.setModel(new CustomDefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Dan Predavanje", "Pocetak Predavanje", "Kraj Predavanje"
			}
		));
		
		
		tablePredavanje.getColumnModel().getColumn(1).setPreferredWidth(280);
		tablePredavanje.getColumnModel().getColumn(2).setPreferredWidth(136);
		
		try {
			popuniTabeluPredavanjima();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
			JButton btnIzbriiRed = new JButton("Izbriši red");
			btnIzbriiRed.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int red = tablePredavanje.getSelectedRow();
					if(red != -1)
					{
						try {
							Object id = tablePredavanje.getValueAt(red, 0);
							IzbrisiRed.izbrisiRed(id,"sifPredavanje","predavanje");
							modelPredavanje.removeRow(red);
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
		
		btnIzbriiRed.setBounds(12, 356, 117, 25);
		frameTabelaPredavanje.getContentPane().add(btnIzbriiRed);
		
	}
	
	/**
	 * metod koji vrsi popunjavanje tabele
	 */
	private void popuniTabeluPredavanjima() throws SQLException {
		modelPredavanje = (CustomDefaultTableModel) tablePredavanje.getModel();

		PomocneF.resetTable(modelPredavanje);

		DBExecutePredavanje.getPredavanja();
		ArrayList<Predavanje_> predavanja = new ArrayList<Predavanje_>();
		predavanja = Predavanje.predavanjeLista;
		for (int i = 0; i < predavanja.size(); i++) {
			Predavanje_ predavanje = new Predavanje_();	
			predavanje = predavanja.get(i);
			String sifPredString = String.valueOf(predavanje.getSifPredavanje());
			/**
			 * Trebamo se povezati sa tabelom 'sala' da pokupimo string sale na osnovu sifre.
			 */
			DBExecuteSala.getSala();
			ArrayList<Sala_> sale = new ArrayList<Sala_>();
			sale = Sala.salaLista;
			Sala_ sala = new Sala_();
			for (int j = 0; j < sale.size(); j++) {
				Sala_ salaPom = new Sala_();
				salaPom = sale.get(j);
				if(salaPom.getSifSala() == sala.getSifSala()){
					sala = salaPom;
					break;
				}
			}
			
			modelPredavanje.addRow(new Object[]{sifPredString, predavanje.getDanPredavanje(), predavanje.getPocetakPredavanje(), predavanje.getKrajPredavanje()});
		}
	}
}
