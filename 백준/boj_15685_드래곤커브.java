package 백준;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_15685_드래곤커브{

    public static int N;
    public static int[] dr = {0,-1,0,1};
    public static int[] dc = {1,0,-1,0};
    public static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[101][101];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> dList = new ArrayList<Integer>();
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dList.add(d);
            //세대 수만큼 반복
            map[y][x] = 1;
            y = y + dr[d];
            x = x + dc[d];
            map[y][x] = 1;
            for (int j = 0; j < g; j++) {
                int size = dList.size();
                for (int z = size - 1; z >= 0; z--) {
                    y = y + dr[(dList.get(z) + 1) % 4];
                    x = x + dc[(dList.get(z) + 1) % 4];
                    map[y][x] = 1;
                    dList.add((dList.get(z) + 1) % 4);
                }
            }
        }

        int cnt = 0;
        for(int i = 0; i<100;i++){
            for(int j = 0; j<100;j++){
                if(map[i][j] == 1 && map[i+1][j] == 1 && map[i][j+1] == 1 && map[i+1][j+1] == 1){
                    cnt++;
                }
            }
        }
        System.out.println(cnt);


//        for(Info info:infoList){
//            System.out.println(info.toString());
//        }

    }

    private static void print() {
        for(int i = 0; i<100;i++){
            for(int j = 0; j<100;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
