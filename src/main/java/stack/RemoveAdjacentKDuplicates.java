package stack;

import lombok.AllArgsConstructor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

class RemoveAdjacentKDuplicates {
    public static void main(String[] args) {
        String inputString = "pbbcggttciiippooaais";
        int k = 2;

        RemoveAdjacentKDuplicates problem = new RemoveAdjacentKDuplicates();

        String res = "After removing '" + k + "' adjacent letters" + "\n" +
                "from the input string: '" + inputString + "' \n\n" +
                "using stack: " + problem.usingStack(inputString, k) + "\n" +
                "using 2 pointer: " + problem.using2Pointer(inputString, k);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private String using2Pointer(String inputString, int k) {
        return "";
    }

    @AllArgsConstructor
    private static class CharacterCountPair {

        char character;
        int count;
    }

    private String usingStack(String inputString, int k) {
        Stack<CharacterCountPair> characterCountPairStack = new Stack<>();
        List<Character> result = new ArrayList<>();

        for (char currentChar : inputString.toCharArray()) {
            if (characterCountPairStack.isEmpty()) {
                result.add(currentChar);
                characterCountPairStack.push(new CharacterCountPair(currentChar, 1));
            } else if (characterCountPairStack.peek().character == currentChar) {
                result.add(currentChar);
                characterCountPairStack.peek().count += 1;

                if (characterCountPairStack.peek().count == k) {
                    while (result.size() > 0) {
                        result.remove(result.size() - 1);
                    }

                    characterCountPairStack.pop();
                }
            }
        }

        return result.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
