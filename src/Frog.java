import impsoundworld.*;
import java.awt.*;

public class Frog {
	PixelPt loc;
	int lives;
	
	/**
	 * Construct the frog with the given location and lives
	 * 
	 * @param loc the location of the frog
	 * @param lives the lives of the frog
	 */
	Frog(PixelPt loc, int lives){
		this.loc = loc;
		this.lives = lives;
	}
	
	/**
	 *  draws the image of the frog.
	 * @return the image of the frog.
	 */
	public WorldImage drawFrog(){
		return new FromFileImage(loc, "frog.png");
	}
	
	/**
	 *  draws the image one of the lives of the frog in the correct position.
	 * @param i the number of lives remaining.
	 * @return the image of the lives in the correct position.
	 */
	public WorldImage drawLife(int i){
		return new FromFileImage(new PixelPt(625-(25*i), 25), "lives.png");
	}
	/**
	 * draws the image of the remaining lives of the frog.
	 * @return The image of the lives of the frog to be drawn.
	 */
	public WorldImage drawLives(){
		int c = lives-1;
		WorldImage drawn = new RectangleImage(new PixelPt(625, 25), 50, 50, Color.white);
		while(c >= 0){
			drawn = drawn.overlayImages(drawLife(c));
			c--;
		}
		return drawn;
	}
	
	 /**
	  *  Move the Frog to the left by the given amount in normal measures.
	  * @param dx the amount to be moved
	  */
	  public void moveLeft(Integer dx) {
		  if(canMoveLeft())
			  loc.moveLeft(dx);
	  }
	 /**
	  *  Move the Frog to the right by the given amount in normal measures.
	  * @param dx the amount to be moved
	  */
	  public void moveRight(Integer dx) {
		  if(canMoveRight())
			  loc.moveRight(dx);
	  }
	  /**
	   *  Move the Frog upwards by the given amount in normal measures.
	   * @param dy the amount to be moved
	   */
	  public void moveUp(Integer dy) {
		  if(canMoveUp())
			  loc.moveUp(dy);
	  }
	  /**
	   *  Move the Frog downwards by the given amount in normal measures.
	   * @param dy the amount to be moved
	   */
	  public void moveDown(Integer dy) {
		  if(canMoveDown())
			  loc.moveDown(dy);
	  }
	  
	  /**
	   *  can the frog move to the left?
	   * @return true if the frog can move, false otherwise.
	   */
	  public boolean canMoveLeft(){
		  return loc.x > 50;
	  }
	  /**
	   *  can the frog move to the right?
	   * @return true if the frog can move, false otherwise.
	   */
	  public boolean canMoveRight(){
		  return loc.x < 600;
	  }
	  /**
	   *  can the frog move to upwards?
	   * @return true if the frog can move, false otherwise.
	   */
	  public boolean canMoveUp(){
		  return loc.y > 50;
	  }
	  /**
	   * can the frog move to the downwards?
	   * @return true if the frog can move, false otherwise.
	   */
	  public boolean canMoveDown(){
		  return loc.y < 650;
	  }
	
	/**
	 *  the frog dies; his lives decrease by one.
	 */
	public void die(){
		lives--;
	}
	
	/**
	 *  the frog respawn again at the start of the map.
	 */
	public void startRespawn(){
		loc = new PixelPt(325, 675);
	}
	
	/**
	 *  the frog respawn again at mid way throgh the map.
	 */
	public void midRespawn(){
		loc = new PixelPt(325, 375);
	}
	
}
