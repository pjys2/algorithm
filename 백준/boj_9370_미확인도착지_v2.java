package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_9370_미확인도착지_v2 {
    public static int n, m, t, s,g,h;
    public static List<Node>[] nodeList;
    public static List<Integer> ansList;
    public static double[] distance;
    public static class Node{
        int num;
        double len;

        public Node(int num, double len){
            this.num = num;
            this.len = len;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            nodeList = new List[n+1];
            ansList = new ArrayList<>();
            for (int i = 1; i<=n;i++){
                nodeList[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            for (int i = 0; i<m;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                double d = Double.parseDouble(st.nextToken());

                if((a == g || a == h) && (b==g || b==h)) d = d-0.1;
                nodeList[a].add(new Node(b,d));
                nodeList[b].add(new Node(a,d));
            }


            dijkstra(s);
            for (int i =0; i<t;i++){
                int x = Integer.parseInt(br.readLine());

                if(distance[x]%1 != 0){
                    ansList.add(x);
                }
            }

            Collections.sort(ansList);
            for (int ans : ansList){
                System.out.print(ans+" ");
            }

            System.out.println();
        }
    }

    public static void dijkstra(int start){
        distance = new double[n+1];
        boolean[] visited = new boolean[n+1];

        Arrays.fill(distance, 3000000);
        distance[start] = 0;

        for (int i = 1; i<=n;i++){
            double min = Integer.MAX_VALUE;
            int current = 0;

            for (int j = 1; j<=n;j++){
                if(!visited[j] && min > distance[j]){
                    min = distance[j];
                    current = j;
                }

                if(!visited[j] && (j == g || j == h) && min >= distance[j]){
                    min = distance[j];
                    current = j;
                }
            }

            visited[current] = true;

            for (Node node:nodeList[current]){
                if(!visited[node.num] && distance[node.num] > node.len + distance[current]){
                    distance[node.num] = node.len+distance[current];
                }
            }
        }
    }
}
