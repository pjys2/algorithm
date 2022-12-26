package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1743_음식물피하기 {
    public static int N, M, K, count;
    public static char[][] map;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static int[][] connect;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N+1][M+1];
        connect = new int[N+1][M+1];
        for (int k = 1; k <= K; k++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = '#';
        }


        int number = 0;
        List<Integer> ansList = new ArrayList<>();
        for (int r = 1; r<=N;r++){
            for (int c = 1; c<=M;c++){
                if(connect[r][c] != 0 || map[r][c] != '#')continue;
                count = 0;
                DFS(r,c,number);
                number++;
                ansList.add(count);
            }
        }
        System.out.println(ansList.toString());
        Collections.reverse(ansList);

        System.out.println(ansList.get(0));

    }

    public static void DFS(int r, int c,int number){
        count++;

        for (int d = 0; d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(nr >= 1 && nr <=N && nc >= 1 && nc <= M && map[nr][nc] == '#' && connect[nr][nc] == 0){
                connect[nr][nc] = number;
                DFS(nr,nc, number);
            }
        }
    }
}
