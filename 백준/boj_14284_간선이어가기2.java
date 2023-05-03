package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_14284_간선이어가기2 {
    public static int n, m;
    public static List<Node>[] nodeList;

    public static class Node implements Comparable<Node>{
        int num, len;
        public Node(int num, int len){
            this.num = num;
            this.len = len;
        }

        @Override
        public int compareTo(Node o) {
            return this.len - o.len;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        nodeList = new List[n+1];

        for (int i =1; i<=n;i++){
            nodeList[i] =  new ArrayList<>();
        }

        for (int i = 1; i<=m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodeList[a].add(new Node(b,c));
            nodeList[b].add(new Node(a,c));
        }

        st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        dijkstra(s,t);

    }
    public static void dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));

        int[] distance = new int[n+1];
        Arrays.fill(distance,999999999);

        distance[start] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if (distance[current.num] < current.len) continue;

            for (Node next : nodeList[current.num]){
                if (distance[next.num] > current.len + next.len){
                    pq.add(new Node(next.num,current.len + next.len));
                    distance[next.num] = current.len + next.len;
                }
            }
        }

        System.out.println(distance[end]);
    }

}
