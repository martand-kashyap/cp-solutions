package stack;

import lombok.AllArgsConstructor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

class RemoveAdjacentDuplicates {
    /*-
    Given a string consisting of lowercase English letters,
    repeatedly remove adjacent duplicate letters, one pair at a time.
    Both members of a pair of adjacent duplicate letters need to be removed.

    Example 1:

    Input: s = "abbaca"
    Output: "ca"
    Explanation:
    For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.
    The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".

    Example 2:

    Input: s = "azxxzy"
    Output: "ay"
     */
    public static void main(String[] args) {
        String inputString = "azxxzy";

        RemoveAdjacentDuplicates problem = new RemoveAdjacentDuplicates();

        String res = "After removing adjacent of pairs letters" + "\n" +
                "from the input string: '" + inputString + "' \n\n" +
                "using stack: " + problem.usingStack(inputString) + "\n" +
                "using 2 pointer: " + problem.using2Pointer(inputString) + "\n" +
                "using 2 pointer (optimized): " + problem.using2PointerOptimized(inputString);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    @AllArgsConstructor
    private static class CharacterCountPair {

        char character;
        int count;
    }

    private String usingStack(String input) {
        Stack<CharacterCountPair> seenCharAdjacentCountPairStack = new Stack<>();
        StringBuilder result = new StringBuilder();
        int k = 2;

        for (char currentChar : input.toLowerCase().toCharArray()) {
            if (!seenCharAdjacentCountPairStack.isEmpty()
                    && seenCharAdjacentCountPairStack.peek().character == currentChar) {
                // stack is not empty
                // & the current char is the same as the previous one

                //increment the count
                seenCharAdjacentCountPairStack.peek().count += 1;

                // when the condition is met, remove the last char from the output
                if (seenCharAdjacentCountPairStack.peek().count == k) {

                    // remove the adjacent pair of the character
                    seenCharAdjacentCountPairStack.pop();

                    result.deleteCharAt(result.length() - 1);
                }
            } else {
                // stack is empty, put the char into it with occurrence count 1
                seenCharAdjacentCountPairStack.push(new CharacterCountPair(currentChar, 1));
                result.append(currentChar);
            }
        }

        return String.valueOf(result);
    }

    private String using2Pointer(String inputString) {
        List<Character> result = new ArrayList<>();
        int j = -1;

        for (char currentChar : inputString.toCharArray()) {
            if (!result.isEmpty() && result.get(j) == currentChar) {
                result.remove(j);
                j--;
            } else {
                j++;
                result.add(currentChar);
            }
        }

        return result.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private String using2PointerOptimized(String inputString) {
//        int i = 0;
//        char[] inputChars = inputString.toCharArray();
//        StringBuilder result = new StringBuilder();
//
//        for (int j = 0; j < inputString.length(); j++, i++) {
//            inputChars[i] = inputChars[j];
//
//            if (inputChars[i - 1] == inputChars[j]) {
//                i -= 2;
//            }
//        }
//
//        return new String(inputChars, 0, j);
        return "";
    }
}
