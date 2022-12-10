package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2573_빙산 {

    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {-1,1,0,0};
    public static int N, M;
    public static int[][] map;

    public static class Point{
        int r, c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int r = 0; r<N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<M;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        //입력
        int time = 0;
        while(isEnd()){
            int[][] connect = new int[N][M];
            int number = 1;


            for (int r = 0; r<N;r++){
                for (int c = 0; c<M;c++){
                    if(connect[r][c] == 0 && map[r][c] != 0){
                        bfs(connect,new Point(r,c), number);
                        number++;
                    }
                }
            }


            if(number > 2){
                System.out.println(time);
                break;
            }

            next();
            time++;
        }

        if(!isEnd()){
            System.out.println(0);
        }

    }

    public static boolean isEnd(){
        for (int r = 0; r<map.length;r++){
            for (int c = 0; c<map[r].length;c++){
                if(map[r][c] != 0) {
                    return true;
                }
            }
        }

        return false;
    }


    public static void next(){
        int[][] counts = new int[N][M];

        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                int count = 0;
                if(map[r][c] == 0) continue;
                for (int d = 0; d<4;d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(nr>=0 && nr<N && nc>=0 && nc <M && map[nr][nc] == 0) {
                        count++;
                    }
                }

                counts[r][c] = count;
            }
        }

        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                if(counts[r][c] == 0) continue;

                map[r][c] -= counts[r][c];
                if(map[r][c] < 0){
                    map[r][c] = 0;
                }
            }
        }
    }


    public static void bfs(int[][] connect, Point start,int number){
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(start);
        connect[start.r][start.c] = number;
        while(!queue.isEmpty()){
            Point current = queue.poll();

            for (int d = 0; d<4;d++){
                int nr = current.r +dr[d];
                int nc = current.c +dc[d];
                if(nr >= 0 && nr <N && nc >= 0 && nc < M && map[nr][nc] != 0 && connect[nr][nc] == 0){
                    queue.offer(new Point(nr,nc));
                    connect[nr][nc] = number;
                }
            }
        }
    }

    public static void print(int[][] map){
        for (int r = 0; r<map.length;r++){
            for (int c = 0; c<map[r].length;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}
