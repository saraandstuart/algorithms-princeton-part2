import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * {@link SeamCarver} unit test
 * 
 * @author Stuart Shannon
 */
public class SeamCarverTest {

    @Test
    public void test4x6PixelsEnergy() {
        double[][] expectedEnergy = new double[][]{
                { 195075, 195075, 195075, 195075 },
                { 195075, 75990, 30003, 195075 },
                { 195075, 30002, 103046, 195075 },
                { 195075, 29515, 38273, 195075 },
                { 195075, 73403, 35399, 195075 },
                { 195075, 195075, 195075, 195075 }
        };
        
        testEnergy(expectedEnergy, "seamCarving/4x6.png");
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

}
