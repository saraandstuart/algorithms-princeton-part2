import java.awt.Color;

/**
 * Implementation of image re-sizing using the seam carving technique.
 * 
 * @author Stuart Shannon
 */
public class SeamCarver {

    private static final double BORDER_PIXEL_ENERGY = 195075.0;
    private Picture picture;
    
    public SeamCarver(Picture picture) {
        this.picture = picture;
    }
    
    public Picture picture() {
        return picture;
    }
    
    public int width() {
        return picture.width();
    }
    
    public int height() {
        return picture.height();
    }
    /**
     *  Energy of pixel at column x and row y.
     *  Uses the dual gradient energy function which is:
     *  
     *  The energy of pixel (x, y) is Δx^2(x, y) + Δy^2(x, y), where the 
     *  square of the x-gradient Δx^2(x, y) = Rx(x, y)^2 + Gx(x, y)^2 + Bx(x, y)^2, 
     *  and where the central differences Rx(x, y), Gx(x, y), and Bx(x, y) 
     *  are the absolute value in differences of red, green, and blue 
     *  components between pixel (x + 1, y) and pixel (x − 1, y).
     */
    public double energy(int x, int y) {
        if (x < 0 || x > width() - 1) throw new IndexOutOfBoundsException("x index out of bounds, x: " + x);
        if (y < 0 || y > height() - 1) throw new IndexOutOfBoundsException("y index out of bounds, y: " + y);
        
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return BORDER_PIXEL_ENERGY;
        }
        
        double xDiff = gradient(picture.get(x - 1, y), picture.get(x + 1, y));
        double yDiff = gradient(picture.get(x, y - 1), picture.get(x, y + 1));
        return xDiff + yDiff;
    }
    
    /**
     * Square of the gradient Δ^2(x, y) = R(x, y)^2 + G(x, y)^2 + B(x, y)^2
     */
    private double gradient(Color a, Color b) {
        int red = a.getRed() - b.getRed();
        int green = a.getGreen() - b.getGreen();
        int blue = a.getBlue() - b.getBlue();
        return red * red + green * green + blue * blue;
    }
    
    public int[] findHorizontalSeam() {
        return null;
    }
    
    public int[] findVerticalSeam() {
        return null;
    }
    
    /**
     * Remove horizontal seam from current picture
     */
    public void removeHorizontalSeam(int[] seam) {
        validateSeam(seam);
        if (seam.length > width()) {
            throw new IllegalArgumentException("Seam length must not be greater than image width.");
        }
    }
    
    /**
     * Remove vertical seam from current picture
     */
    public void removeVerticalSeam(int[] seam) {
        validateSeam(seam);
        if (seam.length > height()) {
            throw new IllegalArgumentException("Seam length must not be greater than image height.");
        }
    }
    
    private void validateSeam(int[] seam) {
        if (seam == null) throw new NullPointerException("seam can not be null");
        if (seam.length <= 1) throw new IllegalArgumentException("Seam size must be greater than 1.");

        for (int i = 0; i < seam.length - 1; i++) {
            if (Math.abs(seam[i] - seam[i + 1]) > 1) {
                throw new IllegalArgumentException("Two adjacent seam entries differ by more than one.");
            }
        }
    }
    
}