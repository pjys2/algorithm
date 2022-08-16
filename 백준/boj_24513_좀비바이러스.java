package 백준;

import java.io.*;
import java.util.*;

public class boj_24513_좀비바이러스 {
    public static int N, M;
    public static int[][] map;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Point{
        int r, c, num;
        public Point(){};

        public Point(int r, int c, int num){
            this.r = r;
            this.c = c;
            this.num = num;
        };

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        Queue<Point> queue = new LinkedList<Point>();
        boolean[][] visited = new boolean[N][M];
        for(int i = 0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j  = 0 ; j< M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1 || map[i][j] == 2){
                    queue.offer(new Point(i,j));
                    visited[i][j] = true;
                }
            }
        }


        bfs(queue, visited);
        print(map,N,M);

        int[] countNum = new int[3];
        for (int i = 0; i<N;i++){
            for(int j = 0; j<M;j++){
                if(map[i][j] == 1){
                    countNum[0]++;
                }else if(map[i][j] == 2){
                    countNum[1]++;
                }else if(map[i][j] == 3){
                    countNum[2]++;
                }
            }
        }

        for (int i = 0; i<3;i++){
            System.out.print(countNum[i]+" ");
        }

    }

    public static void bfs(Queue<Point> queue,boolean[][] visited){
        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            cnt++;
            for(int i = 0; i<size;i++){
                Point current = queue.poll();
                for(int d = 0; d<4;d++){
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    if(nr >=0 && nr < N && nc >=0 && nc<M){
                        if((map[nr][nc] == 1  && map[current.r][current.c] == 2)|| (map[nr][nc] == 2 && map[current.r][current.c] == 1)) {
                            map[nr][nc] = 3;
                        }else if(!visited[nr][nc] && map[nr][nc] != 3){
                            map[nr][nc] = map[current.r][current.c];
                            queue.offer(new Point(nr,nc));
                            visited[nr][nc] = true;
                        }
                    }
                }
            }
        }


    }

    public static void print(int[][] map,int N,int M){
        for(int i = 0; i< N; i++){
            for(int j  = 0 ; j< M; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

    }

}
