package enemy;

public enum EnemyType {
	NORMAL(false, false, false),
	CAMO(true, false, false),
	METAL(false, true, false),
	REGEN(false, false, true),
	CAMOMETAL(true, true, false),
	CAMOREGEN(true, false, true),
	METALREGEN(false, true, true),
	CAMOMETALREGEN(true, true, true);

	boolean camo, metal, regen;

	EnemyType(boolean camo, boolean metal, boolean regen) {
		this.camo = camo;
		this.metal = metal;
		this.regen = regen;
	}
}