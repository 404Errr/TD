package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import data.Data;
import enemy.Enemy;
import enemy.EnemyManager;
import enemy.EnemyType;
import main.Main;
import main.UpdateLoop;
import tower.Tower;
import tower.TowerManager;
import window.Window;
import window.button.Button;

public class UserInput implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener, Data {
	@Override
	public void keyPressed(KeyEvent e) {
		if (!EnemyManager.getEnemies().isEmpty()&&e.getKeyCode()==KeyEvent.VK_UP) {
			EnemyManager.getEnemies().get(0).hit(-1, false);
		}
		if (!EnemyManager.getEnemies().isEmpty()&&e.getKeyCode()==KeyEvent.VK_DOWN&&EnemyManager.getEnemies().get(0).getHealth()>1) {
			EnemyManager.getEnemies().get(0).hit(1, false);
		}
		if (e.getKeyCode()==KeyEvent.VK_M) {
			Player.changeMoney(MONEY_CHEAT_AMOUNT);
		}
		if (e.getKeyCode()==KeyEvent.VK_N) {
			if (Player.canAfford(MONEY_CHEAT_AMOUNT)) {
				Player.changeMoney(-MONEY_CHEAT_AMOUNT);
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_C) {//center the window
			Window.centerWindow();
		}
		if (e.getKeyCode()==KeyEvent.VK_F3) {//toggles debug
			Main.toggleDebug();
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) if (!Main.PAUSED) {//pause
			Main.togglePaused(true);
		}
		if (e.getKeyCode()==KeyEvent.VK_P) {//pause
			Main.togglePaused();
		}
		if (e.getKeyCode()==KeyEvent.VK_L) {
			Main.toggleLoop();
		}
		if (e.getKeyCode()==KeyEvent.VK_A) {
			Main.toggleAutoRound();
		}
		if (e.getKeyCode()==KeyEvent.VK_O) {
			Main.toggleFree();
		}
		if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			Cursor.deselectSelectedTower();
			Cursor.deselectTowerToPlace();
		}
		if (e.getKeyCode()==KeyEvent.VK_MINUS) {
			UpdateLoop.setSpeedFactor(1.0);
		}
		if (e.getKeyCode()==KeyEvent.VK_EQUALS) {
			UpdateLoop.changeSpeedFactor(SPEED_MODIFIER_MULTIPLIER);
		}
		if (e.getKeyCode()==KeyEvent.VK_1) {
			EnemyManager.getEnemies().add(new enemy.Enemy(2,EnemyType.NORMAL, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_2) {
			EnemyManager.getEnemies().add(new enemy.Enemy(2,EnemyType.CAMO, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_3) {
			EnemyManager.getEnemies().add(new enemy.Enemy(2,EnemyType.METAL, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_4) {
			EnemyManager.getEnemies().add(new enemy.Enemy(2,EnemyType.REGEN, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_5) {
			EnemyManager.getEnemies().add(new enemy.Enemy(2,EnemyType.CAMOMETAL, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_6) {
			EnemyManager.getEnemies().add(new enemy.Enemy(2,EnemyType.CAMOREGEN, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_7) {
			EnemyManager.getEnemies().add(new enemy.Enemy(2,EnemyType.METALREGEN, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_8) {
			EnemyManager.getEnemies().add(new enemy.Enemy(2,EnemyType.CAMOMETALREGEN, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_J) {
			ArrayList<Tower> towers = tower.TowerManager.getTowers();
			for (int i = 0;i<towers.size();i++) {
				Tower tower = towers.get(i);
				tower.destroy(true);
			}
			System.out.println("Deleted "+TowerManager.getTowers().size()+" towers");
		}
		if (e.getKeyCode()==KeyEvent.VK_K) {
			for (int i = 0;i<EnemyManager.getEnemies().size();i++) {
				Enemy enemy = EnemyManager.getEnemies().get(i);
				enemy.markDestroy();
			}
			System.out.println("Deleted "+EnemyManager.getEnemies().size()+" enemies");
		}
		if (e.getKeyCode()==KeyEvent.VK_S) {
			System.out.print("S key pressed:\n\t");
			Button.buttonPressed(16);
		}
		if (e.getKeyCode()==KeyEvent.VK_DELETE) {
			System.out.print("Delete key pressed:\n\t");
			Cursor.deleteSelectedTower();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_SPACE) if (Main.PAUSED) {
			Main.togglePaused(false);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {player.Cursor.updateMouse(e);}
	@Override
	public void mousePressed(MouseEvent e) {player.Cursor.updateMouse(e);player.Cursor.processClick(e, true);}
	@Override
	public void mouseReleased(MouseEvent e) {player.Cursor.updateMouse(e);player.Cursor.processClick(e, false);}
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {}
}