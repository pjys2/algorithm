package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1162_도로포장 {
    public static int N, M, K;
    public static List<Node>[] nodeList;
    public static class Node implements Comparable<Node>{
        int num, cnt;
        long len;

        public Node(int num, long len, int cnt){
            this.num = num;
            this.len = len;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return (int)(this.len - o.len);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        nodeList = new List[N+1];
        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i =1 ;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            nodeList[a].add(new Node(b,l,0));
            nodeList[b].add(new Node(a,l,0));
        }

        dijkstra();
    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0,0));


        long[][] distance = new long[N+1][K+1];

        for (int i = 1; i<=N;i++){
            Arrays.fill(distance[i],Long.MAX_VALUE);
        }

        distance[1][0] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if (current.len > distance[current.num][current.cnt]) continue;


            for (Node next : nodeList[current.num]){
                long nextLen = current.len + next.len;
                if (distance[next.num][current.cnt] > nextLen){
                    distance[next.num][current.cnt] = nextLen;
                    pq.add(new Node(next.num,nextLen,current.cnt));
                }

                if (current.cnt < K && distance[next.num][current.cnt+1] > current.len){
                    distance[next.num][current.cnt+1] = current.len;
                    pq.add(new Node(next.num,current.len,current.cnt+1));
                }
            }
        }


        long ans = Long.MAX_VALUE;

        for (int i = 0; i<= K;i++){
            ans = Math.min(ans,distance[N][i]);
        }


        System.out.println(ans);
    }
}
