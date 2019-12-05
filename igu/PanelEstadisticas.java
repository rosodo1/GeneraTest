package igu;

import generic.ColoredTableCellRenderer;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import bd.bbdd;


public class PanelEstadisticas extends JPanel{

	private static final long serialVersionUID = -8703551519088371874L;
    private Dimension ventana;
    private static JTable TableTotal;
    private static JTable TableRealizadas;
    private static JTable TableTest;
    private static TemasTable MTotal;
    private static TemasTable MRealizadas;
    private static TemasTable MTest;
    private static JScrollPane PaneTotal = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private static JScrollPane PaneRealizadas = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private static JScrollPane PaneTest = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private static JLabel LTotal = new JLabel();
    private static JLabel LRealizadas = new JLabel();
    private static JLabel LTest = new JLabel();

	public PanelEstadisticas(){
        int inset = 0;
        ventana = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(inset, 420, ventana.width - inset*2, 230);
		setLayout(null);
		Estadisticas("");
		setVisible(true);
	}
	
	public void Estadisticas(String where){
		PintaTotales(where);
		PintaRealizadas(where);
		PintaTest();
	}
	
	public void PintaTotales(int[] a, int[] m){
		String where="";
		if (a.length>0)
			{where = where + "and (";} 
		for (int i=0; i <a.length; i++){
			where = where + "(m.asignatura="+a[i]+") ";
			if ((i+1)< a.length){where = where + "or ";}
		}
		if (a.length>0)
			{where = where + ") ";} 
		if (m.length>0)
			{where = where + "and (";} 
		for (int i=0; i < m.length; i++){
			where = where + "(p.materia="+m[i]+") ";
			if ((i+1) < m.length){where = where + "or ";}
		}
		if (m.length>0)
			{where = where + ") ";} 
		PintaTotales(where);
	}
	
	public void PintaTotales(String where){
		MTotal = new TemasTable(where, 0);
		TableTotal = new JTable();
		TableTotal.setAutoCreateColumnsFromModel(false);
		TableTotal.setModel(MTotal); 
		TableTotalListener();
		for (int k = 0; k < MTotal.getM_columns().length; k++) {
	      DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
	      renderer.setHorizontalAlignment(MTotal.getM_columns()[k].m_alignment);
	      TableColumn column = new TableColumn(k, MTotal.getM_columns()[k].m_width, renderer, null);
	      TableTotal.addColumn(column);   
	    }
	    JTableHeader header = TableTotal.getTableHeader();
	    header.setUpdateTableInRealTime(true);
	    header.addMouseListener(MTotal.new ColumnListener(TableTotal));
	    header.setReorderingAllowed(true);
		getTL(LTotal, "Preguntas en la BD: " + bbdd.getNumPreguntasBBDD(where), 20, 20, 200, 200, this, TableTotal, PaneTotal);
	}
	
	public void PintaRealizadas(String where){
		//MRealizadas = new TemasTable(1); 
		MRealizadas = new TemasTable(where, 1); 
		TableRealizadas = new JTable();
		TableRealizadas.setAutoCreateColumnsFromModel(false);
		TableRealizadas.setModel(MRealizadas); 
		TableRealizadasListener();
		for (int k = 0; k < MRealizadas.getM_columns().length; k++) {
	      DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
	      renderer.setHorizontalAlignment(MRealizadas.getM_columns()[k].m_alignment);
	      TableColumn column = new TableColumn(k, MRealizadas.getM_columns()[k].m_width, renderer, null);
	      TableRealizadas.addColumn(column);   
	    }
	    JTableHeader header = TableRealizadas.getTableHeader();
	    header.setUpdateTableInRealTime(true);
	    header.addMouseListener(MRealizadas.new ColumnListener(TableRealizadas));
	    header.setReorderingAllowed(true);
		getTL(LRealizadas, "Preguntas realizadas: " + bbdd.getNumPreguntasMostradas(where), 240, 20, 200, 200, this, TableRealizadas, PaneRealizadas);
	}
	
	public void PintaRealizadas(int[] a, int[] m){
		String where="";
		for (int i : a){
			where = "and (m.asignatura="+i+") ";
		}
		for (int i : m){
			where = "and (p.materia="+i+") ";
		}
		PintaRealizadas(where);
	}

	public void PintaRealizadasTest(int id){
		MRealizadas = new TemasTable("", 1);
		TableRealizadas = new JTable();
		TableRealizadas.setAutoCreateColumnsFromModel(false);
		TableRealizadas.setModel(MRealizadas); 
		TableRealizadasListener();
		for (int k = 0; k < MRealizadas.getM_columns().length; k++) {
	      DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
	      renderer.setHorizontalAlignment(MRealizadas.getM_columns()[k].m_alignment);
	      TableColumn column = new TableColumn(k, MRealizadas.getM_columns()[k].m_width, renderer, null);
	      TableRealizadas.addColumn(column);   
	    }
	    JTableHeader header = TableRealizadas.getTableHeader();
	    header.setUpdateTableInRealTime(true);
	    header.addMouseListener(MRealizadas.new ColumnListener(TableRealizadas));
	    header.setReorderingAllowed(true);
		getTL(LRealizadas, "Preguntas realizadas: " + bbdd.getNumPreguntasMostradas(), 240, 20, 200, 200, this, TableRealizadas, PaneRealizadas);
	}

	public void PintaTest(){
		MTest = new TemasTable(2, 0);
		TableTest = new JTable();
		TableTest.setAutoCreateColumnsFromModel(false);
		TableTest.setModel(MTest); 
		for (int k = 0; k < MTest.getM_columns().length; k++) {
	      DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
	      renderer.setHorizontalAlignment(MTest.getM_columns()[k].m_alignment);
	      TableColumn column = new TableColumn(k, MTest.getM_columns()[k].m_width, renderer, null);
	      TableTest.addColumn(column);   
	    }
	    JTableHeader header = TableTest.getTableHeader();
	    header.setUpdateTableInRealTime(true);
	    header.addMouseListener(MTest.new ColumnListener(TableTest));
	    header.setReorderingAllowed(true);
		getTL(LTest, "Preguntas en Este Test: " + bbdd.getNumPreguntasTest(0), 460, 20, 200, 200, this, TableTest, PaneTest);
	}

	public void PintaTest(String where){
		MTest = new TemasTable(where, 2);
		TableTest = new JTable();
		TableTest.setAutoCreateColumnsFromModel(false);
		TableTest.setModel(MTest); 
		for (int k = 0; k < MTest.getM_columns().length; k++) {
	      DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
	      renderer.setHorizontalAlignment(MTest.getM_columns()[k].m_alignment);
	      TableColumn column = new TableColumn(k, MTest.getM_columns()[k].m_width, renderer, null);
	      TableTest.addColumn(column);   
	    }
	    JTableHeader header = TableTest.getTableHeader();
	    header.setUpdateTableInRealTime(true);
	    header.addMouseListener(MTest.new ColumnListener(TableTest));
	    header.setReorderingAllowed(true);
		getTL(LTest, "Preguntas en Este Test: " + bbdd.getNumPreguntasTest(where), 460, 20, 200, 200, this, TableTest, PaneTest);
	}
	
	public void PintaTest(int n){
		String where = "and (t.examen="+n+") ";
		PintaTest(where);
	}
	
	public void PintaTest(int[] examenes){
		String where="";
		for (int i : examenes){
			if (where != "") {where = where + " OR ";}
			where = where + "(t.examen="+i+") ";
		}
		if (where != ""){where = "AND ("+where+")";}
		PintaTest(where);
	}

	public void TableTotalListener(){
		ListSelectionModel rowSM2 = TableTotal.getSelectionModel();
        rowSM2.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent Le2){
                if (Le2.getValueIsAdjusting()) return;
                ListSelectionModel lsm2 = (ListSelectionModel)Le2.getSource();
                if (lsm2.isSelectionEmpty()==false){
                    try{}
                    catch (Exception e){}
                	}
            	}
        	});
	}

	public void TableRealizadasListener(){
		ListSelectionModel rowSM2 = TableRealizadas.getSelectionModel();
        rowSM2.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent Le2){
                if (Le2.getValueIsAdjusting()) return;
                ListSelectionModel lsm2 = (ListSelectionModel)Le2.getSource();
                if (lsm2.isSelectionEmpty()==false){
                    try{}
                    catch (Exception e){}
                	}
            	}
        	});
	}

	public void TableTestListener(){
		ListSelectionModel rowSM2 = TableTest.getSelectionModel();
        rowSM2.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent Le2){
                if (Le2.getValueIsAdjusting()) return;
                ListSelectionModel lsm2 = (ListSelectionModel)Le2.getSource();
                if (lsm2.isSelectionEmpty()==false){
                    try{}
                    catch (Exception e){}
                	}
            	}
        	});
	}

	public void getTL(JLabel LAsig, String label, int x, int y, int w, int h, JPanel panel, JTable TM, JScrollPane SP){
		LAsig.setText(label);
		LAsig.setBounds(x, 0, w, 20);
		LAsig.setVisible(true);
		panel.add(LAsig);
		TM.setBounds(0, 20, w, h-20);
		TM.setVisible(true);
		SP.setViewportView(TM);
        SP.setBounds(x, y, w, h);
        SP.setVisible(true);
        this.add(SP);
	}

	public static JTable getTableRealizadas() {
		return TableRealizadas;
	}

	public static void setTableRealizadas(JTable tableRealizadas) {
		TableRealizadas = tableRealizadas;
	}

	public static JTable getTableTest() {
		return TableTest;
	}

	public static void setTableTest(JTable tableTest) {
		TableTest = tableTest;
	}

	public static JTable getTableTotal() {
		return TableTotal;
	}

	public static void setTableTotal(JTable tableTotal) {
		TableTotal = tableTotal;
	}

	public static JLabel getLRealizadas() {
		return LRealizadas;
	}

	public static void setLRealizadas(JLabel realizadas) {
		LRealizadas = realizadas;
	}

	public static JLabel getLTest() {
		return LTest;
	}

	public static void setLTest(JLabel test) {
		LTest = test;
	}

	public static JLabel getLTotal() {
		return LTotal;
	}

	public static void setLTotal(JLabel total) {
		LTotal = total;
	}

	public static TemasTable getMRealizadas() {
		return MRealizadas;
	}

	public static void setMRealizadas(TemasTable realizadas) {
		MRealizadas = realizadas;
	}

	public static TemasTable getMTest() {
		return MTest;
	}

	public static void setMTest(TemasTable test) {
		MTest = test;
	}

	public static TemasTable getMTotal() {
		return MTotal;
	}

	public static void setMTotal(TemasTable total) {
		MTotal = total;
	}

}
