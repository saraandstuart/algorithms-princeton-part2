import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * {@link SAP} unit test
 * 
 * @author Stuart Shannon
 */
public class SAPTest {
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionForInvalidArgumentLessThanZero() {
        //when
        digraph1(-1, 1, 1, 1);
        //then exception
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionForInvalidArgumentGreaterThanV() {
        //when
        digraph1(3, 14, 1, 1);
        //then exception
    }
    
    @Test
    public void test1ForDigraph1() {
        digraph1(3, 11, 4, 1);
    }
    
    @Test
    public void test2ForDigraph1() {
        digraph1(9, 12, 3, 5);
    }
    
    @Test
    public void test3ForDigraph1() {
        digraph1(7, 2, 4, 0);
    }
    
    @Test
    public void test4ForDigraph1() {
        digraph1(1, 6, -1, -1);
    }
    
    private void digraph1(int v, int w, int expectedLength, int expectedAncestor) {
        //given
        In in = new In("wordnet-testing/digraph1.txt");
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
