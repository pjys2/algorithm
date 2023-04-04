package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16441_아기돼지와늑대 {
    public static int N, M;
    public static char[][] map;
    public static Queue<Point> wolf;
    public static boolean[][] visited;

    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
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

        map = new char[N][M];
        wolf = new LinkedList<>();
        visited = new boolean[N][M];
        for (int r = 0; r < N;r++){
            String input = br.readLine();
            for (int c = 0; c < M;c++){
                map[r][c] = input.charAt(c);
                if(map[r][c] == '.'){
                    map[r][c] = 'P';
                }else if(map[r][c] == 'W'){
                    wolf.add(new Point(r,c));
                    visited[r][c] = true;
                }
            }
        }

        BFS();

        StringBuilder sb = new StringBuilder();
        for (int r = 0;r<N;r++){
            for (int c = 0;c<M;c++){
                sb.append(map[r][c]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void BFS(){

        while(!wolf.isEmpty()){
            Point current = wolf.poll();
            for (int d = 0; d<4;d++){
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr < 0 || nr >= N || nc < 0 || nc >= M  || map[nr][nc] == '#') continue;

                //얼음일 경우

                Point next = new Point(nr,nc);
                if(map[nr][nc] == '+'){
                    next = slide(next,d);
                }
                if (visited[next.r][next.c]) continue;

                wolf.add(next);
                visited[next.r][next.c] = true;

                if(map[next.r][next.c] == 'P') map[next.r][next.c] = '.';
            }
        }

    }

    public static Point slide(Point current, int d){
        int nr = current.r + dr[d];
        int nc = current.c + dc[d];

        if(map[nr][nc] == '#'){
            return current;
        }else if (map[nr][nc] == '+'){
            return slide(new Point(nr,nc),d);
        }

        return new Point(nr,nc);
    }

}
