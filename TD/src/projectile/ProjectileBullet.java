package projectile;

import main.Util;
import tower.Tower;

public class ProjectileBullet extends Projectile {
	public ProjectileBullet(Tower creator, int x, int y, double damage, boolean canDamageMetal, double angle, double velocity, double acceleration) {
		super(creator, x, y, damage);
		this.canDamageMetal = canDamageMetal;
		ddX = Util.getXComp(angle, acceleration);
		ddY = Util.getYComp(angle, acceleration);
		dX = Util.getXComp(angle, velocity);
		dY = Util.getYComp(angle, velocity);
	}
}