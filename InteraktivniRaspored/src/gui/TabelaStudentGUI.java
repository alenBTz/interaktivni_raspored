package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import dataBase.DBExecuteGrupa;
import dataBase.DBExecuteStudent;
import modeli.Grupa_;
import modeli.Student_;
import pomocneF.PomocneF;
import tables.Grupa;
import tables.Student;

import javax.swing.JScrollPane;

public class TabelaStudentGUI {

	public static JFrame frameTabelaStudent;
	private JTable tableStudent;

	/**
	 * Launch the application.
	 */
	public static void startTabelaStudentGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaStudentGUI window = new TabelaStudentGUI();
					window.frameTabelaStudent.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabelaStudentGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTabelaStudent = new JFrame();
		frameTabelaStudent.setBounds(100, 100, 700, 600);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaStudent.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 674, 512);
		frameTabelaStudent.getContentPane().add(scrollPane);
		
		tableStudent = new JTable();
		scrollPane.setViewportView(tableStudent);
		tableStudent.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Ime", "Prezime", "JMBG", "Broj indexa", "Grupa"
			}
		));
		tableStudent.getColumnModel().getColumn(1).setPreferredWidth(155);
		tableStudent.getColumnModel().getColumn(2).setPreferredWidth(205);
		tableStudent.getColumnModel().getColumn(3).setPreferredWidth(166);
		tableStudent.getColumnModel().getColumn(4).setPreferredWidth(90);
		tableStudent.getColumnModel().getColumn(5).setPreferredWidth(120);
		
		try {
			popuniTabeluStudentima();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JButton btnIzbrisiStudenta = new JButton("Izbriši studenta");
		btnIzbrisiStudenta.setBounds(12, 536, 150, 25);
		frameTabelaStudent.getContentPane().add(btnIzbrisiStudenta);
		
		
	}
	
	private void popuniTabeluStudentima() throws SQLException{
		DefaultTableModel model = (DefaultTableModel) tableStudent.getModel();

		PomocneF.resetTable(model);

		DBExecuteStudent.getStudent();
		DBExecuteGrupa.getGrupe();

		ArrayList<Student_> studenti = new ArrayList<>();
		ArrayList<Grupa_> grupe = new ArrayList<>();

		studenti = Student.studentLista;
		grupe = Grupa.grupaLista;

		for (int i = 0; i < studenti.size(); i++) {
			Student_ student = new Student_();
			Grupa_ grupa = new Grupa_();

			student = studenti.get(i);

			String sifStudString = String.valueOf(student.getSifStudent());
			String JMBGString = String.valueOf(student.getJmbgStudent());


			for (int j = 0; j < grupe.size(); j++) {
				Grupa_ grupa1 = new Grupa_();
				grupa1 = grupe.get(j);
				if(grupa1.getSifGrupa() == student.getSifGrupa()){
					grupa = grupa1;
					break;
				}
			}


			model.addRow(new Object[]{sifStudString, student.getImeStudent(), student.getPrezStudent(), JMBGString
					, student.getBrIndexa(), grupa.getNazivGrupa()});
		}
	}

}
