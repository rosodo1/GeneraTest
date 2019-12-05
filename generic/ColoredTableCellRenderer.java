package generic;



import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

public class ColoredTableCellRenderer extends DefaultTableCellRenderer
{
private static final long serialVersionUID = -2976463283815466710L;

public void setValue(Object value) 
  {
    if (value instanceof ColorData) {
      ColorData cvalue = (ColorData)value;
      setForeground(cvalue.m_color);
      setText(cvalue.m_data.toString());
    }
    else if (value instanceof IconData) {
      IconData ivalue = (IconData)value;
      setIcon(ivalue.m_icon);
      setText(ivalue.m_data.toString());
    }
    else
      super.setValue(value);
  }
}
class ColorData
{
  public Color  m_color;
  public Object m_data;
  public static Color GREEN = new Color(0, 128, 0);
  public static Color RED = Color.red;

  public ColorData(Fraction data) {
    m_color = data.doubleValue() >= 0 ? GREEN : RED;
    m_data  = data;
  }

  public ColorData(Color color, Object data) {
    m_color = color;
    m_data  = data;
  }
    
  public ColorData(Double data) {
    m_color = data.doubleValue() >= 0 ? GREEN : RED;
    m_data  = data;
  }
    
  public String toString() {
    return m_data.toString();
  }
}
class Fraction
{
  public int m_whole;
  public int m_nom;
  public int m_den;

  public Fraction(double value) {
    int sign = value <0 ? -1 : 1;
    value = Math.abs(value);
    m_whole = (int)value;
    m_den = 32;
    m_nom = (int)((value-m_whole)*m_den);
    while (m_nom!=0 && m_nom%2==0) {
      m_nom /= 2;
      m_den /= 2;
    }
    if (m_whole==0)
      m_nom *= sign;
    else
      m_whole *= sign;
  }

  public double doubleValue() {
    return (double)m_whole + (double)m_nom/m_den;
  }

  public String toString() {
    if (m_nom==0)
      return ""+m_whole;
    else if (m_whole==0)
      return ""+m_nom+"/"+m_den;
    else
      return ""+m_whole+" "+m_nom+"/"+m_den;
  }
}
class IconData
{
  public ImageIcon  m_icon;
  public Object m_data;

  public IconData(ImageIcon icon, Object data) {
    m_icon = icon;
    m_data = data;
  }
    
  public String toString() {
    return m_data.toString();
  }
}

