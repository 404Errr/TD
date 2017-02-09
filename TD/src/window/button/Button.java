package window.button;

import java.awt.Font;
import java.awt.Rectangle;

import data.Data;
import main.Main;
import player.Cursor;
import round.RoundManager;
import tower.TowerManager;
import tower.towers.TowerBomb;
import tower.towers.TowerIce;
import tower.towers.TowerNinja;
import tower.towers.TowerShotgun;
import tower.towers.TowerSniper;
import tower.towers.TowerSprayer;
import tower.towers.TowerSuperTurret;
import tower.towers.TowerTurret;
import window.ui.UI;

public class Button implements Data {
	protected Rectangle bounds;
	protected boolean hovered, pressed;
	protected String text0 = "", text1 = "", text2 = "";
	protected Font textFont = null;

	Button() {}

	public Button(int i, int j) {
		bounds = new Rectangle(Main.getLevel().getMap()[0].length*Main.getScale()+(j*Main.getScale()*3)+Main.getScale(), (i*Main.getScale()*3)+Main.getScale(), Main.getScale()*2, Main.getScale()*2);
	}

	static final int DELETE_BUTTON = 17;
	public static void buttonPressed(int function) {
		switch (function) {
			case 0:
				System.out.println("Create Turret");
				Cursor.setTowerToPlace(new TowerTurret(0, 0, false));
				break;
			case 1:
				System.out.println("Create Super Turret");
				Cursor.setTowerToPlace(new TowerSuperTurret(0, 0, false));
				break;
			case 2:
				System.out.println("Create Sniper");
				Cursor.setTowerToPlace(new TowerSniper(0, 0, false));
				break;
			case 3:
				System.out.println("Create Sprayer");
				Cursor.setTowerToPlace(new TowerSprayer(0, 0, false));
				break;
			case 4:
				System.out.println("Create Shotgun");
				Cursor.setTowerToPlace(new TowerShotgun(0, 0, false));
				break;
			case 5:
				System.out.println("Create Ninja");
				Cursor.setTowerToPlace(new TowerNinja(0, 0, false));
				break;
			case 6:
				System.out.println("Create Ice");
				Cursor.setTowerToPlace(new TowerIce(0, 0, false));
				break;
			case 7:
				System.out.println("Create Bomb");
				Cursor.setTowerToPlace(new TowerBomb(0, 0, false));
				break;
//			case 10:
//				System.out.println();
//				TowerManager.towers.add(new TowerTurret(20*Main.getScale(), 20*Main.getScale(), true));
//				TowerManager.towers.add(new TowerTurret(20*Main.getScale(), 23*Main.getScale(), true));
//				break;
			case 11:
				System.out.println();
				//2
				//27, 14
				for (int i = 0;i<3;i++) {
					for (int j = 0;j<3;j++) {
						TowerManager.addTower(new TowerTurret((i*27+2)*Main.getScale(), (j*14+2)*Main.getScale(), true));
					}
				}
//				TowerManager.towers.add(new TowerTurret(2*Main.getScale(), 2*Main.getScale(), true));
//				TowerManager.towers.add(new TowerTurret(55*Main.getScale(), 2*Main.getScale(), true));
//				TowerManager.towers.add(new TowerTurret(2*Main.getScale(), 29*Main.getScale(), true));
//				TowerManager.towers.add(new TowerTurret(55*Main.getScale(), 29*Main.getScale(), true));
//				TowerManager.towers.add(new TowerTurret(27*Main.getScale(), 2*Main.getScale(), true));
//				TowerManager.towers.add(new TowerTurret(2*Main.getScale(), 14*Main.getScale(), true));
//				TowerManager.towers.add(new TowerTurret(27*Main.getScale(), 29*Main.getScale(), true));
//				TowerManager.towers.add(new TowerTurret(55*Main.getScale(), 14*Main.getScale(), true));
//				TowerManager.towers.add(new TowerTurret(27*Main.getScale(), 14*Main.getScale(), true));
				break;
//			case 12:
//				System.out.println();
//				TowerManager.towers.add(new TowerTurret(8*Main.getScale(), 9*Main.getScale(), true));
//				TowerManager.towers.add(new TowerTurret(8*Main.getScale(), 19*Main.getScale(), true));
//				break;
//			case 13:
//				System.out.println();
//				TowerManager.towers.add(new TowerTurret(10*Main.getScale(), 10*Main.getScale(), true));
//				TowerManager.towers.add(new TowerTurret(6*Main.getScale(), 10*Main.getScale(), true));
//				TowerManager.towers.add(new TowerTurret(10*Main.getScale(), 18*Main.getScale(), true));
//				TowerManager.towers.add(new TowerTurret(6*Main.getScale(), 18*Main.getScale(), true));
//				break;
			case 14:
				Main.togglePaused();
				break;
			case 15:
				Main.toggleAutoRound();
				break;
			case 16:
				System.out.println("Starting next round");
				if (RoundManager.stillHasRounds()) {
					RoundManager.setStartRound(true);
				}
				else {
					System.out.println("\tOut of rounds");
				}
				break;
			case DELETE_BUTTON:
				Cursor.deleteSelectedTower();
				break;
			default:
				System.out.println("no effect");
				break;
		}
	}

	void buttonText(int button) {
		switch (button) {
			case 0:
				text0 = (int)TOWER_TURRET_VALUE+"";
				text2 = "Turret";
				textFont = new Font("Helvetica", Font.BOLD, main.Main.getScale()/2);
				break;
			case 1:
				text0 = (int)TOWER_SUPER_TURRET_VALUE+"";
				text1 = "Super";
				text2 = "Turret";
				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()/2);
				break;
			case 2:
				text0 = (int)TOWER_SNIPER_VALUE+"";
				text2 = "Sniper";
				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()/2);
				break;
			case 3:
				text0 = (int)TOWER_SPRAYER_VALUE+"";
				text2 = "Sprayer";
				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()/2);
				break;
			case 4:
				text0 = (int)TOWER_SHOTGUN_VALUE+"";
				text2 = "Shotgun";
				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()*7/16);
				break;
			case 5:
				text0 = (int)TOWER_NINJA_VALUE+"";
				text2 = "Ninja";
				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()/2);
				break;
			case 6:
				text0 = (int)TOWER_ICE_VALUE+"";
				text2 = "Ice";
				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()/2);
				break;
			case 7:
				text0 = (int)TOWER_BOMB_VALUE+"";
				text2 = "Bomb";
				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()/2);
				break;
//			case 10:
//				text0 = "    D";
//				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()/2);
//				break;
//			case 11:
//				text0 = "    A";
//				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()/2);
//				break;
//			case 12:
//				text0 = "    B";
//				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()/2);
//				break;
//			case 13:
//				text0 = "    C";
//				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()/2);
//				break;
			case 14:
				text2 = "PAUSE";
				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()/2);
				break;
			case 15:
				text1 = "AUTO";
				text2 = "ROUND";
				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()/2);
				break;
			case 16:
				text0 = "Start";
				text1 = "Next";
				text2 = "Round";
				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()/2);
				break;
			case DELETE_BUTTON:
				text1 = "Sell";
				text2 = "Tower";
				textFont = new Font("Helvetica", Font.BOLD, Main.getScale()*5/8);
				break;
			default:
				break;
		}
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public String getText0() {
		return text0;
	}

	public String getText1() {
		return text1;
	}

	public String getText2() {
		return text2;
	}

	public Font getTextFont() {
		return textFont;
	}

	public void init() {
		buttonText(UI.getButtons().indexOf(this));
		System.out.println(UI.getButtons().indexOf(this)+": "+text0+", "+text1+", "+text2);
	}

	public boolean isHovered() {
		return hovered;
	}

	public boolean isPressed() {
		return pressed;
	}

	public void updatePress(boolean down) {
		if (down) {
			pressed = true;
			System.out.print("button "+UI.getButtons().indexOf(this)+" pressed:\n\t");
			buttonPressed(UI.getButtons().indexOf(this));
		}
		else {
			pressed = false;
		}
	}

	public void tick() {
		if (bounds.contains(Cursor.getX(), Cursor.getY())) {
			hovered = true;
		}
		else {
			hovered = false;
		}
	}

}