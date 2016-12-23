package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;

import dataBase.DBExecuteGrupa;
import dataBase.DBExecuteNastavnik;
import dataBase.DBExecutePredavanje;
import dataBase.DBExecutePredavanjeUsmjerenjeSemestar;
import dataBase.DBExecutePredmet;
import dataBase.DBExecutePredmetNastavnikTipNastave;
import dataBase.DBExecutePredmetPredavanjeTipNastave;
import dataBase.DBExecuteSala;
import dataBase.DBExecuteSemestar;
import dataBase.DBExecuteStudent;
import dataBase.DBExecuteUsmjerenje;
import dataBase.DBExecuteZgrada;
import modeli.PredavanjeUsmjerenjeSemestar_;
import modeli.Predavanje_;
import modeli.Predmet_;
import modeli.Sala_;
import modeli.Semestar_;
import modeli.Student_;
import modeli.Usmjerenje_;
import modeli.Zgrada_;
import pomocneF.PomocneF;
import tables.Grupa;
import tables.Nastavnik;
import tables.Predavanje;
import tables.Predmet;
import tables.Sala;
import tables.Semestar;
import tables.Usmjerenje;
import tables.Zgrada;
import modeli.Grupa_;
import modeli.Item;
import modeli.Nastavnik_;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PrikazRasporedFilteriGUI {

	private JFrame framePrikazRasporedFilteri;
	
	JComboBox<String> comboBoxSemestar = new JComboBox<String>();
	JComboBox<Item<String>> comboBoxUsmjerenje = new JComboBox<Item<String>>();
	JComboBox<String> comboBoxProfesor = new JComboBox<String>();
	JComboBox<String> comboBoxPredmet = new JComboBox<String>();
	JComboBox<String> comboBoxZgrada = new JComboBox<String>();
	JComboBox<String> comboBoxSala = new JComboBox<String>();
	JComboBox<String> comboBoxGrupa = new JComboBox<String>();
	
	ArrayList<Integer> sifGrupa = new ArrayList<Integer>();
	ArrayList<Integer> sifSala = new ArrayList<Integer>();
	ArrayList<Integer> sifZgrada = new ArrayList<Integer>();
	ArrayList<Integer> sifPredmeta = new ArrayList<Integer>();
	ArrayList<Integer> sifNastavnika = new ArrayList<Integer>();


	/**
	 * Launch the application.
	 */
	public static void startPrikazRasporedFilteriGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazRasporedFilteriGUI window = new PrikazRasporedFilteriGUI();
					window.framePrikazRasporedFilteri.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazRasporedFilteriGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		framePrikazRasporedFilteri = new JFrame();
		framePrikazRasporedFilteri.setBounds(100, 100, 386, 430);
		//framePrikazRasporedFilteri.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrikazRasporedFilteri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		framePrikazRasporedFilteri.getContentPane().setLayout(null);
		
		JLabel lblUsmjerenje = new JLabel("Usmjerenje:");
		lblUsmjerenje.setBounds(12, 39, 85, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblUsmjerenje);
		
		comboBoxUsmjerenje.setBounds(115, 34, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxUsmjerenje);
		try {
			fillcomboBoxUsmjerenje();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JLabel lblSemestar = new JLabel("Semestar:");
		lblSemestar.setBounds(24, 75, 73, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblSemestar);
		
		comboBoxSemestar.setBounds(115, 70, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxSemestar);
		try {
			fillcomboBoxSemestar();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblPrikaiRasporedZa = new JLabel("Prikaži raspored za:");
		lblPrikaiRasporedZa.setBounds(12, 12, 143, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblPrikaiRasporedZa);
		
		JButton btnPrikaziRaspored = new JButton("Prikaži raspored");
		
		btnPrikaziRaspored.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				ArrayList<Integer> filtriranaPredavanja = new ArrayList<Integer>();
				Item item = (Item)comboBoxUsmjerenje.getSelectedItem();
				try
				{
					DBExecutePredavanje.getPredavanja();
					filtriranaPredavanja = DBExecutePredavanjeUsmjerenjeSemestar.getPredavanjeByUsmjerenjeAndSemestar(item.getValue(),Integer.parseInt((String)comboBoxSemestar.getSelectedItem()));
				} 
				
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				PrikazRasporedGUI.startPrikazRasporedGUI(filtriranaPredavanja);
			}
		});
		btnPrikaziRaspored.setBounds(115, 106, 250, 25);
		framePrikazRasporedFilteri.getContentPane().add(btnPrikaziRaspored);
		
		JLabel lblProfesor = new JLabel("Profesor:");
		lblProfesor.setBounds(31, 186, 66, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblProfesor);
		
		comboBoxProfesor.setBounds(115, 181, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxProfesor);
		try {
			fillcomboBoxProfesor();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblPredmet = new JLabel("Predmet:");
		lblPredmet.setBounds(32, 222, 65, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblPredmet);
		
		comboBoxPredmet.setBounds(115, 217, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxPredmet);
		try {
			fillcomboBoxPredmet();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblZgrada = new JLabel("Zgrada:");
		lblZgrada.setBounds(40, 258, 57, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblZgrada);
		
		comboBoxZgrada.setBounds(115, 253, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxZgrada);
		try {
			fillcomboBoxZgrada();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setBounds(61, 294, 36, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblSala);
		
		comboBoxSala.setBounds(115, 289, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxSala);
		try {
			fillcomboBoxSala();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblGrupa = new JLabel("Grupa:");
		lblGrupa.setBounds(49, 334, 48, 15);
		framePrikazRasporedFilteri.getContentPane().add(lblGrupa);
		
		comboBoxGrupa.setBounds(115, 329, 250, 24);
		framePrikazRasporedFilteri.getContentPane().add(comboBoxGrupa);
		try {
			fillcomboBoxGrupa();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnPrikaziRaspored2 = new JButton("Prikaži raspored");
		btnPrikaziRaspored2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//--Alen
					ArrayList<Integer> filtriranaPredavanja = new ArrayList<Integer>();
					ArrayList<Student_> studentiPoGrupi = new ArrayList<Student_>();
					filtriranaPredavanja.clear();
					
					Boolean vecFiltrirano = false;
					
					//Ako je odabrana grupa
					if (String.valueOf(comboBoxGrupa.getSelectedItem()) != "  --  ")
					{
						try
						{
							vecFiltrirano = true;
							DBExecutePredavanje.getPredavanja();
							studentiPoGrupi = DBExecuteStudent.getStudentsByGrupa(sifGrupa.get(comboBoxGrupa.getSelectedIndex()));
							
							filtriranaPredavanja = DBExecutePredavanjeUsmjerenjeSemestar.getPredavanjeByUsmjerenjeAndSemestar
									(studentiPoGrupi.get(studentiPoGrupi.size()-1).getSifUsmjerenja(),
											DBExecuteSemestar.getSemestarBySifra(studentiPoGrupi.get(studentiPoGrupi.size()-1).getSifSemestra()));
							vecFiltrirano = true;
						} 
						
						catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
					}
					
					if (String.valueOf(comboBoxSala.getSelectedItem()) != "  --  ")
					{
						try
						{
							if(vecFiltrirano)
							{
								ArrayList<Integer> dodatnaFiltriranaPredavanja = new ArrayList<Integer>();
								dodatnaFiltriranaPredavanja = DBExecutePredavanje.getPredavanjaBySala(sifSala.get(comboBoxSala.getSelectedIndex()));
								for (int i=0; i<filtriranaPredavanja.size();i++)
								{
									Boolean zaBrisanje = true;
									
									for (int j=0; j<dodatnaFiltriranaPredavanja.size(); j++)
									{
										if(dodatnaFiltriranaPredavanja.get(j).equals(filtriranaPredavanja.get(i)))
										{
											zaBrisanje = false;
											break;
										}
									}
									
									if(zaBrisanje)
									{
										filtriranaPredavanja.remove(i);
										i--;
									}
								}
							}
							else
							{
								filtriranaPredavanja = DBExecutePredavanje.getPredavanjaBySala(sifSala.get(comboBoxSala.getSelectedIndex()));
								vecFiltrirano = true;
							}
						} 
						
						catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
					}
					
					
					//Ako je odabrana zgrada 
					if (String.valueOf(comboBoxZgrada.getSelectedItem()) != "  --  ")
					{
						try
						{
							if(vecFiltrirano)
							{
								ArrayList<Integer> dodatnaFiltriranaPredavanja = new ArrayList<Integer>();
								ArrayList<Integer> sifSalePoZgradi = DBExecuteSala.getSalaByZgrada(sifZgrada.get((comboBoxZgrada.getSelectedIndex())));
								for(int i=0; i<sifSalePoZgradi.size(); i++)
								{
									ArrayList<Integer> pomPredavanjaSifre = new ArrayList<Integer>();
									pomPredavanjaSifre = DBExecutePredavanje.getPredavanjaBySala(sifSalePoZgradi.get(i));
									
									for(int j=0;j<pomPredavanjaSifre.size();j++)
									{
										dodatnaFiltriranaPredavanja.add(pomPredavanjaSifre.get(j));
									}
									
								}
								for (int i=0; i<filtriranaPredavanja.size();i++)
								{
									Boolean zaBrisanje = true;
									
									for (int j=0; j<dodatnaFiltriranaPredavanja.size(); j++)
									{
										if(dodatnaFiltriranaPredavanja.get(j).equals(filtriranaPredavanja.get(i)))
										{
											zaBrisanje = false;
											break;
										}
									}
									
									if(zaBrisanje)
									{
										filtriranaPredavanja.remove(i);
										i--;
									}
								}
							}
							else
							{
								ArrayList<Integer> sifSalePoZgradi = DBExecuteSala.getSalaByZgrada(sifZgrada.get((comboBoxZgrada.getSelectedIndex())));
								for(int i=0; i<sifSalePoZgradi.size(); i++)
								{
									ArrayList<Integer> pomPredavanjaSifre = new ArrayList<Integer>();
									pomPredavanjaSifre = DBExecutePredavanje.getPredavanjaBySala(sifSalePoZgradi.get(i));
									
									for(int j=0;j<pomPredavanjaSifre.size();j++)
									{
										filtriranaPredavanja.add(pomPredavanjaSifre.get(j));
									}
									
								}
								vecFiltrirano = true;
							}
						} 
						
						catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
						
					}
					
					if (String.valueOf(comboBoxPredmet.getSelectedItem()) != "  --  ")
					{
						try
						{
							if(vecFiltrirano)
							{
								ArrayList<Integer> dodatnaFiltriranaPredavanja = new ArrayList<Integer>();
								dodatnaFiltriranaPredavanja = DBExecutePredmetPredavanjeTipNastave.getPredavanjeByPredmet(sifPredmeta.get(comboBoxPredmet.getSelectedIndex()));
								for (int i=0; i<filtriranaPredavanja.size();i++)
								{
									Boolean zaBrisanje = true;
									
									for (int j=0; j<dodatnaFiltriranaPredavanja.size(); j++)
									{
										if(dodatnaFiltriranaPredavanja.get(j).equals(filtriranaPredavanja.get(i)))
										{
											zaBrisanje = false;
											break;
										}
									}
									
									if(zaBrisanje)
									{
										filtriranaPredavanja.remove(i);
										i--;
									}
								}
							}
							else
							{
								filtriranaPredavanja = DBExecutePredmetPredavanjeTipNastave.getPredavanjeByPredmet(sifPredmeta.get(comboBoxPredmet.getSelectedIndex()));
								vecFiltrirano = true;
							}
						} 
						
						catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
					}
					
					if (String.valueOf(comboBoxProfesor.getSelectedItem()) != "  --  ")
					{
						try
						{
							if(vecFiltrirano)
							{
								ArrayList<Integer> dodatnaFiltriranaPredavanja = new ArrayList<Integer>();
								ArrayList<Integer> sifPredmetaPoProfesoru = DBExecutePredmetNastavnikTipNastave.getPredmetByNastavnik(sifNastavnika.get((comboBoxProfesor.getSelectedIndex())));

								for(int i=0; i<sifPredmetaPoProfesoru.size(); i++)
								{
									ArrayList<Integer> pomPredmetSifre = new ArrayList<Integer>();
									pomPredmetSifre = DBExecutePredmetPredavanjeTipNastave.getPredavanjeByPredmet(sifPredmetaPoProfesoru.get(i));
									
									for(int j=0;j<pomPredmetSifre.size();j++)
									{
										dodatnaFiltriranaPredavanja.add(pomPredmetSifre.get(j));
									}
									
								}
								
								for (int k=0; k<filtriranaPredavanja.size();k++)
								{
									Boolean zaBrisanje = true;
									for (int j=0; j<dodatnaFiltriranaPredavanja.size(); j++)
									{
										if(dodatnaFiltriranaPredavanja.get(j).equals(filtriranaPredavanja.get(k)))
										{
											zaBrisanje = false;
											break;
										}
									}
									
									if(zaBrisanje)
									{
										filtriranaPredavanja.remove(k);
										k--;
									}
								}
							}
							else
							{
								ArrayList<Integer> sifPredmetaPoProfesoru = DBExecutePredmetNastavnikTipNastave.getPredmetByNastavnik(sifNastavnika.get((comboBoxProfesor.getSelectedIndex())));

								for(int i=0; i<sifPredmetaPoProfesoru.size(); i++)
								{
									ArrayList<Integer> pomPredavanjeSifre = new ArrayList<Integer>();
									pomPredavanjeSifre = DBExecutePredmetPredavanjeTipNastave.getPredavanjeByPredmet(sifPredmetaPoProfesoru.get(i));

									for(int j=0;j<pomPredavanjeSifre.size();j++)
									{

										filtriranaPredavanja.add(pomPredavanjeSifre.get(j));
									}
								}
								vecFiltrirano = true;
							}
						} 
						
						catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
					}
					
										
					
					vecFiltrirano = false;
					System.out.println("filtriranaPredavanja:" + filtriranaPredavanja);
					PrikazRasporedGUI.startPrikazRasporedGUI(filtriranaPredavanja);
				//--End
			}
		});
		btnPrikaziRaspored2.setBounds(115, 365, 250, 25);
		framePrikazRasporedFilteri.getContentPane().add(btnPrikaziRaspored2);
		
		//komentar
	}
	
	private void fillcomboBoxGrupa() throws SQLException {
		PomocneF.clearListe();
		comboBoxGrupa.removeAllItems();

		DBExecuteGrupa.getGrupe();
		ArrayList<Grupa_> grupe = new ArrayList<Grupa_>();
		grupe = Grupa.grupaLista;

		for (int i = 0; i < grupe.size(); i++) 
		{
			Grupa_ grupaPom = new Grupa_();
			grupaPom = grupe.get(i);
			sifGrupa.add(grupaPom.getSifGrupa());
			comboBoxGrupa.addItem(grupaPom.getNazivGrupa());
		}
		
		comboBoxGrupa.addItem("  --  ");
		comboBoxGrupa.setSelectedItem("  --  ");

	}
	
	private void fillcomboBoxSala() throws SQLException {
		PomocneF.clearListe();
		comboBoxSala.removeAllItems();

		DBExecuteSala.getSala();
		ArrayList<Sala_> sale = new ArrayList<Sala_>();
		sale = Sala.salaLista;		

		for (int i = 0; i < sale.size(); i++) 
		{
			Sala_ salaPom = new Sala_();
			salaPom = sale.get(i);
			sifSala.add(salaPom.getSifSala());
			comboBoxSala.addItem(salaPom.getNazivSala());
		}
		comboBoxSala.addItem("  --  ");
		comboBoxSala.setSelectedItem("  --  ");

	}
	
	private void fillcomboBoxSemestar() throws SQLException {
		PomocneF.clearListe();
		comboBoxSemestar.removeAllItems();

		DBExecuteSemestar.getSemestri();
		ArrayList<Semestar_> semestri = new ArrayList<Semestar_>();
		semestri = Semestar.semestarLista;
		
		for (int i = 0; i < semestri.size(); i++) 
		{
			Semestar_ semestarPom = new Semestar_();
			semestarPom = semestri.get(i);
			System.out.println(semestarPom.getNazSemestar());
			comboBoxSemestar.addItem(semestarPom.getNazSemestar());
		}

	}
	
	private void fillcomboBoxZgrada() throws SQLException {
		PomocneF.clearListe();
		comboBoxZgrada.removeAllItems();

		DBExecuteZgrada.getZgrade();
		ArrayList<Zgrada_> zgrade = new ArrayList<Zgrada_>();
		zgrade = Zgrada.zgradaLista;
		

		for (int i = 0; i < zgrade.size(); i++) 
		{
			Zgrada_ zgradaPom = new Zgrada_();
			zgradaPom = zgrade.get(i);
			sifZgrada.add(zgradaPom.getSifZgrada());
			comboBoxZgrada.addItem(zgradaPom.getNazZgrada());
		}
		comboBoxZgrada.addItem("  --  ");
		comboBoxZgrada.setSelectedItem("  --  ");

	}
	
	private void fillcomboBoxPredmet() throws SQLException {
		PomocneF.clearListe();
		comboBoxPredmet.removeAllItems();

		DBExecutePredmet.getPredmeti();
		ArrayList<Predmet_> predmeti = new ArrayList<Predmet_>();
		predmeti = Predmet.predmetLista;
		
		for (int i = 0; i < predmeti.size(); i++) 
		{
			Predmet_ predmetPom = new Predmet_();
			predmetPom = predmeti.get(i);
			sifPredmeta.add(predmetPom.getSifPredmet());
			comboBoxPredmet.addItem(predmetPom.getNazPredmet());
		}
		
		comboBoxPredmet.addItem("  --  ");
		comboBoxPredmet.setSelectedItem("  --  ");

	}
	
	private void fillcomboBoxProfesor() throws SQLException {
		PomocneF.clearListe();
		comboBoxProfesor.removeAllItems();

		DBExecuteNastavnik.getNastavnici();
		ArrayList<Nastavnik_> nastavnici = new ArrayList<Nastavnik_>();
		nastavnici = Nastavnik.nastavnikLista;
		
		for (int i = 0; i < nastavnici.size(); i++) 
		{
			Nastavnik_ nastavnikPom = new Nastavnik_();
			nastavnikPom = nastavnici.get(i);
			sifNastavnika.add(nastavnikPom.getSifNastavnik());
			comboBoxProfesor.addItem(nastavnikPom.getImeNastavnik() +" " + nastavnikPom.getPrezNastavnik());
		}
		
		comboBoxProfesor.addItem("  --  ");
		comboBoxProfesor.setSelectedItem("  --  ");

	}
	
	private void fillcomboBoxUsmjerenje() throws SQLException {
		PomocneF.clearListe();
		comboBoxUsmjerenje.removeAllItems();
		DBExecuteUsmjerenje.getUsmjerenja();
		ArrayList<Usmjerenje_> usmjerenja = new ArrayList<Usmjerenje_>();
		usmjerenja = Usmjerenje.usmjerenjeLista;
		
		for (int i = 0; i < usmjerenja.size(); i++) 
		{
			Usmjerenje_ usmjerenjePom = new Usmjerenje_();
			usmjerenjePom = usmjerenja.get(i);
			comboBoxUsmjerenje.addItem( new Item<String>(usmjerenjePom.getSifUsmjerenje(), usmjerenjePom.getNazUsmjerenje() ) );
			//comboBoxUsmjerenje.addItem(usmjerenjePom.getNazUsmjerenje());
		}
		//comboBoxUsmjerenje.addItem("  --  ");

	}
}
