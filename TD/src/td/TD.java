package td;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;

/* stuff and things:
 * P = toggle PAUSE (or button)
 * SPACE = hold PAUSE
 * S = start next round (or button)
 * A = toggle automatic round start (or button)
 * left mouse = place tower/select tower/everything else
 * right mouse = cancel placement of tower
 * esc = deselect the selected tower/cancel placement of tower
 * delete = delete/sell selected tower (or button)
 *
 * cheats:
 * K = kill all enemies
 * J = delete all towers
 * L = loop toggle
 * O = toggle money doing things
 * button A = spawn 9 turrets in grid
 * button B = spawn 2 turrets near path
 * button C = spawn 4 turrets near path
 * up/down arrow = change health of first enemy
 *
 * use with minor caution: (probably broken anyway)
 * - = 1x speed
 * = = 2x speed
 */


interface Data {
	static final String ROUND_CONFIG = ""

//			+ "0:20x200,1,NORMAL:"
//			+ "1:30x150,1,NORMAL:"
//			+ "2:15x400,2,NORMAL:"
//			+ "3:0,6,NORMAL:"
//			+ "4:0,5,CAMO:"

//			+ "0:3x300,1,NORMAL;3x300,1,CAMO;3x300,1,METAL;3x300,1,REGEN;3x300,1,CAMOMETAL;3x300,1,CAMOREGEN;3x300,1,METALREGEN;3x300,1,CAMOMETALREGEN:"


//			proper
			+ "0:20x400,1,NORMAL:"
			+ "1:30x300,1,NORMAL:"
			+ "2:10x250,1,NORMAL;5x500,2,NORMAL;10x250,1,NORMAL:"
			+ "3:15x250,1,NORMAL;15x250,2,NORMAL;15x250,1,NORMAL:"
			+ "4:15x250,2,NORMAL;15x150,1,NORMAL;15x250,2,NORMAL:"
			+ "5:15x150,1,NORMAL;15x300,2,NORMAL;4x400,3,NORMAL:"
			+ "6:20x150,1,NORMAL;25x300,2,NORMAL;5x400,3,NORMAL:"
			+ "7:10x150,1,NORMAL;20x250,2,NORMAL;14x300,3,NORMAL:"
			+ "8:30x300,3,NORMAL:"
			+ "9:102x250,2,NORMAL:"
			+ "10:10x200,1,NORMAL;10x250,2,NORMAL;12x300,3,NORMAL;2x500,4,NORMAL:"
			+ "11:15x200,2,NORMAL;10x300,3,NORMAL;5x400,4,NORMAL:"
			+ "12:100x200,1,NORMAL;23x250,3,NORMAL;4x500,4,NORMAL:"
			+ "13:50x200,1,NORMAL;15x250,2,NORMAL;10x500,3,NORMAL;9x500,4,NORMAL:"
			+ "14:20x200,1,NORMAL;12x300,3,NORMAL;5x450,4,NORMAL;3x500,5,NORMAL:"
			+ "15:20x300,3,NORMAL;8x350,4,NORMAL;4x500,5,NORMAL:"
			+ "16:8x600,4,REGEN:"
			+ "17:80x200,3,NORMAL:"
			+ "18:10x300,3,NORMAL;4x400,4,NORMAL;5x400,4,REGEN;7x450,5,NORMAL:"
			+ "19:6x500,10,NORMAL:"
			+ "20:14x250,7,NORMAL:"
			+ "21:8x350,10,NORMAL:"
			+ "22:12x350,11,NORMAL:"
			+ "23:0,4,CAMO:"
			+ "24:31x500,4,REGEN:"
			+ "25:23x400,5,NORMAL;2x700,23,NORMAL:"
			+ "26:120x100,1,NORMAL;55x150,2,NORMAL;45x250,3,NORMAL;45x300,4,NORMAL:"
			+ "27:4x600,15,METAL:"
			+ "28:25x400,4,NORMAL;12x500,5,REGEN:"
			+ "29:9x600,17,METAL:"
			+ "30:8x400,23,NORMAL;2x400,23,REGEN:"
			+ "31:55x350,10,NORMAL;8x600,23,NORMAL:"
			+ "32:20x250,4,CAMO:"
			+ "33:140x250,4,NORMAL,5x600,25,NORMAL:"
			+ "34:35x250,5,NORMAL;25x300,11,NORMAL;5x700,35,NORMAL:"
			+ "35:81x200,6,NORMAL:"
			+ "36:40x250,11,NORMAL;7x300,11,CAMO;9x400,18,METAL;10x350,24,NORMAL:"
			+ "37:42x250,5,REGEN;17x300,10,NORMAL;14x350,17,METAL;10x350,24,NORMAL;4x650,35,NORMAL:"
			+ "38:20x200,11,NORMAL;20x350,19,METAL;20x350,24,NORMAL;18x650,37,NORMAL:"
			+ "39:10x650,40,NORMAL;3x800,104,NORMAL:"
			+ "40:60x120,11,NORMAL;60x200,23,NORMAL:"
			+ "41:6x500,50,NORMAL;6x500,50,REGEN:"
			+ "42:12x600,40,NORMAL;6x700,110,NORMAL:"
			+ "43:50x250,23,NORMAL:"
			+ "44:200x20,5,NORMAL;8x200,20,METAL;25x450,55,NORMAL:"
			+ "45:0,300,NORMAL:"
			+ "46:0,18,CAMO;1000,18,METAL;1000,18,CAMOMETAL:"
			+ "47:10x300,18,CAMOMETAL:"
//			+ "48::"
//			+ "49::"
//			+ "50::"
//			+ "51::"
//			+ ""
//			+ ""

	;

	static final double TOWER_TURRET_BASE_DAMAGE = 1,             TOWER_TURRET_BASE_COOLDOWN = 650;
	static final double TOWER_BOMB_BASE_DAMAGE = 1,               TOWER_BOMB_BASE_COOLDOWN = 750,            TOWER_BOMB_BASE_METAL_BONUS_DAMAGE = 5, TOWER_BOMB_BASE_EXPLOSION_RADIUS = 2.5;
	static final double TOWER_SUPER_TURRET_BASE_DAMAGE = 0.5,     TOWER_SUPER_TURRET_BASE_COOLDOWN = 150;
	static final double TOWER_NINJA_BASE_DAMAGE = 1,              TOWER_NINJA_BASE_COOLDOWN = 600;
	static final double TOWER_SNIPER_BASE_DAMAGE = 1,             TOWER_SNIPER_BASE_COOLDOWN = 1000;
	static final double TOWER_SHOTGUN_BASE_DAMAGE = 0.25,         TOWER_SHOTGUN_BASE_COOLDOWN = 600,         TOWER_SHOTGUN_BASE_BULLET_COUNT = 10,   TOWER_SHOTGUN_BASE_SPREAD = 20;
	static final double TOWER_SPRAYER_BASE_DAMAGE = 1,            TOWER_SPRAYER_BASE_COOLDOWN = 500,         TOWER_SPRAYER_BASE_PELLET_COUNT = 8;
	static final double TOWER_ICE_BASE_SLOWDOWN = 0.25,           TOWER_ICE_BASE_COOLDOWN = 800,             TOWER_ICE_BASE_HIT_COUNT = 2,           TOWER_ICE_BASE_DURATION = 3000;

	static final double TOWER_BULLET_NORMAL_INITIAL_VELOCITY = 3, TOWER_BULLET_NORMAL_ACCELERATION = 1;

	static final double TOWER_TURRET_BASE_RANGE = 20,             TOWER_TURRET_SIZE = 1.5;
	static final double TOWER_BOMB_BASE_RANGE = 15,               TOWER_BOMB_SIZE = 1.5;
	static final double TOWER_SUPER_TURRET_BASE_RANGE = 30,       TOWER_SUPER_TURRET_SIZE = 1.5;
	static final double TOWER_NINJA_BASE_RANGE = 16,              TOWER_NINJA_SIZE = 1.5;
	static final double TOWER_SNIPER_BASE_RANGE = 2,              TOWER_SNIPER_SIZE = 1.5;
	static final double TOWER_SHOTGUN_BASE_RANGE = 8,             TOWER_SHOTGUN_SIZE = 1.5;
	static final double TOWER_SPRAYER_BASE_RANGE = 5,             TOWER_SPRAYER_SIZE = 1.25;
	static final double TOWER_ICE_BASE_RANGE = 5,                 TOWER_ICE_SIZE = 1.25;

	static final double TOWER_TURRET_VALUE = 250;
	static final double TOWER_BOMB_VALUE = 250;
	static final double TOWER_SUPER_TURRET_VALUE = 2000;
	static final double TOWER_NINJA_VALUE = 500;
	static final double TOWER_SNIPER_VALUE = 350;
	static final double TOWER_SHOTGUN_VALUE = 750;
	static final double TOWER_SPRAYER_VALUE = 300;
	static final double TOWER_ICE_VALUE = 300;

	static final double STARTING_MONEY = 10000/*750*/, STARTING_LIVES = 100;
	static final double ROUND_MONEY_REWARD = 100;
	static final double TOWER_SELL_RATE = 0.8d;

	static final int ENEMY_SIZE = TD.getScale(), ENEMY_HITBOX_SIZE = TD.getScale()*3/2;
	static final int ENEMY_REGEN_RATE = 1000;//millis

	static final int UPGRADE_UI_SIZE = TD.getScale(), UPGRADE_UI_PADDING = TD.getScale()/2;

	static final Font FONT_UI_TEXT = new Font("Helvetica", Font.BOLD, TD.getScale()*5/4);
	static final Font FONT_UI_HEALTH = new Font("Helvetica", Font.BOLD, TD.getScale()*5/4);

	static final Color COLOR_UI_TEXT = new Color(255,255,255,255);
	static final Color COLOR_UI_HEALTH = new Color(255,0,0,255);

	static final Color COLOR_UI_BACKROUND = new Color(80,80,80,255);
	static final Color COLOR_UI_BUTTON = new Color(50,50,255,255);
	static final Color COLOR_UI_BUTTON_HOVERED = new Color(100,100,255,255);

	static final Color COLOR_TOWER_RANGE = new Color(0,0,0,70);
	static final Color COLOR_TOWER_RANGE_INVALID = new Color(255,0,0,70);

	static final Color COLOR_NORMAL_ENEMY = new Color(255,0,0,255);
	static final Color COLOR_CAMO_ENEMY = new Color(255,255,0,255);
	static final Color COLOR_METAL_ENEMY = new Color(150,150,150,255);
	static final Color COLOR_REGEN_ENEMY = new Color(100,0,0,255);
	static final Color COLOR_NORMAL_ENEMY_BORDER = new Color(100,100,100,255);
	static final Color COLOR_REGEN_ENEMY_BORDER = new Color(150,0,0,255);
	static final Color COLOR_ENEMY_ICED = new Color(147,192,255,180);

	static final Color COLOR_0 = new Color(200,200,200,255);//light gray
	static final Color COLOR_1 = new Color(0,0,0,255);//black
	static final Color COLOR_PATH = new Color(0,0,0,50);//transparent black

	static final Color COLOR_ERR = Color.MAGENTA;
}

public class TD implements Data {
	static int SCREEN_HEIGHT, SCREEN_WIDTH, WINDOW_HEIGHT, WINDOW_WIDTH, UI_HEIGHT, UI_WIDTH;
	private static int scale;
	static final int UI_W = 7;//dont change
	static {
		LevelLayout.initLayout(LevelLayout.level1);//58x33
		GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = screen.getDisplayMode().getWidth(), height = screen.getDisplayMode().getHeight();
		int screenSize = (LevelLayout.layout.length>LevelLayout.layout[0].length+UI_W)?height:width;
		int layoutSize = Math.max(LevelLayout.layout.length, LevelLayout.layout[0].length+UI_W);
		scale = (int)((screenSize*15d/16d)/layoutSize);
		UI_WIDTH = scale*UI_W;
		UI_HEIGHT = scale*LevelLayout.layout.length;
		SCREEN_WIDTH = scale*LevelLayout.layout[0].length+UI_WIDTH;
		SCREEN_HEIGHT = scale*LevelLayout.layout.length;
		WINDOW_WIDTH = SCREEN_WIDTH+3+3;
		WINDOW_HEIGHT = SCREEN_HEIGHT+25+3;
		System.out.println("WxH: "+LevelLayout.layout[0].length+"x"+LevelLayout.layout.length+" WxH: "+width+"x"+height+" Screen Size:"+screenSize+" Layout Size:"+layoutSize+" Scale:"+scale);
	}

	static final int UPS = 60;

	static final int ENEMY_SIZE = (int)(scale*2d/3d);
	static final double ENEMY_SPEED = 0.45d, ENEMY_BASE_SPEED = ENEMY_SPEED*scale/20d*120d/UPS;//0.65d;
	static final double PROJECTILE_SPEED = 1.00d, PROJECTILE_BASE_SPEED = PROJECTILE_SPEED*scale/20d*120d/UPS;

	static final boolean SPAM_CONSOLE = true, GRAPHICS = true;
	static boolean RUNNING = true, LOOP_ENEMIES = false, AUTO_ROUND = false, FREE = false, PAUSED;

	static Level level;
	private static UpdateLoop updateLoop;

	static int getScale() {
		return scale;
	}

	public static void main(String[] args) {
		level = new Level(LevelLayout.layout);
		RoundManager.init();
		updateLoop = new UpdateLoop();
		Window.init();
		UI.init();
		Thread update = new Thread(updateLoop, "TD Update");
		update.start();

	}

	static void toggleAutoRound() {
		AUTO_ROUND = (AUTO_ROUND)?false:true;
		System.out.println("Automatic rounds set to: "+AUTO_ROUND);
	}

	static void toggleFree() {
		FREE = (FREE)?false:true;
		System.out.println("Free set to: "+FREE);
	}

	static void toggleLoop() {
		LOOP_ENEMIES = (LOOP_ENEMIES)?false:true;
		System.out.println("looping set to: "+LOOP_ENEMIES);
	}

	static void togglePaused() {
		PAUSED = (PAUSED)?false:true;
		System.out.println((PAUSED)?"PAUSED":"UNPAUSED");
	}

	static void togglePaused(boolean state) {
		PAUSED = state;
		System.out.println((PAUSED)?"PAUSED":"UNPAUSED");
	}
}

class Button implements Data {
	protected Rectangle bounds;
	protected boolean hovered, pressed;
	protected String text0 = "", text1 = "", text2 = "";

	protected Font textFont = null;

	Button() {}

	Button(int i, int j) {
		bounds = new Rectangle(TD.level.l[0].length*TD.getScale()+(j*TD.getScale()*3)+TD.getScale(), (i*TD.getScale()*3)+TD.getScale(), TD.getScale()*2, TD.getScale()*2);
	}

	static final int DELETE_BUTTON = 17;
	static void buttonPressed(int function) {
		switch (function) {
			case 0:
				System.out.println("Create Turret");
				Cursor.setTowerToPlace(new TowerTurret(0, 0, false));
				break;
			case 1:
				System.out.println("Create Super Turret");
				Cursor.setTowerToPlace(new TowerSuperTurret(0, 0, false));
				break;
			case 2:
				System.out.println("Create Sniper");
				Cursor.setTowerToPlace(new TowerSniper(0, 0, false));
				break;
			case 3:
				System.out.println("Create Sprayer");
				Cursor.setTowerToPlace(new TowerSprayer(0, 0, false));
				break;
			case 4:
				System.out.println("Create Shotgun");
				Cursor.setTowerToPlace(new TowerShotgun(0, 0, false));
				break;
			case 5:
				System.out.println("Create Ninja");
				Cursor.setTowerToPlace(new TowerNinja(0, 0, false));
				break;
			case 6:
				System.out.println("Create Ice");
				Cursor.setTowerToPlace(new TowerIce(0, 0, false));
				break;
			case 7:
				System.out.println("Create Bomb");
				Cursor.setTowerToPlace(new TowerBomb(0, 0, false));
				break;
			case 9:
				System.out.println();
				TowerManager.towers.add(new TowerTurret(2*TD.getScale(), 2*TD.getScale(), true));
				TowerManager.towers.add(new TowerTurret(55*TD.getScale(), 2*TD.getScale(), true));
				TowerManager.towers.add(new TowerTurret(2*TD.getScale(), 29*TD.getScale(), true));
				TowerManager.towers.add(new TowerTurret(55*TD.getScale(), 29*TD.getScale(), true));
				TowerManager.towers.add(new TowerTurret(27*TD.getScale(), 2*TD.getScale(), true));
				TowerManager.towers.add(new TowerTurret(2*TD.getScale(), 14*TD.getScale(), true));
				TowerManager.towers.add(new TowerTurret(27*TD.getScale(), 29*TD.getScale(), true));
				TowerManager.towers.add(new TowerTurret(55*TD.getScale(), 14*TD.getScale(), true));
				TowerManager.towers.add(new TowerTurret(27*TD.getScale(), 14*TD.getScale(), true));
				break;
			case 10:
				System.out.println();
				TowerManager.towers.add(new TowerTurret(8*TD.getScale(), 9*TD.getScale(), true));
				TowerManager.towers.add(new TowerTurret(8*TD.getScale(), 19*TD.getScale(), true));
				break;
			case 11:
				System.out.println();
				TowerManager.towers.add(new TowerTurret(10*TD.getScale(), 10*TD.getScale(), true));
				TowerManager.towers.add(new TowerTurret(6*TD.getScale(), 10*TD.getScale(), true));
				TowerManager.towers.add(new TowerTurret(10*TD.getScale(), 18*TD.getScale(), true));
				TowerManager.towers.add(new TowerTurret(6*TD.getScale(), 18*TD.getScale(), true));
				break;
			case 14:
				TD.togglePaused();
				break;
			case 15:
				TD.toggleAutoRound();
				break;
			case 16:
				System.out.println("Starting next round");
				if (RoundManager.stillHasRounds()) {
					RoundManager.setStartRound(true);
				}
				else {
					System.out.println("\tOut of rounds");
				}
				break;
			case DELETE_BUTTON:
				Cursor.deleteSelectedTower();
				break;
			default:
				System.out.println("no effect");
				break;
		}
	}

	void buttonText(int button) {
		switch (button) {
			case 0:
				text0 = (int)TOWER_TURRET_VALUE+"";
				text2 = "Turret";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()/2);
				break;
			case 1:
				text0 = (int)TOWER_SUPER_TURRET_VALUE+"";
				text1 = "Super";
				text2 = "Turret";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()/2);
				break;
			case 2:
				text0 = (int)TOWER_SNIPER_VALUE+"";
				text2 = "Sniper";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()/2);
				break;
			case 3:
				text0 = (int)TOWER_SPRAYER_VALUE+"";
				text2 = "Sprayer";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()/2);
				break;
			case 4:
				text0 = (int)TOWER_SHOTGUN_VALUE+"";
				text2 = "Shotgun";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()*7/16);
				break;
			case 5:
				text0 = (int)TOWER_NINJA_VALUE+"";
				text2 = "Ninja";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()/2);
				break;
			case 6:
				text0 = (int)TOWER_ICE_VALUE+"";
				text2 = "Ice";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()/2);
				break;
			case 7:
				text0 = (int)TOWER_BOMB_VALUE+"";
				text2 = "Bomb";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()/2);
				break;
			case 9:
				text0 = "    A";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()/2);
				break;
			case 10:
				text0 = "    B";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()/2);
				break;
			case 11:
				text0 = "    C";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()/2);
				break;
			case 14:
				text2 = "PAUSE";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()/2);
				break;
			case 15:
				text1 = "AUTO";
				text2 = "ROUND";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()/2);
				break;
			case 16:
				text0 = "Start";
				text1 = "Next";
				text2 = "Round";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()/2);
				break;
			case DELETE_BUTTON:
				text1 = "Sell";
				text2 = "Tower";
				textFont = new Font("Helvetica", Font.BOLD, TD.getScale()*5/8);
				break;
			default:
				break;
		}
	}

	Rectangle getBounds() {
		return bounds;
	}

	String getText0() {
		return text0;
	}

	String getText1() {
		return text1;
	}

	String getText2() {
		return text2;
	}

	Font getTextFont() {
		return textFont;
	}

	void init() {
		buttonText(UI.getButtons().indexOf(this));
		System.out.println(UI.getButtons().indexOf(this)+": "+text0+", "+text1+", "+text2);
	}

	boolean isHovered() {
		return hovered;
	}

	boolean isPressed() {
		return pressed;
	}

	void press(boolean down) {
		if (down) {
			pressed = true;
			System.out.print("button "+UI.getButtons().indexOf(this)+" pressed:\n\t");
			buttonPressed(UI.getButtons().indexOf(this));
		}
		else {
			pressed = false;
		}
	}

	void tick() {
		if (bounds.contains(Cursor.getX(), Cursor.getY())) {
			hovered = true;
		}
		else {
			hovered = false;
		}
	}

}

class ButtonUpgrade extends Button implements Data {
	ButtonUpgrade(int x, int y, int pos) {
		super();
		bounds = new Rectangle(x+(pos*UPGRADE_UI_SIZE*3/2)+UPGRADE_UI_PADDING, y+UPGRADE_UI_PADDING, UPGRADE_UI_SIZE, UPGRADE_UI_SIZE);
	}

	@Override
	void press(boolean down) {
		if (down) {//if pressed
			pressed = true;
			System.out.println("Upgrade "+UpgradeUI.getButtons().indexOf(this)+" pressed");
			UpgradeUI.pressed(UpgradeUI.getButtons().indexOf(this));//press this buttons index of the upgradeui buttons
		}
		else {
			pressed = false;
		}
		UpgradeUI.setRefresh();
	}
}

class Enemy implements Comparable<Enemy>, Data {
	private int x, y, regenTimer, icedTimer;
	private double speed, iceSpeedModifier = 1, pathPos, health, initHealth;
	private boolean visible, destroy, iced;
	private EnemyType type;
	private Rectangle2D hitbox;

	Enemy(double health, EnemyType type, boolean visible) {
		this.initHealth = health;
		this.health = health;
		x = TD.level.start.y;
		y = TD.level.start.x;
		this.type = type;
		updateSpeed();
		this.visible = visible;
	}

	boolean canDamage(boolean metal) {
		if (isMetal()&&!metal) return false;
		return true;
	}

	boolean canSee(boolean camo) {
		if (isCamo()&&!camo) return false;
		return true;
	}

	@Override
	public int compareTo(Enemy e) {
		if (this.pathPos>e.pathPos) return -1;
		if (this.pathPos<e.pathPos) return 1;
		return 0;
	}

	void markDestroy() {
		if (!destroy) {
			destroy = true;
			visible = false;
		}
	}

	public double getHealth() {
		return health;
	}

	Rectangle2D getHitbox() {
		return hitbox;
	}

	double getInitHealth() {
		return initHealth;
	}

	double getPathPos() {
		return pathPos;
	}

	double getRegenTimer() {
		return regenTimer;
	}

	int getSides() {
		int sides = (int)health+2;
		if (sides<3) {
			sides = 3;
		}
		return sides;
	}

	double getSpeed() {
		return speed;
	}

	EnemyType getType() {
		return type;
	}

	int getX() {
		return y;
	}

	int getY() {
		return x;
	}

	void hit(double damage, boolean givesMoney) {
		double initHealth = health;
		health-=damage;
		if (health<=0) {//check death
			markDestroy();//kill
			if (givesMoney) {
				Player.changeMoney(initHealth-health);//partial reward
			}
		}
		else if (givesMoney) {
			Player.changeMoney(damage);//full reward
		}
		if (type.regen) {
			regenTimer = ENEMY_REGEN_RATE;
		}
		updateSpeed();
	}

	void ice(double modifier, int duration) {
		setIced(true);
		iceSpeedModifier = modifier;
		icedTimer = duration;
	}

	boolean isCamo() {
		return type.camo;
	}

	boolean isDestroy() {
		return destroy;
	}

	boolean isIced() {
		return iced;
	}

	boolean isMetal() {
		return type.metal;
	}

	boolean isRegen() {
		return type.regen;
	}

	boolean isVisible() {
		return visible;
	}

	void move() {
		pathPos+=speed;
		if (pathPos<0||pathPos>=TD.level.path.size()) {//check out of bounds; move to beginning or delete
			if (TD.LOOP_ENEMIES) {
				pathPos = 0;
			}
			else {
				markDestroy();
				double hurt = health;
				if (health>(int)health) {
					hurt = (int)health+1;
				}
				Player.changeLives(-(int)hurt);//remove playerhealth
			}

		}
		else {//set position based on pathpos
			y = TD.level.path.get((int) pathPos).x;
			x = TD.level.path.get((int) pathPos).y;
		}
	}

	void regen() {
		health+=speed;//regens by speed
		if (health>initHealth) {
			health = initHealth;//if health is too high, reset to max
		}
	}

	void setIced(boolean iced) {
		this.iced = iced;
	}

	void setVisible(boolean visible) {
		this.visible = visible;
	}

	boolean tick() {
		if (iced) {
			icedTimer-=1000/TD.UPS;
			if (icedTimer<=0) {
				unIce();
			}
		}
		updateSpeed();
		move();
		hitbox = Util.getRect(x-ENEMY_HITBOX_SIZE/2+ENEMY_SIZE/2, y-ENEMY_HITBOX_SIZE/2+ENEMY_SIZE/2, ENEMY_HITBOX_SIZE);
		if (type.regen) {
			if (regenTimer>0) {
				regenTimer-=1000/TD.UPS;
				if (health<initHealth&&regenTimer<=0) {
					regen();
					if (health<initHealth) {
						regenTimer = ENEMY_REGEN_RATE;
					}
					else {
						regenTimer = 0;
					}
				}
			}
		}
		if (health<=0) {
			markDestroy();
		}
		return destroy;
	}

	@Override
	public String toString() {
		return initHealth+","+type;
	}

	void unIce() {
		setIced(false);
		iceSpeedModifier = 1;
		icedTimer = 0;
	}

	double updateSpeed() {
		double health = this.health;
		if (health<1) {
			health = 1;//fractional health is treated as 1
		}
		speed = (Math.log(health)*1.2d+1d)*iceSpeedModifier*TD.ENEMY_BASE_SPEED;
		return speed;
	}
}

class EnemyManager {
	static ArrayList<Enemy> enemies = new ArrayList<>();
	private static EnemySorter sorter = new EnemySorter();

	static boolean noEnemies() {
		return enemies.size()==0;
	}

	private static void sortEnemies() {
		try {
			Collections.sort(enemies,sorter);
		}
		catch (Exception e) {}
	}

	static void spawnEnemy(Enemy enemy) {
		enemy.setVisible(true);
		enemies.add(enemy);
	}

	static void tick() {
		sortEnemies();
	}
}

class EnemySorter implements Comparator<Enemy> {
	@Override
	public int compare(Enemy e1, Enemy e2) {
		return e1.compareTo(e2);
	}
}

enum EnemyType {
	NORMAL(false, false, false),
	CAMO(true, false, false),
	METAL(false, true, false),
	REGEN(false, false, true),
	CAMOMETAL(true, true, false),
	CAMOREGEN(true, false, true),
	METALREGEN(false, true, true),
	CAMOMETALREGEN(true, true, true);

	boolean camo, metal, regen;

	EnemyType(boolean camo, boolean metal, boolean regen) {
		this.camo = camo;
		this.metal = metal;
		this.regen = regen;
	}
}

class Input implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener {
	@Override
	public void keyPressed(KeyEvent e) {
		if (!EnemyManager.enemies.isEmpty()&&e.getKeyCode()==KeyEvent.VK_UP) {
			EnemyManager.enemies.get(0).hit(-1, false);
		}
		if (!EnemyManager.enemies.isEmpty()&&e.getKeyCode()==KeyEvent.VK_DOWN&&EnemyManager.enemies.get(0).getHealth()>1) {
			EnemyManager.enemies.get(0).hit(1, false);
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) if (!TD.PAUSED) {//pause
			TD.togglePaused(true);
		}
		if (e.getKeyCode()==KeyEvent.VK_P) {//pause
			TD.togglePaused();
		}
		if (e.getKeyCode()==KeyEvent.VK_L) {
			TD.toggleLoop();
		}
		if (e.getKeyCode()==KeyEvent.VK_A) {
			TD.toggleAutoRound();
		}
		if (e.getKeyCode()==KeyEvent.VK_O) {
			TD.toggleFree();
		}
		if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			Cursor.deselectSelectedTower();
			Cursor.deselectTowerToPlace();
		}
		if (e.getKeyCode()==KeyEvent.VK_MINUS) {
			System.out.println("x1");
			UpdateLoop.setSpeedFactor(1);
		}
		if (e.getKeyCode()==KeyEvent.VK_EQUALS) {
			System.out.println("x2");
			UpdateLoop.setSpeedFactor(2);
		}
		if (e.getKeyCode()==KeyEvent.VK_1) {
			EnemyManager.enemies.add(new Enemy(2,EnemyType.NORMAL, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_2) {
			EnemyManager.enemies.add(new Enemy(2,EnemyType.CAMO, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_3) {
			EnemyManager.enemies.add(new Enemy(2,EnemyType.METAL, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_4) {
			EnemyManager.enemies.add(new Enemy(2,EnemyType.REGEN, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_5) {
			EnemyManager.enemies.add(new Enemy(2,EnemyType.CAMOMETAL, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_6) {
			EnemyManager.enemies.add(new Enemy(2,EnemyType.CAMOREGEN, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_7) {
			EnemyManager.enemies.add(new Enemy(2,EnemyType.METALREGEN, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_8) {
			EnemyManager.enemies.add(new Enemy(2,EnemyType.CAMOMETALREGEN, true));
		}
		if (e.getKeyCode()==KeyEvent.VK_J) {
			for (Tower tower:TowerManager.towers) {
				tower.destroy(true);
			}
			System.out.println("Deleted all towers");
		}
		if (e.getKeyCode()==KeyEvent.VK_K) {
			for (Enemy enemy:EnemyManager.enemies) {
				enemy.markDestroy();
			}
			System.out.println("Deleted all enemies");
		}
		if (e.getKeyCode()==KeyEvent.VK_S) {
			System.out.print("S key pressed:\n\t");
			Button.buttonPressed(16);
		}
		if (e.getKeyCode()==KeyEvent.VK_DELETE) {
			System.out.print("Delete key pressed:\n\t");
			Cursor.deleteSelectedTower();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_SPACE) if (TD.PAUSED) {
			TD.togglePaused(false);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {Cursor.updateMouse(e);}
	@Override
	public void mousePressed(MouseEvent e) {Cursor.updateMouse(e);Cursor.click(e, true);}
	@Override
	public void mouseReleased(MouseEvent e) {Cursor.updateMouse(e);Cursor.click(e, false);}
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {}
}

class Level {
	Tile[][] l;
	ArrayList<Point> path = new ArrayList<>();
	Point start, end;

	Level(int[][] layout) {
		path = Path.getPath(layout);
		l = new Tile[layout.length][layout[0].length];
		for (int y = 0;y<layout.length;y++) {
			for (int x = 0;x<layout[0].length;x++) {
				l[y][x] = new Tile(new Point(y*TD.getScale(),x*TD.getScale()), layout[y][x], Renderer.getTileColor(layout[y][x]));
			}
		}
		start = new Point(path.get(0).x, path.get(0).y);
		end = new Point(path.get(path.size()-1).x, path.get(path.size()-1).y);
	}

	Tile get(int y, int x) {
		return l[y][x];
	}
}

class LevelLayout {
	static int[][] layout;

	static final int[][] level0 = {
			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,7,7,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{8,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,7,7,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,7,7,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,0,0,0,0},
	};

	static final int[][] level1 = {
			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,7,7,0,0,0,0,0,0,0,7,7,7,7,7,7,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,7,7,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{8,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,0,0,0,0},
	};

	static void initLayout(int[][] layout) {
		LevelLayout.layout = layout;
	}
}

class Path {
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
			for (int j = 0;j<TD.getScale();j++) {
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
		for (int j = 0;j<TD.getScale()*2;j++) {
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
		start.y*=TD.getScale();
		start.x*=TD.getScale();
		end.y*=TD.getScale();
		end.x*=TD.getScale();
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
		rawPath.add(new Point(y*TD.getScale(),x*TD.getScale()));
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

class Cursor {
	private static int x, y;
	private static Tower selectedTower, towerToPlace;

	static void click(MouseEvent e, boolean down) {
		boolean deselectSelectedTower = false;
		Button b;
		if (down) {//if click was down (not up)
			if (!UI.getBounds().contains(x, y)&&towerToPlace==null) {//check for click in main window
				Tower t;
				for (int i = 0;i<TowerManager.towers.size();i++) {
					t = TowerManager.towers.get(i);
					if (t == selectedTower) {//if clicked on the selected tower
						deselectSelectedTower = true;//deselect the tower
						t.updateUpgrades();//update the tower
					}
					else if (t.bounds.contains(x, y)) {//if clicked on a different tower
						selectedTower(t);//select the tower
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
				if (towerToPlace!=null&&towerToPlace.isPlaceable()&&Player.canAfford(towerToPlace.value)) {//if can place it
					towerToPlace.place();//place it
					deselectTowerToPlace();//stop holding the tower
				}
			}
		}
		if (UpgradeUI.isOpen()) {//if needs to update the upgrade ui
			for (int i = 0;i<UpgradeUI.getButtons().size();i++) {
				b = UpgradeUI.getButtons().get(i);
				if (b.getBounds().contains(x, y)&&e.getButton()==MouseEvent.BUTTON1) {//if cursor is on button and was leftclick
					b.press(down);//press the button if click was down, depress if up
					deselectSelectedTower = false;//dont deselect if clicked an upgrade
				}
				else {
					b.press(false);//depress the button
				}
			}
		}
		if (towerToPlace==null) {//update buttons only if not holding anything
			for (int i = 0;i<UI.getButtons().size();i++) {
				b = UI.getButtons().get(i);
				if (b.getBounds().contains(x, y)&&e.getButton()==MouseEvent.BUTTON1) {//if cursor is on button and was leftclick
					deselectSelectedTower = true;//deselect if pressed a non upgrade button
					b.press(down);//press the button if click was down, depress if up
				}
				else {
					b.press(false);//depress the button
				}
			}
		}
		if (deselectSelectedTower) {//if was told to deslect
			deselectSelectedTower();//deselect
		}
	}

	static void deselectSelectedTower() {
		System.out.println("-Deselected "+selectedTower);
		selectedTower(null);//deselect
	}

	static void deselectTowerToPlace() {
		if (towerToPlace!=null) {
			setTowerToPlace(null);//deselect
		}
	}

	static void deleteSelectedTower() {
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

	static void selectedTower(Tower toSelect) {
		selectedTower = toSelect;
		if (selectedTower!=null) {//if didnt set to null (deselect)
			System.out.println("+Selected "+selectedTower);
		}
		UpgradeUI.setRefresh();//refresh ui (because selected tower changed)
	}

	static void setTowerToPlace(Tower towerToPlace) {
		Cursor.towerToPlace = towerToPlace;
	}

	public static Tower getTowerToPlace() {
		return towerToPlace;
	}

	static void updateMouse(MouseEvent e) {
		x = e.getX()-3;//offset 3
		y = e.getY()-25;//offset 25
		for (int i = 0;i<UI.getButtons().size();i++) {//ui buttons
			UI.getButtons().get(i).tick();//tick the button
		}
		for (int i = 0;i<UpgradeUI.getButtons().size();i++) {//upgrade ui buttons
			UpgradeUI.getButtons().get(i).tick();//tick the button
		}
	}

	static int getX() {
		return x;
	}

	static int getY() {
		return y;
	}
}

class Player implements Data {
	private static double playerMoney = STARTING_MONEY, playerHealth = STARTING_LIVES;

	static boolean canAfford(double amount) {
		return TD.FREE||amount<=playerMoney;
	}

	static void changeMoney(double dMoney) {
		if (dMoney<0) {
			System.out.println("Balance: "+playerMoney+" --> "+(playerMoney+dMoney)+"\tChanged by: "+dMoney);
		}
		playerMoney+=dMoney;
	}

	static double getPlayerHealth() {
		return playerHealth;
	}

	static double getPlayerMoney() {
		return playerMoney;
	}

	static void changeLives(int dLives) {
		playerHealth+=dLives;
	}

}

class ProjectileManager {
	static ArrayList<Projectile> projectiles = new ArrayList<>();

	void tick() {
		//maybe
	}
}

abstract class Projectile implements Data{
	protected double x, y, dX, dY, ddX, ddY, life;
	protected int size;
	protected boolean visible, damages, destroy, canDamageMetal;
	protected Tower creator;
	protected Line2D hitLine;

	Projectile(Tower c, int x, int y) {
		this.creator = c;
		this.x = x;
		this.y = y;
	}

	Projectile(Tower c, int x, int y, double damage) {
		this.creator = c;
		this.x = x;
		this.y = y;
		damages = true;
		this.life = damage;
	}

	protected void attemptHit() {
		Enemy e;
		hitLine = new Line2D.Double((int)x, (int)y, (int)x-dX, (int)y-dY);
		for (int i = 0;i<EnemyManager.enemies.size();i++) {
			e = EnemyManager.enemies.get(i);
			if (life>0&&!e.isDestroy()&&e.canDamage(canDamageMetal)) {
				if (hitLine.intersects(e.getHitbox())) {
					damage(e);
				}
			}
		}
	}

	boolean tick() {
		move();
		tickSpecial();
		if (x<-100||y<-100||x>(TD.level.l[0].length*TD.getScale())+100||y>(TD.level.l.length*TD.getScale())+100||life<=0) {//dead or off screen
			markDestroy();
		}
		if (damages) {
			attemptHit();
		}
		return destroy;
	}

	void tickSpecial() {}

	protected void move() {
		dX+=ddX*TD.PROJECTILE_BASE_SPEED;
		dY+=ddY*TD.PROJECTILE_BASE_SPEED;
		x+=dX*TD.PROJECTILE_BASE_SPEED;
		y-=dY*TD.PROJECTILE_BASE_SPEED;
	}

	protected void damage(Enemy e) {
		double damage = life;
		if (e.isMetal()&&creator instanceof TowerBomb) {
			damage*=((TowerBomb) creator).getMetalBonusDamage();
		}
		if (e.getHealth()>0&&damage>0) {
			double temp = damage;
			if (e.getHealth()>=damage) {
				creator.popCount+=damage;
			}
			else {
				creator.popCount+=e.getHealth();
				Player.changeMoney(e.getHealth());
			}
			life-=e.getHealth();
			e.hit(temp, true);
		}
	}

	protected void markDestroy() {
		destroy = true;
	}

	double getDdX() {
		return ddX;
	}

	double getDdY() {
		return ddY;
	}

	double getdX() {
		return dX;
	}

	double getdY() {
		return dY;
	}

	double getLife() {
		return life;
	}

	double getX() {
		return x;
	}

	double getY() {
		return y;
	}
}

class ProjectileBomb extends Projectile {
	private double explosionRadius;
	private ArrayList<Enemy> inRange;
	ProjectileBomb(Tower creator, int x, int y, double damage, double explosionRadius, boolean canDamageMetal, double angle, double velocity, double acceleration) {
		super(creator, x, y, damage);
		this.explosionRadius = explosionRadius;
		this.canDamageMetal = canDamageMetal;
		inRange = new ArrayList<>();
		ddX = Util.getXComp(angle, acceleration);
		ddY = Util.getYComp(angle, acceleration);
		dX = Util.getXComp(angle, velocity);
		dY = Util.getYComp(angle, velocity);
	}

	@Override
	protected void attemptHit() {
		Enemy e;
		hitLine = new Line2D.Double((int)x, (int)y, (int)x-dX, (int)y-dY);
		for (int i = 0;i<EnemyManager.enemies.size();i++) {
			e = EnemyManager.enemies.get(i);
			if (life>0&&!e.isDestroy()&&e.canDamage(canDamageMetal)) {
				if (hitLine.intersects(e.getHitbox())) {
					hit(e.getY(), e.getX());
					life = 0;
				}
			}
		}
	}

	private void hit(int x, int y) {
		inRange.clear();
		for (int i = 0;i<EnemyManager.enemies.size();i++) {
			if (Util.distance(this.y, this.x, EnemyManager.enemies.get(i).getX()+ENEMY_SIZE/2, EnemyManager.enemies.get(i).getY()+ENEMY_SIZE/2)<=explosionRadius) {
				inRange.add(EnemyManager.enemies.get(i));
			}
		}
		int i = 0;
		while (life>0&&i<inRange.size()) {
			if (!inRange.get(i).isDestroy()&&inRange.get(i).canDamage(canDamageMetal)) {
				damage(inRange.get(i));
			}
			i++;
		}
	}
}

class ProjectileBullet extends Projectile {
	ProjectileBullet(Tower creator, int x, int y, double damage, boolean canDamageMetal, double angle, double velocity, double acceleration) {
		super(creator, x, y, damage);
		this.canDamageMetal = canDamageMetal;
		ddX = Util.getXComp(angle, acceleration);
		ddY = Util.getYComp(angle, acceleration);
		dX = Util.getXComp(angle, velocity);
		dY = Util.getYComp(angle, velocity);
	}
}

class ProjectilePellet extends Projectile {
	private double traveled, range;

	ProjectilePellet(Tower creator, int x, int y, double damage, boolean canDamageMetal, double angle, double range, double velocity, double acceleration) {
		super(creator, x, y, damage);
		this.range = range/2;
		this.canDamageMetal = canDamageMetal;
		ddX = Util.getXComp(angle, acceleration);
		ddY = Util.getYComp(angle, acceleration);
		dX = Util.getXComp(angle, velocity);
		dY = Util.getYComp(angle, velocity);
	}

	@Override
	protected void move() {
		dX+=ddX*TD.PROJECTILE_BASE_SPEED;
		dY+=ddY*TD.PROJECTILE_BASE_SPEED;
		traveled+=Util.distance((int)x, (int)y, (int)(x+dX), (int)(y+dY));
		x+=dX*TD.PROJECTILE_BASE_SPEED;
		y-=dY*TD.PROJECTILE_BASE_SPEED;
	}

	@Override
	void tickSpecial() {
		if (traveled>=range) {
			markDestroy();
		}
	}
}
@SuppressWarnings("serial")
class Renderer extends JPanel implements Data {
	static Color getTileColor(int c) {
		switch (c) {
			case 0:
				return COLOR_0;
			case 1:
				return COLOR_1;
			case 7:
				return COLOR_0;
		}
		return COLOR_ERR;
	}

	private Graphics2D g;

	private void drawEnemies(Graphics2D g) {
		Enemy e;
		for (int i = 0;i<EnemyManager.enemies.size();i++) {
			e = EnemyManager.enemies.get(i);
			if (e.isVisible()) {
				drawEnemy(g, e);
//				g.setStroke(new BasicStroke(1));
//				g.setColor(Color.magenta);
//				if (e.hitbox!=null) g.draw(e.hitbox);

				g.setFont(new Font("Helvetica", Font.PLAIN, TD.getScale()/2));
				g.setColor(Color.BLACK);
				if (TD.PAUSED) {
					g.drawString((EnemyManager.enemies.indexOf(e)==0)+" "+e.getHealth()+"-"+e.getSides()+"-"+(int)e.getSpeed(), e.getY(), e.getX()-TD.getScale());
				}
			}
		}

	}

	private void drawEnemy(Graphics2D g, Enemy e) {
		Polygon poly = Util.getPoly(e.getY()+ENEMY_SIZE/2, e.getX()+ENEMY_SIZE/2, e.getSides(), TD.ENEMY_SIZE);
		Polygon innerPoly = Util.getPoly(e.getY()+ENEMY_SIZE/2, e.getX()+ENEMY_SIZE/2, e.getSides(), TD.ENEMY_SIZE/2);
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
		for (int i = 0;i<TD.level.l.length;i++) {
			for (int j = 0;j<TD.level.l[0].length;j++) {
				g.setColor(TD.level.get(i, j).getColor());
				g.fill(TD.level.get(i, j).getBounds());
				if (TD.level.path.contains(new Point(i*TD.getScale(), j*TD.getScale()))) {
					g.setColor(COLOR_PATH);
					g.fill(TD.level.get(i, j).getBounds());
				}
			}
		}
	}

	private void drawPlayerStats(Graphics2D g) {
		g.setColor(COLOR_UI_HEALTH);
		g.setFont(FONT_UI_HEALTH);
		g.drawString("\u2665 "+(int)Player.getPlayerHealth(), TD.SCREEN_WIDTH-TD.UI_WIDTH+TD.getScale()/4, TD.UI_HEIGHT-TD.getScale()/8-TD.getScale()/4-TD.getScale());
		g.setColor(COLOR_UI_TEXT);
		g.setFont(FONT_UI_TEXT);
		g.drawString("& "+(int)Player.getPlayerMoney(), TD.SCREEN_WIDTH-TD.UI_WIDTH+TD.getScale()/4, TD.UI_HEIGHT-TD.getScale()*3/16);
		g.drawString("Round "+RoundManager.getRoundNumber(true), TD.SCREEN_WIDTH-TD.UI_WIDTH+TD.getScale()/4, TD.UI_HEIGHT-TD.getScale()/2-TD.getScale()*2);
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
			g.setFont(new Font("Helvetica", Font.PLAIN, TD.getScale()/2));
			g.drawString(p.getLife()+"", (int)p.getX(), (int)p.getY()+30);
		}
	}

	private void drawTower(Graphics2D g, Tower t, boolean floating) {
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.BLACK);
		g.draw(t.bounds);
		g.setColor(COLOR_TOWER_RANGE);
		if (floating&&(!t.isPlaceable()||!Player.canAfford(t.value))) {
			g.setColor(COLOR_TOWER_RANGE_INVALID);
		}
		g.setStroke(new BasicStroke(1));
		if (Cursor.getSelectedTower()==t||Cursor.getTowerToPlace()==t) {
			g.fill(Util.getCircle(t.x+t.towerSize/2, t.y+t.towerSize/2, t.getTowerRange(), true));
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("Helvetica", Font.PLAIN, TD.getScale()/2));
		g.drawString(t.getName()+" "+t.getPopCount()+" "+(int)t.getGunAngle(), t.x, t.y+60);
		if (t.getDrawGunAngle()) {
			g.drawLine(t.x+t.towerSize/2, t.y+t.towerSize/2, t.x+t.towerSize/2+(int)Util.getXComp(t.getGunAngle(), t.getTowerRange()/2), t.y+t.towerSize/2-(int)Util.getYComp(t.getGunAngle(), t.getTowerRange()/2));
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
		if (UpgradeUI.isOpen()) {
			for (int i = 0;i<UpgradeUI.getButtons().size();i++) {
				b = UpgradeUI.getButtons().get(i);
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

		drawPlayerStats(g);
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
		drawFloatingTower(g);
//		g.setColor(Color.BLACK);
//		g.setStroke(new BasicStroke(1));
//		g.drawRect((int)UpgradeUI.bounds0.getCenterX()-2, (int)UpgradeUI.bounds0.getCenterY()-2, 5, 5);
//		g.draw(UpgradeUI.bounds0);
	}
}

class Round {
	private ArrayList<SpawnRequest> spawns = new ArrayList<>();
	private int roundNum;
	private boolean custom;

	public Round(ArrayList<SpawnRequest> spawns, int roundNum) {
		for (SpawnRequest sR:spawns) {
			this.spawns.add(sR);
		}
		this.roundNum = roundNum;
		custom = true;
	}

	public Round(int roundNum) {
		this.roundNum = roundNum;
	}

	public int getRoundNum() {
		return roundNum;
	}

	public ArrayList<SpawnRequest> getSpawns() {
		return spawns;
	}

	public boolean isCustom() {
		return custom;
	}

	@Override
	public String toString() {
		if (custom) return roundNum+": "+spawns;
		return roundNum+": "+"?";
	}
}

class RoundConfig implements Data {

	static void init() {
		//roundnum:delay,health,type;delay,health,type:roundnum:delay,health,type;countxdelay,health,type:

		ArrayList<String> rawConfig = new ArrayList<>(Arrays.asList(ROUND_CONFIG.split(":")));
		ArrayList<Integer> roundNumbers = new ArrayList<>();
		ArrayList<String> roundContents = new ArrayList<>();
		ArrayList<Round> rounds = new ArrayList<>();

		for (String str:rawConfig) {
//			Util.removeSpaces(str);
			if (!str.equals("")&&Util.isNumber(str)) {
				roundNumbers.add(Integer.valueOf(str));
			}
			else {
				roundContents.add(str);
			}
		}

		//System.out.println("\n"+rawConfig+"\n"+roundNumbers+"\n"+roundContents+"\n");
		int currentContents = 0;
		for (int i = 0;i<=Collections.max(roundNumbers);i++) {
			if (roundNumbers.contains(i)) {
				ArrayList<SpawnRequest> spawns = new ArrayList<>();
				String[] spawnsStr = roundContents.get(currentContents).split(";");
				currentContents++;
				for (String rawSpawnStr:spawnsStr) {
					String[] spawnStr = rawSpawnStr.split(",");
					int copies = 1;
					if (spawnStr[0].contains("x")) {
						String[] temp = spawnStr[0].split("x");
						spawnStr[0] = temp[1];
						try {
							copies = Integer.parseInt(temp[0]);
						}
						catch (NumberFormatException e) {
							System.out.println("--copy error");
							System.exit(0);
						}
					}
					int delay = 0;
					try {
						delay = Integer.parseInt(spawnStr[0]);
					}
					catch (NumberFormatException e) {
						System.out.println("--delay error");
						System.exit(0);
					}
					double health = 0;
					try {
						health = Double.parseDouble(spawnStr[1]);
					}
					catch (NumberFormatException e) {
						System.out.println("--health error");
						System.exit(0);
					}
					EnemyType type = null;
					try {
						type = EnemyType.valueOf(spawnStr[2]);
					}
					catch (Exception e) {
						System.out.println("--type error");
						System.exit(0);
					}
					for (int j = 0;j<copies;j++) {
						spawns.add(new SpawnRequest((spawns.size()>0)?delay:0, new Enemy(health, type, false)));
					}
				}
				rounds.add(new Round(spawns, i));
			}
			else {
				rounds.add(new Round(i));
			}
		}
		System.out.print("\nNumber of rounds: "+rounds.size()+"\nRoundNum, TotalTime, TotalHealth, Spawns\n");
		for (Round r:rounds) {
			int totalHealth = 0, totalTime = 0;
			for (SpawnRequest sR:r.getSpawns()) {
				totalHealth+=sR.getEnemy().getHealth();
				totalTime+=sR.getDelay();
			}
			System.out.println(r.getRoundNum()+" "+totalTime+" "+totalHealth+" "+r.getSpawns());
			RoundManager.addRound(r);
		}
		System.out.println();
	}
}

class RoundManager implements Data {
	private static ArrayList<Round> rounds = new ArrayList<>();
	private static int roundNumber, currentSpawn;
	private static double tilNextSpawn;
	private static Round currentRound = null;
	private static boolean startRound, stillHasRounds = true, awarded;
	private static boolean firstRound = true;

	static void addRound(Round round) {
		rounds.add(round);
	}

	public static int getRoundNumber(boolean visual) {
		if (visual) return roundNumber+1;
		else return roundNumber;
	}

	static void init() {
		RoundConfig.init();
	}

	static void setStartRound(boolean startRound) {
		RoundManager.startRound = startRound;
	}

	static void spawnNextEnemy() {
		System.out.println("+Spawned "+currentRound.getSpawns().get(currentSpawn).getEnemy());
		EnemyManager.spawnEnemy(currentRound.getSpawns().get(currentSpawn).getEnemy());
		currentSpawn++;
		if (currentSpawn<currentRound.getSpawns().size()) {
			tilNextSpawn = currentRound.getSpawns().get(currentSpawn).getDelay();
		}
	}

	static void startNextRound() {
		if (!firstRound) {
			roundNumber++;
		}
		else {
			firstRound = false;
		}
		System.out.println("Round number: "+getRoundNumber(false));
		if (roundNumber<rounds.size()) {
			currentRound = rounds.get(roundNumber);
			if (currentRound.getSpawns().size()==0) {
				startNextRound();
			}
			currentSpawn = 0;
			tilNextSpawn = currentRound.getSpawns().get(currentSpawn).getDelay();
		}
		else {
			System.out.println("Out of rounds");
			stillHasRounds = false;
		}
	}

	static boolean stillHasRounds() {
		return stillHasRounds;
	}

	static void tick() {
		if (currentRound!=null) {//round going on
			if (tilNextSpawn>0) {//can't spawn check
				tilNextSpawn-=1000/TD.UPS;//loop delay
			}
			else {//new enemy/round check
				if (currentSpawn>=currentRound.getSpawns().size()) {//next round
					if (EnemyManager.noEnemies()) {
						if (!awarded) {
							Player.changeMoney(ROUND_MONEY_REWARD+roundNumber);
							awarded = true;
						}
						if (stillHasRounds&&(startRound||TD.AUTO_ROUND)) {
							startNextRound();
							awarded = false;
						}
					}
					startRound = false;
				}
				else {//next enemy
					spawnNextEnemy();
				}
			}
		}
		else
			if (startRound) {
			startNextRound();//frist round
		}
	}
}

class SpawnRequest {
	private int delay;//milliseconds
	private Enemy enemy;

	SpawnRequest(int delay, Enemy enemy) {
		this.delay = delay;
		this.enemy = enemy;
	}

	int getDelay() {
		return delay;
	}

	Enemy getEnemy() {
		return enemy;
	}

	@Override
	public String toString() {
		return delay+" "+enemy;
	}
}

class Tile {
	private Point pos;
	private Color color;
	private int type;
	private Rectangle2D bounds;

	Tile(Point p, int type, Color c) {
		this.type = type;
		pos = p;
		color = c;
		bounds = Util.getRect(pos.y, pos.x, TD.getScale());
	}

	Rectangle2D getBounds() {
		return bounds;
	}

	Color getColor() {
		return color;
	}

	int getType() {
		return type;
	}
}

abstract class Tower implements Data {
	protected int x, y, towerSize, towerRange;
	protected double popCount, gunAngle, gunCooldown, maxCooldown, damage, value, bulletCount, gunSpread, hitCount, iceSpeedModifier, iceSlowDownDuration, metalBonusDamage, explosionRadius;
	protected boolean destroy, placed, canSeeCamo, canDamageMetal, drawGunAngle;
	protected Ellipse2D bounds;
	protected Rectangle2D hitbox;
	protected String name;
	protected ArrayList<TowerUpgrade> activeUpgrades = new ArrayList<>(), toActivate = new ArrayList<>(), availableUpgrades = new ArrayList<>();

	Tower(int x, int y, boolean placed, double size, double range, double towerTurretValue) {
		if (x>0&&y>0) {
			this.x = x;
			this.y = y;
		}
		else {
			this.x = -1000;
			this.y = -1000;
		}
		this.placed = placed;
		this.value = towerTurretValue;
		towerSize = (int)(TD.getScale()*size);
		towerRange = (int)(TD.getScale()*range);
		drawGunAngle = true;
		bounds = Util.getCircle(x, y, towerSize, false);
		hitbox = Util.getRect(x, y, towerSize);
	}

	boolean anyInRange() {
		for (int i = 0;i<EnemyManager.enemies.size();i++) if (inRange(EnemyManager.enemies.get(i))&&EnemyManager.enemies.get(i).canSee(canSeeCamo)) return true;
		return false;
	}

	boolean canDamageMetal() {
		return canDamageMetal;
	}

	void destroy(boolean sell) {
		System.out.println(name+" deleted: "+x+","+y+"\tFor: "+value);
		if (sell&&!TD.FREE) {
			Player.changeMoney(value*TOWER_SELL_RATE);
		}
		destroy = true;
	}

	Ellipse2D getBounds() {
		return bounds;
	}

	double getDamage() {
		return damage;
	}

	boolean getDrawGunAngle() {
		return drawGunAngle;
	}

	public double getGunAngle() {
		return gunAngle;
	}

	public double getGunCooldown() {
		return gunCooldown;
	}

	Rectangle2D getHitbox() {
		return hitbox;
	}

	double getMaxCooldown() {
		return maxCooldown;
	}

	public String getName() {
		return name;
	}

	public double getPopCount() {
		return popCount;
	}

	public int getTowerRange() {
		return towerRange;
	}
	double getValue() {
		return value;
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	boolean inRange(Enemy e) {
		return inRange(e.getY()+ENEMY_SIZE/2,e.getX()+ENEMY_SIZE/2);
	}

	boolean inRange(int x, int y) {
		return Util.distance(this.x+towerSize/2, this.y+towerSize/2, x, y)<=towerRange/2;
	}

	boolean isCanDamageMetal() {
		return canDamageMetal;
	}

	boolean isCanSeeCamo() {
		return canSeeCamo;
	}

	boolean isPlaceable() {
		if (UI.getBounds().intersects(hitbox)||!Window.bounds.contains(hitbox)) return false;
		for (int i = 0;i<TowerManager.towers.size();i++) {
			Tower t = TowerManager.towers.get(i);
			if (hitbox.intersects(t.hitbox)) return false;
		}
		Tile t;
		for (int i = 0;i<TD.level.l.length;i++) {
			for (int j = 0;j<TD.level.l[0].length;j++) {
				t = TD.level.l[i][j];
				if (t.getType()!=0&&hitbox.intersects(t.getBounds())) return false;
			}
		}
		return true;
	}

	boolean isPlaced() {
		return placed;
	}

	void place() {
		placed = true;
		if (!TD.FREE) {
			Player.changeMoney(-value);
		}
		bounds = Util.getCircle(x, y, towerSize, false);
		hitbox = Util.getRect(x, y, towerSize);
		TowerManager.towers.add(this);
		System.out.println(name+" placed: "+x+","+y+"\tFor: "+value);
	}

	abstract boolean tick();

	protected void tickFloating() {
		x = Cursor.getX()-towerSize/2;
		y = Cursor.getY()-towerSize/2;
		bounds = Util.getCircle(x, y, towerSize, false);
		hitbox = Util.getRect(x, y, towerSize);
	}

	@Override
	public String toString() {
		return name;
	}

	protected void updateUpgrades() {
		for (TowerUpgrade upgrade:toActivate) {
			if (upgrade.getType().isSpecial()) {
				upgradeSpecial(upgrade);
			}
			else {
				upgradeBasic(upgrade);
			}
			activeUpgrades.add(upgrade);
		}
		toActivate.clear();
		System.out.println("Updated "+this);
	}

	private void upgradeBasic(TowerUpgrade upgrade) {//TODO
		switch(upgrade.getType()) {
		case BULLET_COUNT:
			bulletCount = upgrade.getNewValue(bulletCount);
			break;
		case GUN_SPREAD:
			gunSpread = upgrade.getNewValue(gunSpread);
			break;
		case DAMAGE:
			damage = upgrade.getNewValue(damage);
			break;
		case MAX_COOLDOWN:
			maxCooldown = upgrade.getNewValue(maxCooldown);
			break;
		case RANGE:
			towerRange = upgrade.getNewValue(towerRange);
			break;
		case HIT_COUNT:
			hitCount = upgrade.getNewValue(hitCount);


		default:
			break;

		}
	}

	abstract void upgradeSpecial(TowerUpgrade upgrade);
}

class TowerBomb extends Tower {
	TowerBomb(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_BOMB_SIZE, TOWER_BOMB_BASE_RANGE, TOWER_BOMB_VALUE);
		maxCooldown = TOWER_BOMB_BASE_COOLDOWN;
		damage = TOWER_BOMB_BASE_DAMAGE;
		explosionRadius = TOWER_BOMB_BASE_EXPLOSION_RADIUS*TD.getScale();
		metalBonusDamage = TOWER_BOMB_BASE_METAL_BONUS_DAMAGE;
		canDamageMetal = true;
		name = "Bomb";
	}

	double getMetalBonusDamage() {
		return metalBonusDamage;
	}

	void shoot(Enemy target) {
		gunAngle = Util.getAngleDegrees(x+towerSize/2, y+towerSize/2, target.getY()+ENEMY_SIZE/2, target.getX()+ENEMY_SIZE/2);
		if (gunCooldown<=0) {
			gunCooldown = maxCooldown;
			ProjectileManager.projectiles.add(new ProjectileBomb(this, x+towerSize/2, y+towerSize/2, damage, explosionRadius, canDamageMetal, gunAngle, TOWER_BULLET_NORMAL_INITIAL_VELOCITY, TOWER_BULLET_NORMAL_ACCELERATION));
			//ProjectileManager.projectiles.add(new ProjectileBullet(this, x+towerSize/2, y+towerSize/2, damage, canDamageMetal, gunAngle, TOWER_BULLET_NORMAL_INITIAL_VELOCITY, TOWER_BULLET_NORMAL_ACCELERATION));
		}
	}

	@Override
	boolean tick() {
		if (placed) {
			if (gunCooldown>0) {
				gunCooldown-=1000/TD.UPS;
			}
			Enemy e;
			for (int i = 0;i<EnemyManager.enemies.size();i++) {
				e = EnemyManager.enemies.get(i);
				if (inRange(e)&&!e.isDestroy()&&e.canSee(canSeeCamo)) {
					shoot(e);
					break;
				}
			}
		}
		else {
			tickFloating();
		}
		return destroy;
	}

	@Override
	void upgradeSpecial(TowerUpgrade upgrade) {}
}

class TowerIce extends Tower {
	TowerIce(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_ICE_SIZE, TOWER_ICE_BASE_RANGE, TOWER_ICE_VALUE);
		availableUpgrades.addAll(Arrays.asList(
			new TowerUpgrade(TowerUpgradeType.MAX_COOLDOWN, 300, -10d, true, false, 5),
			new TowerUpgrade(TowerUpgradeType.HIT_COUNT, 100, -10d, true, false, 5),
			new TowerUpgrade(TowerUpgradeType.RANGE, 50, 1*TD.getScale(), true, false, 2),
			new TowerUpgrade(TowerUpgradeType.DOES_DAMAGE, 30, new TowerUpgrade(TowerUpgradeType.DAMAGE, 20, 1, true, false, 3))
		));
		maxCooldown = TOWER_ICE_BASE_COOLDOWN;
		hitCount = TOWER_ICE_BASE_HIT_COUNT;
		iceSpeedModifier = TOWER_ICE_BASE_SLOWDOWN;
		iceSlowDownDuration = TOWER_ICE_BASE_DURATION;
		name = "Ice";
		drawGunAngle = false;
	}

	void slow(Enemy e) {
		e.ice(iceSpeedModifier, (int)iceSlowDownDuration);
	}

	@Override
	boolean tick() {
		if (placed) {
			if (gunCooldown>0) {
				gunCooldown-=1000/TD.UPS;
			}
			else {
				Enemy e;
				int hits = 0;
				for (int i = 0;i<EnemyManager.enemies.size();i++) {
					e = EnemyManager.enemies.get(i);
					if (!e.isIced()&&!e.isDestroy()&&inRange(e)&&e.canSee(canSeeCamo)&&e.canDamage(canDamageMetal)) {
						gunCooldown = maxCooldown;
						slow(e);
						if (damage>0) {
							e.hit(damage, true);
						}
						hits++;
						if (hits>=hitCount) {
							break;
						}
					}
				}
			}
		}
		else {
			tickFloating();
		}
		return destroy;
	}

	@Override
	void upgradeSpecial(TowerUpgrade upgrade) {
		switch (upgrade.getType()) {
		case DOES_DAMAGE:
			damage = 1;
			break;
		default:
			break;
		}
	}
}

class TowerManager {
	static ArrayList<Tower> towers = new ArrayList<>();
}

class TowerNinja extends Tower {
	TowerNinja(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_NINJA_SIZE, TOWER_NINJA_BASE_RANGE, TOWER_NINJA_VALUE);
		maxCooldown = TOWER_NINJA_BASE_COOLDOWN;
		damage = TOWER_NINJA_BASE_DAMAGE;
		name = "Ninja";
		canSeeCamo = true;
	}

	void shoot(Enemy target) {
		gunAngle = Util.getAngleDegrees(x+towerSize/2, y+towerSize/2, target.getY()+ENEMY_SIZE/2, target.getX()+ENEMY_SIZE/2);
		if (gunCooldown<=0) {
			gunCooldown = maxCooldown;
			ProjectileManager.projectiles.add(new ProjectileBullet(this, x+towerSize/2, y+towerSize/2, damage, canDamageMetal, gunAngle, TOWER_BULLET_NORMAL_INITIAL_VELOCITY, TOWER_BULLET_NORMAL_ACCELERATION));
		}
	}

	@Override
	boolean tick() {
		if (placed) {
			if (gunCooldown>0) {
				gunCooldown-=1000/TD.UPS;
			}
			Enemy e;
			for (int i = 0;i<EnemyManager.enemies.size();i++) {
				e = EnemyManager.enemies.get(i);
				if (inRange(e)&&!e.isDestroy()&&e.canSee(canSeeCamo)) {
					shoot(e);
					break;
				}
			}
		}
		else {
			tickFloating();
		}
		return destroy;
	}

	@Override
	void upgradeSpecial(TowerUpgrade upgrade) {}
}

class TowerShotgun extends Tower {
	TowerShotgun(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_SHOTGUN_SIZE, TOWER_SHOTGUN_BASE_RANGE, TOWER_SHOTGUN_VALUE);
		maxCooldown = TOWER_SHOTGUN_BASE_COOLDOWN;
		damage = TOWER_SHOTGUN_BASE_DAMAGE;
		bulletCount = TOWER_SHOTGUN_BASE_BULLET_COUNT;
		gunSpread = TOWER_SHOTGUN_BASE_SPREAD;
		name = "Shotgun";
	}

	void shoot(Enemy e) {
		gunAngle = Util.getAngleDegrees(x+towerSize/2, y+towerSize/2, e.getY()+ENEMY_SIZE/2, e.getX()+ENEMY_SIZE/2);
		if (gunCooldown<=0) {
			gunCooldown = maxCooldown;
			double angle;
			for (int i = 0;i<bulletCount;i++) {
				angle = Util.getAngleSpread(gunAngle, gunSpread);
				ProjectileManager.projectiles.add(new ProjectileBullet(this, x+towerSize/2, y+towerSize/2, damage, canDamageMetal, angle, TOWER_BULLET_NORMAL_INITIAL_VELOCITY, TOWER_BULLET_NORMAL_ACCELERATION));
			}
		}
	}

	@Override
	boolean tick() {
		if (placed) {
			if (gunCooldown>0) {
				gunCooldown-=1000/TD.UPS;
			}
			Enemy e;
			for (int i = 0;i<EnemyManager.enemies.size();i++) {
				e = EnemyManager.enemies.get(i);
				if (inRange(e)&&!e.isDestroy()&&e.canSee(canSeeCamo)) {
					shoot(e);
					break;
				}
			}
		}
		else {
			tickFloating();
		}
		return destroy;
	}

	@Override
	void upgradeSpecial(TowerUpgrade upgrade) {}
}
class TowerSniper extends Tower {
	TowerSniper(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_SNIPER_SIZE, TOWER_SNIPER_BASE_RANGE, TOWER_SNIPER_VALUE);
		maxCooldown = TOWER_SNIPER_BASE_COOLDOWN;
		damage = TOWER_SNIPER_BASE_DAMAGE;
		name = "Sniper";
	}

	void shoot(Enemy e) {
		gunAngle = Util.getAngleDegrees(x+towerSize/2, y+towerSize/2, e.getY()+ENEMY_SIZE/2, e.getX()+ENEMY_SIZE/2);
		if (gunCooldown<=0) {
			gunCooldown = maxCooldown;
			if (e.getHealth()>=damage) {
				popCount+=damage;
			}
			else {
				popCount+=e.getHealth();
			}
			e.hit(damage, true);
		}
	}

	@Override
	boolean tick() {
		if (placed) {
			if (gunCooldown>0) {
				gunCooldown-=1000/TD.UPS;
			}
			Enemy e;
			for (int i = 0;i<EnemyManager.enemies.size();i++) {
				e = EnemyManager.enemies.get(i);
				if (!e.isDestroy()&&e.canSee(canSeeCamo)&&e.canDamage(canDamageMetal)) {
					shoot(e);
					break;
				}
			}
		}
		else {
			tickFloating();
		}
		return destroy;
	}

	@Override
	void upgradeSpecial(TowerUpgrade upgrade) {}
}

class TowerSprayer extends Tower {
	TowerSprayer(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_SPRAYER_SIZE, TOWER_SPRAYER_BASE_RANGE, TOWER_SPRAYER_VALUE);
		availableUpgrades.addAll(Arrays.asList(
			new TowerUpgrade(TowerUpgradeType.MAX_COOLDOWN, 300, -10d, true, false, 5),
			new TowerUpgrade(TowerUpgradeType.RANGE, 50, 1*TD.getScale(), true, false, 2),
			new TowerUpgrade(TowerUpgradeType.BULLET_COUNT, 20, 2, true, false, 3),
			new TowerUpgrade(TowerUpgradeType.DAMAGE, 20, 1, true, false, 3)
		));
		maxCooldown = TOWER_SPRAYER_BASE_COOLDOWN;
		damage = TOWER_SPRAYER_BASE_DAMAGE;
		bulletCount = TOWER_SPRAYER_BASE_PELLET_COUNT;
		name = "Sprayer";
		drawGunAngle = false;
	}

	void shoot() {
		if (gunCooldown<=0) {
			gunCooldown = maxCooldown;
			for (int i = 0;i<bulletCount;i++) {
				ProjectileManager.projectiles.add(new ProjectilePellet(this, x+towerSize/2, y+towerSize/2, damage, canDamageMetal, i*(360/bulletCount), getTowerRange()/2, TOWER_BULLET_NORMAL_INITIAL_VELOCITY, TOWER_BULLET_NORMAL_ACCELERATION));
			}
		}
	}

	@Override
	boolean tick() {
		if (placed) {
			if (gunCooldown>0) {
				gunCooldown-=1000/TD.UPS;
			}
			if (anyInRange()) {
				shoot();
			}
		}
		else {
			tickFloating();
		}
		return destroy;
	}

	@Override
	void upgradeSpecial(TowerUpgrade upgrade) {}
}

class TowerSuperTurret extends Tower {
	TowerSuperTurret(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_SUPER_TURRET_SIZE, TOWER_SUPER_TURRET_BASE_RANGE, TOWER_SUPER_TURRET_VALUE);
		maxCooldown = TOWER_SUPER_TURRET_BASE_COOLDOWN;
		damage = TOWER_SUPER_TURRET_BASE_DAMAGE;
		name = "Super Turret";
	}

	void shoot(Enemy target) {
		gunAngle = Util.getAngleDegrees(x+towerSize/2, y+towerSize/2, target.getY()+ENEMY_SIZE/2, target.getX()+ENEMY_SIZE/2);
		if (gunCooldown<=0) {
			gunCooldown = maxCooldown;
			ProjectileManager.projectiles.add(new ProjectileBullet(this, x+towerSize/2, y+towerSize/2, damage, canDamageMetal, gunAngle, TOWER_BULLET_NORMAL_INITIAL_VELOCITY, TOWER_BULLET_NORMAL_ACCELERATION));
		}
	}

	@Override
	boolean tick() {
		if (placed) {
			if (gunCooldown>0) {
				gunCooldown-=1000/TD.UPS;
			}
			Enemy e;
			for (int i = 0;i<EnemyManager.enemies.size();i++) {
				e = EnemyManager.enemies.get(i);
				if (inRange(e)&&!e.isDestroy()&&e.canSee(canSeeCamo)) {
					shoot(e);
					break;
				}
			}
		}
		else {
			tickFloating();
		}
		return destroy;
	}

	@Override
	void upgradeSpecial(TowerUpgrade upgrade) {}
}

class TowerTurret extends Tower {
	TowerTurret(int x, int y, boolean placed) {
		super(x, y, placed, TOWER_TURRET_SIZE, TOWER_TURRET_BASE_RANGE, TOWER_TURRET_VALUE);
		availableUpgrades.addAll(Arrays.asList(
			new TowerUpgrade(TowerUpgradeType.MAX_COOLDOWN, 200, -10d, true, false, 3),
			new TowerUpgrade(TowerUpgradeType.RANGE, 100, 2*TD.getScale(), true, false, 3),
			new TowerUpgrade(TowerUpgradeType.DAMAGE, 230, 1, true, false, 4)
		));
		maxCooldown = TOWER_TURRET_BASE_COOLDOWN;
		damage = TOWER_TURRET_BASE_DAMAGE;
		name = "Turret";
	}

	void shoot(Enemy target) {
		gunAngle = Util.getAngleDegrees(x+towerSize/2, y+towerSize/2, target.getY()+ENEMY_SIZE/2, target.getX()+ENEMY_SIZE/2);
		if (gunCooldown<=0) {
			gunCooldown = maxCooldown;
			ProjectileManager.projectiles.add(new ProjectileBullet(this, x+towerSize/2, y+towerSize/2, damage, canDamageMetal, gunAngle, TOWER_BULLET_NORMAL_INITIAL_VELOCITY, TOWER_BULLET_NORMAL_ACCELERATION));
		}
	}

	@Override
	boolean tick() {
		if (placed) {
			if (gunCooldown>0) {
				gunCooldown-=1000/TD.UPS;
			}
			Enemy e;
			for (int i = 0;i<EnemyManager.enemies.size();i++) {
				e = EnemyManager.enemies.get(i);
				if (inRange(e)&&!e.isDestroy()&&e.canSee(canSeeCamo)) {
					shoot(e);
					break;
				}
			}
		}
		else {
			tickFloating();
		}
		return destroy;
	}

	@Override
	void upgradeSpecial(TowerUpgrade upgrade) {}
}

class TowerUpgrade {
	private TowerUpgradeType type;
	private double value, price;
	private boolean percentage, increment, hasChild;
	private TowerUpgrade child;
	private int repeat;

	TowerUpgrade(TowerUpgradeType type) {
		this.type = type;
	}

	TowerUpgrade(TowerUpgradeType type, double price, double value, boolean increment, boolean percentage) {
		this.type = type;
		this.price = price;
		this.value = value;
		this.increment = increment;
		this.percentage = percentage;
	}

	TowerUpgrade(TowerUpgradeType type, double price, double value, boolean increment, boolean percentage, int repeat) {
		this.type = type;
		this.price = price;
		this.value = value;
		this.increment = increment;
		this.percentage = percentage;
		this.repeat = repeat;
		hasChild = true;
	}

	TowerUpgrade(TowerUpgradeType type, double price, double value, boolean increment, boolean percentage, TowerUpgrade child) {
		this.type = type;
		this.price = price;
		this.value = value;
		this.increment = increment;
		this.percentage = percentage;
		this.child = child;
		hasChild = true;
	}

	TowerUpgrade(TowerUpgradeType type, double price, TowerUpgrade child) {
		this.type = type;
		this.price = price;
		this.child = child;
		hasChild = true;
	}

	public void decreaseRepeat() {
		repeat--;
	}

	TowerUpgrade getChild() {
		return child;
	}

	double getNewValue(double oldValue) {
		if (increment) {
			if (percentage)
				return oldValue+(oldValue*(value/100));
			else
				return oldValue+value;
		}
		else {
			if (percentage)
				return oldValue*(value/100);
			else
				return value;
		}
	}

	int getNewValue(int oldValue) {
		return (int)getNewValue((double)oldValue);
	}

	double getPrice() {
		return price;
	}

	int getRepeat() {
		return repeat;
	}

	TowerUpgradeType getType() {
		return type;
	}

	double getValue() {
		return value;
	}

	boolean hasChild() {
		return hasChild;
	}

	boolean isIncrement() {
		return increment;
	}

	boolean isPercentage() {
		return percentage;
	}

	@Override
	public String toString() {
		if (type.isSpecial())
			return type+"";
		else return type+" "+value;
	}
}

//TODO
enum TowerUpgradeType {
	MAX_COOLDOWN(false, "Firerate"), DAMAGE(false, "Damage"), RANGE(false, "Range"), HIT_COUNT(false, "Hits"),//most
	DOES_DAMAGE(true, "Does Damage"),//ice
	BULLET_COUNT(false, "Pellet count"),//shotgun, sprayer
	GUN_SPREAD(false, "BulletSpread");//shotgun

	private boolean special;
	private String displayText;

	TowerUpgradeType(boolean special, String displayText) {
		this.special = special;
		this.displayText = displayText;
	}

	String getDisplayText() {
		return displayText;
	}

	boolean isSpecial() {
		return special;
	}
}

class UI {
	private static Rectangle bounds;
	private static ArrayList<Button> buttons;

	static Rectangle getBounds() {
		return bounds;
	}

	public static ArrayList<Button> getButtons() {
		return buttons;
	}

	static void init() {
		bounds = new Rectangle(TD.level.l[0].length*TD.getScale(), 0, TD.UI_WIDTH, TD.UI_HEIGHT);
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
}

class UpgradeUI implements Data {
	private static int x, y;
	private static boolean open, refresh;
	private static Tower currentTower;
	private static ArrayList<TowerUpgrade> upgradeList = new ArrayList<>();
	private static ArrayList<ButtonUpgrade> buttons = new ArrayList<>();

	static void open(Tower tower) {
		currentTower = tower;
		if (currentTower!=null&&currentTower.availableUpgrades.size()>0) {
			upgradeList.clear();
			buttons.clear();
			setNewPos(currentTower.getX()+currentTower.towerSize/2, currentTower.getY()+currentTower.towerSize/2);
			for (int i = 0;i<currentTower.availableUpgrades.size();i++) {
				upgradeList.add(currentTower.availableUpgrades.get(i));
				buttons.add(new ButtonUpgrade(x, y, i));
			}
			System.out.println("+Opening Upgrade UI");
			open = true;
		}
	}

	static void close() {
		System.out.println("-Closing Upgrade UI");
		open = false;
		upgradeList.clear();
		buttons.clear();
	}

	private static void refresh() {
		System.out.println("=Refresh Upgrade UI");
		close();
		open(Cursor.getSelectedTower());

	}

	static void tick() {
		if (refresh) {
			refresh();
			refresh = false;
		}
	}

	static void setRefresh() {
		refresh = true;
	}

	static ArrayList<ButtonUpgrade> getButtons() {
		return buttons;
	}

	static ArrayList<TowerUpgrade> getUpgradeList() {
		return upgradeList;
	}

	static int getX() {
		return x;
	}

	static int getY() {
		return y;
	}

	static int getXSize() {
		if (currentTower!=null) return currentTower.availableUpgrades.size()*UPGRADE_UI_SIZE*3/2-UPGRADE_UI_SIZE/2;
		return UPGRADE_UI_SIZE;
	}

	static int getYSize() {
		return UPGRADE_UI_SIZE;
	}

	static boolean isOpen() {
		return open;
	}

	static void pressed(int b) {
		if (currentTower!=null) {
			TowerUpgrade upgrade = currentTower.availableUpgrades.get(b);
			if (Player.canAfford(upgrade.getPrice())) {
				Player.changeMoney(-upgrade.getPrice());
				boolean delete = false;
				if (upgrade.hasChild()) {
					if (upgrade.getRepeat()>0) {
						upgrade.decreaseRepeat();
						if (upgrade.getRepeat()==0) {
							delete = true;
						}
					}
					else {
						currentTower.availableUpgrades.set(currentTower.availableUpgrades.indexOf(upgrade), upgrade.getChild());
					}
				}
				else {
					delete = true;
				}
				currentTower.toActivate.add(upgrade);
				if (delete) {
					currentTower.availableUpgrades.remove(upgrade);
				}
				currentTower.updateUpgrades();
			}
			System.out.println(upgrade.getType());
			setRefresh();
		}
	}

	static void setNewPos(int x, int y) {
		Rectangle2D bounds;
		int attempts = 0;
		double a = 90, dA = 180, xOffset, yOffset, distance = TD.getScale()*3;
		do {
			xOffset = Util.getXComp(a, distance);
			yOffset = Util.getYComp(a, distance);
			bounds = new Rectangle2D.Double(x+xOffset-getXSize()/2-UPGRADE_UI_PADDING, y+yOffset-getYSize()/2-UPGRADE_UI_PADDING, getXSize()+UPGRADE_UI_PADDING*2, getYSize()+UPGRADE_UI_PADDING*2);
			a+=dA;
			if (a>=360) {
				a-=360;
			}
			if (dA==180) {
				dA = 45;
			}
			attempts++;
		}
		while (attempts<14&&(!Window.bounds.contains(bounds)||UI.getBounds().intersects(bounds)));
		UpgradeUI.x = (int)bounds.getX();
		UpgradeUI.y = (int)bounds.getY();

	}

}

class Util {

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

@SuppressWarnings("serial")
class Window extends JFrame {
	private static JFrame frame;
	private static Input input;
	static Renderer renderer;
	static Rectangle2D bounds;

	static void init() {
		renderer = new Renderer();
		input = new Input();
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());
		frame.setSize(TD.WINDOW_WIDTH, TD.WINDOW_HEIGHT);
		frame.setTitle("TD "+TD.WINDOW_WIDTH+"x"+TD.WINDOW_HEIGHT+"-"+TD.getScale());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(input);
		frame.addMouseMotionListener(input);
		frame.addMouseListener(input);
		frame.addMouseWheelListener(input);
		frame.add(renderer);
		bounds = Util.getRect(0, 0, TD.WINDOW_WIDTH-6, TD.WINDOW_HEIGHT-28);
		if (TD.GRAPHICS) {
			frame.setVisible(true);
		}
	}
}

class UpdateLoop implements Runnable {
	private static double speedFactor = 1;

	static void setSpeedFactor(double speedFactor) {
		UpdateLoop.speedFactor = speedFactor;
	}

	@Override
	public void run() {
		System.out.println("UPS: "+TD.UPS);
		final long updateSpeed = 1000000000/TD.UPS;
		long startTime = 0, wait = 0;
		while (TD.RUNNING) {
			startTime = System.nanoTime();

			update();
			Window.renderer.repaint();

			wait = (long)(((updateSpeed/speedFactor)-(System.nanoTime()-startTime))/1000000);
			if (TD.SPAM_CONSOLE) {
				System.out.println(updateSpeed+" "+(System.nanoTime()-startTime)+" "+wait+" "+((TD.PAUSED)?"PAUSED":""));
			}
			try {
				if (wait>0) {
					Thread.sleep(wait);
				}
				else {
					System.out.println("LAGGING "+wait);
				}
			}
			catch (Exception e) {
				System.out.println("-UPDATE LOOP ERROR");
				e.printStackTrace();
			}
		}


	}

	//---
//	@Override
//	public void run() {
//		// convert the time to seconds
//		double delta = 1/TD.UPS;
//		double nextTime = System.nanoTime() / 1000000000.0;
//		double maxTimeDiff = 0.5;
//		int skippedFrames = 1;
//		int maxSkippedFrames = 5;
//		while (TD.RUNNING) {
//			// convert the time to seconds
//			double currTime = System.nanoTime() / 1000000000.0;
//			if ((currTime-nextTime) > maxTimeDiff) {
//				nextTime = currTime;
//			}
//			if (currTime>=nextTime) {
//				// assign the time for the next update
//				nextTime+=delta;
//				update();
//				if ((currTime<nextTime)||(skippedFrames>=maxSkippedFrames)) {
//					Window.renderer.repaint();
//					System.out.println(skippedFrames);
//					skippedFrames = 1;
//				}
//				else {
//					skippedFrames++;
//				}
//			}
//			else {
//				// calculate the time to sleep
//				int sleepTime = (int) (1000.0 * (nextTime - currTime));
//				// sanity check
//				if (sleepTime > 0) {
//					// sleep until the next update
//					try {
//						Thread.sleep(sleepTime);
//					} catch (InterruptedException e) {
//						// do nothing
//					}
//				}
//			}
//
//		}
//	}
	//---

	private void update() {
		if (Cursor.getTowerToPlace()!=null) {
			Cursor.getTowerToPlace().tick();
		}
		if (!TD.PAUSED) {
			try {
				RoundManager.tick();
				for (int i = 0;i<EnemyManager.enemies.size();i++) if (EnemyManager.enemies.get(i).tick()) {
					System.out.println("-Deleted "+EnemyManager.enemies.get(i));
					EnemyManager.enemies.remove(i);
				}
				EnemyManager.tick();
				for (int i = 0;i<ProjectileManager.projectiles.size();i++) if (ProjectileManager.projectiles.get(i).tick()) {
					ProjectileManager.projectiles.remove(i);
				}
				for (int i = 0;i<TowerManager.towers.size();i++) if (TowerManager.towers.get(i).tick()) {
					TowerManager.towers.remove(i);
				}
				UpgradeUI.tick();
			}
			catch (Exception e) {
				System.out.println("-UPDATE ERROR");
				e.printStackTrace();
			}
		}
	}
}






























































