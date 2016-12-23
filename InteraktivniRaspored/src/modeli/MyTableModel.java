package modeli;

import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import dataBase.DBExecuteNastavnik;
import gui.LoginGUI;

public class MyTableModel extends DefaultTableModel{

	/**
	 * 
	 */
	
	public MyTableModel(Object[][] rowData, String[] columnNames) {
		 super(rowData, columnNames);
	}

	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean isCellEditable(int row, int column) {
		String vlasnikRezervacije = (String)this.getValueAt(row, 7);
    	Nastavnik_ logovaniKorisnik = new Nastavnik_();
		try
		{
			logovaniKorisnik = DBExecuteNastavnik.getNastavnikBySifra(LoginGUI.sessionSifNastavnik);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		if (!vlasnikRezervacije.equals(logovaniKorisnik.getImeNastavnik() + " " + logovaniKorisnik.getPrezNastavnik()) || column == 0)// || !vlasnikRezervacije.equals("Prodekan")) 
		{
			return false;
		}  
		else 
		{
			return true;
		}
	}

}
