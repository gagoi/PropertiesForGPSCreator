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
			// Load the file and setup the properties object (idProp and
			// planProp).
			idProp.load(new FileInputStream(id));
			planProp.load(new FileInputStream(plan));
			// Create the arrays to prevent NPE.
			idArray = new String[stringPropertyName(idProp).length];
			planArray = new String[stringPropertyName(planProp).length];
			// Add the values in the arrays using stringPropertyNames()
			// function.
			for (int i = 0; i < stringPropertyName(idProp).length; i++) {
				idArray[i] = (String) stringPropertyName(idProp)[i];
			}
			for (int i = 0; i < stringPropertyName(planProp).length; i++) {
				planArray[i] = (String) stringPropertyName(planProp)[i];
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void transformTempIdArrayToFinalIDArray() {
		// These int are using to got the number of values with each
		// parameters (example : a = number of subject in properties file)
		int a = 1, b = 1, c = 1, d = 1;

		// If file isn't empty we can get all in it.
		if (idArray != null) {

			// Create three temporary arrays
			String[] subjectTemp = new String[stringPropertyName(idProp).length + 1];
			String[] roomTemp = new String[stringPropertyName(idProp).length + 1];
			String[] commentaryTemp = new String[stringPropertyName(idProp).length + 1];
			String[] networkTemp = new String[stringPropertyName(idProp).length + 1];

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
				} else if (idArray[i].toString().contains("network")) {
					networkTemp[d] = idProp.getProperty((String) idArray[i]);
				} else
					System.out.println("ID not valid");
			}
			// This three loops convert the temporary arrays in final arrays
			// with the good length.
			if (d != 1) {
				Utils.existNetwork = true;
				Utils.nList = new Network[d];
				for (int y = 0; y < d; y++) {
					Utils.nList[y] = new Network(networkTemp[y].substring(2), Integer.parseInt(networkTemp[y].substring(0, 1)) == 0 ? false : true);
				}
			}
			if (a != 1) {
				Utils.existSubject = true;
				Utils.sList = new IdSubject[a];
				for (int y = 1; y < a; y++) {
					Utils.sList[y] = new IdSubject(subjectTemp[y].substring(3), Utils.nList[Integer.parseInt(subjectTemp[y].substring(0, 2))]);
				}
			}
			if (b != 1) {
				Utils.existRoom = true;
				Utils.rList = new IdRoom[b];
				for (int y = 1; y < b; y++) {
					Utils.rList[y] = new IdRoom(roomTemp[y].substring(11), Integer.parseInt(roomTemp[y].substring(0, 4)), Integer.parseInt(roomTemp[y].substring(5, 9)));
					System.out.println(y + "=" + Utils.rList[y]);
				}
			}
			if (c != 1) {
				Utils.existCommentary = true;
				Utils.cList = new String[c];
				for (int y = 1; y < c; y++) {
					Utils.cList[y] = commentaryTemp[y];
				}
			}
		}
	}

	static void getPlan() {
		int dayIndex, hour, weekIndex, subjectId, roomId, commentaryId, groupIndex, hourIdInPlan;
		String hourComplet;
		// Object propArrayRow = stringPropertyName(planProp);
		// For each values in properties planProp (File : plan.properties) :
		for (int i = 0; i < stringPropertyName(planProp).length; i++) {
			weekIndex = Integer.parseInt(stringPropertyName(planProp)[i].toString().substring(4, 5));
			groupIndex = Integer.parseInt(stringPropertyName(planProp)[i].toString().substring(1, 2));
			dayIndex = Integer.parseInt(stringPropertyName(planProp)[i].toString().substring(7, 8));
			hour = Integer.parseInt(stringPropertyName(planProp)[i].toString().substring(9, 13));
			hourComplet = stringPropertyName(planProp)[i].toString().substring(9, 11) + ":" + stringPropertyName(planProp)[i].toString().substring(11, 13); // Erreur

			subjectId = Integer.parseInt(planProp.getProperty(stringPropertyName(planProp)[i].toString()).substring(0, 2));
			roomId = Integer.parseInt(planProp.getProperty(stringPropertyName(planProp)[i].toString()).substring(3, 6));
			commentaryId = Integer.parseInt(planProp.getProperty(stringPropertyName(planProp)[i].toString()).substring(7, 9));

			hourIdInPlan = Arrays.asList(PanelGrid.plan).indexOf(hourComplet);

			System.out.println(stringPropertyName(planProp)[i]);
			System.out.println("Group : " + groupIndex + " | Week : " + weekIndex + " | Day : " + dayIndex + " | Hour : " + hour + " - " + hourComplet + "(" + hourIdInPlan
					+ ") || Subject ID : " + subjectId + " | Room ID : " + roomId + " | Commentary Id : " + commentaryId);
			PanelGrid.setItemAt(new Item(subjectId, roomId, commentaryId), hourIdInPlan, dayIndex, groupIndex, weekIndex);
			Main.f.tp.setText("Properties here : " + PanelGrid.toNumberOfProp(hourIdInPlan, dayIndex) + "\r\r");
			Main.f.tp.setText(Main.f.tp.getText() + PanelGrid.toJTextPane(PanelGrid.getItemsArrayAt(hourIdInPlan, dayIndex)));
			PanelGrid.updateTab(hourIdInPlan, dayIndex);
		}
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

	static void saveThePlanInProp(int x, int y, int a, int b) {
		String name, value;
		String s, r, c;
		name = value = s = r = c = null;
		Item item = PanelGrid.getItemOf(x, y, a, b);
		// Verify the prop. If all is empty, don't save.
		if (item.isItemNotNull()) {
			s = "" + item.getSubjectId();
			r = "" + item.getRoomId();
			c = "" + item.getCommentaryId();

			if (item.getSubjectId() == 0) s = "00";
			if (item.getRoomId() == 0) r = "000";
			if (item.getCommentaryId() == 0) c = "00";

			if (item.getSubjectId() < 10) s = "0" + item.getSubjectId();
			if (item.getRoomId() < 10) r = "00" + item.getRoomId();
			if (item.getRoomId() < 100 && item.getRoomId() >= 10) r = "0" + item.getRoomId();
			if (item.getCommentaryId() < 10) c = "0" + item.getCommentaryId();

			// Create the property name. Example : G0.W1.D6.1230
			name = "G" + a + ".W" + b + ".D" + y + "." + PanelGrid.plan[x].replace(":", "").toString();
			// Create the property value. Example : 12 642 04
			value = s + "." + r + "." + c;
			// Set property.
			planProp.setProperty(name, value);

			// Save the prop just set before.
			try {
				System.err.println("STORE");
				planProp.store(new FileOutputStream(plan), null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	static void saveTheIdRoominProp(String room, String coordX, String coordY) {
		if (coordX.length() == 1)
			coordX = "000" + coordX;
		else if (coordX.length() == 2)
			coordX = "00" + coordX;
		else if (coordX.length() == 3) coordX = "0" + coordX;
		if (coordY.length() == 1)
			coordY = "000" + coordY;
		else if (coordY.length() == 2)
			coordY = "00" + coordY;
		else if (coordY.length() == 3) coordY = "0" + coordY;
		idProp.setProperty("room" + "." + nbOfIdUse[1], (coordX + "." + coordY + "." + room));
		System.out.println("room" + "." + nbOfIdUse[1] + "=" + coordX + "." + coordY + "." + room);
		nbOfIdUse[1]++;
		try {
			idProp.store(new FileOutputStream(id), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void saveTheIdCommentaryinProp(String commentary) {
		idProp.setProperty("commentary" + "." + nbOfIdUse[2], commentary);
		System.out.println("commentary" + "." + nbOfIdUse[2] + "=" + commentary);
		nbOfIdUse[2]++;
		try {
			idProp.store(new FileOutputStream(id), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void saveTheIdSubjectinProp(String subject, int networkId) {
		String network = "" + networkId;
		if (networkId < 10) network = "0" + networkId;
		idProp.setProperty("subject" + "." + nbOfIdUse[0], (network + "." + subject));
		System.out.println("subject" + "." + nbOfIdUse[0] + "=" + network + "." + subject);
		nbOfIdUse[0]++;
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
