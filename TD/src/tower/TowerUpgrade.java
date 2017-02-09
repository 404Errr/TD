package tower;

public class TowerUpgrade {
	private TowerUpgradeType type;
	private double value, price;
	private boolean percentage, increment, hasChild;
	private TowerUpgrade child;
	private int repeat;

	public TowerUpgrade(TowerUpgradeType type, double price) {
		this.type = type;
		this.price = price;
	}

	public TowerUpgrade(TowerUpgradeType type, double price, double value, boolean increment, boolean percentage) {
		this.type = type;
		this.price = price;
		this.value = value;
		this.increment = increment;
		this.percentage = percentage;
	}

	public TowerUpgrade(TowerUpgradeType type, double price, double value, boolean increment, boolean percentage, int repeat) {
		this.type = type;
		this.price = price;
		this.value = value;
		this.increment = increment;
		this.percentage = percentage;
		this.repeat = repeat;
		hasChild = true;
	}

	public TowerUpgrade(TowerUpgradeType type, double price, double value, boolean increment, boolean percentage, TowerUpgrade child) {
		this.type = type;
		this.price = price;
		this.value = value;
		this.increment = increment;
		this.percentage = percentage;
		this.child = child;
		hasChild = true;
	}

	public TowerUpgrade(TowerUpgradeType type, double price, TowerUpgrade child) {
		this.type = type;
		this.price = price;
		this.child = child;
		hasChild = true;
	}

	public void decreaseRepeat() {
		repeat--;
	}

	public TowerUpgrade getChild() {
		return child;
	}

	double getNewValue(double oldValue) {
		if (increment) {
			if (percentage)
				return oldValue+(oldValue*(value/100));
			else
				return oldValue+value;
		}
		else {
			if (percentage)
				return oldValue*(value/100);
			else
				return value;
		}
	}

	int getNewValue(int oldValue) {
		return (int)getNewValue((double)oldValue);
	}

	public double getPrice() {
		return price;
	}

	public int getRepeat() {
		return repeat;
	}

	public TowerUpgradeType getType() {
		return type;
	}

	public double getValue() {
		return value;
	}

	public boolean hasChild() {
		return hasChild;
	}

	public boolean isIncrement() {
		return increment;
	}

	public boolean isPercentage() {
		return percentage;
	}

	@Override
	public String toString() {
		if (type.isSpecial())
			return type+"";
		else return type+" "+value;
	}
}
