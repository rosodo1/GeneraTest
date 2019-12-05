package igu;

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
import generic.ColoredTableCellRenderer;

public class PanelTablas extends JPanel{
	
	private static final long serialVersionUID = -852307365427694889L;
    private Dimension ventana;
    private static JTable TableAsignatura;
    private static JTable TableDominio;
    private static JTable TableMateria;
    private static JTable TableTest;
    private AsignaturaTable Asignaturas;
    private MateriaTable Materias;
    private DominioTable Dominios;
    private TestTable Tests;
    private JScrollPane PaneAsignatura = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JScrollPane PaneDominio = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JScrollPane PaneMateria = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private JScrollPane PaneTest = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	public PanelTablas(){
        int inset = 0;
        ventana = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, ventana.width - inset*2, 230);
		setLayout(null);
		PintaAsignaturas();
		PintaMaterias(new int[0]);
		PintaDominios(new int[0]);
		PintaTest();
		setVisible(true);
	}

	public void PintaAsignaturas(){
		Asignaturas = new AsignaturaTable();
		TableAsignatura = new JTable();
		TableAsignatura.setAutoCreateColumnsFromModel(false);
		TableAsignatura.setModel(Asignaturas); 
		TableAsignaturaListener();
		for (int k = 0; k < Asignaturas.getM_columns().length; k++) {
	      DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
	      renderer.setHorizontalAlignment(Asignaturas.getM_columns()[k].m_alignment);
	      TableColumn column = new TableColumn(k, Asignaturas.getM_columns()[k].m_width, renderer, null);
	      TableAsignatura.addColumn(column);   
	    }
	    JTableHeader header = TableAsignatura.getTableHeader();
	    header.setUpdateTableInRealTime(true);
	    header.addMouseListener(Asignaturas.new ColumnListener(TableAsignatura));
	    header.setReorderingAllowed(true);
		getTL("Asignaturas", 20, 20, 200, 200, this, TableAsignatura, PaneAsignatura);
	}

	public void PintaMaterias(int[] asig){
		Materias = new MateriaTable(asig);
		TableMateria = new JTable();
		TableMateria.setAutoCreateColumnsFromModel(false);
		TableMateria.setModel(Materias); 
		TableMateriasListener();
	    for (int k = 0; k < Materias.getM_columns().length; k++) {
	      DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
	      renderer.setHorizontalAlignment(Materias.getM_columns()[k].m_alignment);
	      TableColumn column = new TableColumn(k, Materias.getM_columns()[k].m_width, renderer, null);
	      TableMateria.addColumn(column);   
	    }
	    JTableHeader header = TableMateria.getTableHeader();
	    header.setUpdateTableInRealTime(true);
	    header.addMouseListener(Materias.new ColumnListener(TableMateria));
	    header.setReorderingAllowed(true);
	    getTL("Materias", 240, 20, 200, 200, this, TableMateria, PaneMateria);		
	}
	
	public void PintaDominios(int[] materias){
		Dominios = new DominioTable(materias);
		TableDominio = new JTable();
		TableDominio.setAutoCreateColumnsFromModel(false);
		TableDominio.setModel(Dominios); 
		TableDominiosListener();
	    for (int k = 0; k < Dominios.getM_columns().length; k++) {
	      DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
	      renderer.setHorizontalAlignment(Dominios.getM_columns()[k].m_alignment);
	      TableColumn column = new TableColumn(k, Dominios.getM_columns()[k].m_width, renderer, null);
	      TableDominio.addColumn(column);   
	    }
	    JTableHeader header = TableDominio.getTableHeader();
	    header.setUpdateTableInRealTime(true);
	    header.addMouseListener(Dominios.new ColumnListener(TableDominio));
	    header.setReorderingAllowed(true);
		getTL("Dominios", 460, 20, 200, 200, this, TableDominio, PaneDominio);
	}

	/*public void PintaDominios(int id){
		Dominios = new DominioTable(id);
		TableDominio = new JTable();
		TableDominio.setAutoCreateColumnsFromModel(false);
		TableDominio.setModel(Dominios); 
		TableDominiosListener();
	    for (int k = 0; k < Dominios.getM_columns().length; k++) {
	      DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
	      renderer.setHorizontalAlignment(Dominios.getM_columns()[k].m_alignment);
	      TableColumn column = new TableColumn(k, Dominios.getM_columns()[k].m_width, renderer, null);
	      TableDominio.addColumn(column);   
	    }

	    JTableHeader header = TableDominio.getTableHeader();
	    header.setUpdateTableInRealTime(true);
	    header.addMouseListener(Dominios.new ColumnListener(TableDominio));
	    header.setReorderingAllowed(true);
		getTL("Dominios", 460, 20, 200, 200, this, TableDominio, PaneDominio);
	}*/
	
	public void PintaTest(){
		Tests = new TestTable();
		TableTest = new JTable();
		TableTest.setAutoCreateColumnsFromModel(false);
		TableTest.setModel(Tests); 
		TableTestsListener();
	    for (int k = 0; k < Tests.getM_columns().length; k++) {
	      DefaultTableCellRenderer renderer = new ColoredTableCellRenderer();
	      renderer.setHorizontalAlignment(Tests.getM_columns()[k].m_alignment);
	      TableColumn column = new TableColumn(k, Tests.getM_columns()[k].m_width, renderer, null);
	      TableTest.addColumn(column);   
	    }

	    JTableHeader header = TableTest.getTableHeader();
	    header.setUpdateTableInRealTime(true);
	    header.addMouseListener(Tests.new ColumnListener(TableTest));
	    header.setReorderingAllowed(true);

		getTL("Test Realizados", 680, 20, 200, 200, this, TableTest, PaneTest);
	}

	public void getTL(String label, int x, int y, int w, int h, JPanel panel, JTable TM, JScrollPane SP){
		JLabel LAsig = new JLabel(label);
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
	
	public static int[] getSeleccionA(){
		int[] a;
		try{
			a = TableAsignatura.getSelectedRows();
			for (int i = 0; i<a.length;i++){
				a[i] = (Integer)TableAsignatura.getValueAt(a[i],0);
			}
		}
		catch(NumberFormatException e){a = new int[0];}
		return a;
	}
	
	public static int[] getSeleccionM(){
		int[] a;
		try{
			a = TableMateria.getSelectedRows();
			for (int i = 0; i<a.length;i++){
				a[i] = (Integer)TableMateria.getValueAt(a[i],0);
			}
		}
		catch(NumberFormatException e){a = new int[0];}
		return a;
	}
	
	public static int[] getSeleccionD(){
		int[] a;
		try{
			a= TableDominio.getSelectedRows();
			for (int i = 0; i<a.length;i++){
				a[i] = (Integer)TableDominio.getValueAt(a[i],0);
			}
		}
		catch(NumberFormatException e){a = new int[0];}
		return a;
	}

    public static int[] getSeleccionE(){
		int[] a;
		try{
			a = PanelTablas.getTableTest().getSelectedRows();
			for (int i=0;i<a.length;i++){
				a[i] = (Integer)PanelTablas.getTableTest().getValueAt(a[i], 0);
			}
		}
		catch(NumberFormatException e){a = new int[0];}
		return a;
	}

	public void TableAsignaturaListener(){
		ListSelectionModel rowSM1 = TableAsignatura.getSelectionModel();
        rowSM1.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent Le1){
                if (Le1.getValueIsAdjusting()) return;
                ListSelectionModel lsm1 = (ListSelectionModel)Le1.getSource();
                if (lsm1.isSelectionEmpty()==false){
                    try{
                        int[] asignaturas = getSeleccionA();
                        int[] materias = getSeleccionM();
                        Materias.getM_vector().removeAllElements();
                        PanelEstadisticas.getMTotal().getM_vector().removeAllElements();
                        PanelEstadisticas.getMTest().getM_vector().removeAllElements();
                        PintaMaterias(asignaturas);
                        RunMe.jf.getP_Est().PintaTotales(asignaturas, materias);
                        RunMe.jf.getP_Est().PintaRealizadas(asignaturas, materias);
                    	}
                    catch (Exception e){}
                	}
            	}
        	});
	}

	public void TableMateriasListener(){
		ListSelectionModel rowSM2 = TableMateria.getSelectionModel();
        rowSM2.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent Le2){
                if (Le2.getValueIsAdjusting()) return;
                ListSelectionModel lsm2 = (ListSelectionModel)Le2.getSource();
                if (lsm2.isSelectionEmpty()==false){
                    try{
                    	/*String aux = model2.getValueAt(lsm2.getMinSelectionIndex(), 0).toString();
                        int clave = F._cstoi(aux);*/
                        int[] asignaturas = getSeleccionA();
                        int[] materias = getSeleccionM();
                        Dominios.getM_vector().removeAllElements();
                        PanelEstadisticas.getMTotal().getM_vector().removeAllElements();
                        PanelEstadisticas.getMTest().getM_vector().removeAllElements();
                        PintaDominios(materias);
                        RunMe.jf.getP_Est().PintaTotales(asignaturas, materias);
                        RunMe.jf.getP_Est().PintaRealizadas(asignaturas, materias);
                    	}
                    catch (Exception e){}
                	}
            	}
        	});
	}

	public void TableDominiosListener(){
		ListSelectionModel rowSM3 = TableDominio.getSelectionModel();
        rowSM3.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent Le3){
                if (Le3.getValueIsAdjusting()) return;
                ListSelectionModel lsm3 = (ListSelectionModel)Le3.getSource();
                if (lsm3.isSelectionEmpty()==false){
                	//Fila seleccionada
                    //TableModel model3 = TableDominio.getModel();
                    try{
                    	//String aux = model3.getValueAt(lsm3.getMinSelectionIndex(), 0).toString();
                    	}
                    catch (Exception e){
                    	//System.out.println(model.getColumnClass(0));
                    	}
                	}
            	}
        	});
	}

	public void TableTestsListener(){
		ListSelectionModel rowSM2 = TableTest.getSelectionModel();
        rowSM2.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent Le2){
                if (Le2.getValueIsAdjusting()) return;
                ListSelectionModel lsm2 = (ListSelectionModel)Le2.getSource();
                if (lsm2.isSelectionEmpty()==false){
                	//Fila seleccionada
                    //TableModel model2 = TableTest.getModel();
                    try{
                        //int examen = getSeleccionE()[0];
                    	int[] examenes = getSeleccionE();
                        PanelEstadisticas.getMTest().getM_vector().removeAllElements();
                        RunMe.jf.getP_Est().PintaTest(examenes);
                    	}
                    catch (Exception e){
                    	//System.out.println(model.getColumnClass(0));
                    	}
                	}
            	}
        	});
	}

	public static int getMateriasLength(){
		return TableMateria.getRowCount();
	}

	public static JTable getTableAsignatura() {
		return TableAsignatura;
	}

	public static JTable getTableDominio() {
		return TableDominio;
	}

	public static JTable getTableMateria() {
		return TableMateria;
	}

	public static JTable getTableTest() {
		return TableTest;
	}

	public static void setTableTest(JTable tableTest) {
		TableTest = tableTest;
	}
	
	public static int[] getSelectedTemas(){
		int[] sol;
		if (TableDominio.getSelectedRowCount() == 0){
			sol = new int[TableDominio.getRowCount()];
		}
		else{
			sol = TableDominio.getSelectedRows();
		}
		return sol;	
	}
	
	public static int[] getSelectedMaterias(){
		int[] sol;
		if (TableMateria.getSelectedRowCount() > 0){
			sol = TableMateria.getSelectedRows();
			for (int i = 0; i < sol.length;i++)
				sol[i] = (Integer)TableMateria.getValueAt(sol[i], 0);
		}
		else{
			sol = new int[TableMateria.getRowCount()];
			for (int i = 0; i < sol.length;i++)
				sol[i] = (Integer)TableMateria.getValueAt(i, 0);
		}
		return sol;	
	}
	
}
