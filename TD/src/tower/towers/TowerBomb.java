package tower.towers;

import enemy.Enemy;
import enemy.EnemyManager;
import main.Main;
import main.Util;
import projectile.ProjectileBomb;
import projectile.ProjectileManager;
import tower.Tower;
import tower.TowerUpgrade;

public class TowerBomb extends Tower {
	public TowerBomb(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_BOMB_SIZE, TOWER_BOMB_BASE_RANGE, TOWER_BOMB_VALUE);
		maxCooldown = TOWER_BOMB_BASE_COOLDOWN;
		damage = TOWER_BOMB_BASE_DAMAGE;
		explosionRadius = TOWER_BOMB_BASE_EXPLOSION_RADIUS*Main.getScale();
		metalBonusDamage = TOWER_BOMB_BASE_METAL_BONUS_DAMAGE;
		canDamageMetal = true;
		name = "Bomb";
	}

	public double getMetalBonusDamage() {
		return metalBonusDamage;
	}

	void shoot(Enemy target) {
		gunAngle = Util.getAngleDegrees(x+towerSize/2, y+towerSize/2, target.getY()+ENEMY_SIZE/2, target.getX()+ENEMY_SIZE/2);
		if (gunCooldown<=0) {
			gunCooldown = maxCooldown;
			ProjectileManager.addProjectile(new ProjectileBomb(this, x+towerSize/2, y+towerSize/2, damage, explosionRadius, canDamageMetal, gunAngle, TOWER_BULLET_NORMAL_INITIAL_VELOCITY, TOWER_BULLET_NORMAL_ACCELERATION));
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
