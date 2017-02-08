package main;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Util {

	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static double distance(double x1, double y1, int x2, int y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static double distance(int x1, int y1, double x2, double y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static double distance(int x1, int y1, int x2, int y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

	public static double getAngleDegrees(int x, int y, int xT, int yT) {
		double result = Math.toDegrees(Math.atan2(-(xT-x), -(yT-y)))+90;
		if (result<0) {
			result+=360;
		}
		return result;
	}

	public static double getAngleSpread(double angle, double spread) {
		double offset = (Math.random()-0.5)*spread;
		return angle+offset;
	}

	public static Ellipse2D getCircle(int x, int y, int size, boolean center) {
		if (center) return new Ellipse2D.Double(x-size/2, y-size/2, size, size);
		else return new Ellipse2D.Double(x, y, size, size);
	}

	public static Polygon getPoly(int x, int y, int sides, int size) {
		Polygon poly = new Polygon();
		double a, shift = 0;
		for (int i = 0;i<=sides;i++) {
			if (sides%2!=0) {
				shift = Math.PI;
			}
			else {
				shift = Math.PI/sides;
			}
			a = Math.PI/(sides/2d)*i+shift;
			poly.addPoint((int)(Math.round(x+Math.sin(a)*size)),(int)(Math.round(y+Math.cos(a)*size)));
		}
		return poly;
	}

	public static Rectangle2D getRect(int x, int y, int size) {
		return new Rectangle(x, y, size, size);
	}

	public static Rectangle2D getRect(int x, int y, int sizeX, int sizeY) {
		return new Rectangle(x, y, sizeX, sizeY);
	}

	public static double getXComp(double angle, double mag) {
		return Math.cos(Math.toRadians(angle))*mag;
	}

	public static double getYComp(double angle, double mag) {
		return Math.sin(Math.toRadians(angle))*mag;
	}

	public static boolean isNumber(String str) {
		for (char c:str.toCharArray()) if (!Character.isDigit(c)) return false;
		return true;
	}
}
