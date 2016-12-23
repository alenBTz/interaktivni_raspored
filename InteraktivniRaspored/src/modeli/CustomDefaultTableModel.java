package modeli;

import javax.swing.table.DefaultTableModel;
public class CustomDefaultTableModel extends DefaultTableModel{

	/**
	 * 
	 */
	
	public CustomDefaultTableModel(Object[][] rowData, String[] columnNames) {
		 super(rowData, columnNames);
	}

	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean isCellEditable(int row, int column) {
		
		return false;
	}
	
}
