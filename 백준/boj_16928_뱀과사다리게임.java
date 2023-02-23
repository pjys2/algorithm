package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16928_뱀과사다리게임 {
    public static int N, M;
    public static int[] map;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[101];

        map = new int[101];

        for (int i = 0; i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x] = y;
        }

        for (int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            map[u] = v;
        }


        BFS();
    }

    public static void BFS(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0; s<size;s++){
                int current = queue.poll();
                if(current == 100){
                    System.out.println(cnt);
                    return;
                }
                for (int i = 1; i<=6;i++){
                    int next = current+i;

                    if(next >= 1 && next <= 100 && map[next] != 0 && map[next] > next && !visited[next]){
                        visited[next] = true;
                        next = map[next];
                        visited[next] = true;
                        queue.add(next);
                    }else if(next >= 1 && next <= 100 && map[next] != 0 && map[next] < next){
                        visited[next] = true;
                        next = map[next];
                        visited[next] = true;
                        queue.add(next);
                    }else if(next >= 1 && next<= 100 && !visited[next]){
                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }

            cnt++;
        }
    }
}
