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
	private static int x, y;
	private static boolean open, refresh;
	private static Tower currentTower;
//	private static TowerUpgrade focusedUpgrade;//TODO
//	TODO stats of current tower and stuff
	private static ArrayList<TowerUpgrade> upgradeList = new ArrayList<>();
	private static ArrayList<UpgradeButton> buttons = new ArrayList<>();

	private static void open(Tower tower) {
		currentTower = tower;//set current tower to the given tower
		if (currentTower!=null&&currentTower.getAvailableUpgrades().size()>0) {//if current tower exists and has upgrades
			upgradeList.clear();//clear list
			buttons.clear();//clear list
			setNewPos(currentTower.getX()+currentTower.getTowerSize()/2, currentTower.getY()+currentTower.getTowerSize()/2);//move the ui
			for (int i = 0;i<currentTower.getAvailableUpgrades().size();i++) {//for available updgrade in the selected tower
				upgradeList.add(currentTower.getAvailableUpgrades().get(i));//add upgrades to local list
				buttons.add(new UpgradeButton(x, y, i));//create new buttons
				buttons.get(i).tick();//tick the new buttons
			}
			open = true;//open it
		}
	}

	private static void close() {
		open = false;//close it
	}

	private static void refreshUI() {
		System.out.println("=Refreshing Upgrade UI");
		close();
		open(Cursor.getSelectedTower());//give selected tower

	}

	public static void tick() {
		if (refresh) {
			refreshUI();
			refresh = false;
		}
	}

	public static void setRefresh() {
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

	public static void setNewPos(int x, int y) {
		Rectangle2D bounds;
		int attempts = 0;
		double a = 90, dA = 180, xOffset, yOffset, distance = Main.getScale()*3;
		do {
			xOffset = Util.getXComp(a, distance);
			yOffset = Util.getYComp(a, distance);
			bounds = new Rectangle2D.Double(x+xOffset-getXSize()/2-UPGRADE_UI_PADDING, y+yOffset-getYSize()/2-UPGRADE_UI_PADDING, getXSize()+UPGRADE_UI_PADDING*2, getYSize()+UPGRADE_UI_PADDING*2);
			a+=dA;
			if (a>=360) {
				a-=360;
			}
			if (dA==180) {
				dA = 45;
			}
			attempts++;
		}
		while (attempts<14&&(!Window.bounds.contains(bounds)||UI.getBounds().intersects(bounds)));
		UpgradeUI.x = (int)bounds.getX();
		UpgradeUI.y = (int)bounds.getY();

	}

}