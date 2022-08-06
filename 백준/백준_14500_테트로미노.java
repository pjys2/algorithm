package 백준모음;

import java.util.Arrays;
import java.util.Scanner;

public class 백준_14500_테트로미노 {

	static int N,M,Ans;
	static int[][] paper;
	static boolean[][] check;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		paper = new int[N][M];
		check = new boolean[N][M];
		Ans = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				paper[r][c] = sc.nextInt();
			}
		}
		
		//print(paper);
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				check[r][c] = true;
				dfs(r,c,1,paper[r][c]);
				check[r][c] = false;
				
				sum(r,c,paper[r][c]);
			}
		}		
		System.out.println(Ans);
	}
	
	private static void sum(int r, int c, int sum) {
		int[] arr = new int[4];
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr >= 0 && nr < N && nc >= 0 && nc <M) {
				arr[d] = paper[nr][nc];
			}
		}
		Arrays.sort(arr);
		for (int i = 1; i < 4; i++) {
			sum += arr[i];
		}
		Ans= Math.max(Ans,sum);
	}

	private static void dfs(int r, int c, int count, int sum) {
		if(count == 4) {
			Ans = Math.max(Ans, sum);
			return;
		}
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr >= 0 && nr < N && nc >= 0 && nc <M && !check[nr][nc]) {
				check[nr][nc] = true;
				dfs(nr,nc,count+1,sum+paper[nr][nc]);
				check[nr][nc] = false;
			}
		}
	}
	
	private static void print(int[][] paper) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(paper[r][c]+" ");
			}
			System.out.println();
		}
	}
}
