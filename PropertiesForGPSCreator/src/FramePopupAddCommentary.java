import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;

@SuppressWarnings("serial")
public class FramePopupAddCommentary extends JFrame {
	public static JTextField textField_1;
	String[] txtButton = { "Add ", "Ajouter " };
	private static int typeIndex;
	ImageIcon imageForOne = new ImageIcon(getClass().getResource("ressources/coeur.jpg"));
	JButton btnNewButton;
	boolean isAddOpen;
	JSpinner spinner;

	public FramePopupAddCommentary(int typeIndexConstructor) {
		PropertiesAccess.loadAll();
		typeIndex = typeIndexConstructor;
		setTitle(txtButton[Utils.langId] + Utils.type[Utils.langId][typeIndex].toLowerCase());
		PropertiesAccess.nbOfIdUse = idInProp();
		setPreferredSize(new Dimension(255, 220));
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("id : " + (PropertiesAccess.nbOfIdUse[typeIndex]));
		JLabel lblType = new JLabel(Utils.type[Utils.langId][typeIndex] + " : ");
		textField_1 = new JTextField();
		btnNewButton = new JButton(imageForOne);
		btnNewButton.setText(txtButton[Utils.langId] + Utils.type[Utils.langId][typeIndex].toLowerCase());
		btnNewButton.setHorizontalAlignment(AbstractButton.CENTER);
		btnNewButton.setHorizontalTextPosition(0);
		btnNewButton.setBounds(55, 113, imageForOne.getIconWidth(), imageForOne.getIconHeight());
		lblId.setBounds(100, 10, 45, 15);
		lblType.setBounds(10, 36, 90, 15);
		lblType.setHorizontalAlignment(JLabel.RIGHT);
		textField_1.setBounds(110, 33, 85, 20);
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

		JLabel lblNetwork = new JLabel("Network : ");
		lblNetwork.setForeground(Color.WHITE);
		lblNetwork.setBounds(10, 65, 90, 15);
		lblNetwork.setHorizontalAlignment(AbstractButton.RIGHT);
		getContentPane().add(lblNetwork);

		String[] networkId = new String[100];
		for (int i = 0; i < networkId.length; i++) {
			networkId[i] = "" + i;
		}
		SpinnerListModel networkModel = new SpinnerListModel(networkId);
		spinner = new JSpinner(networkModel);
		spinner.setBounds(110, 64, 85, 20);
		getContentPane().add(spinner);

		setVisible(true);

	}

	public void validAddingId() {
		if (!textField_1.getText().isEmpty()) {
			if (typeIndex == 0 || typeIndex == 1 || typeIndex == 2) {
				PropertiesAccess.saveTheIdinProp(typeIndex, Integer.parseInt(spinner.getValue().toString()));
				dispose();
				System.gc();
				Main.f.isOpen = false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Your " + getTitle().toLowerCase() + " is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public int[] idInProp() {
		PropertiesAccess.loadAll();
		int s = 1, r = 1, c = 1;
		if (Utils.s != null) s = Utils.s.length;
		if (Utils.r != null) s = Utils.r.length;
		if (Utils.c != null) s = Utils.c.length;
		int a[] = { s, r, c };
		return a;
	}
}
