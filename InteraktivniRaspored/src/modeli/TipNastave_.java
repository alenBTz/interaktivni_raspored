package modeli;

public class TipNastave_ {
	int sifTipNastave;
	String nazTipNastave;
	String kratTipNastave;
	
	public TipNastave_() {
		this.sifTipNastave = -1;
		this.nazTipNastave = "";
		this.kratTipNastave = "";
	}

	public int getSifTipNastave() {
		return sifTipNastave;
	}

	public String getNazTipNastave() {
		return nazTipNastave;
	}

	public String getKratTipNastav() {
		return kratTipNastave;
	}

	public void setSifTipNastave(int sifTipNastave) {
		this.sifTipNastave = sifTipNastave;
	}

	public void setNazTipNastave(String nazTipNastave) {
		this.nazTipNastave = nazTipNastave;
	}

	public void setKratTipNastav(String kratTipNastav) {
		this.kratTipNastave = kratTipNastav;
	}
	
	
}
