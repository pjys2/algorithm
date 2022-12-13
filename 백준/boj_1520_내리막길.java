package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1520_내리막길 {

    public static int N, M, ans;
    public static int map[][];
    public static int dr[] = {0,0,1,-1};
    public static int dc[] = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int [M][N];
        ans = 0;
        for (int r = 0; r<M;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<N;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0,0);

        System.out.println(ans);

    }

    public static void DFS(int r, int c){
        if(r == M-1 && c == N-1){
            ans++;
            return;
        }

        for (int d = 0; d<4;d++){
            int nr = r +dr[d];
            int nc = c+dc[d];
            if(nr >= 0 && nr<M && nc >=0 && nc <N&& map[nr][nc] < map[r][c]){
                DFS(nr,nc);
            }
        }
    }

    public static void print(int[][] arr){
        for (int r = 0; r<arr.length;r++){
            for (int c = 0; c<arr[r].length;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}
