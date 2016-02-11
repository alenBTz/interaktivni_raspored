package modeli;

/**
 * @author dino
 * Klasa Nastavnik_ koja ima jednaka polja kao i tabela nastavnik iz baze podataka. 
 * Sadrzi metode za dohvatanje i postavljanje podataka o nastavniku
 */
public class Nastavnik_ {
	int sifNastavnik;
	String imeNastavnik;
	String prezNastavnik;
	int vrstaNastavnik;
	String username;
	String password;
	
	/**
	 * @author dino
	 * default konstruktor za Nastavnik_
	 */
	public Nastavnik_(){
		sifNastavnik = -1;
		imeNastavnik = "";
		prezNastavnik = "";
		vrstaNastavnik = -1;
		username = "";
		password = "";
	}
	
	public int getSifNastavnik() {
		return sifNastavnik;
	}
	public String getImeNastavnik() {
		return imeNastavnik;
	}
	public String getPrezNastavnik() {
		return prezNastavnik;
	}
	public int getVrstaNastavnik() {
		return vrstaNastavnik;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setSifNastavnik(int sifNastavnik) {
		this.sifNastavnik = sifNastavnik;
	}
	public void setImeNastavnik(String imeNastavnik) {
		this.imeNastavnik = imeNastavnik;
	}
	public void setPrezNastavnik(String prezNastavnik) {
		this.prezNastavnik = prezNastavnik;
	}
	public void setVrstaNastavnik(int vrstaNastavnik) {
		this.vrstaNastavnik = vrstaNastavnik;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
