package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.NastavnikGrupa_;

public class NastavnikGrupa {

	
	public static ArrayList<NastavnikGrupa_> nastavnikGrupaLista = new ArrayList<NastavnikGrupa_>();

	/**
	 * @author dino
	 * Metod getNastavnikList koji iz baze podataka uzima sve n-torke iz tabele nastavnik,
	 * i unosi ih u vektor Nastavnik_.
	 */
	public static ArrayList<NastavnikGrupa_> getNastavniGrupakList(ResultSet rs) throws SQLException{
		
		while (rs.next()) {
			NastavnikGrupa_ nastavnikGrupa = new NastavnikGrupa_();	
			nastavnikGrupa.setSifNastGrupa(rs.getInt("sifNastGrupa"));
			nastavnikGrupa.setSifNastavnik(rs.getInt("sifNastavnik"));
			nastavnikGrupa.setSifGrupa(rs.getInt("sifGrupa"));
			nastavnikGrupaLista.add(nastavnikGrupa);
			
		}
		
		return nastavnikGrupaLista;
		
	}
	
	
}
