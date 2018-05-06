public class SearchInABitonicArray {

    public static int binarySearch(int key, int[] a, boolean isIncreasing, int from, int to) {  //lgN for one binary search
        int lo = from, hi = to - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                if (isIncreasing)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            else if (key > a[mid]) {
                if (isIncreasing)
                    lo = mid + 1;
                else
                    hi = mid - 1;
            }
            else
                return mid;
        }

        return -1;
    }


    public static int bitonicSearch3lgN(int key, int[] a) {  //3lgN in worst case
        int lo = 0, hi = a.length - 1;
        int max = -1;

        while (lo <= hi) {  //find max: lgN
            int mid = lo + (hi - lo) / 2;
            if (mid == 0 || mid == a.length - 1)
                max = mid;
            else if (a[mid - 1] > a[mid])
                hi = mid - 1;
            else if (a[mid + 1] > a[mid])
                lo = mid + 1;
            else
                max = mid;
        }

        int res = -1;

        if ((res = binarySearch(key, a, true, 0, max)) != -1)  //binary search the increasing part: lgN
            return res;
        else
            return binarySearch(key, a, true, max, a.length - 1);  //binary search the decreasing part: lgN
    }

    public static int bitonicSearch2lgN(int key, int[] a, int from, int to) {

        int mid = from + (from - 1 - to) / 2;

        if (a[mid] == key)
            return mid;
        if (a[mid] > a[mid - 1]) {
            if key > a[mid]
                return bitonicSearch2lgN(key, a, mid + 1, to)
            else {
                int res = -1;
                i
                    ((res = binarySearch(key, a, true, )))
            }
        }
    }
}
