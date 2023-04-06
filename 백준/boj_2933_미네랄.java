package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2933_미네랄 {
    public static int R, C, N;
    public static char[][] map;
    public static int[][] group;
    public static int[] height;
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
        for (int r = 1; r<= R;r++){
            String input = br.readLine();
            for (int c = 1; c<=C;c++){
                map[r][c] = input.charAt(c-1);
            }
        }
        N = Integer.parseInt(br.readLine());
        height = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i<=N;i++){
            height[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 1; i<=N;i++){
            //클러스터 삭제
            if(i % 2 == 1){
                for (int c = 1;c<=C;c++){
                    if(map[R-height[i]+1][c] == 'x'){
                        map[R-height[i]+1][c] = '.';
                        break;
                    }
                }
            }else{
                for (int c = C;c>0;c--) {
                    if (map[R-height[i]+1][c] == 'x') {
                        map[R-height[i]+1][c] = '.';
                        break;
                    }
                }
            }

            //BFS로 그룹 탐색
            group = new int[R+1][C+1];
            int  num = 1;
            for (int r = R; r>0;r--){
                for(int c = C;c>0;c--){
                    if (group[r][c]!=0  || map[r][c] != 'x') continue;

                    BFS(r,c,num);
                    num++;
                }
            }

            //그룹이 2개 이상이라면 공중에 있는 클러스터를 찾아서 아래로 내려줘야함
            boolean[] visited = new boolean[num+1];
            if(num > 1){
                for (int r = R; r>0;r--){
                    for(int c = C;c>0;c--){
                        if (visited[group[r][c]] || map[r][c] != 'x') continue;

                        visited[group[r][c]] = true;
                        down(r,c,group[r][c]);
                    }
                }
            }

//            printGroup();
//            print();
//            System.out.println("---------");

        }

        print();
    }

    public static void down(int sr, int sc, int num){
        List<Point> pointList = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(sr,sc));
        boolean[][] visited = new boolean[R+1][C+1];
        visited[sr][sc] = true;
        int[] bottom = new int[C+1];
        bottom[sc] = sr;
        while(!queue.isEmpty()){
            Point current = queue.poll();
            pointList.add(current);

            if(current.r == R) return;
            for (int d = 0; d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];
                if(nr < 1 || nr > R || nc < 1 || nc > C || visited[nr][nc] || map[nr][nc] == '.') continue;

                //열별로 가장 아래 있는 블럭 찾기
                if(nr > bottom[nc]) bottom[nc] = nr;

                queue.add(new Point(nr,nc));
                visited[nr][nc] = true;
            }
        }

        int min = R;
        // 클러스터 또는 바닥까지의 거리 찾기
        for (int c = 1; c<=C;c++){
            if (bottom[c] != 0){
                int cnt = 0;
                for (int r = bottom[c]+1; r<=R;r++){
                    cnt++;
                    if (map[r][c] == 'x'){
                        cnt--;
                        break;
                    }
                }

                if(min > cnt){
                    min = cnt;
                }
            }
        }
//        System.out.println(Arrays.toString(bottom));
//        System.out.println(min);
        Comparator<Point> comparator = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o2.r - o1.r;
            }
        };

        Collections.sort(pointList,comparator);

        for (Point point : pointList){
            int nextR = point.r+min;
            map[nextR][point.c] = map[point.r][point.c];
            map[point.r][point.c] = '.';
        }
    }

    public static void BFS(int sr, int sc, int num){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(sr,sc));

        while(!queue.isEmpty()){
            Point current = queue.poll();

            for (int d = 0;d<4;d++){
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr< 1 || nr > R || nc < 1 || nc > C || group[nr][nc]!= 0 || map[nr][nc] != 'x') continue;

                queue.add(new Point(nr,nc));
                group[nr][nc] = num;
            }
        }



    }

    public static void printGroup(){
        for (int r = 1; r<= R;r++){
            for (int c = 1; c<=C;c++){
                System.out.print(group[r][c]);
            }
            System.out.println();
        }
    }

    public static void print(){
        for (int r = 1; r<= R;r++){
            for (int c = 1; c<=C;c++){
                System.out.print(map[r][c]);
            }
            System.out.println();
        }
    }
}
