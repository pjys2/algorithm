package 백준;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1043_거짓말 {
    public static int N, M;
    public static boolean[] people;
    public static int[] parrents;
    public static void makeSet(int n){
        for (int i = 1 ;i<=n;i++){
            parrents[i] = i;
        }
    }

    public static int find(int a){
        if (parrents[a] == a) return a;

        int ret = find(parrents[a]);

        if (people[a]){
            people[parrents[a]] = true;
        }

        if (people[parrents[a]]){
            people[a] = true;
        }

        return ret;
    }

    public static void union(int a, int b){

        int parrentB = find(b);

        if (people[a]) people[b] = true;
        if (people[b]) people[a] = true;

        parrents[parrentB] = a;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parrents = new int[N+1];
        people = new boolean[N+1];

        st = new StringTokenizer(br.readLine());



        int pNum = Integer.parseInt(st.nextToken());


        makeSet(N);

        for (int i = 0; i<pNum;i++){
            int num = Integer.parseInt(st.nextToken());
            people[num] = true;
        }



        List<Integer>[] partyList = new List[M+1];

        for (int i = 1; i<=M;i++){
            partyList[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());

            int a = Integer.parseInt(st.nextToken());
            partyList[i].add(a);
            for (int j = 1;j<num;j++){
                int b = Integer.parseInt(st.nextToken());
                partyList[i].add(b);
                union(a,b);

                a = b;
            }
        }

        int ans = 0;

        System.out.println(Arrays.toString(parrents));
        System.out.println(Arrays.toString(people));
        for (int i = 1; i<=M;i++){
            boolean isPossible = true;
            for (int p : partyList[i]){
                if (people[p]) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible){
                ans++;
            }
        }



        System.out.println(ans);
    }
}
