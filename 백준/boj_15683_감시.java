import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_15683_감시 {

    public static int N,M,Ans;
    public static int[][] map;

    public static int[] dr = {-1,0,1,0};
    public static int[] dc = {0,1,0,-1};

    public static class Camera{
        int dir,r,c;

        public Camera(){};

        public Camera(int dir,int r, int c){
            this.dir = dir;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        Ans = 64;

        List<Camera> cameras = new ArrayList<Camera>();
        for(int i = 0; i< N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j< M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > 0 && map[i][j] <5){
                    cameras.add(new Camera(0,i,j));
                }
            }
        }


        for(int i = 0; i<N;i++){
            for(int j =  0; j<M;j++){
                if(map[i][j] == 5){
                    findRange5(new Camera(0,i,j));
                }
            }
        }


        permutation(0,cameras);
        System.out.println(Ans);
//        print(map);
    }


    private static void permutation(int idx,List<Camera> cameras) {
        if(idx == cameras.size()){
            findCamera(cameras);
            return;
        }

        for(int i = 0; i<4;i++){
            cameras.get(idx).dir = i;
            permutation(idx+1, cameras);
        }


    }

    private static void findCamera(List<Camera> cameras) {
        int[][] mapCopy = mapCopy();

        for(Camera camera : cameras){
            if(map[camera.r][camera.c] == 1){
                findRange1(camera,mapCopy);
            }else if(map[camera.r][camera.c] == 2){
                findRange2(camera,mapCopy);
            }else if(map[camera.r][camera.c] == 3){
                findRange3(camera,mapCopy);
            }else if(map[camera.r][camera.c] == 4){
                findRange4(camera,mapCopy);
            }
        }

        int cnt = 0;

        for(int i = 0; i<N;i++){
            for(int j = 0; j<M;j++){
                if(mapCopy[i][j] == 0){
                    cnt++;
                }
            }
        }


//        if(cnt <= Ans){
//            System.out.println(cnt);
//            print(mapCopy);
//            for(Camera camera:cameras){
//                System.out.print(camera.dir + " ");
//            }
//            System.out.println();
//            System.out.println("------------------------");
//
//        }
        Ans = Math.min(Ans,cnt);
    }

    private static int[][] mapCopy() {
        int[][] mapCopy = new int[N][M];
        for (int i = 0; i<N; i++){
            for(int j =  0; j<M;j++){
                mapCopy[i][j] = map[i][j];
            }
        }
        return mapCopy;
    }

    private static void findRange1(Camera camera, int[][] mapCopy) {
            int nr = camera.r;
            int nc = camera.c;
            while(nr >= 0 && nr<N && nc >= 0 && nc <M && mapCopy[nr][nc] != 6){
                if(mapCopy[nr][nc] == 0) {
                    mapCopy[nr][nc] = 9;
                }
                nr = nr+dr[camera.dir];
                nc = nc+dc[camera.dir];
            }
    }

    private static void findRange2(Camera camera, int[][] mapCopy) {
        for (int i =0; i<2;i++){
            int nr = camera.r;
            int nc = camera.c;
            int dir = (i == 0) ? camera.dir : (camera.dir+2)%4;
            while(nr >= 0 && nr<N && nc >= 0 && nc <M && mapCopy[nr][nc] != 6){
                if(mapCopy[nr][nc] == 0) {
                    mapCopy[nr][nc] = 9;
                }
                nr = nr+dr[dir];
                nc = nc+dc[dir];
            }
        }
    }

    private static void findRange3(Camera camera, int[][] mapCopy) {
        for (int i =0; i<2;i++){
            int nr = camera.r;
            int nc = camera.c;
            int dir = (camera.dir+i)%4;
            while(nr >= 0 && nr<N && nc >= 0 && nc <M && mapCopy[nr][nc] != 6){
                if(mapCopy[nr][nc] == 0) {
                    mapCopy[nr][nc] = 9;
                }
                nr = nr+dr[dir];
                nc = nc+dc[dir];
            }
        }
    }

    private static void findRange4(Camera camera, int[][] mapCopy) {
        for (int i =0; i<3;i++){
            int nr = camera.r;
            int nc = camera.c;
            int dir = (camera.dir+i)%4;
            while(nr >= 0 && nr<N && nc >= 0 && nc <M && mapCopy[nr][nc] != 6){
                if(mapCopy[nr][nc] == 0) {
                    mapCopy[nr][nc] = 9;
                }
                nr = nr+dr[dir];
                nc = nc+dc[dir];
            }
        }
    }

    private static void findRange5(Camera camera) {
        for(int d = 0; d<4;d++){
            int nr = camera.r;
            int nc = camera.c;
//            System.out.println("d : "+d);
            while(nr >= 0 && nr<N && nc >= 0 && nc <M && map[nr][nc] != 6){
//                System.out.println("nr : "+nr+", nc :" + nc);
                if(map[nr][nc] == 0) {
                    map[nr][nc] = 9;
                }
                nr = nr+dr[d];
                nc = nc+dc[d];
            }
//            System.out.println("-------------------------");
        }
    }

    private static void print(int[][] map) {
        for(int i = 0; i< N;i++){
            for(int j = 0; j< M;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
