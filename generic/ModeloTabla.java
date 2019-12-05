package generic;

import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloTabla implements TableModel 
{
private static final long serialVersionUID = 3028301428278348084L;
private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();
private String[] columnNames;
private Object[][] data;

public ModeloTabla(String[] c)
	{
	columnNames = c;
	data = new Object[1][columnNames.length];
	}

public ModeloTabla(Object[][] datos, String[] c)
	{
	columnNames = c;
	data = datos;
	}

public Object[][] getData()
	{
	return data;
	}

public void setData(Object[][] datos)
	{
	data = datos;
	}

public int getColumnCount() 
	{
	return columnNames.length;
	}

public int getRowCount() 
	{
	return data.length;
	}

public Object getValueAt(int row, int col) 
	{
	return data[row][col];
	}

public void addTableModelListener(TableModelListener l) 
	{
	listeners.add(l);
	}

@SuppressWarnings("unchecked")
public Class getColumnClass(int c) 
	{
	if (getValueAt(0, c) == null)
		{return "".getClass();}
	else
		{return getValueAt(0, c).getClass();}
	}

public String getColumnName(int col) 
	{
	return columnNames[col];
	}

public boolean isCellEditable(int row, int col) 
	{
	return false;
	/*if (col < 1) 
		{return false;} 
	else 
		{return true;}*/
	}

public void removeTableModelListener(TableModelListener l)
	{
	listeners.remove(l);
	}

public void setValueAt(Object value, int row, int col) 
	{
	data[row-1][col] = value;
	TableModelEvent evento = new TableModelEvent(this, row, row, col);
	avisaSuscriptores (evento);
	}
private void avisaSuscriptores (TableModelEvent evento)
	{
	int i;
	for (i=0; i<listeners.size(); i++)
		((TableModelListener)listeners.get(i)).tableChanged(evento);
	}

public String[] getColumnNames() {
	return columnNames;
}

public void setColumnNames(String[] columnNames) {
	this.columnNames = columnNames;
}
}	