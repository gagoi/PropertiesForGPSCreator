import javax.swing.SwingUtilities;

public class Main {

	static MyFrame f;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("I love Anna");
				WriteProperties.createFiles();
				// String fonts[] =
				// GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
				// for (int i = 0; i < fonts.length; i++) {
				// System.out.println(fonts[i]);
				// }
				PanelGrid.fillTable();

				f = new MyFrame();
				f.tab.setComponentPopupMenu(f.pm);

			}
		});
	}
}
