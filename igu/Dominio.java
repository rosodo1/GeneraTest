package igu;

import generic.Registro;

import java.util.Comparator;


public class Dominio implements Registro{
	private int id;
	
	public Dominio(){
		this.id = 0;
	}
	
	public Dominio(int i){
		this.id = i;
	}
	
	public void setAllFromArray(Object[] o){
		try{
			this.id = new Integer((String)o[0]).intValue();
		}
		catch(Exception e){
			this.id = 0;
		}
	}
	
	public Object[] toArray(){
		Object[] o = new Object[2];
		o[0] = this.id;
		return o;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}

class DominioComparator implements Comparator{
	protected int m_sortCol;
	protected boolean m_sortAsc;

	public DominioComparator(int sortCol, boolean sortAsc) {
		m_sortCol = sortCol;
		m_sortAsc = sortAsc;
	}

	public int compare(Object o1, Object o2) {
		if(!(o1 instanceof Dominio) || !(o2 instanceof Dominio))
			return 0;
		Dominio s1 = (Dominio)o1;
		Dominio s2 = (Dominio)o2;
		int result = 0;
		switch (m_sortCol) {
		case 0:
			Integer Int1 = (Integer)s1.getId();
			Integer Int2 = (Integer)s2.getId();
			result = Int1.compareTo(Int2);
			break;
		}
		if (!m_sortAsc)
			result = -result;
		return result;
	}

	public boolean equals(Object obj) {
		if (obj instanceof DominioComparator) {
			DominioComparator compObj = (DominioComparator)obj;
			return (compObj.m_sortCol==m_sortCol) && (compObj.m_sortAsc==m_sortAsc);
		}
		return false;
	}
}
