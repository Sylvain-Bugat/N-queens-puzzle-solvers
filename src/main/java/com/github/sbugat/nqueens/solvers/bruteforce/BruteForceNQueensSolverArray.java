package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolver;

/**
 * Brute-force algorithm for the N queens puzzle solver with a count of currently placed queens.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverArray extends GenericNQueensSolver {

	/** Chessboard represented by a 2 dimensional array. */
	private final boolean[][] chessboard;
	/** Current number of queens on the chessboard. */
	private int placedQueens;

	public BruteForceNQueensSolverArray(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new boolean[chessboardSizeArg][chessboardSizeArg];
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
		chessboard[x][y] = true;
		placedQueens++;

		// All queens are sets on the chessboard then a solution may be present
		if (placedQueens >= chessboardSize) {
			if (checkSolutionChessboard()) {
				solutionCount++;
				print();
			}
		}
		else {

			// Recursive call to the next position
			final int nextX = (x + 1) % chessboardSize;
			// Switch to the next line
			if (0 == nextX) {

				// End of the chessboard check
				if (y + 1 < chessboardSize) {
					solve(nextX, y + 1);
				}
			}
			else {
				solve(nextX, y);
			}
		}

		// Remove the queen on the current position
		placedQueens--;
		chessboard[x][y] = false;

		// Recursive call to the next position
		final int nextX = (x + 1) % chessboardSize;
		// Switch to the next line
		if (0 == nextX) {

			// End of the chessboard check
			if (y + 1 < chessboardSize) {
				solve(nextX, y + 1);
			}
		}
		else {
			solve(nextX, y);
		}
	}

	/**
	 * Check if a chessboard with N queens is a solution (only one queens per lines, columns and diagnonals).
	 * 
	 * @return true if the chessboard contain a solution, false otherwise
	 */
	private boolean checkSolutionChessboard() {

		// Check if 2 queens are on the same column
		for (int y = 0; y < chessboardSize; y++) {

			boolean usedLine = false;
			for (int x = 0; x < chessboardSize; x++) {

				if (chessboard[x][y]) {
					if (usedLine) {
						return false;
					}
					usedLine = true;
				}
			}
		}

		// Check if 2 queens are on the same line
		for (int x = 0; x < chessboardSize; x++) {

			boolean usedColumn = false;
			for (int y = 0; y < chessboardSize; y++) {

				if (chessboard[x][y]) {
					if (usedColumn) {
						return false;
					}
					usedColumn = true;
				}
			}
		}

		// Check if 2 queens are on the same descending diagonal
		for (int diagonal = 0; diagonal < chessboardSize * 2 - 1; diagonal++) {

			boolean usedDiagonal = false;

			for (int y = 0; y < chessboardSize; y++) {

				final int x = diagonal - y;

				if (x >= 0 && x < chessboardSize) {

					if (chessboard[x][y]) {
						if (usedDiagonal) {
							return false;
						}
						usedDiagonal = true;
					}
				}
			}
		}

		// Check if 2 queens are on the same ascending diagonal
		for (int diagonal = 0; diagonal < chessboardSize * 2 - 1; diagonal++) {

			boolean usedDiagonal = false;

			for (int y = 0; y < chessboardSize; y++) {

				final int x = diagonal - chessboardSize + 1 + y;

				if (x >= 0 && x < chessboardSize) {

					if (chessboard[x][y]) {
						if (usedDiagonal) {
							return false;
						}
						usedDiagonal = true;
					}
				}
			}
		}

		return true;
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return chessboard[x][y];
	}
}
