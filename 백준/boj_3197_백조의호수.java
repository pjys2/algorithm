package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_3197_백조의호수 {
    public static int R,C;
    public static char[][] map;
    public static Queue<Point> iceQ;
    public static Queue<Point> swanQ;
    public static boolean[][] iceVisit, swanVisit;
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
        swanQ = new LinkedList<>();
        iceQ = new LinkedList<>();
        iceVisit = new boolean[R+1][C+1];
        swanVisit = new boolean[R+1][C+1];
        for (int r = 1; r<=R;r++){
            String input = br.readLine();
            for (int c = 1; c<=C;c++){
                map[r][c] = input.charAt(c-1);
                if(swanQ.isEmpty() && map[r][c] == 'L'){
                    swanQ.add(new Point(r,c));
                    swanVisit[r][c] = true;
                    map[r][c] = '.';
                }
            }
        }

        for (int r = 1; r<=R;r++){
            for (int c = 1; c<=C;c++){
                if (map[r][c] != 'X') continue;
                for (int d = 0; d<4;d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    if (nr < 1 || nr >R || nc < 1|| nc >C) continue;
                    if (map[nr][nc] != 'X'){
                        iceQ.add(new Point(r,c));
                        iceVisit[r][c] = true;
                        break;
                    }
                }
            }
        }

        solve();




    }
    public static void solve(){
        int time = 0;
        if(findSwan()){
            System.out.println(time);
            return;
        }
        while(!iceQ.isEmpty()){

            int size = iceQ.size();

            for (int s = 0; s<size;s++){
                Point current = iceQ.poll();

//                System.out.println("r:"+current.r + " c:"+current.c);


                map[current.r][current.c] = '.';

                for (int d = 0;d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    if (nr < 1 || nr >R || nc < 1|| nc >C || iceVisit[nr][nc] || map[nr][nc] != 'X') continue;

                    iceQ.add(new Point(nr,nc));
                    iceVisit[nr][nc] = true;
                }

            }


            time++;

            if(findSwan()){
                System.out.println(time);
                return;
            }
        }
    }

    public static boolean findSwan(){
        List<Point> swanList = new ArrayList<>();
        while(!swanQ.isEmpty()){
            int size = swanQ.size();

            for (int s = 0; s<size;s++){
                Point current = swanQ.poll();

                boolean iceCheck = false;
                if(map[current.r][current.c] == 'L'){
                    return true;
                }

                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    if(nr < 1 || nr > R || nc < 1 || nc > C || swanVisit[nr][nc]) continue;

                    if(map[nr][nc] == 'X'){
                        iceCheck = true;
                        continue;
                    }

                    //얼음을 만났을 때 다시 저장하도록 구현
                    swanQ.add(new Point(nr,nc));
                    swanVisit[nr][nc] = true;
                }

                if (iceCheck){
                    swanList.add(current);
                }
            }
        }

        for (Point swan : swanList){
            swanQ.add(swan);
        }

        return false;
    }


    public static void print(){
        for (int r = 1; r<=R;r++){
            for (int c = 1; c<=C;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }


}
