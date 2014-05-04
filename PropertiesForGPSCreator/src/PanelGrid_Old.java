public class PanelGrid_Old {

	static String plan[] = { "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00",
			"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
			"18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30" };
	static String columnName[][] = { { "Hours", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" },
			{ "Heures", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" } }, groupName[] = { "All", "A", "B", "1", "2", "3" }, weekName[] = { "All", "A", "B", "1",
			"2", "3" };
	public static String[][] subjectPerCell = new String[48][8], roomPerCell = new String[48][8], commentaryPerCell = new String[48][8];
	public static int[][] idSubjectPerCell = new int[48][8], idRoomPerCell = new int[48][8], idCommentaryPerCell = new int[48][8],
	// For the 2 next array, Id : 0 = rien; 1 = A; 2 = B; 3 = 1; 4 = 2; 5 = 3;
			groupIdPerCell = new int[48][8], weekIdPerCell = new int[48][8];
	static String values[][] = new String[48][8];

//	public static String[] subject, room, commentary;

	public static void fillTable() {
		// Create the tab.
		for (int i = 0; i < values.length; i++) {
			values[i][0] = plan[i];
		}
	}

	public static void updateTab(int row, int column, String subject, String room, String commentary, int groupId, int weekId) {
		String textInTab = subject + " - " + room + " - " + commentary + " - " + groupName[groupId] + " - " + weekName[weekId];
		Main.f.tab.setValueAt(textInTab, row, column);
	}
}