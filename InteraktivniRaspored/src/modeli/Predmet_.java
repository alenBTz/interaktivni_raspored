package modeli;

public class Predmet_ {
	int sifPredmet;
	String nazPredmet;
	String kratPredmet;
	int izborni;
	String tipNastave;
	int sifNastavnik;
	int sifSemestar;
	
	public Predmet_(){
		this.sifPredmet = -1;
		this.nazPredmet = "";
		this.kratPredmet = "";
		this.izborni = -1;
		this.tipNastave = "";
		this.sifNastavnik = -1;
		this.sifSemestar = -1;
	}

	public int getSifPredmet() {
		return sifPredmet;
	}

	public String getNazPredmet() {
		return nazPredmet;
	}

	public String getKratPredmet() {
		return kratPredmet;
	}

	public int getIzborni() {
		return izborni;
	}

	public String getTipNastave() {
		return tipNastave;
	}

	public int getSifNastavnik() {
		return sifNastavnik;
	}

	public int getSifSemestar() {
		return sifSemestar;
	}

	public void setSifPredmet(int sifPredmet) {
		this.sifPredmet = sifPredmet;
	}

	public void setNazPredmet(String nazPredmet) {
		this.nazPredmet = nazPredmet;
	}

	public void setKratPredmet(String kratPredmet) {
		this.kratPredmet = kratPredmet;
	}

	public void setIzborni(int izborni) {
		this.izborni = izborni;
	}

	public void setTipNastave(String tipNastave) {
		this.tipNastave = tipNastave;
	}

	public void setSifNastavnik(int sifNastavnik) {
		this.sifNastavnik = sifNastavnik;
	}

	public void setSifSemestar(int sifSemestar) {
		this.sifSemestar = sifSemestar;
	}
}
