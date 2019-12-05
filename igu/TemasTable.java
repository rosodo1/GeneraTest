package igu;

import generic.ColumnData;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


import bd.bbdd;


public class TemasTable extends AbstractTableModel {

	private static final long serialVersionUID = 2754179235490284352L;
	private int origen;
	private int examen;
	private int[] asignatura;
	private int materia;

	static final private ColumnData m_columns[] = {
		new ColumnData( "Dominio", 10, JLabel.RIGHT ),
		new ColumnData( "Preguntas", 10, JLabel.RIGHT )
	};

	protected Vector<Temas> m_vector;
	protected int m_sortCol = 0;
	protected boolean m_sortAsc = true;
	protected int m_result = 0;

	public TemasTable() {
		m_vector = new Vector<Temas>();
		origen = 0;
		//setDefaultData();
		this.retrieveData();
	}

	public TemasTable(String s, int o) {
		m_vector = new Vector<Temas>();
		this.retrieveData(s, o);
	}

	public TemasTable(int o) {
		m_vector = new Vector<Temas>();
		origen = o;
		//setDefaultData();
		this.retrieveData();
	}

	public TemasTable(int o, int e) {
		m_vector = new Vector<Temas>();
		asignatura = new int[0];
		origen = o;
		examen = e;
		//setDefaultData();
		this.retrieveData();
	}

	public TemasTable(int[] o, String m) {
		m_vector = new Vector<Temas>();
		asignatura = o;
		examen = 0;
		origen = 3;
		//setDefaultData();
		this.retrieveData();
	}
	
/*	public TemasTable(String m, int o) {
		m_vector = new Vector<Temas>();
		asignatura = new int[0];
		examen = 0;
		materia = o;
		origen = 4;
		//setDefaultData();
		this.retrieveData();
	}*/
	
	public ColumnData[] getM_columns() {
		return m_columns;
	}

	@SuppressWarnings("unchecked")
	public void setDefaultData() {
		m_vector.removeAllElements();
		Collections.sort(m_vector, new TemasComparator(m_sortCol, m_sortAsc));
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
		Temas row = (Temas)m_vector.elementAt(nRow);
		switch (nCol) {
		case 0: return row.getDominio();
		case 1: return row.getPreguntas();
		}
		return "";
	}

	public String getTitle() {
		return "Temas";
	}

	@SuppressWarnings("unchecked")
	public int retrieveData() {
		/*Thread runner = new Thread() {
			public void run() {*/
				try {
					ResultSet results = null;
					switch (origen) {
					case 0: results = bbdd.getRSDominiosPreguntas(); break;
					case 1: results = bbdd.getRSRealizadasPreguntas(); break;
					case 2: results = bbdd.getRSTestPreguntas(examen); break;
					case 3: results = bbdd.getRSAsignaturasPreguntas(asignatura); break;
					case 4: results = bbdd.getRSMateriasPreguntas(materia); break;
					}
					boolean hasData = false;
					while (results.next()) {
						if (!hasData) {
							m_vector.removeAllElements();
							hasData = true;
						}
						int dom = results.getInt(1);
						int preg = results.getInt(2);
						m_vector.addElement(new Temas(dom, preg));
					}
					results.close();
					if (!hasData)
						m_result = 1;
				}
				catch (Exception e) {
					System.err.println("Error en la captura de datos");
					m_result = -1;
				}
				Collections.sort(m_vector, new TemasComparator(m_sortCol, m_sortAsc));
				m_result = 0;
			/*}
		};
		runner.start();*/
		return m_result;
	}

	@SuppressWarnings("unchecked")
	public int retrieveData(String s, int o) {
		/*Thread runner = new Thread() {
			public void run() {*/
				try {
					ResultSet results = null;
					switch (o) {
					case 0: results = bbdd.getRSDominiosPreguntas(s); break;
					case 1: results = bbdd.getRSDominiosPreguntasRealizadas(s); break;
					case 2: results = bbdd.getRSTestPreguntas(s); break;
					}
					boolean hasData = false;
					while (results.next()) {
						if (!hasData) {
							m_vector.removeAllElements();
							hasData = true;
						}
						int dom = results.getInt(1);
						int preg = results.getInt(2);
						m_vector.addElement(new Temas(dom, preg));
					}
					results.close();
					if (!hasData)
						m_result = 1;
				}
				catch (Exception e) {
					System.err.println("Error en la captura de datos");
					m_result = -1;
				}
				Collections.sort(m_vector, new TemasComparator(m_sortCol, m_sortAsc));
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
			Collections.sort(m_vector, new TemasComparator(modelIndex, m_sortAsc));
			m_table.tableChanged(new TableModelEvent(TemasTable.this));
			m_table.repaint();
		}
	}

	public Vector<Temas> getM_vector() {
		return m_vector;
	}

	public void setM_vector(Vector<Temas> m_vector) {
		this.m_vector = m_vector;
	}

}
	
