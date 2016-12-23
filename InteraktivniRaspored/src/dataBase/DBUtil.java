package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author dino
 * Klasa u kojoj je sadrzana logika za konektovanje na bazu podataka.
 */
public class DBUtil {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String CONN_STRING = "jdbc:mysql://localhost/interaktivni_raspored";
	
	
	/**
	 * @author dino
	 * Metod za konektovanje na bazu podataka preko DriverManager-a 
	 */
	public static Connection getConnection(DBType dbType) throws SQLException{
		return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
	}
	
	/**
	 * @author dino
	 * Procesuiranje iznimke u slucaju da je konekcija neuspjesna
	 */
	public static void processException(SQLException e){
		System.err.println("Gre�ka: " + e.getMessage());
		System.err.println("Kod gre�ke: " + e.getErrorCode() );
		System.err.println("Reprezentacija gre�ke: " + e.getSQLState());

	}
}
