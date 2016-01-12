import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * {@link Dimension} unit test
 * 
 * @author Stuart Shannon
 */
public class DimensionTest {
    
    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenInputIsGreaterThanWidthTimesHeight() {
        //given
        Dimension dimension = new Dimension(5, 6);
        //when
        dimension.two(31);
        //then exception
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenInputIsLessThanOne() {
        //given
        Dimension dimension = new Dimension(5, 6);
        //when
        dimension.two(0);
        //then exception
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenInputIsLessThanZero() {
        //given
        Dimension dimension = new Dimension(5, 6);
        //when
        dimension.two(-1);
        //then exception
    }
    
    // test dimension twoToOneDimensions
    @Test
    public void oneToTwoDimensions1()
    {
        oneToTwoDimensions(3, 4, 1, new Coordinate(0, 0));
    }
    
    @Test
    public void oneToTwoDimensions5()
    {
        oneToTwoDimensions(3, 4, 5, new Coordinate(1, 1));
    }
    
    @Test
    public void oneToTwoDimensions8()
    {
        oneToTwoDimensions(3, 4, 8, new Coordinate(1, 2));
    }
    
    @Test
    public void oneToTwoDimensions12()
    {
        oneToTwoDimensions(3, 4, 12, new Coordinate(2, 3));
    }
    
    private void oneToTwoDimensions(
            int width, 
            int height, 
            int oneDimensionIndex,
            Coordinate expectedCoordinate)
    {
        //given
        Dimension dimension = new Dimension(width, height);
        
        //when
        Coordinate actualCoordinate = dimension.two(oneDimensionIndex);
        
        //then
        assertEquals(expectedCoordinate, actualCoordinate);
    }
    
    @Test
    public void twoToOneDimension1()
    {
        twoToOneDimension(3, 4, new Coordinate(0, 0), 1);
    }
    
    @Test
    public void twoToOneDimension5()
    {
        twoToOneDimension(3, 4, new Coordinate(1, 1), 5);
    }

    @Test
    public void twoToOneDimension8()
    {
        twoToOneDimension(3, 4, new Coordinate(1, 2), 8);
    }
    
    @Test
    public void twoToOneDimension12()
    {
        twoToOneDimension(3, 4, new Coordinate(2, 3), 12);
    }

    private void twoToOneDimension(
            int width, 
            int height, 
            Coordinate coordinate,
            int expectedOneDimensionIndex)
    {
        //given
        Dimension dimension = new Dimension(width, height);
        
        //when
        int actualOneDimensionIndex = dimension.one(coordinate);
        
        //then
        assertEquals(expectedOneDimensionIndex, actualOneDimensionIndex);
    }
    
}
