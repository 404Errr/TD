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
import window.button.ButtonUpgrade;

public class UpgradeUI implements Data {
	private static int x, y;
	private static boolean open, refresh;
	private static Tower currentTower;
	private static ArrayList<TowerUpgrade> upgradeList = new ArrayList<>();
	private static ArrayList<ButtonUpgrade> buttons = new ArrayList<>();

	static void open(Tower tower) {
		currentTower = tower;
		if (currentTower!=null&&currentTower.getAvailableUpgrades().size()>0) {
			upgradeList.clear();
			buttons.clear();
			setNewPos(currentTower.getX()+currentTower.getTowerSize()/2, currentTower.getY()+currentTower.getTowerSize()/2);
			for (int i = 0;i<currentTower.getAvailableUpgrades().size();i++) {
				upgradeList.add(currentTower.getAvailableUpgrades().get(i));
				buttons.add(new ButtonUpgrade(x, y, i));
			}
//			System.out.println("+Opening Upgrade UI");
			open = true;
		}
	}

	static void close() {
//		System.out.println("-Closing Upgrade UI");
		open = false;
		upgradeList.clear();
		buttons.clear();
	}

	private static void refresh() {
		System.out.println("=Refresh Upgrade UI");
		close();
		open(Cursor.getSelectedTower());

	}

	public static void tick() {
		if (refresh) {
			refresh();
			refresh = false;
		}
	}

	public static void setRefresh() {
		refresh = true;
	}

	public static ArrayList<ButtonUpgrade> getButtons() {
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