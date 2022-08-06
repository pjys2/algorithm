

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class 백준_7569_토마토 {

	static int M,N,H,Ans;
	static int[][][] box; // N, M, H
	static int[] dr = {0,0,1,-1};
	static int[] dc = {-1,1,0,0};
	static class Point{
		int h;
		int r;
		int c;
		public Point(int h, int r, int c) {
			super();
			this.h = h;
			this.r = r;
			this.c = c;
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		box = new int[H][N][M];
		
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					box[h][r][c] = Integer.parseInt(st.nextToken());
				}
			}
		}


//		for (int h = 0; h < H; h++) {
//			print(box[h]);
//			System.out.println("-----------------------------------------");
//		}
		
		Ans = 0;
		if(impossible()) {
			System.out.println(-1);
		}else {
			System.out.println(Ans-1);
		}
	}

	
	


	//전부 익을 수 있는지 판단
	private static boolean impossible() {
		Queue<Point> q = new LinkedList<Point>();
		
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if(box[h][r][c] == 1) {
						q.add(new Point(h,r,c));
					}
				}
			}
		}
		
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s = 0; s<size;s++){
				Point current = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = current.r+dr[d];
					int nc = current.c+dc[d];
					if(nr >= 0 && nr < N && nc >= 0 && nc < M 
							&& box[current.h][nr][nc]==0) {
						box[current.h][nr][nc] = 1;
						q.add(new Point(current.h,nr,nc));
					}
				}
				for (int d = 0; d < 2; d++) {
					int nh = d==0?current.h+1:current.h-1;
					if(nh >= 0 && nh <H 
							&& box[nh][current.r][current.c]==0) {
						box[nh][current.r][current.c] = 1;
						q.add(new Point(nh,current.r,current.c));
					}
				}
			}
			Ans++;
		}
		
		
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if(box[h][r][c] == 0) {
						return true;
					}
				}
			}
		}
		
		return false;
	}



	private static void print(int[][] box) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(box[r][c]+" ");
			} 
			System.out.println();
		}
	}

}
