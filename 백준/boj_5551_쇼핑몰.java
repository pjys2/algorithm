package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_5551_쇼핑몰 {
    public static int N, M, K;
    public static List<Node>[] nodeList;
    public static Set<Integer> shopCity;
    public static int[] minDis;
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

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        minDis = new int[N+1];
        nodeList = new List[N+1];
        shopCity = new HashSet<>();
        Arrays.fill(minDis,300000000);
        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            nodeList[a].add(new Node(b,l));
            nodeList[b].add(new Node(a,l));
        }

        for (int i = 0; i<K;i++){
            int num = Integer.parseInt(br.readLine());
            minDis[num] = 0;
            shopCity.add(num);
        }

        for (int i = 1; i<=N;i++){
            dijkstra(i);
        }

        double ans = 0;
        for (int i = 1; i<=N;i++){
            if (nodeList[i].isEmpty()) continue;
            for (Node node : nodeList[i]){
                ans = Math.max(ans, ((double)(minDis[node.num] + minDis[i] + node.len)/2));
            }
        }


        System.out.println(Math.round(ans));
    }

    public static void dijkstra(int start){
        int[] distance = new int[N+1];
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pQueue = new PriorityQueue<>();

        Arrays.fill(distance,300000000);
        distance[start] = 0;
        pQueue.offer(new Node(start,distance[start]));
        while(!pQueue.isEmpty()){
            Node current = pQueue.poll();

            if (shopCity.contains(current.num)){
                minDis[start] = distance[current.num];
                return;
            }
            if (visited[current.num])continue;
            visited[current.num] = true;

            for (Node next : nodeList[current.num]){
                if(!visited[next.num] && distance[next.num] > current.len + next.len){
                    distance[next.num] = current.len + next.len;
                    pQueue.offer(new Node(next.num,distance[next.num]));
                }
            }
        }
    }
}
