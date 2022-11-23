import java.io.*;
import java.util.*;

public class boj_25307_시루의백화점구경 {
    static int N, M, K;
    static int[][] map;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
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

        Point siru = new Point();
        for(int i =0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 4){
                    siru.r = i;
                    siru.c = j;
                }
            }
        }

        boolean[][] visited = new boolean[N][M];
        for(int i =0; i< N; i++){
            for(int j = 0; j<M;j++){
                if(map[i][j] == 3){
                    maneFind(new Point(i,j),visited);
                }
            }
        }



        int ans = move(siru);
        System.out.println(ans);


    }

    private static void maneFind(Point mane, boolean[][] visited) {
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(mane);
        map[mane.r][mane.c] = 1;
        visited[mane.r][mane.c] = true;
        int distance = -1;
        while(!queue.isEmpty()){
            distance++;
            if(distance == K) break;
            int length = queue.size();
//            System.out.println("------------------------------------------");
            for(int i =0;i<length;i++){
                Point current = queue.poll();
//                System.out.println("좌표 : ["+current.r+", "+current.c+"]");


                for (int d = 0; d<4;d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];

//                        System.out.println("nr : "+nr+", nc :"+nc);


                    if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
                        if(map[nr][nc] != 3) {
                            map[nr][nc] = 1;
                        }
                        queue.offer(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }

        }
//        System.out.println("["+mane.r+", "+mane.c+"]");
//        print(map);
//        System.out.println("----------------------------");
    }

    private static int move(Point siru) {
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(siru);
        boolean[][] visited = new boolean[N][M];
        visited[siru.r][siru.c] = true;

        int energy = 0;
        while(!queue.isEmpty()){
            energy++;
            int length = queue.size();
            for(int i = 0; i<length;i++){
                Point current = queue.poll();


                for(int d = 0; d<4;d++){
                    int nr = current.r +dr[d];
                    int nc = current.c +dc[d];
                    if(nr >=0 && nr <N && nc >=0 && nc <M && !visited[nr][nc] && map[nr][nc] != 1){
                        if(map[nr][nc] == 2){
                            return energy;
                        }

                        queue.offer(new Point(nr,nc));
                        visited[nr][nc] = true;

                    }
                }
            }
        }
        return -1;
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
