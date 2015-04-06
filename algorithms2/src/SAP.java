import java.util.ArrayList;
import java.util.List;

/**
 * SAP - Shortest Ancestral Path.<br/>
 * The SAP between two vertices v and w in a digraph is the shortest directed 
 * path from v to a common ancestor x, together with a directed path from w 
 * to the same ancestor x.
 * 
 * @author Stuart Shannon
 */
public class SAP {

    private Digraph G;

    public SAP(Digraph G){
        this.G = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w){
        validateIndices(v, w);
        
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(G, w);
        
        List<Integer> ancestors = new ArrayList<Integer>();
        for (int i = 0; i < G.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                ancestors.add(i);
            }
        }
        
//        int shortestAncestor = -1;
        int minDist = Integer.MAX_VALUE;
        for (int ancestor : ancestors) {
            int dist = bfsv.distTo(ancestor) + bfsw.distTo(ancestor);
            if (dist < minDist) {
                minDist = dist;
//                shortestAncestor = ancestor;
            }
        }
        
        if (Integer.MAX_VALUE == minDist) {
            minDist = -1;
        } 
        
        return minDist;
    }
    
    private void validateIndices(int v, int w) {
        if (!isValidIndex(v) || !isValidIndex(w)) {
            throw new IndexOutOfBoundsException();
        }
    }
    
    private boolean isValidIndex(int i) {
        return (i > 0 && i < G.V());
    }
    
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w){
        validateIndices(v, w);
        
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(G, w);
        
        List<Integer> ancestors = new ArrayList<Integer>();
        for (int i = 0; i < G.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                ancestors.add(i);
            }
        }
        
        int shortestAncestor = -1;
        int minDist = Integer.MAX_VALUE;
        for (int ancestor : ancestors) {
            int dist = bfsv.distTo(ancestor) + bfsw.distTo(ancestor);
            if (dist < minDist) {
                minDist = dist;
                shortestAncestor = ancestor;
            }
        }
        
        return shortestAncestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w){
        validateIndices(v, w);
        
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(G, w);
        
        List<Integer> ancestors = new ArrayList<Integer>();
        for (int i = 0; i < G.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                ancestors.add(i);
            }
        }
        
//        int shortestAncestor = -1;
        int minDist = Integer.MAX_VALUE;
        for (int ancestor : ancestors) {
            int dist = bfsv.distTo(ancestor) + bfsw.distTo(ancestor);
            if (dist < minDist) {
                minDist = dist;
//                shortestAncestor = ancestor;
            }
        }
        
        if (Integer.MAX_VALUE == minDist) {
            minDist = -1;
        } 
        
        return minDist;
    }
    
    private void validateIndices(Iterable<Integer> vVertices, Iterable<Integer> wVertices) {
        if (!isValidIndex(vVertices) || !isValidIndex(wVertices)) {
            throw new IndexOutOfBoundsException();
        }
    }
    
    private boolean isValidIndex(Iterable<Integer> vertices) {
        for (Integer vertex : vertices) {
            if (!isValidIndex(vertex)) {
                return false;
            }
        }
        return true;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
        validateIndices(v, w);
        
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(G, w);
        
        List<Integer> ancestors = new ArrayList<Integer>();
        for (int i = 0; i < G.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i)) {
                ancestors.add(i);
            }
        }
        
        int shortestAncestor = -1;
        int minDist = Integer.MAX_VALUE;
        for (int ancestor : ancestors) {
            int dist = bfsv.distTo(ancestor) + bfsw.distTo(ancestor);
            if (dist < minDist) {
                minDist = dist;
                shortestAncestor = ancestor;
            }
        }
        
        return shortestAncestor;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}