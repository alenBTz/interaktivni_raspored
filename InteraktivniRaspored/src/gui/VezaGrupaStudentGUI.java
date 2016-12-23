package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataBase.DBExecuteGrupa;
import dataBase.DBExecuteSemestar;
import dataBase.DBExecuteStudent;
import dataBase.DBExecuteUsmjerenje;
import modeli.Grupa_;
import modeli.Semestar_;
import modeli.Student_;
import modeli.Usmjerenje_;
import pomocneF.PomocneF;
import tables.Grupa;
import tables.Semestar;
import tables.Student;
import tables.Usmjerenje;

import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VezaGrupaStudentGUI {

	private JFrame frameGrupaStudent;
	private JTextField txtNazgrupa;
	private JTextField txtImestudenta;
	private JTextField txtPrezstudenta;
	private JTextField txtJmbgstudenta;
	private JTextField txtBrindexstud;
	
	JComboBox<String> comboBoxGrupaStud = new JComboBox<String>();
	JComboBox<String> comboBoxUsmjerenja = new JComboBox<String>();
	JComboBox<String> comboBoxSemestar = new JComboBox<String>();
	
	ArrayList<Integer> semestriSifre ;
	ArrayList<Integer> usmjerenjaSifre ;

	int active1 = 0;
	int active2 = 0;
	int active3 = 0;
	int active4 = 0;
	/**
	 * Launch the application.
	 */
	public static void startGrupaStudentGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VezaGrupaStudentGUI window = new VezaGrupaStudentGUI();
					window.frameGrupaStudent.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VezaGrupaStudentGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameGrupaStudent = new JFrame();
		frameGrupaStudent.setBounds(100, 100, 240, 480);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameGrupaStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameGrupaStudent.getContentPane().setLayout(null);
		
		JButton btnPregledGrupa = new JButton("Pregled grupa");
		btnPregledGrupa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * pozivamo prikaz tabele grupa.
				 */
				/**
				 * Provjera sa varijablama active1 i active2: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active1 == 1)
					TabelaGrupaGUI.frameTabelaGrupa.dispose();
				TabelaGrupaGUI.startTabeGrupaGUI();
				active2 = 1;
				
			}
		});
		btnPregledGrupa.setBounds(22, 12, 180, 25);
		frameGrupaStudent.getContentPane().add(btnPregledGrupa);
		
		JLabel lblGrupa = new JLabel("Grupa:");
		lblGrupa.setBounds(48, 49, 48, 15);
		frameGrupaStudent.getContentPane().add(lblGrupa);
		
		txtNazgrupa = new JTextField();
		txtNazgrupa.setText("");
		txtNazgrupa.setBounds(100, 47, 114, 19);
		frameGrupaStudent.getContentPane().add(txtNazgrupa);
		txtNazgrupa.setColumns(10);
		
		JButton btnPotvrdiUnosGrupa = new JButton("Potvrdi unos");
		btnPotvrdiUnosGrupa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Treba unjeti novou grupu.
				 */
				/**
				 * Ako vec postoji lista nastavnika procitana iz BP, brisemo je, kako se ne bi postojali dupli podaci
				 */
				Grupa.grupaLista.clear();
				
				/**
				 * Kreiramo varijablu nastavnik, u koju cemo upisati podatke koje je unio korisnik
				 */
				Grupa_ grupa = new Grupa_();
				
				/**
				 * Unosimo u 'grupa' podatke koje je unio korisnik
				 */
				grupa.setNazivGrupa(txtNazgrupa.getText());
				
				/**
				 * Kako imamo sve podatke koje su unesene, mozemo varijablu 'grupa' upisati u BP
				 */
				try {
					/**
					 * Upisujemo 'grupa' u BP pozivanjem metoda:
					 */
					DBExecuteGrupa.insertGrupa(grupa);
					
					/**
					 * Posto smo unjeli novou grupu, zelimo da osvjezimo tabelu.
					 */
					if(active2 == 1)
						TabelaGrupaGUI.frameTabelaGrupa.dispose();
					TabelaGrupaGUI.startTabeGrupaGUI();
					active1 = 1;
					
					/**
					 * Posto je unjeta nova grupa u BP, mora se osvjeziti ComboBoxGrupaStud koji se koristi prilikom
					 * unosa novog studenta. Tj u njega treba ubaciti i novu grupu, u taj comboBox
					 */
					comboBoxGrupaStud.setBounds(100, 288, 114, 24);
					frameGrupaStudent.getContentPane().add(comboBoxGrupaStud);
					fillComboBoxStudGrupe();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnPotvrdiUnosGrupa.setBounds(22, 78, 180, 25);
		frameGrupaStudent.getContentPane().add(btnPotvrdiUnosGrupa);
		
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 115, 210, 2);
		frameGrupaStudent.getContentPane().add(separator);
		
		
		
		
		JButton btnPregledStudenata = new JButton("Pregled studenata");
		btnPregledStudenata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * pozivamo prikaz tabele studenti.
				 */
				/**
				 * Provjera sa varijablama active3 i active4: Da se ne otvara duplo prozor sa tabelom, vec da se 
				 * prilikom provjere nastavnika otvori, i ako unesemo nastavnika, stara tabela da se zatvori a nova otvori
				 * nesto kao refresh tabele, nisam znao drugacije implementirati nego ovako
				 */
				if(active3 == 1)
					TabelaStudentGUI.frameTabelaStudent.dispose();
				TabelaStudentGUI.startTabelaStudentGUI();
				active4 = 1;
			
			}
		});
		btnPregledStudenata.setBounds(22, 129, 180, 25);
		frameGrupaStudent.getContentPane().add(btnPregledStudenata);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(66, 166, 30, 15);
		frameGrupaStudent.getContentPane().add(lblIme);
		
		txtImestudenta = new JTextField();
		txtImestudenta.setText("");
		txtImestudenta.setBounds(100, 164, 114, 19);
		frameGrupaStudent.getContentPane().add(txtImestudenta);
		txtImestudenta.setColumns(10);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(34, 197, 62, 15);
		frameGrupaStudent.getContentPane().add(lblPrezime);
		
		txtPrezstudenta = new JTextField();
		txtPrezstudenta.setText("");
		txtPrezstudenta.setBounds(100, 195, 114, 19);
		frameGrupaStudent.getContentPane().add(txtPrezstudenta);
		txtPrezstudenta.setColumns(10);
		
		JLabel lblJmbg = new JLabel("JMBG:");
		lblJmbg.setBounds(56, 228, 40, 15);
		frameGrupaStudent.getContentPane().add(lblJmbg);
		
		txtJmbgstudenta = new JTextField();
		txtJmbgstudenta.setText("");
		txtJmbgstudenta.setBounds(100, 226, 114, 19);
		frameGrupaStudent.getContentPane().add(txtJmbgstudenta);
		txtJmbgstudenta.setColumns(10);
		
		JLabel lblBrojIndexa = new JLabel("Broj indexa:");
		lblBrojIndexa.setBounds(12, 259, 84, 15);
		frameGrupaStudent.getContentPane().add(lblBrojIndexa);
		
		txtBrindexstud = new JTextField();
		txtBrindexstud.setText("");
		txtBrindexstud.setBounds(100, 257, 114, 19);
		frameGrupaStudent.getContentPane().add(txtBrindexstud);
		txtBrindexstud.setColumns(10);
		
		JLabel lblGrupaStud = new JLabel("Grupa:");
		lblGrupaStud.setBounds(48, 293, 48, 15);
		frameGrupaStudent.getContentPane().add(lblGrupaStud);
		
		comboBoxGrupaStud.setBounds(100, 288, 114, 24);
		frameGrupaStudent.getContentPane().add(comboBoxGrupaStud);
		try {
			fillComboBoxStudGrupe();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		JLabel lblUsmjerenje = new JLabel("Usmjerenje:");
		lblUsmjerenje.setBounds(20, 327, 114, 15);
		frameGrupaStudent.getContentPane().add(lblUsmjerenje);
		
		comboBoxUsmjerenja.setBounds(100, 327, 114, 24);
		frameGrupaStudent.getContentPane().add(comboBoxUsmjerenja);
		try {
			fillComboBoxUsmjerenje();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 	
		
		JLabel lblSemestar = new JLabel("Semestar:");
		lblSemestar.setBounds(15, 361, 114, 15);
		frameGrupaStudent.getContentPane().add(lblSemestar);
		
		comboBoxSemestar.setBounds(100, 361, 114, 24);
		frameGrupaStudent.getContentPane().add(comboBoxSemestar);
		try {
			fillComboBoxSemestri();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		 
		
		
		JButton btnPotvrdiUnosStud = new JButton("Potvrdi unos");
		btnPotvrdiUnosStud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				/**
				 * Treba unjeti novog studenta.
				 */
				/**
				 * Ako vec postoji lista studenata procitana iz BP, brisemo je, kako se ne bi postojali dupli podaci
				 */
				Student.studentLista.clear();
				
				/**
				 * Kreiramo varijablu 'student', u koju cemo upisati podatke koje je unio korisnik
				 */
				Student_ student = new Student_();
				
				/**
				 * upis u 'student' sto je korisnik unio
				 */
				student.setImeStudent(txtImestudenta.getText());
				student.setPrezStudent(txtPrezstudenta.getText());
				student.setJmbgStudent(txtJmbgstudenta.getText());
				student.setBrIndexa(txtBrindexstud.getText());
				//--Alen
				if (comboBoxSemestar.getSelectedIndex() == -1)
				{
					System.err.println("Pogresno unesen ili nije unesena grupa");
				}
				else 
				{
					int pom = comboBoxSemestar.getSelectedIndex();
					student.setSifSemestra(semestriSifre.get(pom));
				}
				
				if (comboBoxUsmjerenja.getSelectedIndex() == -1)
				{
					System.err.println("Pogresno unesen ili nije unesena grupa");
				}
				else 
				{
					int pom = comboBoxUsmjerenja.getSelectedIndex();
					student.setSifUsmjerenja(usmjerenjaSifre.get(pom));
					System.out.println("usmjerenjaSifre.get(pom)" + usmjerenjaSifre.get(pom));
				}
				//--End
				/**
				 * Treba da studenta dodamo i odgovarajucoj grupi. Dohvatimo sve grupe iz BP, da bi od odgovarajuce grupe koju 
				 * smo odabrali uzeli potrebne podatke
				 */
				try {
					DBExecuteGrupa.getGrupe();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
				/**
				 * Provjeravamo koja je grupa odabrana u ComboBoxu. Indeksiranje u ComboBoxu je za prvi element 0, drugi 1, itd, isto
				 * kao sto je i u ArrayListi. Posto smo comboBox punili sa elementima arrayliste, znaci da prvi element u combo boxu
				 * odgovara prvom elementu u arraylisti itd.
				 * Arraylistu smo napunili iz BP.
				 */
				if (comboBoxGrupaStud.getSelectedIndex() == -1) {
					System.err.println("Pogresno unesen ili nije unesena grupa");
				} else {
					/**
					 * Vrijednost indexa odabranog elementa snimamo u pom
					 */
					int pom = comboBoxGrupaStud.getSelectedIndex();
					
					/**
					 * kreiramo varijablu 'grupa', te vektor 'grupe'. U vektor 'grupe' upisemo iz BP sve n-torke.
					 */
					Grupa_ grupa = new Grupa_();
					ArrayList<Grupa_> grupe = new ArrayList<>();
					grupe = Grupa.grupaLista;
					
					/*
					 * Iz vektora 'grupe' uzimamo n-torku koja ima index 'pom', u prethodnim komentarima objasnjeno zasto, jer 
					 * su elementi istim redosljedom ubaceni i u vektor, i u combobox. Tu n-torku snimamo u 'grupa'
					 */
					grupa = grupe.get(pom);
					
					/**
					 * Sifru dobivamo pozivom metoda grupa.getSifGrupa(), snimamo u pom2
					 */
					int pom2 = grupa.getSifGrupa();
					
					/**
					 * Unosimo vrijednost sifre grupe u 'student'
					 */
					student.setSifGrupa(grupa.getSifGrupa());
				}
				
				/**
				 * Sada imamo jednu varijablu 'student' popunjenu u potpunosti i mozemo je snimiti u BP
				 */
				try {
					DBExecuteStudent.insertStudent(student);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/**
				 * Kako smo unjeli studenta, treba da osvjezimo tabelu, da se prikaze i zadnji unjeti student.
				 */
				if(active4 == 1)
					TabelaStudentGUI.frameTabelaStudent.dispose();
				TabelaStudentGUI.startTabelaStudentGUI();
				active3 = 1;
				
			}
		});
		btnPotvrdiUnosStud.setBounds(22, 410, 180, 25);
		frameGrupaStudent.getContentPane().add(btnPotvrdiUnosStud);
	}
	
	private void fillComboBoxStudGrupe() throws SQLException{
		PomocneF.clearListe();

		comboBoxGrupaStud.removeAllItems();

		DBExecuteGrupa.getGrupe();
		ArrayList<Grupa_> grupe= new ArrayList<Grupa_>();
		grupe = Grupa.grupaLista;
		for (int i = 0; i < grupe.size(); i++) {
			Grupa_ grupaPom = new Grupa_();	
			grupaPom = grupe.get(i);
			comboBoxGrupaStud.addItem(grupaPom.getNazivGrupa());
		}
	}
	
	private void fillComboBoxUsmjerenje() throws SQLException{
		PomocneF.clearListe();

		comboBoxUsmjerenja.removeAllItems();

		DBExecuteUsmjerenje.getUsmjerenja();
		ArrayList<Usmjerenje_> usmjerenja= new ArrayList<Usmjerenje_>();
		usmjerenja = Usmjerenje.usmjerenjeLista;
		usmjerenjaSifre = new ArrayList<Integer>();
		for (int i = 0; i < usmjerenja.size(); i++) {
			Usmjerenje_ usmjerenjePom = new Usmjerenje_();	
			usmjerenjePom = usmjerenja.get(i);
			usmjerenjaSifre.add(usmjerenjePom.getSifUsmjerenje());
			//comboBoxUsmjerenja.addItem(usmjerenjePom.getSifUsmjerenje() + "." + usmjerenjePom.getNazUsmjerenje());
			comboBoxUsmjerenja.addItem(usmjerenjePom.getNazUsmjerenje());
		}
	}
	
	private void fillComboBoxSemestri() throws SQLException{
		PomocneF.clearListe();

		comboBoxSemestar.removeAllItems();

		DBExecuteSemestar.getSemestri();
		ArrayList<Semestar_> semestri = new ArrayList<Semestar_>();
		semestri = Semestar.semestarLista;
		semestriSifre = new ArrayList<Integer>();
		for (int i = 0; i < semestri.size(); i++) {
			Semestar_ semestarPom = new Semestar_();	
			semestarPom = semestri.get(i);
			semestriSifre.add(semestarPom.getSifSemestar());
			comboBoxSemestar.addItem(semestarPom.getNazSemestar());
		}
	}
}
