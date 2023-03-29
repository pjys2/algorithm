package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_22255_호석사우르스 {
    public static int N, M;
    public static int[][] map, minDis;
    public static Point start, end;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static class Point implements Comparable<Point> {
        int r, c, dmg, cnt;

        public Point(int r, int c, int dmg, int cnt) {
            this.r = r;
            this.c = c;
            this.dmg = dmg;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return this.dmg - o.dmg;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        minDis = new int[N + 1][M + 1];

        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int er = Integer.parseInt(st.nextToken());
        int ec = Integer.parseInt(st.nextToken());

        start = new Point(sr, sc, 0, 1);
        end = new Point(er, ec, 0, 0);


        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        BFS();

    }

    public static void BFS() {
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(start);
        boolean[][][] visited = new boolean[N + 1][M + 1][3];
        int ans = -1;
        int cnt = 1;
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int type = current.cnt % 3;
//                System.out.println("r : "+current.r +"c : "+current.c +" dmg :"+current.dmg +" cnt : "+cnt);

            if (minDis[current.r][current.c] == 0 || minDis[current.r][current.c] > current.dmg) {
                minDis[current.r][current.c] = current.dmg;
            }


            if (current.r == end.r && current.c == end.c) {
                System.out.println(current.dmg);
                return;
            }

            for (int d = 0; d < 4; d++) {
                if (type == 1 && (d == 2 || d == 3)) continue;
                if (type == 2 && (d == 0 || d == 1)) continue;

                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

//                    System.out.println("nr : "+nr +"nc : "+nc);

                if (nr < 1 || nr > N || nc < 1 || nc > M || visited[nr][nc][type] || map[nr][nc] == -1) continue;

                queue.add(new Point(nr, nc, current.dmg + map[nr][nc], current.cnt + 1));
                visited[nr][nc][type] = true;
            }


            cnt++;
        }

        System.out.println(ans);
        return;
    }

    public static void print() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                System.out.print(minDis[r][c] + " ");
            }
            System.out.println();
        }
    }

}
