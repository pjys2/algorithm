package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_9370_미확인도착지 {
    public static int n, m, t, s,g,h;
    public static List<Node>[] nodeList;
    public static List<Integer> ansList;
    public static class Node{
        int num, len;

        public Node(int num, int len){
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
                int c = Integer.parseInt(st.nextToken());

                nodeList[a].add(new Node(b,c));
                nodeList[b].add(new Node(a,c));
            }


            int sTog = dijkstra(s,g);
            int sToh = dijkstra(s,h);
            int gToh = dijkstra(g,h);
            for (int i =0; i<t;i++){
                int x = Integer.parseInt(br.readLine());

                int sTox = dijkstra(s,x);
                int gTox = dijkstra(g,x);
                int hTox = dijkstra(h,x);
                if(sTox >= sTog+gToh+hTox){
                    ansList.add(x);
                }else if(sTox >= sToh+gToh+gTox){
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

    public static int dijkstra(int start, int end){
        int[] distance = new int[n+1];
        boolean[] visited = new boolean[n+1];
//        int[] parents = new int[n+1];
//        boolean check = false;


        Arrays.fill(distance, 3000000);
        distance[start] = 0;

        for (int i = 1; i<=n;i++){
            int min = Integer.MAX_VALUE;
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
            if(current == end) break;


            for (Node node:nodeList[current]){
                if(!visited[node.num] && distance[node.num] > node.len + distance[current]){
                    distance[node.num] = node.len+distance[current];
//                    parents[node.num] = current;
                }
            }
        }

//        check = isPossible(end,parents,false,false);
//
//        if (check){
//            ansList.add(end);
//        }
        return distance[end];

    }

    public static boolean isPossible(int n,int[] parrents, boolean gCheck, boolean hCheck){
        if(n == s){
            if(gCheck && hCheck) return true;
            else return false;
        }


        if(n == h) hCheck = true;
        if(n == g) gCheck = true;

        return isPossible(parrents[n],parrents,gCheck,hCheck);
    }
}
