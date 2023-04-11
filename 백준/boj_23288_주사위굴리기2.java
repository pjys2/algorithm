package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_23288_주사위굴리기2 {
    public static int N, M,K,D, ans;

    public static int[][] map;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};

    //뒤 위 앞 아래 왼 오
    public static int[] dice = {2,1,5,6,4,3};
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
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        //처음 이동방향
        D = 0;
        map = new int[N+1][M+1];
        ans = 0;
        for (int r = 1; r<=N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c<=M;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        Point dicePoint = new Point(1,1);
        for (int k = 1; k<=K;k++){
            int nr = dicePoint.r+dr[D];
            int nc = dicePoint.c+dc[D];

            if(nr < 1 || nr > N || nc < 1 || nc > M){
                if(D == 0) D = 1;
                else if(D == 1) D = 0;
                else if(D == 2) D = 3;
                else if(D == 3) D = 2;
                nr = dicePoint.r+dr[D];
                nc = dicePoint.c+dc[D];
            }

            dicePoint = new Point(nr,nc);
            diceMove(dicePoint);

            ans += BFS(dicePoint,map[dicePoint.r][dicePoint.c]);
        }

        System.out.println(ans);
    }

    public static int BFS(Point start, int num){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[N+1][M+1];
        visited[start.r][start.c] = true;
        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0;s<size;s++){
                Point current = queue.poll();
                cnt++;
                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    if(nr < 1 || nr > N || nc < 1|| nc > M || visited[nr][nc] || map[nr][nc] != num) continue;

                    queue.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }
            }
        }

        return cnt * num;
    }

    public static void diceMove(Point p){
        //주사위 굴리기
        if(D == 0){
            int temp = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[3];
            dice[3] = dice[5];
            dice[5] = temp;
        }else if(D == 1){
            int temp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[3];
            dice[3] = dice[4];
            dice[4] = temp;
        }else if(D == 2){
            int temp = dice[1];
            dice[1] = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[2];
            dice[2] = temp;
        }else if(D == 3){
            int temp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[3];
            dice[3] = dice[0];
            dice[0] = temp;
        }

        //이동 방향 변경
        if(dice[3] > map[p.r][p.c]){
            if(D == 0) D = 2;
            else if(D == 1) D = 3;
            else if(D == 2) D = 1;
            else if(D == 3) D = 0;
        }else if(dice[3] < map[p.r][p.c]){
            if(D == 0) D = 3;
            else if(D == 1) D = 2;
            else if(D == 2) D = 0;
            else if(D == 3) D = 1;
        }
    }
}
