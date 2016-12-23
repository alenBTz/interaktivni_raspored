package gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EventObject;
import java.util.GregorianCalendar;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import org.jdesktop.swingx.renderer.FormatStringValue;
import org.jdesktop.swingx.table.DatePickerCellEditor;

import dataBase.DBExecuteGrupa;
import dataBase.DBExecuteNastavnik;
import dataBase.DBExecutePredmet;
import dataBase.DBExecuteRezervacija;
import dataBase.DBExecuteSala;
import dataBase.DBExecuteStudent;
import javafx.scene.control.DatePicker;
import modeli.MyTableModel;
import modeli.Grupa_;
import modeli.Nastavnik_;
import modeli.Rezervacija_;
import modeli.Student_;
import pomocneF.IzbrisiRed;
import pomocneF.PomocneF;
import tables.Grupa;
import tables.Rezervacija;
import tables.Student;

public class TabelaRezervacijaGUI implements TableModelListener 
{
	public static JFrame frameTabelaRezervacija;
	private JTable tableRezervacija;
	private MyTableModel modelRezervacija;
	private Boolean dolazimSaDelete = false;
	/**
	 * Launch the application.
	 */
	public static void startTabelaRezervacijaGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaRezervacijaGUI window = new TabelaRezervacijaGUI();
					window.frameTabelaRezervacija.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabelaRezervacijaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameTabelaRezervacija = new JFrame();
		frameTabelaRezervacija.setBounds(100, 100, 1000, 600);
		/**
		 * umjesto JFrame.EXIT_ON_CLOSE, koristit cemo JFrame.DISPOSE_ON_CLOSE. Exit on close zatvara cijelu aplikaciju
		 * dok Dispose samo aktivni prozor
		 */
		frameTabelaRezervacija.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameTabelaRezervacija.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 974, 512);
		frameTabelaRezervacija.getContentPane().add(scrollPane);
		
		tableRezervacija = new JTable();
		scrollPane.setViewportView(tableRezervacija);
		tableRezervacija.setModel(new MyTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Datum ", "Tip", "Pocetak", "Kraj", "Predmet", "Sala", "Nastavnik"
			}
		));
		tableRezervacija.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableRezervacija.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableRezervacija.getColumnModel().getColumn(2).setPreferredWidth(205);
		tableRezervacija.getColumnModel().getColumn(3).setPreferredWidth(120);
		tableRezervacija.getColumnModel().getColumn(4).setPreferredWidth(120);
		tableRezervacija.getColumnModel().getColumn(5).setPreferredWidth(400);
		tableRezervacija.getColumnModel().getColumn(6).setPreferredWidth(400);
		tableRezervacija.getColumnModel().getColumn(7).setPreferredWidth(400);
		
		try 
		{
			popuniTabeluStudentima();
			tableRezervacija.getModel().addTableModelListener(this);
			
			TableColumn columnDate = tableRezervacija.getColumnModel().getColumn(1);
			TableColumn columnTimeBegin = tableRezervacija.getColumnModel().getColumn(3);
			TableColumn columnTimeEnd = tableRezervacija.getColumnModel().getColumn(4);
			
			JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
			JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
			timeSpinner.setEditor(timeEditor);
			timeSpinner.setValue(new Date());
			
		    String[] values = new String[] { "08:00:00", "09:00:00", "10:00:00","11:00:00","12:00:00","13:00:00","14:00:00",
		    		"15:00:00","16:00:00","17:00:00","18:00:00","19:00:00"};
			columnTimeBegin.setCellEditor(new SpinnerEditor(values));
			columnTimeEnd.setCellEditor(new SpinnerEditor(values));
			columnDate.setCellEditor(new DatePickerCellEditor());
			
			TableCellRenderer dateRenderer = new DefaultTableRenderer(new FormatStringValue(new SimpleDateFormat("yyyy-MM-dd")));
		    TableCellRenderer timeRenderer = new DefaultTableRenderer(new FormatStringValue(new SimpleDateFormat("HH:mm:ss")));
		    
		    columnDate.setCellRenderer(dateRenderer);
		    columnTimeBegin.setCellRenderer(timeRenderer);
		    columnTimeEnd.setCellRenderer(timeRenderer);
		    
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		
	
		JButton btnIzbrisiRezervaciju = new JButton("Izbriši rezervaciju");
		btnIzbrisiRezervaciju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int red = tableRezervacija.getSelectedRow();
				String vlasnikRezervacije = (String)tableRezervacija.getValueAt(red, 7);
		    	Nastavnik_ logovaniKorisnik = new Nastavnik_();
		    	modelRezervacija =  (MyTableModel)tableRezervacija.getModel();
		    	
		    	try {
					logovaniKorisnik = DBExecuteNastavnik.getNastavnikBySifra(LoginGUI.sessionSifNastavnik);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(red != -1 && vlasnikRezervacije.equals(logovaniKorisnik.getImeNastavnik() + " " + logovaniKorisnik.getPrezNastavnik()))
				{
					try {
						Object id = tableRezervacija.getValueAt(red, 0);
						IzbrisiRed.izbrisiRed(id,"sifRezervacija","rezervacija");
						dolazimSaDelete = true;
						modelRezervacija.removeRow(red);
						tableRezervacija.repaint();
					} catch (SQLException e) {
						System.out.println("Operacija brisanja nije uspjela ");
						e.printStackTrace();
					}
				}
				else{
					System.out.println("Niti jedan red nije selektovan ili niste vlasnik odabrane rezervacije");
				}
			}
		});
		btnIzbrisiRezervaciju.setBounds(12, 536, 150, 25);
		frameTabelaRezervacija.getContentPane().add(btnIzbrisiRezervaciju);
	}
	public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = modelRezervacija;
        
        if (!dolazimSaDelete)
        {
        	 try
             {
             	Nastavnik_ logovaniKorisnik = DBExecuteNastavnik.getNastavnikBySifra(LoginGUI.sessionSifNastavnik);
     			String vlasnikRezervacije = model.getValueAt(row, 7).toString();
     			
     			if (vlasnikRezervacije.equals(logovaniKorisnik.getImeNastavnik() + " " + logovaniKorisnik.getPrezNastavnik()) || vlasnikRezervacije.equals("Prodekan"))
     			{
     				Rezervacija_ editovanaRezervacija = new Rezervacija_(); 
     				
     				SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
     				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
     				
     				String datumRezervacije = dateFormat.format(model.getValueAt(row, 1));
     				
     				try 
     				{
     					java.sql.Date sqlDatumRezervacije = new java.sql.Date(dateFormat.parse(datumRezervacije).getTime());
     					editovanaRezervacija.setDatumRezervacija(sqlDatumRezervacije);
     				}
     				catch (ParseException e1)
     				{
     					e1.printStackTrace();
     				}
     				//String vrijemeKrajaRezervacije = timeFormat.format(model.getValueAt(row, 4));
     				String imePrezime[] = ((String)model.getValueAt(row, 7)).split(" ");
     				try 
     				{
     					if(model.getValueAt(row, 3).getClass() == java.sql.Time.class)
     					{
     						editovanaRezervacija.setSatRezervacije((java.sql.Time)model.getValueAt(row, 3));
     					}
     					else
     					{
     						Date dateRez = timeFormat.parse((String)model.getValueAt(row, 3));
     						Time timeRez = new Time(dateRez.getTime());
     						editovanaRezervacija.setSatRezervacije(timeRez);
     					}
     					if(model.getValueAt(row, 4).getClass() == java.sql.Time.class)
     					{
     						editovanaRezervacija.setSatRezervacijeKraj((java.sql.Time)model.getValueAt(row, 4));
     					}
     					else
     					{
     						Date dateRez = timeFormat.parse((String)model.getValueAt(row, 4));
     						Time timeRez = new Time(dateRez.getTime());
     						editovanaRezervacija.setSatRezervacijeKraj(timeRez);
     					}
     				} 
     				catch (ParseException e1) 
     				{
     					// TODO Auto-generated catch block
     					e1.printStackTrace();
     				}
     				
     				editovanaRezervacija.setSifNastavnik(DBExecuteNastavnik.getSifNastavnikByNameAndSurname(imePrezime[0],imePrezime[1]));
     				editovanaRezervacija.setSifPredmet(DBExecutePredmet.getPredmetByName((String)model.getValueAt(row, 5)).getSifPredmet());
     				editovanaRezervacija.setSifSala(DBExecuteSala.getSalaByName((String)model.getValueAt(row, 6)).getSifSala());
     				editovanaRezervacija.setTipRezervacije((String)model.getValueAt(row, 2));
     				editovanaRezervacija.setSifRezervacija(Integer.parseInt((String)model.getValueAt(row, 0)));
     				
     				DBExecuteRezervacija.updateRezervacija(editovanaRezervacija);
     			}
     		}
        	 
        	 catch (SQLException e1) 
             {
     			// TODO Auto-generated catch block
     			e1.printStackTrace();
     		}
        }
       
        dolazimSaDelete = false;
    }
	private void popuniTabeluStudentima() throws SQLException{
		modelRezervacija =  (MyTableModel)tableRezervacija.getModel();

		PomocneF.resetTable(modelRezervacija);

		DBExecuteRezervacija.getRezervacije();

		ArrayList<Rezervacija_> rezervacije = new ArrayList<Rezervacija_>();

		rezervacije = Rezervacija.rezervacijaLista;

		for (int i = 0; i < rezervacije.size(); i++) {
			Rezervacija_ rezervacija = new Rezervacija_();

			rezervacija = rezervacije.get(i);

			String sifRezervacije = String.valueOf(rezervacija.getSifRezervacija());
			String nazivPredmeta = DBExecutePredmet.getPredmetBySifra(rezervacija.getSifPredmet()).getNazPredmet();
			String nazivSale = DBExecuteSala.getSalaBySifra(rezervacija.getSifSala()).getNazivSala();
			Nastavnik_ nastavnik = DBExecuteNastavnik.getNastavnikBySifra(rezervacija.getSifNastavnik());
			
			String nazivNastavnik = nastavnik.getImeNastavnik() + " " + nastavnik.getPrezNastavnik();

			modelRezervacija.addRow(new Object[]{sifRezervacije, rezervacija.getDatumRezervacija(), rezervacija.getTipRezervacije(), rezervacija.getSatRezervacije(), rezervacija.getSatRezervacijeKraj(), nazivPredmeta, nazivSale, nazivNastavnik.equals(" ") ?  "Prodekan" : nazivNastavnik});
		}
		
	}
}

class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final JSpinner spinner = new JSpinner();

	  public SpinnerEditor(String[] items) {
	    spinner.setModel(new SpinnerListModel(Arrays.asList(items)));
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
	      int row, int column) 
	  {
		  DateFormat df = new SimpleDateFormat("HH:mm:ss");
		  if (value.getClass().equals(java.sql.Time.class))
		  {
			  String text = df.format(value);
			  setValueOnSpiner(text);
		  }
		  else
		  {
			  setValueOnSpiner(value);
		  }
		 
		  return spinner;
	  }
	  
	  private void setValueOnSpiner(Object value)
	  {
		  try
		  {
			  spinner.setValue(value);
		  }
		  catch(java.lang.IllegalArgumentException e)
		  {
			  System.out.println("Unesena vrijednost nije definisana u JSpinner");
		  }
	  }

	  public boolean isCellEditable(EventObject evt) {
	    if (evt instanceof MouseEvent) {
	      return ((MouseEvent) evt).getClickCount() >= 2;
	    }
	    return true;
	  }

	  public Object getCellEditorValue() {
	    return spinner.getValue();
	  }
	}