package level;

import java.awt.Point;
import java.util.ArrayList;

import graphics.Renderer;
import main.Main;

public
class Level {
	Tile[][] l;
	ArrayList<Point> path = new ArrayList<>();
	Point start, end;

	public Level(int[][] layout) {
		path = Path.getPath(layout);
		l = new Tile[layout.length][layout[0].length];
		for (int y = 0;y<layout.length;y++) {
			for (int x = 0;x<layout[0].length;x++) {
				l[y][x] = new Tile(new Point(y*Main.getScale(),x*Main.getScale()), layout[y][x], Renderer.getTileColor(layout[y][x]));
			}
		}
		start = new Point(path.get(0).x, path.get(0).y);
		end = new Point(path.get(path.size()-1).x, path.get(path.size()-1).y);
	}

	public Tile get(int y, int x) {
		return l[y][x];
	}

	public Tile[][] getMap() {
		return l;
	}

	public ArrayList<Point> getPath() {
		return path;
	}

	public Point getStart() {
		return start;
	}

	public Point getEnd() {
		return end;
	}


}