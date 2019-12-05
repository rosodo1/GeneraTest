package igu;

import generic.ColumnData;
import lib.F;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.Vector;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


import bd.bbdd;


public class TestTable extends AbstractTableModel {

	private static final long serialVersionUID = 2754179235490284352L;

	static final private ColumnData m_columns[] = {
		new ColumnData( "Id", 10, JLabel.RIGHT ),
		new ColumnData( "Fecha", 60, JLabel.RIGHT )
	};

	protected Vector<Test> m_vector;
	protected int m_sortCol = 0;
	protected boolean m_sortAsc = true;
	protected int m_result = 0;

	public TestTable() {
		m_vector = new Vector<Test>();
		//setDefaultData();
		this.retrieveData();
	}

	public ColumnData[] getM_columns() {
		return m_columns;
	}

	@SuppressWarnings("unchecked")
	public void setDefaultData() {
		m_vector.removeAllElements();
		Collections.sort(m_vector, new TestComparator(m_sortCol, m_sortAsc));
	}

	public int getRowCount() {
		return m_vector==null ? 0 : m_vector.size(); 
	}

	public int getColumnCount() {
		return m_columns.length; 
	} 

	public String getColumnName(int column) {
		String str = m_columns[column].m_title;
		if (column==m_sortCol)
			str += m_sortAsc ? ">>" : "<<";
			return str;
	}

	public boolean isCellEditable(int nRow, int nCol) {
		return false;
	}

	public Object getValueAt(int nRow, int nCol) {
		if (nRow < 0 || nRow>=getRowCount())
			return "";
		Test row = (Test)m_vector.elementAt(nRow);
		switch (nCol) {
		case 0: return row.getId();
		case 1: return row.getFecha();
		}
		return "";
	}

	public String getTitle() {
		return "Test";
	}

	@SuppressWarnings("unchecked")
	public int retrieveData() {
		/*Thread runner = new Thread() {
			public void run() {*/
				try {
					ResultSet results = bbdd.getRSTests();
					boolean hasData = false;
					while (results.next()) {
						if (!hasData) {
							m_vector.removeAllElements();
							hasData = true;
						}
						int cod = results.getInt(1);
						Date name = F._cstod(results.getString(2));
						m_vector.addElement(new Test(cod, name));
					}
					results.close();
					if (!hasData)
						m_result = 1;
				}
				catch (Exception e) {
					System.err.println("Error en la captura de datos:");
					m_result = -1;
				}
				Collections.sort(m_vector, new TestComparator(m_sortCol, m_sortAsc));
				m_result = 0;
			/*}
		};
		runner.start();*/
		return m_result;
	}

	class ColumnListener extends MouseAdapter{
		protected JTable m_table;

		public ColumnListener(JTable table) {
			m_table = table;
		}

		@SuppressWarnings("unchecked")
		public void mouseClicked(MouseEvent e) {
			TableColumnModel colModel = m_table.getColumnModel();
			int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
			int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();
			if (modelIndex < 0)
				return;
			if (m_sortCol==modelIndex)
				m_sortAsc = !m_sortAsc;
			else
				m_sortCol = modelIndex;
			for (int i=0; i < m_columns.length; i++) {
				TableColumn column = colModel.getColumn(i);
				column.setHeaderValue(getColumnName(column.getModelIndex()));
			}
			m_table.getTableHeader().repaint();
			Collections.sort(m_vector, new TestComparator(modelIndex, m_sortAsc));
			m_table.tableChanged(new TableModelEvent(TestTable.this));
			m_table.repaint();
		}
	}

	public Vector<Test> getM_vector() {
		return m_vector;
	}

	public void setM_vector(Vector<Test> m_vector) {
		this.m_vector = m_vector;
	}

}
	
