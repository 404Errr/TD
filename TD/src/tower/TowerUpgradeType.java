package tower;

public enum TowerUpgradeType {//TODO add more
	MAX_COOLDOWN(false, "Firerate"),//most
	DAMAGE(false, "Damage"),//most
	RANGE(false, "Range"),//most
	CAN_SEE_CAMO(true, "Can See Camo"),//some
	HIT_COUNT(false, "Hits"),//some
	DOES_DAMAGE(true, "Does Damage"),//some
	BULLET_COUNT(false, "Pellet Count"),//shotgun, sprayer
	BULLET_SPREAD(false, "Bullet Spread");//shotgun

	private boolean special;
	private String displayText;

	TowerUpgradeType(boolean special, String displayText) {
		this.special = special;
		this.displayText = displayText;
	}

	String getDisplayText() {
		return displayText;
	}

	boolean isSpecial() {
		return special;
	}
}