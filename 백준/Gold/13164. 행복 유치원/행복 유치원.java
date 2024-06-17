import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] st = br.readLine().split(" ");
    int N = Integer.parseInt(st[0]);
    int K = Integer.parseInt(st[1]);

    int[] heights = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();

    int[] diff = new int[N - 1];
    for (int i = 0; i < N - 1; i++) {
      diff[i] = heights[i + 1] - heights[i];
    }

    Arrays.sort(diff);
    int answer = 0;
    for (int i = 0; i < N - K; i++) {
      answer += diff[i];
    }

    System.out.println(answer);
  }

}
