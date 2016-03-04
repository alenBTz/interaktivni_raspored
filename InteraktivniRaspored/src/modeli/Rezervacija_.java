package modeli;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class Rezervacija_ {
	int sifRezervacija;
	Date datumRezervacija;
	String tipRezervacije;
	Time satRezervacije;
	Time satRezervacijeKraj;
	int sifPredmet;
	int sifSala;
	int sifNastavnik;
	
	java.util.Date pomDatum = new java.util.Date();
	
	public Rezervacija_() {
		String pomDatumString = pomDatum.toString();
		String sat = pomDatum.toString();
		sat = sat.substring(11, 19);
		/*
		 * yyyy-[m]m-[d]d
		 * dow mon dd hh:mm:ss zzz yyyy		
		 * Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec
		 */
		String pomDatumStringMjesec = pomDatumString.substring(4, 7);
		switch (pomDatumStringMjesec) {
		case "Jan":
			pomDatumStringMjesec = "01";
			break;
		case "Feb":
			pomDatumStringMjesec = "02";
			break;
		case "Mar":
			pomDatumStringMjesec = "03";
			break;
		case "Apr":
			pomDatumStringMjesec = "04";
			break;
		case "May":
			pomDatumStringMjesec = "05";
			break;
		case "Jun":
			pomDatumStringMjesec = "06";
			break;
		case "Jul":
			pomDatumStringMjesec = "07";
			break;
		case "Aug":
			pomDatumStringMjesec = "08";
			break;
		case "Sep":
			pomDatumStringMjesec = "09";
			break;
		case "Oct":
			pomDatumStringMjesec = "10";
			break;
		case "Nov":
			pomDatumStringMjesec = "11";
			break;
		case "Dec":
			pomDatumStringMjesec = "12";
			break;
		default:
			break;
		}
		pomDatumString = pomDatumString.substring(24, 28) + "-" + pomDatumStringMjesec + "-" 
		 +  pomDatumString.substring(8, 10);
		
		
		this.sifRezervacija = -1;
		this.datumRezervacija = Date.valueOf(pomDatumString);
		this.tipRezervacije = "";
		this.satRezervacije = Time.valueOf(sat);
		this.satRezervacijeKraj = Time.valueOf("00:00:00");
		this.sifPredmet = -1;
		this.sifSala = -1;
		this.sifNastavnik = -1;
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

	public Time getSatRezervacije() {
		return satRezervacije;
	}

	public Time getSatRezervacijeKraj() {
		return satRezervacijeKraj;
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

	public void setSatRezervacije(Time satRezervacije) {
		this.satRezervacije = satRezervacije;
	}

	public void setSatRezervacijeKraj(Time satRezervacijeKraj) {
		this.satRezervacijeKraj = satRezervacijeKraj;
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
