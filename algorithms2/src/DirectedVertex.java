import java.util.Arrays;

/**
 * Representation of a graph vertex with weight and reachable vertices for {@link SeamCarver}
 * 
 * @author Stuart Shannon
 */
public class DirectedVertex
{
    private final int v;
    private final double weight;
    private final int[] verticesTo;
    
    public DirectedVertex(int v, double weight, int[] verticesTo)
    {
        this.v = v;
        this.weight = weight;
        this.verticesTo = verticesTo;
    }

    public int vertex()
    {
        return v;
    }
    
    public double weight()
    {
        return weight;
    }
    
    public int[] verticesTo()
    {
        return verticesTo;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        
        result = prime * result + Arrays.hashCode(verticesTo);
        result = prime * result + v;
        long temp;
        temp = Double.doubleToLongBits(weight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof DirectedVertex)) return false;
        
        DirectedVertex other = (DirectedVertex) obj;
        if (!Arrays.equals(verticesTo, other.verticesTo)) return false;
        if (v != other.v) return false;
        if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight)) return false;
        
        return true;
    }

    @Override
    public String toString()
    {
        return "DirectedVertex [v=" + v + 
                ", weight=" + weight + 
                ", edgesTo=" + Arrays.toString(verticesTo) + "]";
    }
    
    
    
}
