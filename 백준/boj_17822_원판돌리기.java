package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_17822_원판돌리기 {
    public static int N, M, T;
    public static int[][] board;
    public static boolean[][] visited;
    public static boolean check;
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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[N+1][M+1];
        for (int r = 1; r<=N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c<=M;c++){
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 1; t<=T;t++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for (int n = 1; n <= N; n++){
                if(n % x != 0) continue;
                rotate(n,d,k);

            }



            //회전시키고 BFS 탐색
            visited = new boolean[N+1][M+1];
            check = false;
            for (int r = 1; r<=N;r++){
                for (int c = 1; c<=M;c++){
                    if(visited[r][c] || board[r][c] == 0) continue;
                    BFS(new Point(r,c), board[r][c]);
                }
            }

            if (!check){
                int total = 0;
                int cnt = 0;
                for (int r = 1; r<=N;r++){
                    for (int c = 1; c<=M;c++){
                        if (board[r][c] == 0) continue;

                        total += board[r][c];
                        cnt++;
                    }
                }

                float avg = (float)total/cnt;

                for (int r = 1; r<=N;r++){
                    for (int c = 1; c<=M;c++){
                        if (board[r][c] == 0) continue;

                        if ((float)board[r][c] < avg) board[r][c] += 1;
                        else if((float)board[r][c] > avg) board[r][c] -= 1;
                    }
                }
            }

//            print();
//            System.out.println("------------------------");
        }

        int ans = 0;
        for (int r = 1; r<=N;r++){
            for (int c = 1; c<=M;c++){
                ans += board[r][c];
            }
        }

        System.out.println(ans);
    }

    public static void BFS(Point start, int num){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.r][start.c] = true;
        List<Point> pointList = new ArrayList<>();
        while(!queue.isEmpty()){
            Point current = queue.poll();

            pointList.add(current);
            for (int d = 0; d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];
                if(nr < 1 || nr > N) continue;
                if (nc == 0) nc = M;
                if (nc == M+1) nc = 1;

                if (visited[nr][nc]||board[nr][nc] != num) continue;
                queue.add(new Point(nr,nc));
                visited[nr][nc] = true;
            }
        }

        if (pointList.size()>1){
            for (Point point : pointList){
                board[point.r][point.c] = 0;
            }
            check = true;
        }
    }

    public static void rotate(int target, int d, int k){

        if(d == 0){
            //시계 방향 회전;
            int[] temp = new int[k+1];


            for (int i = k; i>=1;i--){
                temp[i] = board[target][M-k+i];
            }

            for (int i = M; i > k;i--){
                int idx = i-k;
                if(idx <= 0) idx = M + idx;

                board[target][i] = board[target][idx];
            }

            for (int i = k; i>=1;i--){
                board[target][i] = temp[i];
            }

        }else{
            //반시계 방향 회전
            int[] temp = new int[k+1];

            for (int i = 1; i<=k;i++){
                temp[i] = board[target][i];
            }

            for (int i = 1; i <= M;i++){
                int idx = i+k;
                if(idx > M) idx = idx-M;

                board[target][i] = board[target][idx];
            }

            for (int i = 1; i<=k;i++){
                board[target][M-k+i] = temp[i];
            }

        }

    }

    public static void print(){
        for (int r = 1; r<=N;r++){
            for (int c = 1; c<=M;c++){
                System.out.print(board[r][c]+" ");
            }
            System.out.println();
        }

    }
}
