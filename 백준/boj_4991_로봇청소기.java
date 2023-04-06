package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_4991_로봇청소기 {
    public static int w,h;
    public static char[][] map;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static List<Point> trash;
    public static Point start;
    public static class Point{
        int r, c, clean;
        public Point(int r, int c, int clean){
            this.r = r;
            this.c = c;
            this.clean = clean;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0) break;

            map = new char[h+1][w+1];

            trash = new ArrayList<>();
            for (int r = 1;r<=h;r++){
                String input = br.readLine();
                for (int c = 1; c<=w;c++){
                    map[r][c] = input.charAt(c-1);
                    if(map[r][c] == 'o'){
                        start = new Point(r,c,0);
                    }else if(map[r][c] == '*'){
                        trash.add(new Point(r,c,0));
                    }
                }
            }

            if(isPossible()){
                BFS();
            }else{
                System.out.println(-1);
            }
        }

    }

    public static boolean isPossible(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[h+1][w+1];
        visited[start.r][start.c] = true;


        int cnt = 0;
        while(!queue.isEmpty()){
            Point current = queue.poll();

            for (int d = 0; d<4;d++){
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(nr < 1 || nr > h|| nc < 1 || nc > w || visited[nr][nc] || map[nr][nc] == 'x') continue;


                if(map[nr][nc] == '*') cnt++;
                queue.add(new Point(nr,nc,0));
                visited[nr][nc] = true;
            }
        }

        if(cnt == trash.size()){
            return true;
        }

        return false;
    }

    public static void BFS(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        boolean[][][] visited = new boolean[h+1][w+1][1024];
        visited[start.r][start.c][0] = true;

        int end = 0;


        for (int i = 0;i<trash.size();i++){
            end = end + (1<<i);
        }


        int move = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0;s<size;s++){
                Point current = queue.poll();

                if(current.clean == end){
                    System.out.println(move);
                    return;
                }

                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];

                    if(nr < 1 || nr > h || nc < 1 || nc > w || map[nr][nc] == 'x') continue;

                    if (map[nr][nc] == '*'){
                        int idx = 0;

                        for (Point p : trash){
                            if(nr == p.r && nc == p.c) break;

                            idx++;
                        }

                        int bit = 1 << idx;
                        int nClean = current.clean;
                        if((current.clean & bit) != bit) nClean = nClean+bit;

                        if(visited[nr][nc][nClean]) continue;
                        queue.add(new Point(nr,nc,nClean));
                        visited[nr][nc][nClean] = true;
                    }else{
                        if (visited[nr][nc][current.clean]) continue;
                        queue.add(new Point(nr,nc,current.clean));
                        visited[nr][nc][current.clean] = true;
                    }
                }
            }

            move++;
        }

    }
}
