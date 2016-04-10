package pomocneF;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import dataBase.DBType;
import dataBase.DBUtil;
import tables.Usmjerenje;

public class IzbrisiRed {

	public static boolean izbrisiRed(Object red,String imeKolone,String imeTabele) throws SQLException {
		

		 String sql = "DELETE FROM "+imeTabele+" WHERE "+imeKolone+" = ?";

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
				) {
			stmt.setObject(1,red);
			int affected = stmt.executeUpdate();
			if (affected == 1){
				return true;
			}
			else{
				return false;
			}
			
		} 
		catch (SQLException e) {
			DBUtil.processException(e);
			System.err.println(e);
			return false;
		}
	}
}
