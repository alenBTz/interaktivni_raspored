package pomocneF;

import javax.swing.table.DefaultTableModel;

import tables.Grupa;
import tables.Izborni;
import tables.Korisnik;
import tables.Nastavnik;
import tables.NastavnikGrupa;
import tables.Predavanje;
import tables.PredavanjeUsmjerenjeSemestar;
import tables.Predmet;
import tables.PredmetGrupaTipNastave;
import tables.PredmetNastavnikTipNastave;
import tables.PredmetPredavanjeTipNastave;
import tables.PredmetUsmjerenjeIzborni;
import tables.Rezervacija;
import tables.Sala;
import tables.Semestar;
import tables.Student;
import tables.TipNastave;
import tables.Usmjerenje;
import tables.Zgrada;

public class PomocneF {
	
	public static void resetTable(DefaultTableModel model) {
		System.out.println("resetuj tabelu");
		model.setRowCount(0);
		clearListe();

	}



	public static void clearListe() {
		Korisnik.korisnikLista.clear();
		Grupa.grupaLista.clear();
		Izborni.izborniLista.clear();
		Nastavnik.nastavnikLista.clear();
		NastavnikGrupa.nastavnikGrupaLista.clear();
		Predavanje.predavanjeLista.clear();
		Predmet.predmetLista.clear();
		PredmetGrupaTipNastave.predmetGrupaTipNastaveLista.clear();
		PredmetNastavnikTipNastave.predmetNastavnikTipNastaveLista.clear();
		PredmetPredavanjeTipNastave.predmetPredavanjeTipNastaveLista.clear();
		PredmetUsmjerenjeIzborni.predmetUsmjerenjeIzborniLista.clear();
		Rezervacija.rezervacijaLista.clear();
		Sala.salaLista.clear();
		Semestar.semestarLista.clear();
		Student.studentLista.clear();
		TipNastave.tipNastaveLista.clear();
		Usmjerenje.usmjerenjeLista.clear();
		Zgrada.zgradaLista.clear();
		PredavanjeUsmjerenjeSemestar.predavanjeUsmjerenjeSemestarLista.clear();
	}
}
