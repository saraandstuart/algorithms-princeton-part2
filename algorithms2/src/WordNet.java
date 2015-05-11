import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * WordNet - Immutable data type representing the wordnet graph.
 * 
 * @author Stuart Shannon
 */
public class WordNet {

    private final SAP sap;
    private final Map<Integer, String> idToSynset;
    private final Map<String, Set<Integer>> nounToIds;
    
   // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms) {
       idToSynset = new HashMap<Integer, String>();
       nounToIds = new HashMap<String, Set<Integer>>();
       
       readAndInitializeSynsets(synsets);
       Digraph graph = readAndInitializeHypernyms(hypernyms);
       
       DirectedCycle cycle = new DirectedCycle(graph);
       if (cycle.hasCycle() || !isRootedDAG(graph)) {
           throw new IllegalArgumentException("The input is not a rooted DAG!");
       }

       sap = new SAP(graph);
   }

   private void readAndInitializeSynsets(String synsets) {
       In synsetFile = new In(synsets);

       while (synsetFile.hasNextLine()) {
           String[] items = synsetFile.readLine().split(",");
           
           Integer synsetId = Integer.valueOf(items[0]);
           String synset = items[1];
           idToSynset.put(synsetId, synset);

           String[] nouns = synset.split(" ");
           for (String noun : nouns) {
               Set<Integer> ids = nounToIds.containsKey(noun) ? nounToIds.get(noun) : new HashSet<Integer>();
               ids.add(synsetId);
               nounToIds.put(noun, ids);
           }
       }
   }
   
   private Digraph readAndInitializeHypernyms(String hypernyms) {
       Digraph graph = new Digraph(idToSynset.size());

       In hypernymFile = new In(hypernyms);
       while (hypernymFile.hasNextLine()) {
           String[] items = hypernymFile.readLine().split(",");
           
           Integer synsetId = Integer.valueOf(items[0]);
           for (int i = 1; i < items.length; i++) {
               Integer hypernymId = Integer.valueOf(items[i]);
               graph.addEdge(synsetId, hypernymId);
           }
       }

       return graph;
   }
   
   private boolean isRootedDAG(Digraph g) {
       int roots = 0;
       for (int i = 0; i < g.V(); i++) {
           if (!g.adj(i).iterator().hasNext()) {
               roots++;
               if (roots > 1) {
                   return false;
               }
           }
       }

       return roots == 1;
   }

   // returns all WordNet nouns
   public Iterable<String> nouns() {
       return nounToIds.keySet();
   }

   // is the word a WordNet noun?
   public boolean isNoun(String word) {
       if (null == word || "".equals(word)) {
           return false;
       }
       return nounToIds.containsKey(word);
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