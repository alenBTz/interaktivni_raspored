package gui;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;

import dataBase.DBExecuteGrupa;
import dataBase.DBExecuteNastavnik;
import dataBase.DBExecuteNastavnikGrupa;
import modeli.CustomDefaultTableModel;
import modeli.Grupa_;
import modeli.NastavnikGrupa_;
import modeli.Nastavnik_;
import pomocneF.IzbrisiRed;
import pomocneF.PomocneF;
import tables.Grupa;
import tables.Nastavnik;
import tables.NastavnikGrupa;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabelaNastavnikGrupaGUI {

	public static JFrame frameTabelaNastGrupa;
	private JTable tableNastGrupa;
	private CustomDefaultTableModel modelNastGrupa;
	/**
	 * Launch the application.
	 */
	public static void startTabelaNastGrupa() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaNastavnikGrupaGUI window = new TabelaNastavnikGrupaGUI();
					window.frameTabelaNastGrupa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabelaNastavnikGrupaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTabelaNastGrupa = new JFrame();
		frameTabelaNastGrupa.setBounds(100, 100, 400, 500);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaNastGrupa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaNastGrupa.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 374, 412);
		frameTabelaNastGrupa.getContentPane().add(scrollPane);
		
		tableNastGrupa = new JTable();
		scrollPane.setViewportView(tableNastGrupa);
		tableNastGrupa.setModel(new CustomDefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nastavnik", "Grupa"
			}
		));
		tableNastGrupa.getColumnModel().getColumn(0).setPreferredWidth(250);
		tableNastGrupa.getColumnModel().getColumn(1).setPreferredWidth(130);
		
		try {
			popuniTabeluGrupaNastavnicima();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/**
		 * brisanje jedne n-torke iz tabele, tj iz baze.
		 */
		JButton btnIzbrisiVezuNastGrupa = new JButton("Izbriši vezu");
		btnIzbrisiVezuNastGrupa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int red = tableNastGrupa.getSelectedRow();
				if(red != -1)
				{
					try {
						Object id = tableNastGrupa.getValueAt(red, 0);
						IzbrisiRed.izbrisiRed(id,"sifNastGrupa","nastavnikgrupa");
						modelNastGrupa.removeRow(red);
					} catch (SQLException e) {
						System.out.println("Operacija brisanja nije uspjela ");
						e.printStackTrace();
					}
				}
				else{
					System.out.println("Niti jedan red nije selektovan");
				}
			}
		});
		btnIzbrisiVezuNastGrupa.setBounds(12, 436, 117, 25);
		frameTabelaNastGrupa.getContentPane().add(btnIzbrisiVezuNastGrupa);
	}
	
	private void popuniTabeluGrupaNastavnicima() throws SQLException{
		modelNastGrupa = (CustomDefaultTableModel) tableNastGrupa.getModel();
		PomocneF.resetTable(modelNastGrupa);

		/**
		 * konektujemo se na BP ,tj na tabele NastavnikGrupa, nastavnik i grupa.
		 */
		DBExecuteNastavnikGrupa.getNastavnikGrupa();
		DBExecuteGrupa.getGrupe();
		DBExecuteNastavnik.getNastavnici();

		/**
		 * Kreiramo vektore, dohvatimo sve elemente iz tabela i snimomo u vektore
		 */
		ArrayList<NastavnikGrupa_> nastGrupe = new ArrayList<>();
		ArrayList<Nastavnik_> nastavnici = new ArrayList<>();
		ArrayList<Grupa_> grupe = new ArrayList<>();
		nastGrupe = NastavnikGrupa.nastavnikGrupaLista;
		nastavnici = Nastavnik.nastavnikLista;
		grupe = Grupa.grupaLista;

		/**
		 * Prolazimo kroz sve elemente nastGrrupe
		 */
		for (int i = 0; i < nastGrupe.size(); i++) {
			/**
			 * pravimo varijablu u koju cemo upisivati vrijednosti iz tabela NastavnikGrupe.
			 */
			NastavnikGrupa_ nastGrupa = new NastavnikGrupa_();	
			/**
			 * pokupimo jednu n torku i upisemo
			 */
			nastGrupa = nastGrupe.get(i);

			/**
			 * treba da dohvatimo i odgovarajuceg nastavnika i odgovarajucu grupu
			 */
			/**
			 * za nastavnika:
			 */
			Nastavnik_ nastavnik = new Nastavnik_();
			/**
			 * prolazimo kroz sve n torke grupe, i trazimo onu koja ima index koji je u nastGrupa unesen.
			 */
			for (int j = 0; j < nastavnici.size(); j++) {
				Nastavnik_ nastavnikPom = new Nastavnik_();
				nastavnikPom = nastavnici.get(j);
				if (nastavnikPom.getSifNastavnik() == nastGrupa.getSifNastavnik()) {
					nastavnik = nastavnikPom; //u 'grupa' upisujemo pronadjeni element
					break;
				}
			}
			/**
			 * za grupu:
			 */
			Grupa_ grupa = new Grupa_();
			/**
			 * prolazimo kroz sve n torke grupe, i trazimo onu koja ima index koji je u nastGrupa unesen.
			 */
			for (int k = 0; k < grupe.size(); k++) {
				Grupa_ grupaPom = new Grupa_();
				grupaPom = grupe.get(k);
				if (grupaPom.getSifGrupa() == nastGrupa.getSifGrupa()) {
					grupa = grupaPom; //u 'grupa' upisujemo pronadjeni element
					break;
				}
			}

			/**
			 * sad kad smo dohvatili sve elemente, mozemo ispisati tabelu
			 */
			modelNastGrupa.addRow(new Object[]{ nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik(), grupa.getNazivGrupa()});
		}
	}
}
