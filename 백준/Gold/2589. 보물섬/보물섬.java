import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static boolean[][] visited;
    static int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    static int[][] shortCut;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        String[][] board = new String[row][col];


        int answer = 0;
        for (int i = 0; i < row; i++) {
            String[] word = br.readLine().split("");
            System.arraycopy(word, 0, board[i], 0, word.length);
        }

        // 보물 후보지 기록
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 0; j < col; j++) {
                if(board[i][j].equals("L")){
                    visited = new boolean[row][col];
                    visited[i][j] = true;
                    shortCut = new int[row][col];
                    answer = Math.max(answer, bfs(i, j, board));
                }
            }
        }

        System.out.println(answer);
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                System.out.print(shortCut[i][j] + "    ");
//            }
//            System.out.println();
//        }
    }

    private static int bfs(int row, int col, String[][] board) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});

        int recentCnt = 0;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for(int[] dir : dirs) {
                int nextRow = poll[0] + dir[0];
                int nextCol = poll[1] + dir[1];

                if(nextRow >= 0 && nextRow < board.length && nextCol >= 0 && nextCol < board[0].length && board[nextRow][nextCol].equals("L") && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol});
                    shortCut[nextRow][nextCol] = shortCut[poll[0]][poll[1]] + 1;
                    recentCnt = shortCut[nextRow][nextCol];
                }
            }
        }

        return recentCnt;
    }

}
