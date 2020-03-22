import java.util.ArrayList;

/**
 * Class for sorting an array of Comparable objects from smallest to
 * largest.
 * <p/>
 * This code is based on code from Chapter 8 and 9 of
 * Data Structures and Abstractions with Java 4/e by Carrano
 *
 * @author Jamie Hernandez
 * @version 3/10/2020
 */


public class SortArray
{
    /**
     * Task: Swaps the array elements a[i] and a[j].
     *
     * @param a an array of  objects
     * @param i an integer >= 0 and < a.length
     * @param j an integer >= 0 and < a.length
     */
    private static <T>
    void swap(T[] a, int i, int j)
    {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    } // end swap


    /**************************************************************
     * ITERATIVE INSERTION SORT
     **************************************************************/

    /**
     * Task: Iterative insertion sort of the  objects in a range of locations in an array into ascending order.
     *
     * @param a     an array of Comparable objects
     * @param first an integer >= 0
     * @param last  an integer > first and < a.length
     */

    private static <T extends Comparable<? super T>>
    void insertionSort(T[] a, int first, int last)
    {
        System.out.println("  >> In insertion sort: first = " + first + ", last = " + last);
        T temp;
        boolean foundLocation;
        int loc;

        for (int i = first + 1; i <= last; i++)
        {
            temp = a[i];

            // move values over to make room
            loc = i - 1;  // start with value to the left
            foundLocation = false;
            while (loc >= first && !foundLocation)
            {
                if (a[loc].compareTo(temp) > 0)
                {
                    a[loc + 1] = a[loc];
                    loc--;
                }
                else
                    foundLocation = true;  // found the spot
            }

            // put the value in the right place  
            a[loc + 1] = temp;
        }
    } // end insertionSort

    /**
     * ***********************************************************
     * QUICK SORT -
     * RECURSIVE
     * MEDIAN OF THREE
     * DOES INSERTION SORT ON SMALL ARRAYS
     * ************************************************************
     */

    private static int MIN_SIZE = 7;

    /**
     * Task: Sorts the first n objects in an array into ascending order.
     *
     * @param a an array of Comparable objects
     * @param n an integer > 0
     */
    public static <T extends Comparable<? super T>>
    void quickSort(T[] a, int n)
    {
        quickSort(a, 0, n - 1);
    }


    /**
     * Task: Recursively sorts an array into ascending order. Uses quick sort with
     * median-of-three pivot selection for arrays of at least
     * MIN_SIZE elements, and uses insertion sort for other arrays.
     *
     * @param a     an array of Comparable objects
     * @param first an integer >= 0 that is the index of the first
     *              array element to consider
     * @param last  an integer >= 0 that is the index of the last
     *              array element to consider
     */
    private static <T extends Comparable<? super T>>
    void quickSort(T[] a, int first, int last)
    {
        if (last - first + 1 < MIN_SIZE)
        {
            insertionSort(a, first, last);
        }
        else
        {
            // Create the partition: Smaller | Pivot | Larger
            int pivotIndex = partition(a, first, last);

            // Sort subarrays Smaller and Larger
            quickSort(a, first, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, last);
        }
    }


    /**
     * Task: Sorts the first, middle, and last elements of an
     * array into ascending order.
     *
     * @param a     an array of Comparable objects
     * @param first the integer index of the first array element; first >= 0
     * @param mid   the integer index of the middle array element
     * @param last  the integer index of the last array element;
     *              last - first >= 2, last < a.length
     */
    private static <T extends Comparable<? super T>>
    void sortFirstMiddleLast(T[] a, int first, int mid, int last)
    {
        orderTwoItems(a, first, mid); // make a[first] <= a[mid]
        orderTwoItems(a, mid, last); // make a[mid] <= a[last]
        orderTwoItems(a, first, mid); // make a[first] <= a[mid]

        System.out.print("  >> In sortFirstMiddleLast - Three pivot candidates: ");
        System.out.print("a[" + first + "] = " + a[first] + "; ");
        System.out.print("a[" + mid + "] = " + a[mid] + "; ");
        System.out.print("a[" + last + "] = " + a[last] + "; ");
        System.out.println();
    } // end sortFirstMiddleLast


    /**
     * Task: Orders two given array elements into ascending order
     * so that a[i] <= a[j].
     *
     * @param a an array of Comparable objects
     * @param i an integer >= 0 and < array.length
     * @param j an integer >= 0 and < array.length
     */
    private static <T extends Comparable<? super T>>
    void orderTwoItems(T[] a, int i, int j)
    {
        if (a[i].compareTo(a[j]) > 0)
            swap(a, i, j);
    } // end orderTwoItems


    /**
     * Finds recursively indexes of nine pivot candidates
     *
     * @param indexes  indexes of pivot candidates
     * @param a     an array of Comparable objects
     * @param first the integer index  of the first sub-array element
     * @param mid   the integer index of the middle sub-array element
     * @param last  the integer index of the last sub-array element
     * @param maxDistanceBetweenIndexes  number of elements in the sub-array
     */
    private static <T extends Comparable<? super T>>
    void findPivotCandidatesIndexes(ArrayList<Integer> indexes, T[] a, int first, int mid, int last, int maxDistanceBetweenIndexes)
    {
        //TODO Project 4
        // IMPLEMENT findPivotCandidatesIndexes method RECURSIVELY


    }
    /**
     * Performs insertion sort on nine pivot candidates
     * @param indexes indexes of pivot candidates
     * @param a       an array of Comparable objects
     */
    private static <T extends Comparable<? super T>>
    void insertionSortPivotCandidates(ArrayList<Integer> indexes, T[] a)
    {
        //TODO Project 4
        // follow insertion sort algorithm






        System.out.print("  >> Five pivot candidates: ");
        for (int i = 0; i < indexes.size(); i++)
            System.out.print("a[" + indexes.get(i) + "] = " + a[indexes.get(i)] + "; ");
        System.out.println();
    }

    /**
     * Task: Partitions an array as part of quick sort into two subarrays
     * called Smaller and Larger that are separated by a single
     * element called the pivot.
     * Elements in Smaller are left of the pivot and <= pivot.
     * Elements in Larger are right of the pivot and >= pivot.
     *
     * @param a     an array of Comparable objects
     * @param first the integer index of the first array element;
     *              first >= 0
     * @param last  the integer index of the last array element;
     *              last >= first; last < a.length
     * @return the index of the pivot
     */
    private static <T extends Comparable<? super T>>
    int partition(T[] a, int first, int last)
    {
        //TODO Project 4
        // modify this code per lab description
        final int NUMBER_OF_PIVOTS_FOR_OVER_FORTY = 5;
        System.out.println("  >> In partition - first is " + first + ", last is " + last);
        System.out.println("  >> Choosing pivot");
        int mid = first + (last - first) / 2;
        sortFirstMiddleLast(a, first, mid, last);

        int pivotIndex = mid;
        if (last - first + 1 > 3)
        {
            // Move pivot to next-to-last position in array
            System.out.println("  >> swapping pivot " + a[mid] + " at index " + mid + " with element " + a[last-1] + " at index " + (last - 1));
            swap(a, mid, last - 1);
            pivotIndex = last - 1;
        }

        // done choosing pivot
        // at this point pivot is moved to the appropriate spot in the array
        if (last - first + 1 > 3)
        {
            T pivotValue = a[pivotIndex];
            System.out.println("\u001B[35m\u001B[1m    >> partitioning with pivot value = " + pivotValue  +"\u001B[0m\n");
            // Determine subarrays Smaller = a[first..endSmaller]
            // and                 Larger  = a[endSmaller+1..last-1]
            // such that entries in Smaller are <= pivotValue and
            // entries in Larger are >= pivotValue; initially, these subarrays are empty

            int indexFromLeft = first + 1;
            int indexFromRight = last - 2;

            boolean done = false;
            while (!done)
            {
                // Starting at beginning of array, leave entries that are < pivotValue;
                // locate first entry that is >= pivotValue; you will find one,
                // since last entry is >= pivot
                while (indexFromLeft < last && a[indexFromLeft].compareTo(pivotValue) < 0)
                    indexFromLeft++;

                // Starting at end of array, leave entries that are > pivot;
                // locate first entry that is <= pivot; you will find one,
                // since first entry is <= pivot

                while (indexFromRight >= first && a[indexFromRight].compareTo(pivotValue) > 0)
                    indexFromRight--;

                if (indexFromLeft < indexFromRight)
                {
                    swap(a, indexFromLeft, indexFromRight);
                    indexFromLeft++;
                    indexFromRight--;
                }
                else
                    done = true;
            }

            // Place pivotValue between the subarrays Smaller and Larger
            System.out.println("  >> swapping pivot " + a[pivotIndex] + " into final position " + indexFromLeft  + " from position " + pivotIndex);
            swap(a, pivotIndex, indexFromLeft);
            pivotIndex = indexFromLeft;

            // Assertion:
            //   Smaller = a[first..pivotIndex-1]
            //   Pivot = a[pivotIndex]
            //   Larger = a[pivotIndex+1..last]
        }
        return pivotIndex;
    } // end partition


    /**************************************************************
     *
     * COUNTING SORT
     *
     **************************************************************/
    /**
     * Making only one pass through the array, counts the number of times
     * each integer occurs in the array. These counts are then used
     * to sort the array
     *
     * @param a        array to be sorted
     * @param maxValue the maximum possible value in the array
     */
    public static void countingSort(int[] a, int maxValue)
    {
        //TODO Project 2 - DONE
        int[] countArr = new int[maxValue + 1];

        //buckets
        for (int i = 0; i < maxValue + 1; i++){
            countArr[i] = 0;
        }

        for (int i = 0; i < a.length; i++){
            countArr[a[i]]++;
        }
        int index = 0;

        for (int i = 0; i < maxValue + 1; i++){
            for (int j = 0; j < countArr[i]; j++){
                a[index] = i;
                index++;
            }
        }
    } // end countingSort
}// end SortArray