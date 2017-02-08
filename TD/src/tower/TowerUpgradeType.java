package tower;

public enum TowerUpgradeType {//TODO add more
	MAX_COOLDOWN(false, "Firerate"),//most
	DAMAGE(false, "Damage"),//most
	RANGE(false, "Range"),//most
	HIT_COUNT(false, "Hits"),//most
	DOES_DAMAGE(true, "Does Damage"),//ice
	BULLET_COUNT(false, "Pellet count"),//shotgun, sprayer
	BULLET_SPREAD(false, "BulletSpread");//shotgun

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