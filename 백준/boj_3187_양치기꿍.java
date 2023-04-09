package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_3187_양치기꿍 {
    public static int R,C,wCnt,sCnt;
    public static char[][] map;
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

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        wCnt = 0;
        sCnt = 0;

        map = new char[R][C];

        for (int r = 0; r<R;r++){
            String input = br.readLine();
            for (int c = 0; c<C;c++){
                map[r][c] = input.charAt(c);
                if(map[r][c] == 'v') wCnt++;
                else if(map[r][c] == 'k') sCnt++;
            }
        }

        visited = new boolean[R][C];
        for (int r = 0; r<R;r++){
            for (int c = 0; c<C;c++){
                if (visited[r][c]) continue;
                BFS(new Point(r,c));
            }
        }

        System.out.println(sCnt+" "+wCnt);

    }

    public static void BFS(Point start){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.r][start.c] = true;
        List<Point> wolfList = new ArrayList<>();
        List<Point> sheepList = new ArrayList<>();

        while(!queue.isEmpty()){
            Point current = queue.poll();
            if(map[current.r][current.c] == 'v'){
                wolfList.add(current);
            }else if (map[current.r][current.c] == 'k'){
                sheepList.add(current);
            }
            for (int d = 0; d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];
                if(nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || map[nr][nc] == '#') continue;

                queue.add(new Point(nr,nc));
                visited[nr][nc] = true;
            }
        }

        if(sheepList.size() > wolfList.size()){
            wCnt -= wolfList.size();
        }else{
            sCnt -= sheepList.size();
        }
    }


}
