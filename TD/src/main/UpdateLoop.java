package main;

import enemy.EnemyManager;
import player.Cursor;
import projectile.ProjectileManager;
import round.RoundManager;
import tower.TowerManager;
import window.Window;
import window.ui.UpgradeUI;

public class UpdateLoop implements Runnable {
	private static double speedFactor = 1.0;

	@Override
	public void run() {
		System.out.println("UPS: "+Main.getUPS());
		double delay = 1000000000d/Main.getUPS();
		long currentTime, lastTime = 0;
		while (Main.isRunning()) {
			currentTime = System.nanoTime();//makes currentTime the current time
			if (currentTime-lastTime>=delay*speedFactor) {//if the difference between the current time and the last time is greater than delay*speedfactor
				update();//do the updates
				Window.renderer.repaint();//refresh the screen
				lastTime = System.nanoTime();//makes lastTime the current time
			}
		}

	}

//	@Override
//	public void run() {
//		System.out.println("UPS: "+Main.UPS);
//		final long updateSpeed = 1000000000/Main.UPS;
//		long startTime = 0, wait = 0;
//		while (Main.RUNNING) {
//			startTime = System.nanoTime();
//
//			update();
//			Window.renderer.repaint();
//
//			wait = (long)(((updateSpeed/speedFactor)-(System.nanoTime()-startTime))/1000000);
//			if (Main.SPAM_CONSOLE) {
//				System.out.println(updateSpeed+" "+(System.nanoTime()-startTime)+" "+wait+" "+((Main.PAUSED)?"PAUSED":""));
//			}
//			try {
//				if (wait>0) {
//					Thread.sleep(wait);
//				}
//				else {
//					System.out.println("LAGGING "+wait);
//				}
//			}
//			catch (Exception e) {
//				System.out.println("-UPDATE LOOP ERROR");
//				e.printStackTrace();
//			}
//		}
//	}

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
		if (Cursor.getTowerToPlace()!=null) {
			Cursor.getTowerToPlace().tick();
		}
		if (!Main.PAUSED) {
			try {
				RoundManager.tick();
				for (int i = 0;i<EnemyManager.enemies.size();i++) if (EnemyManager.enemies.get(i).tick()) {
					System.out.println("-Deleted "+EnemyManager.enemies.get(i));
					EnemyManager.enemies.remove(i);
				}
				EnemyManager.tick();
				for (int i = 0;i<ProjectileManager.projectiles.size();i++) if (ProjectileManager.projectiles.get(i).tick()) {
					ProjectileManager.projectiles.remove(i);
				}
				for (int i = 0;i<TowerManager.towers.size();i++) if (TowerManager.towers.get(i).tick()) {
					TowerManager.towers.remove(i);
				}
				UpgradeUI.tick();
			}
			catch (Exception e) {
				UpdateLoop.setSpeedFactor(1.0);
				System.out.println("-UPDATE ERROR");
				e.printStackTrace();

			}
		}
	}
}
