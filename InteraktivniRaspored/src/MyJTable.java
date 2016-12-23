import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyJTable extends JTable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public TableCellRenderer getCellRenderer(int row, int column)
	 {
	        if ((column == 1))
	        {	
	            //return DateRenderer();
	        }
	        return super.getCellRenderer(row, column);
	 }

}
