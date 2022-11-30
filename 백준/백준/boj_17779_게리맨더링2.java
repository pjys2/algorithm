package 백준;

import sun.awt.image.ImageWatched;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17779_게리맨더링2 {

    public static class Point{
        int r, c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static int N, ans;
    public static int[][] map;
    public static int[][] people;

    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        people = new int[N+1][N+1];

        for (int r =1; r<=N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c= 1; c<=N;c++){
                people[r][c] = Integer.parseInt(st.nextToken());
            }
        }

//        solve(4,3,1,1);

//        print(map);
        for (int r = 1; r<=N;r++){
            for (int c = 1; c<=N;c++){
                for (int d1 = 1; d1<N;d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if(r+d1+d2<=N && c-d1 >= 1 && c+d2 <=N){
                            solve(r,c,d1,d2);
                            int result = count();
                            if(ans > result){
                                ans = result;

                            }
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }

    public static int count(){
        int[] cnt = new int[6];

        for(int r = 1; r<=N;r++){
            for (int c = 1; c<=N;c++){
                cnt[map[r][c]] += people[r][c];
            }
        }

        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int i = 1 ; i<=5;i++){
            if(cnt[i] < min){
                min = cnt[i];
            }

            if(cnt[i] > max){
                max = cnt[i];
            }
        }

        return max - min;
    }

    public static void solve(int x,int y, int d1, int d2){
        map = new int[N+1][N+1];

        for (int i = 0; i <= d1;i++){
            map[x+i][y-i] = 5;
        }

        for(int i = 0; i <= d2;i++){
            map[x+i][y+i] = 5;
        }

        for (int i = 0; i<=d2;i++){
            map[x+d1+i][y-d1+i] = 5;
        }

        for (int i = 0; i<=d1;i++){
            map[x+d2+i][y+d2-i] = 5;
        }

        FillFive(new Point(x,y));

        ////////////////////////////////
        for (int r = 1; r<=N;r++){
            for (int c = 1; c<=N;c++){
                if(map[r][c] == 0){
                    if(r < x+d1 && c <= y){
                        map[r][c] = 1;
                    }
                    if(r <= x+d2 && c > y ){
                        map[r][c] = 2;
                    }
                    if(x+d1 <= r && c < y-d1+d2){
                        map[r][c] = 3;
                    }
                    if(x+d2 < r && y-d1+d2 <= c){
                        map[r][c] = 4;
                    }
                }
            }
        }
    }

    public static void FillFive(Point start){
//        Queue<Point> queue = new LinkedList<Point>();
//        queue.offer(new Point(start.r+1, start.c));
//        map[start.r+1][start.c] = 5;
//        while(!queue.isEmpty()){
//            Point current = queue.poll();
//
//            for (int d = 0; d<4;d++){
//                int nr = current.r +dr[d];
//                int nc = current.c +dc[d];
//                if(nr >= 1 && nr <= N && nc >=1 && nc <= N && map[nr][nc] == 0){
//                    map[nr][nc] = 5;
//                    queue.offer(new Point(nr, nc));
//                }
//            }
//        }
        int startPoint=0;
        int endPoint=0;
        for (int r = 1; r<=N;r++){
            int cnt = 0;
            for (int c = 1; c<=N;c++){
                if(map[r][c] == 5){
                    cnt++;
                }
            }
            if(startPoint == 0 && cnt == 1){
                startPoint = r;
            }else if(cnt == 1){
                endPoint = r;
            }
        }

        for(int r =  startPoint+1; r < endPoint; r++){
            boolean isStart = false;
            for(int c = 1; c<=N;c++){
                if(map[r][c] == 5){
                    if(!isStart){
                        isStart = true;
                    }else if(isStart){
                        isStart = false;
                    }
                }

                if(isStart){
                    map[r][c] = 5;
                }
            }
        }
    }

    public static void print(int[][] arr){
        for (int r = 1; r <= N;r++) {
            for (int c= 1; c <= N;c++){
                System.out.print(arr[r][c] +" ");
            }
            System.out.println();
        }

    }
}
