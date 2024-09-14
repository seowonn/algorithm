import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static int[][] office;
  static List<int[]> cctvs = new ArrayList<>();
  static int minBlindSpot = Integer.MAX_VALUE;
  static int[] dx = {0, -1, 0, 1}; // 0=right, 1=up, 2=left, 3=down
  static int[] dy = {1, 0, -1, 0};
  static int[][][] directions = {
      {}, // 0번 cctv는 없으니까 빈 칸 처리
      {{0}, {1}, {2}, {3}}, // 1번 cctv는 상, 우, 하, 좌 경우로 탐색 가능
      {{0, 2}, {1, 3}}, // 2번은 상하, 좌우 경우로 탐색 가능
      {{0, 1}, {1, 2}, {2, 3}, {3, 0}}, // 3번은 상우, 우하, 하좌, 좌상 경우로 탐색이 가능
      {{3, 0, 1}, {0, 1, 2}, {1, 2, 3}, {2, 3, 0}}, // 4번은 세 방향 탐색으로 좌하우, 하우상, 우상좌, 상좌하 경우로 탐색이 가능
      {{0, 1, 2, 3}}  // 5번은 네 방향을 감시한다.
  };

  // 맵 복사하는 함수
  static int[][] copyMap(int[][] map) {
    int[][] newMap = new int[N][M];
    for(int i = 0; i < N; i++) {
      System.arraycopy(map[i], 0, newMap[i], 0, M);
    }
    return newMap;
  }

  // 감시하는 함수
  static void watch(int[][] map, int x, int y, int dir) {
    int nx = x + dx[dir];
    int ny = y + dy[dir];
    while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
      if(map[nx][ny] == 6) break; // 벽을 만나면 멈춤
      if(map[nx][ny] == 0) map[nx][ny] = 7; // 감시 영역을 7로 표시
      nx += dx[dir];
      ny += dy[dir];
    }
  }

  // CCTV를 적용하는 함수
  static void setCCTV(int[][] map, int cctvType, int x, int y, int[] dirs) {
    for(int dir : dirs) {
      watch(map, x, y, dir);
    }
  }

  // DFS를 통한 모든 경우 탐색
  static void dfs(int depth, int[][] map) {
    if(depth == cctvs.size()) {
      // 사각지대 계산
      int blindSpot = 0;
      for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
          if(map[i][j] == 0) blindSpot++;
        }
      }
      minBlindSpot = Math.min(minBlindSpot, blindSpot);
      return;
    }

    int[] cctv = cctvs.get(depth);
    int x = cctv[0], y = cctv[1], type = cctv[2];

    // 각 CCTV의 모든 방향에 대해 탐색
    for(int[] dirs : directions[type]) {
      int[][] newMap = copyMap(map);
      setCCTV(newMap, type, x, y, dirs);
      dfs(depth + 1, newMap);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 첫 번째 줄에서 N과 M 읽기
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    office = new int[N][M];

    // 사무실 정보 입력
    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < M; j++) {
        office[i][j] = Integer.parseInt(st.nextToken());
        if(office[i][j] >= 1 && office[i][j] <= 5) {
          cctvs.add(new int[]{i, j, office[i][j]});
        }
      }
    }

    // DFS 탐색 시작
    dfs(0, office);

    // 최소 사각지대 출력
    System.out.println(minBlindSpot);
  }

}
