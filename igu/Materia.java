package igu;

import generic.Registro;

import java.util.Comparator;


public class Materia implements Registro{
	private int id;
	private String materia;
	
	public Materia(){
		this.id = 0;
		this.materia = "";
	}
	
	public Materia(int i, String a){
		this.id = i;
		this.materia = a;
	}
	
	public void setAllFromArray(Object[] o){
		try{
			this.id = new Integer((String)o[0]).intValue();
			this.materia = (String)o[1];
		}
		catch(Exception e){
			this.id = 0;
			this.materia = "";
		}
	}
	
	public Object[] toArray(){
		Object[] o = new Object[2];
		o[0] = this.id;
		o[1] = this.materia;
		return o;
	}
	
	public String getMateria() {
		return materia;
	}
	
	public void setMateria(String materia) {
		this.materia = materia;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}

class MateriaComparator implements Comparator{
	protected int m_sortCol;
	protected boolean m_sortAsc;

	public MateriaComparator(int sortCol, boolean sortAsc) {
		m_sortCol = sortCol;
		m_sortAsc = sortAsc;
	}

	public int compare(Object o1, Object o2) {
		if(!(o1 instanceof Materia) || !(o2 instanceof Materia))
			return 0;
		Materia s1 = (Materia)o1;
		Materia s2 = (Materia)o2;
		int result = 0;
		switch (m_sortCol) {
		case 0:
			Integer Int1 = (Integer)s1.getId();
			Integer Int2 = (Integer)s2.getId();
			result = Int1.compareTo(Int2);
			break;
		case 1:
			result = s1.getMateria().compareTo(s2.getMateria());
			break;
		}
		if (!m_sortAsc)
			result = -result;
		return result;
	}

	public boolean equals(Object obj) {
		if (obj instanceof MateriaComparator) {
			MateriaComparator compObj = (MateriaComparator)obj;
			return (compObj.m_sortCol==m_sortCol) && (compObj.m_sortAsc==m_sortAsc);
		}
		return false;
	}
}
