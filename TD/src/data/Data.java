package data;

import java.awt.Color;
import java.awt.Font;

import main.Main;

public interface Data {
	static final String ROUND_CONFIG_PATH = "src/data/roundConfig";

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


	static final int UPGRADE_UI_SIZE = Main.getScale()*3/2, UPGRADE_UI_PADDING = Main.getScale()/2;

	static final Font FONT_UI_TEXT = new Font("Helvetica", Font.BOLD, Main.getScale()*5/4);
	static final Font FONT_UI_HEALTH = new Font("Helvetica", Font.BOLD, Main.getScale()*5/4);

	static final Color COLOR_UI_UPS = new Color(255,255,0,255);
	static final Color COLOR_UI_TEXT = new Color(255,255,255,255);
	static final Color COLOR_UI_HEALTH = new Color(255,0,0,255);

	static final Color COLOR_UI_BACKROUND = new Color(80,80,80,255);
	static final Color COLOR_UI_BUTTON = new Color(50,50,255,255);
	static final Color COLOR_UI_BUTTON_HOVERED = new Color(100,100,255,255);

	static final Color COLOR_TOWER_RANGE = new Color(0,0,0,50);
	static final Color COLOR_TOWER_RANGE_INVALID = new Color(255,75,75,50);

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

	static final double STARTING_MONEY = 10000/*750*/, STARTING_LIVES = 100;
	static final double ROUND_MONEY_REWARD = 100, MONEY_CHEAT_AMOUNT = 100;

	static final double TOWER_SELL_RATE = 0.8d;

	static final int ENEMY_SIZE = Main.getScale(), ENEMY_HITBOX_SIZE = Main.getScale()*3/2;
	static final int ENEMY_REGEN_RATE = 1000;//millis

	static final int SAFE_ENEMY_COUNT = 100, SAFE_TOWER_COUNT = 100, SAFE_PROJECTILE_COUNT = 50;

}












