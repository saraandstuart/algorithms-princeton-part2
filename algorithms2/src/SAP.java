import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * SAP - Shortest Ancestral Path.<br/>
 * The SAP between two vertices v and w in a digraph is the shortest directed 
 * path from v to a common ancestor x, together with a directed path from w 
 * to the same ancestor x.
 * 
 * @author Stuart Shannon
 */
public class SAP {

    private Map<String, SAPProcessor> cache;
    private Digraph G;
    
    public SAP(Digraph G)
    {
        cache = new HashMap<String, SAPProcessor>();
        this.G = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w){
        validateIndices(v, w);
        return cache(v, w).length;
    }


    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        validateIndices(v, w);
        return cache(v, w).ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        validateIndices(v, w);
        return cache(v, w).length;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        validateIndices(v, w);
        return cache(v, w).ancestor;
    }
    
    private void validateIndices(int v, int w)
    {
        if (!isValidIndex(v) || !isValidIndex(w)) 
        {
            throw new IndexOutOfBoundsException();
        }
    }
    
    private void validateIndices(
            Iterable<Integer> vVertices, 
            Iterable<Integer> wVertices) 
    {
        if (!isValidIndex(vVertices) || !isValidIndex(wVertices)) 
        {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isValidIndex(int i) 
    {
        return (i > 0 && i < G.V());
    }
    
    private boolean isValidIndex(Iterable<Integer> vertices) 
    {
        for (int vertex : vertices) 
        {
            if (!isValidIndex(vertex)) 
            {
                return false;
            }
        }
        return true;
    }
    
    private SAPProcessor cache(int v, int w) 
    {
        String key = v + "_" + w;
        SAPProcessor result = null;
        
        if (cache.containsKey(key)) 
        {
            result = cache.get(key);
        }
        else 
        {
            result = new SAPProcessor(v, w);
            cache.put(key, result);
        }
        
        return result;
    }

    private SAPProcessor cache(Iterable<Integer> v, Iterable<Integer> w) 
    {
        String key = v.toString() + "_" + w.toString();
        SAPProcessor result = null;
        
        if (cache.containsKey(key)) 
        {
            result = cache.get(key);
        }
        else 
        {
            result = new SAPProcessor(v, w);
            cache.put(key, result);
        }
        
        return result;
    }

    private class SAPProcessor 
    {
        private int ancestor = -1;
        private int length = Integer.MAX_VALUE;

        public SAPProcessor(int v, int w)
        {
            BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(G, v);
            BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(G, w);

            process(bfsv, bfsw);
        }

        public SAPProcessor(Iterable<Integer> v, Iterable<Integer> w) 
        {
            BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(G, v);
            BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(G, w);

            process(bfsv, bfsw);
        }

        private void process(
                BreadthFirstDirectedPaths bfsv, 
                BreadthFirstDirectedPaths bfsw) 
        {
            List<Integer> ancestors = ancestors(bfsv, bfsw);

            for (int currAncestor : ancestors) 
            {
                int currLength = bfsv.distTo(currAncestor) + bfsw.distTo(currAncestor);
                if (currLength < length) 
                {
                    this.length = currLength;
                    this.ancestor = currAncestor;
                }
            }
            
            if (length == Integer.MAX_VALUE) 
            {
                this.length = -1;
            }
        }

        private List<Integer> ancestors(
                BreadthFirstDirectedPaths bfsv, 
                BreadthFirstDirectedPaths bfsw) 
        {
            List<Integer> ancestors = new ArrayList<>();
            for (int i = 0; i < G.V(); i++) 
            {
                if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) 
                {
                    ancestors.add(i);
                }
            }
            return ancestors;
        }
    }

    // do unit testing of this class
    public static void main(String[] args) 
    {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) 
        {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}