import java.util.Random;

/**
	Title: The "DirectionsNESW" enumeration.
	Date Written: February 2014 - March 2014
	Author: Samuel Dindyal
	Description: An enumeration type to represent directions in terms of North, East, South and West, which implements "Directions".
*/
public enum DirectionsNESW implements Directions{
	
	NORTH("North", 1),
	EAST("East", 2),
	SOUTH("South", 3),
	WEST("West", 4);
	
	
	private final String direction;
	private final int universalDirection;
/**
	Creates new Direction.

	@param 	direction 				A String representation of the direction.
	@param universalDirection 		An int representation of the direction.
*/
	DirectionsNESW(String direction, int universalDirection)
	{
		this.direction = direction;
		this.universalDirection = universalDirection;
	}
/**
	Gets the integer representation of the direction.

	@return 		The direction as an int.
*/
	public int getDirection()
	{
		return universalDirection;
	}
	
/**
	Gets the String representation of the direction.

	@return 		The direction as a String.
*/
	public String toString()
	{
		return direction;
	}

/**
	Generates a random direction.

	@return 	A random direction.
*/	
	public static Directions getRandomDirection()
	{
		int chosen = new Random().nextInt(4) + 1;
		if (chosen == 1)
			return NORTH;
		else if (chosen == 2)
			return EAST;
		else if (chosen == 3)
			return SOUTH;
		else 
			return WEST;
	}

}
