package level;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import main.Main;
import main.Util;

public class Tile {
	private Point pos;
	private Color color;
	private int type;
	private Rectangle2D bounds;

	Tile(Point p, int type, Color c) {
		this.type = type;
		pos = p;
		color = c;
		bounds = Util.getRect(pos.y, pos.x, Main.getScale());
	}

	public Rectangle2D getBounds() {
		return bounds;
	}

	public Color getColor() {
		return color;
	}

	public int getType() {
		return type;
	}
}
