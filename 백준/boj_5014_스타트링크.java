package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_5014_스타트링크 {
    public static int F,S,G,U,D;
    public static boolean[] visited;
    public static boolean isPossible;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());  //건물 층 수
        S = Integer.parseInt(st.nextToken());  // 강호 위치
        G = Integer.parseInt(st.nextToken());  // 스타트링크 위치
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        visited = new boolean[F+1];

        BFS();

        if (!isPossible){
            System.out.println("use the stairs");
        }

    }

    public static void BFS(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);
        visited[S] = true;
        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0;s<size;s++){
                int current = queue.poll();

                if(current == G){
                    isPossible = true;
                    System.out.println(cnt);
                    return;
                }


                int next = current+U;

                if(next >= 1 && next <=F && !visited[next]){
                    queue.add(next);
                    visited[next] = true;
                }

                next = current-D;

                if(next >= 1 && next <=F && !visited[next]){
                    queue.add(next);
                    visited[next] = true;
                }
            }

            cnt++;
        }

    }
}

