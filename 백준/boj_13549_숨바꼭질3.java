package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_13549_숨바꼭질3 {
    public static int N, K;

    public static class Info implements Comparable<Info>{
        int pos, time;

        public Info(int pos, int time){
            this.pos = pos;
            this.time = time;
        }

        @Override
        public int compareTo(Info o) {
            return this.time-o.time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        BFS(new Info(N,0));
    }

    public static void BFS(Info start){
        PriorityQueue<Info> queue = new PriorityQueue<>();
        queue.add(start);
        Set<Integer> visitSet = new HashSet<>();
        visitSet.add(start.pos);
        while(!queue.isEmpty()){
            Info current = queue.poll();

            if(current.pos == K){
                System.out.println(current.time);
                return;
            }

            int next = current.pos*2;
            if(next > 0 && !visitSet.contains(next)){
                queue.add(new Info(next,current.time));
                visitSet.add(next);
            }

            next = current.pos+1;
            if(next > 0 && !visitSet.contains(next)){
                queue.add(new Info(next,current.time+1));
                visitSet.add(next);
            }

            next = current.pos-1;
            if(next > 0 && !visitSet.contains(next)){
                queue.add(new Info(next,current.time+1));
                visitSet.add(next);
            }


        }
    }
}
