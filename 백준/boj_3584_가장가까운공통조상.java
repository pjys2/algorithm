package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_3584_가장가까운공통조상 {
    public static int tc,N,node1,node2,ans;
    public static int[] parrent;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        for (int t = 1; t<=tc;t++){
            N = Integer.parseInt(br.readLine());

            parrent = new int[N+1];
            visited = new boolean[N+1];
            for (int i = 1; i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                parrent[B] = A;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());

            ans = 0;

            DFS(node1);
            DFS(node2);



            System.out.println(ans);

        }
    }

    public static void DFS(int current){
        if(visited[current]){
            ans = current;
            return;
        }

        visited[current] = true;

        DFS(parrent[current]);
    }
}
