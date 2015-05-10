package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolver;

/**
 * Brute-force algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverGridConstraints extends GenericNQueensSolver {

	/** Chessboard with only one dimension with all lines. */
	private boolean[] chessboard;
	/** Array to count queens on each column. */
	private int[] columnCounts;
	/** Array to count queens on each line. */
	private int[] lineCounts;
	/** Array to count queens on ascending diagonals, diagonal number = x + y. */
	private int[] ascendingDiagonalCounts;
	/** Array to count queens on descending diagonals, diagonal number = x + chessboard size - 1 - y. */
	private int[] descendingDiagonalCounts;

	/** Current number of placedQueens */
	private int placedQueens;

	public BruteForceNQueensSolverGridConstraints(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new boolean[chessboardSizeArg * chessboardSizeArg];
		columnCounts = new int[chessboardSizeArg];
		lineCounts = new int[chessboardSizeArg];
		ascendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];
		descendingDiagonalCounts = new int[chessboardSizeArg * 2 - 1];
	}

	@Override
	public long solve() {

		// Start the algorithm at the first position
		solve(0);

		// Return the number of solutions found
		return solutionCount;
	}

	/**
	 * Solving recursive method, do a brute-force algorithm by testing all combinations.
	 * 
	 * @param position current one-dimention position starting at 0
	 */
	private void solve(final int position) {

		// Recalculate X and Y coordinates
		final int y = position / chessboardSize;
		final int x = position % chessboardSize;

		// Put a queen on the current position
		chessboard[position] = true;
		lineCounts[y]++;
		columnCounts[x]++;
		final int ascendingDiagonal = x + y;
		final int descendingDiagonal = x + chessboardSize - 1 - y;
		ascendingDiagonalCounts[ascendingDiagonal]++;
		descendingDiagonalCounts[descendingDiagonal]++;
		placedQueens++;

		// All queens are sets then a solution may be present
		if (placedQueens >= chessboardSize) {
			if (checkSolutionChessboard()) {
				solutionCount++;
				print();
			}
		}
		else {

			// End of the chessboard check
			if (position + 1 < chessboard.length) {
				solve(position + 1);
			}
		}

		// Remove the queen on the current position
		placedQueens--;
		ascendingDiagonalCounts[ascendingDiagonal]--;
		descendingDiagonalCounts[descendingDiagonal]--;
		lineCounts[y]--;
		columnCounts[x]--;
		chessboard[position] = false;

		// End of the chessboard check
		if (position + 1 < chessboard.length) {
			solve(position + 1);
		}
	}

	/**
	 * Check if a chessboard with N queens is a solution (only one queens per lines, columns and diagnonals).
	 * 
	 * @return true if the chessboard contain a solution, false otherwise
	 */
	private boolean checkSolutionChessboard() {

		// Check if 2 queens are on the same line and column
		for (int i = 0; i < columnCounts.length; i++) {

			if (columnCounts[i] > 1 || lineCounts[i] > 1) {
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

		final int chessboardArrayLength = chessboardSize * chessboardSize;
		if (chessboard.length != chessboardArrayLength) {
			chessboard = new boolean[chessboardArrayLength];
			columnCounts = new int[chessboardSize];
			lineCounts = new int[chessboardSize];
			ascendingDiagonalCounts = new int[chessboardSize * 2 - 1];
			descendingDiagonalCounts = new int[chessboardSize * 2 - 1];
		}
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return chessboard[y * chessboardSize + x];
	}
}
