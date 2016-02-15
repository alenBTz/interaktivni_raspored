package modeli;

public class Sala_ {
	int sifSala;
	String kratSala;
	String nazivSala;
	int sifZgrada;
	int brMjesta;
	
	public Sala_() {
		this.sifSala = -1;
		this.kratSala = "";
		this.nazivSala = "";
		this.sifZgrada = -1;
		this.brMjesta = -1;
	}

	public int getSifSala() {
		return sifSala;
	}

	public String getKratSala() {
		return kratSala;
	}

	public String getNazivSala() {
		return nazivSala;
	}

	public int getSifZgrada() {
		return sifZgrada;
	}

	public int getBrMjesta() {
		return brMjesta;
	}

	public void setSifSala(int sifSala) {
		this.sifSala = sifSala;
	}

	public void setKratSala(String kratSala) {
		this.kratSala = kratSala;
	}

	public void setNazivSala(String nazivSala) {
		this.nazivSala = nazivSala;
	}

	public void setSifZgrada(int sifZgrada) {
		this.sifZgrada = sifZgrada;
	}

	public void setBrMjesta(int brMjesta) {
		this.brMjesta = brMjesta;
	}
	
	
}
