public class Item {

	private String subject, room, commentary, week, group;
	private int idS, idR, idC, idW, idG, planCordX, planCordY;

	public Item(int idS, int idR, int idC, int idW, int idG) {
		setIdSubject(idS);
		setIdRoom(idR);
		setIdCommentary(idC);
		setIdWeek(idW);
		setIdGroup(idG);
		setSubject(Utils.s[idS]);
		setRoom(Utils.r[idR]);
		setCommentary(Utils.c[idC]);
	}

	public int getIdSubject() {
		return idS;
	}

	public void setIdSubject(int idS) {
		this.idS = idS;
	}

	public int getIdRoom() {
		return idR;
	}

	public void setIdRoom(int idR) {
		this.idR = idR;
	}

	public int getIdCommentary() {
		return idC;
	}

	public void setIdCommentary(int idC) {
		this.idC = idC;
	}

	public int getIdWeek() {
		return idW;
	}

	public void setIdWeek(int idW) {
		this.idW = idW;
	}

	public int getIdGroup() {
		return idG;
	}

	public void setIdGroup(int idG) {
		this.idG = idG;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	// ------------------------------------------\\
	public String toString() {
		if (subject == null && room == null && commentary == null)
			return "";
		else
			return ("S:" + subject + " | R:" + room + " | C:" + commentary + " | W:  " + week + " | G:" + group);
	}

	public String getSubjectOf(String prop){
		return Utils.s[getIdSubjectOf(prop)];
	}
	public int getIdSubjectOf(String prop){
		return Integer.parseInt(prop.substring(0,2));
	}
	
	public String getRoomOf(String prop){
		return Utils.r[getIdRoomOf(prop)];
	}
	public int getIdRoomOf(String prop){
		return Integer.parseInt(prop.substring(3,6));
	}
	
	public String getCommentaryOf(String prop){
		return Utils.c[getIdCommentaryOf(prop)];
	}
	public int getIdCommentaryOf(String prop){
		return Integer.parseInt(prop.substring(7,9));
	}

	public int getPlanCordX() {
		return planCordX;
	}

	public void setPlanCordX(int planCordX) {
		this.planCordX = planCordX;
	}

	public int getPlanCordY() {
		return planCordY;
	}

	public void setPlanCordY(int planCordY) {
		this.planCordY = planCordY;
	}
}
