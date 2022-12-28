package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_13023_ABCDE{
    public static int N, M;
    public static Node[] nodeList;
    public static boolean[] visited;
    public static boolean possible;
    public static class Node{
        int num;
        Node node;
        public Node(int num, Node node){
            this.num = num;
            this.node = node;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodeList = new Node[N];
        visited = new boolean[N];
        possible = false;
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            nodeList[from] = new Node(to,nodeList[from]);
            nodeList[to] = new Node(from,nodeList[to]);
        }
        for (int num = 0; num < N; num++){
            visited[num] = true;
            DFS(num,1);
            visited[num] = false;

        }

        if (possible){
            System.out.println(1);
        }else{
            System.out.println(0);
        }

    }

    public static void DFS(int num, int count){
        if(count == 5){
            possible = true;
            return;
        }
        if(possible){
            return;
        }

        for (Node temp = nodeList[num]; temp != null; temp = temp.node){
            if(visited[temp.num]) continue;
            count++;
            visited[temp.num] = true;
            DFS(temp.num, count);
            count--;
            visited[temp.num] = false;
        }
    }
}
