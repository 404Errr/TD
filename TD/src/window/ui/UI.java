package window.ui;

import java.awt.Rectangle;
import java.util.ArrayList;

import window.button.Button;

public class UI {
	private static Rectangle bounds;
	private static ArrayList<Button> buttons;

	public static Rectangle getBounds() {
		return bounds;
	}

	public static ArrayList<Button> getButtons() {
		return buttons;
	}

	public static void init() {
		bounds = new Rectangle(main.Main.getLevel().getMap()[0].length*main.Main.getScale(), 0, main.Main.getUI_WIDTH(), main.Main.getUI_HEIGHT());
		buttons = new ArrayList<>();
		for (int y = 0;y<9;y++) {
			for (int x = 0;x<2;x++) {
				buttons.add(new Button(y, x));
			}
		}
		for (Button b:buttons) {
			b.init();
		}
		System.out.println();
	}

	public static void tick() {
		for (int i = 0;i<buttons.size();i++) {//tick the buttons
			buttons.get(i).tick();
		}
	}
}