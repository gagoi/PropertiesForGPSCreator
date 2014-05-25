public class PanelGrid {

	static String plan[] = { "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00",
			"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
			"18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30" };
	static String columnName[][] = { { "Hours", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" },
			{ "Heures", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche" } }, groupName[] = { "All", "A", "B", "1", "2", "3" }, weekName[] = { "All", "A", "B", "1",
			"2", "3" };
	private static Item values[][][][] = new Item[48][8][8][8];
	static String valuesString[][] = new String[48][8];
	static int z[][][][] = new int[48][8][8][8];

	// public static String[] subject, room, commentary;

	public static void fillTable() {
		// Create the tab.
		for (int x = 0; x < 48; x++) {
			for (int y = 0; y < 8; y++) {
				for (int a = 0; a < 8; a++) {
					for (int b = 0; b < 8; b++) {
						values[x][y][a][b] = new Item(0, 0, 0);
						z[x][y][a][b] = 0;
					}
				}
			}
		}
		for (int x = 0; x < values.length; x++) {
			valuesString[x][0] = plan[x];
			for (int y = 1; y < columnName[0].length; y++) {
				valuesString[x][y] = toJTable(x, y);
			}
		}
	}

	public static void updateTab(int row, int column) {
		// String textInTab = subject + " - " + room + " - " + commentary +
		// " - " + groupName[groupId] + " - " + weekName[weekId];
		String textInTab = toJTable(row, column);
		Main.f.tab.setValueAt(textInTab, row, column);
	}

	public static int toNumberOfProp(int x, int y) {
		int i = 0;
		for (int a = 0; a < 8; a++) {
			for (int b = 0; b < 8; b++) {
				if (values[x][y][a][b].isItemNotNull()) {
					z[x][y][a][b] = 1;
				}
				i += z[x][y][a][b];
			}
		}
		return i;
	}

	public static String toJTable(int x, int y) {
		int z = toNumberOfProp(x, y);
		String txt[] = {" properties", " propriétés"};
		if (z == 0)
			return "";
		else if(z == 1){
			txt[0] = " property";
			txt[1] = " propriété";
			return (z + txt[Utils.langId]);
		}
		else
			return (z + txt[Utils.langId]);
	}

	public static String toJTextPane(Item[][] item) {
		String str[] = new String[2];
		str[0] = str[1] = "";
		for (int a = 0; a < 4; a++) {
			str[0] = str[0] + "Group : " + a + "                                                                   Group : " + (a + 4) + "\r";
			str[1] = str[1] + "Groupe : " + a + "                                                                       Groupe : " + (a + 4) + "\r";
			for (int b = 0; b < 8; b++) {
				str[0] = str[0] + "-Week : " + b + item[a][b].toStringPane() + "       -Week : " + b + item[a + 4][b].toStringPane() + "\r";
				str[1] = str[1] + "-Semaine : " + b + item[a][b].toStringPane() + "       -Semaine : " + b + item[a + 4][b].toStringPane() + "\r";
				if (a == 4 && b == 8) return str[Utils.langId];
			}
			str[0] = str[0] + " \r";
			str[1] = str[1] + " \r";
		}
		str[0] = str[0] + "View help for more informations about numbers.";
		str[1] = str[1] + "Voir l'aide pour plus d'informations à propos des nombres.";
		return str[Utils.langId];

	}

	public static Item[][] getItemsArrayAt(int x, int y) {
		return values[x][y];
	}

	public static Item getItemOf(int x, int y, int a, int b) {
		return values[x][y][a][b];
	}

	public static void setItemAt(Item item, int x, int y, int a, int b) {
		values[x][y][a][b] = item;
	}

	public static void updateAllTab() {
		for (int x = 0; x < 48; x++) {
			for (int y = 0; y < 8; y++) {
				for (int a = 0; a < 8; a++) {
					for (int b = 0; b < 8; b++) {
						if (getItemOf(x, y, a, b).isItemNotNull()) toJTable(x, y);
					}
				}
			}
		}
	}
}