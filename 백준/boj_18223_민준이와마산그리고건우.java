package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_18223_민준이와마산그리고건우 {
    public static int V, E, P;
    public static List<Node>[] nodeList;
    public static class Node implements Comparable<Node>{
        int num, len;
        boolean gunwoo;
        public Node(int num, int len, boolean gunwoo){
            this.num = num;
            this.len = len;
            this.gunwoo = gunwoo;
        }

        @Override
        public int compareTo(Node o) {
            return this.len - o.len;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        nodeList = new List[V+1];
        for (int i = 1; i<=V;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 1; i<=E;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            nodeList[a].add(new Node(b,c,false));
            nodeList[b].add(new Node(a,c,false));
        }

        dijkstra();

    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();

        if(P == 1){
            pq.add(new Node(1,0,true));
        }else{
            pq.add(new Node(1,0,false));
        }

        int[] distance = new int[V+1];
        distance[1] = 0;
        Arrays.fill(distance,999999999);
        boolean ans = false;

        while(!pq.isEmpty()){
            Node current = pq.poll();
//            System.out.println(current.num);
            if (distance[current.num] < current.len) continue;

            if (current.num == V && current.gunwoo){
                ans = true;
            }

            for (Node next : nodeList[current.num]){
                if (distance[next.num] < current.len + next.len) continue;

                boolean gunwoo = current.gunwoo;
                if (next.num == P) gunwoo = true;
                pq.add(new Node(next.num, current.len + next.len,gunwoo));
                distance[next.num] = current.len + next.len;
            }
        }

        if (ans){
            System.out.println("SAVE HIM");
        }else{
            System.out.println("GOOD BYE");
        }

    }
}
