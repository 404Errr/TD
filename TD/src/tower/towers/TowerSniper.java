package tower.towers;

import enemy.Enemy;
import enemy.EnemyManager;
import main.Main;
import main.Util;
import tower.Tower;
import tower.TowerUpgrade;

public class TowerSniper extends Tower {
	public TowerSniper(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_SNIPER_SIZE, TOWER_SNIPER_BASE_RANGE, TOWER_SNIPER_VALUE);
		maxCooldown = TOWER_SNIPER_BASE_COOLDOWN;
		damage = TOWER_SNIPER_BASE_DAMAGE;
		name = "Sniper";
	}

	void shoot(Enemy e) {
		gunAngle = Util.getAngleDegrees(x+towerSize/2, y+towerSize/2, e.getY()+ENEMY_SIZE/2, e.getX()+ENEMY_SIZE/2);
		if (gunCooldown<=0) {
			gunCooldown = maxCooldown;
			if (e.getHealth()>=damage) {
				popCount+=damage;
			}
			else {
				popCount+=e.getHealth();
			}
			e.hit(damage, true);
		}
	}

	@Override
	public boolean tick() {
		if (placed) {
			if (gunCooldown>0) {
				gunCooldown-=1000/Main.UPS;
			}
			Enemy e;
			for (int i = 0;i<EnemyManager.enemies.size();i++) {
				e = EnemyManager.enemies.get(i);
				if (!e.isDestroy()&&e.canSee(canSeeCamo)&&e.canDamage(canDamageMetal)) {
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