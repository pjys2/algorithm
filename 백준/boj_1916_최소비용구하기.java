package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1916_최소비용구하기 {
    public static int N, M;
    public static List<Node>[] nodeList;
    public static class Node{
        int num, v;
        public Node(int num, int v){
            this.num = num;
            this.v = v;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        nodeList = new List[N+1];

        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodeList[from].add(new Node(to,v));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start,end);
    }

    public static void dijkstra(int start, int end){

        long[] dis = new long[N+1];
        boolean[] visited = new boolean[N+1];

        Arrays.fill(dis,Long.MAX_VALUE);

        dis[start] = 0;

        for (int i = 1; i<=N;i++){
            int current = 0;
            long minDis = Long.MAX_VALUE;

            for (int j = 1; j<=N;j++){
                if(!visited[j] && minDis > dis[j]){
                    current = j;
                    minDis = dis[j];
                }
            }

            if(current == end){
                System.out.println(dis[current]);
                return;
            }
            visited[current] = true;


            for (Node node : nodeList[current]){
                if (!visited[node.num] && dis[node.num] > dis[current] + node.v){
                    dis[node.num] = dis[current]+node.v;
                }
            }
        }

    }
}

