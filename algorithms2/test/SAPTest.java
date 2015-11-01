import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

/**
 * {@link SAP} unit test
 * 
 * @author Stuart Shannon
 */
public class SAPTest {
    
    private static final String RESOURCE_PATH = "wordnet-testing";
    private static final String DIGRAPH_1_PATH = RESOURCE_PATH + "/digraph1.txt";
    private static final String DIGRAPH_2_PATH = RESOURCE_PATH + "/digraph2.txt";
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionForInvalidArgumentLessThanZero() {
        //when
        digraph1(-1, 1, 1, 1, DIGRAPH_1_PATH);
        //then exception
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionForInvalidArgumentGreaterThanV() {
        //when
        digraph1(3, 14, 1, 1, DIGRAPH_1_PATH);
        //then exception
    }
    
    @Test
    public void test1ForDigraph1() {
        digraph1(3, 11, 4, 1, DIGRAPH_1_PATH);
    }
    
    @Test
    public void test2ForDigraph1() {
        digraph1(9, 12, 3, 5, DIGRAPH_1_PATH);
    }
    
    @Test
    public void test3ForDigraph1() {
        digraph1(7, 2, 4, 0, DIGRAPH_1_PATH);
    }
    
    @Test
    public void test4ForDigraph1() {
        digraph1(1, 6, -1, -1, DIGRAPH_1_PATH);
    }
    
    @Test
    public void test1ForDigraph2() {
        digraph1(1, 5, 2, 0, DIGRAPH_2_PATH);
    }
    
    private void digraph1(int v, int w, int expectedLength, int expectedAncestor, String resourcePath) {
        //given
        In in = new In(resourcePath);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        
        //when
        int actualLength   = sap.length(v, w);
        int actualAncestor = sap.ancestor(v, w);
        
        //then
        assertEquals(expectedLength, actualLength);
        assertEquals(expectedAncestor, actualAncestor);
    }
    
}
