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
public class FramePopupSet extends JFrame{
	String[] truc = { "truc1", "truc2", "truc3" };
	@SuppressWarnings("rawtypes")
	JList list, listGroup, listWeek;
	JLabel lblType = new JLabel();
	JLabel lblGroup = new JLabel();
	JLabel lblWeek = new JLabel();
	private int typeIndex;
	JButton btnNewButton;
	boolean isSetOpen;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FramePopupSet(int typeIndex) {
		this.typeIndex = typeIndex;
		setResizable(false);
		setTitle(Utils.type[typeIndex]);
		setSize(new Dimension(500, 600));
		getContentPane().setLayout(null);
		lblType.setText(Utils.type[typeIndex] + " : ");
		
		ImageIcon imageForOne = new ImageIcon(getClass().getResource("ressources/coeur.jpg"));
		btnNewButton = new JButton("Set " + Utils.type[typeIndex].toLowerCase());
		btnNewButton.setIcon(imageForOne);
		btnNewButton.setHorizontalAlignment(AbstractButton.CENTER);
		btnNewButton.setHorizontalTextPosition(0);
		if (Utils.language.equals("fr")) btnNewButton.setText("Mettre " + Utils.type[typeIndex].toLowerCase());
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

		lblGroup.setText("Group :");
		if(Utils.language.equals("fr")) lblGroup.setText("Groupe :");
		lblGroup.setBounds(220, 185, 50, 15);
		getContentPane().add(lblGroup);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(40, 205, 420, 115);
		getContentPane().add(scrollPane_1);

		listGroup = new JList(PanelGrid.groupName);
		listGroup.setSelectedIndex(0);
		scrollPane_1.setViewportView(listGroup);

		lblWeek.setText("Week :");
		if(Utils.language.equals("fr")) lblWeek.setText("Semaine :");
		lblWeek.setBounds(220, 345, 50, 15);
		getContentPane().add(lblWeek);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(40, 365, 420, 115);
		getContentPane().add(scrollPane_2);

		listWeek = new JList(PanelGrid.groupName);
		listWeek.setSelectedIndex(0);
		scrollPane_2.setViewportView(listWeek);

		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("ressources/iconGPS.png"));
		setIconImage(icon);
//		getContentPane().setBackground(Color.PINK);
//		list.setBackground(Color.RED);
//		listGroup.setBackground(Color.RED);
//		listWeek.setBackground(Color.RED);
//		list.setCellRenderer(new SelectedListCellRenderer());
//		listGroup.setCellRenderer(new SelectedListCellRenderer());
//		listWeek.setCellRenderer(new SelectedListCellRenderer());
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
		if (!list.isSelectionEmpty() || list.getSelectedIndex() == 0) {
			System.out.println("Valider " + getTitle().toLowerCase());
			setSelectedValue();
			int x = Main.f.tab.getSelectedRow();
			int y = Main.f.tab.getSelectedColumn();
			PanelGrid.updateTab(x, y, PanelGrid.subjectPerCell[x][y], PanelGrid.roomPerCell[x][y], PanelGrid.commentaryPerCell[x][y], PanelGrid.groupIdPerCell[x][y],
					PanelGrid.weekIdPerCell[x][y]);
			WriteProperties.modTabToProperties();
		}
	}

	public String[] setListByType() {
		if (typeIndex == 0 && LoadProperties.subject != null) {
			return LoadProperties.subject;
		} else if (typeIndex == 1 && LoadProperties.room != null)
			return LoadProperties.room;
		else if (typeIndex == 2 && LoadProperties.commentary != null)
			return LoadProperties.commentary;
		else
			return truc;
	}

	public void setSelectedValue() {
		System.out.println("Index selected : " + list.getSelectedIndex());
		System.out.println("Value selected : " + list.getSelectedValue());
		System.out.println("Group - index selected : " + listGroup.getSelectedIndex());
		System.out.println("Group - value selected : " + listGroup.getSelectedValue());
		System.out.println("Week - index selected : " + listWeek.getSelectedIndex());
		System.out.println("Week - value selected : " + listWeek.getSelectedValue());
		
		int x = Main.f.tab.getSelectedRow();
		int y = Main.f.tab.getSelectedColumn();
		if (typeIndex == 0) {
			PanelGrid.subjectPerCell[x][y] = (String) list.getSelectedValue();
			PanelGrid.idSubjectPerCell[x][y] = list.getSelectedIndex();
		} else if (typeIndex == 1) {
			PanelGrid.roomPerCell[x][y] = (String) list.getSelectedValue();
			PanelGrid.idRoomPerCell[x][y] = list.getSelectedIndex();
		} else if (typeIndex == 2) {
			PanelGrid.commentaryPerCell[x][y] = (String) list.getSelectedValue();
			PanelGrid.idCommentaryPerCell[x][y] = list.getSelectedIndex();
		} else
			Main.f.tab.setValueAt("Error !!", x, y);
		PanelGrid.groupIdPerCell[x][y] = listGroup.getSelectedIndex();
		PanelGrid.weekIdPerCell[x][y] = listWeek.getSelectedIndex();
		dispose();
		System.gc();
	}


//	@Override
//	public void windowClosing(WindowEvent e) {
//		int x = Frame_Old.tab.getSelectedRow();
//		int y = Frame_Old.tab.getSelectedColumn();
//		if (typeIndex == 0) {
//			PanelGrid.subjectPerCell[x][y] = null;
//			PanelGrid.idSubjectPerCell[x][y] = 0;
//		} else if (typeIndex == 1) {
//			PanelGrid.roomPerCell[x][y] = null;
//			PanelGrid.idRoomPerCell[x][y] = 0;
//		} else if (typeIndex == 2) {
//			PanelGrid.commentaryPerCell[x][y] = null;
//			PanelGrid.idCommentaryPerCell[x][y] = 0;
//		} else
//			Frame_Old.tab.setValueAt("Error !!", x, y);
//	}
}