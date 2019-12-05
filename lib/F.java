package lib;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.*;

public class F {


	public static boolean _debug = true; 
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @return String
	 * @comment Convierte un entero en String 
	*/
	public static String _citos(int numero)
	{
	return new Integer(numero).toString();
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return Date
	 * @comment Convierte un texto en fecha. Sin Excepciones
	*/
	public static Date _cstod(String fecha) throws Exception
	{
	GregorianCalendar f;
	int a, m, d;
	a = SFechaAnyo(fecha);
	m = SFechaMes(fecha)-1;
	d = SFechaDia(fecha);
	f = new GregorianCalendar(a, m, d);
	return f.getTime();
	}
	
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return Date
	 * @comment Convierte un texto en fecha. Controla Excepciones 
	*/
	public Date _cstodE(String fecha)
	{
	GregorianCalendar f = new GregorianCalendar();
	try
		{f.setTime(_cstod(fecha));}
	catch
		(Exception e){f.setTime(new Date());}
	return f.getTime();
	}
	
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return int
	 * @comment Convierte un texto en integer. Sin Excepciones 
	*/
	public static int _cstoi(String cadena) throws NumberFormatException
	{
	int v;
	v = new Integer(cadena).intValue();
	return v;	
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return Date
	 * @comment Convierte un texto en un vector de int, cada elemento va separado por ;. Sin Excepciones 
	*/
	public static int[] _cstovi(String unvector) throws NumberFormatException
	{
	String[] v1 = unvector.split(";");
	int[] v2 = new int[v1.length];
	for (int i = 0; i<v1.length; i++)
		{
		v2[i] = _cstoi(v1[i]);
		}
	return v2;
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return int[]
	 * @comment Convierte un texto en un vector de int, cada elemento va separado por ;. Sin Excepciones 
	*/
	public static int[] _cstovi(String unvector, char c)
	{
	String[] v1 = unvector.split(""+c);
	int[] v2 = new int[v1.length];
	for (int i = 0; i<v1.length; i++)
		{
		try{
			v2[i] = _cstoi(v1[i].trim());
		}
		catch (NumberFormatException e){
			v2[i] = 0;
		}
		}
	return v2;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return Date
	 * @comment Convierte un texto en una matriz de int, cada fila va separada por ; y cada elemento va separado por ,. 
	*/
	public static Integer[][] _cstomi(String unamatriz)
	{
	String[] v1 = unamatriz.split(";");
	String[][] v2 = new String[v1.length][];
	Integer [][] v3 = new Integer[v1.length][];;
	for (int x = 0;x<v1.length;x++)
		{
		v2[x] = v1[x].split(",");
		v3[x] = new Integer[v2[x].length];
		for (int y = 0; y<v2.length;y++)
			{
			try
				{v3[x][y] = _cstoi(v2[x][y]);}
			catch
				(Exception e){v3[x][y]=0;}
			}
		}
	return v3;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return [] String
	 * @comment Convierte un texto en un vector de String, cada elemento va separado por ;. Sin Excepciones 
	*/
	public static String[] _cstovs(String unvector)
	{
	return unvector.split(";");
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param []char
	 * @return String
	 * @comment Convierte un vector de Char en String. Sin Excepciones 
	*/
	public static String _cvctos(char[] unvector)
	{
	String s = "";
	for (int i = 0; i < unvector.length; i++)
		{
		s = s + (unvector[i]);
		}
	return s;
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param []char
	 * @return String
	 * @comment Convierte un vector de int en String. Sin Excepciones 
	*/
	public static String _cvitos(int [] unvector)
	{
	String s = "";
	for (int i = 0; i<unvector.length; i++)
		{
		s = s + unvector[i];
		}
	return s;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @param int
	 * @param int
	 * @return Double
	 * @comment Devuelve el Area de un triangulo del que nos dan la longitud de cada lado 
	*/
	public static double AreaTriangulo(int a, int b, int c)
	{
	if ((a > b) && (a > c)){a = c;}
	if ((b > a) && (b > c)){b = c;}
	return (a * b) / 2;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @param int
	 * @return Double
	 * @comment Devuelve el Area de un triangulo del que nos dan la base y altura 
	*/
	public static double AreaTriangulo(int a, int b)
	{
	return (a * b) / 2;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @param double
	 * @param int
	 * @param int
	 * @return Double
	 * @comment Calcula el Sueldo a partir de horas trabajadas, precio hora, decuento e impuestos. 
	*/
	public static double CalculaSueldo(int H, double precio, int dc, int imp)
	{
	double total = H * precio;
	total = total - (total * (dc/100));
	total = total - (total * (imp/100));
	return total;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return Boolean
	 * @comment Nos indica si el valor recibido es capicua o no 
	*/
	
	public static void CarACar(String c){
		for (int i=0; i<c.length();i++){
			Integer x = new Integer(c.charAt(i));
			F.Consola(x+": "+c.charAt(i));
		}
		String aux = ""+(char)13+ (char)10;
		c= c.replace(aux, "<br>");
		System.out.println(c);
	}
	
	public static boolean Capicua(String numero)
	{
	boolean res = true;
	if (numero.compareTo("") != 0)
		{
		for (int i = 0; i <= (numero.length()/2); i++)
			{
			if (numero.charAt(i) != numero.charAt(numero.length()-1-i)){res = false;} 
			}
		}
	return res;
	}
	
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @return int
	 * @comment Devuelve el numero de cifras del numero recibido
	*/
	public static int Cifras(int numero)
		{
		int num = 1;
		while ((numero / 10) >= 1)
			{
			numero = numero / 10;
			num = num + 1;
			}
		return num;
		}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param Date
	 * @return int
	 * @comment Devuelve el numero del dia dentro del año de la fecha 
	*/
	public static void Consola(String s){
		if (_debug==true){
			System.out.println(s);
		}
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param Date
	 * @return int
	 * @comment Devuelve el numero del dia dentro del año de la fecha 
	*/
	public static int DiaAnyo(Date fecha)
	{
	GregorianCalendar g = new GregorianCalendar(); 
	g.setTime(fecha);
	return g.get(Calendar.DAY_OF_YEAR);	
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param Date
	 * @return String
	 * @comment Devuelve la fecha en formato completo 
	*/
	public static String DiaCompleto(Date fecha)
	{
	DateFormat df=DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
	return df.format(fecha);	
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param GregorianCalendar
	 * @return String
	 * @comment Devuelve la fecha en formato completo 
	*/
	public static String DiaCompleto(GregorianCalendar fecha)
	{
	return DiaCompleto(fecha.getTime());
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @return String
	 * @comment Devuelve el nombre del dia de la semana segun el numero de la posicion que ocupa el dia en la semana
	*/
	public static String DiaSemana(int numero)
		{
		String dia;
		switch (numero)
			{
			case 1: dia = "Lunes";break; 
			case 2: dia = "Martes";break; 
			case 3: dia = "Miercoles";break; 
			case 4: dia = "Jueves";break; 
			case 5: dia = "Viernes";break; 
			case 6: dia = "Sabado";break; 
			case 7: dia = "Domingo";break;
			default: dia = "Desconocido";
			}
		return dia;
		}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param Date fecha primera
	 * @param Date fecha segunda
	 * @return int
	 * @comment Devuelve el numero de dias existente entre dos fechas 
	*/
	public static int DiasEntreFechas(Date fecha1, Date fecha2)
	{
	/*System.out.println(f1.getTime());
	System.out.println(f2.getTime());
	System.out.println((f1.getTimeInMillis()-f2.getTimeInMillis())/1000/60/60/24);*/
	int anyo1, anyo2; int dias = 0;
	Date fechaaux; GregorianCalendar f1 = new GregorianCalendar(); GregorianCalendar f2 = new GregorianCalendar(); GregorianCalendar faux = new GregorianCalendar();
	if (fecha1.after(fecha2) == true) 
		{fechaaux = fecha1; fecha1 = fecha2; fecha2 = fechaaux;}
	f1.setTime(fecha1); f2.setTime(fecha2);
	int ndia1 = f1.get(Calendar.DAY_OF_YEAR); 
	int ndia2 = f2.get(Calendar.DAY_OF_YEAR);
	int suma = 0;
	anyo1 = f1.get(Calendar.YEAR); anyo2 = f2.get(Calendar.YEAR);
	if (anyo1 < anyo2)
		{
		faux = new GregorianCalendar(anyo1, 11, 31);
		suma = faux.get(Calendar.DAY_OF_YEAR);
		for (int anyo = (anyo1+1); anyo == (anyo2-1); anyo++)
			{
			if (UltimoDiaMes(2, anyo) == 28)
				{suma = suma + 365;}
			else
				{suma = suma + 366;}
			}
		}
	dias = (ndia2+suma) - ndia1;
	return dias;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param Date Fecha
	 * @param int numero de dias
	 * @return int
	 * @comment Devuelve la fecha resultado de sumarle la cantidad de dias indicada a la fecha recibida 
	*/
	public static Date DiasMasNumero(Date fecha1, int numeros)
	{
	GregorianCalendar f1 = new GregorianCalendar();
	f1.setTime(fecha1);
	f1.set(f1.get(Calendar.YEAR), f1.get(Calendar.MONTH), f1.get(Calendar.DAY_OF_MONTH)+5);
	return f1.getTime();
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param Date Fecha
	 * @param int numero de dias
	 * @return int
	 * @comment Devuelve la fecha resultado de sumarle la cantidad de dias indicada a la fecha recibida 
	*/
	public static GregorianCalendar DiasMasNumero(int dia, int mes, int anyo, int numeros)
	{
	GregorianCalendar f1 = new GregorianCalendar();
	f1.set(anyo, mes, dia);
	f1.setTime(DiasMasNumero(f1.getTime(), numeros));
	return f1;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int Dia
	 * @param int Mes
	 * @param int Año
	 * @return boolean
	 * @comment Indica si los valores recibidos corresponden a una fecha valida 
	*/
	public static boolean ExisteDiaMes(int dia, int mes, int anyo)
	{
	boolean res = true;  GregorianCalendar fecha = new GregorianCalendar();
	fecha= new GregorianCalendar(anyo, mes-1, dia);
	int dia2 = fecha.get(Calendar.DAY_OF_MONTH);
	if (dia2 != dia){res = false;}
    return res;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @return int
	 * @comment Devuelve el factorial del numero recibido
	*/
	public static int Factorial(int numero)
		{
		int fact;
		if 
			(numero < 2){fact = 1;}
		else
			{fact = numero * Factorial(numero-1);}
		return fact;
		}
	
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param string
	 * @return string
	 * @comment convierte la fecha recibida en formato aaaa/mm/dd al formato dd/mm/aaaa y la devuelve en cadena.
	*/
	public static String FechaAMDDMA(String a) 
		{
		String anyo = a.substring(0,4);
		String mes = a.substring(5,7);
		String dia = a.substring(8,10);
		return dia+"/"+mes+"/"+anyo;
		}
	
	/**Convierte la fecha recibida a String con el formato simple
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param Date fecha
	 * @return String fecha en formado dd/mm/yyy
	*/
	public static String FechaSimple(Date fecha) {
	    SimpleDateFormat frm = new SimpleDateFormat("dd/MM/yyyy");
	    String currentDate = frm.format(fecha);
        return currentDate; 
	}
	
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param string a. Fecha
	 * @param int b. Formato de Entrada.
	 * @param int c. Formato de Salida.
	 * @return string
	 * @comment convierte la fecha recibida en el formato indicado al formato aaaa/mm/dd al formato indicado y la devuelve en cadena.
	 * Los codigos empleados para los formatos son 1 dd/mm/aaaa, 2 aaaa/mm/dd, 3 mm/dd/aaaa
	*/
	public static String FechasConversion(String a, int b, int c)
		{
		String anyo, dia, mes, fecha;
		switch (b)
			{
			case 1:
				{
				dia = a.substring(0,2);
				mes = a.substring(3,5);
				anyo = a.substring(6,10);
				break;
				}
			case 2:
				{
				anyo = a.substring(0,4);
				mes = a.substring(5,7);
				dia = a.substring(8,10);
				break;
				}
			case 3:
				{
				mes = a.substring(0,2);
				dia = a.substring(3,5);
				anyo = a.substring(6,10);
				break;
				}
			default:
				dia = a.substring(0,2);
				mes = a.substring(3,5);
				anyo = a.substring(6,10);
				break;
			}
		switch (c)
			{
			case 1:
				fecha = dia+"/"+mes+"/"+anyo;
				break;
			case 2: 
				fecha = anyo+"/"+mes+"/"+dia;
				break;
			case 3: 
				fecha = mes+"/"+dia+"/"+anyo;
				break;
			default:
				fecha = dia+"/"+mes+"/"+anyo;
				break;
			}
		return fecha;
		}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param Date
	 * @return String
	 * @comment Devuelve una fecha con el formato del Sistema 
	*/
	public static String FormateaFecha(Date fecha) {
	        DateFormat df = DateFormat.getDateInstance();
	        return "" + df.format(fecha); 
	}
	
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String[][]
	 * @return int
	 * @comment Devuelve el numero de Elementos de una matriz 
	*/
	public static int GetMatrizNumElementos(String[][] matriz)
	{
	int elementos = matriz.length, ite, total = 0;
	for (ite = 0; ite < elementos; ite++)
		{total = total + matriz[ite].length;}
	return total;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int hora
	 * @param int minutos
	 * @param int segundos
	 * @param int segundos de incremento
	 * @return Date
	 * @comment Devuelve la fecha resultado de sumarle la cantidad de segundos indicada a la hora recibida 
	*/
	public static Date HoraMasSegundos(int h, int m, int s, int numeros)
	{
	GregorianCalendar f1 = new GregorianCalendar();
	f1.set(f1.get(Calendar.YEAR), f1.get(Calendar.MONTH), f1.get(Calendar.DAY_OF_MONTH), h, m, s+numeros);
	return f1.getTime();
	}
	
	public static String toHtml(String c){
		String aux = ""+(char)13+ (char)10;
		c= c.replace(aux, "<br>");
		return c;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param 
	 * @return String
	 * @comment Lee texto por teclado desde la consola hasta pulsar ENTER 
	*/
	public static String LeerTeclado()
	{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String frase = "";
	try 
		{frase = br.readLine();} 
	catch (IOException e) 
		{e.printStackTrace();}
	return frase;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int[]
	 * @return int
	 * @comment Devuelve el valor maximo que existe en un vector
	*/
	public static int MaximoDeVector(int[] vector)
	{
	int ite, max;
	max = vector[0];
	for (ite = 1; ite <= vector.length; ite++)
		{
		if (vector[ite] > max){max = vector[ite];}
		}
	return max;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @return String
	 * @comment Devuelve el nombre del mes segun el numero
	*/
	public static String MesNombre(int numero)
	{
	String mes;
	switch (numero)
		{
		case 1: mes = "Enero";break; 
		case 2: mes = "Febrero";break; 
		case 3: mes = "Marzo";break; 
		case 4: mes = "Abril";break; 
		case 5: mes = "Mayo";break; 
		case 6: mes = "Junio";break; 
		case 7: mes = "Julio";break;
		case 8: mes = "Agosto";break;
		case 9: mes = "Septiembre";break;
		case 10: mes = "Octubre";break;
		case 11: mes = "Noviembre";break;
		case 12: mes = "Diciembre";break;
		default: mes = "Desconocido";
		}
	return mes;
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param 
	 * @return []char
	 * @comment Devuelve un vector con los 256 ASCII 
	*/
	public static char [] MiAscii()
	{
	char x;
	char [] vector = new char[256];
	for (int i = 0; i <= 255; i++)
		{
		x = (char)i;
		vector[i] = x;
		}
	return vector;
	}
	
	public static String _cctos(int x){
		char c = (char)x;
		return c+"";
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param []int
	 * @return []int
	 * @comment Devuelve el numero menor y mayor, encontrados en una matriz, en un vector de dos valores
	*/
	public static int[] MinMaxVector(int[] vector)
		{
		int min, max, i;
		int x = vector.length;
		min = vector[0];
		max = vector[0];
		for(i=0; i<x; i++)
			{
			if (vector[i]<min)
				{min = vector[i];} 
			if (vector[i]>max)
				{max = vector[i];} 
			}
		int sols[] ={min, max};
		return sols;
		}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param [][]int
	 * @return []int
	 * @comment Devuelve el numero menor y mayor, encontrados en una matriz, en un vector de dos valores
	*/
	public static int[] MinMaxVector(int[][] vector)
		{
		int min, max, i , j;
		int x = vector.length;
		int y = vector[0].length;
		min = vector[0][0];
		max = vector[0][0];
		for(i=0; i<x; i++)
			{
			for(j=0; j<y; j++)
				{
				if (vector[i][j]<min)
					{min = vector[i][j];} 
				if (vector[i][j]>max)
					{max = vector[i][j];} 
				}
			}
		int sols[] ={min, max};
		return sols;
		}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param void
	 * @return String[][]
	 * @comment Muestra los valores de una matriz 
	*/
	public static void MatrizToConsola(Object [][] Matriz)
		{
		for (int x = 0; x<Matriz.length;x++)
			{
			for (int y=0; y<Matriz[x].length;y++)
				{
				System.out.print(Matriz[x][y]);
				if (y < (Matriz[x].length-1)) {
					System.out.print(", ");
					}
				}
			System.out.print("\n");
			}
		}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param Object[][]
	 * @return String
	 * @comment Muestra los valores de una matriz 
	*/
	public static String MatrizToString(Object [][] Matriz)
		{
		String s = "";
		for (int x = 0; x<Matriz.length;x++)
			{
			for (int y=0; y<Matriz[x].length;y++)
				{
				s += Matriz[x][y];
				if (y < (Matriz[x].length-1)) {
					s += ", ";
					}
				}
			}
		return s;
		}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @return String
	 * @comment Devuelve el numero recibido en letras 
	*/
	public static String NumeroALetra(int numero)
		{
		String letras= ""; String fin = "";
		String [] uni = {"Cero", "Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez", "Once", "Doce", "Trece", "Catorce", "Quince"};
		String [] rar = {"Dieci","Venti"};
		String [] dec = {"Diez", "Veinte", "Treinta", "Cuarenta", "Cincuenta", "Sesenta", "Setenta", "Ochenta", "Noventa", "Cien"};
		if (numero < 16)
			{letras = uni[numero];}
		else if (numero < 30)
			{
			fin = uni[numero % 10];
			if ((numero % 10) == 0)
				{
				letras = dec[1];
				}
			else
				{
				letras = rar[((numero - (numero % 10)) / 10)-1]+ fin;
				} 
			}
		else
			{
			fin = " y " + uni[numero % 10];
			if ((numero % 10) == 0){fin = "";}
			letras = dec[((numero - (numero % 10)) / 10)-1]+ fin;
			}
		return letras;
		}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @return int[]
	 * @comment Nos devuelve un vector con el numero de dias de cada mes 
	*/
	public static int[] NumeroDiasMesDeAnyo(int anyo)
	{
	int mes; int meses[] = new int[12];
	for (mes = 0; mes <= 11; mes++)
		{meses[mes]=UltimoDiaMes(mes+1, anyo);}
	return meses;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int, int, char
	 * @return double
	 * @comment Realiza la operacion basica indicada con los dos numeros recibidos 
	*/
	public static double OperacionBasica(int n1, int n2, char op)
	{
	double sol = 0;
	if (op=='+'){sol = n1+n2;}
	if (op=='-'){sol = n1-n2;}
	if (op=='*'){sol = n1*n2;}
	if (op=='/'){sol = (double)n1/(double)n2;}
	return sol;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int, int
	 * @return int
	 * @comment Realiza una operacion dependiendo de los dos numeros recibidos 
	*/
	public static int OperaDosNumeros(int numero1, int numero2)
	{
	int total=0;
	if(numero1== numero2)
		{total=numero1*numero2;}
	else
		{
		if(numero1 >numero2)
			{total=numero1-numero2;}
		else
			{total=numero1+numero2;}
		}
	return total;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param array int
	 * @return Boolean
	 * @comment Indica si el vector recibido esta ordenado 
	*/
	public static boolean Ordenado(int[] vector)
		{
		boolean estado = true; int i = 0; int ult = vector[0];
		while ((i < vector.length-1) && (estado == true))
			{
			i++;
			if (ult <= vector[i])
			    {ult = vector[i];}
			else
				{estado = false;} 
			}
		return estado;
		}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param [] int
	 * @return [] int
	 * @comment Devuelve un vector ordenado de menor a mayor
	*/
	public static int[] OrdenaVector(int[] vector)
		{
		int iterador, pmaxaux, temp;
		int pos = 0; int pmax = 0; int pmin = 0; int tope = vector.length;
		while (pos <= ((tope/2)-1))
			{
			for (iterador = pos; iterador <= (tope - pos-1); iterador++)
				{
				if (vector[iterador] > vector[pmax]){pmax = iterador;}
				if (vector[iterador] < vector[pmin]){pmin = iterador;}
				}
			temp = vector[pos];
			if (pmax == pos)
				{pmaxaux = pmin;}
			else
				{pmaxaux = pmax;}
			vector[pos] = vector[pmin];
			vector[pmin] = temp;
			temp = vector[tope-pos-1];
			vector[tope-pos-1] = vector[pmaxaux];
			vector[pmaxaux] = temp;
			pos = pos + 1;
			}
		return vector;
		}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return int
	 * @comment Calcula el numero de cadenas entre los caracteres ,. ;: 
	*/
	public static int Palabras(String frase)
		{
		char car;
		int veces = 1;
		for (int i = 0; i < frase.length();i++)
			{
			car = frase.charAt(i);
			if ((car == ',') || (car == '.') || (car == ';') || (car == ':') || (car == ' '))
					{veces = veces + 1;}
			}
		return veces;
		}
	
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int a
	 * @param int b
	 * @param int c
	 * @return []double
	 * @comment Este algoritmo resuelve la ecuacion de segundo grado de la forma ax^2+bx+c y nos devuelve las soluciones en un vector de double.
	*/
	public static double[] Polinomio2(int a, int b, int c) 
	{
	double sol1 = 0;
	double sol2 = 0;
	double test = -(b^2)-(4*a*c);
	if (test >= 0)
		{
		test = Math.sqrt(test);
		sol1 = (-b + test)/2*a;
		sol2 = (-b - test)/2*a;
		}
	double sols[] ={sol1, sol2};
	return sols;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param double
	 * @param double
	 * @param int
	 * @return Double
	 * @comment calcula el precio de un pasaje, segun precio, km y dias
	*/
	public static double PrecioPasaje(double precio, double km, int dias)
	{
	double total;
	total = precio * km;
	if (dias >= 8){total = total * 0.2;}
	return total;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @return boolean
	 * @comment indica si el numero recibido es primo o no
	*/
	public static boolean Primo(int numero)
		{
		boolean primo = true;
		int i = 2;
		while ((primo == true) && (Math.pow(i, 2)<= numero))
			{
			if ((numero % i) == 0)
				{primo = false;}
			i++;
			}
		return primo;
		}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @return String
	 * @comment Devuelve el numero recibido rellenando con 0 a la izquierda hasta tener 8 cifras o las ultimas 8 cifras en caso de tener mas
	*/
	public static String Rellena8(int numero)
		{
		int espacios; String res = ""; int cifras = Cifras(numero);
		if (cifras>8){numero = numero % 100000000;}
		espacios = 8-cifras;
		for (int i=1; i<= espacios; i++)
			{res = "0"+res;}
		res = res + numero;
		return res;
		}
	
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return int
	 * @comment Devuelve los dos primeros caracteres de una hora. Supuestamente los digitos de la hora
	*/
	public static int SHoraH(String hora) throws Exception
	{
	return _cstoi(hora.substring(0, 2));
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return int
	 * @comment Devuelve los dos segundos caracteres de una hora. Supuestamente los digitos de los minutos
	*/
	public static int SHoraM(String hora) throws Exception
	{
	return _cstoi(hora.substring(3, 5));
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return int
	 * @comment Devuelve los dos terceros caracteres de una hora. Supuestamente los digitos de los segundos
	*/
	public static int SHoraS(String hora) throws Exception
	{
	return _cstoi(hora.substring(6, 8));
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return int
	 * @comment Devuelve los 4 ultimos caracteres de una fecha. Supuestamente los digitos del año
	*/
	public static int SFechaAnyo(String fecha)
	{
	return _cstoi(fecha.substring(6, 10));
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return int
	 * @comment Devuelve los dos primeros caracteres de una fecha. Supuestamente los digitos del dia
	*/
	public static int SFechaDia(String fecha)
	{
	return _cstoi(fecha.substring(0, 2));
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param String
	 * @return int
	 * @comment Devuelve los dos segundos caracteres de una fecha. Supuestamente los digitos del mes
	*/
	public static int SFechaMes(String fecha)
	{
	return _cstoi(fecha.substring(3, 5));
	}

	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @return int
	 * @comment Devuelve la suma, de una cifra, de las cifras de un numero 
	*/
	public static int SumaCifras(int numero)
	{
	int cifras, cifra, potencia;
	while (numero > 9)
		{
		int sol = 0;
		cifras = Cifras(numero);
		potencia = (int)Math.pow(10, cifras-1); 
		for (int i = 1; i<=cifras; i++)
			{
			cifra = ((numero - (numero % potencia)) / potencia);
			sol = sol + cifra;
			numero = numero - (cifra * potencia);
			potencia = potencia / 10;
			}
		numero = sol;
		}
	return numero;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param []String
	 * @return int
	 * @comment Suma los elementos existentes en la dimension indicada
	*/
	public static int SumaElementosDimension(String[][] matriz, int dimension)
	{
	int total = 0, ite;
	for (ite=0; ite<matriz.length; ite++)
		{total = total + _cstoi(matriz[ite][dimension]);}
	return total;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param []int
	 * @return int
	 * @comment realiza la suma de los numeros que se introducen por teclado y nos devuelve el total de la suma.
	*/
	public static int SumaNumeros()
	{
	int total = 0, numero;
	boolean sal = false;
	do
		{
		try
			{
			numero = _cstoi(LeerTeclado());
			total = total + numero;
			}
		catch
			(Exception e){sal = true;}
		}
	while(sal == false);
	return total;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param []int
	 * @return int
	 * @comment realiza la suma de los numeros que recibe en el array de enteros y nos devuelve el total de la suma.
	*/
	public static int SumaNumeros(int [] valores)
	{
    int suma = 0;
	int numeros = valores.length;
	for(int i=1; i<=numeros; i++)
		{
		suma = suma + valores[i-1];
		}
	return suma;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int
	 * @param int
	 * @return int
	 * @comment suma los numeros existentes entre dos numeros
	*/
	public static int SumaPares(int minimo, int maximo)
	{
	int total = 0, ite;
	for (ite = minimo; ite <= maximo; ite=+2)
		{
		total = total + ite;
		}
	return total;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int[]
	 * @return int[]
	 * @comment calcula la suma y media de los numeros contenidos en el vector recibido
	*/
	public static int[] SumaYMedia(int[] vector)
	{
	int suma = 0, ite;
	int [] sol = new int[2];
	for (ite = 0; ite <= vector.length; ite++)
		{
		suma = suma + vector[ite];
		}
	sol[0] = suma;
	sol[1] = suma/vector.length;
	return sol;
	}
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param int Mes
	 * @param int Anyo
	 * @return int
	 * @comment Devuelve el numero del dia ultimo del mes y años indicados 
	*/
	public static int UltimoDiaMes(int mes, int anyo)
	{
	int dia = 28; boolean valido = true;
	while ((dia < 31) && (valido == true))
		{
		dia++;
		valido = ExisteDiaMes(dia, mes, anyo);
		if (valido == false){dia = dia - 1;}
		}
	return dia;
	}
	
	/**
	 * @author Roberto Soriano
	 * @version 1.1
	 * @param Object[]
	 * @return String
	 * @comment Muestra los valores de un vector 
	*/
	public static String VectorToString(Object[] vec)
		{
		String s = "";
		for (int x = 0; x<vec.length;x++)
			{
			s += vec[x]+",";
			}
		s=s.substring(0, s.length()-1);
		return s;
		}

	public static String respuestasAleatorias(int x){
		String fields, sol;
		int pos, seed;
		Random aleatorios = new Random(); //Semilla para buscar preguntas aleatorias
		fields = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		fields = fields.substring(0, x);
		sol = "";
		for (seed=1; seed <= x; seed++){
			pos = aleatorios.nextInt(x+1-seed);
			sol = sol + fields.charAt(pos);
			fields = fields.replaceAll(fields.charAt(pos)+"","");
		}
		return sol;
	}
	
}
