package player;

import java.awt.event.MouseEvent;

import tower.Tower;
import tower.TowerManager;
import window.button.Button;
import window.ui.UI;
import window.ui.UpgradeUI;

public class Cursor {
	private static int x, y;
	private static Tower selectedTower, towerToPlace;

	public static void processClick(MouseEvent e, boolean down) {
		Tower towerToSelect = selectedTower;
		boolean deselectTowerToPlace = false;
		Button b;
		if (down) {//if click was down (not up)
			if (!UI.getBounds().contains(x, y)&&towerToPlace==null) {//check for click in main window
				Tower t;
				for (int i = 0;i<TowerManager.towers.size();i++) {
					t = TowerManager.towers.get(i);
					if (t == selectedTower&&towerToSelect==selectedTower) {//if clicked on the selected tower && if towerToSelect hasn't changed (yet)
						towerToSelect = null;//deselect the tower
						t.updateUpgrades();//update the tower
					}
					else if (t.getBounds().contains(x, y)) {//if clicked on a different tower (not t)
						towerToSelect = t;//select the tower
						t.updateUpgrades();//update the tower
					}
				}
			}
			if (e.getButton()==MouseEvent.BUTTON3) {//if right clicked
				deselectTowerToPlace = true;//stop holding the tower
			}
			if (e.getButton()==MouseEvent.BUTTON1) {
				if (towerToPlace!=null&&UI.getBounds().contains(x, y)) {//if is holding tower and clicked in ui area
					deselectTowerToPlace = true;//stop holding the tower
				}
				if (towerToPlace!=null&&towerToPlace.isPlaceable()&&Player.canAfford(towerToPlace.getValue())) {//if can place it
					towerToPlace.place();//place it
					deselectTowerToPlace = true;//stop holding the tower
				}
			}
		}
		if (towerToPlace==null) {//update buttons only if not holding anything
			for (int i = 0;i<UI.getButtons().size();i++) {//for all UI buttons
				b = UI.getButtons().get(i);
				if (b.getBounds().contains(x, y)&&e.getButton()==MouseEvent.BUTTON1) {//if cursor is on button and was leftclick
					towerToSelect = null;//deselect if pressed a non upgrade button
					b.updatePress(down);//press the button if click was down
				}
				else {
					b.updatePress(false);
				}
			}
		}
		if (UpgradeUI.isOpen()) {//if needs to update the upgrade ui
			for (int i = 0;i<UpgradeUI.getButtons().size();i++) {//for all upgrade buttons
				b = UpgradeUI.getButtons().get(i);
				if (b.getBounds().contains(x, y)&&e.getButton()==MouseEvent.BUTTON1) {//if cursor is on button and was leftclick
					b.updatePress(down);//press the button if click was down
					towerToSelect = selectedTower;//don't change selectedTower
				}
				else {
					b.updatePress(false);
				}
			}
		}
		if (deselectTowerToPlace) {//if told to deselect tower to place
			deselectTowerToPlace();//deselect tower to place
		}
		if (selectedTower!=towerToSelect) {//if towerToSelect will change
			selectTower(towerToSelect);//select what was told to select
		}
	}

	public static void deselectSelectedTower() {
		System.out.println("-Deselected "+selectedTower);
		selectTower(null);//deselect
	}

	public static void deselectTowerToPlace() {
		setTowerToPlace(null);//deselect
	}

	public static void deleteSelectedTower() {
		if (selectedTower!=null) {//has something selected
			System.out.println("Delete Tower "+selectedTower);
			selectedTower.destroy(true);//mark the tower for deletion (and give player money if should give money)
			deselectSelectedTower();//deselect
		}
		else {
			System.out.println("Delete tower\n\tnothing selected");
		}
	}

	public static Tower getSelectedTower() {
		return selectedTower;
	}

	public static void selectTower(Tower toSelect) {
		selectedTower = toSelect;
		if (selectedTower!=null) {//if didnt set to null (deselect)
			System.out.println("+Selected "+selectedTower);
		}
		UpgradeUI.setRefresh();//refresh ui (because selected tower changed)
	}

	public static void setTowerToPlace(Tower towerToPlace) {
		Cursor.towerToPlace = towerToPlace;
	}

	public static Tower getTowerToPlace() {
		return towerToPlace;
	}

	public static void updateMouse(MouseEvent e) {
		x = e.getX()-3;//offset 3
		y = e.getY()-25;//offset 25
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}
}
