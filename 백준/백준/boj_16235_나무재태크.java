package 백준;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16235_나무재태크 {
    public static int N, M, K;
    public static int[][] map;
    public static int[][] food;
    public static int[] dr = {-1,-1,-1,0,0,1,1,1};
    public static int[] dc = {-1,0,1,-1,1,-1,0,1};
    public static class Tree implements Comparable<Tree>{
        int r, c, age;

        public Tree(int r, int c, int age){
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public String toString() {
            return "  [" + this.r + ", " + this.c + ", " + this.age + "]  ";
        }

        @Override
        public int compareTo(Tree o) {
                return this.age - o.age;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        food = new int[N+1][N+1];

        Deque<Tree> treeList = new LinkedList<Tree>();
        Queue<Tree> deathTree = new LinkedList<Tree>();

        for (int r = 1; r <= N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                map[r][c] = 5;
                food[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            treeList.offer(new Tree(x,y,z));
        }



        for(int i = 0; i<K;i++){

            spring(treeList,deathTree);
            summer(deathTree);
            fall(treeList);
            winter();
        }

        System.out.println(treeList.size());

    }

    public static void spring(Deque<Tree> treeList, Queue<Tree> deathTree){
        int size = treeList.size();
        for (int i = 0;i<size;i++){
            Tree tree = treeList.poll();
            if(map[tree.r][tree.c] >= tree.age){
                map[tree.r][tree.c] -= tree.age;
                tree.age++;
                treeList.offer(tree);
            }else{
                deathTree.offer(tree);
            }
        }

    }

    public static void summer(Queue<Tree> deathTree){
        for (int i = 0; i<deathTree.size();i++){
            Tree tree = deathTree.poll();
            map[tree.r][tree.c] += tree.age/2;
        }
    }

    public static void fall(Deque<Tree> treeList){

        Queue<Tree> addList = new LinkedList<>();

        for (Tree tree : treeList){
            if(tree.age%5 != 0) continue;


            for (int d = 0; d < 8; d++) {
                int nr = tree.r + dr[d];
                int nc = tree.c + dc[d];
                if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
                    addList.add(new Tree(nr, nc, 1));
                }
            }
        }

        for (Tree tree : addList){
            treeList.addFirst(tree);
        }

    }

    public static void winter(){
        for (int r = 1; r <= N;r++){
            for (int c = 1; c <= N; c++) {
                map[r][c] += food[r][c];
            }
        }
    }



    public static void print(int[][] map){
        for (int r = 1; r<map.length;r++){
            for (int c = 1; c<map[r].length;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}
