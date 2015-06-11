package com.github.sbugat.nqueens.solvers.bruteforce.instrumentations;

import com.github.sbugat.nqueens.GenericInstrumentedNQueensSolver;

/**
 * Brute-force algorithm for the N queens puzzle solver.
 * 
 * @author Sylvain Bugat
 * 
 */
public final class BruteForceNQueensSolverGridConstraints extends GenericInstrumentedNQueensSolver {

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
		methodCallsCount++;
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
		squareWritesCount++;
		chessboard[position] = true;
		lineCounts[y]++;
		columnCounts[x]++;
		final int ascendingDiagonal = x + y;
		final int descendingDiagonal = x + chessboardSize - 1 - y;
		ascendingDiagonalCounts[ascendingDiagonal]++;
		descendingDiagonalCounts[descendingDiagonal]++;
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
			if (position + 1 < chessboard.length) {
				methodCallsCount++;
				solve(position + 1);
			}
		}

		// Remove the queen on the current position
		placedQueens--;
		ascendingDiagonalCounts[ascendingDiagonal]--;
		descendingDiagonalCounts[descendingDiagonal]--;
		lineCounts[y]--;
		columnCounts[x]--;
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

		// Check if 2 queens are on the same line and column
		implicitTestsCount++;
		for (int i = 0; i < columnCounts.length; i++) {

			explicitTestsCount++;
			if (columnCounts[i] > 1) {
				return false;
			}
			else {
				explicitTestsCount++;
				if (lineCounts[i] > 1) {
					return false;
				}
			}

			implicitTestsCount++;
		}

		// Check if 2 queens are on the same column
		implicitTestsCount++;
		for (int i = 0; i < ascendingDiagonalCounts.length; i++) {

			explicitTestsCount++;
			if (ascendingDiagonalCounts[i] > 1) {
				return false;
			}
			else {
				explicitTestsCount++;
				if (descendingDiagonalCounts[i] > 1) {
					return false;
				}
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

	@Override
	public String getName() {
		return "Grid constraints array brute-force";
	}
}
