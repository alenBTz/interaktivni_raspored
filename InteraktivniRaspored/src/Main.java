import java.sql.SQLException;

import gui.ProdekanGUI;
import modeli.Rezervacija_;




public class Main {

	public static void main(String[] args) throws SQLException {
		
		ProdekanGUI.startProdekanGUI();
		
		Rezervacija_ rez = new Rezervacija_();
		System.out.println(rez.getDatumRezervacija());

		System.out.println(rez.getSatRezervacije());
	}

}
