package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_16234_인구이동 {

    public static int N, L, R;
    public static boolean flag;
    public static int[][] map;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {-1,1,0,0};

    public static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static class Group{
        int groupSum;
        int groupNum;
        public Group(int groupSum, int groupNum){
            this.groupSum = groupSum;
            this.groupNum = groupNum;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        //데이터 입력
        for (int i = 0; i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i<=2000;i++) {
            flag = false;
            Map<Integer,Group> groupAvg = new HashMap<Integer,Group>();
            int[][] group = new int[N][N];
            int index = 1;

            for (int r = 0;r<N;r++){
                for (int c = 0; c<N;c++){
                    if(group[r][c] == 0){
                        BFS(group,new Point(r,c),index, groupAvg);
                        index++;
                    }
                }
            }
            
            //인구수 조정
            for (int r = 0;r<N;r++){
                for (int c = 0; c<N;c++){
                    map[r][c] = groupAvg.get(group[r][c]).groupSum/groupAvg.get(group[r][c]).groupNum;
                }
            }


            if(!flag){
                System.out.println(i);
                break;
            }
        }


    }

    private static void BFS(int[][] group,Point start,int index, Map<Integer,Group> groupAvg) {
        group[start.r][start.c] = index;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);

        int groupSum = map[start.r][start.c];
        int groupNum = 1;


        while(!queue.isEmpty()){
            Point current = queue.poll();
            int before = map[current.r][current.c];

            for(int d =0;d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];
                if(nr >= 0 && nr < N && nc >=0 && nc < N ){
                    if(Math.abs(before - map[nr][nc])>=L && Math.abs(before - map[nr][nc])<=R && group[nr][nc] == 0){
                        group[nr][nc] = index;
                        flag = true;
                        queue.offer(new Point(nr,nc));
                        groupNum++;
                        groupSum += map[nr][nc];
                    }
                }
            }
        }

        groupAvg.put(index,new Group(groupSum,groupNum));
    }


    public static void print(int[][] map){
        for (int i = 0; i<map.length;i++){
            for (int j = 0; j<map[i].length;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
