import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int i = 0; i < T; i++) {
      int N = Integer.parseInt(br.readLine());
      int[] heights = new int[N];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        heights[j] = Integer.parseInt(st.nextToken());
      }

      Arrays.sort(heights);

      int ans = 0;
      for (int j = 0; j < N - 2; j++) {
        if(j == 0){
          ans = Math.max(ans, Math.abs(heights[0] - heights[1]));
        }

        ans = Math.max(ans, Math.abs(heights[j] - heights[j + 2]));

        if(j == (N - 3)) {
          ans = Math.max(ans, Math.abs(heights[N - 1] - heights[N - 2]));
        }
      }
      System.out.println(ans);
    }
  }
}
