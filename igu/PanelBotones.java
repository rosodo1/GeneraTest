package igu;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PanelBotones extends JPanel{

	private static final long serialVersionUID = -8703551519088371874L;
    private Dimension ventana;
    protected static final JButton[] botones = new JButton[6];
    private static String[] etiquetas = {"Generar Test", "Test Anterior", "Ingles", "Borrar Test", "Grabar", "Salir"};

	public PanelBotones(){
        int inset = 0;
        ventana = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(inset, 50, ventana.width - inset*2, 20);
		this.setVisible(false);
		this.setLayout(null);
		for (int i = 0; i< botones.length; i++){
			setButtonSize(i);
		}
		this.setVisible(true);
	}
	
	public void setButtonSize(int i){
		int x;
		botones[i] = new JButton(etiquetas[i]);
		if (i == 0)
			x = 5;
		else
			x = 5 + botones[i-1].getX() + botones[i-1].getWidth();
		botones[i].setBounds(x, 0, 130, 20);
		botones[i].addActionListener(new AccionesBotones());
		add(botones[i]);
	}

}
