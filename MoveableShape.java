import java.awt.Graphics2D;

public interface MoveableShape 
{ 
	void move(); 
	boolean collide(MoveableShape other); 
	void draw(Graphics2D g2); 
} 