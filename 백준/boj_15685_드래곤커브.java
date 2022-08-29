package 백준;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_15685_드래곤커브{

    public static int N;
    public static int[] dr = {0,-1,0,1};
    public static int[] dc = {1,0,-1,0};
    public static class Info{
        int x,y,d,g;

        public Info(int x, int y, int d, int g){
            this.x = x;
            this.y = y;
            this.d = d;
            this.g = g;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "x=" + x +
                    ", y=" + y +
                    ", d=" + d +
                    ", g=" + g +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Info> infoList = new ArrayList<Info>();

        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            infoList.add(new Info(x,y,d,g));
        }


        for(Info info:infoList){
            System.out.println(info.toString());
        }

    }
}
