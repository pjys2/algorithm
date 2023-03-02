package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_11060_점프점프 {

    public static int N;
    public static int[] miro;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        miro = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++){
            miro[i] = Integer.parseInt(st.nextToken());
        }

        BFS();
    }

    public static void BFS(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] visited = new boolean[N];

        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                int current = queue.poll();


                if(current == N-1){
                    System.out.println(cnt);
                    return;
                }
                for (int i = 1; i<=miro[current];i++){
                    int next = current+i;
                    if(next < N && !visited[next]){
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }

            cnt++;
        }

        System.out.println(-1);
    }
}
