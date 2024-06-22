import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int[][] board;
  static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int M = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    board = new int[M][N];
    ArrayList<Integer> list = new ArrayList<>();
    // 사각형 영역 색칠
    for(int i = 0; i < K; i++) {
      String[] points = br.readLine().split(" ");
      for (int row = Integer.parseInt(points[1]); row < Integer.parseInt(points[3]); row++) {
        for (int col = Integer.parseInt(points[0]); col < Integer.parseInt(points[2]); col++) {
          board[row][col] = 1;
        }
      }
    }

    // 빈 영역 찾기 - 4방향 서치, BFS 사용(visited[][](board[][]), queue)
    for (int i = M - 1; i >= 0; i--)  {
      for(int j = 0; j < N; j++) {
        if(board[i][j] == 0) {
          board[i][j] = 1;
          list.add(bfs(i, j, 0, board));
        }
      }
    }

    System.out.println(list.size());
    Collections.sort(list);
    for(int num : list) {
      System.out.print(num + " ");
    }
  }

  public static int bfs(int i, int j, int cnt, int[][] board){

    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{i,j});

    while(!queue.isEmpty()) {
      
      int[] pos = queue.poll();
      cnt++;

      int shiftX, shiftY;
      for(int[] dir : dirs) {
        shiftX = pos[0] + dir[0];
        shiftY = pos[1] + dir[1];

        if(shiftX >= 0 && shiftX < board.length && shiftY >= 0 && shiftY < board[0].length && board[shiftX][shiftY] == 0) {
          board[shiftX][shiftY] = 1;
          queue.add(new int[]{shiftX, shiftY});
        }
      }
    }

    return cnt;
  }

}
