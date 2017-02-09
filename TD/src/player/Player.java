package player;

import data.Data;
import main.Main;

public class Player implements Data {
	private static double playerMoney = STARTING_MONEY, playerHealth = STARTING_LIVES;

	public static boolean canAfford(double amount) {
		return Main.FREE||amount<=playerMoney;
	}

	public static void changeMoney(double dMoney) {
		if (dMoney<0) {
			System.out.println("Balance: "+playerMoney+" --> "+(playerMoney+dMoney)+"\tChanged by: "+dMoney);
		}
		playerMoney+=dMoney;
	}

	public static double getPlayerHealth() {
		return playerHealth;
	}

	public static double getPlayerMoney() {
		return playerMoney;
	}

	public static void changeLives(int dLives) {
		playerHealth+=dLives;
	}

}