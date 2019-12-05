package igu;

import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Random;
import java.util.HashMap;
import bd.bbdd;
import javax.swing.JTable;
import lib.F;
import lib.Html;
import lib.DPreguntas;
import lib.Config;
import bd.ModosBBDD;

public class AccionesBotones implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==PanelBotones.botones[0]){GeneraTest();}//Boton Generar Test
		if(e.getSource()==PanelBotones.botones[1]){TestAnterior();}//Boton Test Anterior
		if(e.getSource()==PanelBotones.botones[2]){TestIngles();}//Boton Ingles
		if(e.getSource()==PanelBotones.botones[3]){TestBorra();}//Boton Borrar Test
		if(e.getSource()==PanelBotones.botones[4]){TestGraba();}//Boton Grabar
		if(e.getSource()==PanelBotones.botones[5]){Salir();}//Boton Salir
	}

	public void Salir(){
		RunMe.cerrar();
	}
	
	public void TestGraba(){
		GrabaIni();
	}
	
	public static void GrabaIni(){
		String i = RunMe.jf.getP_Info().getImages();
		String o = RunMe.jf.getP_Info().getOutput();
		String p = RunMe.jf.getP_Info().getPass();
		String s = RunMe.jf.getP_Info().getSource();
		String u = RunMe.jf.getP_Info().getUser();
		String b = RunMe.jf.getP_Info().getbbdd();
		ModosBBDD mb = ModosBBDD.MSACCESS;
		if (b.equals("MYSQL")==true){mb = ModosBBDD.MYSQL;}
		if (b.equals("SQLSERVER")==true){mb = ModosBBDD.SQLSERVER;}
		Config x = new Config(i, o, mb, s, u, p);
		x.guardar();
	}
	
	public void TestAnterior(){
		int examen = 0;
		examen = examenSeleccionado();
		if (examen != 0)
			ImprimeTest(examen);
		else
			F.Consola("No hay examenes anteriores.");
	}
	
	public void TestIngles(){
		int examen = 0;
		examen = examenSeleccionado();
		if (examen != 0)
			ImprimeTestIngles(examen);
		else
			F.Consola("No hay examenes anteriores.");
	}
			
	public void TestBorra(){
		int[] examenes = PanelTablas.getSeleccionE();
		if (examenes.length==0){
			F.Consola("No se han seleccionado examenes.");
			}
		for (int i : examenes){
			if (i != 0)
				bbdd.setTestBorra(i);
			else
				F.Consola("No hay examen para borrar.");
		}
		RunMe.jf.getMPanelTop().PintaTest();
		RunMe.jf.getP_Est().Estadisticas("");
	}
	
	public void GeneraTest(){
		JTable jtable = PanelTablas.getTableDominio();
		int v[];
		DPreguntas x;
		int temasSelect = jtable.getSelectedRowCount();
		int[] dominios_Sel; //Posiciones de los temas seleccionados
		int[] dominios; //Numeros de los temas que aparecen en el test
		if (temasSelect==0){
			dominios = new int[jtable.getRowCount()];
			dominios_Sel = new int[jtable.getRowCount()];
			for(int c=0; c<jtable.getRowCount();c++){
				dominios[c] = (Integer)jtable.getValueAt(c, 0);
				dominios_Sel[c] = c;
			}
		}
		else{
			dominios = new int[temasSelect];
			dominios_Sel = jtable.getSelectedRows();
			for (int c = 0; c< dominios.length; c++){
				dominios[c] = (Integer)jtable.getValueAt(dominios_Sel[c], 0);
			}
		}
		int capitulos = dominios.length; // Numero de Temas que apareceran en el test. Son o los seleccionados o todos.
		boolean repetir = PanelInfoTest.getBoolRepetir(); // Se pueden repetir preguntas?
		boolean ponderar = PanelInfoTest.getBoolPonderar(); //El numero de preguntas esta ponderado por temas?
		int[] aPorcentajes = PanelInfoDominios.getArrayPorcentajes(); //vector con el % de preguntas por tema
		//int[] ad = new int[capitulos]; //Numero de preguntas a emplear por dominio.
		HashMap<Integer, DPreguntas> hsad = new HashMap<Integer, DPreguntas>();
		int[] VMateriasB; //?????
		int TP = 0; //Total Preguntas
		int npreg;
		int examen = 0;
		int aleatoria;
		int marcar = 0;
		int auxdom;
		//int npreguntas;
		String soluciones;
		int disponibles = 0; //Numero de preguntas disponibles
		String CDominio = ""; //?????
		Random aleatorios = new Random(); //Semilla para buscar preguntas aleatorias
		int ntest = PanelInfoTest.getIntPreguntas(); //Numero de Preguntas introducidas en el formulario
		PanelProgreso.setMasProgresoTest(0);
		PanelProgreso.setMasProgresoDoc(0);
		PanelProgreso.setMaxProgresoTest(ntest);
		PanelProgreso.setMaxProgresoDoc(ntest);
		
		VMateriasB = PanelTablas.getSelectedMaterias(); //Array con el numero de los temas marcados.
		int max = Math.min(dominios.length, aPorcentajes.length);
		if (!ponderar){
			for (int p = 0; p < max; p++){
				TP = TP + aPorcentajes[dominios_Sel[p]];
			}
		}
		if (TP > 0){
			CDominio = "";
			for (int p = 1; p <= max;p++){
				int t_num = (int)((ntest*aPorcentajes[p-1])/new Double(TP));
				x = new DPreguntas(dominios[p-1], 0, t_num);
				hsad.put(new Integer(dominios[p-1]), x);
				int tenemos = PregEnTabla(dominios[p-1]);
				if (t_num > tenemos){
					//F.Consola("Necesitamos "+t_num+" y tenemos "+tenemos);
					CDominio = CDominio + p+" ";
					}
				}
			}
		//Realiza el SQL para comprobar el numero de preguntas disponibles.
		disponibles = bbdd.getDisponibles(repetir, ponderar, capitulos, hsad, VMateriasB, true)-1;
		npreg = 1;
		//Comprueba que tenemos disponibles
		//CDominio son dominios de los que no tenemos bastantes preguntas
		if ((disponibles < ntest) || (CDominio != "")){ 
			F.Consola("Necesito mas preguntas para realizar este test de los dominios "+CDominio);
		}
		else{
			//Obtenemos el numero de Examen Creado
			examen = bbdd.setNewExam(PanelInfoTest.getStringFecha());
			//Obtenemos Todas las preguntas disponibles para marcar una al azar
			while (npreg <= ntest){
				aleatoria = aleatorios.nextInt(disponibles)+1;
				//F.Consola("Disponibles: "+disponibles+". Aleatoria: "+aleatoria+".");
				v = bbdd.moveBy(aleatoria, repetir, ponderar, capitulos, hsad, VMateriasB);
				marcar = v[0];
				auxdom = v[1];
				if (TP > 0){
					x = new DPreguntas(auxdom, hsad.get(auxdom).getHechas()+1, hsad.get(auxdom).getPorhacer()-1);
					hsad.put(auxdom, x);
					//PanelEstadisticas.getTableRealizadas().setValueAt((Integer)PanelEstadisticas.getTableRealizadas().getValueAt(auxdom, 1)+1, auxdom, 1);
					//PanelEstadisticas.getTableTest().setValueAt((Integer)PanelEstadisticas.getTableTest().getValueAt(auxdom, 1)+1, auxdom, 1);
				}
				//Inserta Pregunta en Preguntas Empleadas
				//soluciones = abcd4();
				soluciones = F.respuestasAleatorias(bbdd.getNumRespuestas(bbdd.getRs1()));
				bbdd.setMostrada(examen, npreg, marcar, soluciones);
				disponibles = bbdd.getDisponibles(repetir, ponderar, capitulos, hsad, VMateriasB, true)-1;
				PanelProgreso.setProgresoTest(npreg);
				PanelEstadisticas.getLTest().setText("En este Test: "+npreg);
				npreg++;
			}
		RunMe.jf.getMPanelTop().PintaTest();
		RunMe.jf.getP_Est().PintaTest(examen);
		RunMe.jf.getP_Est().PintaRealizadas(PanelTablas.getSeleccionA(), VMateriasB);
		ImprimeTest(examen);
		}
	}
	
	/**
	 * PregEnTabla. Devuelve el numero de preguntas que aun no se han utilizado del dominio que se le indica.
	 * @param int dominio
	 * @return preg preguntas no realizadas
	 */
	public int PregEnTabla(int dom){
		int preg = 0;
		int i = 0, j = 0;
		boolean cond = true; 
		while ((cond==true)&&(i<PanelEstadisticas.getTableTotal().getRowCount())){
			//.getTableRealizadas().getRowCount())){
			//for (int i = 1; i<PanelEstadisticas.getTableRealizadas().getRowCount();i++){
			int h = (Integer)PanelEstadisticas.getTableTotal().getValueAt(i, 0);
			if (dom == h){
				preg = (Integer)PanelEstadisticas.getTableTotal().getValueAt(i, 1);
				while ((cond==true)&&(j<PanelEstadisticas.getTableRealizadas().getRowCount())){
					int d = (Integer)PanelEstadisticas.getTableRealizadas().getValueAt(i, 0);
					if (dom == d){
						preg = preg - (Integer)PanelEstadisticas.getTableRealizadas().getValueAt(i, 1);
					}
					j++;
				}
				cond = false;
			}
			i++;
		}
		return preg;
	}
	
	public void ImprimeTest(int examen){
		String explicacion, foto, pregunta, soluciones, LaSoluciones;
		int Dominio, ntest, orden, respuestas; //newpos, preguntas 
		String[] Sol;
		String SLista;
		int[] Arreglo;
		PanelProgreso.setProgresoDoc(0);
		ntest = bbdd.getNumPreguntasTest(examen);
		PanelProgreso.setMasProgresoDoc(ntest);
		ResultSet rs = bbdd.getRSTest(examen);
		try{
		    Html.CreaObjetosHTML(examen);
			while (rs.next()){
		        //Campos BBDD
		        soluciones = rs.getString("soluciones");
		        if (soluciones != null)
		        	respuestas = soluciones.length();
		        else
		        	respuestas = 0;
		        Sol = new String[respuestas];
		        for (int p = 1; p<=respuestas; p++){
		        	Sol[p-1] = rs.getString("resp"+soluciones.charAt(p-1));
		        	Sol[p-1] = F.toHtml(Sol[p-1]);
		        }
		        foto = rs.getString("file");
		        orden = rs.getInt("orden");
		        SLista = rs.getString("solucion");
		        pregunta = rs.getString("pregunta");
		        Dominio = rs.getInt("dominio");
		        pregunta = F.toHtml(pregunta);
		        explicacion = rs.getString("explicacion");
		        if (explicacion == null){explicacion = "";}
		        explicacion = F.toHtml(explicacion);
		        //newpos = inttoabcd(rs.getInt("solucion")).indexOf(soluciones);
		        Arreglo = F._cstovi(SLista, ',');
		        //newpos := Pos(inttoaz(DM.SQL.FieldValues['solucion']), soluciones);
		        LaSoluciones = "";
		        for (int p=1; p<=Arreglo.length;p++){
		            LaSoluciones = LaSoluciones + inttoaz((soluciones.indexOf(inttoaz(Arreglo[p-1]))+1));
		            if (p < Arreglo.length){
		            	LaSoluciones = LaSoluciones + ",";
		            }
		        }
		        //Respuestas
		        Html.InsertaEnTabla(orden, LaSoluciones, Dominio+"");
		        //Alumnos y Profesor
		        Html.InsertaFoto(foto);
		        Html.InsertaTexto("<LI>"+pregunta + "</LI><OL type='A'>", true);
		        for (int p=1; p<= respuestas; p++){
		        	Html.InsertaTexto("<LI >. "+Sol[p-1]+"</LI>", false);
		        }
		        Html.InsertaTexto("</OL>", false);
		        //Profesor
		        Html.InsertaTextoP("Solución: "+LaSoluciones+" Dominio: "+Dominio+F._cctos(13)+"Explicación: ["+soluciones+"]"+F._cctos(13), true, true);
		        Html.InsertaTextoP(explicacion+F._cctos(13), false, false);
		        PanelProgreso.setMasProgresoDoc(1);
			}
		}
		catch(Exception e){
			
		}
		finally{
			Html.CierraObjetos();
		}
	}

	public void ImprimeTestIngles(int examen){
		String foto, pregunta, soluciones, LaSoluciones;
		int Dominio, ntest, orden, respuestas; //newpos, preguntas, 
		String[] Sol;
		String SLista;
		int[] Arreglo;
		//int Indice;
		PanelProgreso.setProgresoDoc(0);
		ntest = PanelInfoTest.getIntPreguntas();
		PanelProgreso.setMasProgresoDoc(ntest);
		//preguntas = bbdd.getNumPreguntasTest(examen);
		ResultSet rs = bbdd.getRSTestIng(examen);
		try{
		    Html.CreaObjetosHTML(examen);
			while (rs.next()){
		        //Campos de uso frecuente.
		        respuestas = rs.getString("soluciones").length();
		        Sol = new String[respuestas];
		        soluciones = rs.getString("soluciones")+"";
		        foto = rs.getString("file")+"";
		        orden = rs.getInt("orden");
		        //newpos = inttoabcd(rs.getInt("solucion")).indexOf(soluciones);
		        SLista = rs.getString("solucion");
		        Arreglo = F._cstovi(SLista, ',');
		        //newpos := Pos(inttoaz(DM.SQL.FieldValues['solucion']), soluciones);
		        LaSoluciones = "";
		        for (int p=1; p<Arreglo.length;p++){
		        	String xint = inttoaz(Arreglo[p-1]);
		        	int xiO = xint.indexOf(soluciones);
		            LaSoluciones = LaSoluciones + xiO;
		            if (p < Arreglo.length){
		            	LaSoluciones = LaSoluciones + ",";
		            }
		        }
		        pregunta = rs.getString("pregunta");
		        for (int p = 1; p<respuestas; p++){
		        	Sol[p-1] = rs.getString("resp"+soluciones.charAt(p));
		        }
		        Dominio = rs.getInt("dominio");
		        //Respuestas
		        Html.InsertaEnTabla(orden, LaSoluciones, Dominio+"");
		        //Alumnos y Profesor
		        Html.InsertaFoto(foto);
		        Html.InsertaTexto(orden+". "+pregunta + F._cctos(13), true);
		        for (int p=1; p< respuestas; p++){
		        	Html.InsertaTexto(F._cctos(64+p)+". "+Sol[p-1]+F._cctos(13), false);
		        }
		        //Profesor
		        Html.InsertaTextoP("Solución: "+LaSoluciones+" Dominio: "+rs.getString("dominio")+F._cctos(13)+"Explicación: ["+soluciones+"]"+F._cctos(13), true, true);
		        Html.InsertaTextoP(rs.getString("explicacion")+F._cctos(13), false, false);
		        PanelProgreso.setMasProgresoDoc(1);
			}
		}
		catch(Exception e){
			
		}
		finally{
			Html.CierraObjetos();
		}
	}
	
	public String inttoabcd(int sol){
		String aux;
		switch (sol){
			case 1: aux = "A"; break;
			case 2: aux = "B"; break;
			case 3: aux = "C"; break;
			case 4: aux = "D"; break;
			default: aux = " ";
		}
		return aux;
	}

	public String inttoaz(int sol){
		String aux, fields;
		fields = "'ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		aux = fields.charAt(sol)+"";
		return aux;
	}
	
	public int Pred(int v){
		return 0;
	}
	
    public int examenSeleccionado(){
		int examen;
		try{
			examen = (Integer)PanelTablas.getTableTest().getModel().getValueAt(PanelTablas.getTableTest().getSelectedRow(), 0);
		}
		catch(NumberFormatException e){
			examen = 0;
		}
		if (examen <= 0){
			examen = (Integer)PanelTablas.getTableTest().getModel().getValueAt(1, 1);
		}
		return examen;
	}

    public String RevisaMaterias(){
    	int max;
    	String where;
    	int[] v = PanelTablas.getTableMateria().getSelectedRows();
    	max = v.length;
    	where = "";
    	for (int p = 1; p<max; p++){
    		if (where != ""){
    			where = where + " OR ";
    		where = where + "(Materia = "+v[p]+")";
    		}
    	}
    	return where;
    }

	public static String abcd4(){
		int pos;
		String sol;
		String sols = "ABCDABDCACBDACDBADBCADCBBACDBADCBCADBCDABDACBDCACBADCBDACABDCADBCDBACDABDBCADBACDCBADCABDABCDACB";
		Random aleatorios = new Random(); //Semilla para buscar preguntas aleatorias
		pos = aleatorios.nextInt(12);
		sol = sols.substring(pos*4+1, 4);
		return sol;
	}
    
}