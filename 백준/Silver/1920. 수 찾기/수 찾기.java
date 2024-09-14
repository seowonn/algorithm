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
      if(binarySearch(A, num, 0, A.length - 1)) {
        System.out.println(1);
      } else {
        System.out.println(0);
      }
    }
  }

  private static boolean binarySearch(int[] arr, int target, int left, int right) {
    boolean exists;

    // target 값이 존재하지 않을 경우, left나 right 값이 서로 역전 될 수 있다. 이를 대비하여
    // 재귀 탈출 장치를 마련해야 한다.
    if(left > right) return false;

    int mid = (left + right) / 2;
    if(arr[mid] < target) {
      exists = binarySearch(arr, target, mid + 1, right);
    } else if(arr[mid] == target) {
      return true;
    } else {
      exists = binarySearch(arr, target, left, mid - 1);
    }
    return exists;
  }

  public static int[] createArray(String[] str) {
    int[] array = new int[str.length];
    for(int i = 0; i < str.length; i++) {
      array[i] = Integer.parseInt(str[i]);
    }
    return array;
  }

}
