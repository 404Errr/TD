package round;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import data.Data;
import enemy.EnemyType;
import main.Util;

public class RoundConfig implements Data {

	static void init() {
		//roundnum:delay,health,type;delay,health,type:roundnum:delay,health,type;countxdelay,health,type:

		ArrayList<String> rawConfig = new ArrayList<>(Arrays.asList(ROUND_CONFIG.split(":")));
		ArrayList<Integer> roundNumbers = new ArrayList<>();
		ArrayList<String> roundContents = new ArrayList<>();
		ArrayList<Round> rounds = new ArrayList<>();

		for (String str:rawConfig) {
//			Util.removeSpaces(str);
			if (!str.equals("")&&Util.isNumber(str)) {
				roundNumbers.add(Integer.valueOf(str));
			}
			else {
				roundContents.add(str);
			}
		}

		//System.out.println("\n"+rawConfig+"\n"+roundNumbers+"\n"+roundContents+"\n");
		int currentContents = 0;
		for (int i = 0;i<=Collections.max(roundNumbers);i++) {
			if (roundNumbers.contains(i)) {
				ArrayList<SpawnRequest> spawns = new ArrayList<>();
				String[] spawnsStr = roundContents.get(currentContents).split(";");
				currentContents++;
				for (String rawSpawnStr:spawnsStr) {
					String[] spawnStr = rawSpawnStr.split(",");
					int copies = 1;
					if (spawnStr[0].contains("x")) {
						String[] temp = spawnStr[0].split("x");
						spawnStr[0] = temp[1];
						try {
							copies = Integer.parseInt(temp[0]);
						}
						catch (NumberFormatException e) {
							System.out.println("--copy error");
							System.exit(0);
						}
					}
					int delay = 0;
					try {
						delay = Integer.parseInt(spawnStr[0]);
					}
					catch (NumberFormatException e) {
						System.out.println("--delay error");
						System.exit(0);
					}
					double health = 0;
					try {
						health = Double.parseDouble(spawnStr[1]);
					}
					catch (NumberFormatException e) {
						System.out.println("--health error");
						System.exit(0);
					}
					EnemyType type = null;
					try {
						type = EnemyType.valueOf(spawnStr[2]);
					}
					catch (Exception e) {
						System.out.println("--type error");
						System.exit(0);
					}
					for (int j = 0;j<copies;j++) {
						spawns.add(new SpawnRequest((spawns.size()>0)?delay:0, new enemy.Enemy(health, type, false)));
					}
				}
				rounds.add(new Round(spawns, i));
			}
			else {
				rounds.add(new Round(i));
			}
		}
		System.out.print("\nNumber of rounds: "+rounds.size()+"\nRoundNum, TotalTime, TotalHealth, Spawns\n");
		for (Round r:rounds) {
			int totalHealth = 0, totalTime = 0;
			for (SpawnRequest sR:r.getSpawns()) {
				totalHealth+=sR.getEnemy().getHealth();
				totalTime+=sR.getDelay();
			}
			System.out.println(r.getRoundNum()+" "+totalTime+" "+totalHealth+" "+r.getSpawns());
			RoundManager.addRound(r);
		}
		System.out.println();
	}
}