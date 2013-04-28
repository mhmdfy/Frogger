import impsoundworld.*;

public class Car extends NonFrog{
	
	/**
	 * Construct the car and passing the arguments to the super class.
	 * @param locthe location of the car
	 * @param kindthe kind (out of 5) of the car.
	 */
	Car(PixelPt loc, int kind){
		super(loc, kind);
	}
	
	/**
	 *  the method to draw the car depending on its kind.
	 *  @return The image of the car.
	 */
	public WorldImage drawNonFrog(){
		return new FromFileImage(loc, "car"+kind+".png");
	}
}
