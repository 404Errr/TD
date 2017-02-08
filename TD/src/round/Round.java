package round;

import java.util.ArrayList;

public class Round {
	private ArrayList<SpawnRequest> spawns = new ArrayList<>();
	private int roundNum;
	private boolean custom;

	public Round(ArrayList<SpawnRequest> spawns, int roundNum) {
		for (SpawnRequest sR:spawns) {
			this.spawns.add(sR);
		}
		this.roundNum = roundNum;
		custom = true;
	}

	public Round(int roundNum) {
		this.roundNum = roundNum;
	}

	public int getRoundNum() {
		return roundNum;
	}

	public ArrayList<SpawnRequest> getSpawns() {
		return spawns;
	}

	public boolean isCustom() {
		return custom;
	}

	@Override
	public String toString() {
		if (custom) return roundNum+": "+spawns;
		return roundNum+": "+"?";
	}
}
