package main;

import java.awt.event.MouseEvent;

import tower.Tower;
import tower.TowerManager;
import window.button.Button;
import window.ui.UI;
import window.ui.UpgradeUI;

public class Cursor {
	private static int x, y;
	private static Tower selectedTower, towerToPlace;

	public static void click(MouseEvent e, boolean down) {
		boolean deselectSelectedTower = false;
		Tower towerToSelect = selectedTower;
		Button b;
		if (down) {//if click was down (not up)
			if (!UI.getBounds().contains(x, y)&&towerToPlace==null) {//check for click in main window
				Tower t;
				for (int i = 0;i<TowerManager.towers.size();i++) {
					t = TowerManager.towers.get(i);
					if (t == selectedTower) {//if clicked on the selected tower
						if (towerToSelect==selectedTower) towerToSelect = null;
						//deselectSelectedTower = true;//deselect the tower
						t.updateUpgrades();//update the tower
					}
					else if (t.getBounds().contains(x, y)) {//if clicked on a different tower
						towerToSelect = t;
						//selectTower(t);//select the tower
						t.updateUpgrades();//update the tower
					}
				}
			}
			if (e.getButton()==MouseEvent.BUTTON3) {//if right clicked
				deselectTowerToPlace();//deselect hovering tower
			}
			if (e.getButton()==MouseEvent.BUTTON1) {
				if (towerToPlace!=null&&UI.getBounds().contains(x, y)) {//if is holding tower and clicked in ui area
					deselectTowerToPlace();//deselect hovering tower
				}
				if (towerToPlace!=null&&towerToPlace.isPlaceable()&&Player.canAfford(towerToPlace.getValue())) {//if can place it
					towerToPlace.place();//place it
					deselectTowerToPlace();//stop holding the tower
				}
			}
		}
		if (towerToPlace==null) {//update buttons only if not holding anything
			for (int i = 0;i<UI.getButtons().size();i++) {
				b = UI.getButtons().get(i);
				if (b.getBounds().contains(x, y)&&e.getButton()==MouseEvent.BUTTON1) {//if cursor is on button and was leftclick
					towerToSelect = null;
					//deselectSelectedTower = true;//deselect if pressed a non upgrade button
					b.press(down);//press the button if click was down, depress if up
				}
				else {
					b.press(false);//depress the button
				}
			}
		}
		if (UpgradeUI.isOpen()) {//if needs to update the upgrade ui
			for (int i = 0;i<UpgradeUI.getButtons().size();i++) {
				b = UpgradeUI.getButtons().get(i);
				if (b.getBounds().contains(x, y)&&e.getButton()==MouseEvent.BUTTON1) {//if cursor is on button and was leftclick
					b.press(down);//press the button if click was down, depress if up
					towerToSelect = selectedTower;
					//deselectSelectedTower = false;//dont deselect if clicked an upgrade
				}
				else {
					b.press(false);//depress the button
				}
			}
		}
		if (selectedTower!=towerToSelect) selectTower(towerToSelect);
		/*if (deselectSelectedTower) {//if was told to deslect
			deselectSelectedTower();//deselect
		}*/
	}

	public static void deselectSelectedTower() {
		System.out.println("-Deselected "+selectedTower);
		selectTower(null);//deselect
	}

	public static void deselectTowerToPlace() {
		if (towerToPlace!=null) {
			setTowerToPlace(null);//deselect
		}
	}

	public static void deleteSelectedTower() {
		if (selectedTower!=null) {//has something selected
			System.out.println("Delete Tower "+selectedTower);
			selectedTower.destroy(true);//mark the tower for deletion (and give player money if TD.FREE is false)
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
		for (int i = 0;i<UI.getButtons().size();i++) {//ui buttons
			UI.getButtons().get(i).tick();//tick the button
		}
		for (int i = 0;i<UpgradeUI.getButtons().size();i++) {//upgrade ui buttons
			UpgradeUI.getButtons().get(i).tick();//tick the button
		}
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}
}