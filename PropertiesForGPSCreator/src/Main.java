import javax.swing.SwingUtilities;

public class Main {

	static MyFrame f;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("I love Anna");

				PanelGrid.fillTable();
				PropertiesAccess.loadAll();

				f = new MyFrame();
				PropertiesAccess.getPlan();

			}
		});
	}
}