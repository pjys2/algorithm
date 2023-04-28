package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_17396_백도어 {
    public static int N, M;
    public static int[] sight;
    public static List<Node>[] nodeList;
    public static class Node implements Comparable<Node>{
        int num;
        long time;
        public Node(int num, long time){
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return (int)(this.time - o.time);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sight = new int[N];
        nodeList = new List[N];

        for (int i =0;i<N;i++){
            nodeList[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i<N;i++){
            sight[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i<=M;i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long len = Integer.parseInt(st.nextToken());
            if ((sight[a] == 1 && a != N-1) || ( sight[b] == 1 && b!= N-1)) continue;
            nodeList[a].add(new Node(b,len));
            nodeList[b].add(new Node(a,len));
        }

        dijkstra();
    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0));

        long[] distance = new long[N];
        Arrays.fill(distance,Long.MAX_VALUE);
        distance[0] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if (current.num == N-1){
                System.out.println(current.time);
                return;
            }

            if (distance[current.num] < current.time) continue;

            for (Node next : nodeList[current.num]){
                if (distance[next.num] <= current.time + next.time) continue;

                pq.add(new Node(next.num,current.time + next.time));
                distance[next.num] = current.time + next.time;
            }
        }

        System.out.println(-1);
    }
}
