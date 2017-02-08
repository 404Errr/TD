package tower;

public enum TowerUpgradeType {//TODO add more
	MAX_COOLDOWN(false, "Firerate"), DAMAGE(false, "Damage"), RANGE(false, "Range"), HIT_COUNT(false, "Hits"),//most
	DOES_DAMAGE(true, "Does Damage"),//ice
	BULLET_COUNT(false, "Pellet count"),//shotgun, sprayer
	GUN_SPREAD(false, "BulletSpread");//shotgun

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