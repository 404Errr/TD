package window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import data.Data;
import enemy.Enemy;
import enemy.EnemyManager;
import enemy.EnemyType;
import main.Cursor;
import main.Main;
import main.Player;
import main.UpdateLoop;
import tower.Tower;
import window.button.Button;

class Input implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener, Data {
	@Override
	public void keyPressed(KeyEvent e) {
		if (!EnemyManager.enemies.isEmpty()&&e.getKeyCode()==KeyEvent.VK_UP) {
			EnemyManager.enemies.get(0).hit(-1, false);
		}
		if (!EnemyManager.enemies.isEmpty()&&e.getKeyCode()==KeyEvent.VK_DOWN&&EnemyManager.enemies.get(0).getHealth()>1) {
			EnemyManager.enemies.get(0).hit(1, false);
		}
		if (e.getKeyCode()==KeyEvent.VK_M) {
			Player.changeMoney(MONEY_CHEAT_AMOUNT);
		}
		if (e.getKeyCode()==KeyEvent.VK_N) {
			if (Player.canAfford(MONEY_CHEAT_AMOUNT)) {
				Player.changeMoney(-MONEY_CHEAT_AMOUNT);
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_C) {
			Window.centerWindow();
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
			System.out.println("x1");
			UpdateLoop.setSpeedFactor(1);
		}
		if (e.getKeyCode()==KeyEvent.VK_EQUALS) {
			System.out.println("x2");
			UpdateLoop.setSpeedFactor(2);
		}
		if (e.getKeyCode()==KeyEvent.VK_1) {
			EnemyManager.enemies.add(new enemy.Enemy(2,EnemyType.NORMAL, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_2) {
			EnemyManager.enemies.add(new enemy.Enemy(2,EnemyType.CAMO, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_3) {
			EnemyManager.enemies.add(new enemy.Enemy(2,EnemyType.METAL, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_4) {
			EnemyManager.enemies.add(new enemy.Enemy(2,EnemyType.REGEN, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_5) {
			EnemyManager.enemies.add(new enemy.Enemy(2,EnemyType.CAMOMETAL, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_6) {
			EnemyManager.enemies.add(new enemy.Enemy(2,EnemyType.CAMOREGEN, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_7) {
			EnemyManager.enemies.add(new enemy.Enemy(2,EnemyType.METALREGEN, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_8) {
			EnemyManager.enemies.add(new enemy.Enemy(2,EnemyType.CAMOMETALREGEN, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_J) {
			for (Tower tower:tower.TowerManager.towers) {
				tower.destroy(true);
			}
			System.out.println("Deleted all towers");
		}
		if (e.getKeyCode()==KeyEvent.VK_K) {
			for (Enemy enemy:EnemyManager.enemies) {
				enemy.markDestroy();
			}
			System.out.println("Deleted all enemies");
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
	public void mouseMoved(MouseEvent e) {main.Cursor.updateMouse(e);}
	@Override
	public void mousePressed(MouseEvent e) {main.Cursor.updateMouse(e);main.Cursor.click(e, true);}
	@Override
	public void mouseReleased(MouseEvent e) {main.Cursor.updateMouse(e);main.Cursor.click(e, false);}
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {}
}