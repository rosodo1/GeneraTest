package igu;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lib.F;


public class PanelInfoDominios extends JPanel{

	private static final long serialVersionUID = -8703551519088371874L;
    private Dimension ventana;
	private static JLabel lResumen = new JLabel("% de los Dominios. CISA 10, 15, 16, 14, 31, 14. CISM 23, 22, 17, 24, 14.");
	private static JLabel lInfo = new JLabel("Introduce los % de cada tema separados por ,:");
	private static JTextField cInfo = new JTextField("10, 15, 16, 14, 31, 14");

	public PanelInfoDominios(){
        int inset = 0;
        ventana = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(inset, 340, ventana.width - inset*2, 80);
		this.setVisible(false);
		this.setLayout(null);
		lResumen.setBounds(5, 5, 500, 20);
		add(lResumen);
		lInfo.setBounds(5, 25, 300, 20);
		add(lInfo);
		cInfo.setBounds(5, 45, 120, 20);
		add(cInfo);
		this.setVisible(true);
	}

	public static int getSumaPorcentajes(){
		int total = 0;
		int[] p = getArrayPorcentajes();
		for (int i=0; i<p.length;i++)
			{total = total + p[i];}
		return total;
	}

	public static int[] getArrayPorcentajes(){
		int [] p = F._cstovi(cInfo.getText(),',');
		return p;
	}
}
