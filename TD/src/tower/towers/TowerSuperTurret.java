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

public class TowerSuperTurret extends Tower {
	public TowerSuperTurret(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_SUPER_TURRET_SIZE, TOWER_SUPER_TURRET_BASE_RANGE, TOWER_SUPER_TURRET_VALUE);
		availableUpgrades.addAll(Arrays.asList(
			new TowerUpgrade(TowerUpgradeType.MAX_COOLDOWN, 200, -10d, true, false, 3),
			new TowerUpgrade(TowerUpgradeType.RANGE, 100, 2*Main.getScale(), true, false, 3),
			new TowerUpgrade(TowerUpgradeType.DAMAGE, 230, 1, true, false, 4)
		));
		maxCooldown = TOWER_SUPER_TURRET_BASE_COOLDOWN;
		damage = TOWER_SUPER_TURRET_BASE_DAMAGE;
		name = "Super Turret";
	}

	void shoot(Enemy target) {
		gunAngle = Util.getAngleDegrees(x+towerSize/2, y+towerSize/2, target.getY()+ENEMY_SIZE/2, target.getX()+ENEMY_SIZE/2);
		if (gunCooldown<=0) {
			gunCooldown = maxCooldown;
			ProjectileManager.getProjectiles().add(new ProjectileBullet(this, x+towerSize/2, y+towerSize/2, damage, canDamageMetal, gunAngle, TOWER_BULLET_NORMAL_INITIAL_VELOCITY, TOWER_BULLET_NORMAL_ACCELERATION));
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
