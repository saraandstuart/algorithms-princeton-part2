import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


/**
 * Outcast - Immutable data type with method to identify the outcast of a collection of nouns.
 *           Which is that noun that is farthest (greatest distance) from all other nouns 
 *           in the collection.
 * 
 * @author Stuart Shannon
 */
public class Outcast {

    private WordNet wordNet;
    
    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        String outcast = null;
        int maxDist = 0;
        
        for (String nounUnderTest : nouns) {
            int dist = 0;
            for (String otherNoun : nouns) {
                if (!nounUnderTest.equals(otherNoun)) {
                    dist += wordNet.distance(nounUnderTest, otherNoun);
                }
            }
            
            if (dist > maxDist) {
                maxDist = dist;
                outcast = nounUnderTest;
            }
        }
        
        return outcast;
    }

    /**
     * The following test client takes from the command line the name of a 
     * synset file, the name of a hypernym file, followed by the names of 
     * outcast files, and prints out an outcast in each file.
     */
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}