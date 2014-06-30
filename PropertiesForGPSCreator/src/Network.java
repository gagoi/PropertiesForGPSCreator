
public class Network {
	private String name;
	private boolean isOnlyOnePossible;
	public Network(String name, boolean isOnlyOnePossible) {
		this.setName(name);
		this.setOnlyOnePossible(isOnlyOnePossible);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOnlyOnePossible() {
		return isOnlyOnePossible;
	}
	public void setOnlyOnePossible(boolean isOnlyOnePossible) {
		this.isOnlyOnePossible = isOnlyOnePossible;
	}

	
}
