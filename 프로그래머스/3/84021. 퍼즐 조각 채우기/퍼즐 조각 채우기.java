import java.util.*;

class Solution {
    
    static int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int N, M;
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        
        N = game_board.length;
        M = game_board[0].length;
        
        // 빈칸과 퍼즐 조각을 추출하고 정규화
        ArrayList<ArrayList<int[]>> blankSpaces = new ArrayList<>();
        ArrayList<ArrayList<int[]>> puzzles = new ArrayList<>();
        
        boolean[][] gameMapVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (game_board[i][j] == 0 && !gameMapVisited[i][j]) {
                    blankSpaces.add(normalize(bfs(i, j, game_board, gameMapVisited, 0)));
                }
            }
        }
        
        boolean[][] tableMapVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (table[i][j] == 1 && !tableMapVisited[i][j]) {
                    puzzles.add(normalize(bfs(i, j, table, tableMapVisited, 1)));
                }
            }
        }
        
        // 빈칸과 퍼즐을 비교하여 맞는 퍼즐이 있으면 채움
        for (ArrayList<int[]> blankSpace : blankSpaces) {
            boolean found = false;
            for (int i = 0; i < puzzles.size(); i++) {
                ArrayList<int[]> puzzle = puzzles.get(i);
                
                for (int rotation = 0; rotation < 4; rotation++) {
                    if (areShapesEqual(blankSpace, puzzle)) {
                        answer += puzzle.size();
                        puzzles.remove(i);
                        found = true;
                        break;
                    }
                    puzzle = rotate(puzzle);
                }
                if (found) break;
            }
        }
        
        return answer;
    }
    
    public static boolean areShapesEqual(ArrayList<int[]> shape1, ArrayList<int[]> shape2) {
        if (shape1.size() != shape2.size()) {
            return false;
        }
        
        for (int i = 0; i < shape1.size(); i++) {
            if (!Arrays.equals(shape1.get(i), shape2.get(i))) {
                return false;
            }
        }
        
        return true;
    }
    
    // bfs 함수에서 빈칸 또는 퍼즐 조각을 추출
    public static ArrayList<int[]> bfs(int i, int j, int[][] board, boolean[][] visited, int targetValue) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{i, j});
        visited[i][j] = true;
        
        int startX = i, startY = j;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            for (int[] dir : dirs) {
                int nextR = cur[0] + dir[0];
                int nextC = cur[1] + dir[1];

                if (isInRange(nextR, nextC) && !visited[nextR][nextC] && board[nextR][nextC] == targetValue) {
                    visited[nextR][nextC] = true;
                    queue.offer(new int[]{nextR, nextC});
                    list.add(new int[]{nextR, nextC});
                }
            }
        }
        
        return list;
    }
    
    // 퍼즐을 90도 회전
    public static ArrayList<int[]> rotate(ArrayList<int[]> shape) {
        ArrayList<int[]> rotatedShape = new ArrayList<>();
        
        for (int[] coord : shape) {
            rotatedShape.add(new int[]{-coord[1], coord[0]});
        }
        
        return normalize(rotatedShape);
    }
    
    public static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
    
    // 좌표를 정규화하여 (0, 0)을 기준으로 맞춤
    public static ArrayList<int[]> normalize(ArrayList<int[]> shape) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        
        // 좌측 상단의 기준점 찾기
        for (int[] coord : shape) {
            minX = Math.min(minX, coord[0]);
            minY = Math.min(minY, coord[1]);
        }
        
        // 모든 좌표를 (minX, minY) 기준으로 이동
        ArrayList<int[]> normalizedShape = new ArrayList<>();
        for (int[] coord : shape) {
            normalizedShape.add(new int[]{coord[0] - minX, coord[1] - minY});
        }
        
        // 좌표를 정렬하여 일관성 유지
        normalizedShape.sort((a, b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        
        return normalizedShape;
    }
}
