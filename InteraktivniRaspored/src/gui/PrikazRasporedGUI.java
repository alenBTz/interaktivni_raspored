package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import dataBase.DBExecutePredavanje;
import dataBase.DBExecutePredavanjeUsmjerenjeSemestar;
import dataBase.DBExecutePredmet;
import dataBase.DBExecutePredmetPredavanjeTipNastave;
import dataBase.DBExecuteSemestar;
import dataBase.DBExecuteTipNastave;
import dataBase.DBExecuteUsmjerenje;
import modeli.PredavanjeUsmjerenjeSemestar_;
import modeli.Predavanje_;
import modeli.PredmetPredavanjeTipNastave_;
import modeli.Predmet_;
import modeli.Semestar_;
import modeli.TipNastave_;
import modeli.Usmjerenje_;
import pomocneF.RaspKoord_;
import pomocneF.RasporedKoordinate;
import tables.Predavanje;
import tables.PredavanjeUsmjerenjeSemestar;
import tables.Predmet;
import tables.PredmetPredavanjeTipNastave;
import tables.Semestar;
import tables.TipNastave;
import tables.Usmjerenje;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class PrikazRasporedGUI {

	private JFrame framePrikazRaspored;
	private JFrame framePrikazRaspore;

	/**
	 * Launch the application.
	 */
	public static void startPrikazRasporedGUI(ArrayList<PredavanjeUsmjerenjeSemestar_> predavanjaZaPrikazati,ArrayList<Predavanje_> svaPredavanja) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazRasporedGUI window = new PrikazRasporedGUI(predavanjaZaPrikazati,svaPredavanja);
					window.framePrikazRaspored.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazRasporedGUI(ArrayList<PredavanjeUsmjerenjeSemestar_> predavanjaZaPrikazati,ArrayList<Predavanje_> svaPredavanja) {
		initialize(predavanjaZaPrikazati,svaPredavanja);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<PredavanjeUsmjerenjeSemestar_> predavanjaZaPrikazati,ArrayList<Predavanje_> svaPredavanja) {
		//komentar
		framePrikazRaspored = new JFrame();
		framePrikazRaspored.setBounds(100, 100, 1290, 735);
		framePrikazRaspored.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		framePrikazRaspored.getContentPane().setLayout(null);
		
		Border borderBlack = BorderFactory.createLineBorder(Color.BLACK, 2);
		int[] array = {84,136,188,240,292,344,396,448,500,552};
		
		for(int i=0;i<predavanjaZaPrikazati.size();i++){
			for(int j=0;j<svaPredavanja.size();j++){
				if(predavanjaZaPrikazati.get(i).getSifPredavanje() == svaPredavanja.get(j).getSifPredavanje()){
					switch (svaPredavanja.get(j).getDanPredavanje()){
					case "Ponedeljak":
						System.out.println("ponedeljak");
						int pocetak = Integer.parseInt(svaPredavanja.get(j).getPocetakPredavanje().toString().substring(0,2)) - 8;
						int kraj = Integer.parseInt(svaPredavanja.get(j).getKrajPredavanje().toString().substring(0,2)) - 8;
						//System.out.println(svaPredavanja.get(j).getPocetakPredavanje());
						//System.out.println("pocetak"+pocetak);
						//System.out.println("kraj"+kraj);
						
						JLabel lblPonedeljakk = new JLabel("Ime predmeta");
						lblPonedeljakk.setBackground(Color.RED);
						lblPonedeljakk.setOpaque(true);
						lblPonedeljakk.setHorizontalAlignment(SwingConstants.CENTER);
						lblPonedeljakk.setBounds(144, array[pocetak], 150, (40*(kraj-pocetak))+12*(kraj-pocetak-1));
						lblPonedeljakk.setBorder(borderBlack);
						framePrikazRaspored.getContentPane().add(lblPonedeljakk);
						
						break;
					case "Utorak":
						System.out.println("utorak");
						break;
					case "Srijeda":
						System.out.println("srijeda");
						break;
					case "Cetvrtak":
						System.out.println("cevtrtak");
						break;
					case "Petak":
						System.out.println("petak");
						break;
					}
				}
			}
		}

		
		JLabel lblVrijeme = new JLabel("Vrijeme");
		lblVrijeme.setBackground(Color.LIGHT_GRAY);
		lblVrijeme.setOpaque(true);
		lblVrijeme.setHorizontalAlignment(SwingConstants.CENTER);
		lblVrijeme.setBounds(12, 12, 120, 60);
		lblVrijeme.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lblVrijeme);
			
		JLabel lblPonedeljak = new JLabel("Ponedeljak");
		lblPonedeljak.setBackground(Color.LIGHT_GRAY);
		lblPonedeljak.setOpaque(true);
		lblPonedeljak.setHorizontalAlignment(SwingConstants.CENTER);
		lblPonedeljak.setBounds(144, 12, 150, 60);
		lblPonedeljak.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lblPonedeljak);
		
		JLabel lblUtorak = new JLabel("Utorak");
		lblUtorak.setBackground(Color.LIGHT_GRAY);
		lblUtorak.setOpaque(true);
		lblUtorak.setHorizontalAlignment(SwingConstants.CENTER);
		lblUtorak.setBounds(306, 12, 150, 60);
		lblUtorak.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lblUtorak);
		
		JLabel lblSrijeda = new JLabel("Srijeda");
		lblSrijeda.setBackground(Color.LIGHT_GRAY);
		lblSrijeda.setOpaque(true);
		lblSrijeda.setHorizontalAlignment(SwingConstants.CENTER);
		lblSrijeda.setBounds(468, 12, 150, 60);
		lblSrijeda.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lblSrijeda);
		
		JLabel lblCetvrtak = new JLabel("Cetvrtak");
		lblCetvrtak.setBackground(Color.LIGHT_GRAY);
		lblCetvrtak.setOpaque(true);
		lblCetvrtak.setHorizontalAlignment(SwingConstants.CENTER);
		lblCetvrtak.setBounds(630, 12, 150, 60);
		lblCetvrtak.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lblCetvrtak);
		
		JLabel lblPetak = new JLabel("Petak");
		lblPetak.setBackground(Color.LIGHT_GRAY);
		lblPetak.setOpaque(true);
		lblPetak.setHorizontalAlignment(SwingConstants.CENTER);
		lblPetak.setBounds(792, 12, 150, 60);
		lblPetak.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lblPetak);
		
		JLabel lblSubota = new JLabel("Subota");
		lblSubota.setBackground(Color.LIGHT_GRAY);
		lblSubota.setOpaque(true);
		lblSubota.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubota.setBounds(954, 12, 150, 60);
		lblSubota.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lblSubota);
		
		JLabel lblNedelja = new JLabel("Nedelja");
		lblNedelja.setBackground(Color.LIGHT_GRAY);
		lblNedelja.setOpaque(true);
		lblNedelja.setHorizontalAlignment(SwingConstants.CENTER);
		lblNedelja.setBounds(1116, 12, 150, 60);
		lblNedelja.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lblNedelja);
		
		JLabel lbl_08_09 = new JLabel("08 - 09");
		lbl_08_09.setBackground(Color.LIGHT_GRAY);
		lbl_08_09.setOpaque(true);
		lbl_08_09.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_08_09.setBounds(12, 84, 120, 40);
		lbl_08_09.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lbl_08_09);
		
		JLabel lbl_09_10 = new JLabel("09 - 10");
		lbl_09_10.setBackground(Color.LIGHT_GRAY);
		lbl_09_10.setOpaque(true);
		lbl_09_10.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_09_10.setBounds(12, 136, 120, 40);
		lbl_09_10.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lbl_09_10);
		
		JLabel lbl_10_11 = new JLabel("10 - 11");
		lbl_10_11.setBackground(Color.LIGHT_GRAY);
		lbl_10_11.setOpaque(true);
		lbl_10_11.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_10_11.setBounds(12, 188, 120, 40);
		lbl_10_11.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lbl_10_11);
		
		JLabel lbl_11_12 = new JLabel("11 - 12");
		lbl_11_12.setBackground(Color.LIGHT_GRAY);
		lbl_11_12.setOpaque(true);
		lbl_11_12.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_11_12.setBounds(12, 240, 120, 40);
		lbl_11_12.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lbl_11_12);
		
		JLabel lbl_12_13 = new JLabel("12 - 13");
		lbl_12_13.setBackground(Color.LIGHT_GRAY);
		lbl_12_13.setOpaque(true);
		lbl_12_13.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_12_13.setBounds(12, 292, 120, 40);
		lbl_12_13.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lbl_12_13);
		
		JLabel lbl_13_14 = new JLabel("13 - 14");
		lbl_13_14.setBackground(Color.LIGHT_GRAY);
		lbl_13_14.setOpaque(true);
		lbl_13_14.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_13_14.setBounds(12, 344, 120, 40);
		lbl_13_14.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lbl_13_14);
		
		JLabel lbl_14_15 = new JLabel("14 - 15");
		lbl_14_15.setBackground(Color.LIGHT_GRAY);
		lbl_14_15.setOpaque(true);
		lbl_14_15.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_14_15.setBounds(12, 396, 120, 40);
		lbl_14_15.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lbl_14_15);
		
		JLabel lbl_15_16 = new JLabel("15 - 16");
		lbl_15_16.setBackground(Color.LIGHT_GRAY);
		lbl_15_16.setOpaque(true);
		lbl_15_16.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_15_16.setBounds(12, 448, 120, 40);
		lbl_15_16.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lbl_15_16);
		
		JLabel lbl_16_17 = new JLabel("16 - 17");
		lbl_16_17.setBackground(Color.LIGHT_GRAY);
		lbl_16_17.setOpaque(true);
		lbl_16_17.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_16_17.setBounds(12, 500, 120, 40);
		lbl_16_17.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lbl_16_17);
		
		JLabel lbl_17_18 = new JLabel("17 - 18");
		lbl_17_18.setBackground(Color.LIGHT_GRAY);
		lbl_17_18.setOpaque(true);
		lbl_17_18.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_17_18.setBounds(12, 552, 120, 40);
		lbl_17_18.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lbl_17_18);
		
		JLabel lbl_18_19 = new JLabel("18 - 19");
		lbl_18_19.setBackground(Color.LIGHT_GRAY);
		lbl_18_19.setOpaque(true);
		lbl_18_19.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_18_19.setBounds(12, 604, 120, 40);
		lbl_18_19.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lbl_18_19);
		
		JLabel lbl_19_20 = new JLabel("19 - 20");
		lbl_19_20.setBackground(Color.LIGHT_GRAY);
		lbl_19_20.setOpaque(true);
		lbl_19_20.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_19_20.setBounds(12, 656, 120, 40);
		lbl_19_20.setBorder(borderBlack);
		framePrikazRaspored.getContentPane().add(lbl_19_20);
	
		
		/**
		 * inicijalizacija koordinata za raspored. Koordinate sarze koordinate u rasporedu za svaki dan, i onda po danu za vremena, i to 8:00,
		 * 8:30, 9:00, 9:30 itd
		 */
		RasporedKoordinate.setKoordinate();
		
		/*
		 * Nakon inicijalizacije koordinata,mozemo da iscrtavamo raspored prema kriterijumima
		 */
		
		/**
		 * Ponedeljak: Dohvatimo vektor ponedeljak, u koji su upisani svi casovi vezani za taj dan
		 */
		
		
		////////////////////////////////////////////////////////////////////////
		/**
		 * Test:Dohvatimo predavanja po usmjerenju i semestru i punimo u vektor ponRaspored
		 * pretospotavka da je odabran prvi semestar i prvo usmjerenje
		 */
		int sifSemestar = 1;
		int sifUsmjerenje = 1;
		
		/**
		 * trebamo dohvatiti tabele "PredmetNastavnikTipNastave", "semestar", "usmjerenje", "perdavanje" i "predmet".
		 */
		try {
			DBExecutePredmetPredavanjeTipNastave.getPredmetPredavanjeTipNastave();
			DBExecutePredavanje.getPredavanja();
			DBExecuteUsmjerenje.getUsmjerenja();
			DBExecutePredmet.getPredmeti();
			DBExecuteSemestar.getSemestri();
			DBExecutePredavanjeUsmjerenjeSemestar.getPredavanjeUsmjerenjeSemestar();
			DBExecuteTipNastave.getTipNastave();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<PredmetPredavanjeTipNastave_> pptnX = new ArrayList<>();
		ArrayList<Predavanje_> predavanja = new ArrayList<>();
		ArrayList<Usmjerenje_> usmjerenja = new ArrayList<>();
		ArrayList<Predmet_> predmeti = new ArrayList<>();
		ArrayList<Semestar_> semestri = new ArrayList<>();
		ArrayList<PredavanjeUsmjerenjeSemestar_> puX = new ArrayList<>();
		ArrayList<TipNastave_> tnX = new ArrayList<>();
		
		pptnX = PredmetPredavanjeTipNastave.predmetPredavanjeTipNastaveLista;
		predavanja = Predavanje.predavanjeLista;
		usmjerenja = Usmjerenje.usmjerenjeLista;
		predmeti = Predmet.predmetLista;
		semestri = Semestar.semestarLista;
		puX = PredavanjeUsmjerenjeSemestar.predavanjeUsmjerenjeSemestarLista;
		tnX = TipNastave.tipNastaveLista;
		/**
		 * Sada na osnovu prosljedjenih elemenata vadimo iz ovih vektora ono sto nam treba
		 * i smjestamo u varijable koje cemo kreirati ispod.
		 */
		PredmetPredavanjeTipNastave_ pptn = new PredmetPredavanjeTipNastave_();
		Predavanje_ predavanje = new Predavanje_();
		Usmjerenje_ usmjerenje = new Usmjerenje_();
		Predmet_ predmet = new Predmet_();
		Semestar_ semestar = new Semestar_();
		PredavanjeUsmjerenjeSemestar_ pu = new PredavanjeUsmjerenjeSemestar_();
		TipNastave_ tn = new TipNastave_();
		/**
		 * pošto simuliramo da su dohvaceni samo usmjerenje i semestar, radimo sa tim podacima.
		 */
		usmjerenje = usmjerenja.get(sifUsmjerenje);
		semestar = semestri.get(sifSemestar);
		/**
		 * iz vektora predmeti dohvatamo samo one predmete koji pripadaju odabranom semestru, 
		 * i upisujemo u premetiPom
		 */
		ArrayList<Predmet_> predmetiPom = new ArrayList<>();
		for (int i = 0; i < predmeti.size(); i++) {
			if(predmeti.get(i).getSifSemestar() == sifSemestar){
				predmetiPom.add(predmeti.get(i));
			}
		}
		/**
		 * U vektor puXPom upisujemo samo ona predavanja koja se nalaze u odgovarajucem usmjerenju
		 */
		ArrayList<PredavanjeUsmjerenjeSemestar_> puXPom = new ArrayList<>();
		for (int i = 0; i < puX.size(); i++) {
			if(puX.get(i).getSifUsmjerenje() == sifUsmjerenje){
				puXPom.add(puX.get(i));
			}
		}
		
		/**
		 * Sada iz vektora Predavanja filtriramo pedavanja vezana za to usmjerenje. Tj ako je sifra predavanja
		 * jednaka sifri predavanj iz prethodnog koraka, onda je to predavanje koje nas zanima
		 */
		ArrayList<Predavanje_> predavanjaPom = new ArrayList<>();
		ArrayList<PredmetPredavanjeTipNastave_> pptnXPom = new ArrayList<>();
		for (int i = 0; i < predavanja.size(); i++) {
			/**
			 * Treba za svaku sifPredavanje iz "puXPom", ubaciti predavanje sa tom sifrom u PredavanjePom
			 */
			for (int j = 0; j < puXPom.size(); j++) {
				
				if(puXPom.get(j).getSifPredavanje() == predavanja.get(i).getSifPredavanje()){
					predavanjaPom.add(predavanja.get(i));
					pptnXPom.add(pptnX.get(i));
				}
			}	
		}
		
		/**
		 * Sada uz pomoc vektora PredmetPredavanjeTipNastave i Predavanja ispisujemo raspored za ponedeljak
		 */
		/**
		 * Imat cemo onoliko Jlabela koliko imamo elemenata u Predavanja.
		 * Kreiramo vektor Jlabela koji ce se ispisavit
		 */
		
		ArrayList<JLabel> labelePon = new ArrayList<>();
		
		for (int i = 0; i < predavanjaPom.size(); i++) {
			/**
			 * Iz tabele PredmetPredavanjeTipNastave
			 */
			PredmetPredavanjeTipNastave_ pptnPom = new PredmetPredavanjeTipNastave_();
			pptnPom = pptnXPom.get(i);
			
			/**
			 * dohvatimo sif predmet, i siftipnastave. na osnovu njih mozemo dohvatiti naziv predmeta i koji je tip nastave
			 * iz odgovarajucih tabela
			 */
			Predmet_ predmetPom = new Predmet_();
			for (int j = 0; j < predmeti.size(); j++) {
				if(pptnPom.getSifPredmet() == predmeti.get(j).getSifPredmet()){
					predmetPom = predmeti.get(j);
				}
			}
			TipNastave_ tnPom = new TipNastave_();
			for (int j = 0; j < tnX.size(); j++) {
				if(pptnPom.getSifTipNastave() == tnX.get(j).getSifTipNastave()){
					tnPom = tnX.get(j);
					System.out.println(tnPom.getSifTipNastave());
				}
			}
			
			String nazPredmet = null;
			String nazTipNast = null;
			String nazZgrada = null;
			String nazUcionica = null;
			nazPredmet = predmetPom.getNazPredmet();
			nazTipNast = tnPom.getKratTipNastav();
			nazZgrada = "zgrada";
			nazUcionica = "sala";
			
			/**
			 * kreiranje Jlabele
			 */
			//JLabel cas = new JLabel(nazPredmet + "\n" + nazTipNast + "\n" + nazZgrada + "\n" + nazUcionica);
			JLabel cas = new JLabel();
			/**
			 * postavljanje teksta koji se ispisuje
			 */
			cas.setText("<html>" + nazPredmet + "<br/>" + nazTipNast + "<br/>" + nazZgrada + ": " + nazUcionica + "</html>");
			/**
			 * Pozicioniranje: x i y koordinate, h je visina (koja varira od toga koliko dugo traju predavanja). Sirina je konst.
			 * U zavisnosti od toga kad pocinju predavanja i kada zavrsavaju, i u kojem su danu, postavljaju se vrijednosti 
			 * koordinata x, y, h.
			 * x koordinata se mijenja u zavisnosti od toga koji je dan u pitanju
			 * y koordinata se mijenja u zavisnosti od toga kada cas pocinje
			 * h koordinata se mijenja u zavisnosti koliko dugo cas traje.
			 */
			RaspKoord_ rk = new RaspKoord_();
			rk = RasporedKoordinate.getKoordinate(predavanjaPom.get(i).getDanPredavanje(), predavanjaPom.get(i).getPocetakPredavanje(), predavanjaPom.get(i).getKrajPredavanje());
			
			cas.setBounds(rk.getX(), rk.getY(), 150, rk.getHeight());
			
			/**
			 * Promjena boje u zavisnosti od tipa nastave
			 */
			if(nazTipNast.equals("pred")){
				cas.setBackground(Color.RED);
				System.out.println("crvena");
			}
			if(nazTipNast.equals("aud")){
				cas.setBackground(Color.BLUE);
				System.out.println("plava");

			}
			if(nazTipNast.equals("lab")){
				cas.setBackground(Color.GREEN);
				System.out.println("zelena");

			}
			cas.setOpaque(true);
			
			/**
			 * Dodajemo sada kreirani JLabel u arraylist labelePonedeljak
			 */
			
			labelePon.add(cas);
		}
		
		/**
		 * Prikaz ponedeljka
		 */
		
		
		for (int j = 0; j < labelePon.size(); j++) {
			JLabel jLabel = labelePon.get(j);
			/**
			 * Centriranje teksta
			 */
			jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
			
			jLabel.setBorder(borderBlack);
			
			framePrikazRaspored.getContentPane().add(jLabel);

		}

		
		
		////////////////////////////////////////////////////////////////////////

	}
}
