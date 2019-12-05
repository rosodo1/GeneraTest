package lib;

import bd.ModosBBDD;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Config implements Serializable{
	
	private static final long serialVersionUID = -7961903364808672317L;

	private String images = "";
	private String output = "";
	private ModosBBDD bbdd = ModosBBDD.MSACCESS;
	private String bdsource = "";
	private String bduser = "";
	private String bdpass = "";
	
	public Config(){
		bbdd = ModosBBDD.MSACCESS;
		//I:\ISACA\Certificaciones\GeneraTest
		images = "file:///C:/GeneraTest/images/";
		output = "C:\\GeneraTest\\";
		bdsource = "GeneraTest";
		bduser = "";
		bdpass = "";
	}
	
	public Config(String i, String o, ModosBBDD bd, String source, String user, String pass){
		images = i;
		output = o;
		bbdd = bd;
		bdsource = source;
		bduser = user;
		bdpass = pass;
	}

	public void guardar(){
        try{
        	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("config.dat"));
        	oos.writeObject(this);
        	oos.close();
        }
        catch (Exception e){}
	}
	
	public void cargar(){
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("config.dat"));
			Config x = (Config)ois.readObject();
			this.images = x.getImages();
			this.output = x.getOutput();
			this.bbdd = x.getBbdd();
			this.bdsource = x.getBdsource();
			this.bduser = x.getBduser();
			this.bdpass = x.bdpass;
			}
		catch (Exception e){}
	}
	
	public ModosBBDD getBbdd() {
		return bbdd;
	}

	public String getBdpass() {
		return bdpass;
	}

	public String getBdsource() {
		return bdsource;
	}

	public String getBduser() {
		return bduser;
	}

	public String getImages() {
		return images;
	}

	public String getOutput() {
		return output;
	}
}
