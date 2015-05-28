package com.github.sbugat.nqueens.solvers.backtracking;

import com.github.sbugat.nqueens.GenericNQueensSolver;

/**
 * Back-tracking algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BackTrackingNQueensSolverArray extends GenericNQueensSolver {

	/** Chessboard represented by a 2 dimensional array. */
	private int[][] chessboard;

	public BackTrackingNQueensSolverArray(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new int[chessboardSizeArg][chessboardSizeArg];
	}

	@Override
	public long solve() {

		// Start the algorithm at the first line
		solve(0);

		// Return the number of solutions found
		return solutionCount;
	}

	/**
	 * Solving recursive method, do a back-tracking algorithm by testing all valid combinations.
	 * 
	 * @param y line position on the chessboard
	 */
	private void solve(final int y) {

		for (int x = 0; x < chessboardSize; x++) {

			// Put a queen on the current position
			chessboard[x][y] = 1;

			if (checkValidChessboard()) {
				// Last line: all queens are sets then a solution is found present
				if (y + 1 >= chessboardSize) {
					solutionCount++;
					print();
				}
				else {
					// Go to the next line
					solve(y + 1);
				}
			}

			// Remove the current queen
			chessboard[x][y] = 0;
		}
	}

	/**
	 * Check if the current chessboard is valid (only one queens per lines, columns and diagnonals).
	 * 
	 * @return true if the chessboard is valid, false otherwise
	 */
	private boolean checkValidChessboard() {

		// Check if 2 queens are on the same line
		for (int y = 0; y < chessboardSize; y++) {

			boolean usedLine = false;
			for (int x = 0; x < chessboardSize; x++) {

				if (1 == chessboard[x][y]) {
					if (usedLine) {
						return false;
					}
					usedLine = true;
				}
			}
		}

		// Check if 2 queens are on the same column
		for (int x = 0; x < chessboardSize; x++) {

			boolean usedColumn = false;
			for (int y = 0; y < chessboardSize; y++) {

				if (1 == chessboard[x][y]) {
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

				if (x >= 0 && x < chessboardSize && 1 == chessboard[x][y]) {

					if (usedDiagonal) {
						return false;
					}
					usedDiagonal = true;
				}
			}
		}

		// Check if 2 queens are on the same ascending diagonal
		for (int diagonal = 0; diagonal < chessboardSize * 2 - 1; diagonal++) {

			boolean usedDiagonal = false;

			for (int y = 0; y < chessboardSize; y++) {

				final int x = diagonal - chessboardSize + 1 + y;

				if (x >= 0 && x < chessboardSize && 1 == chessboard[x][y]) {

					if (usedDiagonal) {
						return false;
					}
					usedDiagonal = true;
				}
			}
		}

		return true;
	}

	@Override
	public void reset() {

		super.reset();

		if (chessboard.length != chessboardSize) {

			chessboard = new int[chessboardSize][chessboardSize];
		}
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return 1 == chessboard[x][y];
	}
}
