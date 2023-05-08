package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1043_거짓말 {
    public static int N, M;
    public static boolean[] people;
    public static List<Integer>[] partyList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        people = new boolean[N+1];


        st = new StringTokenizer(br.readLine());



        int pNum = Integer.parseInt(st.nextToken());

        for (int i = 0; i<pNum;i++){
            int num = Integer.parseInt(st.nextToken());
            people[num] = true;
        }


        partyList = new List[M+1];

        for (int i = 1; i<=M;i++){
            partyList[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());

            boolean isTrue = true;
            for (int j = 1; j<=num;j++){
                int p =  Integer.parseInt(st.nextToken());
                partyList[i].add(p);
                if (people[p]){
                    isTrue = false;
                }
            }

            if (!isTrue){
                for (int p : partyList[i]){
                    people[p] = true;
                }
            }

        }

        int ans = 0;
        for (int i = 1; i<=M;i++){
            boolean isPossible = true;
            for (int p : partyList[i]){
                if (people[p]){
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) ans++;

        }


        System.out.println(ans);
    }
}
