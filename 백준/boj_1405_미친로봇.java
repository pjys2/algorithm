package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class boj_1405_미친로봇 {
    public static int N;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static boolean[][] visited;
    public static double[] dirValue;
    public static double ans;
    public static class Point{
        int r, c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ans = 0;
        dirValue = new double[4];
        visited = new boolean[30][30];
        for (int i = 0; i<4;i++){
            dirValue[i] = Double.parseDouble(st.nextToken())/100;
        }
        visited[15][15] = true;
        DFS(new Point(15,15),0,1);

        System.out.println(ans);
    }

    public static void DFS(Point current,int k,double result){


        if(k == N){
            ans += result;
            return;
        }


        for (int d = 0; d<4;d++){
            int nr = current.r+dr[d];
            int nc = current.c+dc[d];

            if(nr >= 0 && nr <= 30 && nc >= 0 && nc <= 30 && !visited[nr][nc]){
                visited[current.r][current.c]= true;
                DFS(new Point(nr,nc),k+1,result*dirValue[d]);
                visited[current.r][current.c]= false;
            }
        }
    }



}
