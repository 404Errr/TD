package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import data.Data;
import level.Level;
import level.LevelLayout;
import round.RoundConfig;
import ui.UI;
import window.Window;

/* stuff and things:
 * C = center the window
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
 * F3 = toggle debug
 * K = kill all enemies
 * J = delete all towers
 * L = loop toggle
 * O = toggle money doing things
 * M = cheat in money
 * N = cheat out money
 * up/down arrow = change health of first enemy

 * use with minor caution:
 * - = 1x speed
 * = = multiply speed modifier by SPEED_MODIFIER_MULTIPLIER
 */


public class Main implements Data {
	private static int SCREEN_HEIGHT, SCREEN_WIDTH, WINDOW_HEIGHT, WINDOW_WIDTH, UI_HEIGHT, UI_WIDTH;
	private static int SCALE;
	private static final int UI_W = 7;//dont change
	static {
		LevelLayout.initLayout(LevelLayout.level1);//58x33
		GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = screen.getDisplayMode().getWidth(), height = screen.getDisplayMode().getHeight();
		int screenSize = (LevelLayout.layout.length>LevelLayout.layout[0].length+UI_W)?height:width;
		int layoutSize = Math.max(LevelLayout.layout.length, LevelLayout.layout[0].length+UI_W);
		SCALE = (int)((screenSize*15d/16d)/layoutSize);
		UI_WIDTH = SCALE*UI_W;
		UI_HEIGHT = SCALE*LevelLayout.layout.length;
		SCREEN_WIDTH = SCALE*LevelLayout.layout[0].length+UI_WIDTH;
		SCREEN_HEIGHT = SCALE*LevelLayout.layout.length;
		WINDOW_WIDTH = SCREEN_WIDTH+3+3;
		WINDOW_HEIGHT = SCREEN_HEIGHT+25+3;
		System.out.println("WxH: "+LevelLayout.layout[0].length+"x"+LevelLayout.layout.length+" WxH: "+width+"x"+height+" Screen Size:"+screenSize+" Layout Size:"+layoutSize+" Scale:"+SCALE);
	}

	public static final int UPS = 120, ENEMY_SIZE = (int)(SCALE*2d/3d);
	public static final double ENEMY_SPEED = 0.45d, ENEMY_BASE_SPEED = ENEMY_SPEED*SCALE/20d*120d/UPS;
	public static final double PROJECTILE_SPEED = 1.00d, PROJECTILE_BASE_SPEED = PROJECTILE_SPEED*SCALE/20d*120d/UPS;

//	public static final boolean SPAM_CONSOLE = false;//doesn't do anything atm
	public static boolean RUNNING = true, LOOP_ENEMIES = false, AUTO_ROUND = false, PAUSED, FREE = false, DEBUG = false;

	private static Level level;
	private static UpdateLoop updateLoop;

	public static void main(String[] args) {
		level = new Level(LevelLayout.layout);//create the level
		RoundConfig.init();//generate rounds from the config file
		updateLoop = new UpdateLoop();//create the loop
		Window.init();//create the window
		UI.init();//initialize the ui
		Thread update = new Thread(updateLoop, "TD");//create thread
		update.start();//start thread

	}

	public static void toggleAutoRound() {//toggles automatic round starting
		AUTO_ROUND = (AUTO_ROUND)?false:true;
		System.out.println("Automatic rounds set to: "+AUTO_ROUND);
	}

	public static void toggleDebug() {//toggles debug
		DEBUG = (DEBUG)?false:true;
		System.out.println("Debug set to: "+DEBUG);
	}

	public static void toggleFree() {//toggles money functionality
		FREE = (FREE)?false:true;
		System.out.println("Free set to: "+FREE);
	}

	public static void toggleLoop() {//toggles what happens when enemies reach the end of the path (go to start or get deleted)
		LOOP_ENEMIES = (LOOP_ENEMIES)?false:true;
		System.out.println("looping set to: "+LOOP_ENEMIES);
	}

	public static void togglePaused() {//toggle pause
		PAUSED = (PAUSED)?false:true;
		System.out.println((PAUSED)?"PAUSED":"UNPAUSED");
	}

	public static void togglePaused(boolean state) {//set the pause
		PAUSED = state;
		System.out.println((PAUSED)?"PAUSED":"UNPAUSED");
	}

	public static int getScale() {
		return SCALE;
	}

	public static int getSCREEN_HEIGHT() {
		return SCREEN_HEIGHT;
	}

	public static int getSCREEN_WIDTH() {
		return SCREEN_WIDTH;
	}

	public static int getWINDOW_HEIGHT() {
		return WINDOW_HEIGHT;
	}

	public static int getWINDOW_WIDTH() {
		return WINDOW_WIDTH;
	}

	public static int getUI_HEIGHT() {
		return UI_HEIGHT;
	}

	public static int getUI_WIDTH() {
		return UI_WIDTH;
	}

	public static int getUPS() {
		return UPS;
	}

	public static int getEnemySize() {
		return ENEMY_SIZE;
	}

	public static double getEnemyBaseSpeed() {
		return ENEMY_BASE_SPEED;
	}

	public static double getProjectileBaseSpeed() {
		return PROJECTILE_BASE_SPEED;
	}

//	public static boolean spamConsole() {
//		return SPAM_CONSOLE;
//	}

	public static boolean isRunning() {
		return RUNNING;
	}

	public static boolean doLoopEnemies() {
		return LOOP_ENEMIES;
	}

	public static boolean doAutoRound() {
		return AUTO_ROUND;
	}

	public static boolean isFree() {
		return FREE;
	}

	public static boolean isPaused() {
		return PAUSED;
	}

	public static Level getLevel() {
		return level;
	}

	public static UpdateLoop getUpdateLoop() {
		return updateLoop;
	}
}





















