package window.button;

import java.awt.Rectangle;

import data.Data;
import window.ui.UpgradeUI;

public class UpgradeButton extends Button implements Data {
	public UpgradeButton(int x, int y, int pos) {
		super();
		bounds = new Rectangle(x+(pos*UPGRADE_UI_SIZE*3/2)+UPGRADE_UI_PADDING, y+UPGRADE_UI_PADDING, UPGRADE_UI_SIZE, UPGRADE_UI_SIZE);
	}

	@Override
	public void updatePress(boolean down) {
		if (down) {//if pressed
			pressed = true;
			System.out.println("Upgrade "+UpgradeUI.getButtons().indexOf(this)+" pressed");
			UpgradeUI.pressed(UpgradeUI.getButtons().indexOf(this));//press this buttons index of the upgrade ui buttons
		}
		else {
			pressed = false;
		}
	}
}


