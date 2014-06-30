public class IdSubject {
	private String value;
	private Network network;

	public IdSubject(String value, Network network) {
		this.value = value;
		this.network = network;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public Network getNetwork() {
		return this.network;
	}

	@Override
	public String toString() {
		return (getValue() + "|" + getNetwork().getName());
	}
}
