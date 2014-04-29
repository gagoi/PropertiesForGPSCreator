import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesAccess {
	// These values are using to load the properties.
	// Change this String to change the properties folder.
	static String path = ("C:/Users/" + System.getProperty("user.name") + "/Desktop/HyperGPSDeLaMortXDPtdrLol/");
	static File plan = new File(path, "plan.properties"), id = new File(path, "id.properties");
	static Properties planProp = new Properties(), idProp = new Properties();
	// These arrays contain the temporary values of properties names.
	static String idArray[];
	static String planArray[];
	// Theses arrays contain the id (using index) and the value per type
	// (subject, room, commentary). id 0 = null.
	static String[] idSubject, idRoom, idCommentary;
	static int[] nbOfIdUse = { 1, 1, 1 };

	@SuppressWarnings({ "rawtypes", "unchecked" })
	static Object[] stringPropertyName(Properties prop) {
		// Put all properties name in enumeration e.
		Enumeration e = prop.propertyNames();
		// Create a hashmap to put in the value of e, one by one.
		HashMap hm = new HashMap();
		// Use int a to set the index of each value in the hashmap.
		int a = 0;
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			hm.put(a, key);
			a++;
		}
		// Use a collection with all hashmap values. And transform it to an
		// array of object. Finally return the array with name of properties.
		Collection c = hm.values();
		return c.toArray();
	}

	static void loadIdAndplan() {
		// Try to load the properties files.
		try {
			System.out.println("\rLoad :");
			// Load the file and setup the properties object (idProp and
			// planProp).
			idProp.load(new FileInputStream(id));
			planProp.load(new FileInputStream(plan));
			// Create the arrays to prevent NPE.
			idArray = new String[stringPropertyName(idProp).length];
			planArray = new String[stringPropertyName(planProp).length];
			// Add the values in the arrays using stringPropertyNames()
			// function.
			idArray = (String[]) stringPropertyName(idProp);
			planArray = (String[]) stringPropertyName(planProp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void transformTempIdArrayToFinalIDArray() {
		// These int are using to got the number of values with each
		// parameters (example : a = number of subject in properties file)
		int a = 1, b = 1, c = 1;

		// If file isn't empty we can get all in it.
		if (idArray != null) {

			// Create three temporary arrays
			String[] subjectTemp = new String[stringPropertyName(idProp).length + 1];
			String[] roomTemp = new String[stringPropertyName(idProp).length + 1];
			String[] commentaryTemp = new String[stringPropertyName(idProp).length + 1];

			// This loop is for get and tidy up the properties in
			// idArray[] in
			// temporary arrays with length = file.size().
			for (int i = idArray.length - 1; i >= 0; i--) {
				if (idArray[i].toString().contains("subject")) {
					subjectTemp[a] = idProp.getProperty((String) idArray[i]);
					a++;
				} else if (idArray[i].toString().contains("room")) {
					roomTemp[b] = idProp.getProperty((String) idArray[i]);
					b++;
				} else if (idArray[i].toString().contains("commentary")) {
					commentaryTemp[c] = idProp.getProperty((String) idArray[i]);
					c++;
				} else
					System.out.println("ID not valid");
			}

			// This three loops convert the temporary arrays in final arrays
			// with the good length.
			if (a != 1) {
				PanelGrid.subject = new String[a];
				for (int y = 0; y < a; y++) {
					PanelGrid.subject[y] = subjectTemp[y];
				}
			}
			if (b != 1) {
				PanelGrid.room = new String[b];
				for (int y = 0; y < b; y++) {
					PanelGrid.room[y] = roomTemp[y];
				}
			}
			if (c != 1) {
				PanelGrid.commentary = new String[c];
				for (int y = 0; y < c; y++) {
					PanelGrid.commentary[y] = commentaryTemp[y];
				}
			}
		}
	}

	void getPlan() {
		int dayIndex, hour, weekIndex, subjectId, roomId, commentaryId, groupIndex;
		String hourComplet;
//		Object propArrayRow = stringPropertyName(planProp);
		// For each values in properties planProp (File : plan.properties) :
		for (int i = 0; i < stringPropertyName(planProp).length; i++) {
				weekIndex = Integer.parseInt(stringPropertyName(planProp)[i].toString().substring(4, 5));
				groupIndex = Integer.parseInt(stringPropertyName(planProp)[i].toString().substring(1, 2));
				dayIndex = Integer.parseInt(stringPropertyName(planProp)[i].toString().substring(7, 8));
				hour = Integer.parseInt(stringPropertyName(planProp)[i].toString().substring(9, 13));
				hourComplet = stringPropertyName(planProp)[i].toString().substring(9, 11) + ":" + stringPropertyName(planProp)[i].toString().substring(11, 13);

				subjectId = Integer.parseInt(planProp.getProperty(stringPropertyName(planProp)[i].toString()).substring(0, 2));
				roomId = Integer.parseInt(planProp.getProperty(stringPropertyName(planProp)[i].toString()).substring(3, 6));
				commentaryId = Integer.parseInt(planProp.getProperty(stringPropertyName(planProp)[i].toString()).substring(7, 9));

				System.out.println("Week : " + weekIndex + " | Day : " + dayIndex + " | Hour : " + hour + " || Subject ID : " + subjectId + " | Room ID : " + roomId + " | Commentary Id : "
						+ commentaryId);
				addToJTable(dayIndex, hourComplet, weekIndex, subjectId, roomId, commentaryId, groupIndex);
			}
	}

	static void addToJTable(int dayIndex, String hourComplet, int weekIndex, int subjectId, int roomId, int commentaryId, int groupId) {
		System.out.println(PanelGrid.subject[subjectId] + " - " + PanelGrid.room[roomId] + " - " + PanelGrid.commentary[commentaryId]);
		String value = PanelGrid.subject[subjectId] + " - " + PanelGrid.room[roomId] + " - " + PanelGrid.commentary[commentaryId];
		String hourModified = hourComplet.substring(0, 2) + ":" + hourComplet.substring(2, 4);
		System.out.println("-------------------" + hourComplet + " | " + hourModified + "-------------------");
		int row = Arrays.asList(PanelGrid.plan).indexOf(hourModified);
		System.out.println("Row = " + row + " | " + hourModified);
		if (row != -1) {
			Main.f.tab.setValueAt(value, row, dayIndex);
			addIndexToArray(row, dayIndex, subjectId, roomId, commentaryId, weekIndex, groupId);
		}
	}

	static void addIndexToArray(int row, int column, int idSubject, int idRoom, int idCommentary, int weekIndex, int groupId) {
		PanelGrid.idSubjectPerCell[row][column] = idSubject;
		PanelGrid.idRoomPerCell[row][column] = idRoom;
		PanelGrid.idCommentaryPerCell[row][column] = idCommentary;
		PanelGrid.weekIdPerCell[row][column] = weekIndex;
		PanelGrid.weekIdPerCell[row][column] = groupId;
	}
	static void verifyFolderAndFile() {
		try {
			if (!new File(path).exists()) new File(path).mkdirs();
			if (!plan.exists()) plan.createNewFile();
			if (!id.exists()) id.createNewFile();
		} catch (IOException e) {
			System.err.println("Error when create one of the files/folders... Retry to launch the program.");
			e.printStackTrace();
		}
	}

	static void saveThePlanInProp(int row, int column) {
		String name, value;
		String s, r, c;
		name = value = s = r = c = null;
		// Verify the prop. If all is empty, don't save.
		if (!(PanelGrid.idSubjectPerCell[row][column] == 0) && !(PanelGrid.idRoomPerCell[row][column] == 0) && !(PanelGrid.idCommentaryPerCell[row][column] == 0)) {
			s = "" + PanelGrid.idSubjectPerCell[row][column];
			r = "" + PanelGrid.idRoomPerCell[row][column];
			c = "" + PanelGrid.idCommentaryPerCell[row][column];

			if (PanelGrid.idSubjectPerCell[row][column] == 0) s = "00";
			if (PanelGrid.idRoomPerCell[row][column] == 0) r = "000";
			if (PanelGrid.idCommentaryPerCell[row][column] == 0) c = "00";

			if (PanelGrid.idSubjectPerCell[row][column] < 10) s = "0" + PanelGrid.idSubjectPerCell[row][column];
			if (PanelGrid.idRoomPerCell[row][column] < 10) r = "00" + PanelGrid.idRoomPerCell[row][column];
			if (PanelGrid.idRoomPerCell[row][column] < 100 && PanelGrid.idRoomPerCell[row][column] >= 10) r = "0" + PanelGrid.idRoomPerCell[row][column];
			if (PanelGrid.idCommentaryPerCell[row][column] < 10) c = "0" + PanelGrid.idCommentaryPerCell[row][column];

			// Create the property name. Example : G0.W1.D6.1230
			name = "G" + PanelGrid.groupIdPerCell[row][column] + ".W" + PanelGrid.weekIdPerCell[row][column] + ".D" + column + "." + PanelGrid.plan[row].replace(":", "").toString();
			// Create the property value. Example : 12 642 04
			value = s + "." + r + "." + c;
			// Set property.
			planProp.setProperty(name, value);

			// Save the prop just set before.
			try {
				planProp.store(new FileOutputStream(plan), null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	static void saveTheIdinProp(int typeIndex) {
		String[] type = { "Subject", "Room", "Commentary" };
		idProp.setProperty(type[typeIndex].toLowerCase() + "." + nbOfIdUse[typeIndex], FramePopup.textField_1.getText());
		System.out.println(type[typeIndex].toLowerCase() + "." + nbOfIdUse[typeIndex] + "=" + FramePopup.textField_1.getText());
		nbOfIdUse[typeIndex]++;
		try {
			idProp.store(new FileOutputStream(id), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void loadAll() {
		verifyFolderAndFile();
		loadIdAndplan();
		transformTempIdArrayToFinalIDArray();
	}
}
