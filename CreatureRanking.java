import java.util.Random;

/**
	Title: The "CreatureRanking" enumeration.
	Written: February 2014 - March 2014
	Author: Samuel Dindyal
	Description: An enumeration type representing the ranking/level of a "Creature".
*/
public enum CreatureRanking {
	LEVEL1PREY(1, "Level 1 Prey"),
	LEVEL2PREY(2, "Level 2 Prey"),
	LEVEL3PREY(3, "Level 3 Prey"),
	PREDATOR(4, "Predator");
	
	private final int rank;
	private final String description;
/**
	Creates a ranking with define parameters.

	@param rank 		The ranking as an int.
	@param decription 	The description as a String.
*/
	CreatureRanking(int rank, String description)
	{
		this.rank = rank;
		this.description = description;
	}
/**
	Returns the description of the "CreatureRanking".

	@return 		The description of the "CreatureRanking".
*/
	public String toString()
	{
		return description;
	}
/**
	Returns the integer representation of the "CreatureRanking".

	@return 		The integer representation of the "CreatureRanking".
*/
	public int getRanking()
	{
		return rank;
	}

/**
	Creates a random "Prey" rank.

	@return 	A random "CreatureRank" that is a "Prey".
*/
	public static CreatureRanking randomPreyRank()
	{
		Random random = new Random();
		int chosen = random.nextInt(3) + 1;
		if (chosen == 1)
			return LEVEL1PREY;
		else if (chosen == 2)
			return LEVEL2PREY;
		else
			return LEVEL3PREY;
	}
}
