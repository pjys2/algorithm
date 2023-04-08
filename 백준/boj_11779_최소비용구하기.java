package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_11779_최소비용구하기 {
    public static int n,m;
    public static List<Node>[] nodeList;
    public static int[] parrents;
    public static class Node implements Comparable<Node>{
        int num, v;

        public Node(int num, int v){
            this.num = num;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            return this.v - o.v;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        nodeList = new List[n+1];
        parrents = new int[n+1];
        for (int i = 1; i<=n;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 1; i<=m;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodeList[a].add(new Node(b,v));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start,end);
        printNode(end,start,1);
    }

    public static void printNode(int current,int start,int cnt){
        if(current == start){
            System.out.println(cnt);
            System.out.print(start+" ");
            return;
        }

        printNode(parrents[current], start,cnt+1);
        System.out.print(current+" ");
    }

    public static void dijkstra(int start,int end){
        int[] distance = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(distance,999999999);
        distance[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            visited[current.num] = true;
            if (current.num == end){
                System.out.println(distance[end]);
                return;
            }

            if(nodeList[current.num].isEmpty()) continue;
            for (Node next : nodeList[current.num]){

                if(visited[next.num]) continue;

                if(distance[next.num] > current.v + next.v){
                    distance[next.num] = current.v + next.v;
                    parrents[next.num] = current.num;
                    pq.add(new Node(next.num,distance[next.num]));
                }
            }
        }
    }
}
