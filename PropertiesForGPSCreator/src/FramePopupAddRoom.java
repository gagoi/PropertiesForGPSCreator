import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class FramePopupAddRoom extends JFrame {
	public static JTextField textField_1;
	String[] txtButton = { "Add ", "Ajouter " };
	ImageIcon imageForOne = new ImageIcon(getClass().getResource("ressources/coeur.jpg"));
	JButton btnNewButton;
	boolean isAddOpen;
	private JFormattedTextField textFieldX, textFieldY;
	private JLabel lblCoordX;
	KeyListener listener = new KeyListener() {

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
	};

	public FramePopupAddRoom() {
		PropertiesAccess.loadAll();
		setTitle(txtButton[Utils.langId] + Utils.type[Utils.langId][1].toLowerCase());
		PropertiesAccess.nbOfIdUse = idInProp();
		setPreferredSize(new Dimension(255, 220));
		getContentPane().setLayout(null);
		addWindowListener(new Listeners());

		JLabel lblId = new JLabel("id : " + (PropertiesAccess.nbOfIdUse[1]));
		JLabel lblType = new JLabel(Utils.type[Utils.langId][1] + " : ");
		textField_1 = new JTextField();
		btnNewButton = new JButton(imageForOne);
		btnNewButton.setText(txtButton[Utils.langId] + Utils.type[Utils.langId][1].toLowerCase());
		btnNewButton.setHorizontalAlignment(AbstractButton.CENTER);
		btnNewButton.setHorizontalTextPosition(0);
		btnNewButton.setBounds(55, 121, imageForOne.getIconWidth(), imageForOne.getIconHeight());
		lblId.setBounds(100, 10, 45, 15);
		lblType.setBounds(10, 36, 90, 15);
		lblType.setHorizontalAlignment(JLabel.RIGHT);
		textField_1.setBounds(110, 33, 85, 20);
		textField_1.addKeyListener(listener);

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

		NumberFormat format = NumberFormat.getIntegerInstance();
		format.setParseIntegerOnly(true);
		format.setMaximumIntegerDigits(9999);

		lblCoordX = new JLabel("Coordonate X");
		lblCoordX.setForeground(Color.WHITE);
		lblCoordX.setBounds(10, 65, 90, 15);
		lblCoordX.setHorizontalAlignment(AbstractButton.RIGHT);
		getContentPane().add(lblCoordX);

		textFieldX = new JFormattedTextField(format);
		textFieldX.setBounds(109, 64, 86, 20);
		getContentPane().add(textFieldX);
		textFieldX.setColumns(10);
		textFieldX.addKeyListener(listener);

		textFieldY = new JFormattedTextField(format);
		textFieldY.setBounds(109, 95, 86, 20);
		getContentPane().add(textFieldY);
		textFieldY.setColumns(10);
		textFieldY.addKeyListener(listener);

		JLabel lblCoordY = new JLabel("Coordonate Y");
		lblCoordY.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCoordY.setForeground(Color.WHITE);
		lblCoordY.setBounds(10, 95, 90, 15);
		getContentPane().add(lblCoordY);
		setResizable(false);
		setVisible(true);

	}

	public void validAddingId() {
		if (!textField_1.getText().isEmpty() && !(textFieldX.getText().isEmpty()) && !(textFieldY.getText().isEmpty())) {
			String xString = textFieldX.getText().replaceAll("\\u00A0", "");
			String yString = textFieldY.getText().replaceAll("\\u00A0", "");
			System.out.println(xString + " || " + yString);
			if (xString.length() < 5 && yString.length() < 5) {
				PropertiesAccess.saveTheIdRoominProp(textField_1.getText(), xString, yString);
				dispose();
				System.gc();
				Main.f.isAddRoomOpen = false;
				Main.f.isWindowOpen = false;
			} else
				JOptionPane.showMessageDialog(this, "Coord x and y must be lower in 10000 ", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Your " + Utils.type[Utils.langId][1] + " is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public int[] idInProp() {
		PropertiesAccess.loadAll();
		int s = 1, r = 1, c = 1;
		if (Utils.s != null) s = Utils.s.length;
		if (Utils.r != null) r = Utils.r.length;
		if (Utils.c != null) c = Utils.c.length;
		int a[] = { s, r, c };
		return a;
	}
}
