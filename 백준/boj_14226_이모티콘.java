package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class boj_14226_이모티콘 {
    public static int S;
    public static boolean[][] visited;
    public static class State{
        int num;
        int clipboard;
        int cnt;
        public State(int num, int clipboard, int cnt){
            this.num = num;
            this.clipboard = clipboard;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        visited = new boolean[1001][2];

        BFS(new State(1,0,0));
    }

    public static void BFS(State start){
        Queue<State> queue = new LinkedList<>();

        queue.add(start);
        visited[start.num][0] = true;

        while(!queue.isEmpty()){
            Set<Integer> visitSet = new HashSet<>();

            int size = queue.size();

            for (int s = 0; s<size;s++){
                State current = queue.poll();


                if(current.num == S){
                    System.out.println(current.cnt);
                    return;
                }


                //클립보드 저장
                if(!visited[current.num][1]){
                    queue.add(new State(current.num,current.num, current.cnt+1));
                    visited[current.num][1] = true;
                }

                int next = current.num + current.clipboard;


                //클립보드 내용 복사
                if(next >= 2 && next <= 1000 && !visited[next][0]){
                    queue.add(new State(next,current.clipboard, current.cnt+1));
                    visitSet.add(next);
                }

                next = current.num - 1;

                //이모티콘 1개 삭제
                if(next >= 2 && next <= 1000 && !visited[next][0]){
                    queue.add(new State(next,current.clipboard, current.cnt+1));
                    visitSet.add(next);
                }
            }


            for (int visit : visitSet){
                visited[visit][0] = true;
            }
        }
    }
}
