package modeli;

public class Predavanje_ {
	int sifPredavanje;
	String danPredavanje;
	int pocetakPredavanjeH;
	int pocetakPredavanjeMin;
	int krajPredavanjeH;
	int krajPredavanjeMin;
	
	public Predavanje_() {
		this.sifPredavanje = -1;
		this.danPredavanje = "";
		this.pocetakPredavanjeH = -1;
		this.pocetakPredavanjeMin = -1;
		this.krajPredavanjeH = -1;
		this.krajPredavanjeMin = -1;
	}

	public int getSifPredavanje() {
		return sifPredavanje;
	}

	public String getDanPredavanje() {
		return danPredavanje;
	}

	public int getPocetakPredavanjeH() {
		return pocetakPredavanjeH;
	}

	public int getPocetakPredavanjeMin() {
		return pocetakPredavanjeMin;
	}

	public int getKrajPredavanjeH() {
		return krajPredavanjeH;
	}

	public int getKrajPredavanjeMin() {
		return krajPredavanjeMin;
	}

	public void setSifPredavanje(int sifPredavanje) {
		this.sifPredavanje = sifPredavanje;
	}

	public void setDanPredavanje(String danPredavanje) {
		this.danPredavanje = danPredavanje;
	}

	public void setPocetakPredavanjeH(int pocetakPredavanjeH) {
		this.pocetakPredavanjeH = pocetakPredavanjeH;
	}

	public void setPocetakPredavanjeMin(int pocetakPredavanjeMin) {
		this.pocetakPredavanjeMin = pocetakPredavanjeMin;
	}

	public void setKrajPredavanjeH(int krajPredavanjeH) {
		this.krajPredavanjeH = krajPredavanjeH;
	}

	public void setKrajPredavanjeMin(int krajPredavanjeMin) {
		this.krajPredavanjeMin = krajPredavanjeMin;
	}
	
}
