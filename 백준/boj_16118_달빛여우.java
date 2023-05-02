package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16118_달빛여우 {
    public static int N, M;
    public static List<Node>[] nodeList;
    public static long[][] wolfDis;
    public static long[] foxDis;
    public static class Node implements Comparable<Node>{
        int num;
        long len;
        boolean isRun;
        public Node(int num, long len, boolean isRun){
            this.num = num;
            this.len = len;
            this.isRun = isRun;
        }

        @Override
        public int compareTo(Node o) {
            return (int)(this.len - o.len);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodeList = new List[N+1];

        wolfDis = new long[2][N+1];
        foxDis = new long[N+1];

        Arrays.fill(wolfDis[0],Long.MAX_VALUE);
        Arrays.fill(wolfDis[1],Long.MAX_VALUE);
        Arrays.fill(foxDis,Long.MAX_VALUE);

        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 1; i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            nodeList[a].add(new Node(b,d*2,false));
            nodeList[b].add(new Node(a,d*2,false));
        }

        foxDijk();
        wolfDijk();

        int ans = 0;
        for (int i = 1; i<=N;i++){
            if(foxDis[i] < wolfDis[0][i] && foxDis[i] < wolfDis[1][i]) ans++;

//            System.out.println(foxDis[i]+" "+wolfDis[0][i]+" "+wolfDis[1][i]);
        }

        System.out.println(ans);

    }

    public static void foxDijk(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0,false));
        foxDis[1] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if (foxDis[current.num] < current.len) continue;

            for (Node next : nodeList[current.num]){
                if (foxDis[next.num] > current.len + next.len){
                    pq.add(new Node(next.num, current.len + next.len,false));
                    foxDis[next.num] = current.len + next.len;
                }
            }
        }
    }

    public static void wolfDijk(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0, false));



        while(!pq.isEmpty()){
            Node current = pq.poll();

            if (current.isRun && wolfDis[0][current.num] < current.len) continue;

            if (!current.isRun && wolfDis[1][current.num] < current.len) continue;



            for (Node next : nodeList[current.num]){
                if (current.isRun){
                    long nextLen = current.len + (next.len * 2);

                    if (wolfDis[1][next.num] > nextLen){
                        pq.add(new Node(next.num,nextLen,false));
                        wolfDis[1][next.num] = nextLen;
                    }
                }else{
                    long nextLen = current.len + (next.len / 2);

                    if (wolfDis[0][next.num] > nextLen){
                        pq.add(new Node(next.num,nextLen,true));
                        wolfDis[0][next.num] = nextLen;
                    }
                }
            }
        }

        wolfDis[0][1] = 0;
    }
}
