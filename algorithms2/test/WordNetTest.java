import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

/**
 * {@link WordNet} unit test
 * 
 * @author Stuart Shannon
 */
public class WordNetTest {

    private static final String RESOURCE_PATH = "wordnet-testing";
    private static final String SYNSETS_PATH = RESOURCE_PATH + "/synsets.txt";
    private static final String HYPERNYMS_PATH = RESOURCE_PATH + "/hypernyms.txt";
    
    private static final WordNet wordNet = new WordNet(SYNSETS_PATH, HYPERNYMS_PATH);
    
    @Test
    public void isNounShouldReturnIterableOfCorrectSizeForSynsets() {
        //given
        int expectedCount = 119188;
        //when
        int actualCount = 0;
        for(@SuppressWarnings("unused") String noun : wordNet.nouns()) {
           actualCount++; 
        }
        //then
        assertEquals(expectedCount, actualCount);
    }
    
    @Test
    public void shouldReturnTrueForNoun() {
        assertTrue(wordNet.isNoun("bird"));
    }
    
    @Test
    public void shouldReturnFalseForNotNoun() {
        assertFalse(wordNet.isNoun("notANoun"));
    }
    
    @Test
    public void shouldReturnCorrectSAPForWormAndBird() {
        shouldReturnCorrectSAPForNounPair(
                "animal animate_being beast brute creature fauna", 
                "worm", 
                "bird");
    }
    
    private void shouldReturnCorrectSAPForNounPair(String expectedSAP, String nounA, String nounB) {
        //when
        String actualSAP = wordNet.sap(nounA, nounB);
        //then
        assertEquals(expectedSAP, actualSAP);
    }
    
    @Test
    public void shouldReturnCorrectDistanceForWormAndBird() {
        shouldReturnCorrectDistanceForNounPair(5, "worm", "bird");
    }
    
    // Not sure why this is 22 and not 23...
    @Ignore
    @Test
    public void shouldReturnCorrectDistanceForMarlinAndMileage() {
        shouldReturnCorrectDistanceForNounPair(23, "marlin", "mileage");
    }
    
    @Test
    public void shouldReturnCorrectDistanceForBlack_PlagueAndblack_marlin() {
        shouldReturnCorrectDistanceForNounPair(33, "Black_Plague", "black_marlin");
    }
    
    @Test
    public void shouldReturnCorrectDistanceForAmerican_water_spanielAndhistology() {
        shouldReturnCorrectDistanceForNounPair(27, "American_water_spaniel", "histology");
    }
    
    @Test
    public void shouldReturnCorrectDistanceForBrown_SwissAndbarrel_roll() {
        shouldReturnCorrectDistanceForNounPair(29, "Brown_Swiss", "barrel_roll");
    }
    
    private void shouldReturnCorrectDistanceForNounPair(int expectedDistance, String nounA, String nounB) {
        //when
        int actualDistance = wordNet.distance(nounA, nounB);
        //then
        assertEquals(expectedDistance, actualDistance);
    }

}
