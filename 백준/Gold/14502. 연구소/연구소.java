import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int[][] map;
    static int N, M;
    static int maxSafeArea = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        buildWalls(0);
        System.out.println(maxSafeArea);

    }

    static void buildWalls(int count) {
        if(count == 3) {
            spreadVirus();
            return;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    buildWalls(count + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static void spreadVirus() {

        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = map[i].clone();
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(copy[i][j] == 2) {
                     queue.offer(new int[]{i, j});
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] point = queue.poll();

            for(int[] dir : dirs) {
                int nextR = point[0] + dir[0];
                int nextC = point[1] + dir[1];

                if(isInRange(nextR, nextC) && copy[nextR][nextC] == 0) {
                    copy[nextR][nextC] = 2;
                    queue.offer(new int[]{nextR, nextC});
                }
            }
        }

        countSafeArea(copy);
    }

    static void countSafeArea(int[][] copy) {
        int safeCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(copy[i][j] == 0) {   // 바이러스가 퍼지지 않은 안전 영역인 경우
                    safeCount++;
                }
            }
        }
        maxSafeArea = Math.max(maxSafeArea, safeCount);
    }

    static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

}