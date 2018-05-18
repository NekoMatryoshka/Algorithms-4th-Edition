public class Sort{
    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void selection(Comparable[] a) {  //find the min in the array, put it in the a[0], then find the min of the remain elements, put it in the a[1]...
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[min]) < 0)
                    min = j;
            }
            swap(a, i, min);
        }
    }

    public static void insertion(Comparable[] a) { // divide the array into sorted and unsorted, in each iteration take the first unsorted element and put it into right place in sorted group
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j --) {  // the element before i are in sorted group, while i is the first unsorted element
                if (a[j].compareTo(a[j-1] < 0)){  // if the element before i is larger than i, swap them and continue comparing
                    swap(a, j, j-1)
                } else  // until element before i is smaller than i, which in a sorted group means i is in its right position, then break for next first unsorted element
                    break;
            }
        }
    }

    public static void shell(Comparable[] a) {  // do the large-increment insertion sort(h-sort) first, and then reduce the value of increment to do small-scale insertion sort until increment=1 for the final sort. reduce the swap times compared to direct insertion sort.
        int h = 1;  // increment does not have to be 3x+1, but it is easy to compute, the differ between increments are large enough, and the final increment is 1.
        while (h < a.length/3)  // 3x+1 increment sequence, let the increment be large at first (if increment < 1/3 of the length, let increment*3+1)
            h = 3*h + 1;  // +1 because the last increment value should better be 1 to do the final insertion sort of all elements

        // do the h-sort
        while (h > 1) {
            for (int i = h; i < a.length; i++) {  // do the insertion sort for this increment
                for (int j = 1; j >= h && a[j].compareTo(a[j-h]) < 0; j -= h)  // let i(increment + 1) compares to the j(i - increment = 1) and if i is smaller, swap them
                    swap(a, j, j-h);
            }
            h = h/3;  // let increment /3 to the next more-small-scaled h-sort
        }
    }
}
