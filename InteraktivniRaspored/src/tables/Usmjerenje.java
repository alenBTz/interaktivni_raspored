package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.Usmjerenje_;

public class Usmjerenje {
	
	public static ArrayList<Usmjerenje_> usmjerenjeLista = new ArrayList<Usmjerenje_>();
	
	public static ArrayList<Usmjerenje_> getUsmjerenjeList(ResultSet rs) throws SQLException{
		
		while (rs.next()) {
			Usmjerenje_ usmjerenje = new Usmjerenje_();	
			usmjerenje.setSifUsmjerenje(rs.getInt("sifUsmjerenje"));
			usmjerenje.setNazUsmjerenje(rs.getString("nazUsmjerenje"));
			usmjerenjeLista.add(usmjerenje);
			
		}
		
		return usmjerenjeLista;
		
	}

}
