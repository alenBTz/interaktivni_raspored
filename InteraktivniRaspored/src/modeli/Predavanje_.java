package modeli;

import java.sql.Time;

public class Predavanje_ {
	int sifPredavanje;
	String danPredavanje;
	Time pocetakPredavanje;
	Time krajPredavanje;
	int sifSala;
	String tipPredavanja;
	
	public Predavanje_() {
		
		this.sifPredavanje = -1;
		this.danPredavanje = "";
		this.pocetakPredavanje = Time.valueOf("00:00:00");
		this.krajPredavanje = Time.valueOf("00:00:00");
		this.sifSala = -1;
		this.tipPredavanja = "";
	}

	public int getSifPredavanje() {
		return sifPredavanje;
	}
	
	public String getTipPredavanja() {
		return tipPredavanja;
	}

	public String getDanPredavanje() {
		return danPredavanje;
	}

	public Time getPocetakPredavanje() {
		return pocetakPredavanje;
	}

	public Time getKrajPredavanje() {
		return krajPredavanje;
	}

	public int getSifSala() {
		return sifSala;
	}

	public void setSifPredavanje(int sifPredavanje) {
		this.sifPredavanje = sifPredavanje;
	}
	
	public void setTipPredavanja(String tipPredavanja) {
		this.tipPredavanja = tipPredavanja;
	}

	public void setDanPredavanje(String danPredavanje) {
		this.danPredavanje = danPredavanje;
	}

	public void setPocetakPredavanje(Time pocetakPredavanje) {
		this.pocetakPredavanje = pocetakPredavanje;
	}

	public void setKrajPredavanje(Time krajPredavanje) {
		this.krajPredavanje = krajPredavanje;
	}

	public void setSifSala(int sifSala) {
		this.sifSala = sifSala;
	}
	
	
	
}
