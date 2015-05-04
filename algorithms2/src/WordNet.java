/**
 * WordNet - Immutable data type representing the wordnet graph.
 * 
 * @author Stuart Shannon
 */
public class WordNet {

   // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms) {
       //read synsets
       In synsetFile = new In(synsets);
       while (synsetFile.hasNextLine()) {
           String[] items = synsetFile.readLine().split(",");
           String synsetId = items[0];
           String nouns = items[1];
           String[] nounArray = nouns.split(" ");
           String gloss = items[2];
           
           System.out.println("synsetId=" + synsetId);
           for (String noun : nounArray) {
               System.out.println("noun=" + noun);
           }
           System.out.println("gloss=" + gloss);
       }
       
       
       //read hypernyms
       In hypernymFile = new In(hypernyms);
       while (hypernymFile.hasNextLine()) {
           String[] items = hypernymFile.readLine().split(",");
           Integer synsetId = Integer.valueOf(items[0]);
           SET<Integer> hypernums = new SET<Integer>();
           for (int i = 1; i < items.length; i++) {
               hypernums.add(Integer.valueOf(items[i]));
           }
           
           System.out.println("synsetId=" + synsetId);
           System.out.println("hypernums=" + hypernums);
       }
       
   }

   // returns all WordNet nouns
   public Iterable<String> nouns() {
       return null;
   }

   // is the word a WordNet noun?
   public boolean isNoun(String word) {
       return false;
   }

   // distance between nounA and nounB (defined below)
   public int distance(String nounA, String nounB) {
       return -1;
   }

   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounA, String nounB) {
       return null;
   }

   // do unit testing of this class
   public static void main(String[] args) {
       String synsets = args[0];
       String hypernyms = args[1];
       
       new WordNet(synsets, hypernyms);
   }
   
}