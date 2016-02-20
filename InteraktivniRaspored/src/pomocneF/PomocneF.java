package pomocneF;

import javax.swing.table.DefaultTableModel;

import tables.Grupa;
import tables.Izborni;
import tables.Nastavnik;
import tables.NastavnikGrupa;
import tables.Predavanje;
import tables.Predmet;
import tables.PredmetGrupaTipNastave;
import tables.PredmetNastavnikTipNastave;
import tables.PredmetUsmjerenjeIzborni;
import tables.Sala;
import tables.Semestar;
import tables.Student;
import tables.TipNastave;
import tables.Usmjerenje;
import tables.Zgrada;

public class PomocneF {
	
	public static void resetTable(DefaultTableModel model) {

		model.setRowCount(0);
		clearListe();

	}



	public static void clearListe() {
		Grupa.grupaLista.clear();
		Izborni.izborniLista.clear();
		Nastavnik.nastavnikLista.clear();
		NastavnikGrupa.nastavnikGrupaLista.clear();
		Predavanje.predavanjeLista.clear();
		Predmet.predmetLista.clear();
		PredmetGrupaTipNastave.predmetGrupaTipNastaveLista.clear();
		PredmetNastavnikTipNastave.predmetNastavnikTipNastaveLista.clear();
		PredmetUsmjerenjeIzborni.predmetUsmjerenjeIzborniLista.clear();
		//Rezervacija.
		Sala.salaLista.clear();
		Semestar.semestarLista.clear();
		Student.studentLista.clear();
		TipNastave.tipNastaveLista.clear();
		Usmjerenje.usmjerenjeLista.clear();
		Zgrada.zgradaLista.clear();
	}
}
