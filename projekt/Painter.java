package projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 * Klasa odpowiadająca za rysowanie obiektów oraz obsługę zdarzeń ich
 * dotyczących (na przykład kolizji).
 */
public class Painter extends Panel implements ActionListener {
	int score = 0;
	Timer t = new Timer(5, this);
	int playerWidth = 50;
	int playerHeight = 50;
	int x0 = 475;
	int y0 = 450;
	int playerVel = 2;
	int playerVelx = 0;
	int playerVely = 0;
	int counter = 0;
	int radius = 100;
	ArrayList<Ellipse2D> balls = new ArrayList<Ellipse2D>();
	Rectangle2D player;

	public Painter() {
		setBackground(Color.gray);
		setPreferredSize(new Dimension(1000, 500));

		addEntities();
		t.start();
		MovementControl controller = new MovementControl(this);
		addKeyListener(controller);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.clearRect(0, 0, getWidth(), getHeight());
		drawPlayer(g);
		drawBalls(g);

	}

	public void addEntities() {
		balls.clear();
		for (int i = 0; i < FileParser.noOfBalls; i++) {
			balls.add(new Ellipse2D.Double(FileParser.xStart[i], FileParser.yStart[i], radius, radius));
		}
		player = new Rectangle2D.Double(x0, y0, playerWidth, playerHeight);
	}

	public void drawPlayer(Graphics g) {
		g.setColor(Color.blue);
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(player);

	}

	public void drawBalls(Graphics g) {
		g.setColor(Color.red);
		Graphics2D g2 = (Graphics2D) g;
		for (Ellipse2D e : balls) {
			g2.fill(e);
		}

	}

	public void actionPerformed(ActionEvent evt) {
		repaint();
		addEntities();
		x0 += playerVelx;
		y0 += playerVely;
		for (int i = 0; i < FileParser.noOfBalls; i++) {
			if (FileParser.xStart[i] < 0 || FileParser.xStart[i] > getWidth() - radius - 1) {
				FileParser.xVelocity[i] = -FileParser.xVelocity[i];
			}
			if (FileParser.yStart[i] < 0 || FileParser.yStart[i] > getHeight() - radius - 1) {
				FileParser.yVelocity[i] = -FileParser.yVelocity[i];
			}
			if (balls.get(i).intersects(player) == true) {
				System.out.println("Collision detected!" + counter);
				counter++;
			}
			FileParser.xStart[i] += FileParser.xVelocity[i];
			FileParser.yStart[i] += FileParser.yVelocity[i];
		}

	}

}