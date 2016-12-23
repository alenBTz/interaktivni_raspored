package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modeli.PredavanjeUsmjerenjeSemestar_;
import modeli.Student_;
import tables.PredavanjeUsmjerenjeSemestar;
import tables.Student;

public class DBExecuteStudent {

	
	private static final String SQL = "SELECT * FROM student";

	public static void getStudent() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
			Student.getStudentList(rs);
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	public static ArrayList<Student_> getStudentsByGrupa(int sifGrupa) throws SQLException 
	{
		String sqlGetByName = "SELECT * FROM student WHERE sifGrupa = '" + sifGrupa + "'";
		//System.out.println(sqlGetByName);
		ArrayList<Student_> lista = null;
		try
		{
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sqlGetByName);
				lista = Student.getStudentList(rs);
		} 
		catch (SQLException e)
		{
			DBUtil.processException(e);
			System.out.println("U bazi ne postoji student sa grupom :"+ sifGrupa);
		}
		
		return lista;
	}
	
	/**
	 * @author dino
	 * Metod za ubacivanje jednog studenta u tabelu.
	 * @return 
	 */
	public static boolean insertStudent(Student_ student) throws SQLException {
		
		String sqlInsert = "INSERT INTO student (imeStudent, prezStudent, jmbgStudent, brIndexa, sifGrupa, sifSemestra, sifUsmjerenja) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
				) {
			Statement stmtPom1 = conn.createStatement();
			stmtPom1.execute("SET FOREIGN_KEY_CHECKS=0");
			stmtPom1.close();
			
			stmt.setString(1, student.getImeStudent());
			stmt.setString(2, student.getPrezStudent());
			stmt.setString(3, student.getJmbgStudent());
			stmt.setString(4, student.getBrIndexa());
			stmt.setInt(5, student.getSifGrupa());
			stmt.setInt(6, student.getSifSemestra());
			stmt.setInt(7, student.getSifUsmjerenja());
			int affected = stmt.executeUpdate();
			
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				student.setSifGrupa(newKey);
			} else {
				System.err.println("Nijedan red nije izmjenjen");
				return false;
			}
			
			Statement stmtPom2 = conn.createStatement();
			stmtPom2.execute("SET FOREIGN_KEY_CHECKS=1");
			stmtPom2.close();
			
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			return false;
		} 
		finally {
			if(keys != null)
				keys.close();

		}
		
		return true;
	}
	
	
}
