package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_3184_양 {
    public static int R, C;
    public static char[][] map;
    public static List<Point> sList;
    public static List<Point> wList;
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
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int r = 0; r<R;r++){
            String str = br.readLine();
            for (int c = 0; c<C;c++){
                map[r][c] = str.charAt(c);

            }
        }

        for (int r = 0; r<R;r++){
            for (int c = 0; c<C;c++){
                if (visited[r][c]) continue;

                sList = new ArrayList<>();
                wList = new ArrayList<>();
                DFS(new Point(r,c));

                if(sList.size() > wList.size()){
                    for (Point point : wList){
                        map[point.r][point.c] = '.';
                    }
                }else if(sList.size() <= wList.size()){
                    for (Point point : sList){
                        map[point.r][point.c] = '.';
                    }
                }
            }
        }



        int sAns = 0;
        int wAns = 0;
        for (int r = 0; r<R;r++){
            for (int c = 0; c<C;c++){
                if(map[r][c] == 'o'){
                    sAns++;
                }else if (map[r][c] == 'v'){
                    wAns++;
                }
            }
        }


        System.out.println(sAns+" "+wAns);
    }

    public static void DFS(Point point){
        visited[point.r][point.c] = true;
        if(map[point.r][point.c] == 'o'){
            sList.add(new Point(point.r,point.c));
        }else if(map[point.r][point.c] == 'v'){
            wList.add(new Point(point.r,point.c));
        }

        for(int d = 0; d<4;d++){
            int nr = point.r+dr[d];
            int nc = point.c+dc[d];
            if (nr >=0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && map[nr][nc] != '#'){
                DFS(new Point(nr,nc));
            }
        }
    }

    public static void print(){
        for (int r = 0; r<R;r++){
            for (int c = 0; c<C;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}
