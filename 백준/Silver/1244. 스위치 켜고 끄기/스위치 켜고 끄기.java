import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[] lights = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < lights.length; i++) {
            lights[i] = Integer.parseInt(st.nextToken()) == 1;
        }

        int students = Integer.parseInt(br.readLine());
        int[][] infos = new int[students][2];
        for (int i = 0; i < students; i++) {
            st = new StringTokenizer(br.readLine());
            infos[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        for (int i = 0; i < students; i++) {
            int[] info = infos[i];
            if(info[0] == 1) {
                for (int j = 0; j < lights.length; j++) {
                    if((j + 1) % info[1] == 0) {
                        lights[j] = !lights[j];
                    }
                }
            } else {
                int center = info[1] - 1;
                int gap = 0;

                while ((center - gap) >= 0 && (center + gap) <= lights.length - 1) {
                    if(lights[center - gap] == lights[center + gap]) {
                        gap++;
                    } else {
                        break;
                    }
                }
                for (int j = center - (gap - 1); j <= center + (gap - 1); j++) {
                    lights[j] = !lights[j];
                }
            }
        }
        for(int i=0; i< lights.length; i++) {
            System.out.print((lights[i] ? 1 : 0) + " ");
            if((i+1) % 20 == 0)
                System.out.println();
        }
    }


}