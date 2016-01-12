import edu.princeton.cs.algs4.Stack;

/**
 * @author Stuart Shannon
 */
public class Seam
{
    private double[] energyTo;          // energyTo[v] = energy of least energy s->v path
    private int[] vertexTo;             // vertexTo[v] = last vertex on least energy s->v path
    private VertexWeightedDiGraph g;

    public Seam(VertexWeightedDiGraph G)
    {
        energyTo = new double[G.V()];
        vertexTo = new int[G.V()];
        g = G;

        for (int i = 0; i < G.V(); i++)
        {
            energyTo[i] = Double.POSITIVE_INFINITY;
        }
        energyTo[0] = 0.0;

        DirectedVertex[] vertices = G.vertices();

        for (DirectedVertex v : vertices)
        {
            for (int vertex : v.verticesTo())
            {
                DirectedVertex from = vertices[v.vertex()];
                DirectedVertex to = vertices[vertex];

                relax(from, to);
            }
        }
    }

    private void relax(DirectedVertex from, DirectedVertex to)
    {
        if (energyTo[to.vertex()] > energyTo[from.vertex()] + to.weight())
        {
            energyTo[to.vertex()] = energyTo[from.vertex()] + to.weight();
            vertexTo[to.vertex()] = from.vertex();
        }
    }

    public double energyTo(int v)
    {
        return energyTo[v];
    }

    public Iterable<Integer> pathTo(int v)
    {
        return pathToStack(v);
    }
    
    private Stack<Integer> pathToStack(int v)
    {
        Stack<Integer> path = new Stack<Integer>();
        for (int from = vertexTo[v]; from != 0; from = vertexTo[from])
        {
            path.push(from);
        }
        return path;
    }

    public int[] seam()
    {
        Stack<Integer> path = pathToStack(vertexTo.length -1);
        
        int[] result = new int[path.size()];

        int index = 0;
        for (int i : path)
        {
            result[index] = g.getXCoordinate(i);
            index ++;
        }

        return result;
    }
}
