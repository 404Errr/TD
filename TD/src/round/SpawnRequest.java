package round;

import enemy.Enemy;

public class SpawnRequest {
	private int delay;//milliseconds until this enemy will spawn
	private Enemy enemy;//the enemy to spawn

	SpawnRequest(int delay, Enemy enemy) {
		this.delay = delay;
		this.enemy = enemy;
	}

	int getDelay() {
		return delay;
	}

	Enemy getEnemy() {
		return enemy;
	}

	@Override
	public String toString() {
		return delay+" "+enemy;
	}
}