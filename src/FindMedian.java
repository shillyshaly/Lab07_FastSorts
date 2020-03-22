import java.util.Arrays;
import java.util.Random;

/**
 * A class for implementing and testing findMedianValue method
 *
 * @author Jamie Hernandez
 * @version 3/10/2020
 */


public class FindMedian
{
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
    } // end order

    /**
     * Task: Swaps the array elements a[i] and a[j].
     *
     * @param a an array of  objects
     * @param i an integer >= 0 and < a.length
     * @param j an integer >= 0 and < a.length
     *          assumes that i != j
     */
    private static <T>
    void swap(T[] a, int i, int j)
    {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    } // end swap


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
        int mid = first + (last - first) / 2;
        sortFirstMiddleLast(a, first, mid, last);

        int pivotIndex = mid;

        if (last - first + 1 > 3)
        {

            // Assertion: The pivot is a[mid]; a[first] <= pivot and
            // a[last] >= pivot, so do not compare these two array entries
            // with pivot.

            // Move pivot to next-to-last position in array
            swap(a, mid, last - 1);
            pivotIndex = last - 1;
            T pivotValue = a[pivotIndex];

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
                while (a[indexFromLeft].compareTo(pivotValue) < 0)
                    indexFromLeft++;

                // Starting at end of array, leave entries that are > pivot;
                // locate first entry that is <= pivot; you will find one,
                // since first entry is <= pivot

                while (a[indexFromRight].compareTo(pivotValue) > 0)
                    indexFromRight--;

                assert a[indexFromLeft].compareTo(pivotValue) >= 0 &&
                        a[indexFromRight].compareTo(pivotValue) <= 0;

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
     * ALGORITHM THAT USES PARTITIONING TO FIND THE MEDIAN VALUE
     *       IN THE ARRAY WITHOUT FULLY SORTING
     *        ARRAY MAY BE SORTED OR UNSORTED
     **************************************************************/


    /**
     * Task: Find recursively the value at medianIndex utilizing partitioning process
     *
     * @param a           an array of Comparable objects
     * @param medianIndex an integer >= 0 that is the position of the item to return.
     * @param first       an integer >= 0 that is the index of the first
     *                    array element to consider
     * @param last        an integer >= 0 that is the index of the last
     *                    array element to consider
     */
    private static <T extends Comparable<? super T>>
    T findMedianValue(T[] a, int medianIndex, int first, int last)
    {
        //TODO Project 1 - DONE
        //   IMPLEMENT findMedianValue method RECURSIVELY
        //   HINT: call partition(a, first, last) method to get the pivotIndex
        int pivotIndex = partition(a, first, last);

        if (pivotIndex == medianIndex){
            return a[medianIndex];
        }else if (medianIndex < pivotIndex){
            a[medianIndex] = findMedianValue(a, medianIndex, first, pivotIndex - 1);
        }else{
            a[medianIndex] = findMedianValue(a, medianIndex, pivotIndex + 1, last);
        }

        //System.out.println("call with: " + medianIndex + " " + first + " " + last + " ");

        return a[medianIndex]; //THIS IS A STUB
    } // end findMedianValue

    public static int getNextPrime(int integer)
    {
        // if even, add 1 to make odd
        if (integer % 2 == 0)
        {
            integer++;
        }

        // test odd integers
        while (!isPrime(integer))
        {
            integer = integer + 2;
        }

        return integer;
    } // end getNextPrime

    private static boolean isPrime(int integer)
    {
        boolean result;
        boolean done = false;

        // 1 and even numbers are not prime
        if ((integer == 1) || (integer % 2 == 0))
        {
            result = false;
        }

        // 2 and 3 are prime
        else if ((integer == 2) || (integer == 3))
        {
            result = true;
        }

        else // integer is odd and >= 5
        {
            assert (integer % 2 != 0) && (integer >= 5);

            // a prime is odd and not divisible by every odd integer up to its square root
            result = true; // assume prime
            for (int divisor = 3; !done && (divisor * divisor <= integer); divisor = divisor + 2)
            {
                if (integer % divisor == 0)
                {
                    result = false; // divisible; not prime
                    done = true;
                }
            }
        }

        return result;
    } // end isPrime

    public static void main(String args[])
    {
        int[] arraySizes = {1, 2, 3, 5, 8, 10, 11, 25, 26, 27, 100};
        int seed = 11;  // starting with a prime number of 11
        final int MAX_RANDOM_NUMBER = 99;

        // start testing
        Integer data[];
        Integer forTesting[];
        for (int i = 0; i < arraySizes.length; i++)
        {
            System.out.println("\nTEST #" + (i + 1));
            Random generator = new Random(seed);
            // create an array and fill it with random values between 0 and MAX_RANDOM_NUMBER inclusive
            data = new Integer[arraySizes[i]];
            for (int j = 0; j < arraySizes[i]; j++)
            {
                data[j] = generator.nextInt(MAX_RANDOM_NUMBER + 1);
            }
            System.out.println("The original array is: ");
            System.out.println(Arrays.toString(data));
            // make a copy of the original array we will sort it and use it for comparing result
            forTesting = Arrays.copyOf(data, data.length);
            Arrays.sort(forTesting);

            System.out.println("The original array sorted would be: ");
            System.out.println(Arrays.toString(forTesting));

            boolean failed = false;
            int medianIndex = (0 + (data.length - 1))/2;

            Object result = findMedianValue(data, medianIndex, 0, data.length -1);

            System.out.println(">>> findMedianValue found median at index " + medianIndex + " with value of " + result + " <<<");

            if (!result.equals(forTesting[medianIndex]))
            {
                failed = true;
                System.out.println("    \u001B[35m\u001B[1mfails - item at medianIndex of " + medianIndex + " is : " + forTesting[medianIndex] +
                        " got " + result + " instead\u001B[0m");
            }

            if (!failed)
                System.out.println("    passes");

            seed = getNextPrime(++seed);
        } //end for
    }
}