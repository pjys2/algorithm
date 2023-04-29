package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_13424_비밀모임 {
    public static int T, N, M,K,ans,ansLen;
    public static int[] room;
    public static List<Node>[] nodeList;
    public static class Node implements Comparable<Node>{
        int num, len;
        public Node(int num, int len){
            this.num = num;
            this.len = len;
        }

        @Override
        public int compareTo(Node o) {
            return this.len - o.len;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int tc = 1;tc<=T;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            ansLen = Integer.MAX_VALUE;
            ans = 0;
            nodeList = new List[N+1];

            for (int i = 1;i<=N;i++){
                nodeList[i] = new ArrayList<>();
            }

            for (int i = 1;i<=M;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                nodeList[a].add(new Node(b,c));
                nodeList[b].add(new Node(a,c));
            }

            K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            room = new int[K+1];

            for (int i =1; i<=K;i++){
                int roomNum = Integer.parseInt(st.nextToken());
                room[i] = roomNum;
            }

            for (int i = 1; i<=N;i++){
                dijkstra(i);
            }

            System.out.println(ans);
        }
    }

    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));


//        int bitMask = 0;
        int[] distance = new int[N+1];
        Arrays.fill(distance,Integer.MAX_VALUE);

        while(!pq.isEmpty()){
            Node current = pq.poll();


            if (distance[current.num] < current.len) continue;

            for(Node next : nodeList[current.num]){
                if (distance[next.num] >= current.len + next.len){
                    pq.add(new Node(next.num,current.len + next.len));
                    distance[next.num] = current.len + next.len;
                }
            }
        }

        int sum = 0;
        for (int i = 1; i<=K;i++){
            sum += distance[room[i]];
        }

        if (ansLen > sum){
            ansLen = sum;
            ans = start;
        }else if(ansLen == sum && ans > start){
            ansLen = sum;
            ans = start;
        }
    }
}
