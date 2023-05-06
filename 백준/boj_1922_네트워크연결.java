package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1922_네트워크연결 {
    public static int N, M;
    public static List<Node>[] nodeList;

    public static class Node{
        int num, len;
        public Node(int num, int len){
            this.num = num;
            this.len = len;
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

        for (int i = 1; i<=M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodeList[a].add(new Node(b,c));
            nodeList[b].add(new Node(a,c));
        }

        MST();
    }

    public static void MST(){
        int[] distance = new int[N+1];
        Arrays.fill(distance,999999999);
        distance[1] = 0;

        boolean[] visited = new boolean[N+1];

        int total = 0;

        for (int i = 1; i<=N;i++){
            int current = 0;
            int dis = 999999999;

            for (int j = 1;j<=N;j++){
                if (!visited[j] && dis > distance[j]){
                    current = j;
                    dis = distance[j];
                }
            }

            visited[current] = true;
            total += dis;

            for (Node next : nodeList[current]){
                if (!visited[next.num] && distance[next.num] > next.len){
                    distance[next.num] = next.len;
                }
            }
        }

        System.out.println(total);
    }
}
