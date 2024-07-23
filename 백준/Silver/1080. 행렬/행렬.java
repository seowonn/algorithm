import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] matrixA;
    static int[][] matrixB;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        matrixA = new int[N][M];
        matrixB = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                matrixA[i][j] = Integer.parseInt(Character.toString(line.charAt(j)));
            }
        }

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                matrixB[i][j] = Integer.parseInt(Character.toString(line.charAt(j)));
            }
        }

        // 행렬A를 행렬B로 완전히 일치시킬려면 당연히 다른 부분은 바꿔줘야 한다. 때문에 그리디 알고리즘 이용!
        for (int i = 0; i <= N - 3; i++) {
            for (int j = 0; j <= M - 3; j++) {
                if(matrixA[i][j] != matrixB[i][j]){
                    reverse(i, j);
                    answer++;
                }
            }
        }

        boolean isSame = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrixA[i][j] != matrixB[i][j]) {
                    isSame = false;
                    break;
                }
            }
            if (!isSame) {
                break;
            }
        }

        System.out.println(isSame ? answer : -1);
    }

    static void reverse(int startRow, int startCol) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrixA[startRow + i][startCol + j] = matrixA[startRow + i][startCol + j] == 1 ? 0 : 1;
            }
        }
    }
}