package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_3197_백조의호수 {
    public static int R,C;
    public static char[][] map;
    public static Point start,end;
    public static boolean isPossible;
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

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R+1][C+1];
        start = new Point(0,0);
        end = new Point(0,0);
        for (int r = 1; r<=R;r++){
            String input = br.readLine();
            for (int c = 1; c<=C;c++){
                map[r][c] = input.charAt(c-1);
                if(start.r == 0 && map[r][c] == 'L') {
                    start = new Point(r,c);
                }else if(start.r != 0 && map[r][c] == 'L'){
                    end = new Point(r,c);
                }
            }
        }


        int time = 0;
        while(true){
            BFS();

            if(isPossible){
                break;
            }

            melt();

            time++;
        }


        System.out.println(time);
    }

    public static void melt(){
        List<Point> iceList = new ArrayList<>();

        for (int r = 1; r<=R;r++){
            for (int c = 1; c<=C;c++){
                if(map[r][c] != 'X') continue;
                for (int d = 0;d<4;d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    if (nr < 1 || nr > R || nc < 1|| nc > C) continue;

                    if(map[nr][nc] == '.'){
                        iceList.add(new Point(r,c));
                        break;
                    }
                }
            }
        }

        for (Point ice : iceList){
            map[ice.r][ice.c] = '.';
        }
    }

    public static void BFS(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[R+1][C+1];
        visited[start.r][start.c] = true;

        while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0; s<size;s++){
                Point current = queue.poll();


                if(current.r == end.r && current.c == end.c){
                    isPossible = true;
                    return;
                }

                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];

                    if(nr < 1 || nr > R || nc <1 || nc > C || visited[nr][nc] || map[nr][nc] == 'X') continue;
                    queue.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }
}
