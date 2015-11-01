import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.princeton.cs.algs4.In;

/**
 * {@link Outcast} unit test
 * 
 * @author Stuart Shannon
 */
public class OutcastTest {

    private static final String RESOURCE_PATH = "wordnet-testing";
    private static final String SYNSETS_PATH = RESOURCE_PATH + "/synsets.txt";
    private static final String HYPERNYMS_PATH = RESOURCE_PATH + "/hypernyms.txt";
    
    private static final WordNet wordNet = new WordNet(SYNSETS_PATH, HYPERNYMS_PATH);
    private static final Outcast outcast = new Outcast(wordNet);
    
    @Test
    public void shouldReturnTableForOutcast5() {
        shouldReturnCorrectOutcastForNouns("table", getNouns("/outcast5.txt"));
    }
    
    @Test
    public void shouldReturnBedForOutcast8() {
        shouldReturnCorrectOutcastForNouns("bed", getNouns("/outcast8.txt"));
    }
    
    @Test
    public void shouldReturnPotatoForOutcast11() {
        shouldReturnCorrectOutcastForNouns("potato", getNouns("/outcast11.txt"));
    }
    
    private void shouldReturnCorrectOutcastForNouns(String expectedOutcast, String[] nouns) {
        //given
        //when
        String actualOutcast = outcast.outcast(nouns);
        //then
        assertEquals(expectedOutcast, actualOutcast);
    }
    
    private String[] getNouns(String path) {
        In in = new In(RESOURCE_PATH + path);
        return in.readAllStrings();
    }

}
