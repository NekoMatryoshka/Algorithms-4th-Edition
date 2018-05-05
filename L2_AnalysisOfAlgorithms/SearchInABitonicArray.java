public class SearchInABitonicArray {
    public static int search(int key, int[] bitonicArr) {
        int lo = 0, hi = bitonicArr.length - 1;
        int max = -1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (mid == 0 || mid == bitonicArr.length - 1)
                max = mid;
            else if (bitonicArr[mid - 1] > bitonicArr[mid])
                hi = mid - 1;
            else if (bitonicArr[mid + 1] > bitonicArr[mid])
                lo = mid + 1;
            else
                max = mid;
        }

        lo = 0; hi = bitonicArr.length - 1;

        while (lo <= hi) {
        }
    }
}
