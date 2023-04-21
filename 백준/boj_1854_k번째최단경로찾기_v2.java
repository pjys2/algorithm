package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1854_k번째최단경로찾기_v2 {
    public static int n,m,k;
    public static List<Node>[] nodeList;
    public static class Node implements Comparable<Node>{
        int num,time;
        public Node(int num, int time){
            this.num = num;
            this.time = time;
        }


        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        nodeList = new List[n+1];

        for (int i = 1; i<=n;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 1; i<=m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            nodeList[a].add(new Node(b,c));
        }


        dijkstra();

    }


    public static void dijkstra(){
        int[] distance = new int[n+1];
        List<Integer>[] disList = new List[n+1];
        for (int i = 1;i<=n;i++){
            disList[i] = new ArrayList<>();
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));

        disList[1].add(0);

        while(!pq.isEmpty()){
            Node current = pq.poll();

            for (Node next : nodeList[current.num]){
                //방문 시간이 저장된 시간보다 크고 다음 지점의 방문 횟수가 k번 일 때 continue
                if (disList[next.num].size() < k){
                    disList[next.num].add(current.time+next.time);
                    pq.add(new Node(next.num,current.time+next.time));
                    if (distance[next.num] < current.time+ next.time){
                        distance[next.num] = current.time+next.time;
                    }
                }else if (distance[next.num] > current.time + next.time){
                    disList[next.num].add(current.time+next.time);
                    Collections.sort(disList[next.num]);
                    distance[next.num] = disList[next.num].get(k-1);
                    pq.add(new Node(next.num,current.time+next.time));
                }
            }
        }

        for (int i = 1; i<=n;i++){
            if(disList[i].size() >= k){
                System.out.println(disList[i].get(k-1));
            }else{
                System.out.println(-1);
            }
        }
    }
}
