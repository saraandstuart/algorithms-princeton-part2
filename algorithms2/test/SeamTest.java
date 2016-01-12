import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * {@link Seam} unit test
 * 
 * @author Stuart Shannon
 */
public class SeamTest
{
    @Test
    public void verify3x4VerticalSeam()
    {
        //given
        double[][] energy = new double[][]{
                { 1000.00, 1000.00, 1000.00, 1000.00},
                { 1000.00, 228.53, 228.09, 1000.00 },
                { 1000.00, 1000.00, 1000.00, 1000.00 }
        };
        
        VertexWeightedDiGraph graph = new VertexWeightedDiGraph(energy);
        Seam seam = new Seam(graph);

        int[] expectedSeam = new int[]{0,1,1,0};
        
        //when
        int[] actualSeam = seam.seam();
        
        //then
        assertArrayEquals(expectedSeam, actualSeam);
    }
    
    @Test
    public void verify5x6VerticalSeam()
    {
        //given
        double[][] energy = new double[][]{
                { 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00 },
                { 1000.00, 300.07, 311.94, 295.49, 264.36, 1000.00 },
                { 1000.00, 265.33, 94.36, 312.36, 216.49, 1000.00 },
                { 1000.00, 289.67, 309.61, 193.36, 299.43, 1000.00 },
                { 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00 }
        };
        
        VertexWeightedDiGraph graph = new VertexWeightedDiGraph(energy);
        Seam seam = new Seam(graph);

        int[] expectedSeam = new int[]{1,2,2,3,2,1};
        
        //when
        int[] actualSeam = seam.seam();
        
        //then
        assertArrayEquals(expectedSeam, actualSeam);
    }
    
    @Test
    public void verify10x10VerticalSeam()
    {
        //given
        double[][] energy = new double[][]{
                { 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00 },
                { 1000.00, 136.73, 222.06, 228.47, 293.85, 208.68, 318.51, 225.2, 222.5, 1000.00 },
                { 1000.00, 272.84, 156.22, 252.25, 274.51, 294.31, 222.38, 270.73, 204.43, 1000.00 },
                { 1000.00, 254.64, 186.79, 168.24, 217.81, 243.53, 240.88, 187.06, 252.65, 1000.00 },
                { 1000.00, 280.2, 208.01, 164.97, 165.63, 161.69, 239.79, 197.58, 270.87, 1000.00 },
                { 1000.00, 178.56, 171.06, 127.18, 175.67, 253.29, 220.45, 165.59, 199.05, 1000.00 },
                { 1000.00, 236.84, 295.56, 209.46, 223.37, 236.73, 259.36, 255.09, 324.13, 1000.00 },
                { 1000.00, 128.93, 125.46, 202.49, 194.78, 217.57, 269.76, 276.89, 90.64, 1000.00 },
                { 1000.00, 172.62, 259, 229.03, 243.35, 221.35, 264.14, 124.04, 245.94, 1000.00 },
                { 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00 }
        };
        
        VertexWeightedDiGraph graph = new VertexWeightedDiGraph(energy);
        Seam seam = new Seam(graph);

        int[] expectedSeam = new int[]{6,7,7,7,7,7,8,8,7,6};
        
        //when
        int[] actualSeam = seam.seam();
        
        //then
        assertArrayEquals(expectedSeam, actualSeam);
    }

}
