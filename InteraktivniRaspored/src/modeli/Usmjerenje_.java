package modeli;

public class Usmjerenje_ {
	int sifUsmjerenje;
	String nazUsmjerenje;
	
	/**
	 * @author Samir
	 * default konstruktor za Usmjerenje_
	 */
	public Usmjerenje_(){
		sifUsmjerenje = -1;
		nazUsmjerenje = "";
	}
	
	public int getSifUsmjerenje() {
		return sifUsmjerenje;
	}
	public String getNazUsmjerenje() {
		return nazUsmjerenje;
	}
	public void setSifUsmjerenje(int sifUsmjerenje) {
		this.sifUsmjerenje = sifUsmjerenje;
	}
	public void setNazUsmjerenje(String nazUsmjerenje) {
		this.nazUsmjerenje = nazUsmjerenje;
	}
	
}
