package modeli;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Rezervacija_ {
	int sifRezervacija;
	Date datumRezervacija;
	String tipRezervacije;
	int satRezervacijeH;
	int satRezervacijeMin;
	int satRezervacijeKrajH;
	int satRezervacijeKrajMin;
	int sifPredmet;
	int sifSala;
	int sifNastavnik;
	
	public Rezervacija_() {
		this.sifRezervacija = -1;
		this.datumRezervacija = new Date();
		this.tipRezervacije = "";
		this.satRezervacijeH =  -1;
		this.satRezervacijeMin =  -1;
		this.satRezervacijeKrajH =  -1;
		this.satRezervacijeKrajMin =  -1;
		this.sifPredmet =  -1;
		this.sifSala =  -1;
		this.sifNastavnik =  -1;
	}

	public int getSifRezervacija() {
		return sifRezervacija;
	}

	public Date getDatumRezervacija() {
		return datumRezervacija;
	}

	public String getTipRezervacije() {
		return tipRezervacije;
	}

	public int getSatRezervacijeH() {
		return satRezervacijeH;
	}

	public int getSatRezervacijeMin() {
		return satRezervacijeMin;
	}

	public int getSatRezervacijeKrajH() {
		return satRezervacijeKrajH;
	}

	public int getSatRezervacijeKrajMin() {
		return satRezervacijeKrajMin;
	}

	public int getSifPredmet() {
		return sifPredmet;
	}

	public int getSifSala() {
		return sifSala;
	}

	public int getSifNastavnik() {
		return sifNastavnik;
	}

	public void setSifRezervacija(int sifRezervacija) {
		this.sifRezervacija = sifRezervacija;
	}

	public void setDatumRezervacija(Date datumRezervacija) {
		this.datumRezervacija = datumRezervacija;
	}

	public void setTipRezervacije(String tipRezervacije) {
		this.tipRezervacije = tipRezervacije;
	}

	public void setSatRezervacijeH(int satRezervacijeH) {
		this.satRezervacijeH = satRezervacijeH;
	}

	public void setSatRezervacijeMin(int satRezervacijeMin) {
		this.satRezervacijeMin = satRezervacijeMin;
	}

	public void setSatRezervacijeKrajH(int satRezervacijeKrajH) {
		this.satRezervacijeKrajH = satRezervacijeKrajH;
	}

	public void setSatRezervacijeKrajMin(int satRezervacijeKrajMin) {
		this.satRezervacijeKrajMin = satRezervacijeKrajMin;
	}

	public void setSifPredmet(int sifPredmet) {
		this.sifPredmet = sifPredmet;
	}

	public void setSifSala(int sifSala) {
		this.sifSala = sifSala;
	}

	public void setSifNastavnik(int sifNastavnik) {
		this.sifNastavnik = sifNastavnik;
	}
	
	
}
