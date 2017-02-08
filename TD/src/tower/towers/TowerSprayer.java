package tower.towers;

import java.util.Arrays;

import main.Main;
import projectile.ProjectileManager;
import projectile.ProjectilePellet;
import tower.Tower;
import tower.TowerUpgrade;
import tower.TowerUpgradeType;

public class TowerSprayer extends Tower {
	public TowerSprayer(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_SPRAYER_SIZE, TOWER_SPRAYER_BASE_RANGE, TOWER_SPRAYER_VALUE);
		availableUpgrades.addAll(Arrays.asList(
			new TowerUpgrade(TowerUpgradeType.MAX_COOLDOWN, 300, -10d, true, false, 5),
			new TowerUpgrade(TowerUpgradeType.RANGE, 50, 1*Main.getScale(), true, false, 2),
			new TowerUpgrade(TowerUpgradeType.BULLET_COUNT, 20, 2, true, false, 3),
			new TowerUpgrade(TowerUpgradeType.DAMAGE, 20, 1, true, false, 3)
		));
		maxCooldown = TOWER_SPRAYER_BASE_COOLDOWN;
		damage = TOWER_SPRAYER_BASE_DAMAGE;
		bulletCount = TOWER_SPRAYER_BASE_PELLET_COUNT;
		name = "Sprayer";
		drawGunAngle = false;
	}

	void shoot() {
		if (gunCooldown<=0) {
			gunCooldown = maxCooldown;
			for (int i = 0;i<bulletCount;i++) {
				ProjectileManager.projectiles.add(new ProjectilePellet(this, x+towerSize/2, y+towerSize/2, damage, canDamageMetal, i*(360/bulletCount), getTowerRange()/2, TOWER_BULLET_NORMAL_INITIAL_VELOCITY, TOWER_BULLET_NORMAL_ACCELERATION));
			}
		}
	}

	@Override
	public boolean tick() {
		if (placed) {
			if (gunCooldown>0) {
				gunCooldown-=1000/Main.UPS;
			}
			if (anyInRange()) {
				shoot();
			}
		}
		else {
			tickFloating();
		}
		return destroy;
	}

	@Override
	protected void upgradeSpecial(TowerUpgrade upgrade) {}
}