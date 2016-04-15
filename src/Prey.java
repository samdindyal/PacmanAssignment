import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;
/**
Title: The "Prey" class.
Written: February 2014 - March 2014
Author: Samuel Dindyal
Description: A type of "Creature" with a different look and the ability to evade another "MoveableShape".
*/
public class Prey extends Creature{
	private Color color, randColor;
	private int diameter, counter, xDLoc, yDLoc;
	private Random random;
/**
	Creatures a "Prey" with defined values.

	@param	boundingBox 	The box which bounds the movement of the "Prey".
	@param 	ranking 		The ranking/level of the "Prey".
	@param 	speed 			The speed of the "Prey".
	@param 	facingDirection	The initial direction the "Prey" is facing.
*/
	public Prey(Rectangle boundingBox, CreatureRanking ranking, int speed, Directions facingDirection) 
	{
		super(boundingBox, ranking, speed, facingDirection, (int)((ranking.getRanking()/3.0)*30));
		this.color = color;
		this.diameter = (int)((ranking.getRanking()/3.0)*30);
		counter = 0;
		random = new Random();
		xDLoc = random.nextInt((int)(boundingBox.getWidth()))+boundingBox.x+(int)xLoc;
		yDLoc = random.nextInt((int)(boundingBox.getHeight()))+boundingBox.y+(int)yLoc;

		if (ranking.getRanking()<3)
			super.speed = speed/2.0;

		if (speed ==1)
			color = Color.RED.darker();
		else if (speed <4)
			color = Color.GREEN.darker();
		else if (speed <=4 && ranking.getRanking() == 3)
			color = new Color(5, 125, 218);
		else
			color = Color.GREEN.darker();

		randColor = generateRandomColorShade(color);

	}
/**
	Triggers a change for the "Prey".
*/
	public void trigger(){counter++;}
/**
	Gets integer representation of the "Prey"'s ranking.

	@return 	A representation of the "Prey"'s ranking as an integer.
*/
	public int getRanking(){return ranking.getRanking();}
/**
	Gets the main "Color" of the "Prey".

	@return 	The color of the "Prey".
*/
	public Color getColor(){return color;}
/**
	Moves the "Prey" depending on the "facingDirection".
*/
	public void move() 
	{
		if (boundingBox.x+(int)xLoc+diameter >= boundingBox.getWidth()+boundingBox.x && facingDirection.getDirection() == 2)
			facingDirection = DirectionsNESW.WEST;
		else if ((int)xLoc + boundingBox.x <= boundingBox.x && facingDirection.getDirection() == 4)
			facingDirection = DirectionsNESW.EAST;
		else if ((int)yLoc+diameter+boundingBox.y >= boundingBox.getHeight()+boundingBox.y && facingDirection.getDirection() == 3)
			facingDirection = DirectionsNESW.NORTH;
		else if (boundingBox.getY()+(int)yLoc <= boundingBox.y && facingDirection.getDirection() == 1)
			facingDirection = DirectionsNESW.SOUTH;
		
		if (facingDirection.getDirection() == 2)
			xLoc+=speed/2.0;
		else if (facingDirection.getDirection() == 4)
			xLoc-=speed/2.0;
		else if (facingDirection.getDirection() == 3)
			yLoc+=speed/2.0;
		else if (facingDirection.getDirection() == 1)
			yLoc-=speed/2.0;
	}
/**
	Changes direction to evade another "MoveableShape" by picking a new, random direction which will is cause a collision.

	@param 	o 		The other "MoveableShape" avoiding collision.
*/
	public void evade(MoveableShape o)
	{
		Creature other = (Creature)o;
		boolean evadeSuccess = false;
		facingDirection = other.facingDirection;

		if (boundingBox.x+(int)xLoc+diameter >= boundingBox.getWidth()+boundingBox.x && facingDirection.getDirection() == 2)
			facingDirection = DirectionsNESW.SOUTH;
		else if ((int)xLoc + boundingBox.x <= boundingBox.x && facingDirection.getDirection() == 4)
			facingDirection = DirectionsNESW.NORTH;
		else if ((int)yLoc+diameter+boundingBox.y >= boundingBox.getHeight()+boundingBox.y && facingDirection.getDirection() == 3)
			facingDirection = DirectionsNESW.EAST;
		else if (boundingBox.getY()+(int)yLoc <= boundingBox.y && facingDirection.getDirection() == 1)
			facingDirection = DirectionsNESW.WEST;

		for (int i = 0; i<5; i++)
		move();
	}

/**
	Generates a random "Color" while staying away from another.

	@param 	exclude 		The "Color" to stay away from.
	@return 				A new, random "Color" which is a distance away from the excluded one.
*/
	public Color generateRandomColorwithExclusions(Color exclude)
	{
		int r=0, g=0, b=0; 
		r = random.nextInt(256);
		g = random.nextInt(256);
		b = random.nextInt(256);
		int eR = exclude.getRed(); 
		int eG = exclude.getGreen(); 
		int eB =exclude.getBlue();
		Random random = new Random();
		int d = 50;

		while(Math.abs(r-eR)<d && Math.abs(g-eG)<d && Math.abs(b-eB)<d)
		{
			if (Math.abs(r-eR)<d)
				r = random.nextInt(256);
			if 	(Math.abs(g-eG)<d)
				g = random.nextInt(256);
			if (Math.abs(b-eB)<d)
				b = random.nextInt(256);
		}
		return new Color(r, g, b);
	}

/**
	Generates a random "Color" of the same shade as another.

	@param 	color 			The "Color" to stay close to.
	@return 				A new, random "Color" which is a shade of the other one.
*/
	public Color generateRandomColorShade(Color color)
	{
		Color c = color;
		int factor = random.nextInt(5) -5;
		if (factor > 0)
			for (int i = 0; i<factor; i++)
				c = c.brighter();
		else if (factor <0)
			for (int i = 0; i>factor; i--)
				c = c.darker();
		return c;
	}

/**
	Draws the "Prey".

	@param 	g2 			Graphics2D to draw the "Prey" onto.
*/
	public void draw (Graphics2D g2)
	{
		g2.setColor(color);
		g2.fill(new Ellipse2D.Double(xDLoc-(((int)(diameter*1.25)-diameter)/2), (yDLoc-((int)((diameter*1.25)-diameter))/2), (int)(diameter*1.25), (int)(diameter*1.25)));
		g2.setColor(randColor);
		g2.fill(new Ellipse2D.Double(xDLoc, yDLoc, diameter, diameter));
		xDLoc = boundingBox.x+(int)xLoc; 
		yDLoc = boundingBox.y+(int)yLoc;
	}

}
