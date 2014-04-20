import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class MyCellRenderer extends DefaultTableCellRenderer {
	@SuppressWarnings("unused")
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		} else if (PanelGrid.weekIdPerCell[row][column] == 1) {
			super.setBackground(new Color(255, 200, 255));

		} else if (PanelGrid.weekIdPerCell[row][column] == 2) {
			super.setBackground(new Color(200, 255, 255));

		} else if (PanelGrid.weekIdPerCell[row][column] == 3) {
			super.setBackground(new Color(100, 255, 255));

		} else if (PanelGrid.weekIdPerCell[row][column] == 4) {
			super.setBackground(new Color(255, 100, 255));

		} else if (PanelGrid.weekIdPerCell[row][column] == 5) {
			super.setBackground(new Color(255, 255, 100));

		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}
		return this;
	}
}
