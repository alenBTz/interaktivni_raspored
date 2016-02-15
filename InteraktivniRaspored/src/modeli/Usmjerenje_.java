package modeli;

public class Usmjerenje_ {
	int sifUsmjerenje;
	String nazUsmjerenje;
	String kratUsmjerenje;
	
	public Usmjerenje_() {
		this.sifUsmjerenje = -1;
		this.nazUsmjerenje = "";
		this.kratUsmjerenje = "";
	}

	public int getSifUsmjerenje() {
		return sifUsmjerenje;
	}

	public String getNazUsmjerenje() {
		return nazUsmjerenje;
	}

	public String getKratUsmjerenje() {
		return kratUsmjerenje;
	}

	public void setSifUsmjerenje(int sifUsmjerenje) {
		this.sifUsmjerenje = sifUsmjerenje;
	}

	public void setNazUsmjerenje(String nazUsmjerenje) {
		this.nazUsmjerenje = nazUsmjerenje;
	}

	public void setKratUsmjerenje(String kratUsmjerenje) {
		this.kratUsmjerenje = kratUsmjerenje;
	}

}