package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_4963_섬의개수 {

    public static int w, h;
    public static int[] dr = {0,0,1,-1,1,1,-1,-1};
    public static int[] dc = {1,-1,0,0,1,-1,-1,1};
    public static int[][] map;
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) break;

            map = new int[h][w];
            visited = new boolean[h][w];

            for (int r = 0; r<h;r++){
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c<w;c++){
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            int number = 0;
            for (int r = 0; r<h;r++){
                for (int c = 0; c<w;c++){
                    if(map[r][c] == 0) continue;

                    if(visited[r][c]) continue;

                    number++;
                    DFS(r,c,number);

                }
            }

            System.out.println(number);
        }

    }

    public static void DFS(int r, int c,int number){

        for (int d = 0 ; d < 8; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr >= 0 && nr < h && nc >= 0 && nc < w && map[nr][nc] == 1 && !visited[nr][nc]){
                visited[nr][nc] = true;
                DFS(nr,nc,number);
            }
        }
    }
}
