public class IdSubject {
	private String value;
	private int network;

	public IdSubject(String value, int network) {
		this.value = value;
		this.network = network;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setNetwork(int network) {
		this.network = network;
	}

	public int getNetwork() {
		return this.network;
	}

	@Override
	public String toString() {
		return (getValue() + "|" + getNetwork());
	}
}
