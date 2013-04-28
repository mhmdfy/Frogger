// Assignment 13 Final Project
// Partner Mohammad Al Yahya
// mhmdfy
// Haithem Albetairi
// albeht0a
// 18 April 2012

import tester.*;
import impsoundworld.*;
import java.util.*;

public class ExamplesProject {
	
	// testing methods for PixelPt
	void testPixelPt(Tester t){
		PixelPt exPosn = new PixelPt(0, 0);
		
		exPosn.moveRight(5);
		t.checkExpect(exPosn, new PixelPt(5, 0));
		t.checkExpect(exPosn.x, 5);
		t.checkExpect(exPosn.y, 0);
		
		exPosn.moveLeft(5);
		t.checkExpect(exPosn, new PixelPt(0, 0));
		t.checkExpect(exPosn.x, 0);
		t.checkExpect(exPosn.y, 0);
		
		exPosn.moveDown(5);
		t.checkExpect(exPosn, new PixelPt(0, 5));
		t.checkExpect(exPosn.x, 0);
		t.checkExpect(exPosn.y, 5);
		
		exPosn.moveUp(5);
		t.checkExpect(exPosn, new PixelPt(0, 0));
		t.checkExpect(exPosn.x, 0);
		t.checkExpect(exPosn.y, 0);
		
		t.checkExpect(exPosn.inSameGrid(new PixelPt(1, 0), 5), true);
		t.checkExpect(exPosn.inSameGrid(new PixelPt(1, 0), 1), false);
		t.checkExpect(exPosn.inSameGrid(new PixelPt(1, 1), 1), false);
	}
	
	// test methods in Frog.
	void testFrog(Tester t){
		Frog exFrog = new Frog(new PixelPt(0, 0), 3);
		
		t.checkExpect(exFrog.drawFrog(), new FromFileImage(exFrog.loc, "frog.png"));
		t.checkExpect(exFrog.drawLife(0), new FromFileImage(new PixelPt(625, 25), "lives.png"));
		t.checkExpect(exFrog.drawLife(1), new FromFileImage(new PixelPt(600, 25), "lives.png"));
		t.checkExpect(exFrog.drawLife(2), new FromFileImage(new PixelPt(575, 25), "lives.png"));
		
		exFrog.die();
		t.checkExpect(exFrog.lives, 2);
		exFrog.die();
		t.checkExpect(exFrog.lives, 1);
		
		exFrog.startRespawn();
		t.checkExpect(exFrog.loc, new PixelPt(325, 675));
		exFrog.midRespawn();
		t.checkExpect(exFrog.loc, new PixelPt(325, 375));
		
		t.checkExpect(exFrog.canMoveUp(), true);
		exFrog.moveUp(375);
		t.checkExpect(exFrog.loc, new PixelPt(325, 0));
		t.checkExpect(exFrog.canMoveUp(), false);
		exFrog.moveUp(50);
		t.checkExpect(exFrog.loc, new PixelPt(325, 0));
	}
	
	// test methods in NonFrogs
	void testNonFrog(Tester t){
		NonFrog exCar1 = new Car(new PixelPt(0,0), 1);
		NonFrog exCar2 = new Car(new PixelPt(500, 500), 2);
		NonFrog exLog1 = new Log(new PixelPt(300, 300), 3);
		NonFrog exLog2 = new Log(new PixelPt(150, 150), 4);
		
		t.checkExpect(exCar1.drawNonFrog(), new FromFileImage(exCar1.loc, "car1.png"));
		t.checkExpect(exCar2.drawNonFrog(), new FromFileImage(exCar2.loc, "car2.png"));
		t.checkExpect(exLog1.drawNonFrog(), new FromFileImage(exLog1.loc, "log3.png"));
		t.checkExpect(exLog2.drawNonFrog(), new FromFileImage(exLog2.loc, "log4.png"));
		
	}
	
	// test methods in Line
	void testLine(Tester t){
		Line exLine1 = new Line(1);
		Line exLine2 = new Line(6);
		
		ArrayList<NonFrog> carList = new ArrayList<NonFrog>();
		carList.add(new Car(new PixelPt(525, 625), 1));
		carList.add(new Car(new PixelPt(325, 625), 1));
		carList.add(new Car(new PixelPt(125, 625), 1));
		t.checkExpect(exLine1.list, carList);
		t.checkExpect(exLine1.width, 40);
		t.checkExpect(exLine1.speed, 10);
		t.checkExpect(exLine1.isMovingRight, false);
		
		ArrayList<NonFrog> logList = new ArrayList<NonFrog>();
		logList.add(new Log(new PixelPt(525, 325), 1));
		logList.add(new Log(new PixelPt(125, 325), 1));
		t.checkExpect(exLine2.list, logList);
		t.checkExpect(exLine2.width, 125);
		t.checkExpect(exLine2.speed, 10);
		t.checkExpect(exLine2.isMovingRight, true);
		
		exLine1.moveAll();
		t.checkExpect(exLine1.list.get(0), new Car(new PixelPt(515, 625), 1));
		t.checkExpect(exLine1.list.get(1), new Car(new PixelPt(315, 625), 1));
		t.checkExpect(exLine1.list.get(2), new Car(new PixelPt(115, 625), 1));
		
		exLine2.moveAll();
		t.checkExpect(exLine2.list.get(0), new Log(new PixelPt(535, 325), 1));
		t.checkExpect(exLine2.list.get(1), new Log(new PixelPt(135, 325), 1));
		
		Frog exFrog1 = new Frog(new PixelPt(475, 325), 3);
		Frog exFrog2 = new Frog(new PixelPt(530, 625), 3);
		t.checkExpect(exLine1.impact(exFrog1), false);
		t.checkExpect(exLine2.impact(exFrog1), true);
		t.checkExpect(exLine1.impact(exFrog2), true);
		t.checkExpect(exLine2.impact(exFrog2), false);
	}
	
	// test methods in Frogger.
	void testFrogger(Tester t){
		Frogger exFrogger = new Frogger();
		exFrogger.onKeyEvent("up");
		t.checkExpect(exFrogger.frog, new Frog(new PixelPt(325, 625), 3));
		exFrogger.onKeyEvent("down");
		t.checkExpect(exFrogger.frog, new Frog(new PixelPt(325, 675), 3));
		exFrogger.onKeyEvent("left");
		t.checkExpect(exFrogger.frog, new Frog(new PixelPt(275, 675), 3));
		exFrogger.onKeyEvent("right");
		t.checkExpect(exFrogger.frog, new Frog(new PixelPt(325, 675), 3));
		exFrogger.onKeyEvent("badstring");
		t.checkExpect(exFrogger.frog, new Frog(new PixelPt(325, 675), 3));
		
		
		
	}
	// runs the game.
	void testRunWorld(Tester t){
		Frogger game = new Frogger();
		game.bigBang(650, 700, 0.2);
	}
}