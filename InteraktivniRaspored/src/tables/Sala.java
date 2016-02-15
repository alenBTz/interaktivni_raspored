package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.Sala_;


public class Sala {

	public static ArrayList<Sala_> salaLista = new ArrayList<Sala_>();


	public static ArrayList<Sala_> getSalaList(ResultSet rs) throws SQLException{
		
		while (rs.next()) {
			Sala_ sala = new Sala_();
			
			sala.setSifSala(rs.getInt("sifSala"));
			sala.setKratSala(rs.getString("kratSala"));
			sala.setNazivSala(rs.getString("nazivSala"));
			sala.setSifZgrada(rs.getInt("sifZgrada"));
			sala.setBrMjesta(rs.getInt("brMjesta"));
	
			salaLista.add(sala);
			
		}
		
		return salaLista;
		
	}
	
}
