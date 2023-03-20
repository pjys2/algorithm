package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1238_파티 {
    public static int N, M, X;
    public static List<Node>[] nodeList;
    public static int[] comeDist;
    public static int[] goDist;
    public static class Node{
        int num ,T;
        public Node(int num, int T){
            this.num = num;
            this.T = T;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());


        comeDist = new int[N+1];
        goDist = new int[N+1];
        Arrays.fill(goDist,1000000);

        nodeList = new List[N+1];
        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }


        for (int i = 1; i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            nodeList[from].add(new Node(to,T));
        }



        for (int i = 1; i<=N;i++){
            if (comeDist[i]!=0) continue;
            dijkstra(i,X);
        }

        dijkstra(X);

        int ans = 0;
        for (int i = 1; i<=N;i++){
            System.out.println(i +" : "+(goDist[i]+comeDist[i]));
            ans = Math.max(ans,goDist[i]+comeDist[i]);
        }

        System.out.println(ans);
    }

    public static void dijkstra(int start){

        boolean[] visited = new boolean[N+1];
        goDist[start] = 0;

        for (int i = 1; i<=N;i++){
            int current = 0;
            int minDis = 1000000;
            for (int j = 1; j<=N;j++){
                if (!visited[j] && minDis > goDist[j]){
                    current = j;
                    minDis = goDist[j];
                }
            }

            visited[current] = true;

            for (Node node : nodeList[current]){
                if(goDist[node.num] > node.T + goDist[current]){
                    goDist[node.num] = node.T + goDist[current];
                }
            }
        }
    }

    public static void dijkstra(int start, int end){
        int[] dis = new int[N+1];
        boolean[] visited = new boolean[N+1];
        Node[] parrents = new Node[N+1];

        dis[start] = 0;

        Arrays.fill(dis,1000000);

        //최단 경로 찾기
        for (int i = 1; i<=N;i++){
            int current = 0;
            int minDis = 1000000;
            for (int j = 1; j<=N;j++){
                if (!visited[j] && minDis > comeDist[j]){
                    current = j;
                    minDis = comeDist[j];
                }
            }

            visited[current] = true;
            if (current == end){
                return;
            }

            for (Node node : nodeList[current]){
                if(comeDist[node.num] > node.T + comeDist[current]){
                    comeDist[node.num] = node.T + comeDist[current];
                    parrents[node.num] = new Node(current,node.T);
                }
            }
        }
        //부모를 찾아가면서 시간 기록
        int time = 0;
        for (int i = end; i != start; i = parrents[i].num){
            time = parrents[i].T;
            comeDist[parrents[i].num] = time;
        }

    }
}
