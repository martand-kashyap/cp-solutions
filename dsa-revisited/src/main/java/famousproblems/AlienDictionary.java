package famousproblems;

import java.io.PrintWriter;
import java.util.*;

class AlienDictionary {
    /*-
    There is a new alien language that uses the English alphabet.
    However, the order among the letters is unknown to you.

    You are given a list of strings words from the alien language's dictionary,
    where the strings in words are sorted lexicographically by the rules of this new language.

    Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules.
    If there is no solution, return "".
    If there are multiple solutions, return any of them.

    Examples:
    Input: words = ["wrt","wrf","er","ett","rftt"]
    Output: "wertf"

    Input: words = ["ywx", "wz", "xww", "xz", "zyy", "zwz"]
    Output: "ywxz"

    Input: words = ["abc", "ab"]
    Output: ""
     */
    public static void main(String[] args) {
        String[] words = {"ywx", "wz", "xww", "xz", "zyy", "zwz"};

        AlienDictionary problem = new AlienDictionary();

        String res =
                "Lexicographically Increasing Order of the letters in the words : " + "\n" +
                        Arrays.toString(words) + "\n" +
                        "Kahn's Algorithm T(n) = O(V+N), S(n) = O(V+N) : " + problem.findOrderOfLettersUsingKahnAlgorithm(words);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private String findOrderOfLettersUsingKahnAlgorithm(String[] words) {
        StringBuilder orderedLetters = new StringBuilder();
        Map<Character, Integer> vertexInDegree = new HashMap<>();
        Map<Character, List<Character>> graph = new HashMap<>();
        Queue<Character> bfsQueue = new LinkedList<>();

        //1. Initialize the inDegree & adjacency list
        for (String alienWord : words) {
            for (char alienChar : alienWord.toCharArray()) {
                vertexInDegree.put(alienChar, 0);
                graph.put(alienChar, new ArrayList<>());
            }
        }

        //2. Create the graph
        for (int i = 0; i <= words.length - 2; i++) {
            String word1 = words[i], word2 = words[i + 1];

            // Handle the scenario of type ["abc", "ab"]. This is an invalid order.
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            for (int charIndex = 0; charIndex < Integer.min(word1.length(), word2.length()); charIndex++) {
                char u = word1.charAt(charIndex), v = word2.charAt(charIndex);
                //find the first index where both words have different character
                if (u != v) {
                    //add the edge u->v
                    graph.get(u).add(v);

                    //increase the in-degree of v
                    vertexInDegree.put(v, vertexInDegree.get(v) + 1);

                    //process only the first letter where the two words differ
                    break;
                }
            }
        }

        //3. Identify the sources for Kahn algorithm i.e. vertices whose inDegree = 0
        for (char u : graph.keySet()) {
            if (vertexInDegree.get(u) == 0) {
                bfsQueue.offer(u);
            }
        }

        //4. Find topological order
        while (!bfsQueue.isEmpty()) {
            char u = bfsQueue.poll();

            orderedLetters.append(u);

            for (char v : graph.get(u)) {
                //remove the edge u->v i.e. decrease the inDegree(v) by 1
                vertexInDegree.put(v, vertexInDegree.get(v) - 1);
                if (vertexInDegree.get(v) == 0) {
                    bfsQueue.offer(v);
                }
            }
        }

        return (orderedLetters.length() == vertexInDegree.size()) ? orderedLetters.toString() : "";
    }
}
