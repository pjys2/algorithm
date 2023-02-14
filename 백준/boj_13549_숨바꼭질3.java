package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_13549_숨바꼭질3 {
    public static int N, K;
    public static boolean[][] visited;

    public static class Info{
        int pos, time;
        public Info(int pos, int time,boolean jump){
            this.pos = pos;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited= new boolean[100001][2];
        BFS(new Info(N,0,false));
    }

    public static void BFS(Info start){
        Queue<Info> queue = new LinkedList<>();
        queue.add(start);
        visited[start.pos][0] = true;
        while(!queue.isEmpty()){
            Info current = queue.poll();

            if(current.pos == K){
                System.out.println(current.time);
                return;
            }

            int next = current.pos*2;
            if(next >= 0 && next <= 100000 && !visited[next][1]){
                queue.add(new Info(next,current.time,true));
                visited[next][1] = true;
            }

            next = current.pos+1;
            if(next >= 0 && next <= 100000 && !visited[next][0]){
                queue.add(new Info(next,current.time+1,false));
                visited[next][0] = true;
            }

            next = current.pos-1;
            if(next >= 0 && next <= 100000 && !visited[next][0]){
                queue.add(new Info(next,current.time+1,false));
                visited[next][0] = true;
            }
        }
    }
}
