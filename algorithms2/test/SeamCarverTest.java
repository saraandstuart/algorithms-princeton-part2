import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import edu.princeton.cs.algs4.Picture;

/**
 * {@link SeamCarver} unit test
 * 
 * @author Stuart Shannon
 */
public class SeamCarverTest {

    @Test(expected=IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionForColumnIndexGreaterThanWidthMinusOne() {
        //given
        Picture inputImg = new Picture("seamCarving/3x7.png");
        SeamCarver sc = new SeamCarver(inputImg);
        int height = sc.height();
        int width = sc.width();
        //when
        sc.energy(width, height-1);
        //then exception
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionForRowIndexGreaterThanHeightMinusOne() {
        //given
        Picture inputImg = new Picture("seamCarving/4x6.png");
        SeamCarver sc = new SeamCarver(inputImg);
        int height = sc.height();
        int width = sc.width();
        //when
        sc.energy(width-1, height);
        //then exception
    }
    
    @Test
    public void test3x7PixelsEnergy() {
        double[][] expectedEnergy = new double[][]{
                { 1000.00, 1000.00, 1000.00 },  
                { 1000.00, 294.32, 1000.00 }, 
                { 1000.00, 236.17, 1000.00 }, 
                { 1000.00, 325.15, 1000.00 },
                { 1000.00, 251.36, 1000.00 },
                { 1000.00, 279.64, 1000.00 },
                { 1000.00, 1000.00, 1000.00 }
        };
        
        testEnergy(expectedEnergy, "seamCarving/3x7.png");
    }
    
    @Test
    public void test4x6PixelsEnergy() {
        double[][] expectedEnergy = new double[][]{
                { 1000.00, 1000.00, 1000.00, 1000.00 },
                { 1000.00, 275.66, 173.21, 1000.00 },
                { 1000.00, 173.21, 321.01, 1000.00 },
                { 1000.00, 171.80, 195.63, 1000.00 },
                { 1000.00, 270.93, 188.15, 1000.00 },
                { 1000.00, 1000.00, 1000.00, 1000.00 }
        };
        
        testEnergy(expectedEnergy, "seamCarving/4x6.png");
    }
    
    @Test
    public void test5x6PixelsEnergy() {
        double[][] expectedEnergy = new double[][]{
                { 1000.00, 1000.00, 1000.00, 1000.00, 1000.00 },
                { 1000.00, 300.07, 265.33, 289.67, 1000.00 },
                { 1000.00, 311.94, 94.36, 309.61, 1000.00 },
                { 1000.00, 295.49, 312.36, 193.36, 1000.00 },
                { 1000.00, 264.36, 216.49, 299.43, 1000.00 },
                { 1000.00, 1000.00, 1000.00, 1000.00, 1000.00 }
        };
        
        testEnergy(expectedEnergy, "seamCarving/5x6.png");
    }
    
    @Test
    public void test12x10PixelsEnergy() {
        double[][] expectedEnergy = new double[][]{
                { 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00 },
                { 1000.00, 218.03, 149.70, 244.34, 283.24, 154.42, 356.60, 155.00, 218.34, 283.59, 127.79, 1000.00 },
                { 1000.00, 302.49, 251.81, 374.68, 267.69, 254.96, 212.97, 173.89, 268.38, 198.94, 326.78, 1000.00 },
                { 1000.00, 211.42, 343.91, 253.05, 261.13, 249.92, 211.04, 289.04, 269.88, 334.67, 210.63, 1000.00 },
                { 1000.00, 278.44, 201.02, 251.05, 290.57, 453.32, 184.53, 231.94, 265.70, 256.80, 189.91, 1000.00 },
                { 1000.00, 299.81, 218.19, 230.37, 229.30, 279.72, 237.31, 203.21, 259.03, 230.00, 330.31, 1000.00 },
                { 1000.00, 248.94, 325.12, 158.91, 212.26, 213.61, 259.53, 114.91, 138.97, 309.96, 194.41, 1000.00 },
                { 1000.00, 294.15, 198.64, 194.50, 184.68, 326.50, 297.71, 99.89, 343.30, 267.94, 251.26, 1000.00 },
                { 1000.00, 219.97, 253.51, 150.86, 268.54, 259.28, 227.65, 311.86, 168.54, 217.37, 219.49, 1000.00 },
                { 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00 }
        };
        
        testEnergy(expectedEnergy, "seamCarving/12x10.png");
    }
    
    private void testEnergy(double[][] expectedEnergy, String resourcePath) {
        //given
        Picture inputImg = new Picture(resourcePath);
        SeamCarver sc = new SeamCarver(inputImg);
        //when
        int height = sc.height();
        int width = sc.width();
        double[][] actualEnergy = new double[height][width];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                actualEnergy[j][i] = sc.energy(i, j);
            }
        }
        //then
        assertArrayEquals(expectedEnergy, actualEnergy);
    }
    
    @Test
    public void verify3x4VerticalSeam()
    {
        //given
        Picture inputImg = new Picture("seamCarving/3x4.png");
        SeamCarver sc = new SeamCarver(inputImg);
        int[] expectedSeam = new int[]{0,1,1,0};
        
        //when
        int[] actualSeam = sc.findVerticalSeam();
        
        //then
        assertArrayEquals(expectedSeam, actualSeam);
    }
    
    @Test
    public void verify5x6VerticalSeam()
    {
        //given
        Picture inputImg = new Picture("seamCarving/5x6.png");
        SeamCarver sc = new SeamCarver(inputImg);
        int[] expectedSeam = new int[]{1,2,2,3,2,1};
        
        //when
        int[] actualSeam = sc.findVerticalSeam();
        
        //then
        assertArrayEquals(expectedSeam, actualSeam);
    }
    
    @Test
    public void verify10x10VerticalSeam()
    {
        //given
        Picture inputImg = new Picture("seamCarving/10x10.png");
        SeamCarver sc = new SeamCarver(inputImg);

        int[] expectedSeam = new int[]{6,7,7,7,7,7,8,8,7,6};
        
        //when
        int[] actualSeam = sc.findVerticalSeam();
        
        //then
        assertArrayEquals(expectedSeam, actualSeam);
    }

}
