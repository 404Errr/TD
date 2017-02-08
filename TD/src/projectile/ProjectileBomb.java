package projectile;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import enemy.Enemy;
import enemy.EnemyManager;
import main.Util;
import tower.Tower;

public class ProjectileBomb extends Projectile {
	private double explosionRadius;
	private ArrayList<Enemy> inRange;
	public ProjectileBomb(Tower creator, int x, int y, double damage, double explosionRadius, boolean canDamageMetal, double angle, double velocity, double acceleration) {
		super(creator, x, y, damage);
		this.explosionRadius = explosionRadius;
		this.canDamageMetal = canDamageMetal;
		inRange = new ArrayList<>();
		ddX = Util.getXComp(angle, acceleration);
		ddY = Util.getYComp(angle, acceleration);
		dX = Util.getXComp(angle, velocity);
		dY = Util.getYComp(angle, velocity);
	}

	@Override
	protected void attemptHit() {
		Enemy e;
		hitLine = new Line2D.Double((int)x, (int)y, (int)x-dX, (int)y-dY);
		for (int i = 0;i<EnemyManager.enemies.size();i++) {
			e = EnemyManager.enemies.get(i);
			if (life>0&&!e.isDestroy()&&e.canDamage(canDamageMetal)) {
				if (hitLine.intersects(e.getHitbox())) {
					hit(e.getY(), e.getX());
					life = 0;
				}
			}
		}
	}

	private void hit(int x, int y) {
		inRange.clear();
		for (int i = 0;i<EnemyManager.enemies.size();i++) {
			if (Util.distance(this.y, this.x, EnemyManager.enemies.get(i).getX()+ENEMY_SIZE/2, EnemyManager.enemies.get(i).getY()+ENEMY_SIZE/2)<=explosionRadius) {
				inRange.add(EnemyManager.enemies.get(i));
			}
		}
		int i = 0;
		while (life>0&&i<inRange.size()) {
			if (!inRange.get(i).isDestroy()&&inRange.get(i).canDamage(canDamageMetal)) {
				damage(inRange.get(i));
			}
			i++;
		}
	}
}