public class Item {

	private int subject, room, commentary;

	public Item(int s, int r, int c) {
		this.subject = s;
		this.room = r;
		this.commentary = c;
	}

	public void setAllByString(String prop) {
		this.subject = Integer.parseInt(prop.substring(0, 2));
		this.room = Integer.parseInt(prop.substring(3, 6));
		this.commentary = Integer.parseInt(prop.substring(7, 9));
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public void setCommentary(int commentary) {
		this.commentary = commentary;
	}

	public void setAll(int s, int r, int c) {
		this.subject = s;
		this.room = r;
		this.commentary = c;
	}

	public int getSubjectId() {
		return this.subject;
	}

	public int getRoomId() {
		return this.room;
	}

	public int getCommentaryId() {
		return this.commentary;
	}

	public IdSubject getSubject() {
		return Utils.sList[this.subject];
	}

	public IdRoom getRoom() {
		return Utils.rList[this.room];
	}

	public String getCommentary() {
		return Utils.cList[this.commentary];
	}

	public boolean hasSubject() {
		return this.subject != 0;
	}

	public boolean hasRoom() {
		return this.room != 0;
	}

	public boolean hasCommentary() {
		return this.commentary != 0;
	}

	public boolean isItemNotNull() {
		if (hasSubject() || hasRoom() || hasCommentary())
			return true;
		else
			return false;
	}

	public String toStringProp() {
		String s = "00", r = "000", c = "00";
		// Properties policy.
		// Subject
		if (this.subject < 10)
			s = "0" + this.subject;
		else
			s = "" + this.subject;
		// Room
		if (this.room < 10) r = "00" + this.room;
		if (this.room >= 10 && this.room < 100)
			r = "0" + this.room;
		else
			r = "" + this.room;
		// Commentary
		if (this.commentary < 10)
			c = "0" + this.commentary;
		else
			c = "" + this.commentary;
		String str = s + "." + r + "." + c;
		return str;
	}

	public String toStringPane() {
		String str[] = { (" | Subject : " + getSubjectId() + " | Room :" + getRoomId() + " | Commentary : " + getCommentaryId()),
				(" | Matière : " + getSubjectId() + " | Salle : " + getRoomId() + " | Commentaire : " + getCommentaryId()) };

		return str[Utils.langId];
	}

	@Override
	public String toString() {
		return "Rien";
	}
}