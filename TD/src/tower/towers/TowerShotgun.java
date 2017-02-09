package tower.towers;

import java.util.Arrays;

import enemy.Enemy;
import enemy.EnemyManager;
import main.Main;
import main.Util;
import projectile.ProjectileBullet;
import projectile.ProjectileManager;
import tower.Tower;
import tower.TowerUpgrade;
import tower.TowerUpgradeType;

public class TowerShotgun extends Tower {
	public TowerShotgun(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_SHOTGUN_SIZE, TOWER_SHOTGUN_BASE_RANGE, TOWER_SHOTGUN_VALUE);
		availableUpgrades.addAll(Arrays.asList(
			new TowerUpgrade(TowerUpgradeType.MAX_COOLDOWN, 300, -10d, true, false, 5),
			new TowerUpgrade(TowerUpgradeType.RANGE, 50, 1*Main.getScale(), true, false, 2),
			new TowerUpgrade(TowerUpgradeType.DAMAGE, 20, 1, true, false, 3),
			new TowerUpgrade(TowerUpgradeType.BULLET_COUNT, 20, 2, true, false, 3)
		));
		maxCooldown = TOWER_SHOTGUN_BASE_COOLDOWN;
		damage = TOWER_SHOTGUN_BASE_DAMAGE;
		bulletCount = TOWER_SHOTGUN_BASE_BULLET_COUNT;
		gunSpread = TOWER_SHOTGUN_BASE_SPREAD;
		name = "Shotgun";
	}

	void shoot(Enemy e) {
		gunAngle = Util.getAngleDegrees(x+towerSize/2, y+towerSize/2, e.getY()+ENEMY_SIZE/2, e.getX()+ENEMY_SIZE/2);
		if (gunCooldown<=0) {
			gunCooldown = maxCooldown;
			double angle;
			for (int i = 0;i<bulletCount;i++) {
				angle = Util.getAngleSpread(gunAngle, gunSpread);
				ProjectileManager.getProjectiles().add(new ProjectileBullet(this, x+towerSize/2, y+towerSize/2, damage, canDamageMetal, angle, TOWER_BULLET_NORMAL_INITIAL_VELOCITY, TOWER_BULLET_NORMAL_ACCELERATION));
			}
		}
	}

	@Override
	public boolean tick() {
		if (placed) {
			if (gunCooldown>0) {
				gunCooldown-=1000/Main.UPS;
			}
			Enemy e;
			for (int i = 0;i<EnemyManager.getEnemies().size();i++) {
				e = EnemyManager.getEnemies().get(i);
				if (inRange(e)&&!e.isDestroy()&&e.canSee(canSeeCamo)) {
					shoot(e);
					break;
				}
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

