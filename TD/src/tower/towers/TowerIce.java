package tower.towers;

import java.util.Arrays;

import enemy.Enemy;
import enemy.EnemyManager;
import main.Main;
import tower.Tower;
import tower.TowerUpgrade;
import tower.TowerUpgradeType;

public class TowerIce extends Tower {
	public TowerIce(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_ICE_SIZE, TOWER_ICE_BASE_RANGE, TOWER_ICE_VALUE);
		availableUpgrades.addAll(Arrays.asList(
			new TowerUpgrade(TowerUpgradeType.MAX_COOLDOWN, 300, -10d, true, false, 5),
			new TowerUpgrade(TowerUpgradeType.HIT_COUNT, 100, -10d, true, false, 5),
			new TowerUpgrade(TowerUpgradeType.RANGE, 50, 1*Main.getScale(), true, false, 2),
			new TowerUpgrade(TowerUpgradeType.DOES_DAMAGE, 30,
					new TowerUpgrade(TowerUpgradeType.DAMAGE, 20, 1, true, false, 3))
		));
		maxCooldown = TOWER_ICE_BASE_COOLDOWN;
		hitCount = TOWER_ICE_BASE_HIT_COUNT;
		iceSpeedModifier = TOWER_ICE_BASE_SLOWDOWN;
		iceSlowDownDuration = TOWER_ICE_BASE_DURATION;
		name = "Ice";
		drawGunAngle = false;
	}

	void slow(Enemy e) {
		e.ice(iceSpeedModifier, (int)iceSlowDownDuration);
	}

	@Override
	public boolean tick() {
		if (placed) {
			if (gunCooldown>0) {
				gunCooldown-=1000/Main.UPS;
			}
			else {
				Enemy e;
				int hits = 0;
				for (int i = 0;i<EnemyManager.enemies.size();i++) {
					e = EnemyManager.enemies.get(i);
					if (!e.isIced()&&!e.isDestroy()&&inRange(e)&&e.canSee(canSeeCamo)&&e.canDamage(canDamageMetal)) {
						gunCooldown = maxCooldown;
						slow(e);
						if (damage>0) {
							e.hit(damage, true);
						}
						hits++;
						if (hits>=hitCount) {
							break;
						}
					}
				}
			}
		}
		else {
			tickFloating();
		}
		return destroy;
	}

	@Override
	protected void upgradeSpecial(TowerUpgrade upgrade) {
		switch (upgrade.getType()) {
		case DOES_DAMAGE:
			damage = 1;
			break;
		default:
			break;
		}
	}
}

