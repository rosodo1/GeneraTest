package lib;

public class HSDominiosAD{
	private static final long serialVersionUID = 1L;
	private int dominio;
	private int preguntas;
	public HSDominiosAD(int d, int p){
		dominio = d;
		preguntas = p;
	}

	public int getDominio() {
		return dominio;
	}
	
	public void setDominio(int dominio) {
		this.dominio = dominio;
	}
	
	public int getPreguntas() {
		return preguntas;
	}
	
	public void setPreguntas(int preguntas) {
		this.preguntas = preguntas;
	}
	
}
