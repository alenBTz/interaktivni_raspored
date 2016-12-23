package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.Student_;

public class Student {

	public static ArrayList<Student_> studentLista = new ArrayList<Student_>();

	/**
	 * @author dino
	 * Metod getNastavnikList koji iz baze podataka uzima sve n-torke iz tabele nastavnik,
	 * i unosi ih u vektor Nastavnik_.
	 */
	public static ArrayList<Student_> getStudentList(ResultSet rs) throws SQLException{
		
		while (rs.next()) {
			Student_ student = new Student_();	
			student.setSifStudent(rs.getInt("sifStudent"));
			student.setImeStudent(rs.getString("imeStudent"));
			student.setPrezStudent(rs.getString("prezStudent"));
			student.setJmbgStudent(rs.getString("jmbgStudent"));
			student.setBrIndexa(rs.getString("brIndexa"));
			student.setSifGrupa(rs.getInt("sifGrupa"));
			student.setSifUsmjerenja(rs.getInt("sifUsmjerenja"));
			student.setSifSemestra(rs.getInt("sifSemestra"));
			studentLista.add(student);
			
		}
		
		return studentLista;
		
	}
}
