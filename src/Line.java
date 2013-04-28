import java.util.*;
import impsoundworld.*;

// a line of non frogs objects.
public class Line {
	ArrayList<NonFrog> list;
	int width;
	int row;
	int speed;
	int yLoc;
	boolean isMovingRight;
	
	/**
	 * Construct the line with the given appropriate row.
	 * 
	 * @param rowthe row which the line is going to be on.
	 * @throws RuntimeException if the row isn't acceptable.
	 */
	Line(int row){
		list = new ArrayList<NonFrog>();
		this.row = row;
		
		// checks to see which line is this and initialize it accordingly.
		if(row == 1)
			initializeLine1();
		else if(row == 2)
			initializeLine2();
		else if(row == 3)
			initializeLine3();
		else if(row == 4)
			initializeLine4();
		else if(row == 5)
			initializeLine5();
		else if(row == 6)
			initializeLine6();
		else if(row == 7)
			initializeLine7();
		else if(row == 8)
			initializeLine8();
		else if(row == 9)
			initializeLine9();
		else if(row == 10)
			initializeLine10();
		else
			throw new RuntimeException("invalid row");
		
	}
	
	 //Initializing methods for each line.
	
	/**
	 *  initialize the first row of cars.
	 */
	public void initializeLine1(){
		width = 40;
		yLoc = 625;
		speed = 10;
		isMovingRight = false;
		list.add(new Car(new PixelPt(525, yLoc), 1));
		list.add(new Car(new PixelPt(325, yLoc), 1));
		list.add(new Car(new PixelPt(125, yLoc), 1));
	}
	
	/**
	 * initialize the second row of cars.
	 */
	public void initializeLine2(){
		width = 40;
		yLoc = 575;
		speed = 15;
		isMovingRight = true;
		list.add(new Car(new PixelPt(425, yLoc), 2));
		list.add(new Car(new PixelPt(225, yLoc), 2));
		list.add(new Car(new PixelPt(25, yLoc), 2));
	}
	
	/**
	 *  initialize the third row of cars.
	 */
	public void initializeLine3(){
		width = 40;
		yLoc = 525;
		speed = 30;
		isMovingRight = false;
		list.add(new Car(new PixelPt(525, yLoc), 3));
		list.add(new Car(new PixelPt(125, yLoc), 3));
	}
	
	/**
	 *  initialize the forth row of cars.
	 */
	public void initializeLine4(){
		width = 40;
		yLoc = 475;
		speed = 20;
		isMovingRight = true;
		list.add(new Car(new PixelPt(525, yLoc), 4));
		list.add(new Car(new PixelPt(325, yLoc), 4));
		list.add(new Car(new PixelPt(125, yLoc), 4));
	}
	
	/**
	 *  initialize the fifth row of cars.
	 */
	public void initializeLine5(){
		width = 60;
		yLoc = 425;
		speed = 10;
		isMovingRight = false;
		list.add(new Car(new PixelPt(525, yLoc), 5));
		list.add(new Car(new PixelPt(325, yLoc), 5));
		list.add(new Car(new PixelPt(125, yLoc), 5));
	}
	
	/**
	 *  initialize the first row of logs
	 */
	public void initializeLine6(){
		width = 125;
		yLoc = 325;
		speed = 10;
		isMovingRight = true;
		list.add(new Log(new PixelPt(525, yLoc), 1));
		list.add(new Log(new PixelPt(125, yLoc), 1));
	}
	
	/**
	 *  initialize the second row of logs
	 */
	public void initializeLine7(){
		width = 100;
		yLoc = 275;
		speed = 16;
		isMovingRight = false;
		list.add(new Log(new PixelPt(425, yLoc), 2));
		list.add(new Log(new PixelPt(225, yLoc), 2));
		list.add(new Log(new PixelPt(25, yLoc), 2));
	}
	
	/**
	 *  initialize the third row of logs
	 */
	public void initializeLine8(){
		width = 200;
		yLoc = 225;
		speed = 13;
		isMovingRight = true;
		list.add(new Log(new PixelPt(325, yLoc), 3));
	}
	
	/**
	 *  initialize the forth row of logs
	 */
	public void initializeLine9(){
		width = 60;
		yLoc = 175;
		speed = 20;
		isMovingRight = false;
		list.add(new Log(new PixelPt(525, yLoc), 4));
		list.add(new Log(new PixelPt(325, yLoc), 4));
		list.add(new Log(new PixelPt(125, yLoc), 4));
	}
	
	/**
	 *  initialize the fifth row of logs
	 */
	public void initializeLine10(){
		width = 95;
		yLoc = 125;
		speed = 25;
		isMovingRight = true;
		list.add(new Log(new PixelPt(525, yLoc), 5));
		list.add(new Log(new PixelPt(325, yLoc), 5));
		list.add(new Log(new PixelPt(125, yLoc), 5));
	}
	
	/**
	 *  draw all the Non frogs in the line.
	 *  
	 * @return the image of the items in the line.
	 */
	public WorldImage drawLine(){
		WorldImage drawn = list.get(0).drawNonFrog();
		int c = 1;
		while(c < list.size()){
			drawn = drawn.overlayImages(list.get(c).drawNonFrog());
			c++;
		}
		return drawn;
	}
	
	/**
	 *  move all the non frogs in the line according to their direction.
	 *  if the non frog reaches the end of the map it returns again to the start.
	 */
	public void moveAll(){
		for(NonFrog n: list){
			if(isMovingRight){
				n.loc.moveRight(speed);
				if(n.loc.x > 700+width)
					n.loc = new PixelPt(0-width, yLoc);
			}
			else{
				n.loc.moveLeft(speed);
				if(n.loc.x < 0-width)
					n.loc = new PixelPt(650+width, yLoc);
			}
		}
	}
	
	/**
	 *  did the non-frog and the given frog impacted together?
	 *  
	 * @param f the given frog.
	 * @return true if the frog impacted the item, false otherwise.
	 */
	public boolean impact(Frog f){
		for(NonFrog n: list)
			if(f.loc.inSameGrid(n.loc, width))
				return true;
		return false;
	}
}
