package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;

import dataBase.DBExecuteGrupa;
import dataBase.DBExecuteStudent;
import modeli.CustomDefaultTableModel;
import modeli.Grupa_;
import modeli.Student_;
import pomocneF.IzbrisiRed;
import pomocneF.PomocneF;
import tables.Grupa;
import tables.Student;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabelaStudentGUI {

	public static JFrame frameTabelaStudent;
	private JTable tableStudent;
	private CustomDefaultTableModel modelStudent;
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
		tableStudent.setModel(new CustomDefaultTableModel(
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
		btnIzbrisiStudenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int red = tableStudent.getSelectedRow();
				if(red != -1)
				{
					try {
						Object id = tableStudent.getValueAt(red, 0);
						IzbrisiRed.izbrisiRed(id,"sifStudent","student");
						modelStudent.removeRow(red);
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
		btnIzbrisiStudenta.setBounds(12, 536, 150, 25);
		frameTabelaStudent.getContentPane().add(btnIzbrisiStudenta);
		
		
	}
	
	private void popuniTabeluStudentima() throws SQLException{
		modelStudent = (CustomDefaultTableModel) tableStudent.getModel();

		PomocneF.resetTable(modelStudent);

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


			modelStudent.addRow(new Object[]{sifStudString, student.getImeStudent(), student.getPrezStudent(), JMBGString
					, student.getBrIndexa(), grupa.getNazivGrupa()});
		}
	}

}
