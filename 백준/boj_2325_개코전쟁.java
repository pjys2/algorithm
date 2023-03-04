package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_2325_개코전쟁 {
    public static int N, M, ans;
    public static List<Node>[] nodeList;
    public static int[] parrents;
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
        ans = 0;

        nodeList = new List[N+1];

        parrents = new int[N+1];
        for (int i =1; i<=N;i++){
            nodeList[i] = new ArrayList<Node>();
        }

        for (int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            nodeList[x].add(new Node(y,z));
            nodeList[y].add(new Node(x,z));
        }

        dijkstra(1, N);

        findRoad(N);

        System.out.println(ans);
    }

    public static void findRoad(int current){
        if(current == 1){
            return;
        }

        int next = parrents[current];
        Node node1 = new Node(0,0);
        Node node2 = new Node(0,0);
        for (Node node : nodeList[current]){
            if(node.num == next){
                nodeList[current].remove(node);
                node1 = node;
                break;
            }
        }

        for (Node node : nodeList[next]){
            if(node.num == current){
                nodeList[next].remove(node);
                node2 = node;
                break;
            }
        }

        dijkstra(1,N);

        nodeList[current].add(node1);
        nodeList[next].add(node2);

        findRoad(next);


    }


    public static void dijkstra(int start, int end){
        int[] distance = new int[N+1];
        boolean[] visited = new boolean[N+1];

        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[start] = 0;

        for (int i = 1; i<=N;i++){
            int min = Integer.MAX_VALUE;
            int current = 0;

            for (int j = 1; j<=N;j++){
                if(!visited[j] && min > distance[j]){
                    min = distance[j];
                    current = j;
                }
            }

            visited[current] = true;
            if(current == end) break;

            for (Node node : nodeList[current]) {
                if (!visited[node.num] && distance[node.num] > distance[current] + node.time) {
                    distance[node.num] = distance[current] + node.time;
                    parrents[node.num] = current;
                }
            }
        }

        ans = Math.max(ans, distance[N]);

    }
}
