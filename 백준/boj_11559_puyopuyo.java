package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_11559_puyopuyo {

    public static char[][] map;
    public static boolean[][] visited;
    public static List<Point> puyoList;
    public static int[] dr = {1,-1,0,0};
    public static int[] dc = {0,0,1,-1};
    public static class Point{
        int r, c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        for (int i = 0; i<12;i++){
            String input = br.readLine();
            for (int j = 0; j<6;j++){
                map[i][j] = input.charAt(j);
            }
        }

        int ans = 0;

        clear();
        while(findPuyo()){
            for (Point puyo : puyoList){
                map[puyo.r][puyo.c] = '.';
            }

            clear();

            ans++;



        }

        System.out.println(ans);

    }

    public static void clear(){

        for (int i = 0 ; i < 6; i++){
            List<Character> charList = new ArrayList<>();
            for (int j = 11; j>=0;j--){
                if (map[j][i] != '.'){
                    charList.add(map[j][i]);
                    map[j][i] = '.';
                }
            }

            int idx = 11;
            for (char c : charList){
                map[idx][i] = c;
                idx--;
            }
        }
    }

    public static boolean findPuyo(){

        puyoList = new ArrayList<>();
        visited = new boolean[12][6];

        for (int r = 0; r<12;r++){
            for (int c = 0;c<6;c++){
                if(map[r][c] == '.' || visited[r][c]) continue;

                fuyoCheck(new Point(r,c));
            }
        }


        if(!puyoList.isEmpty()){
            return true;
        }

        return false;
    }

    public static void fuyoCheck(Point start){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.r][start.c] = true;
        List<Point> delList = new ArrayList<>();
        while(!queue.isEmpty()){
            Point current = queue.poll();

            delList.add(current);

            for (int d = 0; d<4;d++){
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && !visited[nr][nc] && map[nr][nc] == map[start.r][start.c]){
                    queue.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }
            }
        }

        if (delList.size()>=4){
            puyoList.addAll(delList);
        }

    }


    public static void print(){
        for (int i = 0; i<12;i++){
            for (int j = 0; j<6;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
