package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_10282_해킹 {
    public static int n,d,c;
    public static List<Node>[] nodeList;
    public static class Node implements Comparable<Node>{
        int num, t;
        public Node(int num, int t){
            this.num = num;
            this.t = t;
        }

        @Override
        public int compareTo(Node o) {
            return this.t - o.t;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc<=T;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            nodeList = new List[n+1];

            for (int i = 1; i<=n;i++){
                nodeList[i] = new ArrayList<>();
            }

            for (int i = 0; i<d;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                nodeList[b].add(new Node(a,t));
            }


            dikjstra(c);

        }


    }

    public static void dikjstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[n+1];
        Arrays.fill(distance, 999999999);
        pq.add(new Node(start,0));
        boolean[] visited = new boolean[n+1];
        visited[start] = true;
        distance[start] = 0;
        int cnt = 0;
        int time = 0;
        while(!pq.isEmpty()){
            Node current = pq.poll();
            visited[current.num] = true;

            for (Node next : nodeList[current.num]){
                if(!visited[next.num] && distance[next.num] > distance[current.num] + next.t){
                    pq.add(new Node(next.num,distance[current.num] + next.t));
                    distance[next.num] = distance[current.num] + next.t;
                }
            }
        }

        for (int i =1; i<=n;i++){
            if (distance[i]<999999999){
                cnt++;
                time = Math.max(time,distance[i]);
            }
        }

        System.out.println(cnt+" "+time);

    }
}
