package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import dataBase.DBExecuteGrupa;
import modeli.Grupa_;
import pomocneF.PomocneF;
import tables.Grupa;

import javax.swing.JScrollPane;

public class TabelaGrupaGUI {

	public static JFrame frameTabelaGrupa;
	private JTable tableGrupa;

	/**
	 * Launch the application.
	 */
	public static void startTabeGrupaGUI (){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaGrupaGUI window = new TabelaGrupaGUI();
					window.frameTabelaGrupa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabelaGrupaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTabelaGrupa = new JFrame();
		frameTabelaGrupa.setBounds(100, 100, 260, 408);
		frameTabelaGrupa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaGrupa.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 234, 320);
		frameTabelaGrupa.getContentPane().add(scrollPane);
		
		tableGrupa = new JTable();
		scrollPane.setViewportView(tableGrupa);
		tableGrupa.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Grupa"
			}
		));
		tableGrupa.getColumnModel().getColumn(1).setPreferredWidth(199);
		
		try {
			popuniTabeluGrupama();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		JButton btnIzbriiGrupu = new JButton("Izbriši grupu");
		btnIzbriiGrupu.setBounds(12, 344, 150, 25);
		frameTabelaGrupa.getContentPane().add(btnIzbriiGrupu);
	}
	
	
	private void popuniTabeluGrupama() throws SQLException{
		DefaultTableModel model = (DefaultTableModel) tableGrupa.getModel();

		PomocneF.resetTable(model);

		DBExecuteGrupa.getGrupe();
		ArrayList<Grupa_> grupe = new ArrayList<>();
		grupe = Grupa.grupaLista;

		for (int i = 0; i < grupe.size(); i++) {
			Grupa_ grupa = new Grupa_();	
			grupa = grupe.get(i);
			String sifGupaString = String.valueOf(grupa.getSifGrupa());

			model.addRow(new Object[]{sifGupaString, grupa.getNazivGrupa()});
		}
	}
	
	
}
