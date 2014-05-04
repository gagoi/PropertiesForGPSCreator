import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class MyCellRenderer extends DefaultTableCellRenderer {
	public static int x, y;

	@SuppressWarnings("unused")
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (isSelected) {
			setForeground(Color.GREEN);
			super.setBackground(Color.GREEN);
		} else if (row == x && column == y) {
			super.setBackground(Color.BLUE);

		} else if (PanelGrid_Old.weekIdPerCell[row][column] == 1) {
			super.setBackground(new Color(255, 200, 255));
			super.setForeground(Color.BLACK);
			
		} else if (PanelGrid_Old.weekIdPerCell[row][column] == 2) {
			super.setBackground(new Color(200, 255, 255));
			super.setForeground(Color.BLACK);
			
		} else if (PanelGrid_Old.weekIdPerCell[row][column] == 3) {
			super.setBackground(new Color(100, 255, 255));
			super.setForeground(Color.BLACK);

		} else if (PanelGrid_Old.weekIdPerCell[row][column] == 4) {
			super.setBackground(new Color(255, 100, 255));
			super.setForeground(Color.BLACK);

		} else if (PanelGrid_Old.weekIdPerCell[row][column] == 5) {
			super.setBackground(new Color(255, 255, 100));
			super.setForeground(Color.BLACK);

		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}
		return this;
	}
}
