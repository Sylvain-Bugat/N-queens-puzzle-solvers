package com.github.sbugat.nqueens.solvers.bruteforce;

import com.github.sbugat.nqueens.GenericNQueensSolver;

/**
 * Brute-force algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverOneDimensionArray extends GenericNQueensSolver {

	/** Chessboard with only one dimension with all lines. */
	private boolean[] chessboard;
	/** Current number of placedQueens */
	private int placedQueens;

	public BruteForceNQueensSolverOneDimensionArray(final int chessboardSizeArg, final boolean printSolutionArg) {

		super(chessboardSizeArg, printSolutionArg);

		chessboard = new boolean[chessboardSizeArg * chessboardSizeArg];
	}

	@Override
	public long solve() {

		// Start the algorithm at the first position
		solve(0);

		// Return the number of solutions found
		return solutionCount;
	}

	/**
	 * Solving recursive method, do a brute-force algorithm by testing all possible combinations.
	 * 
	 * @param position index of the unique dimension
	 */
	private void solve(final int position) {

		// Put a queen on the current position
		chessboard[position] = true;
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

		// Check if 2 queens are on the same line
		for (int y = 0; y < chessboard.length; y += chessboardSize) {

			boolean usedLine = false;
			for (int x = y; x < y + chessboardSize; x++) {

				if (chessboard[x]) {
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
			for (int i = x; i < chessboard.length; i += chessboardSize) {

				if (chessboard[i]) {
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

					if (chessboard[y * chessboardSize + x]) {
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

					if (chessboard[y * chessboardSize + x]) {
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
	public void reset() {

		super.reset();

		final int chessboardArrayLength = chessboardSize * chessboardSize;
		if (chessboard.length != chessboardArrayLength) {
			chessboard = new boolean[chessboardArrayLength];
		}
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return chessboard[y * chessboardSize + x];
	}
}
