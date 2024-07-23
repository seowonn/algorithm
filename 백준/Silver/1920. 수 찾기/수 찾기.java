import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] cards;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        cards = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int cardsNumHeHave = Integer.parseInt(br.readLine());
        int[] cardsHeHave = new int[cardsNumHeHave];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cardsNumHeHave; i++) {
            cardsHeHave[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        for (int i = 0; i < cardsNumHeHave; i++) {
            System.out.println(has(cardsHeHave[i]) ? 1 : 0);
        }

    }

    private static boolean has(int target) {
        int left = 0;
        int right = cards.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(cards[mid] == target){
                return true;
            } else if(cards[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}