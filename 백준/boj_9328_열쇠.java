package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_9328_열쇠 {
    public static int h, w, ans;
    public static Queue<Point> queue;
    public static List<Point> startPoint;
    public static boolean[][] visited;
    public static boolean[] getKey;
    public static char[][] map;
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
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc<=T;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            ans = 0;
            queue = new LinkedList<>();
            startPoint = new ArrayList<>();
            getKey = new boolean[27];
            map = new char[h][w];
            visited = new boolean[h][w];

            for (int r = 0;r<h;r++){
                String input = br.readLine();
                for (int c = 0; c<w;c++){
                    map[r][c] = input.charAt(c);
                }
            }

            String input = br.readLine();

            if(input.charAt(0) != '0'){
                for (int i = 0; i<input.length();i++){
                    int c = input.charAt(i)-'a';
                    getKey[c] = true;
                }
            }

            for (int r = 0;r<h;r++){
                for (int c = 0; c<w;c++){
                    if (map[r][c] == '*') continue;

                    if (map[r][c] >= 'A' && map[r][c] <= 'Z'){
                        int key = map[r][c] - 'A';
                        if(getKey[key]) map[r][c] = '.';
                    }

                    if(r == 0 || r == h-1 || c == 0 || c == w-1){
                        if(map[r][c] >= 'a' && map[r][c] <= 'z') {
                            int key = map[r][c] - 'a';
                            getKey[key] = true;
                            map[r][c] = '.';
                        }else if(map[r][c] == '$'){
                            ans++;
                            map[r][c] = '.';
                        }
                        startPoint.add(new Point(r,c));
                        visited[r][c] = true;
                    }
                }
            }

            BFS();

            System.out.println(ans);
        }
    }


    public static void BFS(){
        for (Point start : startPoint){
            if(map[start.r][start.c] >= 'A' && map[start.r][start.c] <= 'Z' ) continue;
            queue.add(start);
        }

        while(!queue.isEmpty()){
            Point current = queue.poll();

            for (int d = 0; d<4;d++){
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr < 0 || nr >= h || nc < 0 || nc >= w || visited[nr][nc] || map[nr][nc] == '*') continue;


                if(map[nr][nc] >= 'A' && map[nr][nc] <= 'Z') {
                    int key = map[nr][nc] - 'A';
                    if (!getKey[key]) continue;
                    else map[nr][nc] = '.';
                }else if(map[nr][nc] >= 'a' && map[nr][nc] <= 'z'){
                    int key = map[nr][nc] - 'a';

                    if(getKey[key]) map[nr][nc] = '.';
                    else{
                        queue.clear();
                        visited = new boolean[h][w];
                        getKey[key] = true;
                        map[nr][nc] = '.';
                        for (Point start : startPoint){
                            if(map[start.r][start.c] >= 'A' && map[start.r][start.c] <= 'Z'){
                                int k = map[start.r][start.c] - 'A';
                                if(!getKey[k]) continue;
                                else map[start.r][start.c] = '.';
                            }
                            queue.add(start);
                        }
                    }
                }else if(map[nr][nc] == '$'){
                    ans++;
                    map[nr][nc] = '.';
                }

                queue.add(new Point(nr,nc));
                visited[nr][nc] = true;
            }
        }
    }

    public static void print(){
        for (int r = 0; r<h;r++){
            for (int c = 0; c<w;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}
