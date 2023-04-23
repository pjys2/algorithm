package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1719_택배 {
    public static int n,m;
    public static int[][] ans;
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
        ans = new int[n+1][n+1];

        for (int i = 1; i<=n;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 1;i<=m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            nodeList[a].add(new Node(b,l));
            nodeList[b].add(new Node(a,l));
        }

        for (int i = 1; i<=n;i++){
            dijkstra(new Node(i,0));
        }

        for (int i = 1;i<=n;i++){
            for (int j = 1; j<=n;j++){
                if (i == j) System.out.print("- ");
                else System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void dijkstra(Node start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(start);

        int[] distance = new int[n+1];
        int[] parrents = new int[n+1];
        Arrays.fill(distance,99999999);

        distance[start.num] = 0;
        while(!pq.isEmpty()){
            Node current = pq.poll();
            for (Node next : nodeList[current.num]){
                if (distance[next.num] > current.len + next.len){
                    distance[next.num] = current.len + next.len;
                    parrents[next.num] = current.num;
                    pq.add(new Node(next.num, distance[next.num]));
                }
            }
        }

        for (int i = 1;i<=n;i++){
            if (i == start.num) continue;
            ans[start.num][i] = findNode(start.num,i,parrents);
        }
    }

    public static int findNode(int target,int current, int[] parrents){
        if (parrents[current] == target){
            return current;
        }

        return findNode(target,parrents[current],parrents);
    }
}
