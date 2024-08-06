import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        // 그래프 정보 (노드별 인접 노드 목록) 저장 맵
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int[] link : edge) {
            graph.putIfAbsent(link[0], new ArrayList<>());
            graph.putIfAbsent(link[1], new ArrayList<>());
            
            // 양방향 매핑
            graph.get(link[0]).add(link[1]);
            graph.get(link[1]).add(link[0]);
        }
        
        answer = bfs(1, n, graph);
            
        return answer;
    }
    
    static int bfs(int curNode, int totalNodes, Map<Integer, ArrayList<Integer>> graph) {
        
        int maxDist = 0, maxCnt = 0;
        
        boolean[] visited = new boolean[totalNodes + 1];
        visited[curNode] = true;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{curNode, 0});
            
        while(!queue.isEmpty()) {
            
            int[] node = queue.poll();
            
            // 최장 거리 갱신 및 노드 개수 저장
            if(maxDist < node[1]) {
                maxDist = node[1];
                maxCnt = maxCnt == 0 ? maxCnt + 1 : 1;
            } else if(maxDist == node[1]) {
                maxCnt++;
            }
            
            ArrayList<Integer> adjNodes = graph.get(node[0]);
            for(int adjNode : adjNodes) {
                if(!visited[adjNode]) {
                    visited[adjNode] = true;
                    queue.offer(new int[]{adjNode, node[1] + 1});
                }
            }
        }
        
        return maxCnt;
    }
}