package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_5430_AC {
    public static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc<=T;tc++){
            String func = br.readLine();

            int n = Integer.parseInt(br.readLine());
            String input = br.readLine();

            input = input.replace("[","");
            input = input.replace("]","");

            StringTokenizer st = new StringTokenizer(input,",");
            Deque<Integer> deque = new LinkedList<>();

            int tokenSize = st.countTokens();

            for (int i = 0; i<tokenSize;i++){
                deque.add(Integer.parseInt(st.nextToken()));
            }

            boolean error = false;

            boolean isFirst = true;
            for (int i = 0; i<func.length();i++){
                char c = func.charAt(i);

                if (c == 'R'){
                    isFirst = !isFirst;
                }else if (c == 'D'){
                    if (deque.isEmpty()){
                        System.out.println("error");
                        error = true;
                        break;
                    }


                    if (isFirst){
                        deque.pollFirst();
                    }else{
                        deque.pollLast();
                    }
                }
            }

            if (error) continue;

            int qSize = deque.size();

            StringBuilder sb = new StringBuilder();
            sb.append("[");

            if (isFirst){
                for (int i = 0; i<qSize;i++){
                    sb.append(deque.pollFirst());
                    if (i != qSize-1){
                        sb.append(",");
                    }
                }
            }else{
                for (int i = 0; i<qSize;i++){
                    sb.append(deque.pollLast());
                    if (i != qSize-1){
                        sb.append(",");
                    }
                }
            }

            sb.append("]");

            System.out.println(sb);
        }
    }
}
