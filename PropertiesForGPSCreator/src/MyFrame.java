import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.JTableHeader;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	static JMenuItem items[] = new JMenuItem[15];
	JMenu menus[] = new JMenu[4];
	JTable tab;
	JMenuBar mb = new JMenuBar();
	JTextPane tp = new JTextPane();
	JPopupMenu pm = new JPopupMenu();
	JPanel panel = new JPanel();
	FramePopup popupAdd;
	FramePopupSet popupSet;
	public boolean isOpen, isSetOpen, isAddOpen;

	String[][] itemsNames = {
			{ "Quit", "Help", "English", "Français", "Add subject", "Add room", "Add commentary", "Set subject", "Set room", "Set commentary", "Group A", "Group B", "Group 1", "Group 2",
					"Group 3" },
			{ "Quitter", "Aide", "English", "Français", "Ajouter une matière", "Ajouter une salle", "Ajouter un commentaire", "Mettre une matière", "Mettre une salle",
					"Mettre un commentaire", "Groupe A", "Groupe B", "Groupe 1", "Groupe 2", "Groupe 3" } };
	String[][] menusNames = { { "File", "Add things", "Other", "Languages" }, { "Fichier", "Ajouter ID", "Autres", "Langages" } };
	String[][] columnsNames = { { "Hours", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" },
			{ "Heures", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" } };
	String[] titleNames = { "GPS properties editor", "GPS configuration éditeur" };
	int row, column, idS, idR, idC, idW;

	Color b = Color.BLACK, w = Color.WHITE;
	Font font = new Font("Kristen ITC", Font.BOLD, 15);

	public MyFrame() {
		tabSetup();
		createItems();
		panel.setLayout(getContentPane().getLayout());
		setContentPane(panel);

		setResizable(true);
		setTitle(titleNames[Utils.langId]);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane scrollPane = new JScrollPane(tab);
		scrollPane.setBackground(b);
		scrollPane.getVerticalScrollBar().setBackground(Color.DARK_GRAY);

		getContentPane().add(tab.getTableHeader(), BorderLayout.NORTH);
		getContentPane().add(scrollPane);
		getContentPane().add(tp, BorderLayout.SOUTH);
		tp.setText("I love Anna <3<3<3");

		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("ressources/iconGPS.png"));
		setIconImage(icon);

		setPreferredSize(new Dimension(1500, 720));
		setMaximumSize(new Dimension(tab.getX(), tab.getY()));
		pack();
		variableInit();
		updateTp();
		popupMenu();
		menusSetup();
		setVisible(true);
		repaint();
	}

	@SuppressWarnings("unused")
	private void variableInit() {
		int row = tab.getSelectedRow(), column = tab.getSelectedColumn(), idS = PanelGrid.idSubjectPerCell[row][column], idR = PanelGrid.idRoomPerCell[row][column], idC = PanelGrid.idCommentaryPerCell[row][column], idW = PanelGrid.weekIdPerCell[row][column];
	}

	private void createItems() {
		// Create all items for menus like popup or menu bar. Set the name of
		// each item using langId and item id in array : items[].
		for (int i = 0; i < items.length; i++) {
			items[i] = new JMenuItem(itemsNames[Utils.langId][i]);
			items[i].setBackground(b);
			items[i].setForeground(w);
			items[i].addActionListener(new Listeners());

		}
		// Create all menus which used in popup or menu bar. Set the name of
		// each item using langId and item id in array : menus[].
		for (int i = 0; i < menus.length; i++) {
			menus[i] = new JMenu(menusNames[Utils.langId][i]);
			menus[i].setBackground(b);
			menus[i].setForeground(w);
			menus[i].setOpaque(true);
		}
		// Set the name of table header using langId and item id in array :
		// columnsNames[].
		for (int i = 0; i < columnsNames.length; i++) {
			tab.getColumnModel().getColumn(i).setHeaderValue(columnsNames[Utils.langId][i]);
		}
		// Set properties of JTextPane tp.
		tp.setForeground(w);
		tp.setBackground(b);
		tp.setFont(font);
		// Set properties of JMenuBar mb.
		mb.setForeground(w);
		mb.setBackground(b);
	}

	private void tabSetup() {
		// Setup tab with a special constructor to set cell uneditable
		tab = new JTable(PanelGrid.values, PanelGrid.columnName) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		// Set properties of the table.
		JTableHeader th = tab.getTableHeader();
		th.setReorderingAllowed(false);
		th.setResizingAllowed(false);
		th.setBackground(b);
		th.setForeground(w);
		tab.setBackground(b);
		tab.setForeground(w);
		tab.setOpaque(true);
		tab.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tab.changeSelection(0, 0, false, false);
		tab.setRowSelectionAllowed(false);
		// Set a new CellRenderer. It's using to set background color of a cell
		// in function of parameters. See MyCellRenderer.class for more
		// informations.
		tab.setDefaultRenderer(Object.class, new MyCellRenderer());
		// Set listener in class Listeners, where all listeners are.
		tab.addMouseListener(new Listeners());
	}

	private void popupMenu() {
		pm.add(items[7]);
		pm.add(items[8]);
		pm.add(items[9]);
	}

	private void menusSetup() {
		menus[0].add(items[0]);// Menu : File, add item : Quit
		menus[1].add(items[4]);// Menu : Add things, add item : Add subject
		menus[1].add(items[5]);// Menu : Add things, add item : Add room
		menus[1].add(items[6]);// Menu : Add things, add item : Add commentary
		menus[2].add(items[1]);// Menu : Other, add item : Help
		menus[2].add(menus[3]);// Menu : Other, add menu : Languages
		menus[3].add(items[2]);// Menu : Languages, add item : English
		menus[3].add(items[3]);// Menu : Languages, add item : Français
		// Add menus to JMenuBar mb
		mb.add(menus[0]);
		mb.add(menus[1]);
		mb.add(menus[2]);
		// Add the JMenuBar mb to the frame
		setJMenuBar(mb);
		mb.repaint();
	}

	void updateTp() {
		// Set the debug text in the JTextPane tp. And repaint it after.
		tp.setText("I love Anna <3<3 " + " || Selected cell [" + row + ";" + column + "] || Subject : " + PanelGrid.subjectPerCell[row][column] + "(" + idS + ") | Room : "
				+ PanelGrid.roomPerCell[row][column] + "(" + idR + ") | Commentary : " + PanelGrid.commentaryPerCell[row][column] + "(" + idC + ") | Week : " + PanelGrid.weekName[idW] + "("
				+ idW + ") | ");
		tp.repaint();
		this.repaint();
	}

	void updateValues() {
		this.row = Main.f.tab.getSelectedRow();
		this.column = Main.f.tab.getSelectedColumn();
		this.idS = PanelGrid.idSubjectPerCell[row][column];
		this.idR = PanelGrid.idRoomPerCell[row][column];
		this.idC = PanelGrid.idCommentaryPerCell[row][column];
	}
}