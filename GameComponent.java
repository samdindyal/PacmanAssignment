import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
/**
	Title: The "GameComponent" class.
	Date Written: February 2014 - March 2014
	Author: Samuel Dindyal
	Description: A game contained in a drawing panel which can be controlled by arrow keys and mouse clicks.
*/
public class GameComponent extends JComponent implements KeyListener, MouseListener{

	private Color background;
	private int sizeX, sizeY, score, lastKilledRanking;
	public ArrayList<Prey> prey;
	public Graphics2D g2;
	private Random random;
	private Predator predator;
	private Color lastKilledColor;
/**
	Creates a new GameComponent with a defined size and background color.
*/
	public GameComponent(int sizeX, int sizeY, Color background)
	{
		this.background = background;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		setLayout(null);
		setSize(sizeX, sizeY);
		setBackground(background);
		setOpaque(true);

		random = new Random();
		lastKilledColor = Color.BLACK;
		lastKilledRanking = 0;
		prey = new ArrayList<Prey>();
		predator = new Predator(new Rectangle(0, 0, sizeX, sizeY), 3, DirectionsNESW.EAST, 50);
		int d = 0;
		for (int i = 0; i<50; i++)
		{
			int xLen = random.nextInt((3*sizeX/4)-50) + 50;
			int yLen = random.nextInt((3*sizeY/4)-50) + 50;
			int xLoc = random.nextInt(sizeX-xLen);
			int yLoc = random.nextInt(sizeY-yLen);

			Rectangle boundingBox = new Rectangle(xLoc, yLoc, xLen, yLen);
			Prey pTEMP = new Prey(boundingBox,CreatureRanking.randomPreyRank(), random.nextInt(5)+1, DirectionsURDL.getRandomDirection());
			prey.add(pTEMP);
		}
		setVisible(true);
		setFocusable(true);
		this.addMouseListener(this);
		this.addKeyListener(this);
	}
	public int getScore(){return score;}
	public void trigger()
	{
		predator.trigger();
		predator.move();
		for (int i = 0; i<prey.size(); i++)
		{
			if (prey.get(i).getDistance(predator)-predator.getRadius()-prey.get(i).getRadius()<=50)
				prey.get(i).evade(predator);
			
			prey.get(i).move();
			if (predator.collide(prey.get(i)))
			{
				score+=prey.get(i).getRanking();
				lastKilledRanking = prey.get(i).ranking.getRanking();
				lastKilledColor = prey.remove(i).getColor();
			}
		}

		repaint();
	}
/**
	Sets the speed of the predator.

	@param 	newSpeed 		An integer representation of the new speed of the "Predator".
*/
	public void setPredatorSpeed(int newSpeed){predator.setSpeed(newSpeed);}
	
/**
	Gets the color of the last "Prey" the "Predator" killed.

	@return 		The color of the last "Prey" eaten.
*/
	public Color getLastKilledColor(){return lastKilledColor;}
/**
	Gets the ranking of the last "Prey" the "Predator" killed.

	@return 		The ranking of the last "Prey" eaten as an int.
*/
	public int getLastKilledRanking(){return lastKilledRanking;}

/**
	Gets the amount of "Prey" the "Predator" killed.

	@return 		The amount "Prey" eaten.
*/
	public int amountOfPrey(){return prey.size();}
/**
	Sets the difficulty of the game to Easy, Medium or Difficult.

	@param 	difficulty 		A String representation of the new difficulty.
*/
	public void setDifficulty(String difficulty)
	{
		if (difficulty.equals("Easy"))
		{
			for (Prey p : prey)
				p.setSpeed(random.nextInt(2)+1);
		}
		else if (difficulty.equals("Medium"))
		{
			for (Prey p: prey)
				p.setSpeed(random.nextInt(2) + 2);
		}
		else if (difficulty.equals("Hard"))
		{
			for (Prey p: prey)
				p.setSpeed(random.nextInt(3)+3);
		}
	}
/**
	Paints the game.

	@param g 		The Graphics to paint the fame onto.
*/
	public void paintComponent(Graphics g)
	{
		g2 = (Graphics2D) g;
		g2.setColor(background);
		g2.fillRect(0,  0, getWidth(), getHeight());

		predator.draw(g2);
		for (Prey p : prey)
			p.draw(g2);
	}

	public void keyTyped(KeyEvent e) {}
/**
	Does an action if a key was pressed. In this case, turns the "Predator".

	@param e 		The KeyEvents happening and being sent in.
*/
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			predator.turn(DirectionsURDL.LEFT);
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			predator.turn(DirectionsURDL.RIGHT);
		if (e.getKeyCode() == KeyEvent.VK_UP)
			predator.turn(DirectionsURDL.UP);
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			predator.turn(DirectionsURDL.DOWN);
	}
	public void keyReleased(KeyEvent e) {}
	
/**
	Does an action if a button on the mouse was pressed. In this case, turns the "Predator".

	@param e 		The MouseEvents happening and being sent in.
*/
	public void mouseClicked(MouseEvent e) 
	{
		if (SwingUtilities.isLeftMouseButton(e))
			predator.turnCounterClockwise();
		else
			predator.turnClockwise();
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
