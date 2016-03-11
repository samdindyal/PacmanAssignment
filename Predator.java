import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
/**
	Title: The "Directions" class
	Date Written: February 2014 - March 2014
	Author: Samuel Dindyal
	Description: A type of "Creature" that can eat smaller creatures and move around with a "Pacman-like" shape.
*/
public class Predator extends Creature{

	private int acceleration;
	private int counter, counter2, xC[][], yC[][];
	private Color colors[] = new Color[]{Color.BLACK, Color.YELLOW};
/**
	Creates a new "Predator".

	@param	boundingBox 	The box which bounds the movement of the "Predator".
	@param 	speed 			The speed of the "Predator".
	@param 	ranking 		The ranking/level of the "Predator".
	@param 	facingDirection	The initial direction the "Predator" is facing.
*/
	public Predator(Rectangle boundingBox, int speed, Directions facingDirection, int diameter) 
	{
		super(boundingBox, CreatureRanking.PREDATOR, speed, facingDirection, diameter);
		counter = 0;
		counter2 = 0;
		super.diameter = diameter;
	}
/**
	Triggers a change for the "Predator".
*/
	public void trigger()
	{
		if (counter == 60000)
			counter = 0;
		else if (counter2 == 60000)
			counter2 = 0;
		else
			counter++;
		if ((counter%100)%50 == 0)
			counter2++;

	}
/**
	Changes the direction of the "Predator".

	@param 	newFacingDirection 		The new facing direction of the "Predator".
*/
	public void turn(Directions newFacingDirection){super.facingDirection = newFacingDirection;}
/**
	Turns the "Predator" clockwise
*/
	public void turnClockwise()
	{
		if (super.facingDirection.getDirection() == 4)
			turn(DirectionsURDL.UP);
		else if (super.facingDirection.getDirection() == 3)
			turn(DirectionsURDL.LEFT);
		else if (super.facingDirection.getDirection() == 2)
			turn(DirectionsURDL.DOWN);
		else if (super.facingDirection.getDirection() == 1)
			turn(DirectionsURDL.RIGHT);
	}
/**
	Sets the speed of the "Predator".

	@param newSpeed 		The new speed of the "Predator".
*/
	public void setSpeed(double newSpeed){this.speed = newSpeed;}
/**
	Increases the speed of the "Predator" by 1.
*/
	public void increaseSpeed(){speed++;}
/**
	Decreases the speed of the "Predator" by 1.
*/
	public void decreaseSpeed(){speed--;}
/**
	Gets the current speed of the "Predator".

	@return 	The speed of the "Predator" as an int.
*/
	public double getSpeed(){return speed;}
/**
	Updates the coordinates of the mouth of the "Predator".
*/
	public void updateMouth()
	{
		xC = new int[][]{
				{(int)xLoc+(int)(diameter/5), (int)xLoc+(int)(2.5*diameter/5), (int)xLoc+(int)(4*diameter/5)},		//North
				{(int)xLoc+diameter, (int)xLoc+(int)(diameter/2.25), (int)xLoc+diameter}, 							//East
			 	{(int)xLoc+(int)(diameter/5), (int)xLoc+(int)(2.5*diameter/5), (int)xLoc+(int)(4*diameter/5)},		//South
			 	{(int)xLoc, (int)xLoc+(int)(diameter/1.75), (int)xLoc}												//West
			 };
		yC = new int[][]{
				{(int)yLoc, (int)yLoc+(int)(diameter/2.25), (int)yLoc},										//North
				{(int)yLoc+(int)(diameter/5), (int)yLoc+(int)(2.5*diameter/5), (int)yLoc+(int)(4*diameter/5)}, //East
			 	{(int)yLoc+diameter, (int)yLoc+(int)(diameter/2.25), (int)yLoc+diameter},						//South
			 	{(int)yLoc+(int)(diameter/5), (int)yLoc+(int)(2.5*diameter/5), (int)yLoc+(int)(4*diameter/5)}	//West
			 };
	}
/**
	Turns the "Predator" counterclockwise
*/
	public void turnCounterClockwise()
	{
		for (int i = 0; i<3; i++)
			turnClockwise();
	}

/**
	Moves the "Predator" in the direction it is currently facing.
*/
	public void move()
	{
		if ((int)xLoc+diameter >= boundingBox.getWidth() && facingDirection.getDirection() == 2
				|| (int)xLoc <= 0 && facingDirection.getDirection() == 4
				|| (int)yLoc+diameter >= boundingBox.getHeight() && facingDirection.getDirection() == 3
				|| (int)yLoc <= 0 && facingDirection.getDirection() == 1)
			return;
		
		if (facingDirection.getDirection() == 2)
			xLoc+=speed;
		else if (facingDirection.getDirection() == 4)
			xLoc-=speed;
		else if (facingDirection.getDirection() == 3)
			yLoc+=speed;
		else if (facingDirection.getDirection() == 1)
			yLoc-=speed;
	}
	

/**
	Draws the "Predator".

	@param 	g2 		The Graphics2D to draw the "Predator" onto.
*/
	public void draw(Graphics2D g2)
	{
		g2.setColor(Color.YELLOW);
		g2.fill(new Ellipse2D.Double((int)xLoc, (int)yLoc, diameter, diameter));
		updateMouth();
		if (counter2%2==0)
		{
			g2.setColor(Color.BLACK);
			g2.fillPolygon(new Polygon(xC[facingDirection.getDirection()-1], yC[facingDirection.getDirection()-1], 3));
		}
	}

}
