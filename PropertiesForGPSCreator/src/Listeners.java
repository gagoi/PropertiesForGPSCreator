import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Listeners implements ActionListener, WindowListener, MouseListener, KeyListener {
	JFrame fHelp = new JFrame("Help");

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int index = -1;
		System.out.println("\r--------------------");
		System.out.println("Action Performed || " + arg0.getSource());
		Object src = arg0.getSource();
		for (int i = 0; (i < MyFrame.items.length) && (index == -1); i++) {
			if (src == MyFrame.items[i]) index = i;
		}
		if (index != -1) System.out.println("Cliked item : " + MyFrame.items[index].getText() + "(" + index + ")");

		System.out.println("Index = " + index);
		PropertiesAccess.loadAll();
		switch (index) {
		case 0:
			// Quit
			System.exit(-1);
			break;
		case 1:
			// Help
			JLabel label = new JLabel(Utils.textHelp[Utils.langId]);
			label.setForeground(Color.WHITE);
			fHelp.getContentPane().setBackground(Color.black);
			fHelp.getContentPane().add(label);
			fHelp.setSize(new Dimension(450, 120));
			fHelp.setResizable(false);
			fHelp.setVisible(true);
			break;
		case 2:
			// Translate in english
			Utils.langId = 0;
			Utils.language = "en";
			Main.f.dispose();
			System.gc();
			Main.f = new MyFrame();
			break;
		case 3:
			// Translate in french
			Utils.langId = 1;
			Utils.language = "fr";
			Main.f.dispose();
			System.gc();
			Main.f = new MyFrame();
			break;
		case 4:
			// Add subject
			Main.f.isOpen = true;
			Main.f.isAddOpen = true;
			Main.f.popupAdd = new FramePopupAddCommentary(0);
			break;
		case 5:
			// Add room
			Main.f.isOpen = true;
			Main.f.isAddOpen = true;
			Main.f.popupAdd = new FramePopupAddCommentary(1);
			break;
		case 6:
			// Add commentary
			Main.f.isOpen = true;
			Main.f.isAddOpen = true;
			Main.f.popupAdd = new FramePopupAddCommentary(2);
			break;
		case 7:
			// Set subject
			// Verify if subjects are added before use they.
			if (Utils.existSubject) {
				Main.f.isOpen = true;
				Main.f.isSetOpen = true;
				Main.f.pm.setVisible(false);
				Main.f.popupSet = new FramePopupSet(0);
			} else
				JOptionPane.showMessageDialog(Main.f, "No subject in config file.", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case 8:
			// Set room
			// Verify if rooms are added before use they.
			if (Utils.existRoom) {
				Main.f.isOpen = true;
				Main.f.isSetOpen = true;
				Main.f.pm.setVisible(false);
				Main.f.popupSet = new FramePopupSet(1);
			} else
				JOptionPane.showMessageDialog(Main.f, "No room in config file.", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		case 9:
			// Set commentary
			// Verify if commentaries are added before use they.
			if (Utils.existCommentary) {
				Main.f.isOpen = true;
				Main.f.isSetOpen = true;
				Main.f.pm.setVisible(false);
				Main.f.popupSet = new FramePopupSet(2);
			} else
				JOptionPane.showMessageDialog(Main.f, "No commentary in config file.", "Error", JOptionPane.ERROR_MESSAGE);
			break;
		default:
			if (Main.f.isOpen) {
				if (Main.f.isAddOpen) {
					Main.f.popupAdd.validAddingId();
				} else if (Main.f.isSetOpen) {
					System.out.println("Set Popup validate");
					Main.f.popupSet.onButtonClicked();
				}
			}
			break;
		}
		System.out.println("isOpen = " + Main.f.isOpen);
		index = -1;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		Main.f.isOpen = false;
		System.out.println("isOpen = " + Main.f.isOpen);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("----Mouse clicked");
		if (Main.f.tab.columnAtPoint(arg0.getPoint()) != 0) {
			Main.f.updateValues();
			int x = Main.f.tab.rowAtPoint(arg0.getPoint());
			int y = Main.f.tab.columnAtPoint(arg0.getPoint());
			Main.f.tp.setText("Properties here : " + PanelGrid.toNumberOfProp(x, y) + "\r\r");
			Main.f.tp.setText(Main.f.tp.getText() + PanelGrid.toJTextPane(PanelGrid.getItemsArrayAt(x, y)));
			Main.f.tab.changeSelection(Main.f.tab.rowAtPoint(arg0.getPoint()), Main.f.tab.columnAtPoint(arg0.getPoint()), false, false);
			if (SwingUtilities.isRightMouseButton(arg0)) {
				System.out.println("Button right");
				Main.f.pm.show(Main.f.tab, arg0.getX(), arg0.getY());
			} else {
				System.out.println("Button Left");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int x = Main.f.tab.getSelectedRow();
		int y = Main.f.tab.getSelectedColumn();
		if (arg0.getKeyCode() == 38 && x != 0) x -= 1;
		if (arg0.getKeyCode() == 40 && x != 47) x += 1;
		if (arg0.getKeyCode() == 37 && y >= 1) y -= 1;
		if (arg0.getKeyCode() == 39 && y != 7) y += 1;
		Main.f.updateTP(x, y);
		Main.f.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
