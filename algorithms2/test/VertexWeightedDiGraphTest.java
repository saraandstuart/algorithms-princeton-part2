import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * {@link VertexWeightedDiGraph} unit test
 * 
 * @author Stuart Shannon
 */
public class VertexWeightedDiGraphTest
{
    @Test
    public void createsExpectedGraphFrom3x4EnergyMatrix()
    {
        //given
        double[][] energy = new double[][]{
                { 1000.00, 1000.00, 1000.00, 1000.00},
                { 1000.00, 228.53, 228.09, 1000.00 },
                { 1000.00, 1000.00, 1000.00, 1000.00 }
        };
        
        DirectedVertex[] expectedVertices = new DirectedVertex[] {
          new DirectedVertex(0, 1000.00, new int[]{1,2,3}),
          new DirectedVertex(1, 1000.00, new int[]{4,5}),
          new DirectedVertex(2, 1000.00, new int[]{4,5,6}),
          new DirectedVertex(3, 1000.00, new int[]{5,6}),
          new DirectedVertex(4, 1000.00, new int[]{7,8}),
          new DirectedVertex(5, 228.53, new int[]{7,8,9}),
          new DirectedVertex(6, 1000.00, new int[]{8,9}),
          new DirectedVertex(7, 1000.00, new int[]{10,11}),
          new DirectedVertex(8, 228.09, new int[]{10,11,12}),
          new DirectedVertex(9, 1000.00, new int[]{11,12}),
          new DirectedVertex(10, 1000.00, new int[]{13}),
          new DirectedVertex(11, 1000.00, new int[]{13}),
          new DirectedVertex(12, 1000.00, new int[]{13}),
          new DirectedVertex(13, 1000.00, new int[]{})
        };

        verifyExpectedGraphFromEnergyMatrix(
                energy, 
                expectedVertices);
    }
    
    @Test
    public void createsExpectedGraphFrom5x6EnergyMatrix()
    {
        //given
        double[][] energy = new double[][]{
                { 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00 },
                { 1000.00, 300.07,  311.94, 295.49, 264.36, 1000.00 },
                { 1000.00, 265.33, 94.36, 312.36, 216.49, 1000.00 },
                { 1000.00, 289.67, 309.61, 193.36, 299.43, 1000.00 },
                { 1000.00, 1000.00, 1000.00, 1000.00, 1000.00, 1000.00 }
        };
        
        DirectedVertex[] expectedVertices = new DirectedVertex[] {
          new DirectedVertex(0, 1000.00, new int[]{1,2,3,4,5}),
          new DirectedVertex(1, 1000.00, new int[]{6,7}),
          new DirectedVertex(2, 1000.00, new int[]{6,7,8}),
          new DirectedVertex(3, 1000.00, new int[]{7,8,9}),
          new DirectedVertex(4, 1000.00, new int[]{8,9,10}),
          new DirectedVertex(5, 1000.00, new int[]{9,10}),
          new DirectedVertex(6, 1000.00, new int[]{11,12}),
          new DirectedVertex(7, 300.07, new int[]{11,12,13}),
          new DirectedVertex(8, 265.33, new int[]{12,13,14}),
          new DirectedVertex(9, 289.67, new int[]{13,14,15}),
          new DirectedVertex(10, 1000.00, new int[]{14,15}),
          new DirectedVertex(11, 1000.00, new int[]{16,17}),
          new DirectedVertex(12, 311.94, new int[]{16,17,18}),
          new DirectedVertex(13, 94.36, new int[]{17,18,19}),
          new DirectedVertex(14, 309.61, new int[]{18,19,20}),
          new DirectedVertex(15, 1000.00, new int[]{19,20}),
          new DirectedVertex(16, 1000.00, new int[]{21,22}),
          new DirectedVertex(17, 295.49, new int[]{21,22,23}),
          new DirectedVertex(18, 312.36, new int[]{22,23,24}),
          new DirectedVertex(19, 193.36, new int[]{23,24,25}),
          new DirectedVertex(20, 1000.00, new int[]{24,25}),
          new DirectedVertex(21, 1000.00, new int[]{26,27}),
          new DirectedVertex(22, 264.36, new int[]{26,27,28}),
          new DirectedVertex(23, 216.49, new int[]{27,28,29}),
          new DirectedVertex(24, 299.43, new int[]{28,29,30}),
          new DirectedVertex(25, 1000.00, new int[]{29,30}),
          new DirectedVertex(26, 1000.00, new int[]{31}),
          new DirectedVertex(27, 1000.00, new int[]{31}),
          new DirectedVertex(28, 1000.00, new int[]{31}),
          new DirectedVertex(29, 1000.00, new int[]{31}),
          new DirectedVertex(30, 1000.00, new int[]{31}),
          new DirectedVertex(31, 1000.00, new int[]{})
        };

        verifyExpectedGraphFromEnergyMatrix(
                energy, 
                expectedVertices);
    }
    
    private void verifyExpectedGraphFromEnergyMatrix(
            double[][] energy, 
            DirectedVertex[] expectedVertices)
    {
        //given method parameters as input
        
        //when
        VertexWeightedDiGraph graph = new VertexWeightedDiGraph(energy);

        //then
        DirectedVertex[] actualVertices = graph.vertices();
        assertArrayEquals(expectedVertices, actualVertices);
    }

}
