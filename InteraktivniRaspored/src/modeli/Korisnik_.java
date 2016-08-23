package modeli;

/**
 * @author dino
 * Klasa Korisnik_ koja ima jednaka polja kao i tabela korisnik iz baze podataka. 
 * Sadrzi metode za dohvatanje i postavljanje podataka o korisniku
 */
public class Korisnik_ {
	private int sifKorisnik;
	private String korisnickoIme;
	private String sifra;
	private String uloga;
	private String ime;
	private String prezime;
	
	
	public Korisnik_(){
		this.sifKorisnik = -1;
		this.korisnickoIme = "";
		this.sifra = "";
		this.uloga = "";
		this.ime = "";
		this.prezime = "";
	}
	
	public int getSifKorisnik() {
		return sifKorisnik;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public String getSifra() {
		System.out.println("sifra");
		return sifra;
	}
	public String getUloga() {
		System.out.println("uloga");
		return uloga;
	}
	public String getIme() {
		return ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setSifKorisnik(int sifKorisnik) {
		this.sifKorisnik = sifKorisnik;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
}
