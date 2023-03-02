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
                        map[r][c]--;
                    }
                }
            }
        }

        int iceNum = 0;
        for (int r = 0; r<mapSize;r++){
            for (int c = 0; c<mapSize;c++){
                iceNum += map[r][c];
            }
        }

        System.out.println(iceNum);


        visited = new boolean[mapSize][mapSize];
        for (int r = 0; r<mapSize;r++){
            for (int c = 0; c<mapSize;c++){
                if(visited[r][c]) continue;

                BFS(new Point(r,c));
            }
        }

        System.out.println(ans);


        System.out.println("-------------");
        print();
    }

    public static void BFS(Point start){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.r][start.c] = true;

        int iceCnt = 0;
        while(!queue.isEmpty()){
            Point current = queue.poll();
            iceCnt += map[current.r][current.c];

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

        int num = (int) Math.pow(4, L[i]-1);
        for (int r = 0; r<mapSize;r=r+range){
           for (int c = 0;c<mapSize;c=c+range){

               for (int k = 0; k<num;k++){
                    int temp = map[r][c];
                    map[r][c] = map[r+L[i]][c];
                    map[r+L[i]][c] = map[r+L[i]][c+L[i]];
                    map[r+L[i]][c+L[i]] = map[r][c+L[i]];
                    map[r][c+L[i]] = temp;
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
