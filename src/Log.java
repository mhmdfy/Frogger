import impsoundworld.*;

public class Log extends NonFrog{
	
	/**
	 * Construct the Log and passing the arguments to the super class.
	 * @param loc the location of the log
	 * @param kind the kind (out of 5) of the log.
	 */
	Log(PixelPt loc, int kind){
		super(loc, kind);
	}
	
	/**
	 *  the method to draw the log depending on its kind.
	 *  @return The image of the log.
	 */
	public WorldImage drawNonFrog(){
		return new FromFileImage(loc, "log"+kind+".png");
	}
}
