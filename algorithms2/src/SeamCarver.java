import java.awt.Color;

import edu.princeton.cs.algs4.Picture;

/**
 * Implementation of image re-sizing using the seam carving technique.
 * 
 * @author Stuart Shannon
 */
public class SeamCarver
{
    private static final double BORDER_PIXEL_ENERGY = 1000.0;
    private boolean vertical = true;
    private int[][] color;
    private double[][] energy;

    public SeamCarver(Picture picture) 
    {
        color = new int[picture.width()][picture.height()];
        for (int y = 0; y < height(); y++) 
        {
            for (int x = 0; x < width(); x++) 
            {
                color[x][y] = picture.get(x, y).getRGB();
            }
        }
        
        energy = new double[picture.width()][picture.height()];
        for (int y = 0; y < height(); y++) 
        {
            for (int x = 0; x < width(); x++) 
            {
                energy[x][y] = energy(x, y);
            }
        }
    }

    public Picture picture() 
    {
        Picture picture = new Picture(width(), height());

        for (int y = 0; y < height(); y++) 
        {
            for (int x = 0; x < width(); x++) 
            {
                picture.set(x, y, new Color(color[x][y]));
            }
        }

        return picture;
    }

    public int width() 
    {
        return color.length;
    }

    public int height() 
    {
        return color[0].length;
    }
    /**
     *  Energy of pixel at column x and row y.  Uses the dual gradient energy function which is:
     *  <p>
     *  The energy of pixel (x, y) is sqrt ( Δx^2(x, y) + Δy^2(x, y) ), where the square of the x-gradient 
     *  Δx^2(x, y) = Rx(x, y)^2 + Gx(x, y)^2 + Bx(x, y)^2, and where the central differences 
     *  Rx(x, y), Gx(x, y), and Bx(x, y) are the absolute value in differences of red, green, and blue 
     *  components between pixel (x + 1, y) and pixel (x − 1, y).
     *  <p>
     *  The square of the y-gradient Δy^2(x, y) is defined in an analogous manner.
     *  <p>
     *  We define the energy of pixels at the border of the image to be 1000.
     */
    public double energy(int x, int y) 
    {
        if (x < 0 || x > width() - 1) throw new IndexOutOfBoundsException("x index out of bounds, x: " + x);
        if (y < 0 || y > height() - 1) throw new IndexOutOfBoundsException("y index out of bounds, y: " + y);

        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) 
        {
            return BORDER_PIXEL_ENERGY;
        }
        
        double deltaXSquared = sumRgbDeltasSquared(new Color(color[x-1][y]), new Color(color[x+1][y]));
        double deltaYSquared = sumRgbDeltasSquared(new Color(color[x][y-1]), new Color(color[x][y+1]));
        
        double unroundedResult = Math.sqrt(deltaXSquared + deltaYSquared);
        return Math.round(unroundedResult*100.0) / 100.0;
    }

    /**
     * Square of the gradient Δ^2(x, y) = R(x, y)^2 + G(x, y)^2 + B(x, y)^2
     */
    private double sumRgbDeltasSquared(Color a, Color b) 
    {
        int red = a.getRed() - b.getRed();
        int green = a.getGreen() - b.getGreen();
        int blue = a.getBlue() - b.getBlue();
        return red * red + green * green + blue * blue;
    }

    /**
     * @return a sequence of indices for horizontal seam
     */
    public int[] findHorizontalSeam() 
    {
        if(isVerticalOrientation())
        {
            return findSeamWhenOppositeOrientation(energy);
        }

        return findSeam(energy);
    }

    /**
     * @return a sequence of indices for vertical seam
     */
    public int[] findVerticalSeam() 
    {
        if(!isVerticalOrientation())
        {
            return findSeamWhenOppositeOrientation(energy);
        }

        return findSeam(energy);
    }

    private int[] findSeamWhenOppositeOrientation(double[][] matrix)
    {
        toggleOrientation();
        return findSeam(transposeMatrix(energy));
    }

    private void toggleOrientation()
    {
        vertical = !vertical;
    }

    private boolean isVerticalOrientation()
    {
        return vertical;
    }

    private int[] findSeam(double[][] matrix)
    {
        VertexWeightedDiGraph graph = new VertexWeightedDiGraph(matrix);
        Seam seam = new Seam(graph);
        return seam.seam();
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
    public void removeVerticalSeam(int[] seam) 
    {
        validateSeam(seam);
        if (seam.length > height()) 
        {
            throw new IllegalArgumentException("Seam length must not be greater than image height.");
        }
    }

    private void validateSeam(int[] seam) 
    {
        if (seam == null) throw new NullPointerException("seam can not be null");
        if (seam.length <= 1) throw new IllegalArgumentException("Seam size must be greater than 1.");

        for (int i = 0; i < seam.length - 1; i++) 
        {
            if (Math.abs(seam[i] - seam[i + 1]) > 1) 
            {
                throw new IllegalArgumentException("Two adjacent seam entries differ by more than one.");
            }
        }
    }

    public static double[][] transposeMatrix(double [][] original)
    {
        int rows = original[0].length;
        int columns = original.length;

        double[][] transposed = new double[rows][columns];

        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                transposed[j][i] = original[i][j];
            }
        }

        return transposed;
    }
}
