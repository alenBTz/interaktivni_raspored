package gui;

import java.awt.Color;
import org.jdesktop.swingx.JXDatePicker;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

import modeli.PredavanjeUsmjerenjeSemestar_;
import modeli.Predavanje_;
import modeli.PredmetPredavanjeTipNastave_;
import modeli.Predmet_;
import modeli.Rezervacija_;
import modeli.Sala_;
import modeli.Semestar_;
import modeli.TipNastave_;
import modeli.Usmjerenje_;
import pomocneF.PomocneF;
import tables.Predavanje;
import tables.PredavanjeUsmjerenjeSemestar;
import tables.Predmet;
import tables.Sala;
import tables.Semestar;
import tables.TipNastave;
import tables.Usmjerenje;

import javax.swing.JLabel;

import dataBase.DBExecuteIzborni;
import dataBase.DBExecutePredavanjeUsmjerenjeSemestar;
import dataBase.DBExecutePredavanje;
import dataBase.DBExecutePredmet;
import dataBase.DBExecutePredmetPredavanjeTipNastave;
import dataBase.DBExecutePredmetUsmjerenjeIzborni;
import dataBase.DBExecuteRezervacija;
import dataBase.DBExecuteSala;
import dataBase.DBExecuteSemestar;
import dataBase.DBExecuteTipNastave;
import dataBase.DBExecuteUsmjerenje;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
//import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class RasporedGUI {

	private JFrame frameRaspored;
	
	/**
	 * Potreban nam je vektor rasporeda, za svaki dan u sedmici. Vektor ce sadrzavati tipove 'Predavaje_"
	 */
	//komentar 
	public static ArrayList<PredmetPredavanjeTipNastave_> ponedeljak = new ArrayList<PredmetPredavanjeTipNastave_>();
	public static ArrayList<PredmetPredavanjeTipNastave_> utorak = new ArrayList<PredmetPredavanjeTipNastave_>();
	public static ArrayList<PredmetPredavanjeTipNastave_> srijeda = new ArrayList<PredmetPredavanjeTipNastave_>();
	public static ArrayList<PredmetPredavanjeTipNastave_> cetvrtak = new ArrayList<PredmetPredavanjeTipNastave_>();
	public static ArrayList<PredmetPredavanjeTipNastave_> petak = new ArrayList<PredmetPredavanjeTipNastave_>();
	public static ArrayList<PredmetPredavanjeTipNastave_> subota = new ArrayList<PredmetPredavanjeTipNastave_>();
	public static ArrayList<PredmetPredavanjeTipNastave_> nedelja = new ArrayList<PredmetPredavanjeTipNastave_>();
	
	ArrayList<String> dani = new ArrayList<String>();
	ArrayList<Integer> sati = new ArrayList<Integer>();
	ArrayList<Integer> minute = new ArrayList<Integer>();
	
	JList<String> listUsmjerenja = new JList<String>();
	
	//JComboBox<String> comboBoxTipNastave = new JComboBox<String>();
	JComboBox<String> comboBoxUsmjerenje = new JComboBox<String>();
	JComboBox<String> comboBoxSemestar = new JComboBox<String>();
	JComboBox<String> comboBoxDan = new JComboBox<String>();
	JComboBox<String> comboBoxPredmet = new JComboBox<String>();
	JComboBox<String> comboBoxTipNastavee = new JComboBox<String>();
	JComboBox<String> comboBoxSala = new JComboBox<String>();
	JComboBox<String> comboBoxPocetakSat = new JComboBox<String>();
	JComboBox<String> comboBoxPocetakMin = new JComboBox<String>();
	JComboBox<String> comboBoxKrajSat = new JComboBox<String>();
	JComboBox<String> comboBoxKrajMin = new JComboBox<String>();
	
	
	/**
	 * Launch the application.
	 */
	public static void startRasporedGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RasporedGUI window = new RasporedGUI();
					window.frameRaspored.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RasporedGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameRaspored = new JFrame();
		frameRaspored.setBounds(100, 50, 883, 508);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameRaspored.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameRaspored.getContentPane().setLayout(null);
		
		JButton btnPregledRasporeda = new JButton("Pregled rasporeda");
		btnPregledRasporeda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PrikazRasporedFilteriGUI.startPrikazRasporedFilteriGUI();
			}
		});
		btnPregledRasporeda.setBounds(176, 12, 170, 25);
		frameRaspored.getContentPane().add(btnPregledRasporeda);
		
		JLabel lblUsmjerenje = new JLabel("Usmjerenje:");
		lblUsmjerenje.setBounds(500, 90, 85, 15);
		frameRaspored.getContentPane().add(lblUsmjerenje);
		
		try {
			createListUsmjerenja();
		} catch (SQLException e9) {
			// TODO Auto-generated catch block
			e9.printStackTrace();
		}
		
		

		JLabel lblSemestar = new JLabel("Semestar:");
		lblSemestar.setBounds(85, 90, 73, 15);
		frameRaspored.getContentPane().add(lblSemestar);
		
		/*JLabel lblTipPredavanja = new JLabel("Tip predavanja:");
		lblTipPredavanja.setBounds(65, 54, 105, 15);
		frameRaspored.getContentPane().add(lblTipPredavanja);
		
		comboBoxTipNastave.setBounds(176, 54, 300, 24);
		frameRaspored.getContentPane().add(comboBoxTipNastave);
		try {
			fillcomboBoxTipUsmjerenja();
		} catch (SQLException e0){
			e0.printStackTrace();
		}*/
		
		comboBoxSemestar.setBounds(176, 85, 300, 24);
		frameRaspored.getContentPane().add(comboBoxSemestar);
		try {
			fillcomboBoxSemestar();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblDan = new JLabel("Dan:");
		lblDan.setBounds(125, 126, 33, 15);
		frameRaspored.getContentPane().add(lblDan);
		
		comboBoxDan.setBounds(176, 121, 300, 24);
		frameRaspored.getContentPane().add(comboBoxDan);
		try {
			fillcomboBoxDan();
		} catch (SQLException e8) {
			// TODO Auto-generated catch block
			e8.printStackTrace();
		}
		
		JLabel lblPredmet = new JLabel("Predmet:");
		lblPredmet.setBounds(93, 162, 65, 15);
		frameRaspored.getContentPane().add(lblPredmet);
		
		comboBoxPredmet.setBounds(176, 157, 300, 24);
		frameRaspored.getContentPane().add(comboBoxPredmet);
		try {
			fillcomboBoxPredmet();
		} catch (SQLException e7) {
			// TODO Auto-generated catch block
			e7.printStackTrace();
		}
		
		JLabel lblTipNastave = new JLabel("Tip nastave:");
		lblTipNastave.setBounds(71, 198, 87, 15);
		frameRaspored.getContentPane().add(lblTipNastave);
		
		comboBoxTipNastavee.setBounds(176, 193, 300, 24);
		frameRaspored.getContentPane().add(comboBoxTipNastavee);
		try {
			fillcomboBoxTipNastavee();
		} catch (SQLException e6) {
			// TODO Auto-generated catch block
			e6.printStackTrace();
		}
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setBounds(122, 234, 36, 15);
		frameRaspored.getContentPane().add(lblSala);
		
		comboBoxSala.setBounds(176, 229, 300, 24);
		frameRaspored.getContentPane().add(comboBoxSala);
		try {
			fillcomboBoxSala();
		} catch (SQLException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		
		JLabel lblPocetakPredavanja = new JLabel("Pocetak predavanja:");
		lblPocetakPredavanja.setBounds(12, 270, 146, 15);
		frameRaspored.getContentPane().add(lblPocetakPredavanja);
		
		JLabel labelPocetakSat = new JLabel("Sat:");
		labelPocetakSat.setBounds(176, 270, 29, 15);
		frameRaspored.getContentPane().add(labelPocetakSat);
		
		comboBoxPocetakSat.setBounds(223, 265, 98, 24);
		frameRaspored.getContentPane().add(comboBoxPocetakSat);
		try {
			fillcomboBoxPocetakSat();
		} catch (SQLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		JLabel lblPocetakMin = new JLabel("Min:");
		lblPocetakMin.setBounds(330, 270, 30, 15);
		frameRaspored.getContentPane().add(lblPocetakMin);
		
		comboBoxPocetakMin.setBounds(378, 265, 98, 24);
		frameRaspored.getContentPane().add(comboBoxPocetakMin);
		try {
			fillcomboBoxPocetakMin();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		JLabel lblKrajPredavanja = new JLabel("Kraj predavanja:");
		lblKrajPredavanja.setBounds(41, 306, 117, 15);
		frameRaspored.getContentPane().add(lblKrajPredavanja);
		
		JLabel label = new JLabel("Sat:");
		label.setBounds(176, 306, 29, 15);
		frameRaspored.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Min:");
		label_1.setBounds(330, 306, 30, 15);
		frameRaspored.getContentPane().add(label_1);
		
		comboBoxKrajSat.setBounds(223, 301, 98, 24);
		frameRaspored.getContentPane().add(comboBoxKrajSat);
		try {
			fillcomboBoxKrajSat();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		comboBoxKrajMin.setBounds(378, 301, 98, 24);
		frameRaspored.getContentPane().add(comboBoxKrajMin);
		try {
			fillcomboBoxKrajMin();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel labelDatum = new JLabel("Datum predavanja:");
		labelDatum.setBounds(31, 346, 147, 15);
		frameRaspored.getContentPane().add(labelDatum);
		
		JLabel labelDatumPocetak = new JLabel("Od :");
		labelDatumPocetak.setBounds(176, 346, 29, 15);
		frameRaspored.getContentPane().add(labelDatumPocetak);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		java.util.GregorianCalendar cal = (GregorianCalendar) java.util.GregorianCalendar.getInstance();
		java.util.Date utilDate = cal.getTime();
		Date todayDate = new Date(utilDate.getTime());
		
		JXDatePicker pickerOd = new JXDatePicker();
		
		
		pickerOd.setFormats(dateFormat);
		pickerOd.getMonthView().setLowerBound(todayDate); 
		frameRaspored.getContentPane().add(pickerOd);
		pickerOd.setBounds(223, 346, 159, 25);	
		
		JLabel labelDatumKraj = new JLabel("Do :");
		labelDatumKraj.setBounds(176, 386, 29, 15);
		frameRaspored.getContentPane().add(labelDatumKraj);
		
		JXDatePicker pickerDo = new JXDatePicker();
		pickerDo.setFormats(dateFormat);
		pickerDo.getMonthView().setLowerBound(pickerOd.getDate());
		frameRaspored.getContentPane().add(pickerDo);
		pickerDo.setBounds(223, 386, 159, 25);	
		
		pickerOd.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				pickerDo.getMonthView().setLowerBound(pickerOd.getDate());
			}
		});
		
		/*UtilDateModel model = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Danas");
		p.put("text.month", "Mjesec");
		p.put("text.year", "Godina");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		// Don't know about the formatter, but there it is...
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(223, 346, 159, 35);
		
		frameRaspored.add(datePicker);
		
		JLabel labelDatumKraj = new JLabel("Do :");
		labelDatumKraj.setBounds(176, 386, 29, 15);
		frameRaspored.getContentPane().add(labelDatumKraj);
		
		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
		// Don't know about the formatter, but there it is...
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		datePicker2.setBounds(223, 386, 159, 35);
		*/
		
		/*comboBoxKrajSat.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	comboBoxPocetakSat.removeAllItems();
		    	System.out.println("comboBoxKrajSat.getSelectedItem():" + comboBoxKrajSat.getSelectedItem());
		    	System.out.println("(int)comboBoxKrajSat.getSelectedItem():" + (int)comboBoxKrajSat.getSelectedItem());
		    	for (int i = 8; i < (int)comboBoxKrajSat.getSelectedItem(); i++)
		    	{
					String pom = String.valueOf(i);
					comboBoxKrajSat.addItem(pom);
				}
		    }
		});*/
		
		comboBoxPocetakSat.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	comboBoxKrajSat.removeAllItems();
		    	
		    	for (int i = Integer.parseInt((String)comboBoxPocetakSat.getSelectedItem()); i < 20; i++)
		    	{
					String pom = String.valueOf(i);
					comboBoxKrajSat.addItem(pom);
				}
		    }
		});
		
		JButton btnPotvrdiUnos = new JButton("Potvrdi unos");
		btnPotvrdiUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PomocneF.clearListe();
				/**
				 * Konektujemo se na sve Baze koje nam trebaju
				 */
				
				//Rezervacija
				
		        int whichDay = (comboBoxDan.getSelectedIndex() == 6) ? 0 : comboBoxDan.getSelectedIndex() + 1;
		        ArrayList <java.util.Date> allDays = new  ArrayList <java.util.Date>();
		        
		        Calendar startDate = Calendar.getInstance();
		        if(pickerOd.getDate() != null)
		        {
		        	startDate.setTime(pickerOd.getDate());
		        }
		        Calendar endDate = Calendar.getInstance();
		        if(pickerDo.getDate() != null)
		        {
		        	endDate.setTime(pickerDo.getDate());
		        }
		        //get dates of a specific day_of_week between two dates
		        while (startDate.before(endDate)) 
		        {
		            if (startDate.get(Calendar.DAY_OF_WEEK) == whichDay) 
		            {
		                allDays.add(startDate.getTime());
				        startDate.add(Calendar.DATE, 7);
		            } 
		            else 
		            {
				        startDate.add(Calendar.DATE, 1);
		            }
		        }
		        
		        for (int i=0; i<allDays.size(); i++)
		        {
		        	Rezervacija_ rezervacija = new Rezervacija_();
		        	 java.sql.Date sqlDate = new java.sql.Date(allDays.get(i).getTime());//utilDate.getTime());
		        	rezervacija.setDatumRezervacija(sqlDate);
		        	
		        	//int pom = comboBoxPocetakSat.getSelectedIndex();
					int pocetakSat = Integer.parseInt((String)comboBoxPocetakSat.getSelectedItem());//sati.get(pom);
					
					int pom = comboBoxPocetakMin.getSelectedIndex();
					int pocetakMin = minute.get(pom);
					
					//pom = comboBoxKrajSat.getSelectedIndex();
					int krajSat = Integer.parseInt((String)comboBoxKrajSat.getSelectedItem());//sati.get(pom);
					
					pom = comboBoxKrajMin.getSelectedIndex();
					int krajMin = minute.get(pom);
					
					String PocetakSatString = String.valueOf(pocetakSat);
					String PocetakMinString = String.valueOf(pocetakMin);
					String KrajSatString = String.valueOf(krajSat);
					String KrajMinString = String.valueOf(pocetakMin);
					SimpleDateFormat dt = new SimpleDateFormat("hh:mm");
					
					Date parsedPocetak = new Date();
					Date parsedKraj = new Date();
					try {
						parsedPocetak = dt.parse(PocetakSatString+":"+PocetakMinString);
						parsedKraj = dt.parse(KrajSatString+":"+KrajMinString);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Time timePocetak = new Time(parsedPocetak.getTime());
					Time timeKraj = new Time(parsedKraj.getTime());
					
		        	rezervacija.setSatRezervacije(timePocetak);
		        	rezervacija.setSatRezervacijeKraj(timeKraj);
		        	int sifNastavnik = LoginGUI.sessionSifNastavnik;
		        	rezervacija.setSifNastavnik(sifNastavnik);
		        	
		        	try {
						rezervacija.setSifPredmet(DBExecutePredmet.getPredmetByName(comboBoxPredmet.getSelectedItem().toString()).getSifPredmet());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	try {
						rezervacija.setSifSala(DBExecuteSala.getSalaByName(comboBoxSala.getSelectedItem().toString()).getSifSala());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	rezervacija.setTipRezervacije(comboBoxTipNastavee.getSelectedItem().toString());
					try 
					{
						if(pickerOd.getDate() != null && pickerDo.getDate() != null)
						{
							DBExecuteRezervacija.insertRezervacija(rezervacija);
						}
						else
						{
							System.out.println("Unesite interval trajanja predavanja,datum pocetka i kraja!");
						}
					} 
					
					catch (SQLException e1)
					{
						e1.printStackTrace();
					}
		        }
		        if (LoginGUI.sessionSifNastavnik == -1)//logovan je prodekan
		        {
		        	try {
						DBExecutePredavanje.getPredavanja();
						DBExecutePredmet.getPredmeti();
						DBExecuteSala.getSala();
						DBExecuteSemestar.getSemestri();
						DBExecuteTipNastave.getTipNastave();
						DBExecuteUsmjerenje.getUsmjerenja();
						DBExecuteIzborni.getIzborni();
						DBExecutePredmetUsmjerenjeIzborni.getPredmetUsmjerenja();
						DBExecutePredmetPredavanjeTipNastave.getPredmetPredavanjeTipNastave();
						DBExecutePredavanjeUsmjerenjeSemestar.getPredavanjeUsmjerenjeSemestar();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					/**
					 * Kupimo vrijednosti iz ComboBoxova:
					 */
					
					/**
					 * usmjerenje:
					 * Iz comboBoxa dohvatamo index odabranog usmjerenja.
					 * kreiramo varijablu 'usmjerenje' koja ce nam sluziti da u nju iz BP upisemo trazeno usmjerenje
					 */
					Usmjerenje_ usmjerenje = new Usmjerenje_();
					ArrayList<Usmjerenje_> usmjerenja = new ArrayList<Usmjerenje_>();
					/**
					 * Iz liste usmjerenja zelimo da dohvatimo sva usmjerenja koja su odabrana.
					 * kreiramo listu usmjerenja.
					 */
					List<String> odabrUsmjerenje = listUsmjerenja.getSelectedValuesList();
					//System.out.println("Odabrani semestri su :" +  listUsmjerenja.getSelectedValuesList());
					/**
					 * Kreiramo pomocni vektor intova, u koji cemo upisati sve sifre odabranih usmjerenja, i to unutar sljedece petlje
					 */
					ArrayList<Integer> sifUsmjerenjaVektor = new ArrayList<>();
					/**
					 * u listi 'odabrUsmjerenje' se nalaze sva odabran usmjerenja.
					 * prolazimo kroz cijelu listu, tj kroz cijelo usmjerenje
					 */
					for (int i = 0; i < odabrUsmjerenje.size(); i++) {
						/**
						 * Od svakog odabranog elementa iz liste treba da uzmemo podatke, tj konkretno nas zanima sifUsmjerenja. Namjestili smo da u listi pored
						 * naziva usmjerenja bude ispisana i sifra usmjerenja, i to u obliku '1. Tehnicka informatika". Znaci, sifra je do tacke.
						 * 
						 * Kreiramo String pomUsmjerenje, i u njega upisujemo prvi, pa drugi itd odabrani element iz liste 'listUsmjerenje', npr '1. Teh informatika'
						 */
						String pomUsmjerenje = odabrUsmjerenje.get(i);
						/**
						 * Iz stringa 'pomUsmjerenje' trebamo izdvojiti sifru. Sifra zavrsava tackom, pa cemo to iskorisiti.
						 * Prvo cemo da provjerimo na kojoj poziciji se nalazi tacka
						 */
						int tacka = pomUsmjerenje.indexOf('.');
						/**
						 * kako imamo poziciju tacke, mozemo iz stringa 'pomUsmjerenje' izvuci substring do prve tacke, sto predstavlja sifUsmjerenje,
						 * koja ce i dalje biti tipa String
						 */
						String sifUsmjerenjeString = pomUsmjerenje.substring(0, tacka);
						/**
						 * pretvaramo string sifUsmjerenjeString u int
						 */
						int sifUsmjerenje = Integer.parseInt(sifUsmjerenjeString);
						/**
						 * U vektor sifUsmjerenjaVektor sada upisujemo sifru usmjerenja
						 */
						sifUsmjerenjaVektor.add(sifUsmjerenje);
					}
					
					/**
					 * Vadimo iz comboBoxa za semestar
					 */
					Semestar_ semestar = new Semestar_();
					if (comboBoxSemestar.getSelectedIndex() == -1) {
						System.err.println("Pogresno unesen/odabran ili nije unesen/odabran semestar");
					} else {
						
						int pom = comboBoxSemestar.getSelectedIndex();
						ArrayList<Semestar_> semestri = new ArrayList<Semestar_>();
						semestri = Semestar.semestarLista;
						semestar = semestri.get(pom);
					}
					/**
					 * Analogno gornjem ponavljamo za Predmet
					 */
					Predmet_ predmet = new Predmet_();
					if (comboBoxPredmet.getSelectedIndex() == -1) 
					{
						System.err.println("Pogresno unesen/odabran ili nije unesen/odabran semestar");
					} else {
						
						int pom = comboBoxPredmet.getSelectedIndex();
						ArrayList<Predmet_> predmeti = new ArrayList<Predmet_>();
						predmeti = Predmet.predmetLista;
			
						predmet = predmeti.get(pom);
					}
					/**
					 * Analogno gornjem ponavljamo za TipNastave
					 */
					TipNastave_ tipNastave = new TipNastave_();
					if (comboBoxTipNastavee.getSelectedIndex() == -1) 
					{
						System.err.println("Pogresno unesen/odabran ili nije unesen/odabran semestar");
					} else {
						
						int pom = comboBoxTipNastavee.getSelectedIndex();
						ArrayList<TipNastave_> tipoviNastave = new ArrayList<TipNastave_>();
						tipoviNastave = TipNastave.tipNastaveLista;
			
						tipNastave = tipoviNastave.get(pom);
					}
					/**
					 * Analogno gornjem ponavljamo za Sale
					 */
					Sala_ sala = new Sala_();
					if (comboBoxSala.getSelectedIndex() == -1) {
						System.err.println("Pogresno unesen/odabran ili nije unesen/odabran semestar");
					} else {
						int pom = comboBoxSala.getSelectedIndex();
						ArrayList<Sala_> sale = new ArrayList<Sala_>();
						sale = Sala.salaLista;
						sala = sale.get(pom);
					}
					/**
					 * Analogno gornjem ponavljamo za Dan
					 */
					String dan = new String();
					if (comboBoxDan.getSelectedIndex() == -1) {
						System.err.println("Pogresno unesen/odabran ili nije unesen/odabran semestar");
					} else {
						int pom = comboBoxDan.getSelectedIndex();
						dan = dani.get(pom);
					}
					/**
					 * Analogno gornjem ponavljamo za pocetakSat
					 */
					String pocetakSatString = null;
					if (comboBoxPocetakSat.getSelectedIndex() == -1) 
					{
						System.err.println("Pogresno unesen/odabran ili nije unesen/odabran semestar");
					} 
					else 
					{
						int pom = comboBoxPocetakSat.getSelectedIndex();
						int pocetakSat = Integer.parseInt((String)comboBoxPocetakSat.getSelectedItem());//sati.get(pom);
						 pocetakSatString = String.valueOf(pocetakSat);
					}
					/**
					 * Analogno gornjem ponavljamo za pocetakMin
					 */
					String pocetakMinString = null;
					if (comboBoxPocetakMin.getSelectedIndex() == -1) {
						System.err.println("Pogresno unesen/odabran ili nije unesen/odabran semestar");
					} else {
						int pom = comboBoxPocetakMin.getSelectedIndex();
						int pocetakMin = minute.get(pom);
						pocetakMinString = String.valueOf(pocetakMin);
					}
					/**
					 * Analogno gornjem ponavljamo za krajSat
					 */
					String krajSatString = null;
					if (comboBoxKrajSat.getSelectedIndex() == -1) {
						System.err.println("Pogresno unesen/odabran ili nije unesen/odabran semestar");
					} else {
						//int pom = comboBoxKrajSat.getSelectedIndex();
						int krajSat = Integer.parseInt((String)comboBoxKrajSat.getSelectedItem());//sati.get(pom);
						krajSatString = String.valueOf(krajSat);
					}
					/**
					 * Analogno gornjem ponavljamo za krajMin
					 */
					String krajMinString = null;
					if (comboBoxKrajMin.getSelectedIndex() == -1) {
						System.err.println("Pogresno unesen/odabran ili nije unesen/odabran semestar");
					} else {
						int pom = comboBoxKrajMin.getSelectedIndex();
						int krajMin = minute.get(pom);
						krajMinString = String.valueOf(krajMin);
					}
				
					/**
					 * Sada su prikupljeni svi podaci. Mozemo poceti praviti raspored.
					 * 
					 */
					/**
					 * Treba provjeriti da li je za odgovarajuci dan u odgovarajucoj sali i u odgovarajucem vremenu koji je korisnik odabrao vec unjet neki predmet.
					 * U 'predavanja' snimamo sve n-torke iz tabele
					 */
					ArrayList<Predavanje_> predavanja = new ArrayList<Predavanje_>();
					predavanja = Predavanje.predavanjeLista;
					/**
					 * kreiramo varijablu 'predavanje' u koju unosimo sve vrijednosti iz odabranih comboBoxova
					 */
					Predavanje_ predavanje = new Predavanje_();
					predavanje.setDanPredavanje(dan);
					predavanje.setSifSala(sala.getSifSala());
					/**
					 * Prije nego upisemo u 'predavanje' vrijeme pocetka i kraja predavanja, trebamo ih pretvoriti u tip Time.
					 * kreiramo varijablu tipa Time i u nju upisujemo vrijeme dobiveno od pocetka. String koji se parsira je oblika "hh:mm:ss".
					 */
					Time pocetakPredavanje = Time.valueOf(pocetakSatString + ":" + pocetakMinString + ":00");
					Time krajPredavanje = Time.valueOf(krajSatString + ":" + krajMinString + ":00");
					
					/**
					 * popunimo sada predavanje
					 */
					predavanje.setPocetakPredavanje(pocetakPredavanje);
					predavanje.setKrajPredavanje(krajPredavanje);
					predavanje.setTipPredavanja((String)comboBoxTipNastavee.getSelectedItem());
					
					/**
					 * Sada u 'predavanje' imamo sve sto je korisnik unio. provjeravamo iz BP da li je data sala u datom trenutku zauzeta , 
					 * tj da li se zeli zakazati predavanje dok neko drugo traje u toj sali. 
					 * prolazimo kroz vektor koji smo dohvatili iz bp. Ako jeste postavljamo kljuc na lock=1.
					 */
					int lock = 0;

					for (int i = 0; i < predavanja.size(); i++) {
						/**
						 * Kreiramo pomocnu varijablu u koju cemo smjestati vrijednosti iz vektora
						 */
						Predavanje_ predavanjePom = predavanja.get(i);
						/**
						 * Provjeravamo da li je je u pitanju ista sala u istom danu
						 */
						if (predavanjePom.getSifSala() == predavanje.getSifSala() && predavanjePom.getDanPredavanje().equals(predavanje.getDanPredavanje())) {
							/**
							 * Ako jeste, ide sljedeci stepen provjere: zelimo provjeriti da li je sala zauzeta, tj da li se odabrano vrijeme korisnika
							 * poklapa sa vremenom dok traje predavanje
							 */
							Time pocetakPredPom = predavanjePom.getPocetakPredavanje();
							Time krajPredPom = predavanjePom.getKrajPredavanje();
							Time pocetakOdabrani = predavanje.getPocetakPredavanje();
							Time krajOdabrani = predavanje.getKrajPredavanje();
							if (pocetakOdabrani.equals(pocetakPredPom)) {							
								lock = 1;
								////////////////////////////////////////test
								System.out.println("test: Vec postoji pocetak nastave u tom terminu za drugi pocetak. Lock = " + lock);
								////////////////////////////////////////
								break;
							}
							if (pocetakOdabrani.after(pocetakPredPom) && pocetakOdabrani.before(krajPredPom)) {
								/*
								 * Postavljamo lock na unos novog elementa u tabelu predavanje
								 */
								lock= 1;
								////////////////////////////////////////test
								System.out.println("test: Pocetak nastave se nalazi unutar termina druge nastave. Lock = " + lock);
								////////////////////////////////////////
								break;
							}
							if (krajOdabrani.after(pocetakPredPom) && krajOdabrani.before(krajPredPom)) {
								lock= 1;
								////////////////////////////////////////test
								System.out.println("test: Kraj nastave se nalazi unutar termina druge nastave. Lock = " + lock);
								////////////////////////////////////////
								break;
							}
						}
					}	
					
					/**
					 * Ako su prosle sve provjere iznad, onda se moze zakazati predavanje u datom terminu u datom danu u datoj sali
					 */
					/**
					 * treba obratiti paznju na veze:
					 * jedan predmet, jednog tipa, moze imati jedno predavanje u datom danu/trenutku/sali.
					 * jedan predmet, jedno predavanje, moze imati iz vise tipova
					 * jedno predavanje (u datom trenutku,sali,danu) iz jednog tipa, moze imati jedan predmet.
					 */
					if(lock == 0){
						PredmetPredavanjeTipNastave_ pptp = new PredmetPredavanjeTipNastave_();
						/**
						 * Upisujemo dati unjete podatke u tabele predavanja, PredavanjeUsmjerenje i PredmetPredavanjaTipNastave
						 */					
						try {
							/**
							 * Unos u BP Predavanje
							 */
							DBExecutePredavanje.insertPredavanje(predavanje);
							/**
							 * Unos u BP PredmetPredavanjeTipNastave
							 */
							pptp.setSifPredavanje(DBExecutePredavanje.sifPredavanjePublic); //DBExecutePredavanje.sifPredavanjePublic vraca kljuc trenutno unesene stavke u BP Predavanje
							pptp.setSifPredmet(predmet.getSifPredmet());
							pptp.setSifTipNastave(tipNastave.getSifTipNastave());
							DBExecutePredmetPredavanjeTipNastave.insertPredmetNastavnikTipNastave(pptp);
							
							/**
							 * unos u BP PredavanjeUsmjerenje
							 */
							/**
							 * unosimo sifre svih odabranih usmjerenja za odgovarajuce predavanje.
							 * te sifre smo smjestili u vektor intova 'sifUsmjerenjaVektor'
							 */
							PredavanjeUsmjerenjeSemestar_ pu = new PredavanjeUsmjerenjeSemestar_();
							for (int i = 0; i < sifUsmjerenjaVektor.size(); i++) 
							{
								pu.setSifPredavanje(DBExecutePredavanje.sifPredavanjePublic);
								pu.setSifUsmjerenje(sifUsmjerenjaVektor.get(i));
								//--Added by Alen
								pu.setSifSemestar(Integer.parseInt(semestar.getNazSemestar()));
								//--End
								DBExecutePredavanjeUsmjerenjeSemestar.insertPredavanjeUsmjerenjeSemestar(pu);
							}
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						/**
						 * Treba popuniti vektore ponedeljak, utorak itd.
						 */				
						switch (dan) {
						case "Ponedeljak":
							ponedeljak.add(pptp);
							break;
						case "Utorak":
							utorak.add(pptp);
							break;
						case "Srijeda":
							srijeda.add(pptp);
							break;
						case "Cetvrtak":
							cetvrtak.add(pptp);
							break;
						case "Petak":
							petak.add(pptp);
							break;
						case "Subota":
							subota.add(pptp);
							break;
						case "Nedelja":
							nedelja.add(pptp);
							break;
						}
					}
					
					/**
					 * Osvjezavanje tabele:
					 */
				}
		        }
				
		});
		btnPotvrdiUnos.setBounds(176, 437, 170, 25);
		frameRaspored.getContentPane().add(btnPotvrdiUnos);
	}
	
	
	/**
	 * Metod koji puni ComboBox semestra
	 * @throws SQLException
	 * 
	JComboBox<String> comboBoxKrajSat = new JComboBox<String>();
	JComboBox<String> comboBoxKrajMin = new JComboBox<String>();
	 */
	/*
	private void fillcomboBoxUsmjerenje() throws SQLException {
		PomocneF.clearListe();
		comboBoxUsmjerenje.removeAllItems();

		DBExecuteUsmjerenje.getUsmjerenja();
		ArrayList<Usmjerenje_> usmjerenja = new ArrayList<Usmjerenje_>();
		usmjerenja = Usmjerenje.usmjerenjeLista;
		for (int i = 0; i < usmjerenja.size(); i++) {
			Usmjerenje_ usmjerenje = new Usmjerenje_();
			usmjerenje = usmjerenja.get(i);
			comboBoxUsmjerenje.addItem(usmjerenje.getNazUsmjerenje());
		}
	}
	*/
	
	private void fillcomboBoxSemestar() throws SQLException {
		PomocneF.clearListe();
		comboBoxSemestar.removeAllItems();

		DBExecuteSemestar.getSemestri();
		ArrayList<Semestar_> semestri = new ArrayList<Semestar_>();
		semestri = Semestar.semestarLista;
		for (int i = 0; i < semestri.size(); i++) {
			Semestar_ semestarPom = new Semestar_();
			semestarPom = semestri.get(i);
			comboBoxSemestar.addItem(semestarPom.getNazSemestar());
		}
	}
	
	/*private void fillcomboBoxTipUsmjerenja() throws SQLException {
		PomocneF.clearListe();
		comboBoxTipNastavee.removeAllItems();

		DBExecuteTipNastave.getTipNastave();
		ArrayList<TipNastave_> tipoviNastave = new ArrayList<TipNastave_>();
		tipoviNastave = TipNastave.tipNastaveLista;
		for (int i = 0; i < tipoviNastave.size(); i++) {
			TipNastave_ tipSemestraPom = new TipNastave_();
			tipSemestraPom = tipoviNastave.get(i);
			comboBoxTipNastavee.addItem(tipSemestraPom.getNazTipNastave());
		}
	}*/
	
	private void fillcomboBoxDan() throws SQLException {
		
		fillArrayListDani();
		
		for (int i = 0; i < dani.size(); i++) {
			String pom = dani.get(i);
			comboBoxDan.addItem(pom);
		}
	}

	private void fillcomboBoxPredmet() throws SQLException {
		PomocneF.clearListe();
		comboBoxPredmet.removeAllItems();

		DBExecutePredmet.getPredmeti();
		ArrayList<Predmet_> predmeti = new ArrayList<Predmet_>();
		predmeti = Predmet.predmetLista;
		for (int i = 0; i < predmeti.size(); i++) {
			Predmet_ predmet = new Predmet_();
			predmet = predmeti.get(i);
			comboBoxPredmet.addItem(predmet.getNazPredmet());
		}
	}

	private void fillcomboBoxTipNastavee() throws SQLException {
		PomocneF.clearListe();
		comboBoxTipNastavee.removeAllItems();

		DBExecuteTipNastave.getTipNastave();
		ArrayList<TipNastave_> tipoviNastave = new ArrayList<TipNastave_>();
		tipoviNastave = TipNastave.tipNastaveLista;
		for (int i = 0; i < tipoviNastave.size(); i++) {
			TipNastave_ tipNastave = new TipNastave_();
			tipNastave = tipoviNastave.get(i);
			comboBoxTipNastavee.addItem(tipNastave.getNazTipNastave());
		}
	}

	private void fillcomboBoxSala() throws SQLException {
		PomocneF.clearListe();
		comboBoxSala.removeAllItems();

		DBExecuteSala.getSala();
		ArrayList<Sala_> sale = new ArrayList<Sala_>();
		sale = Sala.salaLista;
		for (int i = 0; i < sale.size(); i++) {
			Sala_ sala = new Sala_();
			sala = sale.get(i);
			comboBoxSala.addItem(sala.getNazivSala());
		}
	}

	private void fillcomboBoxPocetakSat() throws SQLException {
		
		for (int i = 8; i < 20; i++) {
			String pom = String.valueOf(i);
			comboBoxPocetakSat.addItem(pom);
		}
	}

	private void fillcomboBoxPocetakMin() throws SQLException {
		
		for (int i = 0; i < 60; i++) {
			String pom = String.valueOf(i);
			comboBoxPocetakMin.addItem(pom);
		}
	}

	private void fillcomboBoxKrajSat() throws SQLException {
		fillArrayListSati();
		for (int i = 8; i < 20; i++) {
			String pom = String.valueOf(i);
			comboBoxKrajSat.addItem(pom);
		}
	}

	private void fillcomboBoxKrajMin() throws SQLException {
		fillArrayListMinute();
		for (int i = 0; i < 60; i++) {
			String pom = String.valueOf(i);
			comboBoxKrajMin.addItem(pom);
		}
	}

	private void fillArrayListDani(){
		dani.add("Ponedeljak");
		dani.add("Utorak");
		dani.add("Srijeda");
		dani.add("Cetvrtak");
		dani.add("Petak");
		dani.add("Subota");
		dani.add("Nedelja");
	}
	
	private void fillArrayListSati(){
		for (int i = 8; i < 20; i++) {
			sati.add(i);
		}
	}
	
	private void fillArrayListMinute(){
		for (int i = 0; i < 60; i++) {
			minute.add(i);
		}
	}
	
	
	private void createListUsmjerenja() throws SQLException {
		PomocneF.clearListe();
		
		/**
		 * Pokupiti usmjerenja iz baze podataka i upisati u vektor usmjerenja
		 */
		DBExecuteUsmjerenje.getUsmjerenja();
		ArrayList<Usmjerenje_> usmjerenja = new ArrayList<>();
		usmjerenja = Usmjerenje.usmjerenjeLista;
		
		/**
		 * Trebamo napraviti niz stringova, koji sadrzi sve stringove koji se ispisuju u listi.
		 */
		final String[] valuesUsmjerenja = new String[usmjerenja.size()];//kreiramo niz stringova sa nastavici.size polja
		/**
		 * upisujemo u taj niz usmjerenja iz tabele
		 */
		for (int i = 0; i < usmjerenja.size(); i++) {
			Usmjerenje_ usmjerenje = new Usmjerenje_();
			usmjerenje = usmjerenja.get(i);
			valuesUsmjerenja[i] = usmjerenje.getSifUsmjerenje() + ". " + usmjerenje.getNazUsmjerenje();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(635, 90, 200, 100);
		frameRaspored.getContentPane().add(scrollPane);
		scrollPane.setViewportView(listUsmjerenja);
				

		listUsmjerenja.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listUsmjerenja.setBackground(Color.LIGHT_GRAY);
		listUsmjerenja.setModel(new AbstractListModel() {
			public int getSize() {
				return valuesUsmjerenja.length;
			}
			public Object getElementAt(int index) {
				return valuesUsmjerenja[index];
			}
		});
		listUsmjerenja.setSelectedIndex(0);

	}
	
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}
}
