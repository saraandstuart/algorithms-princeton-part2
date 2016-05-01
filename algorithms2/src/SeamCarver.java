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
    private boolean colorVertical = true;
    private int[][] color;
    private boolean energyVertical = true;
    private double[][] energy;

    public SeamCarver(Picture picture) 
    {
        color = new int[picture.width()][picture.height()];
        energy = new double[picture.width()][picture.height()];

        for (int x = 0; x < width(); x++) 
        {
            for (int y = 0; y < height(); y++) 
            {
                color[x][y] = picture.get(x, y).getRGB();
            }
        }

        calculateEnergies();
    }
    
    private void calculateEnergies()
    {
        for (int x = 0; x < width(); x++) 
        {
            for (int y = 0; y < height(); y++) 
            {
                energy[x][y] = energy(x, y);
            }
        }
    }

    public Picture picture() 
    {
        orientateVertically();
        Picture picture = new Picture(width(), height());

        for (int x = 0; x < width(); x++) 
        {
            for (int y = 0; y < height(); y++) 
            {
                picture.set(x, y, new Color(color[x][y]));
            }
        }

        return picture;
    }
    
    private void orientateVertically()
    {
        if (!isEnergyVerticalOrientation())
        {
            toggleAndTransposeEnergy();
        }
        
        if (!isColorVerticalOrientation())
        {
            toggleAndTransposeColor();
        }
    }
    
    private void orientateHorizontally()
    {
        if (isEnergyVerticalOrientation())
        {
            toggleAndTransposeEnergy();
        }
        
        if (isColorVerticalOrientation())
        {
            toggleAndTransposeColor();
        }
    }
    
    private void toggleAndTranspose()
    {
        toggleAndTransposeEnergy();
        toggleAndTransposeColor();
    }
    
    private void toggleAndTransposeEnergy()
    {
        toggleEnergyOrientation();
        energy = transposeMatrix(energy);
    }
    
    private void toggleAndTransposeColor()
    {
        toggleColorOrientation();
        color = transposeMatrix(color);
    }
    
    private void toggleColorOrientation()
    {
        colorVertical = !colorVertical;
    }

    private boolean isColorVerticalOrientation()
    {
        return colorVertical;
    }

    public int width() 
    {
        orientateVertically();
        return color.length;
    }

    public int height() 
    {
        orientateVertically();
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

//        double unroundedResult = Math.sqrt(deltaXSquared + deltaYSquared);
//        return Math.round(unroundedResult*100.0) / 100.0;
        return Math.sqrt(deltaXSquared + deltaYSquared);
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
     *         an array of length W such that entry x is 
     *         the row number of the pixel to be removed 
     *         from column x of the image.
     */
    public int[] findHorizontalSeam() 
    {
        if (isEnergyVerticalOrientation())
        {
            toggleAndTransposeEnergy();
        }

        return findSeam(energy);
    }

    /**
     * @return a sequence of indices for vertical seam
     *         an array of length H such that entry y is 
     *         the column number of the pixel to be removed 
     *         from row y of the image.
     */
    public int[] findVerticalSeam() 
    {
        if (!isEnergyVerticalOrientation())
        {
            toggleAndTransposeEnergy();
        }

        return findSeam(energy);
    }

    private void toggleEnergyOrientation()
    {
        energyVertical = !energyVertical;
    }

    private boolean isEnergyVerticalOrientation()
    {
        return energyVertical;
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
        
        orientateVertically();
        removeSeam(seam);
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

        orientateHorizontally();
        removeSeam(seam);
    }
    
    private void removeSeam(int[] seam)
    {
        for (int row = 0; row < seam.length; row++)
        {
            int col = seam[row];
            color[row] = removeElementByIndex(color[row], col);
            energy[row] = removeElementByIndex(energy[row], col);
        }
        
        toggleAndTranspose();
        // TODO (SS) - try optimisation to only update energy matrix for those energies along the seam.
        calculateEnergies();
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

    private static double[][] transposeMatrix(double [][] original)
    {
        int width = original[0].length;
        int height = original.length;

        double[][] transposed = new double[width][height];

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                transposed[j][i] = original[i][j];
            }
        }

        return transposed;
    }
    
    private static int[][] transposeMatrix(int [][] original)
    {
        int width = original[0].length;
        int height = original.length;

        int[][] transposed = new int[width][height];

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                transposed[j][i] = original[i][j];
            }
        }

        return transposed;
    }

    private static int[] removeElementByIndex(int[] input, int index)
    {
        int[] result = new int[input.length - 1];

        System.arraycopy(
                input, 
                0, 
                result, 
                0, 
                index);
        
        System.arraycopy(
                input,
                index + 1,
                result,
                index,
                input.length - index - 1);

        return result;
    }
    
    private static double[] removeElementByIndex(double[] input, int index)
    {
        double[] result = new double[input.length - 1];

        System.arraycopy(
                input, 
                0, 
                result, 
                0, 
                index);
        
        System.arraycopy(
                input,
                index + 1,
                result,
                index,
                input.length - index - 1);

        return result;
    }
}
