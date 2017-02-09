package main;

import enemy.EnemyManager;
import player.Cursor;
import projectile.ProjectileManager;
import round.RoundManager;
import tower.TowerManager;
import window.Window;
import window.ui.UI;
import window.ui.UpgradeUI;

public class UpdateLoop implements Runnable {
	private static double speedFactor = 1.0;//speed multiplier (0.5 is 2x speed)

	@Override
	public void run() {
		System.out.println("UPS: "+Main.getUPS());//prints ups
		double delay = 1000000000d/Main.getUPS();//gets nanosecond delay per frame
		long currentTime, lastTime = 0;
		while (Main.isRunning()) {
			currentTime = System.nanoTime();//makes currentTime the current time
			if (currentTime-lastTime>=delay*speedFactor) {//if the difference between the current time and the last time is greater than delay*speedfactor
				lastTime = currentTime;//makes lastTime the current time
				update();//do the updates
				Window.renderer.repaint();//refresh the screen
			}
		}
	}

	public static void printSpeedFactor() {
		System.out.println("x"+1/UpdateLoop.getSpeedFactor());
	}

	public static void setSpeedFactor(double speedFactor) {
		UpdateLoop.speedFactor = speedFactor;
		printSpeedFactor();
	}

	public static void changeSpeedFactor(double dSpeedFactor) {
		UpdateLoop.speedFactor*=dSpeedFactor;
		printSpeedFactor();
	}

	public static double getSpeedFactor() {
		return speedFactor;
	}

	private void update() {
		try {
			if (Cursor.getTowerToPlace()!=null) {
				Cursor.getTowerToPlace().tick();
			}
			UpgradeUI.tick();
			UI.tick();
			if (!Main.PAUSED) {
				RoundManager.tick();
				for (int i = 0;i<EnemyManager.getEnemies().size();i++) if (EnemyManager.getEnemies().get(i).tick()) {
					System.out.println("-Deleted "+EnemyManager.getEnemies().get(i));
					EnemyManager.getEnemies().remove(i);
				}
				EnemyManager.tick();
				for (int i = 0;i<ProjectileManager.getProjectiles().size();i++) if (ProjectileManager.getProjectiles().get(i).tick()) {
					ProjectileManager.getProjectiles().remove(i);
				}
				ProjectileManager.tick();
				for (int i = 0;i<TowerManager.getTowers().size();i++) if (TowerManager.getTowers().get(i).tick()) {
					TowerManager.getTowers().remove(i);
				}
				TowerManager.tick();
			}
		}
		catch (Exception e) {
			UpdateLoop.setSpeedFactor(1.0);
			System.out.println("-UPDATE ERROR");
			e.printStackTrace();
		}
	}
}
