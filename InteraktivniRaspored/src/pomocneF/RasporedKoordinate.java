package pomocneF;

import java.util.ArrayList;
import java.sql.Time;

public class RasporedKoordinate {

	public static ArrayList<RaspKoord_> koordinate = new ArrayList<>();
	
	/**
	 * Napuni vektor koordinatama
	 */
	
	public static RaspKoord_ getKoordinate(String dan, Time vrijemePoc, Time vrijemeKraj) {
		RaspKoord_ rk = new RaspKoord_();
		
		/**
		 * Prolazimo kroz vektor koordinata. Trazimo koordinate za odgovarajuci dan i odgovarajuce vrijeme pocetka i kraja.
		 * 
		 */
		
		
		for (int i = 0; i < koordinate.size(); i++) {
			RaspKoord_ rkPom = new RaspKoord_();
			rkPom = koordinate.get(i);
			
			if(rkPom.getDan().equals(dan) && rkPom.getVrijemePoc().equals(vrijemePoc)){
				int h = visina(vrijemePoc, vrijemeKraj);
				rk.setDan(rkPom.getDan());
				rk.setVrijemePoc(vrijemePoc);
				rk.setVrijemeKraj(vrijemeKraj);
				rk.setX(rkPom.getX());
				rk.setY(rkPom.getY());
				rk.setHeight(h);
				break;
			}

		}
		
		return rk;
	}

	public static void setKoordinate() {
		
		
		/**
		 * postavljanje koordinata za ponedeljak, utorak, srijedu, cetvrtak, petak, subotu i nedelju
		 */
		ponedeljak();
		//utorak(koord);
		//srijeda(koord);
		//cetvrtak(koord);
		//petak(koord);
		//subota(koord);
		//nedelja(koord);
		
		
	}
	
	private static void ponedeljak() {
		/*
		 * addPonKoord(vrijemePoc, vrijemeKraj, x, y);
		 */
		addPonKoord(Time.valueOf("08:00:00"), Time.valueOf("08:30:00"), 144, 84);
		addPonKoord(Time.valueOf("08:30:00"), Time.valueOf("09:00:00"), 144, 103);
		addPonKoord(Time.valueOf("09:00:00"), Time.valueOf("09:30:00"), 144, 136);
		addPonKoord(Time.valueOf("09:30:00"), Time.valueOf("10:00:00"), 144, 155);
		addPonKoord(Time.valueOf("10:00:00"), Time.valueOf("10:30:00"), 144, 188);
		addPonKoord(Time.valueOf("10:30:00"), Time.valueOf("11:00:00"), 144, 207);
		addPonKoord(Time.valueOf("11:00:00"), Time.valueOf("11:30:00"), 144, 240);
		addPonKoord(Time.valueOf("11:30:00"), Time.valueOf("12:00:00"), 144, 259);
		addPonKoord(Time.valueOf("12:00:00"), Time.valueOf("12:30:00"), 144, 292);
		addPonKoord(Time.valueOf("12:30:00"), Time.valueOf("13:00:00"), 144, 311);
		addPonKoord(Time.valueOf("13:00:00"), Time.valueOf("13:30:00"), 144, 344);
		addPonKoord(Time.valueOf("13:30:00"), Time.valueOf("14:00:00"), 144, 363);
		addPonKoord(Time.valueOf("14:00:00"), Time.valueOf("14:30:00"), 144, 396);
		addPonKoord(Time.valueOf("14:30:00"), Time.valueOf("15:00:00"), 144, 415);
		addPonKoord(Time.valueOf("15:00:00"), Time.valueOf("15:30:00"), 144, 448);
		addPonKoord(Time.valueOf("15:30:00"), Time.valueOf("16:00:00"), 144, 467);
		addPonKoord(Time.valueOf("16:00:00"), Time.valueOf("16:30:00"), 144, 500);
		addPonKoord(Time.valueOf("16:30:00"), Time.valueOf("17:00:00"), 144, 519);
		addPonKoord(Time.valueOf("17:00:00"), Time.valueOf("17:30:00"), 144, 552);
		addPonKoord(Time.valueOf("17:30:00"), Time.valueOf("18:00:00"), 144, 571);
		addPonKoord(Time.valueOf("18:00:00"), Time.valueOf("18:30:00"), 144, 601);
		addPonKoord(Time.valueOf("18:30:00"), Time.valueOf("19:00:00"), 144, 623);
		addPonKoord(Time.valueOf("19:00:00"), Time.valueOf("19:30:00"), 144, 656);
		addPonKoord(Time.valueOf("19:30:00"), Time.valueOf("20:00:00"), 144, 675);
	}

	private static void addPonKoord(Time vrijemePoc, Time vrijemeKraj, int x, int y) {
		RaspKoord_ koord = new RaspKoord_();
		
		koord.setDan("Ponedeljak");
		
		koord.setVrijemePoc(vrijemePoc);
		koord.setVrijemeKraj(vrijemeKraj);
		koord.setX(x);
		koord.setY(y);
		koordinate.add(koord);
	}
	
	
	private static int xKoordinate(String dan){
		int x;
		switch (dan) {
		case "Ponedeljak":
			x = 144;
			break;
		case "Utorak":
			x = 306;
			break;
		case "Srijeda":
			x = 468;
			break;
		case "Cetvrtak":
			x = 630;
			break;
		case "Petak":
			x = 792;
			break;
		case "Subota":
			x = 954;
			break;
		case "Nedelja":
			x = 1116;
			break;
		default:
			x = -1;
			break;
		}
		return x;
	}
	
	private static int yKoordinatePocetak(Time pocetakPred){
		int y = -1;
		
		if(pocetakPred.equals(Time.valueOf("08:00:00"))){
			y = 84;
		}
		else if (pocetakPred.equals(Time.valueOf("08:30:00"))){
			y = 103;
		}
		else if(pocetakPred.equals(Time.valueOf("09:00:00"))){
			y = 136;
		}
		else if (pocetakPred.equals(Time.valueOf("09:30:00"))){
			y = 155;
		}
		else if (pocetakPred.equals(Time.valueOf("10:00:00"))){
			y = 188;
		}
		else if (pocetakPred.equals(Time.valueOf("10:30:00"))){
			y = 207;
		}
		else if(pocetakPred.equals(Time.valueOf("11:00:00"))){
			y = 240;
		}
		else if (pocetakPred.equals(Time.valueOf("11:30:00"))){
			y = 259;
		}
		else if(pocetakPred.equals(Time.valueOf("12:00:00"))){
			y = 292;
		}
		else if (pocetakPred.equals(Time.valueOf("12:30:00"))){
			y = 311;
		}
		else if(pocetakPred.equals(Time.valueOf("13:00:00"))){
			y = 344;
		}
		else if (pocetakPred.equals(Time.valueOf("13:30:00"))){
			y = 363;
		}
		else if(pocetakPred.equals(Time.valueOf("14:00:00"))){
			y = 396;
		}
		else if (pocetakPred.equals(Time.valueOf("14:30:00"))){
			y = 415;
		}
		else if(pocetakPred.equals(Time.valueOf("15:00:00"))){
			y = 448;
		}
		else if (pocetakPred.equals(Time.valueOf("15:30:00"))){
			y = 467;
		}
		else if(pocetakPred.equals(Time.valueOf("16:00:00"))){
			y = 500;
		}
		else if (pocetakPred.equals(Time.valueOf("16:30:00"))){
			y = 519;
		}
		else if(pocetakPred.equals(Time.valueOf("17:00:00"))){
			y = 552;
		}
		else if (pocetakPred.equals(Time.valueOf("17:30:00"))){
			y = 571;
		}
		else if(pocetakPred.equals(Time.valueOf("18:00:00"))){
			y = 604;
		}
		else if (pocetakPred.equals(Time.valueOf("18:30:00"))){
			y = 623;
		}
		else if(pocetakPred.equals(Time.valueOf("19:00:00"))){
			y = 656;
		}
		else if (pocetakPred.equals(Time.valueOf("19:30:00"))){
			y = 675;
		}
		
		return y;
	}
	
	private static int yKoordinateKraj(Time krajPred){
		int y = -1;
		
		if (krajPred.equals(Time.valueOf("08:30:00"))){
			y = 103;
		}
		else if(krajPred.equals(Time.valueOf("09:00:00"))){
			y = 124;
		}
		else if (krajPred.equals(Time.valueOf("09:30:00"))){
			y = 155;
		}
		else if(krajPred.equals(Time.valueOf("10:00:00"))){
			y = 176;
		}
		else if (krajPred.equals(Time.valueOf("10:30:00"))){
			y = 207;
		}
		else if(krajPred.equals(Time.valueOf("11:00:00"))){
			y = 228;
		}
		else if (krajPred.equals(Time.valueOf("11:30:00"))){
			y = 259;
		}
		else if(krajPred.equals(Time.valueOf("12:00:00"))){
			y = 280;
		}
		else if (krajPred.equals(Time.valueOf("12:30:00"))){
			y = 311;
		}
		else if(krajPred.equals(Time.valueOf("13:00:00"))){
			y = 332;
		}
		else if (krajPred.equals(Time.valueOf("13:30:00"))){
			y = 363;
		}
		else if(krajPred.equals(Time.valueOf("14:00:00"))){
			y = 384;
		}
		else if (krajPred.equals(Time.valueOf("14:30:00"))){
			y = 415;
		}
		else if(krajPred.equals(Time.valueOf("15:00:00"))){
			y = 436;
		}
		else if (krajPred.equals(Time.valueOf("15:30:00"))){
			y = 467;
		}
		else if(krajPred.equals(Time.valueOf("16:00:00"))){
			y = 488;
		}
		else if (krajPred.equals(Time.valueOf("16:30:00"))){
			y = 519;
		}
		else if(krajPred.equals(Time.valueOf("17:00:00"))){
			y = 540;
		}
		else if (krajPred.equals(Time.valueOf("17:30:00"))){
			y = 571;
		}
		else if(krajPred.equals(Time.valueOf("18:00:00"))){
			y = 592;
		}
		else if (krajPred.equals(Time.valueOf("18:30:00"))){
			y = 623;
		}
		else if(krajPred.equals(Time.valueOf("19:00:00"))){
			y = 644;
		}
		else if (krajPred.equals(Time.valueOf("19:30:00"))){
			y = 675;
		}
		else if (krajPred.equals(Time.valueOf("20:00:00"))){
			y = 696;
		}
		return y;
	}

	private static int visina(Time pocetakPred, Time krajPred){
		/**
		 * visinu racunamo na nacin da uzmemo y koordinatu pocetka predavanja, ykoordinatu kraja predavanja, i to oduzmemo jedno od drugog. 
		 */
		int h = -1;
		h = yKoordinateKraj(krajPred)-yKoordinatePocetak(pocetakPred);

		return h;
	}
	
	/*
	
	private static void utorak(RaspKoord_ koord) {
		koord.setDan("Utorak");
		
		koord.setVrijeme("08:00 - 08:30");
		koord.setX(306);
		koord.setY(84);
		koordinate.add(koord);
		
		koord.setVrijeme("08:30 - 09:00");
		koord.setY(103);
		koordinate.add(koord);
		
		
		koord.setVrijeme("09:00 - 09:30");
		koord.setY(136);
		koordinate.add(koord);
		
		koord.setVrijeme("09:30 - 10:00");
		koord.setY(155);
		koordinate.add(koord);
		
		
		koord.setVrijeme("10:00 - 10:30");
		koord.setY(188);
		koordinate.add(koord);
		
		koord.setVrijeme("10:30 - 11:00");
		koord.setY(207);
		koordinate.add(koord);
		
		
		koord.setVrijeme("11:00 - 11:30");
		koord.setY(240);
		koordinate.add(koord);
		
		koord.setVrijeme("11:30 - 12:00");
		koord.setY(259);
		koordinate.add(koord);
		
		
		koord.setVrijeme("12:00 - 12:30");
		koord.setY(292);
		koordinate.add(koord);
		
		koord.setVrijeme("12:30 - 13:00");
		koord.setY(311);
		koordinate.add(koord);
		
		
		koord.setVrijeme("13:00 - 13:30");
		koord.setY(344);
		koordinate.add(koord);
		
		koord.setVrijeme("13:30 - 14:00");
		koord.setY(363);
		koordinate.add(koord);
		
		
		koord.setVrijeme("14:00 - 14:30");
		koord.setY(396);
		koordinate.add(koord);
		
		koord.setVrijeme("14:30 - 15:00");
		koord.setY(415);
		koordinate.add(koord);
		
		
		koord.setVrijeme("15:00 - 15:30");
		koord.setY(448);
		koordinate.add(koord);
		
		koord.setVrijeme("15:30 - 16:00");
		koord.setY(467);
		koordinate.add(koord);
		
		
		koord.setVrijeme("16:00 - 16:30");
		koord.setY(500);
		koordinate.add(koord);
		
		koord.setVrijeme("16:30 - 17:00");
		koord.setY(519);
		koordinate.add(koord);
		
		
		koord.setVrijeme("17:00 - 17:30");
		koord.setY(552);
		koordinate.add(koord);
		
		koord.setVrijeme("17:30 - 18:00");
		koord.setY(571);
		koordinate.add(koord);
		
		
		koord.setVrijeme("18:00 - 18:30");
		koord.setY(601);
		koordinate.add(koord);
		
		koord.setVrijeme("18:30 - 19:00");
		koord.setY(623);
		koordinate.add(koord);
		
		
		koord.setVrijeme("19:00 - 19:30");
		koord.setY(656);
		koordinate.add(koord);
		
		koord.setVrijeme("19:30 - 20:00");
		koord.setY(675);
		koordinate.add(koord);
	}
	
	private static void srijeda(RaspKoord_ koord) {
		koord.setDan("Srijeda");
		
		koord.setVrijeme("08:00 - 08:30");
		koord.setX(468);
		koord.setY(84);
		koordinate.add(koord);
		
		koord.setVrijeme("08:30 - 09:00");
		koord.setY(103);
		koordinate.add(koord);
		
		
		koord.setVrijeme("09:00 - 09:30");
		koord.setY(136);
		koordinate.add(koord);
		
		koord.setVrijeme("09:30 - 10:00");
		koord.setY(155);
		koordinate.add(koord);
		
		
		koord.setVrijeme("10:00 - 10:30");
		koord.setY(188);
		koordinate.add(koord);
		
		koord.setVrijeme("10:30 - 11:00");
		koord.setY(207);
		koordinate.add(koord);
		
		
		koord.setVrijeme("11:00 - 11:30");
		koord.setY(240);
		koordinate.add(koord);
		
		koord.setVrijeme("11:30 - 12:00");
		koord.setY(259);
		koordinate.add(koord);
		
		
		koord.setVrijeme("12:00 - 12:30");
		koord.setY(292);
		koordinate.add(koord);
		
		koord.setVrijeme("12:30 - 13:00");
		koord.setY(311);
		koordinate.add(koord);
		
		
		koord.setVrijeme("13:00 - 13:30");
		koord.setY(344);
		koordinate.add(koord);
		
		koord.setVrijeme("13:30 - 14:00");
		koord.setY(363);
		koordinate.add(koord);
		
		
		koord.setVrijeme("14:00 - 14:30");
		koord.setY(396);
		koordinate.add(koord);
		
		koord.setVrijeme("14:30 - 15:00");
		koord.setY(415);
		koordinate.add(koord);
		
		
		koord.setVrijeme("15:00 - 15:30");
		koord.setY(448);
		koordinate.add(koord);
		
		koord.setVrijeme("15:30 - 16:00");
		koord.setY(467);
		koordinate.add(koord);
		
		
		koord.setVrijeme("16:00 - 16:30");
		koord.setY(500);
		koordinate.add(koord);
		
		koord.setVrijeme("16:30 - 17:00");
		koord.setY(519);
		koordinate.add(koord);
		
		
		koord.setVrijeme("17:00 - 17:30");
		koord.setY(552);
		koordinate.add(koord);
		
		koord.setVrijeme("17:30 - 18:00");
		koord.setY(571);
		koordinate.add(koord);
		
		
		koord.setVrijeme("18:00 - 18:30");
		koord.setY(601);
		koordinate.add(koord);
		
		koord.setVrijeme("18:30 - 19:00");
		koord.setY(623);
		koordinate.add(koord);
		
		
		koord.setVrijeme("19:00 - 19:30");
		koord.setY(656);
		koordinate.add(koord);
		
		koord.setVrijeme("19:30 - 20:00");
		koord.setY(675);
		koordinate.add(koord);
	}
	
	private static void cetvrtak(RaspKoord_ koord) {
		koord.setDan("Cetvrtak");
		
		koord.setVrijeme("08:00 - 08:30");
		koord.setX(630);
		koord.setY(84);
		koordinate.add(koord);
		
		koord.setVrijeme("08:30 - 09:00");
		koord.setY(103);
		koordinate.add(koord);
		
		
		koord.setVrijeme("09:00 - 09:30");
		koord.setY(136);
		koordinate.add(koord);
		
		koord.setVrijeme("09:30 - 10:00");
		koord.setY(155);
		koordinate.add(koord);
		
		
		koord.setVrijeme("10:00 - 10:30");
		koord.setY(188);
		koordinate.add(koord);
		
		koord.setVrijeme("10:30 - 11:00");
		koord.setY(207);
		koordinate.add(koord);
		
		
		koord.setVrijeme("11:00 - 11:30");
		koord.setY(240);
		koordinate.add(koord);
		
		koord.setVrijeme("11:30 - 12:00");
		koord.setY(259);
		koordinate.add(koord);
		
		
		koord.setVrijeme("12:00 - 12:30");
		koord.setY(292);
		koordinate.add(koord);
		
		koord.setVrijeme("12:30 - 13:00");
		koord.setY(311);
		koordinate.add(koord);
		
		
		koord.setVrijeme("13:00 - 13:30");
		koord.setY(344);
		koordinate.add(koord);
		
		koord.setVrijeme("13:30 - 14:00");
		koord.setY(363);
		koordinate.add(koord);
		
		
		koord.setVrijeme("14:00 - 14:30");
		koord.setY(396);
		koordinate.add(koord);
		
		koord.setVrijeme("14:30 - 15:00");
		koord.setY(415);
		koordinate.add(koord);
		
		
		koord.setVrijeme("15:00 - 15:30");
		koord.setY(448);
		koordinate.add(koord);
		
		koord.setVrijeme("15:30 - 16:00");
		koord.setY(467);
		koordinate.add(koord);
		
		
		koord.setVrijeme("16:00 - 16:30");
		koord.setY(500);
		koordinate.add(koord);
		
		koord.setVrijeme("16:30 - 17:00");
		koord.setY(519);
		koordinate.add(koord);
		
		
		koord.setVrijeme("17:00 - 17:30");
		koord.setY(552);
		koordinate.add(koord);
		
		koord.setVrijeme("17:30 - 18:00");
		koord.setY(571);
		koordinate.add(koord);
		
		
		koord.setVrijeme("18:00 - 18:30");
		koord.setY(601);
		koordinate.add(koord);
		
		koord.setVrijeme("18:30 - 19:00");
		koord.setY(623);
		koordinate.add(koord);
		
		
		koord.setVrijeme("19:00 - 19:30");
		koord.setY(656);
		koordinate.add(koord);
		
		koord.setVrijeme("19:30 - 20:00");
		koord.setY(675);
		koordinate.add(koord);
	}
	
	private static void petak(RaspKoord_ koord) {
		koord.setDan("Petak");
		
		koord.setVrijeme("08:00 - 08:30");
		koord.setX(792);
		koord.setY(84);
		koordinate.add(koord);
		
		koord.setVrijeme("08:30 - 09:00");
		koord.setY(103);
		koordinate.add(koord);
		
		
		koord.setVrijeme("09:00 - 09:30");
		koord.setY(136);
		koordinate.add(koord);
		
		koord.setVrijeme("09:30 - 10:00");
		koord.setY(155);
		koordinate.add(koord);
		
		
		koord.setVrijeme("10:00 - 10:30");
		koord.setY(188);
		koordinate.add(koord);
		
		koord.setVrijeme("10:30 - 11:00");
		koord.setY(207);
		koordinate.add(koord);
		
		
		koord.setVrijeme("11:00 - 11:30");
		koord.setY(240);
		koordinate.add(koord);
		
		koord.setVrijeme("11:30 - 12:00");
		koord.setY(259);
		koordinate.add(koord);
		
		
		koord.setVrijeme("12:00 - 12:30");
		koord.setY(292);
		koordinate.add(koord);
		
		koord.setVrijeme("12:30 - 13:00");
		koord.setY(311);
		koordinate.add(koord);
		
		
		koord.setVrijeme("13:00 - 13:30");
		koord.setY(344);
		koordinate.add(koord);
		
		koord.setVrijeme("13:30 - 14:00");
		koord.setY(363);
		koordinate.add(koord);
		
		
		koord.setVrijeme("14:00 - 14:30");
		koord.setY(396);
		koordinate.add(koord);
		
		koord.setVrijeme("14:30 - 15:00");
		koord.setY(415);
		koordinate.add(koord);
		
		
		koord.setVrijeme("15:00 - 15:30");
		koord.setY(448);
		koordinate.add(koord);
		
		koord.setVrijeme("15:30 - 16:00");
		koord.setY(467);
		koordinate.add(koord);
		
		
		koord.setVrijeme("16:00 - 16:30");
		koord.setY(500);
		koordinate.add(koord);
		
		koord.setVrijeme("16:30 - 17:00");
		koord.setY(519);
		koordinate.add(koord);
		
		
		koord.setVrijeme("17:00 - 17:30");
		koord.setY(552);
		koordinate.add(koord);
		
		koord.setVrijeme("17:30 - 18:00");
		koord.setY(571);
		koordinate.add(koord);
		
		
		koord.setVrijeme("18:00 - 18:30");
		koord.setY(601);
		koordinate.add(koord);
		
		koord.setVrijeme("18:30 - 19:00");
		koord.setY(623);
		koordinate.add(koord);
		
		
		koord.setVrijeme("19:00 - 19:30");
		koord.setY(656);
		koordinate.add(koord);
		
		koord.setVrijeme("19:30 - 20:00");
		koord.setY(675);
		koordinate.add(koord);
	}
	
	private static void subota(RaspKoord_ koord) {
		koord.setDan("Subota");
		
		koord.setVrijeme("08:00 - 08:30");
		koord.setX(954);
		koord.setY(84);
		koordinate.add(koord);
		
		koord.setVrijeme("08:30 - 09:00");
		koord.setY(103);
		koordinate.add(koord);
		
		
		koord.setVrijeme("09:00 - 09:30");
		koord.setY(136);
		koordinate.add(koord);
		
		koord.setVrijeme("09:30 - 10:00");
		koord.setY(155);
		koordinate.add(koord);
		
		
		koord.setVrijeme("10:00 - 10:30");
		koord.setY(188);
		koordinate.add(koord);
		
		koord.setVrijeme("10:30 - 11:00");
		koord.setY(207);
		koordinate.add(koord);
		
		
		koord.setVrijeme("11:00 - 11:30");
		koord.setY(240);
		koordinate.add(koord);
		
		koord.setVrijeme("11:30 - 12:00");
		koord.setY(259);
		koordinate.add(koord);
		
		
		koord.setVrijeme("12:00 - 12:30");
		koord.setY(292);
		koordinate.add(koord);
		
		koord.setVrijeme("12:30 - 13:00");
		koord.setY(311);
		koordinate.add(koord);
		
		
		koord.setVrijeme("13:00 - 13:30");
		koord.setY(344);
		koordinate.add(koord);
		
		koord.setVrijeme("13:30 - 14:00");
		koord.setY(363);
		koordinate.add(koord);
		
		
		koord.setVrijeme("14:00 - 14:30");
		koord.setY(396);
		koordinate.add(koord);
		
		koord.setVrijeme("14:30 - 15:00");
		koord.setY(415);
		koordinate.add(koord);
		
		
		koord.setVrijeme("15:00 - 15:30");
		koord.setY(448);
		koordinate.add(koord);
		
		koord.setVrijeme("15:30 - 16:00");
		koord.setY(467);
		koordinate.add(koord);
		
		
		koord.setVrijeme("16:00 - 16:30");
		koord.setY(500);
		koordinate.add(koord);
		
		koord.setVrijeme("16:30 - 17:00");
		koord.setY(519);
		koordinate.add(koord);
		
		
		koord.setVrijeme("17:00 - 17:30");
		koord.setY(552);
		koordinate.add(koord);
		
		koord.setVrijeme("17:30 - 18:00");
		koord.setY(571);
		koordinate.add(koord);
		
		
		koord.setVrijeme("18:00 - 18:30");
		koord.setY(601);
		koordinate.add(koord);
		
		koord.setVrijeme("18:30 - 19:00");
		koord.setY(623);
		koordinate.add(koord);
		
		
		koord.setVrijeme("19:00 - 19:30");
		koord.setY(656);
		koordinate.add(koord);
		
		koord.setVrijeme("19:30 - 20:00");
		koord.setY(675);
		koordinate.add(koord);
	}
	
	private static void nedelja(RaspKoord_ koord) {
		koord.setDan("Nedelja");
		
		koord.setVrijeme("08:00 - 08:30");
		koord.setX(1116);
		koord.setY(84);
		koordinate.add(koord);
		
		koord.setVrijeme("08:30 - 09:00");
		koord.setY(103);
		koordinate.add(koord);
		
		
		koord.setVrijeme("09:00 - 09:30");
		koord.setY(136);
		koordinate.add(koord);
		
		koord.setVrijeme("09:30 - 10:00");
		koord.setY(155);
		koordinate.add(koord);
		
		
		koord.setVrijeme("10:00 - 10:30");
		koord.setY(188);
		koordinate.add(koord);
		
		koord.setVrijeme("10:30 - 11:00");
		koord.setY(207);
		koordinate.add(koord);
		
		
		koord.setVrijeme("11:00 - 11:30");
		koord.setY(240);
		koordinate.add(koord);
		
		koord.setVrijeme("11:30 - 12:00");
		koord.setY(259);
		koordinate.add(koord);
		
		
		koord.setVrijeme("12:00 - 12:30");
		koord.setY(292);
		koordinate.add(koord);
		
		koord.setVrijeme("12:30 - 13:00");
		koord.setY(311);
		koordinate.add(koord);
		
		
		koord.setVrijeme("13:00 - 13:30");
		koord.setY(344);
		koordinate.add(koord);
		
		koord.setVrijeme("13:30 - 14:00");
		koord.setY(363);
		koordinate.add(koord);
		
		
		koord.setVrijeme("14:00 - 14:30");
		koord.setY(396);
		koordinate.add(koord);
		
		koord.setVrijeme("14:30 - 15:00");
		koord.setY(415);
		koordinate.add(koord);
		
		
		koord.setVrijeme("15:00 - 15:30");
		koord.setY(448);
		koordinate.add(koord);
		
		koord.setVrijeme("15:30 - 16:00");
		koord.setY(467);
		koordinate.add(koord);
		
		
		koord.setVrijeme("16:00 - 16:30");
		koord.setY(500);
		koordinate.add(koord);
		
		koord.setVrijeme("16:30 - 17:00");
		koord.setY(519);
		koordinate.add(koord);
		
		
		koord.setVrijeme("17:00 - 17:30");
		koord.setY(552);
		koordinate.add(koord);
		
		koord.setVrijeme("17:30 - 18:00");
		koord.setY(571);
		koordinate.add(koord);
		
		
		koord.setVrijeme("18:00 - 18:30");
		koord.setY(601);
		koordinate.add(koord);
		
		koord.setVrijeme("18:30 - 19:00");
		koord.setY(623);
		koordinate.add(koord);
		
		
		koord.setVrijeme("19:00 - 19:30");
		koord.setY(656);
		koordinate.add(koord);
		
		koord.setVrijeme("19:30 - 20:00");
		koord.setY(675);
		koordinate.add(koord);
	}
	*/
}
