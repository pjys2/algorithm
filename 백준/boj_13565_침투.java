package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_13565_침투 {
    public static int M,N;
    public static boolean check;
    public static int[][] map;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        for (int r = 0;r<M;r++){
            String input = br.readLine();
            for (int c = 0; c<N;c++){
                map[r][c] = input.charAt(c)-'0';
            }
        }

        for (int c = 0; c<N;c++){
            if(map[M-1][c] == 1) continue;
            DFS(M-1,c);
            if(check) break;
        }


        if(check){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }


    }

    public static void DFS(int r, int c){
        if(r == 0){
            check = true;
            return;
        }
        map[r][c] = 1;
        for (int d = 0; d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];
            if(nr < 0 || nr >= M || nc < 0 || nc >= N || map[nr][nc] == 1) continue;
            DFS(nr,nc);
        }
    }

    public static void print(){
        for (int r = 0;r<M;r++){
            for (int c = 0; c<N;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}
