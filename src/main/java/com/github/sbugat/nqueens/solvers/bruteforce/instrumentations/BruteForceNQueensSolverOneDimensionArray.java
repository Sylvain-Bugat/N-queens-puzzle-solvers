package com.github.sbugat.nqueens.solvers.bruteforce.instrumentations;

import com.github.sbugat.nqueens.GenericInstrumentedNQueensSolver;

/**
 * Brute-force algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverOneDimensionArray extends GenericInstrumentedNQueensSolver {

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
		methodCallsCount++;
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
		queenPlacementsCount++;
		squareWritesCount++;
		chessboard[position] = true;
		placedQueens++;

		// All queens are sets then a solution may be present
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

			// End of the chessboard check
			explicitTestsCount++;
			if (position + 1 < chessboard.length) {

				methodCallsCount++;
				solve(position + 1);
			}
		}

		// Remove the queen on the current position
		placedQueens--;
		squareWritesCount++;
		chessboard[position] = false;

		// End of the chessboard check
		explicitTestsCount++;
		if (position + 1 < chessboard.length) {

			methodCallsCount++;
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
		implicitTestsCount++;
		for (int y = 0; y < chessboard.length; y += chessboardSize) {

			boolean usedLine = false;
			implicitTestsCount++;
			for (int x = y; x < y + chessboardSize; x++) {

				squareReadsCount++;
				explicitTestsCount++;
				if (chessboard[x]) {
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
			for (int i = x; i < chessboard.length; i += chessboardSize) {

				squareReadsCount++;
				explicitTestsCount++;
				if (chessboard[i]) {
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
				if (x >= 0 && x < chessboardSize) {

					explicitTestsCount++;
					squareReadsCount++;
					if (chessboard[y * chessboardSize + x]) {

						explicitTestsCount++;
						if (usedDiagonal) {
							return false;
						}

						usedDiagonal = true;
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
				if (x >= 0 && x < chessboardSize) {

					explicitTestsCount++;
					squareReadsCount++;
					if (chessboard[y * chessboardSize + x]) {

						explicitTestsCount++;
						if (usedDiagonal) {
							return false;
						}
						usedDiagonal = true;
					}
				}

				implicitTestsCount++;
			}

			implicitTestsCount++;
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

	@Override
	public String getName() {
		return "One-dimension array brute-force";
	}
}
