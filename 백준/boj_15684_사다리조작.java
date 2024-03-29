import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_15684_사다리조작 {
    static int N,M,H;
    static int[][] sadari;
    public static class Point{
        int r, c;
        public Point(){};
        public Point(int r, int c){
            this.r=r;
            this.c=c;

        }
    }
    public static List<Point> lineList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        sadari = new int[H+1][N+1];

        Ans = 4;
        for (int i = 0; i< M; i++){
//            System.out.println(i);
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sadari[a][b] = 1;
        }

        lineList = new ArrayList<Point>();

        for(int i =1; i<=H;i++){
            for(int j = 1; j<=N;j++){
                if(sadari[i][j] == 1) continue;
                lineList.add(new Point(i,j));
            }
        }

//        print();
//        for(Point p : lineList){
//            System.out.println("r : "+p.r+" c : "+p.c);
//        }


//        print();
//        if(sadariStart()){
//            System.out.println("성공");
//        }else{
//            System.out.println("실패");
//        }

        for(int i = 0; i<=3;i++){
            combination(new int[i],0,0);
            //combination(현재 선택한 사다리 개수, 사다리 위치, 선택할 수 있는 사다리 개수)
        }
        System.out.println(Ans==4 ? -1 : Ans);
    }

    public static int Ans;
    public static boolean[] select;
    private static void combination(int[] sel, int idx, int k) {
        if(k >= Ans){
            return;
        }

        if(k == sel.length){
            addSadari(sel);
            return;
        }
        for(int i = idx; i < lineList.size();i++){
            sel[k] = i;
            combination(sel, i+1,k+1);
        }
    }

    private static void addSadari(int[] sel) {
        for (int i = 0; i< sel.length;i++){
            int r = lineList.get(sel[i]).r;
            int c = lineList.get(sel[i]).c;
            sadari[r][c] = 1;
        }


        if(sadariStart()){
            Ans = sel.length;
        };



        for (int i = 0; i< sel.length;i++){
            int r = lineList.get(sel[i]).r;
            int c = lineList.get(sel[i]).c;
            sadari[r][c] = 0;
        }

    }


    private static boolean sadariStart() {
        for(int i =1; i<N+1;i++){
            int target = i;
            for (int j = 1; j<H+1;j++){
                if(target != N && sadari[j][target] == 1){
                    //오른쪽으로 이동
                    target = target+1;
                }else if(target != 1 && sadari[j][target-1] == 1){
                    //왼쪽으로 이동
                    target = target-1;
                }
            }
            if(target != i){
                return false;
            }
        }

        return true;
    }


    private static void print() {
        for (int i =1; i<H+1;i++){
            for(int j = 1; j<N+1;j++){
                System.out.print(sadari[i][j]+" ");
            }
            System.out.println();
        }
    }
}
