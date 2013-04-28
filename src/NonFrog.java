import impsoundworld.*;

public abstract class NonFrog {
	
	int kind;
	PixelPt loc;
	
	/**
	 * Construct the Non-frog with the given information on its location and kind.
	 * @param loc the location of the Non-frog
	 * @param kind the kind (out of 5) of the Non-frog.
	 */
	NonFrog(PixelPt loc, int kind){
		this.loc = loc;
		this.kind = kind;
	}
	
	/**
	 *  the method to draw the Non-frog depending on its kind.
	 *  @return The image of the Non-frog.
	 */
	abstract WorldImage drawNonFrog();
}
