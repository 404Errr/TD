package enemy;

import java.awt.geom.Rectangle2D;

import data.Data;
import main.Main;
import main.Util;
import player.Player;

public class Enemy implements Comparable<Enemy>, Data {
	private int x, y, regenTimer, icedTimer;
	private double speed, iceSpeedModifier = 1, pathPos, health, initHealth;
	private boolean visible, destroy, iced;
	private EnemyType type;
	private Rectangle2D hitbox;

	public Enemy(double health, EnemyType type, boolean visible) {
		this.initHealth = health;
		this.health = health;
		x = Main.getLevel().getStart().y;
		y = Main.getLevel().getStart().x;
		this.type = type;
		updateSpeed();
		this.visible = visible;
	}

	public boolean canDamage(boolean metal) {
		if (isMetal()&&!metal) return false;
		return true;
	}

	public boolean canSee(boolean camo) {
		if (isCamo()&&!camo) return false;
		return true;
	}

	@Override
	public int compareTo(Enemy e) {
		if (this.pathPos>e.pathPos) return -1;
		if (this.pathPos<e.pathPos) return 1;
		return 0;
	}

	public void markDestroy() {
		if (!destroy) {
			destroy = true;
			visible = false;
		}
	}

	public double getHealth() {
		return health;
	}

	public Rectangle2D getHitbox() {
		return hitbox;
	}

	public double getInitHealth() {
		return initHealth;
	}

	public double getPathPos() {
		return pathPos;
	}

	public double getRegenTimer() {
		return regenTimer;
	}

	public int getSides() {
		int sides = (int)health+2;
		if (sides<3) {
			sides = 3;
		}
		return sides;
	}

	public double getSpeed() {
		return speed;
	}

	EnemyType getType() {
		return type;
	}

	public int getX() {
		return y;
	}

	public int getY() {
		return x;
	}

	public void hit(double damage, boolean givesMoney) {
		double initHealth = health;
		health-=damage;
		if (health<=0) {//check death
			markDestroy();//kill
			if (givesMoney) {
				Player.changeMoney(initHealth-health);//partial reward
			}
		}
		else if (givesMoney) {
			Player.changeMoney(damage);//full reward
		}
		if (type.regen) {
			regenTimer = ENEMY_REGEN_RATE;
		}
		updateSpeed();
	}

	public void ice(double modifier, int duration) {
		setIced(true);
		iceSpeedModifier = modifier;
		icedTimer = duration;
	}

	public boolean isCamo() {
		return type.camo;
	}

	public boolean isDestroy() {
		return destroy;
	}

	public boolean isIced() {
		return iced;
	}

	public boolean isMetal() {
		return type.metal;
	}

	public boolean isRegen() {
		return type.regen;
	}

	public boolean isVisible() {
		return visible;
	}

	private void move() {
		pathPos+=speed;
		if (pathPos<0||pathPos>=Main.getLevel().getPath().size()) {//check out of bounds; move to beginning or delete
			if (Main.LOOP_ENEMIES) {
				pathPos = 0;
			}
			else {
				markDestroy();
				double hurt = health;
				if (health>(int)health) {
					hurt = (int)health+1;
				}
				Player.changeLives(-(int)hurt);//remove playerhealth
			}

		}
		else {//set position based on pathpos
			y = Main.getLevel().getPath().get((int) pathPos).x;
			x = Main.getLevel().getPath().get((int) pathPos).y;
		}
	}

	private void regen() {
		health+=speed;//regens by speed
		if (health>initHealth) {
			health = initHealth;//if health is too high, reset to max
		}
	}

	public void setIced(boolean iced) {
		this.iced = iced;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean tick() {
		if (iced) {
			icedTimer-=1000/Main.UPS;
			if (icedTimer<=0) {
				unIce();
			}
		}
		updateSpeed();
		move();
		hitbox = Util.getRect(x-ENEMY_HITBOX_SIZE/2+ENEMY_SIZE/2, y-ENEMY_HITBOX_SIZE/2+ENEMY_SIZE/2, ENEMY_HITBOX_SIZE);
		if (type.regen) {
			if (regenTimer>0) {
				regenTimer-=1000/Main.UPS;
				if (health<initHealth&&regenTimer<=0) {
					regen();
					if (health<initHealth) {
						regenTimer = ENEMY_REGEN_RATE;
					}
					else {
						regenTimer = 0;
					}
				}
			}
		}
		if (health<=0) {
			markDestroy();
		}
		return destroy;
	}

	@Override
	public String toString() {
		return initHealth+","+type;
	}

	void unIce() {
		setIced(false);
		iceSpeedModifier = 1;
		icedTimer = 0;
	}

	double updateSpeed() {
		double health = this.health;
		if (health<1) {
			health = 1;//fractional health is treated as 1
		}
		speed = (Math.log(health)*1.2d+1d)*iceSpeedModifier*Main.ENEMY_BASE_SPEED;
		return speed;
	}
}
