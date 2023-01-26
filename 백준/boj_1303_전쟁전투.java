package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1303_전쟁전투 {
    public static int N, M, ansW,ansB, count;
    public static char[][] map;
    public static boolean[][] visited;
    public static int[] dr = {0,0,-1,1};
    public static int[] dc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ansW = 0;
        ansB = 0;
        map = new char[M][N];
        visited = new boolean[M][N];

        for (int r = 0; r<M;r++){
            String str = br.readLine();
            for (int c = 0; c<N;c++){
                map[r][c] = str.charAt(c);
            }
        }

        for (int r = 0; r<M;r++){
            for (int c = 0; c<N;c++){
                count = 0;
                if (visited[r][c]) continue;

                DFS(r,c,map[r][c]);
                if (map[r][c] == 'W'){
                    ansW += count * count;
                }else if(map[r][c] == 'B'){
                    ansB += count * count;
                }
            }
        }

        System.out.println(ansW);
        System.out.println(ansB);


    }


    public static void DFS(int r, int c, char word){
        visited[r][c] = true;
        count++;

        for (int d = 0; d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];
            if (nr >= 0 && nr < M && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] == word){
                DFS(nr,nc,word);
            }
        }
    }

}
