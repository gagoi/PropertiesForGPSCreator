public class IdRoom {
	private String value;
	private int x, y;

	public IdRoom(String value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y = y;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return (getValue() + "|" + getX() + "|" + getY());
	}
}
