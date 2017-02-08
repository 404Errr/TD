package window;

import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

import graphics.Renderer;
import main.Main;
import main.Util;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public static JFrame frame;
	public static Input input;
	public static Renderer renderer;
	public static Rectangle2D bounds;

	public static void init() {
		renderer = new Renderer();
		input = new Input();
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());
		frame.setSize(Main.getWINDOW_WIDTH(), Main.getWINDOW_HEIGHT());
		frame.setTitle("TD "+main.Main.getWINDOW_WIDTH()+"x"+Main.getWINDOW_HEIGHT()+"-"+Main.getScale());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(input);
		frame.addMouseMotionListener(input);
		frame.addMouseListener(input);
		frame.addMouseWheelListener(input);
		frame.add(renderer);
		bounds = Util.getRect(0, 0, Main.getWINDOW_WIDTH()-6, Main.getWINDOW_HEIGHT()-28);
		frame.setVisible(true);
	}
}


