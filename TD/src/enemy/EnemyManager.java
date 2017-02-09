package enemy;

import java.util.ArrayList;
import java.util.Collections;

import data.Data;

public class EnemyManager implements Data {
	private static ArrayList<Enemy> enemies = new ArrayList<>();
	private static EnemySorter sorter = new EnemySorter();
	private static boolean canSpawnEnemies;

	public static boolean areNoEnemies() {
		return enemies.size()==0;
	}

	private static void sortEnemies() {
		try {
			Collections.sort(enemies,sorter);
		}
		catch (Exception e) {}
	}

	public static void spawnEnemy(Enemy enemy) {
		if (canSpawnEnemies()) {
			enemy.setVisible(true);
			enemies.add(enemy);
		}
		else {
			System.out.println("Can't spawn "+enemy);
		}
	}

	public static void tick() {
		sortEnemies();
		canSpawnEnemies = enemies.size()<=SAFE_ENEMY_COUNT;
	}

	public static boolean canSpawnEnemies() {
		return canSpawnEnemies;
	}

	public static ArrayList<Enemy> getEnemies() {
		return enemies;
	}
}
