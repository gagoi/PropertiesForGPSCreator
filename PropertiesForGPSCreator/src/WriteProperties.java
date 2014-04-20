import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WriteProperties {
	static String test;
	static File file = new File("C:/Users/" + System.getProperty("user.name") + "/Desktop/HyperGPSDeLaMortXDPtdrLol/", "plan.properties");
	static File path = new File("C:/Users/" + System.getProperty("user.name") + "/Desktop/HyperGPSDeLaMortXDPtdrLol/");
	static File fileId = new File("C:/Users/" + System.getProperty("user.name") + "/Desktop/HyperGPSDeLaMortXDPtdrLol/", "id.properties");
	static Properties prop = new Properties();
	static Properties propId = new Properties();
	static int propFromTab[][] = new int[PanelGrid.values.length][PanelGrid.columnName.length];
	static String[] dayList = { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
	static int nbOfIDUse[] = {1, 1, 1}, next[] = new int[3];

	public static void saveProperties() {
		try {
			if (!path.exists()) path.mkdirs();
			if (!file.exists()) file.createNewFile();
			System.out.println("Save-Prop");
			prop.store(new FileOutputStream(file), null);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void modTabToProperties() {
		String proptxt = "propTxt";
		String propName = "propName";
		String s = null, r = null, c = null;
		// First loop : line.
		for (int x = 0; x < Main.f.tab.getRowCount(); x++) {
			// Second loop : column - the first column with hours.
			for (int y = 1; y < Main.f.tab.getColumnCount(); y++) {
				if (!(PanelGrid.idSubjectPerCell[x][y] == 0) || !(PanelGrid.idRoomPerCell[x][y] == 0) || !(PanelGrid.idCommentaryPerCell[x][y] == 0)) {
					s = "" + PanelGrid.idSubjectPerCell[x][y];
					r = "" + PanelGrid.idRoomPerCell[x][y];
					c = "" + PanelGrid.idCommentaryPerCell[x][y];

					if (PanelGrid.idSubjectPerCell[x][y] == 0) s = "00";
					if (PanelGrid.idRoomPerCell[x][y] == 0) r = "000";
					if (PanelGrid.idCommentaryPerCell[x][y] == 0) c = "00";

					if (PanelGrid.idSubjectPerCell[x][y] < 10) s = "0" + PanelGrid.idSubjectPerCell[x][y];
					if (PanelGrid.idRoomPerCell[x][y] < 10) r = "00" + PanelGrid.idRoomPerCell[x][y];
					if (PanelGrid.idRoomPerCell[x][y] < 100 && PanelGrid.idRoomPerCell[x][y] >= 10) r = "0" + PanelGrid.idRoomPerCell[x][y];
					if (PanelGrid.idCommentaryPerCell[x][y] < 10) c = "0" + PanelGrid.idCommentaryPerCell[x][y];

					proptxt = s + " " + r + " " + c;
					propName = "G" + PanelGrid.groupIdPerCell[x][y] + ".W" + PanelGrid.weekIdPerCell[x][y] + ".D" + y + "." + PanelGrid.plan[x].replace(":", "").toString();
					prop.setProperty(propName, proptxt);
					saveProperties();
					System.out.println("Save of " + propName + " with parameter : " + proptxt);
				}
			}
		}
		System.out.println("Save end");
	}

	public static void saveId(int typeIndex) {
		String[] type = {"Subject", "Room", "Commentary"};
		try {
			if (!path.exists()) path.mkdirs();
			if (!fileId.exists()) fileId.createNewFile();
			propId.setProperty(type[typeIndex].toLowerCase() + "." + nbOfIDUse[typeIndex], FramePopup.textField_1.getText());
			System.out.println(type[typeIndex].toLowerCase() + "." + nbOfIDUse[typeIndex] + "=" + FramePopup.textField_1.getText());
			nbOfIDUse[typeIndex]++;
			propId.store(new FileOutputStream(fileId), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createFiles() {
		try {
			if (!path.exists()) path.mkdirs();
			if (!file.exists()) file.createNewFile();
			if (!fileId.exists()) fileId.createNewFile();
		} catch (IOException e) {
			System.err.println("createFiles()");
			e.printStackTrace();
		}
	}
}
