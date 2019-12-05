package igu;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lib.F;

public class PanelInfoTest extends JPanel{

	private static final long serialVersionUID = -8703551519088371874L;
    private Dimension ventana;
	private static JLabel lFecha = new JLabel("Fecha del TEST:");
	private static JLabel lPreguntas = new JLabel("Nº de Preguntas:");
	private static JLabel lRepetir = new JLabel("Repetir:");
	private static JLabel lPonderar = new JLabel("Sin Ponderar:");
	private static JTextField cFecha = new JTextField(F.FechaSimple(new Date()));
	private static JTextField cPreguntas = new JTextField("200");
	private static JCheckBox cRepetir = new JCheckBox();
	private static JCheckBox cPonderar = new JCheckBox();

	private static JLabel lImages = new JLabel("Directorio Imagenes:");
	private static JTextField cImages = new JTextField(RunMe.getConfig().getImages());
	private static JLabel lOutput = new JLabel("Directorio Test:");
	private static JTextField cOutput = new JTextField(RunMe.getConfig().getOutput());
	private static JLabel lbbdd = new JLabel("Motor BBDD:");
	private static JTextField cbbdd = new JTextField(RunMe.getConfig().getBbdd().toString());
	private static JLabel lSource = new JLabel("Base de Datos:");
	private static JTextField cSource = new JTextField(RunMe.getConfig().getBdsource());
	private static JLabel lUser = new JLabel("Usuario:");
	private static JTextField cUser = new JTextField(RunMe.getConfig().getBduser());
	private static JLabel lPass = new JLabel("Contraseña:");
	private static JTextField cPass = new JTextField(RunMe.getConfig().getBdpass());
	
	public PanelInfoTest(){
        int inset = 0;
        ventana = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(inset, 230, ventana.width - inset*2, 110);
		this.setVisible(false);
		this.setLayout(null);
		lFecha.setBounds(5, 5, 100, 20);
		add(lFecha);
		cFecha.setBounds(105, 5, 100, 20);
		add(cFecha);
		lPreguntas.setBounds(5, 25, 100, 20);
		add(lPreguntas);
		cPreguntas.setBounds(105, 25, 100, 20);
		add(cPreguntas);
		lRepetir.setBounds(5, 65, 100, 20);
		add(lRepetir);
		cRepetir.setBounds(105, 65, 100, 20);
		add(cRepetir);
		lPonderar.setBounds(5, 85, 100, 20);
		add(lPonderar);
		cPonderar.setBounds(105, 85, 100, 20);
		add(cPonderar);

		lImages.setBounds(305, 5, 100, 20);
		add(lImages);
		cImages.setBounds(405, 5, 300, 20);
		add(cImages);
		lOutput.setBounds(305, 25, 100, 20);
		add(lOutput);
		cOutput.setBounds(405, 25, 300, 20);
		add(cOutput);
		lbbdd.setBounds(505, 65, 100, 20);
		add(lbbdd);
		cbbdd.setBounds(605, 65, 100, 20);
		add(cbbdd);
		lSource.setBounds(505, 85, 100, 20);
		add(lSource);
		cSource.setBounds(605, 85, 100, 20);
		add(cSource);
		lUser.setBounds(305, 65, 100, 20);
		add(lUser);
		cUser.setBounds(405, 65, 100, 20);
		add(cUser);
		lPass.setBounds(305, 85, 100, 20);
		add(lPass);
		cPass.setBounds(405, 85, 100, 20);
		add(cPass);
		this.setVisible(true);
	}
	
	public static boolean getBoolPonderar(){
		boolean sol = false;
		sol = (cPonderar.isSelected() == true);
		return sol;
	}

	public static boolean getBoolRepetir(){
		boolean sol = false;
		sol = (cRepetir.isSelected() == true);
		return sol;
	}

	public static int getIntPreguntas(){
		int sol = 0;
		try {
			sol = F._cstoi(cPreguntas.getText());
		}
		catch (NumberFormatException e){
			sol = 0;
		}
		return sol;
	}

	public static String getStringFecha(){
		return cFecha.getText();
	}

	public static Date getDateFecha(){
		String sfecha = cFecha.getText();
		Date sol = new Date();
		try {
			sol = F._cstod(sfecha);
		}
		catch (Exception e){
			sol = new Date();
		}
		return sol;
	}

	public String getbbdd() {
		return cbbdd.getText();
	}

	public String getImages() {
		return cImages.getText();
	}

	public String getOutput() {
		return cOutput.getText();
	}

	public String getPass() {
		return cPass.getText();
	}

	public String getSource() {
		String x = cSource.getText(); 
		return x;
	}

	public String getUser() {
		return cUser.getText();
	}
}
