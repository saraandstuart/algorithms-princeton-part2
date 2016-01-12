
/**
 * @author Stuart Shannon
 */
public class Dimension
{
    private int width;
    private int height;
    
    public Dimension(int width, int height)                                                                                                                                                                                                                                           
    {
        this.width = width;
        this.height = height;
    }
    
    public int one(Coordinate coord)
    {
        return (coord.y * width) + coord.x + 1;
    }
    
    public Coordinate two(int i)
    {
        if (i < 1 || i > width * height) {
            throw new IllegalArgumentException("Input must be between 1 and " + width * height + ". But it was " + i);
        }
        
        int x = (i - 1) % width;
        int y = (i - 1) / width;
        return new Coordinate(x, y);
    }

}
