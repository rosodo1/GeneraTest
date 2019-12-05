package lib;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import igu.RunMe;

public class Html {

	private static String SetMyDir = RunMe.getConfig().getOutput();//"C:\\GeneraTest\\";
	private static String ImgDir = RunMe.getConfig().getImages();//"file:///C:/GeneraTest/images/";
	private static DataOutputStream WA;
	private static DataOutputStream WP;
	private static DataOutputStream WR;

	public static void wl(DataOutputStream dos, String s){
		try{
			dos.writeChars(s);
			dos.writeChars("\n");
		} catch (IOException e){
			F.Consola("Error al escribir en el fichero");
		}
	}

	public static void EscribeInicio(DataOutputStream dos, String titulo){
		wl(dos, "<html><head><meta http-equiv=Content-Type content='text/html; charset=windows-1252'>");
		wl(dos, "<title>"+titulo+"</title></head>");
		wl(dos, "<body lang=ES>");
	}

	public static void EscribeFin(DataOutputStream dos){
		wl(dos, "</body></html>");
	}

	public static void EscribeIniTabla(DataOutputStream dos){
		wl(dos, "<table border=0 cellspacing=0 cellpadding=0 style='border-collapse:collapse'>");
	}

	public static void EscribeFinTabla(DataOutputStream dos){
		wl(dos, "</table>");
	}

	public static void TablaCabecera(DataOutputStream dos){
		try{
			dos.writeChars("<th><tr><td><b>Nº</b></td><td><b>Sol</b></td><td><b>Dom</b></td></tr></th>");
		} catch (IOException e){
			F.Consola("Error escribiendo la cabecera.");
		}
	}

	public static void InsertaEnTabla(int orden, String solucion, String dominio){
		wl(WR, "<tr><td>"+orden+"</td><td>"+solucion+"</td><td>"+dominio+"</td></tr>");
	}

	public static void InsertaFoto(String foto){
		if (foto != null){
			wl(WA, "<p align='center'><img align='center' src='"+ ImgDir + foto + "' alt='"+foto+"'></p>");
			wl(WP, "<img src='"+ ImgDir + foto + "' alt='"+foto+"'>");
		}
	}

	public static void InsertaTexto(String texto, boolean bold){
		String cadena = "";
		//String cadena = "<p>";
		if (bold) cadena = cadena + "<b>";
		cadena = cadena + texto;
		if (bold) cadena = cadena + "</b>";
		//cadena = cadena + "</p>";
		wl(WA, cadena);
		wl(WP, cadena);
	}

	public static void InsertaTextoP(String texto, boolean bold, boolean color){
		String cadena = "";
		if (color) cadena = cadena + "<p style='color:#FF0000;'>";
		if (bold) cadena = cadena + "<b>";
		cadena = cadena + texto;
		if (bold) cadena = cadena + "</b>";
		if (color) cadena = cadena + "</p>";
		wl(WP, cadena);
	}

	public static void CreaObjetosHTML(int num){
		String fichero = "WA";
		try {
			WA = new DataOutputStream(new FileOutputStream(SetMyDir+"testalumnos"+num+".htm"));
			EscribeInicio(WA, "Test para Alumnos "+num);
			fichero = "WP";
			WP = new DataOutputStream(new FileOutputStream(SetMyDir+"testprofesor"+num+".htm"));
			EscribeInicio(WP, "Test para Profesor "+num);
			fichero = "WR";
			InsertaTexto("<OL>", false);
			WR = new DataOutputStream(new FileOutputStream(SetMyDir+"respuestas"+num+".htm"));
			EscribeInicio(WR, "Test de Respuestas "+num);
			EscribeIniTabla(WR);
			TablaCabecera(WR);
		} catch (IOException e) {
			F.Consola(fichero + " ha dado error al intentar crearlo.");
			e.printStackTrace();
		}

	}

	public static void CierraObjetos(){
		String fichero = "WA";
		try{
			InsertaTexto("</OL>", false);
			EscribeFin(WA);
			WA.close();
			fichero = "WP";
			EscribeFin(WP);
			WP.close();
			fichero = "WR";
			EscribeFinTabla(WR);
			EscribeFin(WR);
			WR.close();
		} catch (IOException e) {
			F.Consola(fichero + " ha dado error al intentar cerrarlo.");
			e.printStackTrace();
		}
	}
}
