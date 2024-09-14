import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[] A = createArray(br.readLine().split(" "));

    int M = Integer.parseInt(br.readLine());
    int[] B = createArray(br.readLine().split(" "));

    Arrays.sort(A);
    for(int num : B) {
      if(binarySearch2(A, num)) {
        System.out.println(1);
      } else {
        System.out.println(0);
      }
    }
  }

  private static boolean binarySearch2(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
      int mid = (left + right) / 2;
      if(arr[mid] < target) {
        left = mid + 1;
      } else if(arr[mid] > target) {
        right = mid - 1;
      } else {
        return true;
      }
    }
    return false;
  }

  public static int[] createArray(String[] str) {
    int[] array = new int[str.length];
    for(int i = 0; i < str.length; i++) {
      array[i] = Integer.parseInt(str[i]);
    }
    return array;
  }

}
