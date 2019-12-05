package igu;

import generic.Registro;

import java.util.Comparator;

public class Temas implements Registro{
	private int dominio;
	private int preguntas;
	
	public Temas(){
		this.dominio = 0;
		this.preguntas = 0;
	}
	
	public Temas(int i, int n){
		this.dominio = i;
		this.preguntas = n;
	}
	
	public void setAllFromArray(Object[] o){
		try{
			this.dominio = new Integer((String)o[0]).intValue();
			this.preguntas = new Integer((String)o[1]).intValue();
		}
		catch(Exception e){
			this.dominio = 0;
			this.preguntas = 0;
		}
	}
	
	public Object[] toArray(){
		Object[] o = new Object[2];
		o[0] = this.dominio;
		o[1] = this.preguntas;
		return o;
	}
	
	public int getDominio() {
		return dominio;
	}
	
	public void setDominio(int d) {
		this.dominio = d;
	}
	
	public int getPreguntas() {
		return preguntas;
	}
	
	public void setPreguntas(int p) {
		this.preguntas = p;
	}
}

class TemasComparator implements Comparator{
	protected int m_sortCol;
	protected boolean m_sortAsc;

	public TemasComparator(int sortCol, boolean sortAsc) {
		m_sortCol = sortCol;
		m_sortAsc = sortAsc;
	}

	public int compare(Object o1, Object o2) {
		if(!(o1 instanceof Temas) || !(o2 instanceof Temas))
			return 0;
		Temas s1 = (Temas)o1;
		Temas s2 = (Temas)o2;
		int result = 0;
		switch (m_sortCol) {
		case 0:
			Integer Int1 = (Integer)s1.getDominio();
			Integer Int2 = (Integer)s2.getDominio();
			result = Int1.compareTo(Int2);
			break;
		case 1:
			Integer Int3 = (Integer)s1.getPreguntas();
			Integer Int4 = (Integer)s2.getPreguntas();
			result = Int3.compareTo(Int4);
			break;
		}
		if (!m_sortAsc)
			result = -result;
		return result;
	}

	public boolean equals(Object obj) {
		if (obj instanceof TemasComparator) {
			TemasComparator compObj = (TemasComparator)obj;
			return (compObj.m_sortCol==m_sortCol) && (compObj.m_sortAsc==m_sortAsc);
		}
		return false;
	}
}
