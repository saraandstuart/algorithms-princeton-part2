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
                { 195075, 195075, 195075 },
                { 195075, 86627, 195075 },
                { 195075, 55775, 195075 },
                { 195075, 105720, 195075 },
                { 195075, 63180, 195075 },
                { 195075, 78196, 195075 },
                { 195075, 195075, 195075 }
        };
        
        testEnergy(expectedEnergy, "seamCarving/3x7.png");
    }
    
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
    
    @Test
    public void test5x6PixelsEnergy() {
        double[][] expectedEnergy = new double[][]{
                { 195075, 195075, 195075, 195075, 195075 },
                { 195075, 90043, 70399, 83908, 195075 },
                { 195075, 97309, 8903, 95856, 195075 },
                { 195075, 87312, 97567, 37387, 195075 },
                { 195075, 69888, 46867, 89661, 195075 },
                { 195075, 195075, 195075, 195075, 195075 }
        };
        
        testEnergy(expectedEnergy, "seamCarving/5x6.png");
    }
    
    @Test
    public void test12x10PixelsEnergy() {
        double[][] expectedEnergy = new double[][]{
                {    195075,    195075,    195075,    195075,    195075,    195075,    195075,    195075,    195075,    195075,    195075,    195075 }, 
                {    195075,     47535,     22411,     59701,     80223,     23846,    127161,     24026,     47672,     80424,     16330,    195075 }, 
                {    195075,     91500,     63408,    140387,     71656,     65003,     45355,     30236,     72026,     39576,    106787,    195075 }, 
                {    195075,     44700,    118273,     64032,     68187,     62460,     44539,     83547,     72837,    112001,     44363,    195075 }, 
                {    195075,     77527,     40409,     63028,     84431,    205496,     34050,     53795,     70597,     65945,     36064,    195075 }, 
                {    195075,     89889,     47608,     53072,     52580,     78242,     56315,     41294,     67094,     52900,    109104,    195075 }, 
                {    195075,     61973,    105703,     25252,     45055,     45631,     67356,     13205,     19313,     96078,     37795,    195075 }, 
                {    195075,     86524,     39457,     37831,     34108,    106604,     88634,      9978,    117856,     71794,     63131,    195075 }, 
                {    195075,     48388,     64267,     22759,     72114,     67226,     51823,     97255,     28405,     47249,     48174,    195075 }, 
                {    195075,    195075,    195075,    195075,    195075,    195075,    195075,    195075,    195075,    195075,    195075,    195075  }
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

}
