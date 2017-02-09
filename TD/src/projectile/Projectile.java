package projectile;

import java.awt.geom.Line2D;

import data.Data;
import enemy.Enemy;
import enemy.EnemyManager;
import main.Main;
import player.Player;
import tower.Tower;
import tower.towers.TowerBomb;

public abstract class Projectile implements Data {
	protected double x, y, dX, dY, ddX, ddY, life;
	protected int size;
	protected boolean visible, damages, destroy, canDamageMetal;
	protected Tower creator;
	protected Line2D hitLine;

	public Projectile(Tower c, int x, int y) {
		this.creator = c;
		this.x = x;
		this.y = y;
	}

	public Projectile(Tower c, int x, int y, double damage) {
		this.creator = c;
		this.x = x;
		this.y = y;
		damages = true;
		this.life = damage;
	}

	protected void attemptHit() {
		hitLine = new Line2D.Double((int)x, (int)y, (int)x-(dX*Main.PROJECTILE_BASE_SPEED), (int)y+(dY*Main.PROJECTILE_BASE_SPEED));//creates projectiles "hitbox" (position to future position)
		Enemy e;
		for (int i = 0;i<EnemyManager.enemies.size();i++) {//for all enemies
			e = EnemyManager.enemies.get(i);
			if (life>0&&!e.isDestroy()&&e.canDamage(canDamageMetal)) {//if enemy isnt dead && enemy isnt marked for deletion && tower can damage the enemy
				if (e.getHitbox()!=null&&hitLine.intersects(e.getHitbox())) {//if the projectiles "hitbox" intersects with the enemy's hitbox
					damage(e);//damage the enemy
				}
			}
		}
	}

	public boolean tick() {
		move();
		tickSpecial();
		if (x<-100||y<-100||x>(Main.getLevel().getMap()[0].length*Main.getScale())+100||y>(Main.getLevel().getMap().length*Main.getScale())+100||life<=0) {//dead or off screen
			markDestroy();
		}
		if (damages) {//if able to damage
			attemptHit();//try to damage (anything/something)
		}
		return destroy;//return if projectile should be destroyed
	}

	void tickSpecial() {}//used if projectile subclass needs it

	protected void move() {
		dX+=ddX*Main.PROJECTILE_BASE_SPEED;
		dY+=ddY*Main.PROJECTILE_BASE_SPEED;
		x+=dX*Main.PROJECTILE_BASE_SPEED;
		y-=dY*Main.PROJECTILE_BASE_SPEED;
	}

	protected void damage(Enemy e) {
		double damage = life;
		if (e.isMetal()&&creator instanceof TowerBomb) {
			damage*=((TowerBomb) creator).getMetalBonusDamage();
		}
		if (e.getHealth()>0&&damage>0) {
			double temp = damage;
			if (e.getHealth()>=damage) {
				creator.addPops(damage);
			}
			else {
				creator.addPops(e.getHealth());
				Player.changeMoney(e.getHealth());
			}
			life-=e.getHealth();
			e.hit(temp, true);
		}
	}

	protected void markDestroy() {
		destroy = true;
	}

	public double getDdX() {
		return ddX;
	}

	public double getDdY() {
		return ddY;
	}

	public double getdX() {
		return dX;
	}

	public double getdY() {
		return dY;
	}

	public double getLife() {
		return life;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
