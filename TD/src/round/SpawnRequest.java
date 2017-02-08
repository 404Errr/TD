package round;

import enemy.Enemy;

public class SpawnRequest {
	private int delay;//milliseconds
	private Enemy enemy;

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