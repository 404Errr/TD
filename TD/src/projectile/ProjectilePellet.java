package projectile;

import main.Main;
import main.Util;
import tower.Tower;

public class ProjectilePellet extends Projectile {
	private double traveled, range;

	public ProjectilePellet(Tower creator, int x, int y, double damage, boolean canDamageMetal, double angle, double range, double velocity, double acceleration) {
		super(creator, x, y, damage);
		this.range = range/2;
		this.canDamageMetal = canDamageMetal;
		ddX = Util.getXComp(angle, acceleration);
		ddY = Util.getYComp(angle, acceleration);
		dX = Util.getXComp(angle, velocity);
		dY = Util.getYComp(angle, velocity);
	}

	@Override
	protected void move() {
		dX+=ddX*Main.PROJECTILE_BASE_SPEED;
		dY+=ddY*Main.PROJECTILE_BASE_SPEED;
		traveled+=Util.distance((int)x, (int)y, (int)(x+dX), (int)(y+dY));
		x+=dX*Main.PROJECTILE_BASE_SPEED;
		y-=dY*Main.PROJECTILE_BASE_SPEED;
	}

	@Override
	void tickSpecial() {
		if (traveled>=range) {
			markDestroy();
		}
	}
}