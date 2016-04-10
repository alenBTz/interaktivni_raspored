package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dataBase.DBExecuteSala;
import dataBase.DBExecuteZgrada;
import modeli.Sala_;
import modeli.Zgrada_;
import pomocneF.IzbrisiRed;
import pomocneF.PomocneF;
import tables.Sala;
import tables.Zgrada;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabelaSalaGUI {

	public static JFrame frameTabelaSala;
	private JTable tableSale;
	private DefaultTableModel modelSala;
	/**
	 * Launch the application.
	 */
	public static void startTabelaSalaGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaSalaGUI window = new TabelaSalaGUI();
					window.frameTabelaSala.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabelaSalaGUI() throws SQLException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException{
		frameTabelaSala = new JFrame();
		frameTabelaSala.setBounds(100, 100, 800, 450);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaSala.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaSala.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 774, 362);
		frameTabelaSala.getContentPane().add(scrollPane);
		
		tableSale = new JTable();
		scrollPane.setViewportView(tableSale);
		tableSale.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Zgrada", "\u0160ifra", "Naziv sale", "Skra\u0107enica", "Kapacitet"
			}
		));
		tableSale.getColumnModel().getColumn(0).setPreferredWidth(193);
		tableSale.getColumnModel().getColumn(2).setPreferredWidth(201);
		tableSale.getColumnModel().getColumn(3).setPreferredWidth(81);
		
		/**
		 * Funkcija implementira popunjavanje date tabele zgradama
		 */
			popuniTabeluSalama();
		
		
		JButton btnIzbrisiSalu = new JButton("Izbriši salu");
		btnIzbrisiSalu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int red = tableSale.getSelectedRow();
				if(red != -1)
				{
					try {
						Object id = tableSale.getValueAt(red, 1);
						IzbrisiRed.izbrisiRed(id,"sifSala","sala");
						modelSala.removeRow(red);
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
		btnIzbrisiSalu.setBounds(12, 386, 117, 25);
		frameTabelaSala.getContentPane().add(btnIzbrisiSalu);
	}
	
	private void popuniTabeluSalama() throws SQLException{
		modelSala = (DefaultTableModel) tableSale.getModel();

		PomocneF.resetTable(modelSala);

		DBExecuteSala.getSala();
		DBExecuteZgrada.getZgrade();

		ArrayList<Sala_> sale = new ArrayList<Sala_>();
		ArrayList<Zgrada_> zgrade = new ArrayList<Zgrada_>();

		sale = Sala.salaLista;
		zgrade = Zgrada.zgradaLista;


		for (int i = 0; i < sale.size(); i++) {
			Sala_ sala= new Sala_();	
			Zgrada_ zgrada = new Zgrada_();

			sala = sale.get(i);
			String sifSalaString = String.valueOf(sala.getSifSala());

			/**
			 * pretrazujemo vektor zgrada da nadjemo sa odgovarajucim indeksom. Kada ga pronadjemo, tu zgradu snimimo u varijablu zgrada i izadjemo.
			 */
			for (int j = 0; j < zgrade.size(); j++) {
				Zgrada_ zgrada1 = new Zgrada_();
				zgrada1 = zgrade.get(j);
				if(zgrada1.getSifZgrada() == sala.getSifZgrada()){
					zgrada = zgrada1;
					break;
				}
			}


			modelSala.addRow(new Object[]{zgrada.getNazZgrada(), sifSalaString, sala.getNazivSala(), sala.getKratSala(), sala.getBrMjesta()});
		}
	}

}
