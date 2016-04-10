package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import dataBase.DBExecuteSemestar;
import modeli.Semestar_;
import pomocneF.IzbrisiRed;
import pomocneF.PomocneF;
import tables.Semestar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabelaSemestarGUI {

	public static JFrame frameTabelaSemestar;
	private JTable tableSemestar;
	private JScrollPane scrollPane;
	private DefaultTableModel modelSemestar;

	/**
	 * Launch the application.
	 */
	public static void startTabelaSemestarGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaSemestarGUI window = new TabelaSemestarGUI();
					window.frameTabelaSemestar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabelaSemestarGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTabelaSemestar = new JFrame();
		frameTabelaSemestar.setBounds(100, 100, 206, 400);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaSemestar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaSemestar.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 174, 312);
		frameTabelaSemestar.getContentPane().add(scrollPane);
		
		tableSemestar = new JTable();
		scrollPane.setViewportView(tableSemestar);
		tableSemestar.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Semestar"
			}
		));
		tableSemestar.getColumnModel().getColumn(1).setPreferredWidth(120);
		
		/**
		 * Popunjavamo tabelu semestrima pozivom metoda popuniTabeluSemestrima();
		 */
		try {
			popuniTabeluSemestrima();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		JButton btnIzbrisiSemestar = new JButton("Izbriši semestar");
		btnIzbrisiSemestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int red = tableSemestar.getSelectedRow();
				if(red != -1)
				{
					try {
						Object id = tableSemestar.getValueAt(red, 0);
						IzbrisiRed.izbrisiRed(id,"sifSemestar","semestar");
						modelSemestar.removeRow(red);
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
		btnIzbrisiSemestar.setBounds(12, 336, 174, 25);
		frameTabelaSemestar.getContentPane().add(btnIzbrisiSemestar);
	}
	
	private void popuniTabeluSemestrima() throws SQLException{
		modelSemestar = (DefaultTableModel) tableSemestar.getModel();

		PomocneF.resetTable(modelSemestar);

		DBExecuteSemestar.getSemestri();
		ArrayList<Semestar_> semestri = new ArrayList<Semestar_>();
		semestri = Semestar.semestarLista;
		for (int i = 0; i < semestri.size(); i++) {
			Semestar_ semestar = new Semestar_();	
			semestar = semestri.get(i);
			String sifSemtString = String.valueOf(semestar.getSifSemestar());

			modelSemestar.addRow(new Object[]{sifSemtString, semestar.getNazSemestar()});
		}
	}

}
