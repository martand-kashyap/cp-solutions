package misc;

class LargestPossibleSquareFromCutSticks {
    /*-
    Largest possible square for cut sticks: -

    Want to cut sticks so that we achieve 4 sticks of the same length. (there can be leftover pieces).
    What is the longest square side that we can achieve?

    -use this function def square(a, b)
    Input: two integers a, b
    Output: return the side length of the largest square that we can have,
            If not possible function should return 0.

    Input: two integers A, B
    Output: Return the side length of the largest square that we can have, if not possible function should return 0.

    ex1:
    Input: A = 13, B = 11
    Output: function will return 5 because we can cut two sticks of length 5 from each of the given sticks.

    ex2:
    Input: Given A = 10, B = 21
    Output: the function will return 7 because we can split the second stick B into three sticks of length 7 and shorten the first stick A by 3.

    ex3:
    Input: Given A = 2, B = 1
    Output: the function will return 0 as it is not possible to make any square from the sticks provided

    ex4:
    Input: Given A = 1, B = 8
    Output: the function will return 2 because we can cut stick B into 4 parts
     */

    public static void main(String[] args) {
        LargestPossibleSquareFromCutSticks problem = new LargestPossibleSquareFromCutSticks();
        System.out.println(problem.bruteforce(11, 13));
        System.out.println(problem.binarySearch(11, 13));
    }

    private int bruteforce(int a, int b) {
        int totalLength = a + b;
        int maxSideLength = totalLength / 4;

        while (maxSideLength >= 0) {
            int piecesFromA = a / maxSideLength;
            int piecesFromB = b / maxSideLength;

            if (piecesFromA + piecesFromB >= 4) {
                return maxSideLength;
            } else {
                maxSideLength--;
            }
        }

        return 0;
    }

    private int binarySearch(int a, int b) {
        int totalLength = a + b;
        int l = 1, r = totalLength / 4, result = 0;

        while (l < r) {
            int maxSideLength = l + (r - l) / 2;

            if (isCutValid(a, b, maxSideLength)) {
                result = maxSideLength;
                l = maxSideLength + 1;
            } else {
                r = maxSideLength - 1;
            }
        }

        return result;
    }

    private boolean isCutValid(int a, int b, int maxSideLength) {
        int piecesFromA = a / maxSideLength;
        int piecesFromB = b / maxSideLength;

        return piecesFromA + piecesFromB >= 4;
    }
}
