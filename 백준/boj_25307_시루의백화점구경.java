package 백준;


import java.io.*;
import java.util.*;

public class boj_25307_시루의백화점구경 {
    static int N, M, K;
    static int[][] map;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    public static List<Point> maneList;
    public static Point siruStart;
    public static boolean[][] maneVisit;
    public static class Point{
        int r, c;

        public Point(){}

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        maneList = new ArrayList<>();

        maneVisit = new boolean[N][M];

        for (int r = 0; r<N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<M;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 3){
                    maneList.add(new Point(r,c));
                }else if(map[r][c] == 4){
                    siruStart = new Point(r,c);
                }
            }
        }

        for (Point mane : maneList){
            mapCheck(mane);
        }

        siruMove(siruStart);

    }

    public static void mapCheck(Point start){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        maneVisit[start.r][start.c] = true;

        for (int cnt = 0;cnt<=K;cnt++){
            int size = queue.size();
            for(int s = 0; s<size;s++) {
                Point current = queue.poll();

                map[current.r][current.c] = -1;


                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    if(nr >= 0 && nr < N && nc >= 0 && nc < M && !maneVisit[nr][nc]){
                        queue.add(new Point(nr,nc));
                        maneVisit[nr][nc] = true;
                    }
                }
            }
        }

    }

    public static void siruMove(Point start){
        Queue<Point> queue = new LinkedList<>();

        queue.add(start);
        boolean[][] visited = new boolean[N][M];
        visited[start.r][start.c] = true;

        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                Point current = queue.poll();

                if(map[current.r][current.c] == 2){
                    System.out.println(cnt);
                    return;
                }

                for (int d = 0; d<4;d++){
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] != -1){
                        queue.add(new Point(nr,nc));
                        visited[nr][nc] = true;
                    }
                }
            }

            cnt++;
        }

        System.out.println(-1);
    }


    private static void print(int[][] map) {
        for(int i =0; i< N; i++){
            for(int j = 0; j<M;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
