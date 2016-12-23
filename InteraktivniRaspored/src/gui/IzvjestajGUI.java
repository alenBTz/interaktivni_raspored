package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXDatePicker;

import dataBase.DBExecuteNastavnik;
import dataBase.DBExecuteRezervacija;
import dataBase.DBExecuteSemestar;
import modeli.Nastavnik_;
import modeli.Rezervacija_;
import modeli.Semestar_;
import pomocneF.PomocneF;
import tables.Nastavnik;
import tables.Rezervacija;
import tables.Semestar;

public class IzvjestajGUI {
	JFrame frameIzvjestajGUI;
	private JTextField txtFakultet;
	private JTextField txtNastavnik;
	private JTextField txtStudijskiProgram;
	private JTextField txtGodina;
	
	JComboBox<String> comboBoxNastavnik = new JComboBox<String>();
	
	public static void startIzvjestajGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzvjestajGUI window = new IzvjestajGUI();
					window.frameIzvjestajGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public IzvjestajGUI() {
		initialize();
	}
	
	private void initialize() {
		frameIzvjestajGUI = new JFrame();
		frameIzvjestajGUI.setBounds(100, 100, 400, 400);
	
		frameIzvjestajGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameIzvjestajGUI.getContentPane().setLayout(null);
		
		JLabel lblFakultet = new JLabel("Fakultet: ");
		lblFakultet.setBounds(12, 12, 120, 20);
		frameIzvjestajGUI.getContentPane().add(lblFakultet);
		
		txtFakultet = new JTextField();
		txtFakultet.setText("");
		txtFakultet.setBounds(150, 12, 166, 20);
		txtFakultet.setColumns(10);
		frameIzvjestajGUI.getContentPane().add(txtFakultet);
		
		JLabel lblNastavnik = new JLabel("Izvrsilac: ");
		lblNastavnik.setBounds(12, 42, 120, 20);
		frameIzvjestajGUI.getContentPane().add(lblNastavnik);
		
		comboBoxNastavnik.setBounds(150, 42, 166, 20);
		frameIzvjestajGUI.getContentPane().add(comboBoxNastavnik);
		try {
			fillcomboBoxNastavnik();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblStudijskiProgram = new JLabel("Studijski program: ");
		lblStudijskiProgram.setBounds(12, 72, 120, 20);
		frameIzvjestajGUI.getContentPane().add(lblStudijskiProgram);
		
		txtStudijskiProgram = new JTextField();
		txtStudijskiProgram.setText("");
		txtStudijskiProgram.setBounds(150, 72, 166, 20);
		//txtStudijskiProgram.setColumns(10);
		frameIzvjestajGUI.getContentPane().add(txtStudijskiProgram);
		
		JLabel lblDatumIzvjestaja = new JLabel("Mjesec: ");
		lblDatumIzvjestaja.setBounds(12, 102, 120, 20);
		frameIzvjestajGUI.getContentPane().add(lblDatumIzvjestaja);
		
		String months[] = { "Januar", "Februar", "Mart", "April", "Maj",
                "Juni", "Juli", "August", "September", "Oktobar", "Novembar",
                "Decembar" };
        SpinnerModel model = new SpinnerListModel(months);
        JSpinner spinner = new JSpinner(model);
        spinner.setBounds(150, 102, 120, 20);
        frameIzvjestajGUI.getContentPane().add(spinner);
        
        JLabel lblGodina = new JLabel("Godina: ");
        lblGodina.setBounds(12, 132, 120, 20);
		frameIzvjestajGUI.getContentPane().add(lblGodina);
		
		txtGodina = new JTextField();
		txtGodina.setText("");
		txtGodina.setBounds(150, 132, 166, 20);
		frameIzvjestajGUI.getContentPane().add(txtGodina);
		
		System.out.println("Arrays.asList(months).indexOf(spinner.getValue()):" + Arrays.asList(months).indexOf("Februar"));
		
		JButton btnGenerisiIzvjestaj = new JButton("Generisi Izvjestaj");
		btnGenerisiIzvjestaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int mjesec = Arrays.asList(months).indexOf("Februar");
				mjesec = mjesec + 1;//odabrani mjesec
				
				int godina = Integer.parseInt(txtGodina.getText());//godina
				
				try {
					DBExecuteRezervacija.getRezervacije();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				ArrayList<Rezervacija_>rezervacije = Rezervacija.rezervacijaLista;
				ArrayList<Rezervacija_>filtriraneRezervacije = new ArrayList<Rezervacija_>();
				
				for (int i=0; i< rezervacije.size(); i++){
					try {
						Nastavnik_ nastavnik = DBExecuteNastavnik.getNastavnikBySifra(rezervacije.get(i).getSifNastavnik());
						String imeNastavnika = nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik();
						if (imeNastavnika.equals(comboBoxNastavnik.getSelectedItem())){
							Calendar cal = Calendar.getInstance();
							cal.setTime(rezervacije.get(i).getDatumRezervacija());
							System.out.println("cal.get(Calendar.MONTH):" + cal.get(Calendar.MONTH));
							if (cal.get(Calendar.MONTH) == mjesec && cal.get(Calendar.YEAR) == godina){
								filtriraneRezervacije.add(rezervacije.get(i));
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		btnGenerisiIzvjestaj.setBounds(100, 320, 175, 23);
		frameIzvjestajGUI.getContentPane().add(btnGenerisiIzvjestaj);
	}
		private void fillcomboBoxNastavnik() throws SQLException{
			PomocneF.clearListe();
			comboBoxNastavnik.removeAllItems();

			DBExecuteNastavnik.getNastavnici();
			ArrayList<Nastavnik_> nastavnici = new ArrayList<Nastavnik_>();
			nastavnici = Nastavnik.nastavnikLista;
			for (int i = 0; i < nastavnici.size(); i++) {
				Nastavnik_ nastavnikPom = new Nastavnik_();
				nastavnikPom = nastavnici.get(i);
				comboBoxNastavnik.addItem(nastavnikPom.getImeNastavnik() + " " + nastavnikPom.getPrezNastavnik());
		}
	}
	
	
}
