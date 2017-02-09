package window.ui;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import data.Data;
import main.Main;
import main.Util;
import player.Cursor;
import player.Player;
import tower.Tower;
import tower.TowerUpgrade;
import window.Window;
import window.button.UpgradeButton;

public class UpgradeUI implements Data {
	private static int x, y;//position
	private static boolean open, refresh;//if its open, if it should refresh the ui next frame
	private static Tower currentTower;//the tower it is using
	private static TowerUpgrade focusedUpgrade;//TODO
//	TODO stats of current tower and stuff
	private static ArrayList<TowerUpgrade> upgradeList = new ArrayList<>();
	private static ArrayList<UpgradeButton> buttons = new ArrayList<>();

	private static void refreshUI() {
		System.out.println("=Refreshing Upgrade UI");
		open = false;//close the ui
		currentTower = Cursor.getSelectedTower();//set current tower to the selected tower
		if (currentTower!=null&&currentTower.getAvailableUpgrades().size()>0) {//if current tower exists and has available upgrades
			upgradeList.clear();//clear list
			buttons.clear();//clear list
			setNewPos(currentTower.getX()+currentTower.getTowerSize()/2, currentTower.getY()+currentTower.getTowerSize()/2);//move the ui
			for (int i = 0;i<currentTower.getAvailableUpgrades().size();i++) {//for available updgrade in the selected tower
				upgradeList.add(currentTower.getAvailableUpgrades().get(i));//add upgrades to local list
				buttons.add(new UpgradeButton(x, y, i));//create new buttons
				buttons.get(i).tick();//tick the new buttons
			}
			open = true;//open it if current tower exists and has availableupgrades
		}
	}

	public static void tick() {
		if (refresh) {//if was told to refresh
			refreshUI();//refresh
			refresh = false;//reset
		}
		for (int i = 0;i<buttons.size();i++) {//tick the buttons
			buttons.get(i).tick();
		}
	}

	public static void setRefresh() {//refresh the ui next frame
		refresh = true;
	}

	public static ArrayList<UpgradeButton> getButtons() {
		return buttons;
	}

	public static ArrayList<TowerUpgrade> getUpgradeList() {
		return upgradeList;
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public static TowerUpgrade getFocusedUpgrade() {
		return focusedUpgrade;
	}

	public static void setFocusedUpgrade(TowerUpgrade focusedUpgrade) {
		UpgradeUI.focusedUpgrade = focusedUpgrade;
	}

	public static int getXSize() {
		if (currentTower!=null) return currentTower.getAvailableUpgrades().size()*UPGRADE_UI_SIZE*3/2-UPGRADE_UI_SIZE/2;
		return UPGRADE_UI_SIZE;
	}

	public static int getYSize() {
		return UPGRADE_UI_SIZE;
	}

	public static boolean isOpen() {
		return open;
	}

	public static void pressed(int b) {
		if (currentTower!=null) {
			TowerUpgrade upgrade = currentTower.getAvailableUpgrades().get(b);
			if (Player.canAfford(upgrade.getPrice())) {
				Player.changeMoney(-upgrade.getPrice());
				boolean delete = false;
				if (upgrade.hasChild()) {
					if (upgrade.getRepeat()>0) {
						upgrade.decreaseRepeat();
						if (upgrade.getRepeat()==0) {
							delete = true;
						}
					}
					else {
						currentTower.getAvailableUpgrades().set(currentTower.getAvailableUpgrades().indexOf(upgrade), upgrade.getChild());
					}
				}
				else {
					delete = true;
				}
				currentTower.getToActivate().add(upgrade);
				if (delete) {
					currentTower.getAvailableUpgrades().remove(upgrade);
				}
				currentTower.updateUpgrades();
			}
			System.out.println(upgrade.getType());
			setRefresh();
		}
	}

	private final static int DISTANCE = 6;
	public static void setNewPos(int x, int y) {
		Rectangle2D bounds;
		int attempts = 0;//prevent infinite looping if it can't find a position
		double a = 90, dA = 180, xOffset, yOffset, distance = Main.getScale()*DISTANCE;//the current angle, the current angle increment (180 to check above after below (only once)), where it is relative to the given postion (x), where it is relative to the given postion (y), how far away it will be from the given position
		do {
			xOffset = Util.getXComp(a, distance);
			yOffset = Util.getYComp(a, distance);
			bounds = new Rectangle2D.Double(x+xOffset-getXSize()/2-UPGRADE_UI_PADDING, y+yOffset-getYSize()/2-UPGRADE_UI_PADDING, getXSize()+UPGRADE_UI_PADDING*2, getYSize()+UPGRADE_UI_PADDING*2);
			a+=dA;
			if (a>=360) {//if its greater than 360
				a-=360;
			}
			if (dA==180) {//after the second check is unsuccessful go to 45 degree increments (instead of the initial 180)
				dA = 45;
			}
			attempts++;
		} while (attempts<14&&(!Window.bounds.contains(bounds)||UI.getBounds().intersects(bounds)));//if it has tried less than 14 times && if its off of the screen (and not on the ui)
		UpgradeUI.x = (int)bounds.getX();//move it
		UpgradeUI.y = (int)bounds.getY();//move it

	}

}