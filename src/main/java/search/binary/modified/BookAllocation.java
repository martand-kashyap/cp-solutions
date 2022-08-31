package search.binary.modified;

import java.io.PrintWriter;
import java.util.Arrays;

class BookAllocation {
    /*-
    Given an array ‘pages’ of integer numbers,
    where ‘pages[i]’ represents the number of pages in the ‘i-th’ book.

    There are ‘m’ number of students,
    and the task is to allocate all the books to their students.

    Allocate books in a way such that:
    1. Each student gets at least one book.
    2. Each book should be allocated to a student.
    3. Book allocation should be in a contiguous manner.

    You have to allocate the books to ‘m’ students such that
    the maximum number of pages assigned to a student is minimum.

    Example:
        Input : pages[] = {12, 34, 67, 90} , m = 2
        Output : 113
        Explanation:
        There are 2 number of students.
        Books can be distributed in following fashion :
          1) [12] and [34, 67, 90]
              Max number of pages is allocated to student
              '2' with 34 + 67 + 90 = 191 pages
          2) [12, 34] and [67, 90]
              Max number of pages is allocated to student
              '2' with 67 + 90 = 157 pages
          3) [12, 34, 67] and [90]
              Max number of pages is allocated to student
              '1' with 12 + 34 + 67 = 113 pages

        Of the 3 cases, Option 3 has the minimum pages = 113.
     */
    public static void main(String[] args) {
        int[] pages = {12, 34, 67, 90};
        int n = pages.length, numberOfStudents = 2;

        BookAllocation ba = new BookAllocation();
        String res = "Min #pages for a student: " + ba.minimizePageAllocation(pages, n, numberOfStudents);

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(res);
        pw.close();
    }

    private int minimizePageAllocation(int[] pages, int n, int numberOfStudents) {
        if (n < numberOfStudents) {
            return -1;
        }

        int l = Arrays.stream(pages).max().getAsInt();
        int r = Arrays.stream(pages).sum();
        int result = Integer.MAX_VALUE;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (isAllocationValid(pages, n, numberOfStudents, m)) {
                result = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return result;
    }

    private boolean isAllocationValid(int[] pages, int n, int numberOfStudents, int maxPagesPerStudent) {
        int currentAllocatedStudents = 1, sumOfPagesAllocated = 0;

        for (int i = 0; i < n; i++) {
            sumOfPagesAllocated += pages[i];

            if (sumOfPagesAllocated > maxPagesPerStudent) {
                //add new student
                currentAllocatedStudents += 1;

                //assign the current book(#pages) to the new student
                sumOfPagesAllocated = pages[i];
            }

            if (currentAllocatedStudents > numberOfStudents) {
                return false;
            }
        }
        return true;
    }
}
