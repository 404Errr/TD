package round;

import java.util.ArrayList;

import data.Data;
import enemy.EnemyManager;
import main.Main;
import main.Player;

public class RoundManager implements Data {
	private static ArrayList<Round> rounds = new ArrayList<>();
	private static int roundNumber, currentSpawn;
	private static double tilNextSpawn;
	private static Round currentRound = null;
	private static boolean startRound, stillHasRounds = true, awarded;
	private static boolean firstRound = true;

	static void addRound(Round round) {
		rounds.add(round);
	}

	public static int getRoundNumber(boolean visual) {
		if (visual) return roundNumber+1;
		else return roundNumber;
	}

	public static void init() {
		RoundConfig.init();
	}

	public static void setStartRound(boolean startRound) {
		RoundManager.startRound = startRound;
	}


	static void spawnNextEnemy() {
		System.out.println("+Spawned "+currentRound.getSpawns().get(currentSpawn).getEnemy());
		EnemyManager.spawnEnemy(currentRound.getSpawns().get(currentSpawn).getEnemy());
		currentSpawn++;
		if (currentSpawn<currentRound.getSpawns().size()) {
			tilNextSpawn = currentRound.getSpawns().get(currentSpawn).getDelay();
		}
	}

	static void startNextRound() {
		if (!firstRound) {
			roundNumber++;
		}
		else {
			firstRound = false;
		}
		System.out.println("Round number: "+getRoundNumber(false));
		if (roundNumber<rounds.size()) {
			currentRound = rounds.get(roundNumber);
			if (currentRound.getSpawns().size()==0) {
				startNextRound();
			}
			currentSpawn = 0;
			tilNextSpawn = currentRound.getSpawns().get(currentSpawn).getDelay();
		}
		else {
			System.out.println("Out of rounds");
			stillHasRounds = false;
		}
	}

	public static boolean stillHasRounds() {
		return stillHasRounds;
	}

	public static void tick() {
		if (currentRound!=null) {//round going on
			if (tilNextSpawn>0) {//can't spawn check
				tilNextSpawn-=1000/Main.UPS;//loop delay
			}
			else {//new enemy/round check
				if (currentSpawn>=currentRound.getSpawns().size()) {//next round
					if (EnemyManager.areNoEnemies()) {
						if (!awarded) {
							Player.changeMoney(ROUND_MONEY_REWARD+roundNumber);
							awarded = true;
						}
						if (stillHasRounds&&(startRound||Main.AUTO_ROUND)) {
							startNextRound();
							awarded = false;
						}
					}
					startRound = false;
				}
				else {//next enemy
					spawnNextEnemy();
				}
			}
		}
		else
			if (startRound) {
			startNextRound();//frist round
		}
	}
}
