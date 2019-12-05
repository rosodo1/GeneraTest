package igu;

import generic.Registro;

import java.util.Comparator;

public class Asignatura implements Registro{
	private int id;
	private String asignatura;
	
	public Asignatura(){
		this.id = 0;
		this.asignatura = "";
	}
	
	public Asignatura(int i, String a){
		this.id = i;
		this.asignatura = a;
	}
	
	public void setAllFromArray(Object[] o){
		try{
			this.id = new Integer((String)o[0]).intValue();
			this.asignatura = (String)o[1];
		}
		catch(Exception e){
			this.id = 0;
			this.asignatura = "";
		}
	}
	
	public Object[] toArray(){
		Object[] o = new Object[2];
		o[0] = this.id;
		o[1] = this.asignatura;
		return o;
	}
	
	public String getAsignatura() {
		return asignatura;
	}
	
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}

class AsignaturaComparator implements Comparator{
	protected int m_sortCol;
	protected boolean m_sortAsc;

	public AsignaturaComparator(int sortCol, boolean sortAsc) {
		m_sortCol = sortCol;
		m_sortAsc = sortAsc;
	}

	public int compare(Object o1, Object o2) {
		if(!(o1 instanceof Asignatura) || !(o2 instanceof Asignatura))
			return 0;
		Asignatura s1 = (Asignatura)o1;
		Asignatura s2 = (Asignatura)o2;
		int result = 0;
		switch (m_sortCol) {
		case 0:
			Integer Int1 = (Integer)s1.getId();
			Integer Int2 = (Integer)s2.getId();
			result = Int1.compareTo(Int2);
			break;
		case 1:
			result = s1.getAsignatura().compareTo(s2.getAsignatura());
			break;
		}
		if (!m_sortAsc)
			result = -result;
		return result;
	}

	public boolean equals(Object obj) {
		if (obj instanceof AsignaturaComparator) {
			AsignaturaComparator compObj = (AsignaturaComparator)obj;
			return (compObj.m_sortCol==m_sortCol) && (compObj.m_sortAsc==m_sortAsc);
		}
		return false;
	}
}
