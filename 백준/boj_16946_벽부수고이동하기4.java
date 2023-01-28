package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16946_벽부수고이동하기4 {
    public static int N, M, count;
    public static int[][] map;
    public static boolean[][] visited;
    public static int[][] ans;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ans = new int[N][M];

        for (int r = 0; r<N;r++){
            String str = br.readLine();
            for (int c = 0; c<M;c++){
                map[r][c] = str.charAt(c)-'0';
            }
        }

        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                if(map[r][c] == 0){
                    ans[r][c] = 0;
                    continue;
                }

                count = 0;
                visited = new boolean[N][M];
                map[r][c] = 0;
                DFS(r,c);
                map[r][c] = 1;
                ans[r][c] = count%10;
            }
        }

        print();

    }

    public static void DFS(int r, int c){
        visited[r][c] = true;
        count++;
        for (int d = 0; d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];
            if (nr >=0 && nr < N && nc >= 0 && nc <M && !visited[nr][nc] && map[nr][nc] == 0){
                DFS(nr,nc);
            }
        }
    }

    public static void print(){
        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                System.out.print(ans[r][c]);
            }
            System.out.println();
        }
    }
}
