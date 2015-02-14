import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
Title: The "Creature" class
Written: February 2014 - March 2014
Author: Samuel Dindyal
Description: A class for an object of a "Creature" with a direction, diameter, bounding box, ranking and speed. This object uses Graphics2D to draw itself 
and move around.
*/
public class Creature implements MoveableShape {

	protected Directions facingDirection;
	protected int diameter; 
	protected double xLoc, yLoc, speed;
	protected CreatureRanking ranking;
	protected Rectangle boundingBox;
	protected Graphics2D g2;
/**
	Creates a "Creature" with defined values.

	@param	boundingBox 	The box which bounds the movement of the creature.
	@param 	ranking 		The ranking/level of the creature.
	@param 	speed 			The speed of the creature.
	@param 	facingDirection	The initial direction the creature is facing.
	@param 	diameter		The diameter of the creature, excluding the bounding box.
*/
	public Creature(Rectangle boundingBox, CreatureRanking ranking, int speed, Directions facingDirection, int diameter)
	{
		this.boundingBox = boundingBox;
		this.ranking = ranking;
		this.speed = speed;
		this.facingDirection = facingDirection;
		this.diameter = diameter;
		xLoc=0;
		yLoc=0;
	}
/**
	Moves the creature one unit in each domain.
*/
	public void move()
	{
		xLoc++;
		yLoc++;
	}

/**
	Checks if the creature has collided with another "MoveableShape".

	@param 	other 		The other shape being tested for collision.
*/
	public boolean collide(MoveableShape other) 
	{
		Creature o = (Creature)other;
		return getDistance(other)<(diameter +o.diameter)/2.0;
	}

/**
	Returns the distance away from another "MoveableShape".

	@param 	other 		The other shape being measured for distance.
	@return 			A double representation of the distance the other "MoveableShape" is.
*/
	public double getDistance(MoveableShape other)
	{
		Creature o = (Creature)other;
		int dx = getCenterX() - o.getCenterX(); 
		int dy = getCenterY() - o.getCenterY();
		return Math.sqrt(Math.pow(dx,  2) + Math.pow(dy,2));		
	}

/**
	Changes the speed of the creature to a new speed.

	@param 	newSpeed 	The need speed of the "Creature".
*/
	public void setSpeed(int newSpeed)
	{
		this.speed = newSpeed;
	}
	/**
	Returns the value of the X pixel in the center of the "Creature", not the bounding box.

	@return 	The center X pixel of the "Creature".
	*/
	public int getCenterX(){return (int)(boundingBox.getX() + xLoc+ (diameter/2.0));}
	/**
	Returns the value of the Y pixel in the center of the "Creature", not the bounding box.

	@return 	The center Y pixel of the "Creature".
	*/	
	public int getCenterY(){return (int)(boundingBox.getY() + yLoc+ (diameter/2.0));}
		/**
	Returns the radius of the "Creature".

	@return 	The radius of the "Creature", not the bounding box.
	*/
	public double getRadius(){return diameter/2.0;}
	/**
	Draws the "Creature".

	@param 	g2 		The Graphics2D to be drawn on.
	*/
	public void draw(Graphics2D g2) {
		this.g2 = g2;

	}

}
