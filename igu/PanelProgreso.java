package igu;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class PanelProgreso extends JPanel{

	private static final long serialVersionUID = -8703551519088371874L;
    private Dimension ventana;
	private static JLabel lTest = new JLabel("% de Generación del Test");
	private static JLabel lDocumento = new JLabel("% de Generación del Documento");
	private static JProgressBar PBTest = new JProgressBar(0, 100);
	private static JProgressBar PBDoc = new JProgressBar(0, 100);

	public PanelProgreso(){
        int inset = 0;
        ventana = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(inset, 0, ventana.width - inset*2, 50);
		this.setVisible(false);
		this.setLayout(null);
		lTest.setBounds(5, 5, 190, 20);
		add(lTest);
		lDocumento.setBounds(5, 25, 190, 20);
		add(lDocumento);
		PBTest.setBounds(200, 5, 200, 15);
		add(PBTest);
		PBDoc.setBounds(200, 25, 200, 15);
		add(PBDoc);
		this.setVisible(true);
	}
	
	public static void setProgresoTest(int valor){
		PBTest.setValue(valor);
	}

	public static void setMasProgresoTest(int valor){
		PBTest.setValue(PBTest.getValue()+valor);
	}

	public static void setMaxProgresoTest(int valor){
		PBTest.setMaximum(valor);
	}

	public static void setProgresoDoc(int valor){
		PBDoc.setValue(valor);
	}

	public static void setMasProgresoDoc(int valor){
		PBDoc.setValue(PBDoc.getValue()+valor);
	}

	public static void setMaxProgresoDoc(int valor){
		PBDoc.setMaximum(valor);
	}

}
