import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] datas = new int[10_000_000 * 2 + 1 + 1];

        int[] cards = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 숫자 카드에는 음수가 적힐 수도 있다.
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            datas[cards[i] + 10_000_000]++;
        }

        int cardsNumHeHave = Integer.parseInt(br.readLine());
        int[] cardsHeHave = new int[cardsNumHeHave];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cardsNumHeHave; i++) {
            cardsHeHave[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[cardsNumHeHave];

        Arrays.sort(cards);
        for (int i = 0; i < cardsNumHeHave; i++) {
            answer[i] = datas[cardsHeHave[i] + 10_000_000];
        }

        String collect = Arrays.stream(answer).mapToObj(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(collect);
    }
}