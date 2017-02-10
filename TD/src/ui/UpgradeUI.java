package ui;

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
	private static Tower currentTower;//the tower it is focusing on
	private static TowerUpgrade focusedUpgrade;//TODO
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
		if (currentTower!=null) {//if there is a current tower
			TowerUpgrade upgrade = currentTower.getAvailableUpgrades().get(b);
			if (Player.canAfford(upgrade.getPrice())) {//can afford upgrade
				Player.changeMoney(-upgrade.getPrice());//charge money
				boolean delete = false;
				if (upgrade.hasChild()) {//if has child
					if (upgrade.getRepeat()>0) {//if has repeat
						upgrade.decreaseRepeat();//-=1
						if (upgrade.getRepeat()==0) {//if out of repeats
							delete = true;//delete it
						}
					}
					else {
						currentTower.getAvailableUpgrades().set(currentTower.getAvailableUpgrades().indexOf(upgrade), upgrade.getChild());//replace upgrade with its child
					}
				}
				else {//if doesnt have child
					delete = true;//delete it
				}
				currentTower.getToActivate().add(upgrade);//place upgrade in activation queue
				if (delete) {//if should delete
					currentTower.getAvailableUpgrades().remove(upgrade);//remove from available list
				}
				currentTower.updateUpgrades();//update the tower
			}
			System.out.println(upgrade.getType());
			setRefresh();//refresh ui
		}
	}

	public static void setNewPos(int x, int y) {
		Rectangle2D bounds = null;
		double xOffset, yOffset, distance = Main.getScale()*UPGRADE_UI_DISTANCE;
		for (int i = 0;i<UPGRADE_UI_ANGLES.length&&(i==0||!Window.bounds.contains(bounds)||UI.getBounds().intersects(bounds));i++) {//for each programmed position && still not valid
			xOffset = Util.getXComp(UPGRADE_UI_ANGLES[i], distance);
			yOffset = Util.getYComp(UPGRADE_UI_ANGLES[i], distance);
			bounds = new Rectangle2D.Double(x+xOffset-getXSize()/2-UPGRADE_UI_PADDING, y+yOffset-getYSize()/2-UPGRADE_UI_PADDING, getXSize()+UPGRADE_UI_PADDING*2, getYSize()+UPGRADE_UI_PADDING*2);
		}
		UpgradeUI.x = (int)bounds.getX();//move it
		UpgradeUI.y = (int)bounds.getY();//move it
	}

}