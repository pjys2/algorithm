package 백준;

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

        return find(parrents[a]);
    }

    public static void union(int a, int b){

        int parrentA = find(a);
        int parrentB = find(b);


        parrents[parrentB] = parrentA;

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
                if (find(a) != find(b)){
                    union(a,b);
                }

                a = b;
            }
        }


        for (int i = 1; i<=N;i++){
            if (people[i]){
                int root = find(i);
                people[root] = true;
            }
        }


        int ans = 0;

//        System.out.println(Arrays.toString(parrents));
//        System.out.println(Arrays.toString(people));
        for (int i = 1; i<=M;i++){
            boolean isPossible = true;
            for (int p : partyList[i]){
                int root = find(p);
                if (people[root]) {
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
