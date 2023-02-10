package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_3584_가장가까운공통조상 {
    public static int tc,N,node1,node2;
    public static int[] parrent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        for (int t = 1; t<=tc;t++){
            N = Integer.parseInt(br.readLine());

            parrent = new int[N+1];

            for (int i = 1; i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                parrent[B] = A;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());


            List<Integer> parrentList1 = new ArrayList<>();
            List<Integer> parrentList2 = new ArrayList<>();

            DFS(node1,parrentList1);
            DFS(node2,parrentList2);

            int idx1 = parrentList1.size()-1;
            int idx2 = parrentList2.size()-1;

            int ans = 0;
            while (true){

                if (idx1 < 0 || idx2 < 0 || parrentList1.get(idx1) != parrentList2.get(idx2)){
                    ans = parrentList1.get(idx1+1);
                    break;
                }

                idx1--;
                idx2--;
            }

            System.out.println(ans);

        }
    }

    public static void DFS(int current, List<Integer> parrentList){
        if(parrent[current] == 0){
            return;
        }

        parrentList.add(current);

        DFS(parrent[current],parrentList);
    }
}
