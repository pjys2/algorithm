package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1103_게임 {
    public static int N, M, ans;
    public static char[][] board;
    public static boolean isLoop;
    public static boolean[][] visited;
    public static boolean[][][][] loopCheck;
    public static int[] dr = {-1,1,0,0};
    public static int[] dc = {0,0,-1,1};
    public static class Point{
        int r,c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = 0;
        isLoop = false;
        loopCheck = new boolean[N][M][N][M];
        board = new char[N][M];
        visited = new boolean[N][M];

        for (int r = 0; r<N;r++){
            String str = br.readLine();
            for (int c = 0;c<M;c++){
                board[r][c] = str.charAt(c);
            }
        }

        DFS(new Point(0,0),1);
        System.out.println(ans);
    }


    public static void DFS(Point current, int cnt){
        if (isLoop){
            ans = -1;
            return;
        }else if(ans < cnt){
            ans = cnt;
        }

        int num = board[current.r][current.c]-'0';
        for (int d = 0; d<4;d++){
            int nr = current.r+(dr[d]*num);
            int nc = current.c+(dc[d]*num);


            if(nr >= 0 && nr < N && nc >= 0 && nc < M && loopCheck[current.r][current.c][nr][nc]){
                isLoop = true;
                ans = -1;
                return;
            }

            if(nr >= 0 && nr < N && nc >= 0 && nc < M && board[nr][nc] != 'H' && !visited[nr][nc]){
                DFS(new Point(nr,nc),cnt+1);
                visited[nr][nc] = true;
                loopCheck[current.r][current.c][nr][nc] = true;
            }
        }
    }
}
