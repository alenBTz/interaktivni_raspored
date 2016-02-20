package tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeli.TipNastave_;

public class TipNastave {

	
	public static ArrayList<TipNastave_> tipNastaveLista = new ArrayList<TipNastave_>();

	/**
	 * @author dino
	 * Metod getNastavnikList koji iz baze podataka uzima sve n-torke iz tabele nastavnik,
	 * i unosi ih u vektor Nastavnik_.
	 */
	public static ArrayList<TipNastave_> getTipNastaveList(ResultSet rs) throws SQLException{
		
		while (rs.next()) {
			TipNastave_ tipNastave = new TipNastave_();	
			tipNastave.setSifTipNastave(rs.getInt("sifTipNastave"));
			tipNastave.setNazTipNastave(rs.getString("nazTipNastave"));
			tipNastave.setKratTipNastav(rs.getString("kratTipNastave"));
			tipNastaveLista.add(tipNastave);
			
		}
		
		return tipNastaveLista;
		
	}
}
