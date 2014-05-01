import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FramePopup extends JFrame {
	public static JTextField textField_1;
	String[] txtButton = { "Add", "Ajouter" };
	private static int typeIndex;
	ImageIcon imageForOne = new ImageIcon(getClass().getResource("ressources/coeur.jpg"));
	JButton btnNewButton;
	boolean isAddOpen;

	public FramePopup(int typeIndexConstructor) {
		// LoadProperties.loadPropertiesId();
		PropertiesAccess.loadAll();
		typeIndex = typeIndexConstructor;
		if (Utils.langId == 1) FramePopup.typeIndex += 3;
		setResizable(false);
		setTitle("Add " + Utils.type[typeIndex].toLowerCase());
		// WriteProperties.nbOfIDUse = idInProp();
		PropertiesAccess.nbOfIdUse = idInProp();
		setPreferredSize(new Dimension(250, 150));
		getContentPane().setLayout(null);

//		JLabel lblId = new JLabel("id : " + WriteProperties.next[typeIndex]);
		JLabel lblId = new JLabel("id : " + PropertiesAccess.nbOfIdUse[typeIndex]+1);
		JLabel lblType = new JLabel(Utils.type[typeIndex] + " : ");
		textField_1 = new JTextField();
		btnNewButton = new JButton(imageForOne);
		btnNewButton.setText(txtButton[Utils.langId] + " " + Utils.type[typeIndex].toLowerCase());
		btnNewButton.setHorizontalAlignment(AbstractButton.CENTER);
		btnNewButton.setHorizontalTextPosition(0);
		btnNewButton.setBounds(49, 66, imageForOne.getIconWidth(), imageForOne.getIconHeight());
		lblId.setBounds(100, 10, 45, 15);
		lblType.setBounds(10, 36, 90, 15);
		lblType.setHorizontalAlignment(JLabel.RIGHT);
		textField_1.setBounds(108, 36, 85, 20);
		textField_1.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == Event.ENTER) validAddingId();
			}
		});

		btnNewButton.addActionListener(new Listeners());

		getContentPane().add(lblId);
		getContentPane().add(lblType);
		getContentPane().add(textField_1);
		getContentPane().add(btnNewButton);

		textField_1.setColumns(10);
		pack();
		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("ressources/iconGPS.png"));
		setIconImage(icon);

		lblId.setForeground(Color.WHITE);
		lblType.setForeground(Color.WHITE);
		textField_1.setBackground(Color.BLACK);
		textField_1.setForeground(Color.WHITE);
		getContentPane().setBackground(Color.BLACK);

		setVisible(true);

	}

	public void validAddingId() {
		if (!textField_1.getText().isEmpty()) {
			if (typeIndex == 0 || typeIndex == 1 || typeIndex == 2) {
				// WriteProperties.saveId(typeIndex);
				PropertiesAccess.saveTheIdinProp(typeIndex);
				dispose();
				System.gc();
				Main.f.isOpen = false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Your " + getTitle().toLowerCase() + " is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public int[] idInProp() {
//		LoadProperties.loadPropertiesId();
		PropertiesAccess.loadAll();
		int s = 1, r = 1, c = 1;
//		if (LoadProperties.subject != null) s = LoadProperties.subject.length;
//		if (LoadProperties.room != null) r = LoadProperties.room.length;
//		if (LoadProperties.commentary != null) c = LoadProperties.commentary.length;
		if(PanelGrid.subject != null) s = PanelGrid.subject.length;
		if(PanelGrid.room != null) s = PanelGrid.room.length;
		if(PanelGrid.commentary != null) s = PanelGrid.commentary.length;
		int a[] = { s, r, c };
		return a;
	}
}
