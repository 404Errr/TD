package tower;

import java.util.ArrayList;

import data.Data;

public class TowerManager implements Data {
	private static ArrayList<Tower> towers = new ArrayList<>();
	private static boolean canSpawntowers;

	public static void tick() {
		canSpawntowers = towers.size()>SAFE_TOWER_COUNT;
	}

	public static ArrayList<Tower> getTowers() {
		return towers;
	}

	public static boolean canSpawnTowers() {
		return canSpawntowers;
	}

	public static void addTower(Tower tower) {
		towers.add(tower);
	}
}

