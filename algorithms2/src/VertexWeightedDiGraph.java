
/**
 * Matrix graph representation of pixel energies for {@link SeamCarver}
 * 
 * @author Stuart Shannon
 */
public class VertexWeightedDiGraph
{
    private static final double BORDER_PIXEL_ENERGY = 1000.0;
    
    private final int V;                // number of vertices in this digraph
    private final int height;           // digraph must be a regular rectangle
    private final int width;
    private DirectedVertex[] vertices;
    private Dimension dimension;

    public VertexWeightedDiGraph(double[][] energy)
    {
        width = energy.length;
        height = energy[0].length;
        V = width * height + 2;
        vertices = new DirectedVertex[V];
        dimension = new Dimension(width, height);

        for (int i = 0 ; i < V ; i++)
        {
            double weight = BORDER_PIXEL_ENERGY;
            if(!firstOrLastVertex(i))
            {
                Coordinate coord = dimension.two(i);
                weight = energy[coord.x][coord.y];
            }
            
            vertices[i] = 
                    new DirectedVertex(
                            i, 
                            weight, 
                            edgesTo(i));
        }
    }
    
    private boolean firstOrLastVertex(int i)
    {
        return firstVertex(i) || lastVertex(i);
    }
    
    private boolean firstVertex(int i)
    {
        return i == 0;
    }
    
    private boolean lastVertex(int i)
    {
        return i == (V - 1);
    }
    
    private boolean lastRow(int i)
    {
        return i > ((width * height) - width);
    }
    
    private boolean firstColumn(int i)
    {
        return i % width == 1;
    }
    
    private boolean lastColumn(int i)
    {
        return i % width == 0;
    }

    private int[] edgesTo(int index)
    {
        int[] edgesTo;
        
        // order matters
        if (firstVertex(index))
        {
            edgesTo = new int[width];
            for (int i = 0; i < width; i++)
            {
                edgesTo[i] = i + 1;
            }
        }
        else if (lastVertex(index))
        {
            edgesTo = new int[0];
        }
        else if (lastRow(index))
        {
            edgesTo = new int[1];
            edgesTo[0] = V - 1;
        }
        else if (firstColumn(index))
        {
            edgesTo = new int[2];
            edgesTo[0] = index + width;
            edgesTo[1] = index + width + 1;
        }
        else if (lastColumn(index))
        {
            edgesTo = new int[2];
            edgesTo[0] = index + width - 1;
            edgesTo[1] = index + width;
        }
        else
        {
            edgesTo = new int[3];
            edgesTo[0] = index + width - 1;
            edgesTo[1] = index + width;
            edgesTo[2] = index + width + 1;
        }
        
        return edgesTo;
    }
    
    public int V()
    {
        return V;
    }
    
    public DirectedVertex[] vertices()
    {
        return vertices;
    }
    
    public Dimension geDimension()
    {
        return dimension;
    }
    
}
