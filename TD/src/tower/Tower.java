package tower;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import data.Data;
import enemy.Enemy;
import enemy.EnemyManager;
import level.Tile;
import main.Cursor;
import main.Main;
import main.Player;
import main.Util;
import window.Window;
import window.ui.UI;

public abstract class Tower implements Data {
	protected int x, y, towerSize, towerRange;
	public double popCount;
	protected double gunAngle;
	protected double gunCooldown;
	protected double maxCooldown;
	protected double damage;
	protected double value;
	protected double bulletCount;
	protected double gunSpread;
	protected double hitCount;
	protected double iceSpeedModifier;
	protected double iceSlowDownDuration;
	protected double metalBonusDamage;
	protected double explosionRadius;
	protected boolean destroy, placed, canSeeCamo, canDamageMetal, drawGunAngle;
	protected Ellipse2D bounds;
	protected Rectangle2D hitbox;
	protected String name;
	protected ArrayList<TowerUpgrade> activeUpgrades = new ArrayList<>(), toActivate = new ArrayList<>(), availableUpgrades = new ArrayList<>();

	protected Tower(int x, int y, boolean placed, double size, double range, double towerTurretValue) {
		if (x>0&&y>0) {
			this.x = x;
			this.y = y;
		}
		else {
			this.x = -1000;
			this.y = -1000;
		}
		this.placed = placed;
		this.value = towerTurretValue;
		towerSize = (int)(Main.getScale()*size);
		towerRange = (int)(Main.getScale()*range);
		drawGunAngle = true;
		bounds = Util.getCircle(x, y, towerSize, false);
		hitbox = Util.getRect(x, y, towerSize);
	}

	public boolean anyInRange() {
		for (int i = 0;i<EnemyManager.enemies.size();i++) if (inRange(EnemyManager.enemies.get(i))&&EnemyManager.enemies.get(i).canSee(canSeeCamo)) return true;
		return false;
	}

	public boolean canDamageMetal() {
		return canDamageMetal;
	}

	public void destroy(boolean sell) {
		System.out.println(name+" deleted: "+x+","+y+"\tFor: "+value);
		if (sell&&!Main.FREE) {
			Player.changeMoney(value*TOWER_SELL_RATE);
		}
		destroy = true;
	}

	public Ellipse2D getBounds() {
		return bounds;
	}

	public double getDamage() {
		return damage;
	}

	public boolean getDrawGunAngle() {
		return drawGunAngle;
	}

	public double getGunAngle() {
		return gunAngle;
	}

	public double getGunCooldown() {
		return gunCooldown;
	}

	public Rectangle2D getHitbox() {
		return hitbox;
	}

	public double getMaxCooldown() {
		return maxCooldown;
	}

	public String getName() {
		return name;
	}

	public double getPopCount() {
		return popCount;
	}

	public int getTowerRange() {
		return towerRange;
	}

	public int getTowerSize() {
		return towerSize;
	}

	public double getValue() {
		return value;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ArrayList<TowerUpgrade> getToActivate() {
		return toActivate;
	}

	public ArrayList<TowerUpgrade> getAvailableUpgrades() {
		return availableUpgrades;
	}

	public boolean inRange(Enemy e) {
		return inRange(e.getY()+ENEMY_SIZE/2,e.getX()+ENEMY_SIZE/2);
	}

	public boolean inRange(int x, int y) {
		return Util.distance(this.x+towerSize/2, this.y+towerSize/2, x, y)<=towerRange/2;
	}

	public boolean isCanDamageMetal() {
		return canDamageMetal;
	}

	public boolean isCanSeeCamo() {
		return canSeeCamo;
	}

	public boolean isPlaceable() {
		if (UI.getBounds().intersects(hitbox)||!Window.bounds.contains(hitbox)) return false;
		for (int i = 0;i<TowerManager.towers.size();i++) {
			Tower t = TowerManager.towers.get(i);
			if (hitbox.intersects(t.hitbox)) return false;
		}
		Tile t;
		for (int i = 0;i<main.Main.getLevel().getMap().length;i++) {
			for (int j = 0;j<main.Main.getLevel().getMap()[0].length;j++) {
				t = main.Main.getLevel().getMap()[i][j];
				if (t.getType()!=0&&hitbox.intersects(t.getBounds())) return false;
			}
		}
		return true;
	}

	public boolean isPlaced() {
		return placed;
	}

	public void place() {
		placed = true;
		if (!Main.FREE) {
			Player.changeMoney(-value);
		}
		bounds = Util.getCircle(x, y, towerSize, false);
		hitbox = Util.getRect(x, y, towerSize);
		TowerManager.towers.add(this);
		System.out.println(name+" placed: "+x+","+y+"\tFor: "+value);
	}

	public abstract boolean tick();

	protected void tickFloating() {
		x = Cursor.getX()-towerSize/2;
		y = Cursor.getY()-towerSize/2;
		bounds = Util.getCircle(x, y, towerSize, false);
		hitbox = Util.getRect(x, y, towerSize);
	}

	@Override
	public String toString() {
		return name;
	}

	public void updateUpgrades() {
		for (TowerUpgrade upgrade:toActivate) {
			if (upgrade.getType().isSpecial()) {
				upgradeSpecial(upgrade);
			}
			else {
				upgradeBasic(upgrade);
			}
			activeUpgrades.add(upgrade);
		}
		toActivate.clear();
		System.out.println("Updated "+this);
	}

	private void upgradeBasic(TowerUpgrade upgrade) {//TODO
		switch(upgrade.getType()) {
		case BULLET_COUNT:
			bulletCount = upgrade.getNewValue(bulletCount);
			break;
		case GUN_SPREAD:
			gunSpread = upgrade.getNewValue(gunSpread);
			break;
		case DAMAGE:
			damage = upgrade.getNewValue(damage);
			break;
		case MAX_COOLDOWN:
			maxCooldown = upgrade.getNewValue(maxCooldown);
			break;
		case RANGE:
			towerRange = upgrade.getNewValue(towerRange);
			break;
		case HIT_COUNT:
			hitCount = upgrade.getNewValue(hitCount);


		default:
			break;

		}
	}

	protected abstract void upgradeSpecial(TowerUpgrade upgrade);
}
