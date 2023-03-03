package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1939_중량제한 {
    public static int N, M;
    public static List<Node>[] nodeList;
    public static boolean[] visited;
    public static class Node{
        int num, weight;

        public Node(int num, int weight){
            this.num = num;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodeList = new ArrayList[N+1];
        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 1; i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodeList[A].add(new Node(B,w));
            nodeList[B].add(new Node(A,w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        BFS(start,end);

    }

    public static void BFS(int start, int end){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 1000000000));
        visited[start] = true;

        List<Integer> weightList = new ArrayList<>();
        while(!queue.isEmpty()){
            Node current = queue.poll();

            if(current.num == end){
                weightList.add(current.weight);
            }

            for (Node next : nodeList[current.num]){
                if(!visited[next.num]){
                   int weight = Math.min(next.weight,current.weight);
                   queue.add(new Node(next.num,weight));
                   visited[next.num] = true;
                }
            }
        }

        int ans = 0;
        for (int weight : weightList){
            ans = Math.max(ans, weight);
        }

        System.out.println(ans);
    }
}
