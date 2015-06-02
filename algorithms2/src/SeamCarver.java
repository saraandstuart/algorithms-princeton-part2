/**
 * Implementation of image re-sizing using the seam carving technique.
 * 
 * @author Stuart Shannon
 */
public class SeamCarver {

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
     *  Energy of pixel at column x and row y
     */
    public double energy(int x, int y) {
        if (x < 0 || x > width() - 1) throw new IndexOutOfBoundsException("x index out of bounds, x: " + x);
        if (y < 0 || y > height() - 1) throw new IndexOutOfBoundsException("y index out of bounds, y: " + y);
        
        
        return -1d;
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
