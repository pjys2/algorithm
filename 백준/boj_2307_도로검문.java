package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2307_도로검문 {

    public static int N, M;
    public static int[] parrent;
    public static List<Node>[] nodeList;
    public static class Node implements Comparable<Node>{
        int num, time;
        public Node(int num, int time){
            this.num = num;
            this.time = time;

        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodeList = new List[N+1];


        //최단거리 부모 노드 구하기
        parrent = new int[N+1];

        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i =1; i<=M;i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            nodeList[a].add(new Node(b,t));
            nodeList[b].add(new Node(a,t));
        }

        int minDis = dijkstra(0,0);

        int ans = 0;
        for (int i = 2; i<=N;i++){
            int distance = dijkstra(parrent[i],i);
            ans = Math.max(ans, distance - minDis);

            if (distance == 999999999){
                ans = -1;
                break;
            }
        }


        System.out.println(ans);
    }

    public static int dijkstra(int roadS, int roadE){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));

        int[] distance = new int[N+1];

        Arrays.fill(distance,999999999);

        distance[1] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if (current.num == N) return distance[N];

            if (distance[current.num] < current.time) continue;

            for (Node next : nodeList[current.num]){
                if (current.num == roadS && next.num == roadE) continue;
                if (next.num == roadS && current.num== roadE) continue;

                if (distance[next.num] > current.time + next.time){
                    pq.add(new Node(next.num,current.time + next.time));
                    distance[next.num] = current.time + next.time;
                    parrent[next.num] = current.num;
                }
            }
        }

        return distance[N];
    }
}
