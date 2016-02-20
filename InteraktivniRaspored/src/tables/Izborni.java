package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.Izborni_;

public class Izborni {
	
	public static ArrayList<Izborni_> izborniLista = new ArrayList<Izborni_>();
	
	public static ArrayList<Izborni_> getIzborniList(ResultSet rs) throws SQLException{
		
		while(rs.next()){
			Izborni_ izborni = new Izborni_();
			izborni.setSifIzborni(rs.getInt("sifIzborni"));
			izborni.setIzborni(rs.getString("izborni"));
			
			izborniLista.add(izborni);
		}
		return izborniLista;
	}
}
