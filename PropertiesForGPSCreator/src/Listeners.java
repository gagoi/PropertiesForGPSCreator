import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Listeners implements ActionListener, WindowListener, MouseListener {

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
		switch (index) {
		case 0:
			// Quit
			System.exit(-1);
			break;
		case 1:
			// Help
			break;
		case 2:
			// Translate in english
			Utils.langId = 0;
			Utils.language = "en";
			Main.f.repaint();
			break;
		case 3:
			// Translate in french
			Utils.langId = 1;
			Utils.language = "fr";
			Main.f.repaint();
			break;
		case 4:
			// Add subject
			Main.f.isOpen = true;
			Main.f.popupAdd = new FramePopup(0);
			break;
		case 5:
			// Add room
			Main.f.isOpen = true;
			Main.f.popupAdd = new FramePopup(1);
			break;
		case 6:
			// Add commentary
			Main.f.isOpen = true;
			Main.f.popupAdd = new FramePopup(2);
			break;
		case 7:
			// Set subject
			Main.f.isOpen = true;
			Main.f.pm.setVisible(false);
			Main.f.popupSet = new FramePopupSet(0);
			break;
		case 8:
			// Set room
			Main.f.isOpen = true;
			Main.f.pm.setVisible(false);
			Main.f.popupSet = new FramePopupSet(1);
			break;
		case 9:
			// Set commentary
			Main.f.isOpen = true;
			Main.f.pm.setVisible(false);
			Main.f.popupSet = new FramePopupSet(2);
			break;
		default:
			if (Main.f.isOpen == true) {
				Main.f.popupAdd.validAddingId();
			} else if (Main.f.popupSet != null && src == Main.f.popupSet.btnNewButton) Main.f.popupSet.onButtonClicked();
			break;
		}
		System.out.println("isOpen = " + Main.f.isOpen);
		index = -1;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		Main.f.isOpen = false;
		System.out.println("isOpen = " + Main.f.isOpen);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("----Mouse clicked");
		Main.f.updateTp();
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
}
