package lib;

public class DPreguntas {

	private int dominio;
	private int hechas;
	private int porhacer;

	public DPreguntas(){
		dominio = 0;
		hechas = 0;
		porhacer = 0;
	}
	
	public DPreguntas(int d, int h, int p){
		dominio = d;
		hechas = h;
		porhacer = p;
	}
	
	public int getDominio() {
		return dominio;
	}
	public void setDominio(int dominio) {
		this.dominio = dominio;
	}
	public int getHechas() {
		return hechas;
	}
	public void setHechas(int hechas) {
		this.hechas = hechas;
	}
	public int getPorhacer() {
		return porhacer;
	}
	public void setPorhacer(int porhacer) {
		this.porhacer = porhacer;
	}
	
	
}
