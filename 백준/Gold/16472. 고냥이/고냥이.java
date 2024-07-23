import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {

        // 문제 고려사항
        // 1. 번역기는 최대 N개 종류의 알파벳만 인식 가능하다. -> set
        // 2. 번역기는 연속된 문자열만 인식 가능하다. -> 연속된 특성 때문에 순서를 저장해야 함. -> list

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String word = br.readLine();

        int p1 = 0, p2 = 0, answer = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        Set<Character> set = new HashSet<>();
        ArrayList<Character> list = new ArrayList<>();

        while (p2 < word.length()) {

            // list는 알파벳이 들어온 순서를 보존하면서 저장하기 위한 용도
            // p2가 전진. p2가 가리키는 char를 list와 set에 추가해준다.
            char charAtP2 = word.charAt(p2);
            map.put(charAtP2, map.getOrDefault(charAtP2, 0) + 1);
//            list.add(word.charAt(p2));
//            set.add(word.charAt(p2));

            // set의 크기를 보고 저장된 알파벳의 개수가 최대 N를 넘어갔는지 검사한다.
            while (map.size() > N) {

                // 최대 개수를 넘어갔다면 p1부터 바로 이전 (p2 - 1)까지는 허용 범위로,
                // answer을 p2 - 1 - p1 + 1 = p2 - p1으로 갱신해준다.
                answer = Math.max(answer, p2 - p1);

                // list에 저장된 가장 맨 앞 친구가 제거 대상이 된다. (연속된 문자열만 측정가능하니까)
//                int idx = p1;
//                Character deleteChar = word.charAt(idx);
                char charAtP1 = word.charAt(p1);
                map.put(charAtP1, map.get(charAtP1) - 1);

                if (map.get(charAtP1) == 0) {
                    map.remove(charAtP1);
                }
                p1++;
                // 삭제 대상 char가 list에 없을때까지 list의 모든 원소들을 제거한다. 
//                while (list.contains(deleteChar)){
//                    list.remove(0); // 순서 보장, 맨 앞쪽만 계속 삭제
//                    p1++; // 삭제 횟수만큼 p1은 전진하고 있음 
//                }
                
                // list에서 완전히 제거 됐기 때문에 set에서도 제거해줘야 함.
//                set.remove(deleteChar);
            }

            // 알파벳 종류 최대 개수와 상관없이 p2는 계속 전진
            answer = Math.max(answer, p2 - p1 + 1);
            p2++;
        }

        // p2가 마지막단에 갈때까지 set은 무조건 N으로 유지할 것이다. 
        // 그러나 set.size()가 N을 넘어서지 않을 경우 answer를 갱신하지 않는다. 때문에 이에 대한 처리가 필요하다.
//        if(set.size() <= N){
//            answer = Math.max(answer, p2 - p1);
//        }

        System.out.println(answer);
    }
}