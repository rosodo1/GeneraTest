package bd;

import generic.Registro;

import java.sql.*;
import java.util.Vector;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import igu.RunMe;
import javax.swing.*;

import lib.*;


public class bbdd {
	public static boolean _debug = true;
	public static Connection con1;
	public static Statement st1;
	public static ResultSet rs1;
	public static boolean primera = true, ponderar = false;  
	
	public static void CargaDriver(ModosBBDD i){
		try {
			switch (i){
				case MSACCESS: Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); break; 
				case MYSQL: Class.forName("com.mysql.jdbc.Driver"); break;
				case MYSQLint: Class.forName("org.gjt.mm.mysql.Driver"); break;
				case ORACLE: Class.forName("oracle.jdbc.driver.OracleDriver"); break;
				case SQLSERVER: Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); break;//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); break;
			}
		}
		catch (ClassNotFoundException e1){
			F.Consola("Driver no encontrado");
		}
	}
	
	public static Connection getConexion(ModosBBDD i, String bd, String user, String pass){
		con1 = null;
		try {
			switch (i){
				case MSACCESS: con1 = DriverManager.getConnection("jdbc:odbc:"+bd, user, pass); break;
				case MYSQL:
				case MYSQLint: con1 = DriverManager.getConnection("jdbc:mysql://" + bd, user, pass); break; //bd = host + "/" + database
				case ORACLE: con1 = DriverManager.getConnection("jdbc:oracle:thin:@machine_name:"+bd,user,pass); break; // bd = host +":"+ databasename
				case SQLSERVER: con1 = DriverManager.getConnection("jdbc:odbc:"+bd, user, pass); break;//con1 = DriverManager.getConnection("jdbc:sqlserver://"+bd+";;user="+user+";password="+pass);//bd=localhost:1433;databaseName=AdventureWorks
			}
		}
		catch (SQLException e1){
			F.Consola("Error al conectar con la BBDD");
		}
		return con1;
	}

	public static Statement getStatement(Connection con1){
		Statement stA = null;
		try {
			stA = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}
		catch (SQLException e1){
			F.Consola("Error al crear Statement");
		}
		return stA; 
	}
	
	public static Statement getStatement2(Connection con1){
		Statement stB = null;
		try {
			stB = con1.createStatement();
			}
		catch (SQLException e1){
			F.Consola("Error al crear Statement");
		}
		catch (NullPointerException e1){
			F.Consola("No existe la conexión.");
		}
		return stB; 
	}
	
	public static ResultSet getResulset(Statement st1, String sql){
		rs1 = null;
		try {
			rs1 =st1.executeQuery(sql);
			}
		catch (SQLException e1){
			F.Consola("Error al obtener Resulset:"+sql);
			e1.printStackTrace();
		}
		catch (NullPointerException e1){
			F.Consola("No existe el Statement");
		}
		return rs1;
	}
	
	public static void setEjecutaConsulta(Statement st1, String sql){
		try {
			st1.executeUpdate(sql);
			}
		catch (SQLException e1){
			F.Consola("Error al ejecutar consulta");
		}
	}

	public static void CierraRs(ResultSet rs){
		try {
			rs.close();
		}
		catch (SQLException e1) {
			F.Consola("Error al cerrar resultset");
		}
	}

	public static int getIntCampo(ResultSet rs, int n){
		int valor = 0;
		try{
			valor = rs.getInt(n);
		}
		catch (SQLException e1){
			F.Consola("Error leyendo campo");
		}
		return valor;
	}

	public static Object[][] ResultSetArray(int col, int row){
		int j = 0;
		Object[][] o;
		try{
			rs1.beforeFirst();
			o = new Object[row][col];
			j = 1;
			while (rs1.next()){
				for(int i=0;i<col;i++){
					o[j][i] = rs1.getString(i+1);
					System.out.println(j+" "+rs1.getString(1));
				}
				j++;
			}
		}
		catch (SQLException e1){
			F.Consola("Error recorriendo consulta");
			o = null;
		}
		return o;
	}

	public static int getCount(String sql){
		int rows = 0;
		getResulset(st1, sql);
		try {
			while (rs1.next()){
				rows = rs1.getInt(1);  			
			}
		} catch (SQLException e) {}
		return rows;
	}

	public static int setNewExam(String fecha){
		String sql;
		int num=0;
		sql = "insert into examen (fecha) values ('"+fecha+"') ";
		setEjecutaConsulta(st1, sql);
		sql = "Select Max(id) as maximo From examen";
		getResulset(st1, sql);
		try{
			if (rs1.next())
				num = rs1.getInt("maximo");
		}
		catch(Exception e){
			num = 0;
		}
		return num;
	}
	
	public static void setTestBorra(int test){
		String sql, cadena="";
		switch (RunMe.getModoBD()){
			case MSACCESS: cadena = " * ";break;
		}
		sql = "Delete "+cadena+" From test WHERE examen = "+test+";";
		setEjecutaConsulta(st1, sql);
		sql = "Delete "+cadena+" From examen WHERE id = "+test+";";
		setEjecutaConsulta(st1, sql);
	}
	
	public static int[] moveBy(int num, boolean repetir, boolean ponderar, int capitulos, HashMap ad, int[] VMateriasB){
		int[] valores = new int[2];
		ResultSet rs;
		primera = true;
		rs = getRSDisponibles(repetir, ponderar, capitulos, ad, VMateriasB, false);
		try{
			rs.first();
			rs.absolute(num);
			valores[0] = rs.getInt("id");
			valores[1] = rs.getInt("dominio");
		}
		catch (Exception e){
			//System.out.println("Num: "+num);
			//System.out.println(e.getStackTrace());
			//e.printStackTrace();
			valores[0] = 0;
			valores[1] = 0;
		}
		return valores;
	}
	
	public static void setMostrada(int examen, int npreg, int marcar, String soluciones){
		String sql;
		sql = "insert into test (examen, mostrada, orden, ref, soluciones) values ("+examen+", true, "+npreg+", '"+marcar+"', '"+soluciones+"') ";
		setEjecutaConsulta(st1, sql);
	}

	public static int getNumPreguntasBBDD(String where){
		int num;
		String consulta;
		consulta = "Select Count(p.id) as total from PREGUNTAS p, MATERIAS m where (p.fuera = "+BDbool(false)+") and (p.materia = m.id) ";
		if (where != "")
			consulta = consulta + where;
		getResulset(st1, consulta);
		num = 0;
		try{
			if (rs1.next())
				num = rs1.getInt("total");
		}
		catch(Exception e){
			num = 0;
		}
		return num;
	}

	public static int getNumPreguntasDominio(int dominio, String where){
		int num;
		String consulta;
		consulta = "Select Count(*) as total from PREGUNTAS where (fuera = false) and dominio = "+dominio;
		if (where != "")
			consulta = consulta + " and (" + where +")";
		getResulset(st1, consulta);
		num = 0;
		try{
			if (rs1.next())
				num = rs1.getInt("total");
		}
		catch(Exception e){
			num = 0;
		}
		return num;
	}
	
	public static int getNumPreguntasMostradas(){
		int num;
		getResulset(st1, "Select Count(*) as total from test t where t.mostrada = true");
		num = 0;
		try{
			if (rs1.next())
				num = rs1.getInt("total");
		}
		catch(Exception e){
			num = 0;
		}
		return num;
	}

	public static int getNumPreguntasMostradas(int dominio, String where){
		int num;
		getResulset(st1, "Select Count(*) as total from PREGUNTAS C, test t where C.ID = T.REF AND t.mostrada = true and dominio = "+dominio);
		num = 0;
		try{
			if (rs1.next())
				num = rs1.getInt("total");
		}
		catch(Exception e){
			num = 0;
		}
		return num;
	}

	public static int getNumPreguntasMostradas(String where){
		int num;
		String consulta;
		consulta = "Select Count(*) as total from PREGUNTAS P, MATERIAS M, test t where (p.fuera = "+BDbool(false)+") and (p.materia = m.id) and (P.ID = T.REF) AND (t.mostrada = "+BDbool(true)+")";
		if (where != "")
			consulta = consulta + where;
		getResulset(st1, consulta);
		num = 0;
		try{
			if (rs1.next())
				num = rs1.getInt("total");
		}
		catch(Exception e){
			num = 0;
		}
		return num;
	}

	public static int getNumPreguntasTest(String where){
		int num;
		String consulta;
		consulta = "Select Count(p.id) as total from PREGUNTAS p, MATERIAS m, test t where (p.fuera = "+BDbool(false)+") and (p.materia = m.id) and (P.ID = T.REF) ";
		if (where != "")
			consulta = consulta + where;
		getResulset(st1, consulta);
		num = 0;
		try{
			if (rs1.next())
				num = rs1.getInt("total");
		}
		catch(Exception e){
			num = 0;
		}
		return num;
	}

	public static int getNumPreguntasTest(int examen){
		int num;
		getResulset(st1, "Select Count(*) as total from test t where T.examen = "+examen);
		num = 0;
		try{
			if (rs1.next())
				num = rs1.getInt("total");
		}
		catch(Exception e){
			num = 0;
		}
		return num;
	}

	public static ResultSet getRSAsignaturas(){
		String[] cols = {"id", "asignatura"};
		getResulset(st1, "Select "+F.VectorToString(cols)+" From Asignaturas Order By Asignatura;");
		return rs1;
	}
	
	public static ResultSet getRSDominio(int[] m){
		String[] cols = {"dominio"}; 
		/*switch (RunMe.getModoBD()){
		case SQLSERVER: cols[0] = "id"; break;
		}*/
		String materias = "";
		for (int i : m){
			materias = "and (materia = "+i+")";
		}
		if (materias.equals("")){materias = "and (materia = 0)";}
		getResulset(st1, "Select distinct "+F.VectorToString(cols)+" From Preguntas WHERE (fuera = "+BDbool(false)+") "+materias+" Order By Dominio;");
		return rs1;
	}
	
	public static String BDbool(boolean s){
		String x; 
		if (s) x = "true";
		else x = "false";
		switch (RunMe.getModoBD()){
		case SQLSERVER : if (s) x = "1"; else x = "0";break;
		}
		return x;
	}
	public static ResultSet getRSDominiosPreguntasRealizadas(String where){
		String consulta;
		String cols[] = {"p.dominio, count(*)"};
		/*switch (RunMe.getModoBD()){
		case SQLSERVER: cols[0] = "dominio"; break;
		}*/
		consulta = "Select distinct "+F.VectorToString(cols)+" from PREGUNTAS p, MATERIAS m, TEST t where (p.fuera = "+BDbool(false)+") and (p.materia = m.id) and (P.ID = T.REF) AND (t.mostrada = "+BDbool(true)+")";
		if (where != "")
			consulta = consulta + where;
		consulta = consulta + " group by p.dominio";
		getResulset(st1, consulta);
		return rs1;
	}

	public static ResultSet getRSDominiosPreguntas(String where){
		String consulta;
		String cols[] = {"p.dominio, count(*)"};
		/*switch (RunMe.getModoBD()){
		case SQLSERVER: cols[0] = "(*)"; break;
		}*/
		consulta = "Select distinct "+F.VectorToString(cols)+" from PREGUNTAS p, MATERIAS m where (p.fuera = "+BDbool(false)+") and (p.materia = m.id) ";
		if (where != "")
			consulta = consulta + where;
		consulta = consulta + " group by p.dominio";
		getResulset(st1, consulta);
		return rs1;
	}

	public static ResultSet getRSDominiosPreguntas(){
		String cols[] = {"dominio, count(*)"};
		switch (RunMe.getModoBD()){
		case SQLSERVER: cols[0] = "(*)"; break;
		}
		getResulset(st1, "Select distinct "+F.VectorToString(cols)+" From PREGUNTAS GROUP BY dominio;");
		return rs1;
	}
	
	public static ResultSet getRSMaterias(int[] a){
		String[] cols = {"M.id", "M.materia", "A.asignatura"};
		String asignaturas = "";
		for (int i : a){
			asignaturas = "and (A.id = "+i+")";
		}
		if (asignaturas.equals("")){asignaturas = "and (A.id = 0)";}
		getResulset(st1, "Select "+F.VectorToString(cols)+" From Materias M, Asignaturas A WHERE (A.id = M.Asignatura) "+asignaturas+" Order By M.Asignatura;");
		return rs1;
	}
	
	public static ResultSet getRSAsignaturasPreguntas(int[] a){
		String[] cols = {"p.dominio, count(p.id)"};
		switch (RunMe.getModoBD()){
		case SQLSERVER: cols[0] = "(*)"; break;
		}
		String asignaturas = "";
		for (int i : a){
			asignaturas = asignaturas + "and (M.asignatura = "+i+")";
		}
		getResulset(st1, "Select distinct "+F.VectorToString(cols)+" From PREGUNTAS P, MATERIAS M WHERE (M.id = P.Materia) "+asignaturas+" GROUP BY P.dominio;");
		return rs1;
	}
	
	public static ResultSet getRSMateriasPreguntas(int materia){
		String[] cols = {"dominio, count(id)"};
		switch (RunMe.getModoBD()){
		case SQLSERVER: cols[0] = "(*)"; break;
		}
		getResulset(st1, "Select distinct "+F.VectorToString(cols)+" From PREGUNTAS, MATERIAS WHERE (MATERIAS.id = PREGUNTAS.Materia) And (Materias.id = "+materia+") GROUP BY dominio;");
		return rs1;
	}
	
	public static int getRSNumMaterias(int id){
		getResulset(st1, "Select Count(*) as total from materias");
		int num = 0;
		try{
			if (rs1.next())
				num = rs1.getInt("total");
		}
		catch(Exception e){
			num = 0;
		}
		return num;
	}
	
	public static ResultSet getRSRealizadasPreguntas(){
		String[] cols = {"dominio, count(id)"};
		switch (RunMe.getModoBD()){
		case SQLSERVER: cols[0] = "(*)"; break;
		}
		getResulset(st1, "Select distinct "+F.VectorToString(cols)+" From PREGUNTAS, TEST WHERE Preguntas.id = Test.Ref GROUP BY dominio;");
		return rs1;
	}
	
	public static ResultSet getRSTests(){
		getResulset(st1, "Select id, fecha From Examen;");
		return rs1;
	}

	public static ResultSet getRSTestPreguntas(int examen){
		String[] cols = {"p.dominio, count(p.id)"};
		/*switch (RunMe.getModoBD()){
		case SQLSERVER: cols[0] = "(*)"; break;
		}*/
		getResulset(st1, "Select distinct "+F.VectorToString(cols)+" From PREGUNTAS P, TEST T WHERE (P.id = T.Ref) And (T.examen = "+examen+") GROUP BY P.dominio;");
		return rs1;
	}
	
	public static ResultSet getRSTestPreguntas(String where){
		String[] cols = {"p.dominio, count(p.id)"};
		/*switch (RunMe.getModoBD()){
		case SQLSERVER: cols[0] = "(*)"; break;
		}*/
		getResulset(st1, "Select distinct "+F.VectorToString(cols)+" From PREGUNTAS P, TEST T WHERE (P.id = T.Ref) "+where+" GROUP BY P.dominio;");
		return rs1;
	}
	
	public static ResultSet getRSTest(int examen){
		getResulset(st1, "Select * From PREGUNTAS c, test t WHERE (c.id = t.ref) and (t.examen = "+examen+") and (C.fuera=false) order by orden ASC;");
		return rs1;
	}

	public static ResultSet getRSTestIng(int examen){
		getResulset(st1, "SELECT c2.*, t.* FROM PREGUNTAS C1, PREGUNTAS C2, test t WHERE (C1.id = t.ref) and (t.examen = "+examen+") and (C1.fuera=false) And (C1.ref = c2.ref) and (c2.materia = 7)) UNION (SELECT c1.*, t.* FROM PREGUNTAS AS C1, test t WHERE (c1.id = t.ref) and (t.examen= "+examen+") and (c1.fuera = false) and C1.ref not in (SELECT c2.ref FROM PREGUNTAS C1, PREGUNTAS C2, test t WHERE (C1.id = t.ref) and (t.examen = "+examen+") and (C1.fuera=false) And (C1.ref = c2.ref) and (c2.materia = 7))) order by orden");
		return rs1;
	}

	public static ResultSet getRSTestFecha(String fecha){
		getResulset(st1, "Select id as maximo From examen WHERE fecha = '"+fecha+"';");
		return rs1;
	}

	public static String getSQLPrimera(int[] vMaterias, boolean sWhere){
		String sql=""; 
		if (! primera)
		    sql = sql + ")";
		primera = true;
		for (int p= 0; p <vMaterias.length; p++){
			if (!primera)
				sql = sql + " or (materia = "+vMaterias[p]+')';
			else{
				if (sWhere)
					sql = sql + " and (";
				else
					sql = sql + " where ";
				sql = sql+"(materia = "+vMaterias[p]+")";
				primera = false;
			}
		}
		if (primera == false)
			sql = sql + ")";
		return sql;
	}

	public static String getSQLPonderar(boolean ponderar, Set set, boolean sWhere){
		String sql=""; 
		if (!ponderar){
			Iterator i = set.iterator();
			while (i.hasNext()){
				Map.Entry me = (Map.Entry)i.next();
				DPreguntas x = (DPreguntas)me.getValue();
				if (x.getPorhacer() > 0){
		        	if (!primera)
		        		sql = sql + " or (dominio = "+(Integer)me.getKey()+")";
		        	else{
		        		if (! sWhere)
		        			sql = sql + " where ";
		        		else
		        			sql = sql + " and (";
		        		sql = sql + " (dominio = "+(Integer)me.getKey()+")";
		        		primera = false;
		        	}
				}
				
			}
		}
		return sql;
	}
	
	public static int getDisponibles(boolean repetir, boolean ponderar, int capitulos, HashMap ad, int[] vMaterias, boolean contar){
		boolean sWhere = false;
		String campos, sql;
		int num;
		Set set = ad.entrySet();
		if (contar)
			campos = "Count(c.id) as Total";
		else
			campos = "c.id";
		sql = "Select "+campos+" From PREGUNTAS c ";
		if (! repetir){
		      sql = sql + "where (c.fuera = false) and c.id not in (select t.ref from test t) ";
		      sWhere = true;
		}
		sql = sql + getSQLPonderar(ponderar, set, sWhere);
		sql = sql + getSQLPrimera(vMaterias, sWhere);
		//F.Consola("Count: "+sql);
		primera = true;
		getResulset(st1, sql);
		num = 0;
		try{
			while ((rs1.next()) && (contar == true)){
				num = rs1.getInt("total");}
		}
		catch(Exception e){
			num = 0;
		}
		return num;
	}

	public static ResultSet getRSDisponibles(boolean repetir, boolean ponderar, int capitulos, HashMap ad, int[] vMaterias, boolean contar){
		String sql;
		boolean sWhere = false;
		Set set = ad.entrySet();
		sql = "Select * From PREGUNTAS c ";
		if (!repetir){
			sql = sql + "where (c.fuera = false) and c.id not in (select t.ref from test t)";
			sWhere = true;
		}
		sql = sql + getSQLPonderar(ponderar, set, sWhere);
		sql = sql + getSQLPrimera(vMaterias, sWhere);
		//F.Consola("RS: "+sql);
		primera = true;
		ResultSet rs = getResulset(getStatement(con1), sql);
		return rs;
	}

	public static void RecorreRS(ResultSet rs, int id){
		int j = 0;
		try{
			rs.beforeFirst();
			while (rs.next()){
				if (rs==null)
					{F.Consola("NULO");}
				else
					{
					F.Consola(rs.getString(id));
					j++;
					}
			}
		}
		catch (SQLException e1){
			F.Consola(""+e1.toString());
			F.Consola("Error recorriendo consulta");
		}
    }

	public static void RellenaTable(JTable MT, ResultSet rs, int id, Registro reg, Vector<Registro> w){
		w.removeAllElements();
		try{
			while (rs.next()){
				Object[] mArray = new Object[id];
				for (int i = 0; i<id;i++){
					mArray[i] = rs.getString(i+1);
				}
				reg.setAllFromArray(mArray);
				w.addElement(reg);
			}
			MT.repaint();
		}
		catch (SQLException e1){
			F.Consola("Error recorriendo consulta");
		}
    }
    
	public static void RellenaJL(DefaultListModel LM, ResultSet rs, int id){
		int j = 0;
		LM.removeAllElements();
		try{
			while (rs.next()){
				LM.add(j, rs.getString(id));
				j++;
			}
		}
		catch (SQLException e1){
			F.Consola("Error recorriendo consulta");
		}
    }

	public static Statement getSt1() {
		return st1;
	}

	public static void setSt1(Statement st1) {
		bbdd.st1 = st1;
	}

	public static int getNumRespuestas(ResultSet rs){
		int aux=0, x=0;
		boolean cond=false;
		String fields = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		while ((x<=fields.length()) && (cond==false)){
			try{
				String y = rs.getString("resp"+fields.charAt(x));
				if (y != null)
					aux = x+1;
				else
					cond = true;
				}
			catch(Exception e){
				cond = true;
				}
			x++;
			}
		return aux;
		}

	public static ResultSet getRs1() {
		return rs1;
	}

	public static void setRs1(ResultSet rs1) {
		bbdd.rs1 = rs1;
	}
	}
