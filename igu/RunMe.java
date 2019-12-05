package igu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import bd.ModosBBDD;
import bd.bbdd;
import lib.Config;

public class RunMe extends JFrame {
	private static final long serialVersionUID = 1L;
    private Dimension ventana;
    private JSplitPane Divisor1;
    private JPanel PanelSuperior = new JPanel();
    private JPanel PanelInferior = new JPanel();
    private PanelTablas MPanelTop;
    private PanelBotones p_Bot;
    private PanelInfoTest p_Info;
    private PanelInfoDominios p_Dom;
    private PanelEstadisticas p_Est;
    private PanelProgreso p_Pro;
	private static ModosBBDD ModoBD; // = ModosBBDD.MSACCESS; //ModosBBDD.MYSQL; //
	public static RunMe jf;
	private static String version = "1.2.1";
	private static Config config;
	
	public RunMe() {
        LeeConfig();
		Tablas();
        int inset = 0;
        ventana = Toolkit.getDefaultToolkit().getScreenSize();
		setLayout(null);
		PintaPaneles();
        setBounds(inset, inset, ventana.width - inset*2, ventana.height - inset*2);
        setTitle("Genera Test v"+RunMe.getVersion());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addWindowListener(new WindowAdapter()
            {
            public void windowClosing(WindowEvent e)
                {
                RunMe.cerrar();
                }
            });
        }
	
	public void LeeConfig(){
        config = new Config();
        config.cargar();
        ModoBD = config.getBbdd();
	}
	
	public static String getVersion(){
		return version;
	}
	
    public static void cerrar(){
    	AccionesBotones.GrabaIni();
    	jf.dispose();
    	System.exit(0);
    }

	public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
		jf = new RunMe();
	}

	public static void Tablas(){
		bbdd.CargaDriver(ModoBD);
		bbdd.getConexion(ModoBD, config.getBdsource(), config.getBduser(), config.getBdpass());
		bbdd.setSt1(bbdd.getStatement2(bbdd.con1));
	}

	public void PintaPaneles(){
		PanelSuperior.setVisible(false);
		PanelSuperior.setBounds(0, 0, ventana.width, 350);
		PanelSuperior.setLayout(null);
		PanelInferior.setVisible(false);
		PanelInferior.setBounds(0, 0, ventana.width, 350);
		PanelInferior.setLayout(null);
		MPanelTop = new PanelTablas();
		p_Info = new PanelInfoTest();
		p_Dom = new PanelInfoDominios();
		p_Est = new PanelEstadisticas();
		p_Pro = new PanelProgreso();
		p_Bot = new PanelBotones();
		PanelSuperior.add(MPanelTop);
		PanelSuperior.add(p_Info);
		PanelSuperior.add(p_Dom);
		PanelSuperior.add(p_Est);
		PanelInferior.add(p_Pro);
		PanelInferior.add(p_Bot);
		PanelSuperior.setVisible(true);
		PanelInferior.setVisible(true);
		Divisor1 = new PanelDivisor(PanelSuperior, PanelInferior, ventana.width, ventana.height, ventana.height-130);
		this.getContentPane().add(Divisor1);	
 	}

	public PanelEstadisticas getP_Est() {
		return p_Est;
	}

	public void setP_Est(PanelEstadisticas est) {
		p_Est = est;
	}

	public PanelTablas getMPanelTop() {
		return MPanelTop;
	}

	public void setMPanelTop(PanelTablas panelTop) {
		MPanelTop = panelTop;
	}

	public static ModosBBDD getModoBD() {
		return ModoBD;
	}

	public static Config getConfig() {
		return config;
	}

	public PanelInfoTest getP_Info() {
		return p_Info;
	}
}
