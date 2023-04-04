package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class boj_16954_움직이는미로탈출 {
    public static char[][] map;
    public static int[] dr = {-1,-1,-1,0,0,0,1,1,1};
    public static int[] dc = {-1,0,1,-1,0,1,-1,0,1};
    public static class Point{
        int r , c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[8][8];

        for (int r = 0; r<8;r++){
            String input = br.readLine();
            for (int c = 0; c<8;c++){
                map[r][c] = input.charAt(c);
            }
        }


        BFS();

    }

    public static void BFS(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(7,0));
        boolean[][][] visited = new boolean[8][8][10000];
        visited[7][0][0] = true;

        int time = 1;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                Point current = queue.poll();

                if (current.r == 0 && current.c == 7){
                    System.out.println(1);
                    return;
                }
                if(map[current.r][current.c] == '#') continue;

                for (int d = 0; d<9;d++){
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    if(nr < 0 || nr >= 8 || nc < 0 || nc >= 8 || visited[nr][nc][time] || map[nr][nc] == '#') continue;

                    queue.add(new Point(nr,nc));
                    visited[nr][nc][time] = true;
                }
            }
            time++;
            down();
        }

        System.out.println(0);
    }


    public static void down(){
        for (int c = 0; c<8;c++){
            for (int r = 7;r>=0;r--){
                if(map[r][c] != '#') continue;

                if (r == 7){
                    map[r][c] = '.';
                }else{
                    map[r+1][c] = '#';
                    map[r][c] = '.';
                }
            }
        }
    }
}
