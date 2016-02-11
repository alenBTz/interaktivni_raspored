import java.sql.SQLException;

import dataBase.DBExecuteNastavnik;
import gui.LoginScreen;




public class Main {

	public static void main(String[] args) throws SQLException {
		DBExecuteNastavnik.connect();
		
		LoginScreen.startLoginScreen();
	}

}
