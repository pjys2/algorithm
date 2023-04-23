package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2211_네트워크복구 {
    public static int N,M,K;
    public static List<Node>[] nodeList;
    public static class Node{
        int num, time;

        public Node(int num, int time){
            this.num = num;
            this.time = time;
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
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            nodeList[A].add(new Node(B,C));
            nodeList[B].add(new Node(A,C));
        }

        dijkstra();
    }

    public static void dijkstra(){
        int[] distance = new int[N+1];
        int[] parrent = new int[N+1];
        Arrays.fill(distance,999999999);

        distance[1] = 0;

        boolean[] visited = new boolean[N+1];


        for (int i = 1;i<=N;i++){
            int current = 0;
            int minDis = 999999999;

            for (int j = 1; j<=N;j++){
                if (!visited[j] && distance[j] < minDis){
                    current = j;
                    minDis = distance[j];
                }
            }

            visited[current] = true;

//            System.out.println(current+" "+minDis);
            for (Node next : nodeList[current]){
                if (!visited[next.num] && distance[next.num] > distance[current] + next.time){
                    distance[next.num] = distance[current] + next.time;
                    parrent[next.num] = current;
                }
            }
        }

        int cnt = 0;

        for (int i = 1; i<=N;i++){
            if (parrent[i] != 0) cnt++;
        }

        System.out.println(cnt);

        for (int i = 1; i<=N;i++){
            if (parrent[i] != 0) System.out.println(i+" "+parrent[i]);
        }
    }
}
