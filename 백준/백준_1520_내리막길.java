package edu.ssafy.chap02;

import java.util.Arrays;
import java.util.Scanner;

public class 백준_1520_내리막길 {

	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int[] memo;
	static int N,M,Ans = 0;
	static int[][] map;
	static int[][] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = sc.nextInt();
			}
		}
		
		//print(map);
		//재귀완탐
//		dfs(0,0);
//		System.out.println(Ans);
		
		
		dp = new int[N][M];//각각의 칸에서 도착지점까지 가는 시간을 적는다.
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i],-1);
		}
		
		 Ans = dfs_dp(0,0);
//		 print(dp);
		 System.out.println(dp[0][0]);
			
	}
	
	private static int dfs_dp(int r, int c) {
		if(r==N-1 && c == M-1) {
//			print(dp);
//			System.out.println("---------------------");
			return 1;
		}
		
		if(dp[r][c] != -1) {
			return dp[r][c];
		}
		dp[r][c] = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>=0 && nr < N && nc >=0 && nc<M&&map[r][c] > map[nr][nc]) {
				//r과 c까지 오는데 걸리는 시간 + nr, nc에서 목표지까지 가는 시간
				dp[r][c] = dp[r][c] + dfs_dp(nr,nc);
			}
		}
			
		return dp[r][c];
	}

	private static void dfs(int r, int c) {
		if(r==N-1 && c ==M-1) {
			Ans++;
			
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>=0 && nr < N && nc >=0 && nc<M&&map[r][c] > map[nr][nc]) {
				dfs(nr,nc);
			}
		}
	}

	private static void print(int[][] map) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c]+" ");;
			}
			System.out.println();
		}
		
	}

}
