package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_21609_상어중학교 {
    public static int N, M, blockSize,rainbowCnt, rlen, clen,ans;
    public static int[][] map;
    public static boolean[][] visited;
    public static List<Point> delList;
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


        map = new int[N][N];

        for (int r = 0; r<N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<N;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }


        while(isPossible()){
            solve();
        }




        System.out.println(ans);

    }

    public static boolean isPossible(){
        for (int r = 0; r<N;r++){
            for (int c = 0; c<N;c++){
                if(map[r][c] < 1) continue;

                for (int d = 0; d<4;d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                    if (map[nr][nc] == 0 || map[nr][nc] == map[r][c]) return true;

                }
            }
        }

        return false;
    }


    public static void solve(){
        visited = new boolean[N][N];
        delList = new ArrayList<>();
        blockSize = 0;
        rainbowCnt = 0;
        rlen = 0;
        clen = 0;

        for (int r = 0; r<N;r++){
            for (int c = 0; c<N;c++){
                if(visited[r][c] || map[r][c] < 1) continue;
                BFS(new Point(r,c), map[r][c]);
            }
        }

        ans += (int)Math.pow(delList.size(),2);
        for (Point point : delList){
            map[point.r][point.c] = -2;
        }

        gravity();


        map = rotate();


        gravity();


    }

    public static void gravity(){
        for (int c = 0; c< N;c++){
            for (int r = N-1; r>0;r--){

                if(map[r][c] != -2) continue;
                for (int  k = r-1;k>=0;k--){
                    if(map[k][c] >= 0){
                        map[r][c] = map[k][c];
                        map[k][c] = -2;
                        break;
                    }else if (map[k][c] == -1){
                        r = k;
                        break;
                    }
                }

            }
        }
    }

    public static int[][] rotate(){
        int[][] rotateMap = new int[N][N];
        for (int r = 0; r< N;r++){
            for (int c = 0; c<N;c++){
                rotateMap[r][c] = map[c][N-1-r];
            }
        }

        return rotateMap;
    }


    public static void BFS(Point start, int color){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        List<Point> blockList = new ArrayList<>();
        List<Point> rainbowList = new ArrayList<>();
        visited[start.r][start.c] = true;
        Point standard = start;
        int rainbow = 0;
        while(!queue.isEmpty()){
            Point current = queue.poll();

            if(map[current.r][current.c] != 0 && standard.r > current.r){
                standard = current;
            }else if(map[current.r][current.c] != 0 && standard.r == current.r && standard.c > current.c){
                standard = current;
            }

            if(map[current.r][current.c] == 0) {
                rainbowList.add(current);
                rainbow++;
            }

            blockList.add(current);

            for (int d = 0; d<4;d++){
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

                if(map[nr][nc] == 0 || map[nr][nc] == color){
                    queue.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }
            }
        }


        for (Point point : rainbowList){
            visited[point.r][point.c] = false;
        }


        if(blockSize < blockList.size()){
            delList = blockList;
            blockSize = blockList.size();
            rainbowCnt = rainbow;
            rlen = standard.r;
            clen = standard.c;
        }else if (blockSize == blockList.size() && rainbowCnt < rainbow){
            delList = blockList;
            blockSize = blockList.size();
            rainbowCnt = rainbow;
            rlen = standard.r;
            clen = standard.c;
        }else if (blockSize == blockList.size() && rainbowCnt == rainbow && rlen < standard.r){
            delList = blockList;
            blockSize = blockList.size();
            rainbowCnt = rainbow;
            rlen = standard.r;
            clen = standard.c;
        }else if(blockSize == blockList.size() && rainbowCnt == rainbow && rlen == standard.r && clen < standard.c){
            delList = blockList;
            blockSize = blockList.size();
            rainbowCnt = rainbow;
            rlen = standard.r;
            clen = standard.c;
        }

    }



    public static void print(){
        for (int r = 0; r<N;r++){
            for (int c = 0; c<N;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}
