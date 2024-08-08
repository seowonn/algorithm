import java.util.*;

class Solution {
    
    static boolean[] visited; 
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        // 노드 간의 연결 정보를 저장할 그래프 생성
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int node = 0; node < computers.length; node++) {
            int[] computer = computers[node];
            for(int pnt = 0; pnt < computer.length; pnt++) {
                if(computer[pnt] == 0) continue;
                
                graph.putIfAbsent(node, new ArrayList<>());
                graph.get(node).add(pnt);
            }
        }
        
        // graph의 모든 노드들을 가지고 탐색을 하면서 네트워크를 확인함
        visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                bfs(i, graph);
                answer++;
            }
        }
        return answer;
    }
    
    private void bfs(int node, Map<Integer, ArrayList<Integer>> graph) {
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        
        visited[node] = true;
        
        while(!queue.isEmpty()) {
            int curNode = queue.poll();
            
            ArrayList<Integer> adjNodes = graph.get(curNode);
            for(int adjNode : adjNodes) {
                if(!visited[adjNode]) {
                    visited[adjNode] = true;
                    queue.offer(adjNode);
                }
            }
        }
    }
}