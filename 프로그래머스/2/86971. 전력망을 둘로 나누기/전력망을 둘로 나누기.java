import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        
        int answer = Integer.MAX_VALUE;
        
        // 돌아가면서 연결 설정 안하고 그래프 만들기
        for(int skip = 0; skip < wires.length; skip++) {
            Map<Integer, ArrayList<Integer>> graph = makeGraph(skip, wires);
            
            // 연결된 노드 수 구하기 
            Integer randomKey = graph.keySet().iterator().next();
            int group1 = bfs(graph, randomKey, n);
            answer = Math.min(answer, Math.abs(group1 - (n - group1)));
        }
        return answer;
    }
    
    private Map<Integer, ArrayList<Integer>> makeGraph(int skip, int[][] wires) {
        
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int i = 0; i < wires.length; i++) {
            
            if(i == skip) continue;
            
            graph.putIfAbsent(wires[i][0], new ArrayList<>());
            graph.putIfAbsent(wires[i][1], new ArrayList<>());
            
            graph.get(wires[i][0]).add(wires[i][1]);
            graph.get(wires[i][1]).add(wires[i][0]);
        }
        
        return graph;
    }
    
    private int bfs(Map<Integer, ArrayList<Integer>> graph, int startNode, int n) {
        
        int totalCnt = 0;
        
        boolean[] visited = new boolean[n + 1];
        visited[startNode] = true;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNode);
        
        while(!queue.isEmpty()) {
            int node = queue.poll();
            
            totalCnt++;
            
            for(Integer nextNode : graph.get(node)){
                if(!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.offer(nextNode);
                }
            }
        }
        
        return totalCnt;
    }
}