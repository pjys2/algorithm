package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_5972_택배배송 {
    public static int N, M;
    public static List<Node>[] nodeList;
    public static class Node implements Comparable<Node>{
        int num, cost;

        public Node(int num, int cost){
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodeList = new List[N+1];

        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 1; i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodeList[a].add(new Node(b,c));
            nodeList[b].add(new Node(a,c));
        }

        dijkstra();
    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));

        int[] distance = new int[N+1];
        Arrays.fill(distance,Integer.MAX_VALUE);

        distance[1] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            for (Node next : nodeList[current.num]){
                if (distance[next.num] > current.cost + next.cost){
                    pq.add(new Node(next.num,current.cost + next.cost));
                    distance[next.num] = current.cost + next.cost;
                }
            }
        }

        System.out.println(distance[N]);
    }
}
