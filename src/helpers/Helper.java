package helpers;

public class Helper {
	
	/**
	 * To populate an 2D array with a value
	 * @param array
	 * @param value
	 */
    public static void populate(IDrawable[][] array, IDrawable value) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = value;
            }
        }
    }
}
