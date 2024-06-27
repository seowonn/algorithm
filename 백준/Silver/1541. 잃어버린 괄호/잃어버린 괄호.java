import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] string = br.readLine().split("");

        int answer = 0, subSum = 0, num = 0;
        boolean wasMinus = false;
        StringBuilder sb = new StringBuilder();
        for (String s : string) {
            if(s.equals("+") || s.equals("-")) {
                num = Integer.parseInt(sb.toString());
                if(s.equals("+")) {
                    if(wasMinus) {
                        subSum += num;
                    } else {
                        answer += num;
                    }
                } else {
                    if(!wasMinus) {
                        answer += num;
                        wasMinus = true;
                    } else {
                        answer -= num;
                    }
                }
                sb.setLength(0);
            } else {
                sb.append(s);
            }
        }

        if(wasMinus) {
            subSum += Integer.parseInt(sb.toString());
            answer -= subSum;
        } else {
            answer += Integer.parseInt(sb.toString());
        }

        System.out.println(answer);

    }

}
