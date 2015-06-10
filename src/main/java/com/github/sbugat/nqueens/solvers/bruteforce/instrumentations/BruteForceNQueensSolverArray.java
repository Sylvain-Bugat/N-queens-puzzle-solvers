package com.github.sbugat.nqueens.solvers.bruteforce.instrumentations;

import com.github.sbugat.nqueens.GenericInstrumentedNQueensSolver;

/**
 * Brute-force algorithm for the N queens puzzle solver with a count of currently placed queens.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverArray extends GenericInstrumentedNQueensSolver {

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
		methodCallsCount++;
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
		queenPlacementsCount++;
		squareWritesCount++;
		chessboard[x][y] = true;
		placedQueens++;

		// All queens are sets on the chessboard then a solution may be present
		explicitTestsCount++;
		if (placedQueens >= chessboardSize) {
			methodCallsCount++;
			explicitTestsCount++;
			if (checkSolutionChessboard()) {
				solutionCount++;
				methodCallsCount++;
				print();
			}
		}
		else {

			// Recursive call to the next position
			final int nextX = (x + 1) % chessboardSize;
			// Switch to the next line
			explicitTestsCount++;
			if (0 == nextX) {

				// End of the chessboard check
				explicitTestsCount++;
				if (y + 1 < chessboardSize) {
					methodCallsCount++;
					solve(nextX, y + 1);
				}
			}
			else {
				methodCallsCount++;
				solve(nextX, y);
			}
		}

		// Remove the queen on the current position
		placedQueens--;
		squareWritesCount++;
		chessboard[x][y] = false;

		// Recursive call to the next position
		final int nextX = (x + 1) % chessboardSize;
		// Switch to the next line
		explicitTestsCount++;
		if (0 == nextX) {

			// End of the chessboard check
			explicitTestsCount++;
			if (y + 1 < chessboardSize) {
				methodCallsCount++;
				solve(nextX, y + 1);
			}
		}
		else {
			methodCallsCount++;
			solve(nextX, y);
		}
	}

	/**
	 * Check if a chessboard with N queens is a solution (only one queens per lines, columns and diagnonals).
	 * 
	 * @return true if the chessboard contain a solution, false otherwise
	 */
	private boolean checkSolutionChessboard() {

		// Check if 2 queens are on the same line
		implicitTestsCount++;
		for (int y = 0; y < chessboardSize; y++) {

			boolean usedLine = false;
			implicitTestsCount++;
			for (int x = 0; x < chessboardSize; x++) {

				explicitTestsCount++;
				squareReadsCount++;
				if (chessboard[x][y]) {
					explicitTestsCount++;
					if (usedLine) {
						return false;
					}
					usedLine = true;
				}

				implicitTestsCount++;
			}

			implicitTestsCount++;
		}

		// Check if 2 queens are on the same column
		implicitTestsCount++;
		for (int x = 0; x < chessboardSize; x++) {

			boolean usedColumn = false;
			implicitTestsCount++;
			for (int y = 0; y < chessboardSize; y++) {

				explicitTestsCount++;
				squareReadsCount++;
				if (chessboard[x][y]) {
					explicitTestsCount++;
					if (usedColumn) {
						return false;
					}
					usedColumn = true;
				}

				implicitTestsCount++;
			}

			implicitTestsCount++;
		}

		// Check if 2 queens are on the same descending diagonal
		implicitTestsCount++;
		for (int diagonal = 0; diagonal < chessboardSize * 2 - 1; diagonal++) {
			boolean usedDiagonal = false;
			implicitTestsCount++;
			for (int y = 0; y < chessboardSize; y++) {
				final int x = diagonal - y;
				explicitTestsCount++;
				if (x >= 0) {
					explicitTestsCount++;
					if (x < chessboardSize) {
						explicitTestsCount++;
						squareReadsCount++;
						if (chessboard[x][y]) {
							explicitTestsCount++;
							if (usedDiagonal) {
								return false;
							}
							usedDiagonal = true;
						}
					}
				}

				implicitTestsCount++;
			}

			implicitTestsCount++;
		}

		// Check if 2 queens are on the same ascending diagonal
		implicitTestsCount++;
		for (int diagonal = 0; diagonal < chessboardSize * 2 - 1; diagonal++) {
			boolean usedDiagonal = false;
			implicitTestsCount++;
			for (int y = 0; y < chessboardSize; y++) {
				final int x = diagonal - chessboardSize + 1 + y;
				explicitTestsCount++;
				if (x >= 0) {
					explicitTestsCount++;
					if (x < chessboardSize) {
						explicitTestsCount++;
						squareReadsCount++;
						if (chessboard[x][y]) {

							explicitTestsCount++;
							if (usedDiagonal) {
								return false;
							}
							usedDiagonal = true;
						}
					}
				}

				implicitTestsCount++;
			}

			implicitTestsCount++;
		}

		return true;
	}

	@Override
	public boolean getChessboardPosition(final int x, final int y) {
		return chessboard[x][y];
	}

	@Override
	public String getName() {
		return "Array brute-force";
	}
}
