import impsoundworld.*;
import geometry.*;
import tunes.*;

import java.awt.*;
import java.util.*;

/** 
 * @author mhmdfy
 * Extending the Posn class to add new methods.
 * 
 */
class PixelPt extends Posn {

	/**
	 * Construct the PixelPt and pass the arguments to Super class.
	 * @param x x-coordinate of the posn
	 * @param y y-coordinate of the posn
	 */
	PixelPt(Integer x, Integer y) {
		super(x, y);
	}
	  
	/**
	 *  Move the Posn to the left by the given amount in normal measures.
	 * @param dx the amount to be moved.
	 */
	public void moveLeft(Integer dx) {
		x = x-dx;
	}
	/**
	 *  Move the Posn to the Right by the given amount in normal measures.
	 * @param dx the amount to be moved.
	 */
	public void moveRight(Integer dx) {
		x = x+dx;
	}
	/**
	 *  Move the Posn upwards by the given amount in normal measures.
	 * @param dy the amount to be moved.
	 */
	public void moveUp(Integer dy) {
		y = y-dy;
	}
	/**
	 *  Move the Posn downwards by the given amount in normal measures.
	 * @param dy the amount to be moved.
	 */
	public void moveDown(Integer dy) {
		y = y+dy;
	}
	/**
	 * is this posn in same grid as the other posn with the given width?
	 * @param other the other posn to be compared with.
	 * @param width the width of the object with the other posn.
	 * @return true if they would be in the same grid, false otherwise.
	 */
	public boolean inSameGrid(Posn other, int width){
		return x <= other.x+width/2 && x >= other.x-width/2 
				&& y == other.y;
	}
}

/**
 * @author mhmdfy
 * Frogger is the game itself and it extends World class.
 *
 */
public class Frogger extends World {

	Frog frog = new Frog(new PixelPt(325, 675), 3);
	ArrayList<Note> notes = new ArrayList<Note>();
	int noteCounter = 0;
	String lastMessage = "";
	
	/**
	 *  initializing background
	 */
	WorldImage bi = new RectangleImage(new PixelPt(325, 375), 650, 650, Color.blue);
	WorldImage gi1 = new RectangleImage(new PixelPt(325, 675), 650, 50, Color.green);
	WorldImage gi2 = new RectangleImage(new PixelPt(325, 375), 650, 50, Color.green);
	WorldImage gi3 = new RectangleImage(new PixelPt(325, 75), 650, 50, Color.green);
	WorldImage si = new RectangleImage(new PixelPt(325, 525), 650, 250, Color.black);
	WorldImage background = bi.overlayImages(si, gi1, gi2, gi3);
	
	/**
	 *  initializing Car lines
	 */
	Line cl1 = new Line(1);
	Line cl2 = new Line(2);
	Line cl3 = new Line(3);
	Line cl4 = new Line(4);
	Line cl5 = new Line(5);
	
	/**
	 *  initializing Log lines
	 */
	Line ll1 = new Line(6);
	Line ll2 = new Line(7);
	Line ll3 = new Line(8);
	Line ll4 = new Line(9);
	Line ll5 = new Line(10);
	
	ArrayList<Line> lines = new ArrayList<Line>();
	
	Frogger(){
		// adding lines to the list.
		lines.add(cl1);
		lines.add(cl2);
		lines.add(cl3);
		lines.add(cl4);
		lines.add(cl5);
		
		lines.add(ll1);
		lines.add(ll2);
		lines.add(ll3);
		lines.add(ll4);
		lines.add(ll5);
		
		// making the musical notes.
		createSong();
	}
	
	/** 
	 * Makes the musical notes and add them to the list.
	 * This is going to be played while the game is running.
	 * 
	 */
	public void createSong(){
		
		Note d5 = new Note("D5n1");
		Note e5 = new Note("E5n1");
		Note f5 = new Note("F5s1");
		Note g5 = new Note("G5n1");
		Note a6 = new Note("A6n1");
		Note a5 = new Note("A5n1");
		Note mt = new Note("C1n0");
		
		notes.add(d5);
		notes.add(e5);
		notes.add(f5);
		notes.add(g5);
		notes.add(a6);
		notes.add(mt);
		notes.add(f5);
		notes.add(mt);
		notes.add(d5);
		notes.add(e5);
		notes.add(f5);
		notes.add(e5);
		notes.add(d5);
		notes.add(mt);
		notes.add(d5);
		notes.add(e5);
		notes.add(f5);
		notes.add(g5);
		notes.add(a6);
		notes.add(mt);
		notes.add(f5);
		notes.add(mt);
		notes.add(a5);
		notes.add(g5);
		notes.add(f5);
		notes.add(e5);
		notes.add(d5);
		
	}
	
	/**
	 * 
	 *  produce the image of the whole world -
	 *  including the cars and the logs.
	 *  
	 *  @return the image to be displayed.
	 */
	public WorldImage makeImage() {
		
		WorldImage fi = frog.drawFrog();
		WorldImage li = frog.drawLives();
		WorldImage l1 = cl1.drawLine();
		WorldImage l2 = cl2.drawLine();
		WorldImage l3 = cl3.drawLine();
		WorldImage l4 = cl4.drawLine();
		WorldImage l5 = cl5.drawLine();
		WorldImage l6 = ll1.drawLine();
		WorldImage l7 = ll2.drawLine();
		WorldImage l8 = ll3.drawLine();
		WorldImage l9 = ll4.drawLine();
		WorldImage l10 = ll5.drawLine();
		return background.overlayImages(li, l6, l7, l8, l9, l10, fi, l1, l2, l3, l4, l5);
	}
	
	/**
	 *  What happens each tick
	 *  includes the road phase, the ocean phase and the winning condition.
	 */
	public void onTick(){
		
		tickTunes.addNote(0, notes.get(noteCounter));
		noteCounter = (noteCounter + 1) % notes.size();
		// Everything moves.
		for(Line l : lines){
			l.moveAll();
		}
		
		
		// road phase
		if(frog.loc.y > 350)
			roadPhase();
		
		// ocean phase
		else if (frog.loc.y <= 350 && frog.loc.y >= 125)
			oceanPhase();
		
		// winning condition.
		if(frog.loc.y < 125){
			lastMessage = "Congratulation, \nYou WIN!";
			this.endOfWorld("You WIN!");
		}
	}
	
	public WorldEnd WorldEnds(){
			return new WorldEnd(true, lastImage("You WIN!"));
	}
	
	/**
	 *  Indicates the first phase of the game (on the road).
	 *  the frog dies if was crushed by a car and respawn if has lives.
	 */
	public void roadPhase(){
		if(cl1.impact(frog) || cl2.impact(frog) || cl3.impact(frog) || cl4.impact(frog) || cl5.impact(frog)){
			if(frog.lives > 0){
				frog.die();
				frog.startRespawn();
			}
			else {
				lastMessage = "Sorry, \nYou Lose";
				endOfWorld("You Lose");
			}
		}
	}
	
	/**
	 *  Indicates the second phase of the game (on the ocean)
	 *  the frog dies if was drowned in water and respawn if has lives.
	 *  if the frog was on a log, it moves with it.
	 */
	public void oceanPhase(){
		if(ll1.impact(frog))
			frog.moveRight(ll1.speed);
		else if(ll2.impact(frog))
			frog.moveLeft(ll2.speed);
		else if(ll3.impact(frog))
			frog.moveRight(ll3.speed);
		else if(ll4.impact(frog))
			frog.moveLeft(ll4.speed);
		else if(ll5.impact(frog))
			frog.moveRight(ll5.speed);
		else if(frog.loc.y <= 350){
			if(frog.lives > 0){
				frog.die();
				frog.midRespawn();
			}
			else {
				lastMessage = "Sorry, \nYou Lose";
				endOfWorld("You Lose");
			}
		}
	}
	
	/**
	 *  last image to be displayed when the game ends.
	 *  
	 *  @return The image to be displayed.
	 */
	public WorldImage lastImage(String s){
		return makeImage().overlayImages(new TextImage(new PixelPt(325, 350), lastMessage, 50, Color.red));
	}
	
	/**
	 *  Control with keys to move the frog.
	 */
	public void onKeyEvent(String key){
		if(key.equals("up"))
			frog.moveUp(50);
		else if(key.equals("down"))
			frog.moveDown(50);
		else if(key.equals("right"))
			frog.moveRight(50);
		else if(key.equals("left"))
			frog.moveLeft(50);
		// else do nothing.
	}

}
