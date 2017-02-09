package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JPanel;

import data.Data;
import enemy.Enemy;
import enemy.EnemyManager;
import main.Main;
import main.Util;
import player.Cursor;
import player.Player;
import projectile.Projectile;
import projectile.ProjectileManager;
import round.RoundManager;
import tower.Tower;
import tower.TowerManager;
import window.button.Button;
import window.ui.UI;
import window.ui.UpgradeUI;

public @SuppressWarnings("serial")
class Renderer extends JPanel implements Data {
	public static Color getTileColor(int c) {
		switch (c) {
			case 0:
				return COLOR_0;
			case 1:
				return COLOR_1;
			case 7:
				return COLOR_0;
		}
		System.out.println("Tile color error.");
		System.exit(0);
		return null;
	}

	private Graphics2D g;

	private void drawEnemies(Graphics2D g) {
		Enemy e;
		for (int i = 0;i<EnemyManager.enemies.size();i++) {
			e = EnemyManager.enemies.get(i);
			if (e.isVisible()) {
				drawEnemy(g, e);
				g.setFont(new Font("Helvetica", Font.PLAIN, Main.getScale()/2));
				g.setColor(Color.BLACK);
				if (Main.PAUSED) {
					g.drawString((EnemyManager.enemies.indexOf(e)==0)+" "+e.getHealth()+"-"+e.getSides()+"-"+(int)e.getSpeed(), e.getY(), e.getX()-Main.getScale());
				}
			}
		}
	}

	private void drawEnemy(Graphics2D g, Enemy e) {
		Polygon poly = Util.getPoly(e.getY()+ENEMY_SIZE/2, e.getX()+ENEMY_SIZE/2, e.getSides(), Main.ENEMY_SIZE);
		Polygon innerPoly = Util.getPoly(e.getY()+ENEMY_SIZE/2, e.getX()+ENEMY_SIZE/2, e.getSides(), Main.ENEMY_SIZE/2);
		boolean makeCamoInner = e.isCamo(), makeRegenBorder = e.isRegen();
		Color mainColor = COLOR_NORMAL_ENEMY, innerColor = COLOR_CAMO_ENEMY;
		if (e.isMetal()) {
			mainColor = COLOR_METAL_ENEMY;
		}
		g.setColor(mainColor);
		g.fill(poly);
		if (makeCamoInner) {
			g.setColor(innerColor);
			g.fill(innerPoly);
		}
		g.setStroke(new BasicStroke(3));
		if (makeRegenBorder) {
			g.setColor(COLOR_REGEN_ENEMY_BORDER);
		}
		else {
			g.setColor(COLOR_NORMAL_ENEMY_BORDER);
		}
		g.draw(poly);
		if (e.isIced()) {
			g.setColor(COLOR_ENEMY_ICED);
			g.fill(poly);
		}
	}

	private void drawFloatingTower(Graphics2D g) {
		if (Cursor.getTowerToPlace()!=null) {
			drawTower(g, Cursor.getTowerToPlace(), true);
		}
	}

	void drawGrid(Graphics2D g) {
		for (int i = 0;i<Main.getLevel().getMap().length;i++) {
			for (int j = 0;j<Main.getLevel().getMap()[0].length;j++) {
				g.setColor(Main.getLevel().get(i, j).getColor());
				g.fill(Main.getLevel().get(i, j).getBounds());
				if (Main.getLevel().getPath().contains(new Point(i*Main.getScale(), j*Main.getScale()))) {
					g.setColor(COLOR_PATH);
					g.fill(Main.getLevel().get(i, j).getBounds());
				}
			}
		}
	}

	private void drawPlayerStats(Graphics2D g) {
		g.setColor(COLOR_UI_HEALTH);
		g.setFont(FONT_UI_HEALTH);
		g.drawString("\u2665 "+(int)Player.getPlayerHealth(), Main.getSCREEN_WIDTH()-Main.getUI_WIDTH()+Main.getScale()/4, Main.getUI_HEIGHT()-Main.getScale()/8-Main.getScale()/4-Main.getScale());
		g.setColor(COLOR_UI_TEXT);
		g.setFont(FONT_UI_TEXT);
		g.drawString("& "+(int)Player.getPlayerMoney(), Main.getSCREEN_WIDTH()-Main.getUI_WIDTH()+Main.getScale()/4, Main.getUI_HEIGHT()-Main.getScale()*3/16);
		g.drawString("Round "+RoundManager.getRoundNumber(true), Main.getSCREEN_WIDTH()-Main.getUI_WIDTH()+Main.getScale()/4, Main.getUI_HEIGHT()-Main.getScale()/2-Main.getScale()*2);
	}

	private void drawProjectiles(Graphics2D g) {
		Projectile p;
		for (int i = 0;i<ProjectileManager.projectiles.size();i++) {
			p = ProjectileManager.projectiles.get(i);
			g.setStroke(new BasicStroke(3));
			g.setColor(Color.BLUE);
			g.draw(Util.getCircle((int)p.getX(), (int)p.getY(), 4, true));
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(1));
			g.drawLine((int)p.getX(), (int)p.getY(), (int)(p.getX()+p.getdX()*2), (int)(p.getY()-p.getdY()*2));
			g.setFont(new Font("Helvetica", Font.PLAIN, Main.getScale()/2));
			g.drawString(p.getLife()+"", (int)p.getX(), (int)p.getY()+30);
		}
	}

	private void drawTower(Graphics2D g, Tower t, boolean floating) {
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.BLACK);
		g.draw(t.getBounds());
		g.setColor(COLOR_TOWER_RANGE);
		if (floating&&(!t.isPlaceable()||!Player.canAfford(t.getValue()))) {
			g.setColor(COLOR_TOWER_RANGE_INVALID);
		}
		g.setStroke(new BasicStroke(1));
		if (Cursor.getSelectedTower()==t||Cursor.getTowerToPlace()==t) {
			g.fill(Util.getCircle(t.getX()+t.getTowerSize()/2, t.getY()+t.getTowerSize()/2, t.getTowerRange(), true));
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("Helvetica", Font.PLAIN, Main.getScale()/2));
		g.drawString(t.getName()+" "+t.getPopCount()+" "+(int)t.getGunAngle(), t.getX(), t.getY()+60);
		if (t.getDrawGunAngle()) {
			g.drawLine(t.getX()+t.getTowerSize()/2, t.getY()+t.getTowerSize()/2, t.getX()+t.getTowerSize()/2+(int)Util.getXComp(t.getGunAngle(), t.getTowerRange()/2), t.getY()+t.getTowerSize()/2-(int)Util.getYComp(t.getGunAngle(), t.getTowerRange()/2));
		}
	}

	private void drawTowers(Graphics2D g) {
		for (int i = 0;i<TowerManager.towers.size();i++) {
			drawTower(g, TowerManager.towers.get(i), false);
		}
	}

	private void drawUI(Graphics2D g) {
		g.setColor(COLOR_UI_BACKROUND);
		g.fill(UI.getBounds());
		drawPlayerStats(g);
	}

	private void drawUIButtons(Graphics2D g) {
		Button b;
		for (int i = 0;i<UI.getButtons().size();i++) {
			b = UI.getButtons().get(i);
			if (b.isHovered()&&!b.isPressed()) {
				g.setColor(COLOR_UI_BUTTON_HOVERED);
			}
			else {
				g.setColor(COLOR_UI_BUTTON);
			}
			g.fill3DRect(b.getBounds().x, b.getBounds().y, b.getBounds().width, b.getBounds().height, !b.isPressed());
			if (b.getText0()!=""||b.getText1()!=""||b.getText2()!="") {
				g.setColor(COLOR_UI_TEXT);
				g.setFont(b.getTextFont());
				g.drawString(b.getText0(), b.getBounds().x+2, b.getBounds().y-2+b.getBounds().height-b.getTextFont().getSize()*2);
				g.drawString(b.getText1(), b.getBounds().x+2, b.getBounds().y-2+b.getBounds().height-b.getTextFont().getSize());
				g.drawString(b.getText2(), b.getBounds().x+2, b.getBounds().y-2+b.getBounds().height);
			}
		}
	}

	private void drawUpgradeUIButtons(Graphics2D g) {
		if (UpgradeUI.isOpen()) {
			Button b;
			for (int i = 0;i<UpgradeUI.getButtons().size();i++) {
				b = UpgradeUI.getButtons().get(i);
				if (b.isHovered()&&!b.isPressed()) {
					g.setColor(COLOR_UI_BUTTON_HOVERED);
				}
				else {
					g.setColor(COLOR_UI_BUTTON);
				}
				g.fill3DRect(b.getBounds().x, b.getBounds().y, b.getBounds().width, b.getBounds().height, !b.isPressed());
//
//				TODO add icon or something to buttons
//
//				if (b.getText0()!=""||b.getText1()!=""||b.getText2()!="") {
//					g.setColor(COLOR_UI_TEXT);
//					g.setFont(b.getTextFont());
//					g.drawString(b.getText0(), b.getBounds().x+2, b.getBounds().y-2+b.getBounds().height-b.getTextFont().getSize()*2);
//					g.drawString(b.getText1(), b.getBounds().x+2, b.getBounds().y-2+b.getBounds().height-b.getTextFont().getSize());
//					g.drawString(b.getText2(), b.getBounds().x+2, b.getBounds().y-2+b.getBounds().height);
//				}
			}
		}
	}

	private void drawUpgradeUI(Graphics2D g) {
		//TODO make this
	}

	@Override
	public void paint(Graphics g0) {
		g = (Graphics2D) g0;
		super.paintComponent(g);
		drawGrid(g);
		drawTowers(g);
		drawEnemies(g);
		drawProjectiles(g);
		drawUI(g);
		drawUIButtons(g);
		drawUpgradeUI(g);
		drawUpgradeUIButtons(g);
		drawFloatingTower(g);
	}
}
