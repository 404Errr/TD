package enemy;

import java.util.ArrayList;
import java.util.Collections;

public class EnemyManager {
	public static ArrayList<Enemy> enemies = new ArrayList<>();
	private static EnemySorter sorter = new EnemySorter();

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
		enemy.setVisible(true);
		enemies.add(enemy);
	}

	public static void tick() {
		sortEnemies();
	}
}
