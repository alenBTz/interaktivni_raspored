package modeli;

public class Predmet_ {
	int sifPredmet;
	String nazPredmet;
	String kratPredmet;
	int sifSemestar;
	
	public Predmet_(){
		this.sifPredmet = -1;
		this.nazPredmet = "";
		this.kratPredmet = "";
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

	public void setSifSemestar(int sifSemestar) {
		this.sifSemestar = sifSemestar;
	}
}
