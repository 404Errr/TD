package projectile;

import java.util.ArrayList;

import data.Data;

public class ProjectileManager implements Data {
	private static ArrayList<Projectile> projectiles = new ArrayList<>();
	private static boolean canSpawnProjectiles;

	public static void tick() {
		canSpawnProjectiles = projectiles.size()>SAFE_PROJECTILE_COUNT;
	}

	public static boolean canSpawnProjectiles() {
		return canSpawnProjectiles;
	}

	public static ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
}
