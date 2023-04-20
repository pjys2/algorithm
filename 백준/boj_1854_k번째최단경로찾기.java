package 백준;

import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1854_k번째최단경로찾기 {
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
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));

        PriorityQueue<Integer>[] disq = new PriorityQueue[n+1];

        for (int i = 1; i<=n;i++){
            disq[i] = new PriorityQueue(new Comparator<Integer>(){

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
        }

        disq[1].add(0);

        while(!pq.isEmpty()){
            Node current = pq.poll();

            for (Node next : nodeList[current.num]){
                int nextTime = current.time+next.time;
                if (disq[next.num].size() < k){
                    disq[next.num].add(nextTime);
                    pq.add(new Node(next.num,nextTime));
                }else if (disq[next.num].peek() > nextTime){
                    disq[next.num].poll();
                    disq[next.num].add(nextTime);
                    pq.add(new Node(next.num,nextTime));
                }
            }
        }

        for (int i = 1; i<=n;i++){
            if(disq[i].size() == k){
                System.out.println(disq[i].peek());
            }else{
                System.out.println(-1);
            }
        }
    }
}
