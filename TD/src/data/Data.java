package data;

import java.awt.Color;
import java.awt.Font;

import main.Main;

public interface Data {
	static final String ROUND_CONFIG = ""//TODO move this to own file

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

	static final int ENEMY_SIZE = Main.getScale(), ENEMY_HITBOX_SIZE = Main.getScale()*3/2;
	static final int ENEMY_REGEN_RATE = 1000;//millis

	static final int UPGRADE_UI_SIZE = Main.getScale(), UPGRADE_UI_PADDING = Main.getScale()/2;

	static final Font FONT_UI_TEXT = new Font("Helvetica", Font.BOLD, Main.getScale()*5/4);
	static final Font FONT_UI_HEALTH = new Font("Helvetica", Font.BOLD, Main.getScale()*5/4);

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
