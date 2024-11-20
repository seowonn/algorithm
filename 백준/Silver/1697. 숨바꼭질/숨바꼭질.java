import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
  int src;
  int dest;
  int time;

  Point(int src, int dest, int time) {
    this.src = src;
    this.dest = dest;
    this.time = time;
  }
}

public class Main {

  static boolean[] visited = new boolean[100001];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int answer = findLeastTime(n, k);
    System.out.println(answer);
  }

  private static int findLeastTime(int n, int k) {

    Queue<Point> queue = new LinkedList<>();
    queue.add(new Point(n, k, 0));
    visited[n] = true;

    while (!queue.isEmpty()){
      Point poll = queue.poll();

      if(poll.src == poll.dest) {
        return poll.time;
      }

      if(poll.src - 1 >= 0 && !visited[poll.src - 1]) {
        visited[poll.src - 1] = true;
        queue.add(new Point(poll.src - 1, poll.dest, poll.time + 1));
      }
      if(poll.src + 1 < visited.length && !visited[poll.src + 1]) {
        visited[poll.src + 1] = true;
        queue.add(new Point(poll.src + 1, poll.dest, poll.time + 1));
      }
      if(poll.src * 2 < visited.length && !visited[poll.src * 2]) {
        visited[poll.src * 2] = true;
        queue.add(new Point(poll.src * 2, poll.dest, poll.time + 1));
      }

    }

    return -1;
  }
}
