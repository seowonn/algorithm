class Solution {
    
    // 던전 수는 전체에서 최댓값이니까 static으로 선언
    static int answer = 0;
    // 방문했던 지점 구분을 위한 visited 배열 static 선언
    static boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        
        int n = dungeons.length;
        
        // visited 배열 초기화
        visited = new boolean[n];
                
        countDungeons(dungeons, 0, k);
        // backtrack(k, n, dungeons, 0);
        
        return answer;
    }
    
    public static void countDungeons(int[][] dungeons, int cnt, int currK){
        
        // 갱신 조건
        if(cnt > answer){
            answer = Math.max(answer, cnt);
        }
        
//         int[] dungeon = dungeons[currDun];
//         // 체킹 조건 : 해당 던전의 최소 필요 피로도가 현재 내 피로도 k보다 같거나 작은지 검사
//         if(dungeon[0] > currK){
//             return;
//         }

//         currK -= dungeon[1];
//         cnt++;
        
        // dungeons의 각각의 던전 별로 다 순회해야 함으로 for문 순회
        for(int i = 0; i < dungeons.length; i++){
            if(visited[i] || dungeons[i][0] > currK){
                continue;
            }
            visited[i] = true;
            countDungeons(dungeons, cnt + 1, currK - dungeons[i][1]);
            visited[i] = false;
        }
    }
    
    public static void backtrack(int k, int n, int[][] dungeons, int cnt){
        // basecase
        if(cnt > answer) {
            answer = cnt;
        }
        
        // recursive call
        for(int i = 0; i < n; i++){
            if(k >= dungeons[i][0] && !visited[i]){
                visited[i] = true;
                backtrack(k - dungeons[i][1], n, dungeons, cnt + 1);
                visited[i] = false;
            }
            
        }
    }
}