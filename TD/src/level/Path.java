package level;

import java.awt.Point;
import java.util.ArrayList;

import main.Main;

public class Path {
	private static int[][] layout;
	private static boolean[][] looked;
	private static Point start, end;
	private static ArrayList<Point> path = new ArrayList<>();
	private static ArrayList<Point> rawPath = new ArrayList<>();

	private static Point findEnd() {
		for (int y = 0;y<layout.length;y++) {
			for (int x = 0;x<layout[0].length;x++) if (layout[y][x]==9) {
				layout[y][x] = 7;
				return new Point(y,x);
			}
		}
		System.out.println("-no end");
		System.exit(0);
		return null;
	}

	private static Point findStart() {
		for (int y = 0;y<layout.length;y++) {
			for (int x = 0;x<layout[0].length;x++) if (layout[y][x]==8) {
				return new Point(y,x);
			}
		}
		System.out.println("-no start");
		System.exit(0);
		return null;
	}
	static void finishPath() {
		int y = 0, x = 0, direction = 0;
		Point pCur = null, pNext = null, pPrevious;
		for (int i = 0;i<rawPath.size();i++) {
			y = 0;
			x = 0;
			pCur = rawPath.get(i);
			if (i!=rawPath.size()-1) {
				pNext = rawPath.get(i+1);
			}
			for (int j = 0;j<Main.getScale();j++) {
				path.add(new Point(pCur.x+x, pCur.y+y));
				if (i!=rawPath.size()-1) {
					if (pCur.y<pNext.y&&pCur.x==pNext.x) {
						direction = 0;
					}
					if (pCur.y>pNext.y&&pCur.x==pNext.x) {
						direction = 1;
					}
					if (pCur.y==pNext.y&&pCur.x>pNext.x) {
						direction = 2;
					}
					if (pCur.y==pNext.y&&pCur.x<pNext.x) {
						direction = 3;
					}
				}
				switch (direction) {
					case 0:
						y++;
						break;
					case 1:
						y--;
						break;
					case 2:
						x--;
						break;
					case 3:
						x++;
						break;
				}
			}
		}
		y = 0;
		x = 0;
		pCur = rawPath.get(0);
		pPrevious = rawPath.get(1);
		for (int j = 0;j<Main.getScale()*2;j++) {
			if (j!=0) {
				path.add(0,new Point(pCur.x+x, pCur.y+y));
			}
			if (pCur.y<pPrevious.y&&pCur.x==pPrevious.x) {
				y--;
			}
			if (pCur.y>pPrevious.y&&pCur.x==pPrevious.x) {
				y++;
			}
			if (pCur.y==pPrevious.y&&pCur.x>pPrevious.x) {
				x++;
			}
			if (pCur.y==pPrevious.y&&pCur.x<pPrevious.x) {
				x--;
			}
		}

	}

	static ArrayList<Point> getPath(int[][] layout) {
		Path.layout = layout.clone();
		looked = new boolean[layout.length][layout[0].length];
		start = findStart();
		end = findEnd();
		for (int y = 0;y<layout.length;y++) {
			for (int x = 0;x<layout[0].length;x++) if (layout[y][x]==8||layout[y][x]==9) {
				Path.layout[y][x] = 7;
			}
		}
		solvePath(start.x,start.y);
		start.y*=Main.getScale();
		start.x*=Main.getScale();
		end.y*=Main.getScale();
		end.x*=Main.getScale();
		finishPath();
		System.out.println("\nPath size: "+path.size()+"\n"+path);
		return path;
	}

	private static boolean open(int y, int x) {
		return y>=0&&x>=0&&y<layout.length&&x<layout[0].length&&!looked[y][x]&&layout[y][x]==7;
	}

	private static boolean solvePath(int y, int x) {
		looked[y][x] = true;
//		boolean done = false;
		rawPath.add(new Point(y*Main.getScale(),x*Main.getScale()));
		if (end.y==y&&end.x==x) return true;
		if (open(y,x+1)) {
			return solvePath(y,x+1);//R
		}
		if (open(y+1,x)) {
			return solvePath(y+1,x);//D
		}
		if (open(y,x-1)) {
			return solvePath(y,x-1);//L
		}
		if (open(y-1,x)) {
			return solvePath(y-1,x);//U
		}
//		return done;
		return false;
	}
}
