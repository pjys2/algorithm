package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1939_중량제한 {
    public static int N, M,ans;
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

        int min = 1000000000;
        int max = 0;


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
            min = Math.min(min,w);
            max = Math.max(max,w);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());



        while(min <= max){
            int mid = (min+max)/2;
            if(BFS(start,end,mid)){
                min = mid+1;
            }else{
                max = mid-1;
            }
        }
        System.out.println(min);

    }

    public static boolean BFS(int start, int end, int limit){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        visited = new boolean[N+1];

        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                Node current = queue.poll();

                if(current.num == end){
                    return true;
                }

                for (Node next : nodeList[current.num]){
                    if(!visited[next.num] && next.weight > limit){
                        queue.add(next);
                        visited[next.num] = true;
                    }
                }
            }
        }

        return false;
    }
}
