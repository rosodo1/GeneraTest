package igu;

import generic.Registro;
import lib.F;

import java.util.Comparator;
import java.util.Date;


public class Test implements Registro{
	private int id;
	private String fecha;
	
	public Test(){
		this.id = 0;
		this.fecha = F.FechaSimple(new Date());
	}
	
	public Test(int i, Date a){
		this.id = i;
		this.fecha = F.FechaSimple(a);;
	}
	
	public void setAllFromArray(Object[] o){
		try{
			this.id = new Integer((String)o[0]).intValue();
			this.fecha = F.FechaSimple((Date)o[1]);
		}
		catch(Exception e){
			this.id = 0;
			this.fecha = F.FechaSimple(new Date());
		}
	}
	
	public Object[] toArray(){
		Object[] o = new Object[2];
		o[0] = this.id;
		o[1] = this.fecha;
		return o;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setMateria(Date fecha2) {
		this.fecha = F.FechaSimple(fecha2);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}

class TestComparator implements Comparator{
	protected int m_sortCol;
	protected boolean m_sortAsc;

	public TestComparator(int sortCol, boolean sortAsc) {
		m_sortCol = sortCol;
		m_sortAsc = sortAsc;
	}

	public int compare(Object o1, Object o2) {
		if(!(o1 instanceof Test) || !(o2 instanceof Test))
			return 0;
		Test s1 = (Test)o1;
		Test s2 = (Test)o2;
		int result = 0;
		switch (m_sortCol) {
		case 0:
			Integer Int1 = (Integer)s1.getId();
			Integer Int2 = (Integer)s2.getId();
			result = Int1.compareTo(Int2);
			break;
		case 1:
			result = s1.getFecha().compareTo(s2.getFecha());
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
