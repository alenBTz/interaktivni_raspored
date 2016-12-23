package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;

import dataBase.DBExecuteGrupa;
import modeli.CustomDefaultTableModel;
import modeli.Grupa_;
import pomocneF.IzbrisiRed;
import pomocneF.PomocneF;
import tables.Grupa;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabelaGrupaGUI {

	public static JFrame frameTabelaGrupa;
	private JTable tableGrupa;
	private CustomDefaultTableModel modelGrupa;

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
	public TabelaGrupaGUI() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException{
		frameTabelaGrupa = new JFrame();
		frameTabelaGrupa.setBounds(100, 100, 260, 408);
		frameTabelaGrupa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaGrupa.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 234, 320);
		frameTabelaGrupa.getContentPane().add(scrollPane);
		
		tableGrupa = new JTable();
		scrollPane.setViewportView(tableGrupa);
		tableGrupa.setModel(new CustomDefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Grupa"
			}
		));
		tableGrupa.getColumnModel().getColumn(1).setPreferredWidth(199);
		
	
			popuniTabeluGrupama();

		
		
		JButton btnIzbriiGrupu = new JButton("Izbriši grupu");
		btnIzbriiGrupu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int red = tableGrupa.getSelectedRow();
				if(red != -1)
				{
					try {
						Object id = tableGrupa.getValueAt(red, 0);
						IzbrisiRed.izbrisiRed(id,"sifGrupa","grupa");
						modelGrupa.removeRow(red);
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
		btnIzbriiGrupu.setBounds(12, 344, 150, 25);
		frameTabelaGrupa.getContentPane().add(btnIzbriiGrupu);
	}
	
	
	private void popuniTabeluGrupama() throws SQLException{
		modelGrupa = (CustomDefaultTableModel) tableGrupa.getModel();

		PomocneF.resetTable(modelGrupa);

		DBExecuteGrupa.getGrupe();
		ArrayList<Grupa_> grupe = new ArrayList<Grupa_>();
		grupe = Grupa.grupaLista;

		for (int i = 0; i < grupe.size(); i++) {
			Grupa_ grupa = new Grupa_();	
			grupa = grupe.get(i);
			String sifGrupaString = String.valueOf(grupa.getSifGrupa());

			modelGrupa.addRow(new Object[]{sifGrupaString, grupa.getNazivGrupa()});
		}
	}
	
	
}
