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
	
	/**
	 * @author dino
	 * default konstruktor za Nastavnik_
	 */
	public Nastavnik_(){
		this.sifNastavnik = -1;
		this.imeNastavnik = "";
		this.prezNastavnik = "";
		this.vrstaNastavnik = -1;
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
	
}
