package main;

import enemy.EnemyManager;
import projectile.ProjectileManager;
import round.RoundManager;
import tower.TowerManager;
import window.Window;
import window.ui.UpgradeUI;

public class UpdateLoop implements Runnable {
	private static double speedFactor = 1;


	@Override
	public void run() {
		System.out.println("UPS: "+Main.UPS);
		final long updateSpeed = 1000000000/Main.UPS;
		long startTime = 0, wait = 0;
		while (Main.RUNNING) {
			startTime = System.nanoTime();

			update();
			Window.renderer.repaint();

			wait = (long)(((updateSpeed/speedFactor)-(System.nanoTime()-startTime))/1000000);
			if (Main.SPAM_CONSOLE) {
				System.out.println(updateSpeed+" "+(System.nanoTime()-startTime)+" "+wait+" "+((Main.PAUSED)?"PAUSED":""));
			}
			try {
				if (wait>0) {
					Thread.sleep(wait);
				}
				else {
					System.out.println("LAGGING "+wait);
				}
			}
			catch (Exception e) {
				System.out.println("-UPDATE LOOP ERROR");
				e.printStackTrace();
			}
		}
	}

	public static void setSpeedFactor(double speedFactor) {
		UpdateLoop.speedFactor = speedFactor;
	}



	//---
//	@Override
//	public void run() {
//		// convert the time to seconds
//		double delta = 1/TD.UPS;
//		double nextTime = System.nanoTime() / 1000000000.0;
//		double maxTimeDiff = 0.5;
//		int skippedFrames = 1;
//		int maxSkippedFrames = 5;
//		while (TD.RUNNING) {
//			// convert the time to seconds
//			double currTime = System.nanoTime() / 1000000000.0;
//			if ((currTime-nextTime) > maxTimeDiff) {
//				nextTime = currTime;
//			}
//			if (currTime>=nextTime) {
//				// assign the time for the next update
//				nextTime+=delta;
//				update();
//				if ((currTime<nextTime)||(skippedFrames>=maxSkippedFrames)) {
//					Window.renderer.repaint();
//					System.out.println(skippedFrames);
//					skippedFrames = 1;
//				}
//				else {
//					skippedFrames++;
//				}
//			}
//			else {
//				// calculate the time to sleep
//				int sleepTime = (int) (1000.0 * (nextTime - currTime));
//				// sanity check
//				if (sleepTime > 0) {
//					// sleep until the next update
//					try {
//						Thread.sleep(sleepTime);
//					} catch (InterruptedException e) {
//						// do nothing
//					}
//				}
//			}
//
//		}
//	}
	//---

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
				System.out.println("-UPDATE ERROR");
				e.printStackTrace();
			}
		}
	}
}
