package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolver;

/**
 * Brute-force algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverOneQueenPerLine extends GenericNQueensSolver {

	/** Chessboard represented by a 2 dimentionnal array. */
	private boolean[][] chessboard;
	/** Array to count queens on each column. */
	private int[] columnCounts;
	/** Array to count queens on ascending diagonals, diagonal number = x + y. */
	private int[] ascendingDiagonalCounts;
	/** Array to count queens on descending diagonals, diagonal number = x + chess board size - 1 - y. */
	private int[] descendingDiagonalCounts;

	/** Current number of placedQueens */
	private int placedQueens;

	public BruteForceNQueensSolverOneQueenPerLine(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new boolean[chessboardSizeArg][chessboardSizeArg];
		columnCounts = new int[chessboardSizeArg];
		ascendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];
		descendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];
	}

	@Override
	public long solve() {

		// Start the algorithm at the first position
		solve(0, 0);

		// Return the number of solutions found
		return solutionCount;
	}

	/**
	 * Solving recursive method, do a brute-force algorithm by testing all combinations.
	 * 
	 * @param x X position on the chessboard
	 * @param y Y position on the chessboard
	 */
	private void solve(final int x, final int y) {

		// Put a queen on the current position
		chessboard[y][x] = true;
		columnCounts[x]++;
		final int ascendingDiagnonal = x + y;
		final int descendingDiagnonal = x + chessboardSize - 1 - y;
		ascendingDiagonalCounts[ascendingDiagnonal]++;
		descendingDiagonalCounts[descendingDiagnonal]++;
		placedQueens++;

		// All queens are sets then a solution may be present
		if (placedQueens >= chessboardSize) {
			if (checkSolutionChessboard()) {
				solutionCount++;
				print();
			}
		}
		else {

			// End of chessboard check
			if (y + 1 < chessboardSize) {
				// Go to the next line
				solve(0, y + 1);
			}
		}

		// Remove the placed queen
		placedQueens--;
		ascendingDiagonalCounts[ascendingDiagnonal]--;
		descendingDiagonalCounts[descendingDiagnonal]--;
		columnCounts[x]--;
		chessboard[y][x] = false;

		// End of line check
		if (x + 1 < chessboardSize) {
			// Go to the next position on the line
			solve(x + 1, y);
		}
	}

	/**
	 * Check if a chessboard with N queens is a solution (only one queens per lines, columns and diagnonals).
	 * 
	 * @return true if the chessboard contain a solution, false otherwise
	 */
	private boolean checkSolutionChessboard() {

		// Check if 2 queens are on the same line and column
		for (int x = 0; x < columnCounts.length; x++) {

			if (columnCounts[x] > 1) {
				return false;
			}
		}

		// Check if 2 queens are on the same column
		for (int i = 0; i < ascendingDiagonalCounts.length; i++) {

			if (ascendingDiagonalCounts[i] > 1 || descendingDiagonalCounts[i] > 1) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void reset() {

		super.reset();

		if (chessboard.length != chessboardSize) {
			chessboard = new boolean[chessboardSize][chessboardSize];
			columnCounts = new int[chessboardSize];
			ascendingDiagonalCounts = new int[chessboardSize * 2 - 1];
			descendingDiagonalCounts = new int[chessboardSize * 2 - 1];
		}
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return chessboard[y][x];
	}
}
