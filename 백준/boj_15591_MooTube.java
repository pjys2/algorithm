package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_15591_MooTube {
    public static int N, Q;
    public static List<Node>[] mooTube;
    public static class Node implements Comparable<Node>{
        int num, usado;
        public Node(int num, int usado){
            this.num = num;
            this.usado = usado;
        }

        @Override
        public int compareTo(Node o) {
            return this.usado - o.usado;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        mooTube = new List[N+1];
        for (int i = 1; i<=N;i++){
            mooTube[i] = new ArrayList<>();
        }

        for (int i = 1; i<N;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int usado = Integer.parseInt(st.nextToken());
            mooTube[p].add(new Node(q,usado));
            mooTube[q].add(new Node(p,usado));
        }

        for (int i = 1;i<=Q;i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            BFS(k,v);
        }

    }

    public static void BFS(int k, int v){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(v,0));
        boolean[] visited = new boolean[N+1];
        visited[v] = true;

        int cnt = 0;
        while(!queue.isEmpty()){
            Node current = queue.poll();
            if(current.usado >= k){
                cnt++;
            }

            for (Node next : mooTube[current.num]){
                if (visited[next.num])continue;

                int nextUsado = next.usado;

                if (current.usado != 0) nextUsado = Math.min(nextUsado,current.usado);
                queue.add(new Node(next.num,nextUsado));
                visited[next.num] = true;
            }
        }

        System.out.println(cnt);

    }
}
