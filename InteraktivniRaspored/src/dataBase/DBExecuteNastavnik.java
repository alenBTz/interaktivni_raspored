package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author dino
 * Klasa koja uspostavlja konekciju sa Bazom podataka, i izvršava jednu SQl naredbu vezanu za tabelu nastavnik.
 */
public class DBExecuteNastavnik {

	private static final String SQL = "SELECT * FROM nastavnik";
	

	/**
	 * @author dino
	 * Metod za uspostavljanje konekcije na bazu podataka.
	 */
	public static void connect() throws SQLException {

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(SQL);
				) {
		
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
		}
	}
	
	
}
