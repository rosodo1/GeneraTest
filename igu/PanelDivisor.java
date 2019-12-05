package igu;

import javax.swing.JSplitPane;
import java.awt.Component;


public class PanelDivisor extends JSplitPane{

	private static final long serialVersionUID = -8703551519088371874L;

	public PanelDivisor(Component c1, Component c2, int x, int y, int pos){
		this.setVisible(false);
		this.setBounds(0, 0, x, y);
		setOrientation(VERTICAL_SPLIT);
		setTopComponent(c1);
		setBottomComponent(c2);
		setDividerLocation(pos);
		setVisible(true);
	}
	
}
