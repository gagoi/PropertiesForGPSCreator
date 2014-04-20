import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

@SuppressWarnings("serial")
public class SelectedListCellRenderer extends DefaultListCellRenderer {
	@SuppressWarnings("rawtypes")
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (index == list.getSelectedIndex()) {
			c.setBackground(Color.BLUE);
//			c.setBackground(Color.PINK);
		} else
//			c.setBackground(Color.RED);
			c.setBackground(Color.GREEN);
		return c;
	}
}
