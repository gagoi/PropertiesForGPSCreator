import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class FramePopupSet extends JFrame {
	String[] truc = { "truc1", "truc2", "truc3" };
	@SuppressWarnings("rawtypes")
	JList list, listGroup, listWeek;
	JLabel lblType = new JLabel();
	JLabel lblGroup = new JLabel();
	JLabel lblWeek = new JLabel();
	String txt[] = { "Week ", "Semaine " };
	String txt2[] = { "Group ", "Groupe " };
	String txt3[] = { "Set ", "Mettre" };
	private int typeIndex;
	JButton btnNewButton;
	boolean isSetOpen;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FramePopupSet(int typeIndex) {
		this.typeIndex = typeIndex;
		setResizable(false);
		setTitle(Utils.type[Utils.langId][typeIndex]);
		setSize(new Dimension(500, 600));
		getContentPane().setLayout(null);
		lblType.setText(Utils.type[Utils.langId][typeIndex] + " : ");

		ImageIcon imageForOne = new ImageIcon(getClass().getResource("ressources/coeur.jpg"));
		btnNewButton = new JButton(txt3[Utils.langId] + Utils.type[Utils.langId][typeIndex].toLowerCase());
		btnNewButton.setIcon(imageForOne);
		btnNewButton.setHorizontalAlignment(AbstractButton.CENTER);
		btnNewButton.setHorizontalTextPosition(0);
		btnNewButton.setBounds(166, 505, imageForOne.getIconWidth(), imageForOne.getIconHeight());
		btnNewButton.addActionListener(new Listeners());
		getContentPane().add(btnNewButton);

		lblType.setBounds(210, 25, 130, 15);
		getContentPane().add(lblType);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 45, 420, 115);
		list = new JList(setListByType());
		scrollPane.setViewportView(list);
		getContentPane().add(scrollPane);

		lblGroup.setText(txt2[Utils.langId] + ":");
		lblGroup.setBounds(220, 185, 50, 15);
		getContentPane().add(lblGroup);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(40, 205, 420, 115);
		getContentPane().add(scrollPane_1);

		listGroup = new JList(PanelGrid.groupName);
		listGroup.setSelectedIndex(0);
		scrollPane_1.setViewportView(listGroup);

		lblWeek.setText(txt[Utils.langId] + ":");
		lblWeek.setBounds(200, 345, 70, 15);
		getContentPane().add(lblWeek);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(40, 365, 420, 115);
		getContentPane().add(scrollPane_2);

		listWeek = new JList(PanelGrid.groupName);
		listWeek.setSelectedIndex(0);
		scrollPane_2.setViewportView(listWeek);

		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("ressources/iconGPS.png"));
		setIconImage(icon);
		// getContentPane().setBackground(Color.PINK);
		// list.setBackground(Color.RED);
		// listGroup.setBackground(Color.RED);
		// listWeek.setBackground(Color.RED);
		// list.setCellRenderer(new SelectedListCellRenderer());
		// listGroup.setCellRenderer(new SelectedListCellRenderer());
		// listWeek.setCellRenderer(new SelectedListCellRenderer());
		list.setBackground(Color.BLACK);
		list.setForeground(Color.WHITE);
		listGroup.setBackground(Color.BLACK);
		listGroup.setForeground(Color.WHITE);
		listWeek.setBackground(Color.BLACK);
		listWeek.setForeground(Color.WHITE);
		lblType.setForeground(Color.WHITE);
		lblGroup.setForeground(Color.WHITE);
		lblWeek.setForeground(Color.WHITE);
		btnNewButton.setForeground(Color.BLACK);
		getContentPane().setBackground(Color.BLACK);
		addWindowListener(new Listeners());
		setVisible(true);
		repaint();

	}

	public void onButtonClicked() {
		if (!list.isSelectionEmpty() || (list.getSelectedIndex() + 1) != 0) {
			System.out.println("Valider " + getTitle().toLowerCase());
			setSelectedValue_New();
			int x = Main.f.tab.getSelectedRow();
			int y = Main.f.tab.getSelectedColumn();
			int a = listGroup.getSelectedIndex();
			int b = listWeek.getSelectedIndex();
			switch (typeIndex) {
			case 0:
				PanelGrid.getItemOf(x, y, a, b).setSubject((list.getSelectedIndex() + 1));
				break;
			case 1:
				PanelGrid.getItemOf(x, y, a, b).setRoom((list.getSelectedIndex() + 1));
				break;
			case 2:
				PanelGrid.getItemOf(x, y, a, b).setCommentary((list.getSelectedIndex() + 1));
				break;

			default:
				break;
			}
			PanelGrid.updateTab(x, y);
			PropertiesAccess.saveThePlanInProp(x, y, a, b);
		}
	}

	public String[] setListByType() {
		if (typeIndex == 0 && Utils.sList != null)
			return Utils.sList;
		else if (typeIndex == 1 && Utils.rList != null)
			return Utils.rList;
		else if (typeIndex == 2 && Utils.cList != null)
			return Utils.cList;
		else
			return truc;
	}

	public void setSelectedValue_New() {
		System.out.println("Value selected : " + (list.getSelectedValue()) + "(" + ((list.getSelectedIndex() + 1) + 1) + ")");
		System.out.println("Group - value selected : " + listGroup.getSelectedValue() + "(" + listGroup.getSelectedIndex() + ")");
		System.out.println("Week - value selected : " + listWeek.getSelectedValue() + "(" + listWeek.getSelectedIndex() + ")");

		int x = Main.f.tab.getSelectedRow();
		int y = Main.f.tab.getSelectedColumn();
		int a = listGroup.getSelectedIndex();
		int b = listWeek.getSelectedIndex();
		Item item = PanelGrid.getItemOf(x, y, a, b);
		System.out.println(item.toStringPane());
		if (typeIndex == 0) {
			item.setSubject((list.getSelectedIndex() + 1));
		} else if (typeIndex == 1) {
			item.setRoom((list.getSelectedIndex() + 1));
		} else if (typeIndex == 2) {
			item.setCommentary((list.getSelectedIndex() + 1));
		} else
			Main.f.tab.setValueAt("Error !!", x, y);
		Main.f.updateTP(x, y);
		Main.f.repaint();
		dispose();
		System.gc();
	}
}