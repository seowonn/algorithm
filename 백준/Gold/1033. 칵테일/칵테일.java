import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 나와 상대 노드간의 비율을 온전히 저장해놓기 위한 클래스
class cNode {
    int b;  // 상대 노드 번호
    int p;  // 나의 노드 비율
    int q;  // 상대 노드 비율

    public cNode(int b, int p, int q) {
        this.b = b;
        this.p = p;
        this.q = q;
    }

    public int getB() {
        return b;
    }
    public int getP() {
        return p;
    }
    public int getQ() {
        return q;
    }
}

public class Main {

    // 입력 데이터와 각 재료들 간의 관계를 온전히 저장하기 위한 인접리스트
    static ArrayList<cNode>[] A;
    // DFS 탐색 시 필요한 배열
    static boolean visited[];
    // 비율을 환산한 질량 값을 저장하는 배열
    static long D[];
    // 입력 데이터들의 최소공배수를 저장하는 변수
    static long lcm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        A = new ArrayList[N];
        visited = new boolean[N];
        D = new long[N];
        lcm = 1;
        // 인접리스트 초기화
        for (int i = 0; i < N; i++) {
            A[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            A[a].add(new cNode(b, p, q));
            A[b].add(new cNode(a, q, p));
            lcm *= (p * q / gcd(p, q));
        }
        // 초기 셋팅을 최소공배수로 설정
        D[0] = lcm;
        // 초기 노드부터 탐색
        DFS(0);
        // 최소 질량 합을 위한 최대공약수 저장 변수
        long mgcd = D[0];
        for (int i = 1; i < N; i++) {
            mgcd = gcd(mgcd, D[i]);
        }
        for (int i = 0; i < N; i++) {
            System.out.print(D[i] / mgcd + " ");
        }
    }

    public static long gcd(long a, long b) {
        if(b == 0){
            return a;
        }
        return gcd(b, a % b);
    }

    public static void DFS(int Node){
        visited[Node] = true;
        for(cNode i : A[Node]) {
            int next = i.getB();
            if(!visited[next]) {
                D[next] = D[Node] * i.getQ() / i.getP();
                DFS(next);
            }
        }
    }

}