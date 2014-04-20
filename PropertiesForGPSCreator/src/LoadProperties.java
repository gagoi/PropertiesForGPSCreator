import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class LoadProperties extends WriteProperties {
	static Object[] propArray;
	public static String[] subject, room, commentary;

	public static void loadProperties() {
		try {
			System.out.println("\rLoad prop :");
			prop.load(new FileInputStream(file));
			propArray = prop.stringPropertyNames().toArray();
			for (int i = 0; i < propArray.length; i++) {
				System.out.print(propArray[i] + "=" + prop.getProperty((String) propArray[i]));
				System.out.print(" | ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getProp() {
		loadProperties();
		int dayIndex, hour, weekIndex, subjectId, roomId, commentaryId, groupIndex = 0;
		String hourComplet;
		System.out.println("\r");
		for (int i = 0; i < prop.stringPropertyNames().toArray().length; i++) {
			weekIndex = Integer.parseInt(propArray[i].toString().substring(4, 5));
			groupIndex = Integer.parseInt(propArray[i].toString().substring(1, 2));  
			dayIndex = Integer.parseInt(propArray[i].toString().substring(7, 8));
			hour = Integer.parseInt(propArray[i].toString().substring(9, 13));
			hourComplet = propArray[i].toString().substring(9, 11) + ":" + propArray[i].toString().substring(11, 13);
			
			subjectId = Integer.parseInt(prop.getProperty(propArray[i].toString()).substring(0, 2));
			roomId = Integer.parseInt(prop.getProperty(propArray[i].toString()).substring(3, 6));
			commentaryId = Integer.parseInt(prop.getProperty(propArray[i].toString()).substring(7, 9));

			System.out.println("Week : " + weekIndex + " | Day : " + dayIndex + " | Hour : " + hour + " || Subject ID : " + subjectId + " | Room ID : " + roomId + " | Commentary Id : "
					+ commentaryId);
			addToJTable(dayIndex, hourComplet, weekIndex, subjectId, roomId, commentaryId, groupIndex);
		}
	}

	public static void addToJTable(int dayIndex, String hourComplet, int weekIndex, int subjectId, int roomId, int commentaryId, int groupId) {
		System.out.println(subject[subjectId] + " - " + room[roomId] + " - " + commentary[commentaryId]);
		String value = subject[subjectId] + " - " + room[roomId] + " - " + commentary[commentaryId];
		String hourModified = hourComplet.substring(0, 2) + ":" + hourComplet.substring(2, 4);
		System.out.println("-------------------" + hourComplet + " | " + hourModified + "-------------------");
		int row = Arrays.asList(PanelGrid.plan).indexOf(hourModified);
		System.out.println("Row = " + row + " | " + hourModified);
		if (row != -1) {
			Main.f.tab.setValueAt(value, row, dayIndex);
			addIndexToArray(row, dayIndex, subjectId, roomId, commentaryId, weekIndex, groupId);
		}
//		Frame_Old.tab.repaint();
	}

	public static void addIndexToArray(int row, int column, int idSubject, int idRoom, int idCommentary, int weekIndex, int groupId) {
		PanelGrid.idSubjectPerCell[row][column] = idSubject;
		PanelGrid.idRoomPerCell[row][column] = idRoom;
		PanelGrid.idCommentaryPerCell[row][column] = idCommentary;
		PanelGrid.weekIdPerCell[row][column] = weekIndex;
		PanelGrid.weekIdPerCell[row][column] = groupId;
	}

	public static void loadPropertiesId() {
		System.out.println("\rLoad Id");
		try {
			// These int are using to got the number of values with each
			// parameters (ex : a = number of subject in properties file)
			int a = 1, b = 1, c = 1;

			// Load the properties file and get an array of the parameters in
			// it.
			propId.load(new FileInputStream(fileId));
			propArray = propId.stringPropertyNames().toArray();

			// If file isn't empty we can get all in it.
			if (propArray != null) {

				// Create three temporary arrays
				String[] subjectTemp = new String[propId.stringPropertyNames().toArray().length + 1];
				String[] roomTemp = new String[propId.stringPropertyNames().toArray().length + 1];
				String[] commentaryTemp = new String[propId.stringPropertyNames().toArray().length + 1];

				// This loop is for get and tidy up the properties in
				// propArray[] in
				// temporary arrays with length = file.size().
				for (int i = propArray.length - 1; i >= 0; i--) {
					if (propArray[i].toString().contains("subject")) {
						subjectTemp[a] = propId.getProperty((String) propArray[i]);
						a++;
					} else if (propArray[i].toString().contains("room")) {
						roomTemp[b] = propId.getProperty((String) propArray[i]);
						b++;
					} else if (propArray[i].toString().contains("commentary")) {
						commentaryTemp[c] = propId.getProperty((String) propArray[i]);
						c++;
					} else
						System.out.println("ID not valid");
				}

				// This three loops convert the temporary arrays in final arrays
				// with the good lenght.
				if (a != 1) {
					System.out.println(a);
					subject = new String[a];
					for (int y = 0; y < a; y++) {
						subject[y] = subjectTemp[y];
					}
				}
				if (b != 1) {
					room = new String[b];
					for (int y = 0; y < b; y++) {
						room[y] = roomTemp[y];
					}
				}
				if (c != 1) {
					commentary = new String[c];
					for (int y = 0; y < c; y++) {
						commentary[y] = commentaryTemp[y];
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}