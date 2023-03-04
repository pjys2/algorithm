package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_20058_마법사상어와파이어스톰 {

    public static int N, Q,mapSize,ans;
    public static int[][] map;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static int[] L;
    public static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        mapSize = (int) Math.pow(2,N);
        map = new int[mapSize][mapSize];
        L = new int[Q];

        for (int r = 0; r<mapSize;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<mapSize;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i<Q;i++){
            L[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i<Q;i++){
            if(L[i] != 0){
                divide(i);
            }


            //얼음 녹음
            List<Point> iceList = new ArrayList<>();
            for (int r = 0; r<mapSize;r++){
                for (int c = 0; c<mapSize;c++){
                    if(map[r][c] == 0) continue;

                    int cnt = 0;
                    for (int d = 0; d<4;d++){
                        int nr = r+dr[d];
                        int nc = c+dc[d];
                        if(nr >= 0 && nr < mapSize && nc >= 0 && nc < mapSize && map[nr][nc]!=0){
                            cnt++;
                        }
                    }

                    if(cnt < 3){
                        iceList.add(new Point(r,c));
                    }
                }
            }

            for (Point ice : iceList){
                map[ice.r][ice.c]--;
            }



        }
        

        int iceNum = 0;
        for (int r = 0; r<mapSize;r++){
            for (int c = 0; c<mapSize;c++){
                iceNum += map[r][c];
            }
        }

        System.out.println(iceNum);

        ans = 0;
        visited = new boolean[mapSize][mapSize];
        for (int r = 0; r<mapSize;r++){
            for (int c = 0; c<mapSize;c++){
                if(visited[r][c] || map[r][c] == 0) continue;

                BFS(new Point(r,c));
            }
        }

        System.out.println(ans);
    }

    public static void BFS(Point start){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.r][start.c] = true;


        int iceCnt = 0;
        while(!queue.isEmpty()){
            Point current = queue.poll();
            iceCnt++;

            for (int d = 0; d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];

                if(nr >= 0 && nr < mapSize && nc >= 0 && nc < mapSize && !visited[nr][nc] && map[nr][nc] != 0){
                    queue.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }
            }
        }
        if(ans < iceCnt){
            ans = iceCnt;
        }


    }


    public static void divide(int i){
        int range = (int) Math.pow(2,L[i]);


        for (int r = 0; r<mapSize;r=r+range){
           for (int c = 0;c<mapSize;c=c+range){
                //이부분 고쳐야됨
               int[][] copyMap = new int[range][range];


               int nr = 0;
               int nc = 0;
               for (int cc = c; cc<c+range;cc++){
                   for(int cr = r+range-1;cr >= r;cr--){
                           copyMap[nr][nc] = map[cr][cc];
                           nc++;
                           if(nc == range){
                               nc = 0;
                               nr++;
                           }
                   }
               }

               for(int cr = 0;cr <range;cr++){
                   for (int cc = 0; cc<range;cc++){
                       map[r+cr][c+cc] = copyMap[cr][cc];
                   }
               }
           }
        }
    }

    public static void print(){
        for (int r = 0; r<map.length;r++){
            for (int c = 0; c<map[r].length;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}
